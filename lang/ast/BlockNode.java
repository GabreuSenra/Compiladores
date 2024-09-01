package lang.ast;

import java.util.List;

public class BlockNode extends CmdNode {
    private final List<CmdNode> cmds;

    public BlockNode(int line, int column, List<CmdNode> cmds) {
        super(line, column);
        this.cmds = cmds;
    }

    public List<CmdNode> getCmds() {
        return cmds;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}