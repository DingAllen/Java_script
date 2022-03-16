package parser;

public class Scalar extends Factor{
    public Scalar(AstNode parent, ASTNodeTypes type, String label) {
        super(parent, ASTNodeTypes.SCALAR, null);
    }
}
