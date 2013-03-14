/**
   @author Janne Ronkonen <leadnose@gmail.com>
 */

/*
  The exercise is: "Write a Java-program that converts roman positive integer
  values (I, II, III, IV, ... M ) to corresponding decimal values (1, 2, 3, 4,
  ... 1000)."
*/


import java.util.Arrays;
import java.util.Scanner;

public class RomanConverter {

    private static final int I = 1,
                             V = 5,
                             X = 10,
                             L = 50,
                             C = 100,
                             D = 500,
                             M = 1000;

    private static int rchar2int(char c) {
        switch (c) {
        case 'I': return I;
        case 'V': return V;
        case 'X': return X;
        case 'L': return L;
        case 'C': return C;
        case 'D': return D;
        case 'M': return M;
        default:
            throw new IllegalArgumentException("Unexpected character " + c);
        }
    }

    /**
      Converts a valid roman numeral to its decimal value. May also
      convert strings that aren't sensible roman numerals, such as
      XIIIII, which would be written as XV normally. 

      @param roman The string to be converted. May be in either upper
      or lower case, and may contain whitespace before or after the numeral.
      @throws IllegalArgumentException if roman is null, or is not recognized as a roman numeral
      @see http://en.wikipedia.org/wiki/Roman_numerals

     */
    public static int romanToDecimal(String roman) throws IllegalArgumentException {

        /* First, check that the string looks like a roman numeral */

        final String romanRegex = "(I|V|X|L|C|D|M)+";

        if (roman == null) {
            throw new IllegalArgumentException("The string must not be null.");
        } else if (!roman.toUpperCase().trim().matches(romanRegex)) {
            throw new IllegalArgumentException("The string "
                                               + roman +
                                               " is not a valid roman numeral.");
        }

        /*
          From Wikipedia:
          
          Symbols are placed from left to right in order of value,
          starting with the largest. However, in a few specific cases,
          to avoid four characters being repeated in succession (such as
          IIII or XXXX) these can be reduced as follows:
          
          - the numeral I can be placed before V and X to make 4 units (IV)
          and 9 units (IX) respectively
          
          - X can be placed before L and C to make 40 (XL) and 90 (XC)
          respectively
          
          - C can be placed before D and M to make 400 and 900 according
          to the same pattern
          
        */

        final String normal = roman
            .toUpperCase()
            .trim()

            .replace("IV", "IIII")
            .replace("IX", "VIIII")

            .replace("XL", "XXXX")
            .replace("XC", "LXXXX")

            .replace("CD", "CCCC")
            .replace("CM", "DCCCC");

        // check that the things are in order
        for (int i = 0; i < normal.length() - 1; i++) {
            if (rchar2int(normal.charAt(i)) < rchar2int(normal.charAt(i+1))) {
                throw new IllegalArgumentException("The string " + roman + " is not a valid roman numeral (normalized string was: " + normal + ").");
            }
        }

        // Simply calculate sum of the digits */
        int sum = 0;

        for (int i = 0; i < normal.length(); i++) {
            sum += rchar2int(normal.charAt(i));
        }

        return sum;
    }

    public static void main(String[] args) {
        try {
            if (args.length == 1) {
                System.out.println(romanToDecimal(args[0]));
                System.exit(0);
            } else if (args.length == 0) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNext()) {
                    try {
                        String line = scanner.nextLine();
                        System.out.println(romanToDecimal(line));
                    } catch (IllegalArgumentException e) {
                        System.err.println(e);
                    }
                }
            } else {
                System.err.println("You should provide 0 or 1 arguments.");
                System.exit(1);
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

}