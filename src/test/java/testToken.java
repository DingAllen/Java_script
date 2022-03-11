import common.PeekIterator;
import lexer.Token;
import lexer.TokenType;
import org.junit.jupiter.api.Test;

public class testToken {

    @Test
    public void test_make_var_or_keyword() {

        PeekIterator<Character> it1 = new PeekIterator<>("if abc".chars().mapToObj(x -> (char) x));
        PeekIterator<Character> it2 = new PeekIterator<>("true abc".chars().mapToObj(x -> (char) x));
        PeekIterator<Character> it3 = new PeekIterator<>("cnm abc".chars().mapToObj(x -> (char) x));
        Token token1 = Token.makeVarOrKeyword(it1);
        Token token2 = Token.makeVarOrKeyword(it2);
        Token token3 = Token.makeVarOrKeyword(it3);

        assert TokenType.KEYWORD == token1.getType();
        assert "if".equals(token1.getValue());
        assert TokenType.BOOLEAN == token2.getType();
        assert "true".equals(token2.getValue());
        assert TokenType.VARIABLE == token3.getType();
        assert "cnm".equals(token3.getValue());
    }
}
