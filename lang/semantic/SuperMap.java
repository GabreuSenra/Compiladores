/*Trabalho 2 - Teoria dos Compiladores (DCC045) 
 * Alunos: Gabriel Pereira Senra Soares - 201935006
 *         Lucas Castro Carvalho - 201835015
*/

package lang.semantic;

import java.util.List;

public class SuperMap {
    private String id;
    private String type;
    private SuperValue value;
    private int scopo;

    public SuperMap(String id, String type , SuperValue value, int scope){
        this.id = id;
        this.value = value;
        this.scopo = scope;
        this.type = type;    
    }

    public String getID(){
        return id;
    }

    public String getType(){
        return type;
    }

    public SuperValue getValue(){
        return value;
    }

    public void setValue(Object newValue, List<Object> indices){
        if(indices == null){ //não é array
            value.setValue(newValue);
        }
    }

    public void setSuperValue(SuperValue superValue){
        this.value = superValue;
    }

    public int getScope(){
        return scopo;
    }
}
