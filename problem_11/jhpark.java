package com.company;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args)  {
        final List<Class<? extends Gamer> > gamerClasses
                = Lists.newArrayList(시민.class, 무법자.class, 정신병자.class);

        final LinkedList<Gamer> gamers = Lists.newArrayList(1, 0, 1, 1, 0, 0, 2, 0, 0)
                .stream()
                .map(Main::fromValue)
                .collect(Collectors.toCollection(LinkedList::new));

        while (!gamers.isEmpty()) {
            for (int i = 0; i < gamers.size(); i++) {
                i = gamers.get(i).shot(gamers, i);

                gamerClasses.stream()
                        .filter(it -> isWinner(gamers, it))
                        .findFirst()
                        .ifPresent(it -> {
                            System.out.println(it.getSimpleName() + " 승리 " + gamers.size());
                            gamers.clear();
                        });
            }
        }
    }


    static boolean isWinner(List<Gamer> gamers, Class<? extends Gamer> theClass) {
        if (theClass.equals(시민.class)) {
            return gamers.stream()
                    .filter(it -> !무법자.class.isInstance(it))
                    .count() == gamers.size();
        } else {
            return gamers.stream()
                    .filter(theClass::isInstance)
                    .count() == gamers.size();
        }
    }

    static Gamer fromValue(int value) {
        return value == 0 ? new 시민() : value == 1 ? new 무법자() : new 정신병자();
    }

    static abstract class Gamer {
        int killCnt = 0;

        abstract int shot(List<Gamer> gamers, int index);

        void kill(List<Gamer> gamers, int index) {
            gamers.remove(index);
            killCnt++;
        }

        int prev(List<Gamer> gamers, int index) {
            return index == 0 ? gamers.size() - 1 : index - 1;
        }

        int next(List<Gamer> gamers, int index) {
            return index == gamers.size() - 1 ? 0 : index + 1;
        }
    }

    static class 시민 extends Gamer {
        @Override
        int shot(List<Gamer> gamers, int index) {
            int prev = prev(gamers, index);
            int next = next(gamers, index);

            if (gamers.get(prev).killCnt > gamers.get(next).killCnt) {
                kill(gamers, prev);
                return prev < index ? index - 1 : index;
            } else if (gamers.get(next).killCnt > gamers.get(prev).killCnt) {
                kill(gamers, next);
            }
            return index;
        }
    }

    static class 무법자 extends Gamer {
        @Override
        int shot(List<Gamer> gamers, int index) {
            int prev = prev(gamers, index);
            int next = next(gamers, index);

            if (gamers.get(prev).killCnt < gamers.get(next).killCnt) {
                if (!(gamers.get(prev) instanceof 무법자)) {
                    kill(gamers, prev);
                    return prev < index ? index - 1 : index;
                }
            } else {
                if (!(gamers.get(next) instanceof 무법자)) {
                    kill(gamers, next);
                }
            }
            return index;
        }
    }

    static class 정신병자 extends Gamer {
        @Override
        int shot(List<Gamer> gamers, int index) {
            if (gamers.size() % 2 != 0) {
                kill(gamers, index);
                return index - 1;
            } else {
                final int half = gamers.size() / 2;
                if (index < half) {
                    kill(gamers,  index + half);
                } else {
                    kill(gamers,  index - half);
                    return index - 1;
                }
                return index;
            }
        }

        @Override
        void kill(List<Gamer> gamers, int index) {
            gamers.remove(index);
        }
    }

}
