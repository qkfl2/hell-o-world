import java.util.stream.IntStream;

/**
 * Created by codemaker88 on 2016-10-22.
 */
public class TextPalindrome {
    public static void main(String[] args)
    {
        String text = "ioi";
        System.out.print("isTextPalindrome - " + text + " : " + isTextPalindrome(text) + "\n");
        System.out.print("isTextPalindromeFunctional - " + text + " : " + isTextPalindromeFunctional(text) + "\n");
    }

    //original code
    public static boolean isTextPalindrome(String text) {
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left++) != text.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    //functional
    public static boolean isTextPalindromeFunctional(String text) {
        final int TRUE = 1;
        final int FALSE = 0;
        final int textEndIndex = text.length() - 1;
        final int rangeEnd = text.length() / 2;

        int result = IntStream.range(0, rangeEnd)
                .map(i -> text.charAt(i) == text.charAt(textEndIndex - i) ? TRUE : FALSE)
                .sum();

        return result == rangeEnd * TRUE;
    }


}
