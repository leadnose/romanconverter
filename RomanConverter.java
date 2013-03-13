// The exercise is: "Write a Java-program that converts roman positive integer
// values (I, II, III, IV, ... M ) to corresponding decimal values (1, 2, 3, 4,
// ... 1000)."

// http://en.wikipedia.org/wiki/Roman_numerals

public class RomanConverter {

    public static int romanToDecimal(String roman) throws IllegalArgumentException {

        final String romanRegex = "(I|V|X|L|C|D|M)+";

        if (roman == null) {
            throw new IllegalArgumentException("The string must not be null.");
        } else if (!roman.toUpperCase().trim().matches(romanRegex)) {
            throw new IllegalArgumentException("The string "
                                               + roman +
                                               " is not a valid roman numeral.");
        }

        return 1;

    }

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.err.println("You should provide exactly one argument.");
                System.exit(1);
            } else {
                System.out.println(romanToDecimal(args[0]));
                System.exit(0);
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

}