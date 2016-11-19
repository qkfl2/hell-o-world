package com.company;

import com.sun.tools.javac.util.Pair;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Stream.iterate(new Pair<>(1L, 1L), p -> new Pair<>(p.snd, p.fst + p.snd))
                .filter(p -> p.fst > 100000)
                .filter(p -> isPrime(p.fst))
                .findFirst()
                .ifPresent(p -> System.out.println(p.fst));
    }

    private static boolean isPrime(final long p) {
        return LongStream.rangeClosed(2, (long)(Math.sqrt(p))).allMatch(n -> p % n != 0);
    }
}
