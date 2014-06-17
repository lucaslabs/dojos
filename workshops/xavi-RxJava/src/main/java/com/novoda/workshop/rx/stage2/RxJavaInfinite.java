package com.novoda.workshop.rx.stage2;

import com.novoda.workshop.rx.stage2.observer.IntegerPrinterObserver;
import com.novoda.workshop.rx.stage2.observer.StringPrinterObserver;
import rx.Observable;
import rx.functions.Func2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class RxJavaInfinite {
    private static final int TWENTY = 20;

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
        getOneElement();

        getTwentyElement();

        enumerateSentences();

        concatenateSequence();
    }

    private static void getOneElement() {
        System.out.println("\nGet one element");
        Observable.from(INFINITE_ITERABLE)
                .first()
                .subscribe(new IntegerPrinterObserver());
    }

    private static void getTwentyElement() {
        System.out.println("\nGet 20 elements");
        Observable.from(INFINITE_ITERABLE)
                .take(TWENTY)
                .subscribe(new IntegerPrinterObserver());
    }

    private static void enumerateSentences() {
        System.out.println("\nEnumerate sentences 2");
        Observable.zip(
                Observable.from(SENTENCES),
                Observable.from(INFINITE_ITERABLE),
                combineStringWithInteger())
                .subscribe(new StringPrinterObserver());

    }

    private static void concatenateSequence() {
        System.out.println("\nConcatenate sentences");
        Observable.from(SENTENCES).scan(joinStrings()).subscribe(new StringPrinterObserver());
    }

    private static Func2<String, Integer, String> combineStringWithInteger() {
        return new Func2<String, Integer, String>() {
            @Override
            public String call(String s, Integer i) {
                return i + " - " + s;
            }
        };
    }

    private static Func2<String, String, String> joinStrings() {
        return new Func2<String, String, String>() {
            @Override
            public String call(String s, String acc) {
                return s + " " + acc;
            }
        };
    }
}
