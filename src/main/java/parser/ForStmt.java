package parser;

public class ForStmt extends Stmt{
    public ForStmt(AstNode parent) {
        super(parent, ASTNodeTypes.FOR_STMT, "for");
    }
}
