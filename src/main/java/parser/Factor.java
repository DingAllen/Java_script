package parser;

public abstract class Factor extends AstNode {
    public Factor(AstNode parent, ASTNodeTypes type, String label) {
        super(parent, type, label);
    }
}
