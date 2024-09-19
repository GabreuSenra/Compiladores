/*Trabalho 2 - Teoria dos Compiladores (DCC045) 
 * Alunos: Gabriel Pereira Senra Soares - 201935006
 *         Lucas Castro Carvalho - 201835015
*/

package lang.semantic;

import java.util.HashMap;
import java.util.Map;

public class DataDefinition {
    Map<String, String> fields;  // Mapeia o nome do campo para o tipo (ex: "x" :: "Int", "y" :: "Int")

    public DataDefinition() {
        fields = new HashMap<>();
    }

    public void addField(String fieldName, String fieldType) { //adiciona um novo field
        fields.put(fieldName, fieldType);
    }

    public String getFieldType(String fieldName) { //retorna o tipo de tederminado field
        return fields.get(fieldName);
    }

    public boolean hasField(String fieldName) { //verifica se um field existe em 'data' 
        return fields.containsKey(fieldName);
    }

    public Map<String, String> getFields(){
        return fields;
    }
}
