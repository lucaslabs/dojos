package com.novoda.workshop.rx;

import com.novoda.workshop.rx.observer.IntegerPrinterObserver;

import java.util.Arrays;
import java.util.List;

import com.novoda.workshop.rx.observer.StringPrinterObserver;
import rx.Observable;

import static com.novoda.workshop.rx.Functions.isEven;
import static com.novoda.workshop.rx.Functions.prependByInteger;
import static com.novoda.workshop.rx.Functions.twoTimes;
import static com.novoda.workshop.rx.Functions.threeTimes;
import static com.novoda.workshop.rx.Functions.threeTimesEven;

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

        //Multiply by 2
        Observable.from(INTEGERS).map(twoTimes())
                .subscribe(new IntegerPrinterObserver());

        //Get only even element and multiply them by 2
        Observable.from(INTEGERS).filter(isEven())
                .map(twoTimes())
                .subscribe(new IntegerPrinterObserver());

        //Prepend the string "Integer : " in front of every element
        Observable.from(INTEGERS).map(prependByInteger())
                .subscribe(new StringPrinterObserver());

        //Repeat 3 time every element
        Observable.from(INTEGERS).flatMap(threeTimes()).subscribe(new IntegerPrinterObserver());

        //Repeat 3 times every even element only say once odd ones
        Observable.from(INTEGERS).flatMap(threeTimesEven()).subscribe(new IntegerPrinterObserver());

        //Fail if an element is not even.
    }

}
