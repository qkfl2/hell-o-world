package Chapter4;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by codemaker88 on 2016-11-04.
 */
public class Chapter4 {
    public static void main(String[] args)
    {
        Bibimbap bibimbap = new Bibimbap();
        bibimbap.addMaterial(재료추가::콩나물, 재료추가::고추장);
        System.out.println(bibimbap.mix());
        bibimbap.addMaterial(재료추가::참치, 재료추가::마요네즈, 재료추가::스팸);
        System.out.println(bibimbap.mix());
    }

    static class Bibimbap {
        private static final String NAME = "비빔밥";
        private Function<String, String> material;

        public String mix() {
            return material.apply(NAME);
        }

        public void addMaterial(final Function<String, String>... materials) {
            material = Stream.of(materials)
                    .reduce(Function::compose)
                    .orElse(material -> material);
        }
    }

    static class 재료추가 {

        static String 고추장(String materials) {
            return add("고추장", materials);
        }

        static String 콩나물(String materials) {
            return add("콩나물", materials);
        }

        static String 마요네즈(String materials) {
            return add("마요네즈", materials);
        }

        static String 참치(String materials) {
            return add("참치", materials);
        }

        static String 스팸(String materials) {
            return add("스팸", materials);
        }

        static String add(String material1, String material2) {
            return material1 + " " + material2;
        }
    }
}
