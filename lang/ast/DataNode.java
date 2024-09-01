package lang.ast;

import java.util.List;

public class DataNode extends SuperNode{

    private String id;
    private List<DeclNode> declarations;

    public DataNode(int line, int column, String id, List<DeclNode> declarations) {
        super(line, column);
        this.id = id;
        this.declarations = declarations;
    }

    public String getId() {
        return id;
    }

    public List<DeclNode> getDeclarations() {
        return declarations;
    }
    
    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
