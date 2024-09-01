/*Trabalho 2 - Teoria dos Compiladores (DCC045) 
 * Alunos: Gabriel Pereira Senra Soares - 201935006
 *         Lucas Castro Carvalho - 201835015
*/

package lang.ast;

public class ParamNode extends SuperNode {
    private String id;
    private TypeNode type;

    public ParamNode(int line, int column, String id, TypeNode type) {
        super(line, column);
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public TypeNode getType() {
        return type;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}