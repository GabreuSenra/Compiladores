package lang.ast;

public class BinOpNode extends ExpNode {
    private final ExpNode left;
    private final String op;
    private final ExpNode right;

    public BinOpNode(int line, int column, String op, ExpNode left, ExpNode right) {
        super(line, column);
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public ExpNode getLeft() {
        return left;
    }

    public String getOp() {
        return op;
    }

    public ExpNode getRight() {
        return right;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}