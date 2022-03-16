package parser;

public class Variable extends Factor{
    public Variable(AstNode parent, ASTNodeTypes type, String label) {
        super(parent, ASTNodeTypes.VARIABLE, null);
    }
}
