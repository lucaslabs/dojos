package com.novoda.workshop.rx;

import rx.functions.Func2;

public class InfiniteFunctions {

    public static Func2<String, Integer, String> prependIndexInFrontOfStrings() {
        return new Func2<String, Integer, String>() {
            @Override
            public String call(String s, Integer integer) {
                return String.valueOf(integer) + " - " + s;
            }
        };
    }

    public static Func2<String, String, String> concatenateStrings() {
        return new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {
                return s + " " + s2;
            }
        };
    }
}
