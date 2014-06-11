package com.novoda.workshop.rx;

import com.novoda.workshop.rx.observer.IntegerPrinterObserver;
import com.novoda.workshop.rx.observer.StringPrinterObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
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
        Observable.from(INTEGERS)
                .filter(isEven())
                .subscribe(new IntegerPrinterObserver());

        System.out.println("Multiply all elements by 2");
        Observable.from(INTEGERS)
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer*2;
                    }
                })
                .subscribe(new IntegerPrinterObserver());

        System.out.println("Prepend the string \"Integer : \" in front of every element");
        Observable.from(INTEGERS)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return "Integer : " + integer;
                    }
                })
                .subscribe(new StringPrinterObserver());

        System.out.println("Repeat 3 time every element");
        Observable.from(INTEGERS)
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        List<Integer> array = repeat(3, integer);
                        return Observable.from(array);
                    }
                })
                .subscribe(new IntegerPrinterObserver());

        System.out.println("Repeat 3 times every even element only say once odd ones");
        Observable.from(INTEGERS)
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        if ((integer % 2) == 0) {
                            return Observable.from(repeat(3, integer));
                        } else {
                            return Observable.from(integer);
                        }
                    }
                })
                .subscribe(new IntegerPrinterObserver());

        System.out.println("Fail if an element is not even");
        Observable.from(INTEGERS)
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        if ((integer % 2) == 0) {
                            return Observable.from(integer);
                        } else {
                            return Observable.error(new Exception("fail, " + integer + " is not even"));
                        }
                    }
                })
                .subscribe(new IntegerPrinterObserver());

        System.out.println("If an element is not even restart and double the entire sequence");
        /*
        Observable.from(INTEGERS)
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        if ((integer % 2) == 0) {
                            return Observable.from(integer);
                        } else {
                            return Observable.error(new Exception("fail, " + integer + " is not even"));
                        }
                    }
                })
                .subscribe(new IntegerPrinterObserver());
                */
    }

    private static List<Integer> repeat(final int pN, final Integer pInteger) {
        List<Integer> array = new ArrayList<Integer>(pN);
        for (int i = 0; i < pN; i++) {
            array.add(pInteger);
        }
        array.add(pInteger);
        return array;
    }
}
