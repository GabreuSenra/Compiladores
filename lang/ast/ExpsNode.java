package lang.ast;

import java.util.List;

public class ExpsNode extends SuperNode {
    private List<ExpNode> expressions;

    public ExpsNode(int line, int column, List<ExpNode> expressions) {
        super(line, column);
        this.expressions = expressions;
    }

    public List<ExpNode> getExpressions() {
        return expressions;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}