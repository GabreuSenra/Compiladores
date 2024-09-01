package lang.ast;

public class BTypeNode extends TypeNode {
    private final String btype; // Nome do tipo básico (ex: "Int", "Char", etc.)

    // Construtor
    public BTypeNode(int line, int column, String btype) {
        super(line, column, null);
        this.btype = btype;
    }

    // Getter para o nome do tipo básico
    public String getBtype() {
        return btype;
    }

    // Implementação do método accept para o Visitor
    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}