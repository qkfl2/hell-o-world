import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

/**
 * Created by Bombay on 2016-11-22.
 */
public class bombay {

    private static final IntUnaryOperator getFibonacci =
            n -> (int) Math.round((Math.pow(1 + Math.sqrt(5), n) - Math.pow(1 - Math.sqrt(5), n)) / (Math.pow(2, n) * Math.sqrt(5)));

    private static final IntPredicate isBiggerThan100K =
            n -> n >= 100000;

    private static final IntPredicate isPrimeNumberErathos =
            n -> IntStream.range(2, (int) Math.ceil(Math.sqrt(n)))
            .mapToObj(r -> n % r != 0)
            .reduce((ls, rs) -> ls && rs)
            .orElse(true);

    public static void main(String[] args) {
        IntStream.range(1, 35)
                .map(getFibonacci)
                .filter(isBiggerThan100K)
                .filter(isPrimeNumberErathos)
                .findFirst().ifPresent(System.out::println);
    }
}
