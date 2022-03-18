package parser.ast;

public class FunctionDeclareStmt extends Stmt{
    public FunctionDeclareStmt(AstNode parent) {
        super(parent, ASTNodeTypes.FUNCTION_DECLARE_STMT, "func");
    }
}
