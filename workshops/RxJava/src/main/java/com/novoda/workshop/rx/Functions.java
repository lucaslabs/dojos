package com.novoda.workshop.rx;

import rx.Observable;
import rx.functions.Func1;

import java.util.IllegalFormatException;

public class Functions {

    public static Func1<Integer, Boolean> isEven() {
        return new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return isEven(integer);
            }
        };
    }

    public static Func1<Integer, Integer> multiplyByTwo() {
        return new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return multiplyByTwo(integer);
            }
        };
    }

    public static Func1<Integer, String> prepandString() {
        return new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return prepandString(integer);
            }
        };
    }

    public static Func1<Integer, Observable<Integer>> repeatThreeTime() {
        return new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                return repeatThreeTime(integer);
            }
        };
    }

    public static Func1<Integer, Observable<Integer>> repeat() {
        return new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                return repeat(integer);
            }
        };
    }

    public static Func1<Integer, Integer> failIfNotEven() {
        return new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                if(!isEven(integer)) {
                    throw 
                } else {
                    return integer;
                }
            }
        };
    }

    private static boolean isEven(Integer integer) {
        return (integer % 2) == 0;
    }

    private static Observable failIfNotEven(Integer integer) {
        if(!isEven(integer)) {

        }
    }

    private static Integer multiplyByTwo(Integer integer) {
        return integer * 2;
    }

    private static String prepandString(Integer integer) {
        return "Integer: " + integer;
    }

    private static Observable<Integer> repeatThreeTime(Integer integer) {
        return Observable.from(integer, integer, integer);
    }

    private static Observable<Integer> repeat(Integer integer) {
        if(integer % 2 == 0) {
            return Observable.from(integer, integer, integer);
        } else {
            return Observable.from(integer);
        }
    }

}
