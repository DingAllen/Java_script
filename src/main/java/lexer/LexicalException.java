package lexer;

public class LexicalException extends Exception {

    private String msg;

    @Override
    public String getMessage() {
        return msg;
    }

    public LexicalException(String msg) {
        this.msg = msg;
    }
    public LexicalException(char c) {
        this.msg = String.format("Unexpected character %c", c);
    }
}
