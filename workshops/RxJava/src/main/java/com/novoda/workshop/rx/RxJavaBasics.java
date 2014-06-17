package com.novoda.workshop.rx;

import com.novoda.workshop.rx.observer.IntegerPrinterObserver;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

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
        final Observable<Integer> NUMBERS = Observable.from(INTEGERS);
        NUMBERS
//                .filter(isEven())
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(final Integer integer) {
                        if (integer % 2 == 0) {
                            return Observable.from(Collections.nCopies(3, integer));
                        }
                        return Observable.just(integer);
                    }
                })
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(final Integer integer) {
                        if (integer % 2 == 0) {
                            return integer;
                        }
                        throw new IllegalArgumentException("not even");
                    }
                })
                .onExceptionResumeNext(NUMBERS.filter(Functions.isEven()))
                .subscribe(new IntegerPrinterObserver());

    }

}
