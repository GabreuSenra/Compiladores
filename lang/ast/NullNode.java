/*Trabalho 2 - Teoria dos Compiladores (DCC045) 
 * Alunos: Gabriel Pereira Senra Soares - 201935006
 *         Lucas Castro Carvalho - 201835015
*/

package lang.ast;

public class NullNode extends ExpNode {
    public NullNode(int line, int column) {
        super(line, column);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}