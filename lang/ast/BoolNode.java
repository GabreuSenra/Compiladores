package lang.ast;

public class BoolNode extends ExpNode {
    private final boolean value;

    public BoolNode(int line, int column, boolean value) {
        super(line, column);
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}