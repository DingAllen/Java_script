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

    // 提取字符串
    public static Token makeString(PeekIterator<Character> it) throws LexicalException {
        String s = "";
        int state = 0;

        while (it.hasNext()) {
            char c = it.next();
            switch (state) {
                case 0:
                    if (c == '\'') {
                        state = 1;
                    } else {
                        state = 2;
                    }
                    s += c;
                    break;
                case 1:
                    if (c == '\'') {
                        return new Token(TokenType.STRING, s + c);
                    } else {
                        s += c;
                    }
                    break;
                case 2:
                    if (c == '"') {
                        return new Token(TokenType.STRING, s + c);
                    } else {
                        s += c;
                    }
                    break;
            }
        }
        throw new LexicalException("Unexcepted error");
    }

    // 提取操作符
    public static Token makeOp(PeekIterator<Character> it) throws LexicalException {

        int state = 0;

        while(it.hasNext()) {
            Character lookahead = it.next();

            switch (state) {
                case 0:
                    switch (lookahead) {
                        case '+':
                            state = 1;
                            break;
                        case '-':
                            state = 2;
                            break;
                        case '*':
                            state = 3;
                            break;
                        case '/':
                            state = 4;
                            break;
                        case '>':
                            state = 5;
                            break;
                        case '<':
                            state = 6;
                            break;
                        case '=':
                            state = 7;
                            break;
                        case '!':
                            state = 8;
                            break;
                        case '&':
                            state = 9;
                            break;
                        case '|':
                            state = 10;
                            break;
                        case '^':
                            state = 11;
                            break;
                        case '%':
                            state = 12;
                            break;
                        case ',':
                            return new Token(TokenType.OPERATOR, ",");
                        case ';':
                            return new Token(TokenType.OPERATOR,  ";");
                    }
                    break;
                case 1:
                    if(lookahead == '+') {
                        return new Token(TokenType.OPERATOR, "++");
                    } else if(lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "+=");
                    }else {
                        it.putBack();
                        return new Token(TokenType.OPERATOR, "+");
                    }
                case 2:
                    if(lookahead == '-') {
                        return new Token(TokenType.OPERATOR, "--");
                    } else if(lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "-=");
                    }else {
                        it.putBack();
                        return new Token(TokenType.OPERATOR, "-");
                    }
                case 3:
                    if(lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "*=");
                    }else {
                        it.putBack();
                        return new Token(TokenType.OPERATOR, "*");
                    }
                case 4:
                    if(lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "/=");
                    }else {
                        it.putBack();
                        return new Token(TokenType.OPERATOR, "/");
                    }
                case 5:
                    if(lookahead == '=') {
                        return new Token(TokenType.OPERATOR, ">=");
                    }else if(lookahead== '>') {
                        return new Token(TokenType.OPERATOR, ">>");
                    } else {
                        it.putBack();
                        return new Token(TokenType.OPERATOR, ">");

                    }
                case 6:
                    if(lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "<=");
                    }else if(lookahead== '<') {
                        return new Token(TokenType.OPERATOR, "<<");
                    } else {
                        it.putBack();
                        return new Token(TokenType.OPERATOR, "<");
                    }
                case 7:
                    if(lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "==");
                    } else {
                        it.putBack();
                        return new Token(TokenType.OPERATOR, "=");
                    }
                case 8:
                    if(lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "!=");
                    } else {
                        it.putBack();
                        return new Token(TokenType.OPERATOR, "!");
                    }
                case 9:
                    if(lookahead == '&') {
                        return new Token(TokenType.OPERATOR, "&&");
                    }  else if (lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "&=");
                    } else {
                        it.putBack();
                        return new Token(TokenType.OPERATOR, "&");
                    }
                case 10:
                    if(lookahead == '|') {
                        return new Token(TokenType.OPERATOR, "||");
                    }  else if (lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "|=");
                    } else {
                        it.putBack();
                        return new Token(TokenType.OPERATOR, "|");
                    }
                case 11:
                    if(lookahead == '^') {
                        return new Token(TokenType.OPERATOR, "^^");
                    }  else if (lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "^=");
                    } else {
                        it.putBack();
                        return new Token(TokenType.OPERATOR, "^");
                    }
                case 12:
                    if (lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "%=");
                    } else {
                        it.putBack();
                        return new Token(TokenType.OPERATOR, "%");
                    }
            }

        }
        throw new LexicalException("Unexpected error");
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + _type +
                ", value='" + _value + '\'' +
                '}';
    }
}
