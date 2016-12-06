package chapter8_9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static chapter8_9.Chapter8_9.TailCalls.done;

/**
 * Created by bross on 2016. 11. 28..
 */
public class Chapter8_9 {


    public static List<Integer> partSameNumberList;

    final static int MIN = 1000;
    final static int MAX = 9999;

    @FunctionalInterface
    public interface TailCall<T> {
        TailCall<T> apply();

        default boolean isComplete() {
            return false;
        }

        default T result() {
            throw new Error("not implemented");
        }

        default T invoke() {
            return Stream.iterate(this, TailCall::apply)
                    .filter(TailCall::isComplete)
                    .findFirst()
                    .get()
                    .result();
        }
    }

    public static class TailCalls {
        public static <T> TailCall<T> call(final TailCall<T> nextCall) {
            return nextCall;
        }

        public static <T> TailCall<T> done(final T value) {
            return new TailCall<T>() {
                @Override
                public boolean isComplete() {
                    return true;
                }

                @Override
                public T result() {
                    return value;
                }

                @Override
                public TailCall<T> apply() {
                    throw new Error("not implemented");
                }
            };
        }
    }


    public static void main(String[] args) {
        partSameNumberList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            partSameNumberList.add(i * (1111));
        }

        System.out.println(IntStream.range(MIN, MAX).filter(KaprekarHelper::inScope).filter(KaprekarHelper::isPartSameNumber).mapToLong(KaprekarHelper::getCycle).parallel().max().getAsLong());

    }


    static class KaprekarHelper {

        static boolean isKaprekar(int value) {
            return value == 6174;
        }

        static boolean inScope(int value) {
            return 1000 <= value && value <= 9999;
        }

        static int minValue(int value) {
            return Integer.parseInt(ascOrderNumber(value).stream().map(Object::toString).collect(Collectors.joining()));
        }

        static int maxValue(int value) {
            return Integer.parseInt(descOrderNumber(value).stream().map(Object::toString).collect(Collectors.joining()));
        }

        static List<Character> ascOrderNumber(int value) {
            String strValue = String.valueOf(value);
            List<Character> numberArray = new ArrayList<>();
            for (char c : strValue.toCharArray()) {
                numberArray.add(c);
            }
            Collections.sort(numberArray);
            return numberArray;
        }

        static List<Character> descOrderNumber(int value) {
            List<Character> descList = ascOrderNumber(value);
            Collections.reverse(descList);
            return descList;
        }

        static int calculate(int value) {
            return maxValue(value) - minValue(value);
        }

        static boolean isPartSameNumber(int value) {
            return !partSameNumberList.contains(value);
        }


        static int getCycle(int value) {
            return getCycle(value, 0).invoke();
        }


        static TailCall<Integer> getCycle(final int value, final int count) {
            if (isKaprekar(value) || value == 0) {
                return done(count);
            } else {
                return TailCalls.call(() -> getCycle(calculate(value), count + 1));
            }
        }

    }


}
