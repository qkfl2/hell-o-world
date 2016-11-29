import java.math.BigDecimal;
import java.util.stream.Stream;

public class 백병민 {
	final static BigDecimal divisor = new BigDecimal(1000);
	final static BigDecimal val = new BigDecimal(10);

	public static void main(String[] args) {
		System.out.println(icecreamProcess(new BigDecimal(100000000), 0).invoke());
	}

	private static TailCall<Integer> icecreamProcess(final BigDecimal icecreamSize, final int tryCount) {
		if (icecreamSize.compareTo(val) < 0) {
			return TailCalls.done(tryCount);
		} else {
			return TailCalls.call(() -> {
				return icecreamProcess(icecreamSize.subtract(icecreamSize.divide(divisor)), tryCount + 1);
			});
		}
	}
}
