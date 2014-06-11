package com.novoda.workshop.rx;

import rx.functions.Func1;
import rx.Observable;
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


    public static Func1<Integer, Integer> twoTimes() {
        return new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return twoTimes(integer);
            }
        };
    }

    private static Integer twoTimes(Integer integer) {
        return (integer * 2) ;
    }

    public static Func1<Integer, String> prependByInteger() {
        return new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return prependByInteger(integer);
            }
        };
    }

    private static String prependByInteger(Integer integer) {
        return ("Integer : " + integer) ;
    }

    public static Func1<Integer, Observable<Integer>> threeTimes() {
        return new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                return threeTimes(integer);
            }
        };
    }

    private static Observable<Integer> threeTimes(Integer integer) {
        return Observable.from(integer, integer, integer);
    }

    public static Func1<Integer, Observable<Integer>> threeTimesEven() {
        return new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                return threeTimesEven(integer);
            }
        };
    }

    private static Observable<Integer> threeTimesEven(Integer integer) {
        if ((integer % 2) == 0)
        {
            return Observable.from(integer, integer, integer);
        }
        else {
            return Observable.from(integer);
        }


    }

}
