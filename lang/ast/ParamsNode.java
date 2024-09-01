package lang.ast;

import java.util.List;

public class ParamsNode extends SuperNode {
    private List<ParamNode> params;

    public ParamsNode(int line, int column, List<ParamNode> params) {
        super(line, column);
        this.params = params;
    }

    public List<ParamNode> getParams() {
        return params;
    }


    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}