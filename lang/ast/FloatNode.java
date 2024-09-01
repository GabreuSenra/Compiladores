package lang.ast;

public class FloatNode extends ExpNode {
    private final float value;

    public FloatNode(int line, int column, float value) {
        super(line, column);
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}