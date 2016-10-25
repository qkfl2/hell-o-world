import java.util.Optional;
import java.util.stream.IntStream;

public class Bombay {
	private static final String input = "TESTSET";

	public static void main(String args[]) {
		// Way 1
		Optional<Boolean> result = IntStream.range(0, input.length() / 2)
				.mapToObj(n -> input.charAt(n) == input.charAt(input.length() - n - 1))
				.reduce((val1, val2) -> val1 && val2);
		System.out.println(result.get());

		// Way 2
		System.out.println(new StringBuffer(input).reverse().toString().equals(input)); // Q: 이런것도 함수형이라 할 수 있는지
	}
}
