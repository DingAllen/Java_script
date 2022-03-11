import common.AlphabetHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testAlphabetHelper {

    @Test
    public void test() {

        assertEquals(true, AlphabetHelper.isLetter('a'));
        assertEquals(false, AlphabetHelper.isLetter('1'));
        assertEquals(true, AlphabetHelper.isNumber('1'));
        assertEquals(true, AlphabetHelper.isOperator('%'));
        assertEquals(false, AlphabetHelper.isOperator('c'));
    }
}
