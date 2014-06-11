package com.novoda.workshop.rx;

import rx.Observable;
import rx.functions.Func1;

public class Functions {

    public static Func1<Integer, Boolean> isEven() {
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

    public static Func1<Integer, Boolean> isOdd() {
        return new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return !isEven(integer);
            }
        };
    }

    public static Func1<Integer, Integer> doubleInt() {
        return new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return doubleInt(integer);
            }
        };
    }

    private static Integer doubleInt(Integer integer) {
        return integer * 2;
    }

    public static Func1<Integer, String> prependIntegerString() {
        return new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "Integer : " + integer;
            }
        };
    }

    public static Func1<Integer, Observable<Integer>> repeatThreeTimes() {
        return new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                    return Observable.from(integer).repeat(3);
            }
        };
    }

    public static Func1<Integer, Observable<Integer>> filterAndRepeatIfEven() {
        return new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                if (isEven(integer)) {
                    return Observable.from(integer).repeat(3);
                } else {
                    return Observable.from(integer);
                }
            }
        };
    }

    public static Func1<Integer, Observable<Integer>> evenOrError() {
        return new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                if (isEven(integer)) {
                    return Observable.from(integer);
                } else {
                    return Observable.error(new IllegalArgumentException("NO odd elements señor..."));
                }
            }
        };
    }
}
