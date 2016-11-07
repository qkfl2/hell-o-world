package com.company;

import java.util.function.Function;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println(new 비빔밥(재료.고추장, 재료.된장, 재료.마요네즈).mix());
    }

    private enum 재료 implements Function<String, String> {
        고추장, 초장, 된장, 마요네즈, 와사비, 소주;

        @Override
        public String apply(String s) {
            return name() + " " + s;
        }
    }

    private static class 비빔밥 {
        private final Function<String, String> materials;

        @SafeVarargs
        비빔밥(final Function<String, String>... input) {
            materials = Stream.of(input)
                    .reduce(Function::compose)
                    .orElse(material -> material);
        }

        final String mix() {
            return materials.apply(getClass().getSimpleName());
        }
    }

}
