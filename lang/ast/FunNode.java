package lang.ast;

import java.util.List;

public class FunNode extends SuperNode {
    private String id;
    private List<ParamNode> params;
    private List<TypeNode> returnTypes;
    private List<CmdNode> cmds;

    public FunNode(int line, int column, String id, List<ParamNode> params, List<TypeNode> returnTypes, List<CmdNode> cmds) {
        super(line, column);
        this.id = id;
        this.params = params;
        this.returnTypes = returnTypes;
        this.cmds = cmds;

        for(int i = 0; i < cmds.size(); i++){
            cmds.get(i).setFunction(this);
        }
    }

    public String getId() {
        return id;
    }

    public List<ParamNode> getParams() {
        return params;
    }

    public List<TypeNode> getReturnTypes() {
        return returnTypes;
    }

    public List<CmdNode> getCmds() {
        return cmds;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}