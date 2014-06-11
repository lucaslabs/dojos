package com.novoda.workshop.rx;

import com.novoda.workshop.rx.observer.IntegerPrinterObserver;

import java.util.Arrays;
import java.util.List;

import com.novoda.workshop.rx.observer.StringPrinterObserver;
import rx.Observable;

import static com.novoda.workshop.rx.Functions.*;

public class RxJavaBasics {

    private static final List<Integer> INTEGERS = Arrays.asList(0, 1, 2, 3, 4, 5, 6);

    /*
        TODO:






        - If an element is not even restart and double the entire sequence
     */

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Observable.from(INTEGERS).filter(isEven()).subscribe(new IntegerPrinterObserver());");
        Observable.from(INTEGERS)
                .filter(isEven())
                .subscribe(new IntegerPrinterObserver());

        //- Multiply all elements by 2
        Observable.from(INTEGERS)
                .map(multiplyByTwo())
                .subscribe(new IntegerPrinterObserver());

        //- Get only even element and multiply them by 2
        Observable.from(INTEGERS)
                .filter(isEven())
                .map(multiplyByTwo())
                .subscribe(new IntegerPrinterObserver());

        //- Prepend the string "Integer : " in front of every element
        Observable.from(INTEGERS)
                .map(prepandString())
                .subscribe(new StringPrinterObserver());

        //- Repeat 3 time every element
        Observable.from(INTEGERS)
                .flatMap(repeatThreeTime())
                .subscribe(new IntegerPrinterObserver());

        //- Repeat 3 times every even element only say once odd ones
        Observable.from(INTEGERS)
                .flatMap(repeat())
                .subscribe(new IntegerPrinterObserver());

        //- Fail if an element is not even.
        Observable.from(INTEGERS)
                .map(failIfNotEven())
                .subscribe(new IntegerPrinterObserver());

    }

}
