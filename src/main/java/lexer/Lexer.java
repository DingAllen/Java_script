package lexer;

import common.AlphabetHelper;
import common.PeekIterator;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Lexer {

    public ArrayList<Token> analyse(Stream source) throws LexicalException {

        ArrayList<Token> tokens = new ArrayList<>();
        PeekIterator<Character> it = new PeekIterator<Character>(source, (char) 0);

        while (it.hasNext()) {

            char c = it.next();

            if (c == 0) {
                break;
            }

            char lookahead = it.peek();

            if (c == ' ' || c == '\n') {
                continue;
            }

            // 删除注释
            if (c == '/') {
                if (lookahead == '/') {
                    while (it.hasNext() && (c = it.next()) != '\n') ;
                    continue;
                } else if (lookahead == '*') {
                    it.next();
                    boolean valid = false;
                    while (it.hasNext()) {
                        char p = it.next();
                        if (p == '*' && it.peek() == '/') {
                            it.next();
                            valid = true;
                            break;
                        }
                    }
                    if (!valid) {
                        throw new LexicalException("comments not match");
                    }
                    continue;
                }
            }

            if (c == '{' || c == '}' || c == '(' || c == ')') {
                tokens.add(new Token(TokenType.BRACKET, c + ""));
                continue;
            }

            if (c == '"' || c == '\'') {
                it.putBack();
                tokens.add(Token.makeString(it));
                continue;
            }

            if (AlphabetHelper.isLetter(c)) {
                it.putBack();
                tokens.add(Token.makeVarOrKeyword(it));
                continue;
            }

            if (AlphabetHelper.isNumber(c)) {
                it.putBack();
                tokens.add(Token.makeNumber(it));
                continue;
            }

            if ((c == '+' || c == '-' || c == '.') && AlphabetHelper.isNumber(lookahead)) {
                Token lastToken = tokens.size() == 0 ? null : tokens.get(tokens.size() - 1);
                if (lastToken == null || !lastToken.isNumber()) {
                    it.putBack();
                    tokens.add(Token.makeNumber(it));
                    continue;
                }
            }

            if (AlphabetHelper.isOperator(c)) {
                it.putBack();
                tokens.add(Token.makeOp(it));
                continue;
            }

            throw new LexicalException(c);
        }

        return tokens;
    }
}
