package com.company;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(LongStream.range(1, 40)
                .mapToObj(x -> String.valueOf(Bfs.nextLevelFriends()))
                .collect(Collectors.joining(", ")).concat(", ..."));
    }

    private static class Bfs {
        static Queue<Long> currentChildren = new ArrayDeque<>(Collections.singletonList(1L));
        static final HashSet<Long> cache = new HashSet<>(currentChildren);

        static long nextLevelFriends() {
            final Queue<Long> nextChildren = new ArrayDeque<>(currentChildren.size() * 2);

            currentChildren.stream()
                    .peek(it -> addChild(nextChildren, it * 2))
                    .filter(it -> (it % 3 == 1) && (it % 2 == 0))
                    .forEach(it -> addChild(nextChildren, (it - 1) / 3));

            return (currentChildren = nextChildren).size();
        }

        static void addChild(Queue<Long> target, Long value) {
            if (!cache.contains(value)) {
                target.add(value);
                cache.add(value);
            }
        }
    }

}
