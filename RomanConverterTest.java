import org.junit.Test;
import static org.junit.Assert.*;


public class RomanConverterTest {

    @Test(expected=IllegalArgumentException.class)
    public void testLanguageFails () {
        RomanConverter.romanToDecimal("asdfadsf");
    }

    @Test
    public void testLanguageOk1 () {
        RomanConverter.romanToDecimal("IVXLCDMMMXD");
    }

    @Test
    public void testLanguageOk2 () {
        RomanConverter.romanToDecimal("llmcxmimdv");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testEmptyStringFail() {
        RomanConverter.romanToDecimal("");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNullFail() {
        RomanConverter.romanToDecimal(null);
    }
    

}