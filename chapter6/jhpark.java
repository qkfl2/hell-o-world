package com.company;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Stream.iterate(new long[] {1, 1}, p -> new long[] {p[1], p[0] + p[1]})
                .filter(i -> i[0] > 100000)
                .filter(i -> isPrime(i[0]))
                .findFirst()
                .ifPresent(i -> System.out.println(i[0]));
    }

    private static boolean isPrime(final long number) {
        return LongStream.rangeClosed(2, (long)(Math.sqrt(number))).allMatch(n -> number % n != 0);
    }
}
