package com.novoda.workshop.rx;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

public class Functions {

    public static Func2<String, String, String> concat() {
        return new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {
                return s + " " + s2;
            }
        };
    }

    public static Func2<String, Integer, String> prepend() {
        return new Func2<String, Integer, String>() {
            @Override
            public String call(String s, Integer integer) {
                return integer + ": " + s;
            }
        };
    }

    public static Func1<Integer, Observable<Integer>> failIfNotEven() {
        return new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                if (isEven(integer)) {
                    return Observable.just(integer);
                }
                return Observable.error(new IllegalArgumentException());
            }
        };
    }

    public static Func1<Integer, Observable<Integer>> threeTimesIfEven() {
        return new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                if (isEven(integer)) {
                    return repeat(integer, 3);
                }
                return Observable.just(integer);
            }
        };
    }

    public static Func1<Integer, Observable<Integer>> threeTimes() {
        return new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                return repeat(integer, 3);
            }
        };
    }

    public static <T> Observable<T> repeat(T value, int count) {
        return Observable.just(value).repeat(count);
    }

    public static Func1<Integer, String> format() {
        return new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "Integer : " + integer;
            }
        };
    }

    public static Func1<Integer, Integer> multiplyBy2() {
        return new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return integer * 2;
            }
        };
    }

    public static Func1<Integer, Boolean> isEven() {
        return new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return isEven(integer);
            }
        };
    }

    public static Func1<Integer, Integer> times(final int factor) {
        return new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return times(integer, factor);
            }
        };
    }

    private static boolean isEven(Integer integer) {
        return (integer % 2) == 0;
    }
    private static Integer times(Integer integer, int factor) {
        return integer * factor;
    }
}
