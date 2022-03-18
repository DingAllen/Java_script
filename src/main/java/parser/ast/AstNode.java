package parser.ast;

import lexer.Token;

import java.util.ArrayList;

public abstract class AstNode {

    protected ArrayList<AstNode> children;
    protected AstNode parent;

    protected Token lexeme; // 词法单元
    protected String label; // 备注（标签）
    protected ASTNodeTypes type; // 类型

    public AstNode(AstNode parent) {
        this.parent = parent;
    }

    public AstNode(AstNode parent, ASTNodeTypes type, String label) {
        this.parent = parent;
        this.type = type;
        this.label = label;
    }

    public AstNode getChild(int index) {
        return children.get(index);
    }

    public void addChild(AstNode node) {
        children.add(node);
    }

    public ArrayList<AstNode> getChildren() {
        return children;
    }
}
