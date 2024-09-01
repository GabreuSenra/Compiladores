package lang.ast;

public class DefNode extends SuperNode {
    private SuperNode definition;

    public DefNode(int line, int column, SuperNode definition) {
        super(line, column);
        this.definition = definition;
    }

    public SuperNode getDefinition() {
        return definition;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}