package lang.ast;

import java.util.List;

public class CallNode extends CmdNode {
    private final String functionName;
    private final List<ExpNode> args;
    private final List<LValueNode> returnValues;

    public CallNode(int line, int column, String functionName, List<ExpNode> args, List<LValueNode> returnValues) {
        super(line, column);
        this.functionName = functionName;
        this.args = args;
        this.returnValues = returnValues;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<ExpNode> getArgs() {
        return args;
    }

    public List<LValueNode> getReturnValues() {
        return returnValues;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}