import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Scanner;
import java.io.*;

public class RomanConverterTest {

    @Test(expected=IllegalArgumentException.class)
    public void testLanguageFails () {
        RomanConverter.romanToDecimal("asdfadsf");
    }

    @Test
    public void testLanguageOk1 () {
        RomanConverter.romanToDecimal("MDCLXVI");
    }

    @Test
    public void testLanguageOk2 () {
        RomanConverter.romanToDecimal("mdclxvi");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testEmptyStringFail() {
        RomanConverter.romanToDecimal("");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNullFail() {
        RomanConverter.romanToDecimal(null);
    }

    @Test
    public void simpleConvert1() {
        int i = RomanConverter.romanToDecimal("I");
        assertEquals(i, 1);
    }

    @Test
    public void simpleConvert2() {
        int i = RomanConverter.romanToDecimal("II");
        assertEquals(i, 2);
    }

    @Test
    public void oneToThousandNormal() throws FileNotFoundException {
        File file = new File("one-to-thousand-roman-normal.txt");
        Scanner scanner = new Scanner(file);
        int i = 1;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            int n = RomanConverter.romanToDecimal(line);
            assertEquals(i, n);
            i++;
        }
    }

    @Test
    public void oneToThousandOldStyle() throws FileNotFoundException {
        File file = new File("one-to-thousand-roman-old-style.txt");
        Scanner scanner = new Scanner(file);
        int i = 1;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            int n = RomanConverter.romanToDecimal(line);
            assertEquals(i, n);
            i++;
        }
    }

}