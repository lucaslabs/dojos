package com.novoda.workshop.rx;

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
