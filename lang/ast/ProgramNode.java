package lang.ast;

import java.util.List;

public class ProgramNode extends SuperNode {
    private List<SuperNode> programs;

    public ProgramNode(int line, int column, List<SuperNode> programs) {
        super(line, column);
        this.programs = programs;
    }

    public List<SuperNode> getPrograms() {
        return programs;
    }

    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
