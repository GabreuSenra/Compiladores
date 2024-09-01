package lang.ast;

public class SuperNodeImpl extends SuperNode {
    public SuperNodeImpl(int line, int column) {
        super(line, column);
        //TODO Auto-generated constructor stub
    }

    private int line;
    private int column;

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accept'");
    }

    // Adicione outros campos e métodos conforme necessário
}