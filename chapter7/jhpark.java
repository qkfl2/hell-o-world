package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(eat(100000000, 1).invoke());
    }

    private static TailCall eat(float gram, long count) {
        return gram > 10 ? TailCalls.call(() -> eat(gram - (gram * 0.001f), count + 1)) : TailCalls.done(count);
    }

}
