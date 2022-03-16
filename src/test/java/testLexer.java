import lexer.Lexer;
import lexer.LexicalException;
import lexer.Token;
import lexer.TokenType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testLexer {

    void assertToken(Token token, String value, TokenType type) {
        assert value.equals(token.getValue());
        assert type == token.getType();
    }

    @Test
    public void test_expression() throws LexicalException {
        Lexer lexer = new Lexer();
        String source = "(a+b)^100.12==+100-20";
        ArrayList<Token> tokens = lexer.analyse(source.chars().mapToObj(x -> (char) x));

        assert tokens.size() == 11;
        assertToken(tokens.get(0), "(", TokenType.BRACKET);
        assertToken(tokens.get(1), "a", TokenType.VARIABLE);
        assertToken(tokens.get(2), "+", TokenType.OPERATOR);
        assertToken(tokens.get(3), "b", TokenType.VARIABLE);
        assertToken(tokens.get(4), ")", TokenType.BRACKET);
        assertToken(tokens.get(5), "^", TokenType.OPERATOR);
        assertToken(tokens.get(6), "100.12", TokenType.FLOAT);
        assertToken(tokens.get(7), "==", TokenType.OPERATOR);
        assertToken(tokens.get(8), "+100", TokenType.INTEGER);
        assertToken(tokens.get(9), "-", TokenType.OPERATOR);
        assertToken(tokens.get(10), "20", TokenType.INTEGER);
    }

    @Test
    public void test_function() throws LexicalException {
        String source = "func test(a, b) {\n" +
                "print(a+b)\n" +
                "}\n" +
                "foo(-100.0, 100)";
        Lexer lexer = new Lexer();
        ArrayList<Token> tokens = lexer.analyse(source.chars().mapToObj(x -> (char) x));

        assertToken(tokens.get(0), "func", TokenType.KEYWORD);
    }
}
