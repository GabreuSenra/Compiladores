/*Trabalho 2 - Teoria dos Compiladores (DCC045) 
 * Alunos: Gabriel Pereira Senra Soares - 201935006
 *         Lucas Castro Carvalho - 201835015
*/

package lang.ast;

public class TypeNode extends SuperNode {
    private final SuperNode type; // Pode ser um BTypeNode ou outro TypeNode para tipos compostos
    private String stringType = "";

    // Construtor
    public TypeNode(int line, int column, SuperNode type, String stringType) {
        super(line, column);
        this.type = type;
        this.stringType = stringType;
    }

    // Getter para o tipo
    public SuperNode getType() {
        return type;
    }

    public String getStringType(){
        return stringType;
    }
    // Implementação do método accept para o Visitor
    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}