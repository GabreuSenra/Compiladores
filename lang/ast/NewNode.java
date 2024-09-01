/*Trabalho 2 - Teoria dos Compiladores (DCC045) 
 * Alunos: Gabriel Pereira Senra Soares - 201935006
 *         Lucas Castro Carvalho - 201835015
*/

package lang.ast;

import java.util.List;

public class NewNode extends ExpNode {
    private final TypeNode type;
    private final List<ExpNode> exps;

    public NewNode(int line, int column, TypeNode type, List<ExpNode> exps) {
        super(line, column);
        this.type = type;
        this.exps = exps;
    }

    public TypeNode getType() {
        return type;
    }

    public List<ExpNode> getExps() {
        return exps;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}