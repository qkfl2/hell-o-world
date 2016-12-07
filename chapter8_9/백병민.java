
import java.util.stream.Collectors;

public class 백병민 {
	public static void main(String[] args) {
		final int input = 4371;
		System.out.println(kap(input, 0).invoke());
	}

	private static TailCall<Integer> kap(final int input, final int tryCount) {
		String kap = String.format("%04d", input);
		if (kap.replaceAll("(.)(?=.*?\\1)", "").length() == 1)
			return TailCalls.done(-1);
		if (kap.equals("6174")) {
			return TailCalls.done(tryCount);
		}
		final String small = kap.chars().mapToObj(c -> String.valueOf((char)c)).sorted(String::compareTo).collect(Collectors.joining());
		final String large = new StringBuilder(small).reverse().toString();
		return TailCalls.call(() -> kap(Integer.parseInt(large) - Integer.parseInt(small), tryCount + 1));
	}
}
