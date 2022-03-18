package parser.util;

import lexer.Token;

public class ParseException extends Exception {

    private String _msg;

    public ParseException(String _msg) {
        this._msg = _msg;
    }

    public ParseException(Token token) {
        _msg = String.format("Syntax Error, unexpected token %s", token.getValue());
    }

    @Override
    public String getMessage() {
        return _msg;
    }
}
