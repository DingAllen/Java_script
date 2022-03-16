package parser;

public class IfStmt extends Stmt {
    public IfStmt(AstNode parent) {
        super(parent, ASTNodeTypes.IF_STMT, "if");
    }
}
