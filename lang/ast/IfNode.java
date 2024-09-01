package lang.ast;

public class IfNode extends CmdNode {
    private final ExpNode condition;
    private final CmdNode thenCmd;
    private final CmdNode elseCmd;

    public IfNode(int line, int column, ExpNode condition, CmdNode thenCmd, CmdNode elseCmd) {
        super(line, column);
        this.condition = condition;
        this.thenCmd = thenCmd;
        this.elseCmd = elseCmd;
    }

    public ExpNode getCondition() {
        return condition;
    }

    public CmdNode getThenCmd() {
        return thenCmd;
    }

    public CmdNode getElseCmd() {
        return elseCmd;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}