package lexer;

import common.AlphabetHelper;
import common.PeekIterator;

public class Token {

    TokenType _type;
    String _value;

    public Token(TokenType _type, String _value) {
        this._type = _type;
        this._value = _value;
    }

    public boolean isVariable() {
        return _type == TokenType.VARIABLE;
    }

    public boolean isScalar() {
        return _type == TokenType.INTEGER ||
                _type == TokenType.FLOAT ||
                _type == TokenType.STRING ||
                _type == TokenType.BOOLEAN;
    }

    public String getValue() {
        return _value;
    }

    public TokenType getType() {
        return _type;
    }

    // 提取变量或关键字
    public static Token makeVarOrKeyword(PeekIterator<Character> it) {
        String s = "";

        while (it.hasNext()) {
            Character lookahead = it.peek();
            if (AlphabetHelper.isLiteral(lookahead)) {
                s += lookahead;
            } else {
                break;
            }
            it.next();
        }

        if (Keywords.isKeyword(s)) {
            return new Token(TokenType.KEYWORD, s);
        }

        if (s.equals("true") || s.equals("false")) {
            return new Token(TokenType.BOOLEAN, s);
        }

        return new Token(TokenType.VARIABLE, s);
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + _type +
                ", value='" + _value + '\'' +
                '}';
    }
}
