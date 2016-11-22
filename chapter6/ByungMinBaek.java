import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ByungMinBaek {
	private final static UnaryOperator<long[]> fibonacciAlgo = arr -> new long[] { arr[1], arr[0] + arr[1] };
	private final static Function<long[], Long> getFibonacciNum = arr -> arr[0] + arr[1];
	private final static Predicate<Long> isPrimeFunctionalStyle = n -> IntStream.rangeClosed(2, (int) Math.sqrt(n)).noneMatch(i -> n % i == 0);
	private final static Predicate<Long> over100000 = n -> n >= 100000;
	private final static Consumer<Long> print = n -> System.out.println(n);

	public static void main(String[] args) throws IOException {
		Stream.iterate(new long[] { 1L, 1L }, fibonacciAlgo)
				.map(getFibonacciNum)
				.filter(over100000)
				.filter(isPrimeFunctionalStyle)
				.findFirst()
				.ifPresent(print);
	}
}
