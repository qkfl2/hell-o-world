package com.estsoft.hell.o.world;

import java.util.stream.Stream;

/**
 * Created by Bombay on 2016-11-28.
 */
@FunctionalInterface
public interface TailCall<T> {
    TailCall<T> apply();

    default boolean isComplete() { return false; }
    default T result() { throw new Error("not implemented"); }
    default T invoke() {
        return Stream.iterate(this, TailCall::apply)
                .filter(TailCall::isComplete)
                .findFirst()
                .get()
                .result();
    }
}
