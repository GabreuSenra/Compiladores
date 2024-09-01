/*Trabalho 2 - Teoria dos Compiladores (DCC045) 
 * Alunos: Gabriel Pereira Senra Soares - 201935006
 *         Lucas Castro Carvalho - 201835015
*/

package lang.ast;

public class ReadNode extends CmdNode  {
    private final LValueNode lvalue;

    public ReadNode(int line, int column, LValueNode lvalue) {
        super(line, column);
        this.lvalue = lvalue;
    }

    public LValueNode getLvalue() {
        return lvalue;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}