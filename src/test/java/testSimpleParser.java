import lexer.Lexer;
import lexer.LexicalException;
import org.junit.jupiter.api.Test;
import parser.SimpleParser;
import parser.ast.ASTNode;
import parser.util.ParseException;
import parser.util.PeekTokenIterator;

import java.util.stream.Stream;

public class testSimpleParser {

    @Test
    public void test() throws LexicalException, ParseException {
        Stream<Character> source = "1+2+3+4".chars().mapToObj(x -> (char) x);
        Lexer lexer = new Lexer();
        PeekTokenIterator it = new PeekTokenIterator(lexer.analyse(source).stream());
        ASTNode expr = SimpleParser.parse(it);

        assert expr.getChildren().size() == 2;
    }
}
