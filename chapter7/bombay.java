import java.math.BigDecimal;
import com.estsoft.hell.o.world.TailCall;
import static com.estsoft.hell.o.world.TailCalls.*;

/**
 * Created by Bombay on 2016-11-28.
 */
public class Icecream {
    private static TailCall<Integer> calcEatCountTail(final BigDecimal mass, final int count) {
        if(mass.compareTo(BigDecimal.TEN) < 0)
            return done(count);
        return call(()->calcEatCountTail(mass.multiply(BigDecimal.valueOf(0.999)), count + 1));
    }
    private static int calcEatCount(final BigDecimal mass) {
        return calcEatCountTail(mass, 0).invoke();
    }
    private static int calcEatCount(final double mass) {
        return calcEatCountTail(BigDecimal.valueOf(mass), 0).invoke();
    }

    public static void main(String[] args) {
        System.out.println(calcEatCount(100000000));
        System.out.println(calcEatCount(new BigDecimal("1000000000000000000000000")));
    }
}
