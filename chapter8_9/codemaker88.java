package Chapter8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by codemaker88 on 2016-12-04.
 */
public class Chapter8 {

    public static void main(String[] args) {
        System.out.println(IntStream.range(1000, 10000)
                .map(KaprekarHelper::getKaprekarCalCount)
                .max()
                .getAsInt());

        //System.out.println(getKaprekarCalCount(4371));
    }

    static class KaprekarHelper {
        public static int KAPREKAR = 6174;

        public static boolean isKaprekar(int number) {
            return KAPREKAR == number;
        }

        public static int getKaprekarCalCount(int number) {
            return computeKaprekar(number, 0).invoke();
        }

        public static TailCall<Integer> computeKaprekar(final int number, final int computeCount) {
            if(isKaprekar(number)) {
                return TailCalls.done(computeCount);
            } else {
                int nextNumber = computeKaprekar(number);
                if(nextNumber == 0) {//0인 경우는 같은 숫자들 1111, 2222
                    return TailCalls.done(computeCount);
                }
                return TailCalls.call( () -> computeKaprekar(nextNumber, computeCount + 1) );
            }
        }

        public static int computeKaprekar(int number) {
            //max, min 조합 얻어내고 빼.
            //뺏을때 0이면 패스, 0
            List<Integer> numberDigits = getNumberDigits(number);
            Collections.sort(numberDigits, Collections.reverseOrder());
            int max = combineDigit(numberDigits);
            Collections.sort(numberDigits);
            int min = combineDigit(numberDigits);
            //System.out.println("" + max + " - " + min +" = " + (max - min));
            return max - min;
        }

        public static List<Integer> getNumberDigits(int number) {
            ArrayList<Integer> result = new ArrayList<>();
            for( ; number > 0 ; number /= 10) {
                result.add(number % 10);
            }
            return result;
        }

        public static int combineDigit(List<Integer> numberDigits) {
            int result = 0;
            for(Integer digit : numberDigits) {
                result += digit;
                result *= 10;
            }
            result /= 10;
            return result;
        }
    }
}
