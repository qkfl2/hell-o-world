package com.company;

import org.apache.commons.lang3.ArrayUtils;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class Main {

    public static void main(String[] args) {
        numberCombinations()
                .map(it -> Kaprekar.calc(it, 1).invoke())
                .max(Comparator.comparing(String::valueOf))
                .ifPresent(System.out::println);
    }

    private static Stream<Integer> numberCombinations() {
        final Integer[] digits
                = ArrayUtils.toObject(IntStream.rangeClosed(0, 9).map(i -> 9 - i).toArray());

        final Generator<Integer> combination
                = Factory.createMultiCombinationGenerator(Factory.createVector(digits), 4);

        return StreamSupport.stream(combination.spliterator(), false)
                    .map(Number::makeValue)
                    .filter(it -> it != Number.shift(it));
    }

    private final static class Kaprekar {
        private static TailCall calc(int number, Integer count) {
            return number != 7641
                    ? TailCalls.call(() -> calc(Number.largest(number - Number.reverse(number)), count + 1))
                    : TailCalls.done(count);
        }
    }

    private final static class Number {
        private static int makeValue(final ICombinatoricsVector<Integer> vector) {
            return StreamSupport.stream(vector.spliterator(), false)
                    .reduce(0, (number, digit) -> number = 10 * number + digit) / 10;
        }

        private static int reverse(final int number) {
            return number >= 10
                    ? (number % 10) * (int) Math.pow(10, (int) Math.log10(number)) + reverse(number / 10)
                    : number;
        }

        private static int shift(final int number) {
            return (int) (number * 10 % Math.pow(10, (int) Math.log10(number) + 1)
                    + (number / Math.pow(10, (int) Math.log10(number))));
        }

        private static int largest(final int number) {
            int result = 0;

            for (int i = 9; i >= 0; i--) {
                int tmpNumber = number;

                while (tmpNumber > 0) {
                    int digit = tmpNumber % 10;

                    if (digit == i) {
                        result = (result * 10) + digit;
                    }

                    tmpNumber /= 10;
                }
            }

            return result * (int)(Math.pow(10, 3 - (int) Math.log10(result)));
        }

    }

}
