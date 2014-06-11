package com.novoda.workshop.rx;

import com.novoda.workshop.rx.observer.IntegerPrinterObserver;
import com.novoda.workshop.rx.observer.StringPrinterObserver;

import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;

import static com.novoda.workshop.rx.Functions.isEven;

public class RxJavaBasics {

    private static final List<Integer> INTEGERS = Arrays.asList(0, 1, 2, 3, 4, 5, 6);

    /*
        TODO:
        - Multiply all elements by 2
        - Get only even element and multiply them by 2
        - Prepend the string "Integer : " in front of every element
        - Repeat 3 time every element
        - Repeat 3 times every even element only say once odd ones
        - Fail if an element is not even.
        - If an element is not even restart and double the entire sequence
     */

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Observable.from(INTEGERS).filter(isEven()).subscribe(new IntegerPrinterObserver());");
//        multiplyByTwo();
//        getOnlyEvenElementsAndMultiplyByTwo();
//        prependIntegerWord();
//        repeatThreeTimes();
//        repeatEven3TimesOddOnce();
        final Observable<Integer> from = Observable.from(INTEGERS);
        from
                .flatMap(onEvenThrowError())
                .onErrorFlatMap(new Func1<OnErrorThrowable, Observable<? extends Integer>>() {
                    @Override
                    public Observable<? extends Integer> call(OnErrorThrowable onErrorThrowable) {
                        return Observable.from(INTEGERS).map(new Func1<Integer, Integer>() {
                            @Override
                            public Integer call(Integer integer) {
                                return integer * 2;
                            }
                        });
                    }
                })
                .subscribe(new IntegerPrinterObserver());
    }

    private static Func1<Integer, Observable<Integer>> onEvenThrowError() {
        return new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                if (integer % 2 != 0)
                    return Observable.error(new InvalidAlgorithmParameterException());
                else
                    return Observable.just(integer);
            }
        };
    }

    private static void repeatEven3TimesOddOnce() {
        Observable.from(INTEGERS)
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        if (integer % 2 == 0)
                            return Observable.just(integer).repeat(3);
                        else
                            return Observable.just(integer);
                    }
                })
                .subscribe(new IntegerPrinterObserver());
    }

    private static void repeatThreeTimes() {
        Observable.from(INTEGERS)
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        return Observable.from(integer, integer, integer);
                    }
                })
                .subscribe(new IntegerPrinterObserver());
    }

    private static void prependIntegerWord() {
        Observable.from(INTEGERS)
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer * 2;
                    }
                })
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return "Integer : " + integer;
                    }
                })
                .subscribe(new StringPrinterObserver());
    }

    private static void getOnlyEvenElementsAndMultiplyByTwo() {
        Observable.from(INTEGERS)
                .filter(isEven())
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer * 2;
                    }
                })
                .subscribe(new IntegerPrinterObserver());
    }

    private static void multiplyByTwo() {
        Observable.from(INTEGERS)
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer * 2;
                    }
                })
                .subscribe(new IntegerPrinterObserver());
    }
}
