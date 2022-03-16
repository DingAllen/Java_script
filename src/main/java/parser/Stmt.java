package parser;

public abstract class Stmt extends AstNode{

    public Stmt(AstNode parent, ASTNodeTypes type, String label) {
        super(parent, type, label);
    }
}
