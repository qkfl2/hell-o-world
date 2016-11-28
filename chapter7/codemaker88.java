
public class Chapter7 {

	public static void main(String[] args) {

		System.out.println(bigIcecreamFighter(100000000, 0).invoke());
	}

	public static TailCall<Integer> bigIcecreamFighter(final double remainIcecreamSize, final int eatingCount) {
		
		if(remainIcecreamSize <= 10) {
			return TailCalls.done(eatingCount);
		} else {
			return TailCalls.call( () -> bigIcecreamFighter(remainIcecreamSize - remainIcecreamSize * 0.001, eatingCount + 1) );
		}
	}
}
