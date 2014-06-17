package com.novoda.workshop.rx.stage1;

import rx.Observable;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;

import java.util.List;

class Functions {

    static Func1<Integer, Boolean> isEven() {
        return new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return isEven(integer);
            }
        };
    }

    private static boolean isEven(Integer integer) {
        return (integer % 2) == 0;
    }

    static Func1<Integer, Integer> multiplyBy2() {
        return new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return timesTwo(integer);
            }
        };
    }

    private static int timesTwo(Integer integer) {
        return integer * 2;
    }

    static Func1<Integer, String> prependString() {
        return new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "Integer: " + integer;
            }
        };
    }

    static Func1<Integer, Observable<Integer>> repeat3Times() {
        return new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                return repeat3TimesObservable(integer);
            }
        };
    }

    static Observable<Integer> repeat3TimesObservable(Integer integer) {
        return Observable.just(integer).repeat(3);
    }

    static Func1<Integer, Observable<Integer>> repeatEven3TimesAndOddJustOnce() {
        return new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                if (isEven().call(integer)) {
                    return repeat3TimesObservable(integer);
                }
                return Observable.just(integer);
            }
        };
    }

    static Func1<Integer, Observable<Integer>> failIfItemIsNotEven() {
        return new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                if (isEven().call(integer)) {
                    return Observable.just(integer);
                }
                return Observable.error(new IllegalArgumentException("The number: " + integer + " is not even!"));
            }
        };
    }

    static Func1<OnErrorThrowable, Observable<? extends Integer>> onErrorMultiplyBy2(final List<Integer> integers) {
        return new Func1<OnErrorThrowable, Observable<? extends Integer>>() {
            @Override
            public Observable<? extends Integer> call(OnErrorThrowable onErrorThrowable) {
                return Observable.from(integers)
                        .map(multiplyBy2());
            }
        };
    }
}
