package lang.ast;

import java.util.List;

public class CmdNode extends SuperNode {
    private String cmdType;
    private List<SuperNode> children;
    private FunNode function;

    public CmdNode(int line, int column) {
        super(line, column);
    }
    
    public CmdNode(int line, int column, String cmdType, List<SuperNode> children) {
        super(line, column);
        this.cmdType = cmdType;
        this.children = children;
    }

    public void setFunction(FunNode func){
        function = func;
    }
    public FunNode getFunction(){
        return function;
    }

    public String getCmdType() {
        return cmdType;
    }

    public List<SuperNode> getChildren() {
        return children;
    }
    
    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}