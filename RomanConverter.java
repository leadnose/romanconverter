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

        return parseRomanToDecimal(roman.toUpperCase().trim());

    }


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

    private static char int2rchar(int i) {
        switch (i) {
        case I: return 'I';
        case V: return 'V';
        case X: return 'X';
        case L: return 'L';
        case C: return 'C';
        case D: return 'D';
        case M: return 'M';
        default:
            throw new IllegalArgumentException("Unexpected integer " + i);
        }
    }

    private static boolean isNormalized(String roman) {
        if (roman.length() == 1) {
            return true;
        }

        for (int i = 1; i < roman.length(); i++) {
            if (rchar2int(roman.charAt(i-1)) < rchar2int(roman.charAt(i))) {
                return false;
            }
        }

        return true;
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
    
    private static String normalized(String roman) {
        if (roman.length() == 1) {
            return roman;
        }

        if (isNormalized(roman)) {
            return roman;
        }

        /* 
           we now know that the string is NOT normalized, and the only
           allowable exceptions are as enumerated above (and here):

           - the numeral I can be placed before V and X to make 4 units (IV)
           and 9 units (IX) respectively

           - X can be placed before L and C to make 40 (XL) and 90 (XC)
           respectively

           - C can be placed before D and M to make 400 and 900 according
           to the same pattern
        */

        return roman
            .replace("IV", "IIII")
            .replace("IX", "VIIII")

            .replace("XL", "XXXX")
            .replace("XC", "LXXXX")

            .replace("CD", "CCCC")
            .replace("CM", "DCCCC");
        /*

        // convert the string into numbers for easier comparisons
        int[] arr = new int[roman.length()];
        for (int i = 0; i < roman.length(); i++) {
            arr[i] = rchar2int(roman.charAt(i));
        }

        String normalized = "";

        for (int i = 0; i < arr.length - 1; i++) {
           // - the numeral I can be placed before V and X to make 4 units (IV)
           // and 9 units (IX) respectively
            if (arr[i] == I && arr[i+1] == V) {
                normalized += "IIII";
                i++;
            } else if (arr[i] == I && arr[i+1] == X) {
                normalized += "VIIII";
                i++;
            }
            // - X can be placed before L and C to make 40 (XL) and 90 (XC)
            // respectively
            else if (arr[i] == X && (arr[i+1] == L)) {
                normalized += "XXXX";
                i++;
            } else if (arr[i] == X && (arr[i+1] == C)) {
                normalized += "LXXXX";
                i++;
            }
        
           // - C can be placed before D and M to make 400 and 900 according
           // to the same pattern
            else if (arr[i] == C && arr[i+1] == D) {
                normalized += "CCCC";
                i++;
            } else if (arr[i] == C && arr[i+1] == M) {
                normalized += "DCCCC";
                i++;
            } else {
                normalized += int2rchar(arr[i]);
            }
        }

        // remember the last char too
        // normalized += int2rchar(arr[arr.length - 1]);

        return normalized;

        //     // // simply sort the list of numbers (it may not necessarily be in order after the previous operations) (or is it??)
        
        //     // java.util.Arrays.sort(normalized);
        //     // and reverse it (couldn't find anything in the std-lib to do this, omgwtfbbq?)
        // for (int left = 0, right = normalized.length - 1; left < right; left++, right--) {
        //     int tmp = normalized[left];
        //     normalized[left] = normalized[right];
        //     normalized[right] = tmp;
        // }

        // // and convert it back into characters
        // int[] ans = new int[normalized.length()];
        // for (int i = 0; i < normalized.length(); 
        */

    }
    


    // assumes that roman is in upper case, isn't null, isn't empty string and contains only
    // relevant characters


    private static int parseRomanToDecimal(String roman) {

        String normalized = normalized(roman);

        int ans = 0;

        for (int i = 0; i < normalized.length(); i++) {
            ans += rchar2int(normalized.charAt(i));
        }

        return ans;
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