package parser.ast;

import lexer.Token;
import lexer.TokenType;
import parser.util.PeekTokenIterator;

public abstract class Factor extends AstNode {
    public Factor(AstNode parent, PeekTokenIterator it) {
        super(parent);
        Token token = it.next();
        TokenType type = token.getType();

        if (type == TokenType.VARIABLE) {
            this.type = ASTNodeTypes.VARIABLE;
        } else {
            this.type = ASTNodeTypes.SCALAR;
        }
        this.label = token.getValue();
        this.lexeme = token;
    }
}
