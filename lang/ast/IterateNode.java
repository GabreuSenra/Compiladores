package lang.ast;

public class IterateNode extends CmdNode {
    private final ExpNode condition;
    private final CmdNode body;

    public IterateNode(int line, int column, ExpNode condition, CmdNode body) {
        super(line, column);
        this.condition = condition;
        this.body = body;
    }

    public ExpNode getCondition() {
        return condition;
    }

    public CmdNode getBody() {
        return body;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}