package parser;

public class Block extends Stmt {
    public Block(AstNode parent) {
        super(parent, ASTNodeTypes.BLOCK, "block");
    }
}