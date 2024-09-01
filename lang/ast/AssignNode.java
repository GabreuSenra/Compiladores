package lang.ast;

public class AssignNode extends CmdNode {
    private final LValueNode lvalue;
    private final ExpNode exp;

    public AssignNode(int line, int column, LValueNode lvalue, ExpNode exp) {
        super(line, column);
        this.lvalue = lvalue;
        this.exp = exp;
    }

    public LValueNode getLvalue() {
        return lvalue;
    }

    public ExpNode getExp() {
        return exp;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}