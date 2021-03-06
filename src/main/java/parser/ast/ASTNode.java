package parser.ast;

import lexer.Token;

import java.util.ArrayList;

public abstract class ASTNode {

    protected ArrayList<ASTNode> children = new ArrayList<>();
    protected ASTNode parent;

    protected Token lexeme; // 词法单元
    protected String label; // 备注（标签）
    protected ASTNodeTypes type; // 类型

    public ASTNode(ASTNode parent) {
        this.parent = parent;
    }

    public ASTNode(ASTNode parent, ASTNodeTypes type, String label) {
        this.parent = parent;
        this.type = type;
        this.label = label;
    }

    public ASTNode getChild(int index) {
        return children.get(index);
    }

    public void addChild(ASTNode node) {
        node.parent = this;
        children.add(node);
    }

    public ArrayList<ASTNode> getChildren() {
        return children;
    }

    public void setLexeme(Token lexeme) {
        this.lexeme = lexeme;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setType(ASTNodeTypes type) {
        this.type = type;
    }
}
