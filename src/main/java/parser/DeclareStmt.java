package parser;

public class DeclareStmt extends Stmt{
    public DeclareStmt(AstNode parent) {
        super(parent, ASTNodeTypes.DECLARE_STMT, "declare");
    }
}
