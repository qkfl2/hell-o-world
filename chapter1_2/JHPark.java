import java.util.Arrays;

public class Palindrome {

    public static void main(String []args) {
        Arrays.asList("winter", "is", "coming", "I.O.I", "2NE1", "2NN2", "AOA", "wow", "level")
            .stream()
            .filter(text -> text.equals(reverse(text)))
            .forEach(text -> System.out.println(text));
    }

    private String reverse(String text) {
        return new StringBuilder(text).reverse().toString();
    }

}
