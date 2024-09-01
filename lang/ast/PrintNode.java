/*Trabalho 2 - Teoria dos Compiladores (DCC045) 
 * Alunos: Gabriel Pereira Senra Soares - 201935006
 *         Lucas Castro Carvalho - 201835015
*/

package lang.ast;

public class PrintNode extends CmdNode  {
    private final ExpNode exp;

    public PrintNode(int line, int column, ExpNode exp) {
        super(line, column);
        this.exp = exp;
    }

    public ExpNode getExp() {
        return exp;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}