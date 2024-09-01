package lang.ast;

public class NullNode extends ExpNode {
    public NullNode(int line, int column) {
        super(line, column);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}