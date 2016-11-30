package java8Study;

public class Chapter7 {
	private static final double 한번_핱으면_줄어드는량 = 0.001;
	public static void main(String[] args) {
		System.out.println(먹은_갯수_반환(100000000 , 0).invoke());
	}
	public static TailCall<Integer> 먹은_갯수_반환(double 남은_아이스크림_그램 ,  int 몇번했는지 ){
		if( 남은_아이스크림_그램 <= 10 ){
			return TailCalls.done(몇번했는지);
		} else {
			return TailCalls.call( () ->먹은_갯수_반환( (남은_아이스크림_그램 - (남은_아이스크림_그램 * 한번_핱으면_줄어드는량))  , (몇번했는지 + 1)));
		}
	}
}
