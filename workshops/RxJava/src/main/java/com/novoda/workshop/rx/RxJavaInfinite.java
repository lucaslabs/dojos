package com.novoda.workshop.rx;

import com.novoda.workshop.rx.observer.IntegerPrinterObserver;
import com.novoda.workshop.rx.observer.StringPrinterObserver;
import rx.Observable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.novoda.workshop.rx.Functions.attachIndexToSentance;
import static com.novoda.workshop.rx.Functions.concat;

public class RxJavaInfinite {

    private static final List<String> SENTENCES = Arrays.asList("This is the first sentence", "I want those to be enumerated", "How would you ask?", "That is yours to find out!");

    private static final Iterable<Integer> INFINITE_ITERABLE = new Iterable<Integer>() {
        @Override
        public Iterator<Integer> iterator() {
            return new IntegerIterator();
        }
    };

    /*
        TODO:
        - Get one element from the infinite observable.
        - Get the 20 first integers
        - Enumerate the sentences by adding their index in front of it.
        - Concatenate the sequences into one line.
     */

    public static void main(String[] args) throws InterruptedException {

//        System.out.println("\nObservable.from(INFINITE_ITERABLE).subscribe(new IntegerPrinterObserver());");
//        Observable.from(INFINITE_ITERABLE).subscribe(new IntegerPrinterObserver());

        System.out.println("\nGet one element from the infinite observable");
        Observable.from(INFINITE_ITERABLE)
                .first()
                .subscribe(new IntegerPrinterObserver());

        System.out.println("\nGet the 20 first integers");
        Observable.from(INFINITE_ITERABLE)
                .take(20)
                .subscribe(new IntegerPrinterObserver());

        System.out.println("\nEnumerate the sentences by adding their index in front of it");
        Observable.zip(Observable.from(SENTENCES),
                Observable.from(INFINITE_ITERABLE),
                attachIndexToSentance()
        ).subscribe(new StringPrinterObserver());

        System.out.println("\nConcatenate the sequences into one line");
        Observable.from(SENTENCES)
                .scan(concat())
                .last()
                .subscribe(new StringPrinterObserver());

    }
}
