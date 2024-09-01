package lang.ast;

import java.util.List;

public class LValueNode extends ExpNode {
    private String id = "";
    private LValueNode lvalue;
    private ExpNode indice;
    private List<String> fields;

    public LValueNode(int line, int column, String id, LValueNode lvalue, ExpNode indice, List<String> fields) {
        super(line, column);
        this.id = id;
        this.lvalue = lvalue;
        this.indice = indice;
        this.fields = fields;
    }

    public String getId() {
        return id;
    }

    public LValueNode getLvalue(){
        return lvalue;
    }

    public ExpNode getIndice() {
        return indice;
    }

    public List<String> getFields() {
        return fields;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}