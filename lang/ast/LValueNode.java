/*Trabalho 2 - Teoria dos Compiladores (DCC045) 
 * Alunos: Gabriel Pereira Senra Soares - 201935006
 *         Lucas Castro Carvalho - 201835015
*/

package lang.ast;

import java.util.List;

public class LValueNode extends ExpNode {
    private String id = "";
    private LValueNode lvalue;
    private ExpNode indice;
    private List<String> fields;
    private List<Object> indexes; //valor do indice

    public LValueNode(int line, int column, String id, LValueNode lvalue, ExpNode indice, List<String> field, List<Object> indexes) {
        super(line, column);
        this.id = id;
        this.lvalue = lvalue;
        this.indice = indice;
        this.fields = field;
        this.indexes = indexes;
    }

    public String getId() {
        return id;
    }

    public LValueNode getLvalue(){
        return lvalue;
    }

    public ExpNode getIndice() {
        return indice;
    }

    public List<String> getField() {
        return fields;
    }

    public List<Object> getIndexes(){
        return indexes;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}