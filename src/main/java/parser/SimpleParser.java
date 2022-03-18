package parser;

import parser.ast.ASTNode;
import parser.ast.ASTNodeTypes;
import parser.ast.Expr;
import parser.ast.Scalar;
import parser.util.ParseException;
import parser.util.PeekTokenIterator;

public class SimpleParser {

    public static ASTNode parse(PeekTokenIterator it) throws ParseException {

        Expr expr = new Expr(null);
        Scalar scalar = new Scalar(expr, it);
        if (!it.hasNext()) {
            return scalar;
        }

        expr.setLexeme(it.peek());
        it.nextMatch("+");
        expr.setLabel("+");
        expr.addChild(scalar);
        expr.setType(ASTNodeTypes.BINARY_EXPR);
        ASTNode rightNode = parse(it);
        expr.addChild(expr);
        return expr;
    }
}
