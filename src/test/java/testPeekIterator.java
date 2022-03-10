import common.PeekIterator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testPeekIterator {

    @Test
    public void test_peek() {
        String source = "abcdefg";
        PeekIterator<Character> it = new PeekIterator<>(source.chars().mapToObj(c -> (char) c));

        assertEquals('a', it.next());
        assertEquals('b', it.next());
        assertEquals('c', it.next());
        assertEquals('d', it.next());
        assertEquals('e', it.peek());
        assertEquals('e', it.next());
        assertEquals('f', it.next());
    }
}
