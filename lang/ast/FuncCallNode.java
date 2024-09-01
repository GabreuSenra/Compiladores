package lang.ast;

import java.util.List;

public class FuncCallNode extends ExpNode {
    private final String functionName;
    private final List<ExpNode> args;
    private final ExpNode index;

    public FuncCallNode(int line, int column, String functionName, List<ExpNode> args, ExpNode index) {
        super(line, column);
        this.functionName = functionName;
        this.args = args;
        this.index = index;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<ExpNode> getArgs() {
        return args;
    }

    public ExpNode getIndex() {
        return index;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}