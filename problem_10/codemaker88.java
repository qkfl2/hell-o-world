package ChapterEX;

import java.util.*;

/**
 * Created by codemaker88 on 2016-12-10.
 */
public class CollatzConjecture {

    public void findJHSequence() {
        long startTime = System.currentTimeMillis();

        List<Long> list = new ArrayList<>();
        list.add(1L);
        System.out.println(find(40, new StringBuilder(), list).invoke());

        System.out.println("time : " + (System.currentTimeMillis() - startTime));
    }

    public TailCall<String> find(final int depth, final StringBuilder result, final List<Long> BFSQueue) {

        if(depth <= 1) {
            return TailCalls.done(result.toString());
        } else {
            findNext(BFSQueue);
            //System.out.println("CollatzList : " + "(" + (BFSQueue.size()) + ")" + BFSQueue);
            result.append(BFSQueue.size());
            result.append(", ");
            return TailCalls.call( () -> find(depth - 1, result, BFSQueue) );
        }
    }

    public void findNext(final List<Long> currentCollatzList) {

        Queue<Long> temp = new ArrayDeque<>();

        temp.addAll(currentCollatzList);
        currentCollatzList.clear();

        for (Long value : temp) {
            //짝수의 경우는 항상 포함
            long even = value << 1;
            currentCollatzList.add(even);
            //홀수의 경우는 (x - 1) / 3 값이 홀수이며 정수인 값만 가능한 수로 포함
            if (isAvailableOdd(value)) {
                long odd = (value - 1) / 3;
                currentCollatzList.add(odd);
            }
        }
    }

    public boolean isAvailableOdd(long number) {
        double floatNumber = ((double) number - 1) / 3;
        return floatNumber > 1 && floatNumber % 2 == 1 && floatNumber % 1 == 0;//홀수 & 정수
    }
}
