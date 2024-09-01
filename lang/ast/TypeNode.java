package lang.ast;

public class TypeNode extends SuperNode {
    private final SuperNode type; // Pode ser um BTypeNode ou outro TypeNode para tipos compostos

    // Construtor
    public TypeNode(int line, int column, SuperNode type) {
        super(line, column);
        this.type = type;
    }

    // Getter para o tipo
    public SuperNode getType() {
        return type;
    }

    // Implementação do método accept para o Visitor
    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}