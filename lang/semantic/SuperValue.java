/*Trabalho 2 - Teoria dos Compiladores (DCC045) 
 * Alunos: Gabriel Pereira Senra Soares - 201935006
 *         Lucas Castro Carvalho - 201835015
*/

package lang.semantic;

    import java.util.List;
    import java.util.ArrayList;

public class SuperValue { // exemplo: int:ijk
    private Object value;
    private List<SuperValue> superValues = new ArrayList<>();
    private List<Object> indices;     
    
    public SuperValue(Object value, List<Object> indices, List<SuperValue> supervalues){
        this.value = value;
        this.indices = indices;
        this.superValues = supervalues;
    }

    public SuperValue(SuperValue v){
        this.value = v.getValue();
        this.indices = v.getIndicies();

        this.superValues = new ArrayList<>();

        for(SuperValue sv : v.getSuperValues()){
            this.superValues.add(new SuperValue(sv));
        }
    }

    public void setValue(Object value){
        this.value = value;
    }

    public Object getValue(){
        return value;
    }
    public List<Object> getIndicies(){
        return indices;
    }
    public List<SuperValue> getSuperValues(){
        return superValues;
    }
}
