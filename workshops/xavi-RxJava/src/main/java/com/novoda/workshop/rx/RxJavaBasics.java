package com.novoda.workshop.rx;

import com.novoda.workshop.rx.observer.IntegerPrinterObserver;

import java.util.Arrays;
import java.util.List;

import com.novoda.workshop.rx.observer.StringPrinterObserver;
import rx.Observable;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;

import static com.novoda.workshop.rx.Functions.*;
import static rx.functions.Functions.not;

public class RxJavaBasics {

    static final List<Integer> INTEGERS = Arrays.asList(0, 1, 2, 3, 4, 5, 6);

    /*
        TODO:
        - Multiply all elements by 2
        - Get only even element and multiply them by 2
        - Prepend the string "Integer : " in front of every element
        - Repeat 3 time every element
        - Repeat 3 times every even element but only repeat once the odd ones
        - Fail if an element is not even.
        - If an element is not even restart and double the entire sequence
     */

    public static void main(String[] args) throws InterruptedException {
        originalArray();

        onlyEven();

        multiplyAllBy2();

        onlyEvenBy2();

        prependAString();

        repeatAll3Times();

        repeatEven3TimesAndOddOnly1();

        failIfAnyIsNotEven();

        restartIfNotEvenAndDoubleAll();
    }

    private static void originalArray() {
        System.out.println("Original array");
        Observable.from(INTEGERS)
                .subscribe(new IntegerPrinterObserver());
    }

    private static void onlyEven() {
        System.out.println("Only even elements");
        Observable.from(INTEGERS)
                .filter(isEven())
                .subscribe(new IntegerPrinterObserver());
    }

    private static void multiplyAllBy2() {
        System.out.println("Multiply by 2");
        Observable.from(INTEGERS)
                .map(multiplyBy2())
                .subscribe(new IntegerPrinterObserver());
    }

    private static void onlyEvenBy2() {
        System.out.println("Only Even Multiply by 2");
        Observable.from(INTEGERS)
                .filter(isEven())
                .map(multiplyBy2())
                .subscribe(new IntegerPrinterObserver());
    }

    private static void prependAString() {
        System.out.println("Add text in front");
        Observable.from(INTEGERS)
                .map(prependString())
                .subscribe(new StringPrinterObserver());
    }

    private static void repeatAll3Times() {
        System.out.println("Repeat all three times");
        Observable.from(INTEGERS)
                .flatMap(repeat3Times())
                .subscribe(new IntegerPrinterObserver());
    }

    private static void repeatEven3TimesAndOddOnly1() {
        System.out.println("Repeat even 3 times and odd just once");
        Observable.from(INTEGERS)
                .flatMap(repeatEven3TimesAndOddJustOnce())
                .subscribe(new IntegerPrinterObserver());
    }

    private static void failIfAnyIsNotEven() {
        System.out.println("Fail if any is not even");
        Observable.from(INTEGERS)
                .flatMap(failIfItemIsNotEven())
                .subscribe(new IntegerPrinterObserver());
    }

    private static void restartIfNotEvenAndDoubleAll() {
        System.out.println("Restart if not even & double all");
        Observable.from(INTEGERS)
                .flatMap(failIfItemIsNotEven())
                .onErrorFlatMap(onErrorMultiplyBy2(INTEGERS))
                .subscribe(new IntegerPrinterObserver());
    }

}
