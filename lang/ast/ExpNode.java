package lang.ast;

import java.util.List;

public class ExpNode extends SuperNode {
    private String expType;
    private List<SuperNode> children;

    public ExpNode(int line, int column){
        super(line, column);
    }

    public ExpNode(int line, int column, String expType, List<SuperNode> children) {
        super(line, column);
        this.expType = expType;
        this.children = children;
    }

    public String getExpType() {
        return expType;
    }

    public List<SuperNode> getChildren() {
        return children;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}