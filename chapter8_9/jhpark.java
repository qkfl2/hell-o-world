package com.company;

import org.apache.commons.lang3.ArrayUtils;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class Main {

    public static void main(String[] args) {
        numberCombination()
            .map(it -> kaprekar.calc(it, 1).invoke())
            .max(Comparator.comparing(String::valueOf))
            .ifPresent(System.out::println);
    }

    private static Stream<Integer> numberCombination() {
        final Integer[] digits
                = ArrayUtils.toObject(IntStream.rangeClosed(0, 9).map(i -> 9 - i).toArray());

        final Generator<Integer> combination
                = Factory.createMultiCombinationGenerator(Factory.createVector(digits), 4);

        return StreamSupport.stream(combination.spliterator(), true)
                    .filter(it -> !Number.isAllSame(it))
                    .map(Number::makeValue);
    }

    private static class kaprekar {
        private static TailCall calc(int number, int count) {
            return number > 10
                    ? TailCalls.call(() -> calc(number - Number.reverse(number), count + 1))
                    : TailCalls.done(count);
        }
    }

    private static class Number {
        private static int reverse(int number) {
            return number >= 10
                    ? (number % 10) * (int) Math.pow(10, (int) Math.log10(number)) + reverse(number / 10)
                    : number;
        }

        private static boolean isAllSame(ICombinatoricsVector<Integer> vector) {
            return StreamSupport.stream(vector.spliterator(), true)
                    .distinct().count() == 1;
        }

        private static int makeValue(ICombinatoricsVector<Integer> vector) {
            return StreamSupport.stream(vector.spliterator(), true)
                    .reduce(0, (number, digit) -> number = 10 * number + digit) / 10;
        }
    }

}
