package Chapter6;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by codemaker88 on 2016-11-18.
 */
public class Chapter6 {
    public static void main(String[] args) {
        int result = Stream.iterate(0, FibonacciHelper::fibonacciGenerator)
                .filter(fibonacci -> fibonacci >= 100000)
                .filter(fibonacci -> isPrime(fibonacci))
                .findFirst()
                .get();

        System.out.println("result : " + result);
    }

    static class FibonacciHelper {
        static int mid = 1;
        static int end = 0;

        static int fibonacciGenerator(int front) {
            end = front + mid;
            front = mid;
            mid = end;
            return front;
        }
    }

    static boolean isPrime(final int number) {
        return number > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .noneMatch(divisor -> number % divisor == 0);
    }
}
