import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

/**
 * Created by Bombay on 2016-12-06.
 */
public class Kaprekar {
    private static final IntUnaryOperator calcKaprekar = n -> {
        StringBuilder sb = new StringBuilder();
        String.format("%04d", n)
                .chars()
                .sorted()
                .mapToObj(v -> (char)v)
                .forEach(c -> sb.append(c));

        int min = Integer.parseInt(sb.toString());
        int max = Integer.parseInt(sb.reverse().toString());

        return max - min;
    };

    private static int calcKaprekarCount(int num, int count) {
        return num == 6174 ? count : calcKaprekarCount(calcKaprekar.applyAsInt(num), count+1);
    }

    private static final IntUnaryOperator kaprekarCount = n -> calcKaprekarCount(n, 0);
    private static final IntPredicate sameDigit = n -> n % 1111 != 0;

    public static void main(String[] args) {
        IntStream.range(1000, 9999)
                .filter(sameDigit)
                .map(kaprekarCount)
                .reduce(Integer::max)
                .ifPresent(System.out::println);
    }
}
