package com.novoda.workshop.rx;

import com.novoda.workshop.rx.observer.IntegerPrinterObserver;
import com.novoda.workshop.rx.observer.StringPrinterObserver;

import java.util.Arrays;
import java.util.List;

import rx.Observable;

import static com.novoda.workshop.rx.Functions.doubleInt;
import static com.novoda.workshop.rx.Functions.evenOrError;
import static com.novoda.workshop.rx.Functions.filterAndRepeatIfEven;
import static com.novoda.workshop.rx.Functions.isEven;
import static com.novoda.workshop.rx.Functions.prependIntegerString;
import static com.novoda.workshop.rx.Functions.repeatThreeTimes;

public class RxJavaBasics {

    private static final List<Integer> INTEGERS = Arrays.asList(0, 1, 2, 3, 4, 5, 6);

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Observable.from(INTEGERS).filter(isEven()).subscribe(new IntegerPrinterObserver());");
        Observable.from(INTEGERS)
                .filter(isEven())
                .subscribe(new IntegerPrinterObserver());

        printExerciseTitle("Multiply all elements by 2");
        Observable.from(INTEGERS)
                .map(doubleInt())
                .subscribe(new IntegerPrinterObserver());

        printExerciseTitle("Get only even element and multiply them by 2");
        Observable.from(INTEGERS)
                .filter(isEven())
                .map(doubleInt())
                .subscribe(new IntegerPrinterObserver());

        printExerciseTitle("Prepend the string \"Integer : \" in front of every element");
        Observable.from(INTEGERS)
                .map(prependIntegerString())
                .subscribe(new StringPrinterObserver());

        printExerciseTitle("Repeat 3 time every element");
        Observable.from(INTEGERS)
                .switchMap(repeatThreeTimes())
                .subscribe(new IntegerPrinterObserver());

        printExerciseTitle("Repeat 3 times every even element only say once odd ones");
        Observable.from(INTEGERS)
                .switchMap(filterAndRepeatIfEven())
                .subscribe(new IntegerPrinterObserver());

        printExerciseTitle("Fail if an element is not even");
        Observable.from(INTEGERS)
                .flatMap(evenOrError())
                .subscribe(new IntegerPrinterObserver());

        printExerciseTitle("If an element is not even restart and double the entire sequence");
        // TODO

    }

    private static void printExerciseTitle(String title) {
        System.out.println();
        System.out.println(title);
    }
}
