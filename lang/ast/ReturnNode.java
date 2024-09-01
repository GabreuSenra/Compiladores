package lang.ast;

import java.util.List;

public class ReturnNode extends CmdNode  {
    private final List<ExpNode> exps;

    public ReturnNode(int line, int column, List<ExpNode> exps) {
        super(line, column);
        this.exps = exps;
    }

    public List<ExpNode> getExps() {
        return exps;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
