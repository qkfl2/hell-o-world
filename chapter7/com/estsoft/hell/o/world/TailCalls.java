package com.estsoft.hell.o.world;

/**
 * Created by Bombay on 2016-11-28.
 */
public class TailCalls {
    public static <T> TailCall<T> call(final TailCall<T> nextCall) {
        return nextCall;
    }

    public static <T> TailCall<T> done(final T value) {
        return new TailCall<T>() {
            @Override public boolean isComplete() { return true; }
            @Override public T result() { return value; }
            @Override public TailCall<T> apply() {
                throw new Error("not implemented");
            }
        };
    }
}
