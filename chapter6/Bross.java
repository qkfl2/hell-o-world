package chapter6;

import com.sun.istack.internal.NotNull;

import java.util.*;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by bross on 2016. 11. 16..
 */
public class Chapter6 {

    private final static long MNIMUM_NUMBER = 100000;

    public static void main(String[] args) {
        List<Function> functionList = new ArrayList<>();
        functionList.add(new LazyFunction());
        functionList.add(new MemoizationFunction());
        functionList.add(new RecussiveFunction());
        functionList.forEach(c -> {
            long start = System.currentTimeMillis();
            long result = (long) c.apply(null);
            long end = System.currentTimeMillis();
            System.out.println(c.getClass() + " [run time : " + (end - start) + "] [result : " + result + "]");
        });

    }

    private static int getFiboNumber(int n) {
        return n <= 1 ? n : getFiboNumber(n - 1) + getFiboNumber(n - 2);
    }

    private static boolean isPrime(final long number) {
        return number > 1 &&
                LongStream.rangeClosed(2, (long) Math.sqrt(number)).noneMatch(divisor -> number % divisor == 0);
    }


    private static class RecussiveFunction implements Function<Void, Long> {

        @NotNull
        @Override
        public Long apply(Void aVoid) {
            boolean flag = true;
            long findNumber = 0;
            for (int i = 1; flag; i++) {
                findNumber = getFiboNumber(i);
                if (findNumber > MNIMUM_NUMBER && isPrime(findNumber)) {
                    flag = false;
                }
            }
            return findNumber;
        }
    }

    private static class MemoizationFunction implements Function<Void, Long> {
        private Map<Integer, Long> maps = new HashMap<>();


        long getFiboNumber(int index) {
            return maps.get(index) == null ? calculateFiboNumber(index) : maps.get(index);
        }

        long calculateFiboNumber(int index) {
            if (index <= 2)
                maps.put(index, (long) 1);
            else
                maps.put(index, maps.get(index - 1) + maps.get(index - 2));

            return maps.get(index);
        }

        @NotNull
        @Override
        public Long apply(Void aVoid) {
            boolean flag = true;
            long findNumber = 0;

            for (int i = 1; flag; i++) {
                findNumber = getFiboNumber(i);
                if (findNumber > MNIMUM_NUMBER && isPrime(findNumber)) {
                    flag = false;
                }
            }
            return findNumber;
        }
    }

    private static class LazyFunction implements Function<Void, Long> {

        @NotNull
        @Override
        public Long apply(Void aVoid) throws NoSuchElementException {
            return Stream.iterate(
                    new long[]{1, 1},
                    f -> new long[]{f[1], f[0] + f[1]}
            ).mapToLong(f -> f[0]).filter(l -> l > MNIMUM_NUMBER).filter(Chapter6::isPrime).findFirst().getAsLong();
        }
    }

}
