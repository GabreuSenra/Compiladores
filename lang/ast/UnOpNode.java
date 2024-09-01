
/*Trabalho 2 - Teoria dos Compiladores (DCC045) 
 * Alunos: Gabriel Pereira Senra Soares - 201935006
 *         Lucas Castro Carvalho - 201835015
*/
package lang.ast;

public class UnOpNode extends ExpNode {
    private final String op;
    private final ExpNode expr;

    public UnOpNode(int line, int column, String op, ExpNode expr) {
        super(line, column);
        this.op = op;
        this.expr = expr;
    }

    public String getOp() {
        return op;
    }

    public ExpNode getExpressions() {
        return expr;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}