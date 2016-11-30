package chapter7;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static chapter7.Chapter7.TailCalls.done;

/**
 * Created by bross on 2016. 11. 28..
 */
public class Chapter7 {


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

    final static int MAX = 100000000;
    final static int MIN = 10000;

    public static void main(String[] args) {
        IntStream.range(MIN, MAX).forEach(integer -> {
            System.out.println(integer + " : " + eat(integer, 0).invoke());
        });
    }

    static TailCall<Integer> eat(final int gram, final int count) {
        if (gram < 10) {
            return done(count);
        } else {
            return TailCalls.call(() -> eat((int) (gram * 0.999), count + 1));
        }
    }

}
