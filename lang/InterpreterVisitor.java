/*Trabalho 2 - Teoria dos Compiladores (DCC045) 
 * Alunos: Gabriel Pereira Senra Soares - 201935006
 *         Lucas Castro Carvalho - 201835015
*/

package lang;

import lang.ast.*;
import lang.parser.LangBaseVisitor;
import lang.parser.LangParser.LvalueContext;
import lang.semantic.DataDefinition;
import lang.semantic.SuperMap;
import lang.semantic.SuperValue;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.xml.crypto.Data;

import org.antlr.v4.codegen.BlankOutputModelFactory;
import org.antlr.v4.gui.SystemFontMetrics;
import org.antlr.v4.misc.EscapeSequenceParsing.Result.Type;
import org.antlr.v4.parse.GrammarTreeVisitor.outerAlternative_return;
import org.antlr.v4.runtime.misc.ObjectEqualityComparator;

import java.util.Scanner;

public class InterpreterVisitor extends LangBaseVisitor<Object> implements Visitor<Object> {

    private Map<String, ArrayList<SuperMap>> superMap = new HashMap<>(); //mantem um registro de: Ambiente -> Variaveis, Tipos, Valores e Scopos
    private Map<String, FunNode> functions = new HashMap<>(); //mantem registro de todas as funções
    Map<String, DataDefinition> dataTypes = new HashMap<>(); //mantem registro de todas as Datas
    public int actualScope = -1;


    //variaveis auxiliares
    private String variableIdAux = ""; //auxilia na criação de variaveis parâmetros, ex: f(x :: Int)
    public String actualFuncName = ""; //auxilia no SuperMap
    public String actualType = ""; //auxilia no tipo das variaveis
    public String variableDataArray = ""; //auxilia na criação de array de Data
    public int actualTypeBracketsCount = 0;
    public Boolean acessandoDataArray = false;

    //auxila ao CallNode e FuncCallNode a determinar qual return usar.
    public ReturnNode actualReturn;

    public String[] primitiveTypes = {"Int", "Float", "Bool", "Char"};

    public InterpreterVisitor() { 
        System.out.println("=====================================");
        System.out.println("\n\nInicializando o Interpretador");
        System.out.println("Saída do CMD: \n");
    }

    public void assignVariavel(String id, ExpNode exp, List<Object> indices, List<String> Fields){
        //==========================================================Registro de Variavel==================================================================

        //caso receba uma lista de indices vazia, atribui null.
        if(indices != null){
            if(indices.isEmpty()){
                indices = null;
            }
        }

        if(indices == null && Fields.isEmpty()){ //ID
            //Obtem o resultado da Expressão recursivamente
            Object value = exp.accept(this);

            if(value instanceof LValueNode){ //Resolve LValue para um Valor
                LValueNode lval = (LValueNode) value;
                //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());
                value = acessaVariavel(lval.getId(), lval.getIndexes(), exp, lval.getField());
            }

            if(value instanceof NewNode){
                NewNodeAux(id, (NewNode)value, actualType);
                return;
            }

            //cria um novo objeto SuperMap para salvar a variavel, seu valor, seu tipo e seu scopo ===========================================================
            SuperValue superValue = new SuperValue(value, null, new ArrayList<>());

            //cria o objeto supermap para ser inserido no hashMap
            SuperMap supe = new SuperMap(id, actualType, superValue, actualScope);
                
            if(!superMap.containsKey(actualFuncName)){ //caso o superMap não possua a função atual como chave
                superMap.put(actualFuncName, new ArrayList<>()); //cria um enviroment novo sob a função atual e cria uma lista de SuperMaps
            }

            ArrayList<SuperMap> newList = superMap.get(actualFuncName); //recebe lista atual de SuperMaps da função atual

            if(newList.isEmpty()){ //Se a lista esta vazia, adicione a variavel
                newList.add(supe);
            }
            else{ //caso contrario, itere sobre a lista para verificar se a variavel pode ser adicionada
                for(int i = 0; i < newList.size(); i++){
                    if(supe.getID().equals(newList.get(i).getID())){ 
                        if(supe.getScope() < newList.get(i).getScope()){ //Se a variavel atual tem um scopo menor do que a ja existente, ela pode ser inserida
                            newList.add(supe);
                        }
                        else{ //caso contrario, se ela tiver o tipo correto, atualize seu valor
                            if(supe.getType().equals(newList.get(i).getType())){
                                newList.get(i).setValue(value, null);
                                return;
                            }
                            else{
                                System.err.println("Valor inserido inválido. Type mismatch("+exp.getLine()+','+exp.getColumn()+')' 
                                + ". Cannot convert " + supe.getType() + " to " + newList.get(i).getType());
                                System.exit(0);
                                return;
                            }
                        }
                    }
                }
                newList.add(supe); //Se nenhuma variavel no ambiente tiver o mesmo ID, essa variavel é adicionada.
            }

        }     
        if(indices != null){ //ID[exp]

            Boolean hasDataArray =
            verificaArrayDeData(id, exp, new ArrayList<>(indices), new ArrayList<>(Fields), "", "", false);

            if(hasDataArray){ //ex: Ponto[exp].y
                assignDataArray(variableDataArray, exp, null, new ArrayList<>());
                
                return;
            }

            //Obtem o resultado da Expressão recursivamente
            Object value = exp.accept(this);

            if(value instanceof LValueNode){ //Resolve LValue para um Valor
                LValueNode lval = (LValueNode) value;
                //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());
                value = acessaVariavel(lval.getId(), lval.getIndexes(), exp, lval.getField());
            }

            String idConcatenado = id ;
            for(String field : Fields){
                idConcatenado = idConcatenado + '.' + field;
            }

            //Verifica se a função atual ja esta registrada no hashmap
            if(!superMap.containsKey(actualFuncName)){ //caso o superMap não possua a função atual como chave
                System.err.println("Uso de variável não incializada. Não permitido("+exp.getLine()+','+exp.getColumn()+')');
                System.exit(0);
                return;
            }

            ArrayList<SuperMap> newList = superMap.get(actualFuncName); //recebe lista atual de SuperMaps da função atual

            if(newList.isEmpty()){ //Se a lista esta vazia, erro
                System.err.println("Uso de variável não incializada. Não permitido("+exp.getLine()+','+exp.getColumn()+')');
                System.exit(0);
                return; 
            }
            else{ //caso contrario, itere sobre a lista para verificar se a variavel pode ser adicionada
                for(int i = 0; i < newList.size(); i++){

                    if(idConcatenado.equals(newList.get(i).getID())){ //se a variavel ja existe no ambiente atual
                        String type = actualType;
                        String idType = newList.get(i).getType();
                        
                        for(Object indice : indices){
                            type = type + "[]";
                        }

                        /* 
                        if(!Fields.isEmpty()){ //Se tiver fields, podemos estar lidando com id.id[0]
                            //caso não esteja vazio, recebe o proximo field e verifica se existe em data
                            idType = validaFields(Fields, idType, exp); //valida os fields recursivamente, caso não encontre erros, lastType terá o tipo final
                        }*/



                        if(type.equals(idType)){ //verifica o tipo
                            AssignSuperValue(newList.get(i).getValue(), indices, 0, value, exp);
                            return;
                        }
                        else{
                            System.err.println("Valor inserido inválido. Type mismatch("+exp.getLine()+','+exp.getColumn()+')' 
                            + ". Cannot convert " + actualType + " to " + idType);
                            System.exit(0);
                            return; 
                        }
                    }
                }
                System.err.println("Uso de variável não incializada. Não permitido("+exp.getLine()+','+exp.getColumn()+')');
                System.exit(0);
                return;
            }
        }
        if(!Fields.isEmpty()){ //é id.id

            //verifica se id existe no contexto atual
            //caso sim, verifica se o primeiro field esta no contexto do tipo de id. ex: x.y com x sendo do tipo Ponto. Verifica se y esta no ambiente Ponto.
            String idConcatenado = id ;
            for(String field : Fields){
                idConcatenado = idConcatenado + '.' + field;
            }

            if(!superMap.containsKey(actualFuncName)){ //caso o superMap não possua a função atual como chave
                System.err.println("Uso de variável não incializada. Não permitido("+exp.getLine()+','+exp.getColumn()+')');
                System.exit(0);
                return;
            }
            ArrayList<SuperMap> newList = superMap.get(actualFuncName); //recebe lista atual de SuperMaps da função atual

            if(newList.isEmpty()){ //Se a lista esta vazia, erro
                System.err.println("Uso de variável não incializada. Não permitido("+exp.getLine()+','+exp.getColumn()+')');
                System.exit(0);
                return; 
            }
            else{ //caso contrario, itere sobre a lista para verificar se a variavel pode ser adicionada
                for(int i = 0; i < newList.size(); i++){
                    if(id.equals(newList.get(i).getID())){ //se a variavel ja existe no ambiente atual
                        String type = newList.get(i).getType();
                        for(int j = 0; j < primitiveTypes.length; j++){
                            if(type.equals(primitiveTypes[j])){ //verifica se o tipo é primitivo, se for: erro
                                System.err.println("Tipos primitivos não possuem fields("+exp.getLine()+','+exp.getColumn()+')');
                                System.exit(0);
                            } 
                            else //se o tipo não for primitivo, verifica se o tipo existe
                            {
                                //O tipo não primitivo precisa existir, a checagem é feita na criação "new"
                                if(Fields.isEmpty()){//verifica se tem mais fields
                                    System.err.println("Uso de variável não incializada. Não permitido("+exp.getLine()+','+exp.getColumn()+')');
                                    System.exit(0);
                                }
                                //caso não esteja vazio, recebe o proximo field e verifica se existe em data
                                String lastType = validaFields(Fields, type, exp); //valida os fields recursivamente, caso não encontre erros, lastType terá o tipo final
                                //registra a variavel

                                //Obtem o resultado da Expressão recursivamente
                                Object value = exp.accept(this);

                                if(value instanceof LValueNode){ //Resolve LValue para um Valor
                                    LValueNode lval = (LValueNode) value;
                                    //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());
                                    value = acessaVariavel(lval.getId(), lval.getIndexes(), exp, lval.getField());
                                }

                                if(value instanceof NewNode){
                                    NewNodeAux(idConcatenado, (NewNode)exp, lastType);
                                    return;
                                }

                                if(!actualType.equals(lastType)){
                                    System.err.println("(É aqui) Valor inserido inválido. Type mismatch("+exp.getLine()+','+exp.getColumn()+')' 
                                    + ". Cannot convert " + actualType + " to " + lastType);
                                    System.exit(0);
                                    return;
                                }
                                actualScope = newList.get(i).getScope(); //seta o scopo como o scopo do id pai
                                assignVariavel(idConcatenado, exp, null, new ArrayList<>());
                                return;
                            }
                        }
                    }
                }
                System.err.println("Uso de variável não incializada. Não permitido("+exp.getLine()+','+exp.getColumn()+')');
                System.exit(0);
                return;
            }
        }
    }
    
    public void assignDataArray(String id, ExpNode exp, List<Object> indices, List<String> Fields){
        //==========================================================Registro de Variavel==================================================================

        if(indices == null && Fields.isEmpty()){ //ID
            //Obtem o resultado da Expressão recursivamente
            Object value = exp.accept(this);

            if(value instanceof LValueNode){ //Resolve LValue para um Valor
                LValueNode lval = (LValueNode) value;
                //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());
                value = acessaVariavel(lval.getId(), lval.getIndexes(), exp, lval.getField());
            }

            if(value instanceof NewNode){
                NewNodeAux(id, (NewNode)value, actualType);
                return;
            }

            String dataArray = getDataArrayType(id);

            //System.out.println(dataArray);

            //cria um novo objeto SuperMap para salvar a variavel, seu valor, seu tipo e seu scopo ===========================================================
            SuperValue superValue = new SuperValue(value, null, new ArrayList<>());

            //cria o objeto supermap para ser inserido no hashMap
            SuperMap supe = new SuperMap(id, actualType, superValue, actualScope);
                
            if(!superMap.containsKey(actualFuncName)){ //caso o superMap não possua a função atual como chave
                superMap.put(actualFuncName, new ArrayList<>()); //cria um enviroment novo sob a função atual e cria uma lista de SuperMaps
            }

            ArrayList<SuperMap> newList = superMap.get(actualFuncName); //recebe lista atual de SuperMaps da função atual

            String dataArrayType = "";
            for(int i = 0; i < newList.size(); i++){
                if(dataArray.equals(newList.get(i).getID())){ 
                    dataArrayType = newList.get(i).getType();
                }
            }
            //System.out.println(dataArrayType);

            String dataArrayTypeNoBrackets = dataArrayType.replaceAll("[\\[\\]]", "");
            //System.out.println(dataArrayTypeNoBrackets);

            if(!actualType.equals(dataArrayTypeNoBrackets)){
                System.err.println("Valor inserido inválido. Type mismatch("+exp.getLine()+','+exp.getColumn()+')' 
                + ". Cannot convert " + actualType + " to " + dataArrayTypeNoBrackets);
                System.exit(0);
                return;
            }

            if(newList.isEmpty()){ //Se a lista esta vazia, adicione a variavel
                newList.add(supe);
            }
            else{ //caso contrario, itere sobre a lista para verificar se a variavel pode ser adicionada
                for(int i = 0; i < newList.size(); i++){
                    if(supe.getID().equals(newList.get(i).getID())){ 
                        if(supe.getScope() < newList.get(i).getScope()){ //Se a variavel atual tem um scopo menor do que a ja existente, ela pode ser inserida
                            newList.add(supe);
                        }
                        else{ //caso contrario, se ela tiver o tipo correto, atualize seu valor
                            if(supe.getType().equals(newList.get(i).getType())){
                                newList.get(i).setValue(value, null);
                                return;
                            }
                            else{
                                System.err.println("Valor inserido inválido. Type mismatch("+exp.getLine()+','+exp.getColumn()+')' 
                                + ". Cannot convert " + supe.getType() + " to " + newList.get(i).getType());
                                System.exit(0);
                                return;
                            }
                        }
                    }
                }
                newList.add(supe); //Se nenhuma variavel no ambiente tiver o mesmo ID, essa variavel é adicionada.
            }

        }     
    }

    public String getDataArrayType(String input){
        // Encontrar o índice do último ponto
        int lastDotIndex = input.lastIndexOf('.');
                
        if (lastDotIndex != -1) {
            // Divide a string em duas partes: antes e depois do último ponto
            String beforeLastDot = input.substring(0, lastDotIndex + 1);
            String afterLastDot = input.substring(lastDotIndex + 1);
            
            // Remove os colchetes apenas da parte após o último ponto
            afterLastDot = afterLastDot.replaceAll("\\[.*\\]$", "");
            
            // Junta as partes novamente
            String output = beforeLastDot + afterLastDot;
            return output;
        } 
        return input;
    }

    public String validaFields(List<String> Fields, String type, ExpNode exp){
        String actualField = Fields.get(0); //recebe o proximo field
        Fields.remove(0); //remove o field atual da lista
        DataDefinition def = dataTypes.get(type); //recebe o DataDefinition do tipo
        Map<String, String> map = def.getFields(); //recebe o hashmap de variaveis de Data
        for(Map.Entry<String, String> field : map.entrySet()){ //itera pelo hashmap para encontrar se actualField pertence ao hashmap
            if(field.getKey().equals(actualField)){ //field existe em Data
                if(!Fields.isEmpty()){ //se existir mais fields:
                    //verifica se é primitivo, se for: Erro
                    for(int j = 0; j < primitiveTypes.length; j++){
                        if(field.getValue().equals(primitiveTypes[j])){ //verifica se o tipo é primitivo, se for: erro
                            System.err.println("Tipos primitivos não possuem fields("+exp.getLine()+','+exp.getColumn()+')');
                            System.exit(0);
                        } 
                    }
                    return validaFields(Fields, field.getValue(), exp);
                }
                else
                {
                    return field.getValue(); //retorna o tipo
                }
            }
        }
        //caso ele itere pelo Data e não encontre o field:
        System.err.println("Field " + actualField + " não existe em " + type);
        System.exit(0);
        return "";
    }

    public void registrarNewVariavel(String id, String type, List<Object> indices){
        //==========================================================Registro de Variavel==================================================================

        Object value = null;

        if(!indices.isEmpty()){ //esta criando um array
            for(Object indice : indices){
                type = type + "[]";
            }
        }

        //cria um novo objeto SuperMap para salvar a variavel, seu valor, seu tipo e seu scopo ===========================================================
        SuperValue superValue = new SuperValue(null, null, new ArrayList<>());

        CreateSuperValue(superValue, indices, 0);

        SuperMap supe = new SuperMap(id, type, superValue, actualScope);

        if(!superMap.containsKey(actualFuncName)){ //caso o superMap não possua a função atual como chave
            superMap.put(actualFuncName, new ArrayList<>()); //cria um enviroment novo sob a função atual e cria uma lista de SuperMaps
        }

        ArrayList<SuperMap> newList = superMap.get(actualFuncName); //recebe lista atual de SuperMaps da função atual


        if(newList.isEmpty()){ //Se a lista esta vazia, adicione a variavel
            newList.add(supe);
        }
        else{ //caso contrario, itere sobre a lista para verificar se a variavel pode ser adicionada
            for(int i = 0; i < newList.size(); i++){
                if(supe.getID().equals(newList.get(i).getID())){ 
                    if(supe.getScope() < newList.get(i).getScope()){ //Se a variavel atual tem um scopo menor do que a ja existente, ela pode ser inserida
                        newList.add(supe);
                        return;
                    }
                    else{ //caso contratio, erro;
                        System.err.println("Nome de variável ja existente");
                        System.exit(0);   
                        return;
                    }
                }
            }
            newList.add(supe); //Se nenhuma variavel no ambiente tiver o mesmo ID, essa variavel é adicionada.
        }

        //================================================================================================================================================
    }

    public void registrarNewVariavelParam(String id, String type, List<Object> indices){
        //==========================================================Registro de Variavel==================================================================

        Object value = null;

        if(!indices.isEmpty()){ //esta criando um array
            for(Object indice : indices){
                type = type + "[]";
            }
        }

        //cria um novo objeto SuperMap para salvar a variavel, seu valor, seu tipo e seu scopo ===========================================================
        SuperValue superValue = new SuperValue(null, null, new ArrayList<>());

        CreateSuperValue(superValue, indices, 0);

        SuperMap supe = new SuperMap(id, type, superValue, actualScope);

        if(!superMap.containsKey(actualFuncName)){ //caso o superMap não possua a função atual como chave
            superMap.put(actualFuncName, new ArrayList<>()); //cria um enviroment novo sob a função atual e cria uma lista de SuperMaps
        }

        ArrayList<SuperMap> newList = superMap.get(actualFuncName); //recebe lista atual de SuperMaps da função atual


        for(int i = 0; i < newList.size(); i++){
            if(supe.getID().equals(newList.get(i).getID())){ 
                newList.get(i).setSuperValue(superValue);
            }
        }
        

        //================================================================================================================================================
    }

    public Object acessaVariavel(String id, List<Object> indices, SuperNode node, List<String> Fields){
        acessandoDataArray = false;

        if(!indices.isEmpty() && !Fields.isEmpty()){ //se a variavel que estamos acessando possui fields e indices, precisamos verificar se existe um array de Data
            String arrayDeData = acessaArrayDeData(id, node, new ArrayList<>(indices), new ArrayList<>(Fields), "", "");
            //System.out.println(acessandoDataArray);
            if(acessandoDataArray){
                return acessaVariavel(arrayDeData, new ArrayList<>(), node, new ArrayList<>());
            }
        }

        //verificar se existem fields
        if(!Fields.isEmpty()){
            for(String field : Fields){
                id = id + '.' + field;
            }
        }

        //verificar se id existe no contexto atual
        if(!superMap.containsKey(actualFuncName)){ //caso o superMap não possua a função atual como chave
            System.err.println("Uso de variável não incializada. Não permitido("+node.getLine()+','+node.getColumn()+')');
            System.exit(0);
        }
        ArrayList<SuperMap> newList = superMap.get(actualFuncName);
        
        if(newList.isEmpty()){ //Se a lista esta vazia, erro
            System.err.println("Uso de variável não incializada. Não permitido("+node.getLine()+','+node.getColumn()+')');
            System.exit(0);
        }
        else{ //caso contrario, itere sobre a lista para verificar se a variavel existe
            for(int i = 0; i < newList.size(); i++){
                //System.out.println("NOSSO ID: " + id);
                //System.out.println("ID TESTADO: " + newList.get(i).getID());

                if(id.equals(newList.get(i).getID())){ //se a variavel ja existe no ambiente atual
                    if(indices.isEmpty()){ //recebe a variavel dos tipos id ou id.id*
                        return newList.get(i).getValue().getValue();
                    }
                    if(!indices.isEmpty()){ //recebe a variavel do tipo id[exp]([exp]*)
                        return getSuperValue(newList.get(i).getValue(), indices, 0, node);
                    }
                }
            }
            System.err.println("Uso de variável não incializada. Não permitido("+node.getLine()+','+node.getColumn()+')');
            System.exit(0);
        }
        return null;
    }

    public void CreateSuperValue(SuperValue supe, List<Object> indices, int aux){ //chamado no primeiro indice
        if(aux >= indices.size())
            return;
        for(int i = 0; i < (int)indices.get(aux); i++){
            SuperValue sv = new SuperValue(null, null, new ArrayList<>());     
            supe.getSuperValues().add(sv);
        }
        for(SuperValue sv : supe.getSuperValues()){
            CreateSuperValue(sv, indices, aux + 1);
        }
    }
    public void AssignSuperValue(SuperValue supe, List<Object> indices, int aux, Object value, SuperNode node){
        if(aux >= indices.size())
            return;
        if((int)indices.get(aux) >= supe.getSuperValues().size()){
            System.err.println("Index " + (int)indices.get(aux) + " out of bounds for length " + supe.getSuperValues().size()
                + '(' + node.getLine() + ',' + node.getColumn() + ')');
            System.exit(0);
            return;
        }
        SuperValue sv = supe.getSuperValues().get((int)indices.get(aux));
        if(aux == indices.size() - 1){ //atingimos o nó que queremos alterar
            sv.setValue(value);
        }
        AssignSuperValue(sv, indices, aux+1, value, node);
    }

    public Object getSuperValue(SuperValue supe, List<Object> indices, int aux, SuperNode node){
        if(aux >= indices.size())
            return null;
        if((int)indices.get(aux) >= supe.getSuperValues().size()){
            System.err.println("Index " + (int)indices.get(aux) + " out of bounds for length " + supe.getSuperValues().size()
                + '(' + node.getLine() + ',' + node.getColumn() + ')');
            System.exit(0);
        }
        SuperValue sv = supe.getSuperValues().get((int)indices.get(aux));
        if(aux == indices.size() - 1){ //atingimos o nó que queremos alterar
            return sv.getValue();
        }
        return getSuperValue(sv, indices, aux+1, node);
    }

    public Boolean verificaArrayDeData(String id, ExpNode exp, List<Object> indices, List<String> Fields, String atual, String dataType, Boolean hasDataArray){
        if(dataType.equals("")){ //Esta verificando no Hashmap de funções
            if(!superMap.containsKey(actualFuncName)){ //caso o superMap não possua a função atual como chave
                System.err.println("Uso de variável não incializada. Não permitido("+exp.getLine()+','+exp.getColumn()+')');
                System.exit(0);
            }
            ArrayList<SuperMap> newList = superMap.get(actualFuncName); //recebe lista atual de SuperMaps da função atual
            if(newList.isEmpty()){ //Se a lista esta vazia, erro
                System.err.println("Uso de variável não incializada. Não permitido("+exp.getLine()+','+exp.getColumn()+')');
                System.exit(0);
            }
            else //variável existe no contexto atual, verifica se id existe
            {
                for(int i = 0; i < newList.size(); i++){
                    if(id.equals(newList.get(i).getID())){ //se a variavel ja existe no ambiente atual
                        String type = newList.get(i).getType();
                        String typeNoBrackets = type.replaceAll("[\\[\\]]", ""); //retira todos os colchetes do typo

                        String idConcatenado = id;

                        //VERIFICAR SE EXISTEM OUTROS FIELDS, SE EXISTIR E O TIPO FOR PRIMITIVO: ERRO
                        Boolean isPrimitive = false;
                        for(int j = 0; j < primitiveTypes.length; j++){
                            if(typeNoBrackets.equals(primitiveTypes[j])){ //verifica se o tipo é primitivo, se for e ainda tiver mais fields: erro
                                isPrimitive = true;
                                if(!Fields.isEmpty()){
                                    System.err.println("Tipos primitivos não possuem fields("+exp.getLine()+','+exp.getColumn()+')');
                                    System.exit(0);
                                }
                            } 
                        }    

                        int count = (int) type.chars().filter(ch -> ch == '[').count(); //verifica quantos arranjos
                        String dType = typeNoBrackets; //Diz q o dataType = ao typo (sem colchetes)
                        //Verificamos se o tipo é um array e quantos arranjos ele possui
                        if(type.contains("[")){ //Se for um array:.
                            if(!isPrimitive) { 
                                hasDataArray = true; //ENCONTROU UM ARRAY DE DATA
                                //Precisamos verificar se o concatenado atual existe na lista de variaveis da função atual.
                                String actualName = atual + id;

                                Boolean inicializada = false;
                                //Ja sabemos que a lista não é vazia, portanto apenas procure a variavel
                                for(int j = 0; j < newList.size(); j++){
                                    if(actualName.equals(newList.get(i).getID())){ //se a variavel ja existe no ambiente atual
                                        //Foi inicializada       
                                        inicializada = true;
                                        //Verificar o tamanho, e se o indice esta dentro dos bounds

                                        List<Object> indexes = new ArrayList<>();
                                        for(int k = 0; k < count; k++){
                                            indexes.add(indices.get(k));
                                        }


                                        AssignSuperValue(newList.get(i).getValue(), indexes, 0, null, exp);
                                    }
                                }
                                if(!inicializada){
                                    System.err.println("Uso de arranjo não inicializado("+exp.getLine()+','+exp.getColumn()+')');
                                    System.exit(0);
                                }

                            } 

                            for(int j = 0; j < count; j++){ //para cada arranjo, atribuiremos um indice
                                idConcatenado = idConcatenado + '[' + indices.get(0) + ']';
                                indices.remove(0); //retiramos o primeiro elemento da lista de indices
                            }
                            atual = atual + idConcatenado;
                            variableDataArray = atual;

                            if(!Fields.isEmpty()){ //se ainda tiver mais fields, continua a concatenação recursivamente
                                String newId = Fields.get(0);
                                Fields.remove(0);
        
                                hasDataArray = verificaArrayDeData(newId, exp, indices, Fields, atual + '.', dType, hasDataArray);
                            }
                            else if(Fields.isEmpty() && !indices.isEmpty())//se não existem mais fields e ainda tem indices: ERRO
                            {
                                System.err.println("ERRO("+exp.getLine()+','+exp.getColumn()+')');
                                System.exit(0);
                            }
                        }
                        else //Se não for array
                        {
                            atual = atual + idConcatenado;
                            variableDataArray = atual;

                            if(!Fields.isEmpty()){ //se ainda tiver mais fields, continua a concatenação recursivamente
                                String newId = Fields.get(0);
                                Fields.remove(0);
        
                                hasDataArray = verificaArrayDeData(newId, exp, indices, Fields, atual + '.', dType, hasDataArray);
                            }
                            else if(Fields.isEmpty() && !indices.isEmpty())//se não existem mais fields e ainda tem indices: ERRO
                            {
                                System.err.println("ERRO("+exp.getLine()+','+exp.getColumn()+')');
                                System.exit(0);
                            }
                        }
                    }
                }
            }
        }
        else
        {
            DataDefinition def = dataTypes.get(dataType); //recebe o DataDefinition do tipo
            Map<String, String> map = def.getFields(); //recebe o hashmap de variaveis de Data

            for(Map.Entry<String, String> field : map.entrySet()){ //itera pelo hashmap para encontrar se actualField pertence ao hashmap
                if(field.getKey().equals(id)){ //field existe em Data
                    String type = field.getValue();
                    String typeNoBrackets = type.replaceAll("[\\[\\]]", ""); //retira todos os colchetes do typo
                    actualType = type;

                    String idConcatenado = id;

                    //VERIFICAR SE EXISTEM OUTROS FIELDS, SE EXISTIR E O TIPO FOR PRIMITIVO: ERRO
                    Boolean isPrimitive = false;
                    for(int j = 0; j < primitiveTypes.length; j++){
                        if(typeNoBrackets.equals(primitiveTypes[j])){ //verifica se o tipo é primitivo, se for e ainda tiver mais fields: erro
                            isPrimitive = true;
                            if(!Fields.isEmpty()){
                                System.err.println("Tipos primitivos não possuem fields("+exp.getLine()+','+exp.getColumn()+')');
                                System.exit(0);
                            }
                        } 
                    }                        

                    int count = (int) type.chars().filter(ch -> ch == '[').count(); //verifica quantos arranjos
                    String dType = typeNoBrackets; //Diz q o dataType = ao typo (sem colchetes)
                    //Verificamos se o tipo é um array e quantos arranjos ele possui
                    if(type.contains("[")){ //Se for um array:.
                        if(!isPrimitive) { 
                            hasDataArray = true; //ENCONTROU UM ARRAY DE DATA
                            String actualName = atual + id;
                            //Precisamos verificar se actualName existe na função atual.

                            //Ja sabemos que o hasmap existe
                            ArrayList<SuperMap> newList = superMap.get(actualFuncName); //recebe lista atual de SuperMaps da função atual

                            Boolean inicializada = false;
                            //Ja sabemos que a lista não é vazia, portanto apenas procure a variavel
                            for(int i = 0; i < newList.size(); i++){
                                if(actualName.equals(newList.get(i).getID())){ //se a variavel ja existe no ambiente atual
                                    //Foi inicializada       
                                    inicializada = true;
                                    //Verificar o tamanho, e se o indice esta dentro dos bounds

                                    List<Object> indexes = new ArrayList<>();
                                    for(int k = 0; k < count; k++){
                                        indexes.add(indices.get(k));
                                    }

                                    AssignSuperValue(newList.get(i).getValue(), indexes, 0, null, exp);
                                }
                            }
                            if(!inicializada){
                                System.err.println("Uso de arranjo não inicializado("+exp.getLine()+','+exp.getColumn()+')');
                                System.exit(0);
                            }
                        } 

                        String actualName = atual + id;
                        //Precisamos verificar se actualName existe na função atual.

                        //Ja sabemos que o hasmap existe
                        ArrayList<SuperMap> newList = superMap.get(actualFuncName); //recebe lista atual de SuperMaps da função atual

                        Boolean isIntinializing = false;
                        Object isNew = exp.accept(this);
                        if(isNew instanceof NewNode){ //Esta inicializando vetor
                            isIntinializing = true;
                        }

                        if(!isIntinializing){
                            Boolean inicializada = false;
                            
                            //Ja sabemos que a lista não é vazia, portanto apenas procure a variavel
                            for(int i = 0; i < newList.size(); i++){
                                if(actualName.equals(newList.get(i).getID())){ //se a variavel ja existe no ambiente atual
                                    //Foi inicializada       
                                    inicializada = true;
                                    //Verificar o tamanho, e se o indice esta dentro dos bounds
    
                                    List<Object> indexes = new ArrayList<>();
                                    for(int k = 0; k < count; k++){
                                        indexes.add(indices.get(k));
                                    }
    
                                    AssignSuperValue(newList.get(i).getValue(), indexes, 0, null, exp);
                                }
                            }
                            if(!inicializada){
                                System.err.println("Uso de arranjo não inicializado("+exp.getLine()+','+exp.getColumn()+')');
                                System.exit(0);
                            }
                        }
                        

                        //para cada arranjo, atribuiremos um indice
                        if(!indices.isEmpty()){
                            for(int j = 0; j < count; j++){ 
                                idConcatenado = idConcatenado + '[' + indices.get(0) + ']';
                                indices.remove(0); //retiramos o primeiro elemento da lista de indices
                            }
                        }
                        atual = atual + idConcatenado;
                        variableDataArray = atual;
                        if(!Fields.isEmpty()){ //se ainda tiver mais fields, continua a concatenação recursivamente
                            String newId = Fields.get(0);
                            Fields.remove(0);
    
                            hasDataArray = verificaArrayDeData(newId, exp, indices, Fields, atual + '.', dType, hasDataArray);
                        }
                        else if(Fields.isEmpty() && !indices.isEmpty())//se não existem mais fields e ainda tem indices: ERRO
                        {
                            System.err.println("ERRO("+exp.getLine()+','+exp.getColumn()+')');
                            System.exit(0);
                        }
                    }
                    else //Se não for array
                    {
                        atual = atual + idConcatenado;
                        variableDataArray = atual;
                        if(!Fields.isEmpty()){ //se ainda tiver mais fields, continua a concatenação recursivamente
                            String newId = Fields.get(0);
                            Fields.remove(0);
    
                            hasDataArray = verificaArrayDeData(newId, exp, indices, Fields, atual + '.', dType, hasDataArray);
                        }
                        else if(Fields.isEmpty() && !indices.isEmpty())//se não existem mais fields e ainda tem indices: ERRO
                        {
                            System.err.println("ERRO("+exp.getLine()+','+exp.getColumn()+')');
                            System.exit(0);
                        }
                    }
                }
            }
        }
        
        return hasDataArray;
    }

    public String acessaArrayDeData(String id, SuperNode exp, List<Object> indices, List<String> Fields,  String dataType ,String concatenada){

        if(dataType.equals("")){
            if(!superMap.containsKey(actualFuncName)){ //caso o superMap não possua a função atual como chave
                System.err.println("Uso de variável não incializada. Não permitido("+exp.getLine()+','+exp.getColumn()+')');
                System.exit(0);
            }
            ArrayList<SuperMap> newList = superMap.get(actualFuncName); //recebe lista atual de SuperMaps da função atual
            if(newList.isEmpty()){ //Se a lista esta vazia, erro
                System.err.println("Uso de variável não incializada. Não permitido("+exp.getLine()+','+exp.getColumn()+')');
                System.exit(0);
            }
            else //variável existe no contexto atual, verifica se id existe
            {
                for(int i = 0; i < newList.size(); i++){
                    if(id.equals(newList.get(i).getID())){ //se a variavel ja existe no ambiente atual
                        String type = newList.get(i).getType();
                        String typeNoBrackets = type.replaceAll("[\\[\\]]", ""); //retira todos os colchetes do typo

                        int count = (int) type.chars().filter(ch -> ch == '[').count(); //verifica quantos arranjos
                        String dType = typeNoBrackets; //Diz q o dataType = ao typo (sem colchetes)
                        //Verificamos se o tipo é um array e quantos arranjos ele possui
                        if(type.contains("[")){ //Se for um array:.

                            //VERIFICAR SE O TIPO DE ID É PRIMITOV
                            Boolean isPrimitive = false;
                            for(int j = 0; j < primitiveTypes.length; j++){
                                if(typeNoBrackets.equals(primitiveTypes[j])){ //verifica se o tipo é primitivo
                                    //System.out.println("É Primitivo");
                                    isPrimitive = true;
                                } 
                            } 

                            if(!isPrimitive) //se for uma array e não é primitivo
                                acessandoDataArray = true; //estamos acessando um data array

                            concatenada = concatenada + id;
                            for(int j = 0; j < count; j++){ //para cada arranjo, atribuiremos um indice
                                concatenada = concatenada + '[' + indices.get(0) + ']';
                                indices.remove(0); //retiramos o primeiro elemento da lista de indices
                            }

                            if(!Fields.isEmpty()){ //se ainda tiver mais fields, continua a concatenação recursivamente passando o prximo field como id
                                String newId = Fields.get(0);
                                Fields.remove(0);
        
                                concatenada = acessaArrayDeData(newId, exp, indices, Fields, dType, concatenada + '.');
                            }
                        }
                        else //não é um array
                        {
                            concatenada = concatenada + id;
    
                            if(!Fields.isEmpty()){ //acessa  função recursivamente passando o proximo field como id
                
                                String newId = Fields.get(0);
                                Fields.remove(0);
                
                                concatenada = acessaArrayDeData(newId, exp, indices, Fields, dType,concatenada + '.'); 
                            }
                        }
                    }
                }
            }
        }
        else
        {
            DataDefinition def = dataTypes.get(dataType); //recebe o DataDefinition do tipo
            Map<String, String> map = def.getFields(); //recebe o hashmap de variaveis de Data

            for(Map.Entry<String, String> field : map.entrySet()){ //itera pelo hashmap para encontrar se actualField pertence ao hashmap
                if(field.getKey().equals(id)){ //field existe em Data
                    String type = field.getValue();
                    String typeNoBrackets = type.replaceAll("[\\[\\]]", ""); //retira todos os colchetes do typo

                    int count = (int) type.chars().filter(ch -> ch == '[').count(); //verifica quantos arranjos
                    String dType = typeNoBrackets; //Diz q o dataType = ao typo (sem colchetes)
                    //Verificamos se o tipo é um array e quantos arranjos ele possui
                    if(type.contains("[")){ //Se for um array:.

                        //VERIFICAR SE O TIPO DE ID É PRIMITOV
                        Boolean isPrimitive = false;
                        for(int j = 0; j < primitiveTypes.length; j++){
                            if(typeNoBrackets.equals(primitiveTypes[j])){ //verifica se o tipo é primitivo
                                //System.out.println("É Primitivo");
                                isPrimitive = true;
                            } 
                        } 

                        if(!isPrimitive) //se for uma array e não é primito
                            acessandoDataArray = true; //estamos acessando um data array

                        concatenada = concatenada + id;
                        for(int j = 0; j < count; j++){ //para cada arranjo, atribuiremos um indice
                            concatenada = concatenada + '[' + indices.get(0) + ']';
                            indices.remove(0); //retiramos o primeiro elemento da lista de indices
                        }

                        if(!Fields.isEmpty()){ //se ainda tiver mais fields, continua a concatenação recursivamente passando o prximo field como id
                            String newId = Fields.get(0);
                            Fields.remove(0);
        
                            concatenada = acessaArrayDeData(newId, exp, indices, Fields, dType, concatenada + '.');
                        }
                    }
                    else //não é um array
                    {
                        concatenada = concatenada + id;

                        if(!Fields.isEmpty()){ //acessa  função recursivamente passando o proximo field como id
                
                            String newId = Fields.get(0);
                            Fields.remove(0);
                
                            concatenada = acessaArrayDeData(newId, exp, indices, Fields, dType,concatenada + '.'); 
                        }
                    }
                }
            }
        }

        return concatenada;
    }

    public String acessaVariavelType(String id, List<Object> indices, SuperNode node, List<String> Fields){
        acessandoDataArray = false;

        if(!indices.isEmpty() && !Fields.isEmpty()){ //se a variavel que estamos acessando possui fields e indices, precisamos verificar se existe um array de Data
            String arrayDeData = acessaArrayDeData(id, node, new ArrayList<>(indices), new ArrayList<>(Fields), "", "");
            //System.out.println(acessandoDataArray);
            if(acessandoDataArray){
                return acessaVariavelType(arrayDeData, new ArrayList<>(), node, new ArrayList<>());
            }
        }

        //verificar se existem fields
        if(!Fields.isEmpty()){
            for(String field : Fields){
                id = id + '.' + field;
            }
        }

        //verificar se id existe no contexto atual
        if(!superMap.containsKey(actualFuncName)){ //caso o superMap não possua a função atual como chave
            System.err.println("Uso de variável não incializada. Não permitido("+node.getLine()+','+node.getColumn()+')');
            System.exit(0);
        }
        ArrayList<SuperMap> newList = superMap.get(actualFuncName);
        
        if(newList.isEmpty()){ //Se a lista esta vazia, erro
            System.err.println("Uso de variável não incializada. Não permitido("+node.getLine()+','+node.getColumn()+')');
            System.exit(0);
        }
        else{ //caso contrario, itere sobre a lista para verificar se a variavel existe
            for(int i = 0; i < newList.size(); i++){
                //System.out.println("NOSSO ID: " + id);
                //System.out.println("ID TESTADO: " + newList.get(i).getID());

                if(id.equals(newList.get(i).getID())){ //se a variavel ja existe no ambiente atual
                    if(indices.isEmpty()){ //recebe a variavel dos tipos id ou id.id*
                        return newList.get(i).getType();
                    }
                    if(!indices.isEmpty()){ //recebe a variavel do tipo id[exp]([exp]*)
                        return newList.get(i).getType().replaceAll("[\\[\\]]", "");
                    }
                }
            }
            System.err.println("Uso de variável não incializada. Não permitido("+node.getLine()+','+node.getColumn()+')');
            System.exit(0);
        }
        return null;
    }

    @Override
    public Object visit(ProgramNode node) {

        List<SuperNode> definitions = node.getPrograms();

        //System.out.println(definitions.size()); // Log para depuração

        //prog -> def+ -> data | fun
        for (SuperNode def : definitions) {
            //System.out.println("Visitando nó: " + def.getClass().getName()); // Log para depuração
            if (def instanceof DefNode) {
                def.accept(this);
            } else if (def instanceof FunNode) { //def -> fun
                def.accept(this);
            } else if (def instanceof DataNode) { //def -> data
                def.accept(this);
            } else {
                System.err.println("Error: Unexpected node type: " + def.getClass().getName());
                System.exit(0);
            }
        }
        return null;
    }
    
    @Override
    public Object visit(DataNode node) {

        //System.out.println("DATA NODE");

        List<DeclNode> declarations = new ArrayList<>();

        for(DeclNode declNode : node.getDeclarations()){ //coleta todas as declarações de 'data'
            declarations.add(declNode);
        }

        DataDefinition def = new DataDefinition();
        for (int i = 0; i < declarations.size(); i++) {
            Boolean achei = false;
            String stringType = declarations.get(i).getType().getStringType();
            stringType = stringType.replaceAll("[\\[\\]]", "");

            for(int j = 0; j < primitiveTypes.length; j++){ //verifica se o tipo é um tipo primitivo
                if(stringType.equals(primitiveTypes[j])){ //se for igual, adicionamos o field
                    def.addField(declarations.get(i).getId(), declarations.get(i).getType().getStringType());
                    achei = true;
                    break;
                } 
            }
            if(!achei){
                //Caso o tipo não seja um tipo primitivo:
                if(!dataTypes.containsKey(stringType)){//verifica se type existe no map de 'datas', se não existir, emite erro
                    System.err.println("Error: Tipo " + declarations.get(i).getType().getStringType() + " desconhecido."
                    + "(" + node.getLine() +"," + node.getColumn()+')');
                    System.exit(0);
                }else{ //se existir, adiciona no mapa 
                    def.addField(declarations.get(i).getId(), declarations.get(i).getType().getStringType());
                }
            }
        }

        dataTypes.put(node.getId(), def);

        return null;
    }

    @Override
    public Object visit(DeclNode node) {

        //System.out.println("DECL NODE");

        return null;
    }

    @Override
    public Object visit(FunNode node) {

        //verifica se o nome da função esta disponivel
        if(functions.containsKey(node.getId())){
            System.err.println("line " + node.getLine() + ':' + node.getColumn() + 
            " Duplicate method " + node.getId());
            System.exit(0);
        }
        if(dataTypes.containsKey(node.getId())){
            System.err.println("line " + node.getLine() + ':' + node.getColumn() + 
            " Method name already exists");
            System.exit(0);
        }

        // Registra a função no mapa de funções usando o nome da função como chave
        //System.out.println("Registrando função: " + node.getId());
        functions.put(node.getId(), node);
        actualFuncName = node.getId();

        actualScope = 0;; 

        ParamsNode pn = new ParamsNode(node.getLine(), node.getColumn(), node.getParams());
        pn.accept(this);

        /* 
        if(actualFuncName.equals("main")){
            System.out.println("Executa a main");
            BlockNode bn = new BlockNode(node.getLine(), node.getColumn(), node.getCmds());
            bn.accept(this);
        }
        */

        return null;
    }

    @Override
    public Object visit(ParamsNode node) {

        //System.out.println("Registrando parâmetros");

        // Itera sobre cada nó de parâmetro na lista de parâmetros
        for (ParamNode param : node.getParams()) {
            param.accept(this);
        }

        return null;
    }

    @Override
    public Object visit(ParamNode node) {

        //System.out.println("PARAM NODE");

        String id = node.getId();
        setAux(id); //usa o auxiliar para registrar a variavel

        String stringType = node.getType().getStringType();
        stringType = stringType.replaceAll("[\\[\\]]", "");

        Boolean achei = false;
        for(int j = 0; j < primitiveTypes.length; j++){ //verifica se o tipo é um tipo primitivo  
            if(stringType.equals(primitiveTypes[j])){ //se for igual, adicionamos o field
                registrarNewVariavel(id, node.getType().getStringType(), new ArrayList<>());
                achei = true;
                break;
            } 
        }
        if(!achei){
            //Caso o tipo não seja um tipo primitivo:
            if(!dataTypes.containsKey(stringType)){//verifica se type existe no map de 'datas', se não existir, emite erro
                System.err.println("Error: Tipo " + node.getType().getStringType() + " desconhecido."
                + "(" + node.getLine() +"," + node.getColumn()+')');
                System.exit(0);
            }else{ //se existir, registra o parametro
                registrarNewVariavel(id, node.getType().getStringType(), new ArrayList<>());
            }
        }

        return null;
    }

    @Override
    public Object visit(TypeNode node) {
        //System.out.println("TYPE NODE");
        if(node.getType() instanceof BTypeNode)
            return node;

        return node.getType().accept(this); //retorna o type para recursão
    }

    //função auxiliar para registrar variaveis
    public void setAux(String id){
        variableIdAux = id;
    }
    public String getAux(){
        return variableIdAux;
    }

    @Override
    public Object visit(BTypeNode node) {
        //System.out.println(node.getBtype());
        return null;
    }


    @Override
    public Object visit(CmdNode node) {
        //System.out.println("CMD NODE");

        return null;
    }

    @Override
    public Object visit(IfNode node) {

        //System.out.println("IF NODE");

        Object value = node.getCondition().accept(this);
        
        //System.out.println("VALOR: LVALUE: " + value);


        Boolean condition = false;
        if(value instanceof LValueNode){
            LValueNode lval = (LValueNode) value;
            //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());
            value = acessaVariavel(lval.getId(), lval.getIndexes(), node, lval.getField());
        }

        if(value instanceof Boolean){
            condition = (Boolean) value;
        }
        else{
            System.err.println("Line: " + node.getLine() + "," + node.getColumn() + " error: incompatible type: if expression must be Bool");
            System.exit(0);
        }

        if (condition) {
            return node.getThenCmd().accept(this);
        } else if (node.getElseCmd() != null) {
            return node.getElseCmd().accept(this);
        }
        return null;
    }

    @Override
    public Object visit(IterateNode node) {

        //Resultado dentro da expressão do iterate precisa ser um Int

        //System.out.println("ITERATE NODE");

        Object value = node.getCondition().accept(this);

        if(value instanceof LValueNode){ //resolve lvalue
            LValueNode lval = (LValueNode) value;
            //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());
            value = acessaVariavel(lval.getId(), lval.getIndexes(), node, lval.getField());
        }

        if(value instanceof Integer){
            int times = (Integer) value;

            for (int i = 0; i < times; i++) {
                node.getBody().accept(this);
            }
        }
        else{
            System.err.println("Line " + node.getLine() + ":" + node.getColumn() + " incompatible types: Iterate expression must be Int");
            System.exit(0);
        }

        return null;
    }

    @Override
    public Object visit(ReadNode node) {

        //System.out.println("READ NODE");

        Object val = node.getLvalue().accept(this);
        LValueNode lval = (LValueNode) val;

        Object value = readInput(); //Lê a entrada do usuario
        //**************************************** A entrada do usuário pode apenas ser de tipos primitivos: ****************************************
        //System.out.println(value.getClass());

        ExpNode exp = new ExpNode(node.getLine(), node.getColumn());

        if(value instanceof Integer){
            actualType = "Int";
            IntNode intNode = new IntNode(exp.getLine(), exp.getColumn(), (Integer)value);
            exp = intNode;
        }else if(value instanceof Float || value instanceof Double){
            actualType = "Float";
            FloatNode floatNode = new FloatNode(exp.getLine(), exp.getColumn(), (Float)value);
            exp = floatNode;
        }else if(value instanceof Boolean){
            actualType = "Bool";
            BoolNode boolNode = new BoolNode(exp.getLine(), exp.getColumn(), (Boolean)value);
            exp = boolNode;
        }else if(value instanceof Character){
            actualType = "Char";
            CharNode charNode = new CharNode(exp.getLine(), exp.getColumn(), (char)value);
            exp = charNode;
        }else{
            System.err.println("Line " + node.getLine() + ":" + node.getColumn() + 
            " incompatible types: the expression cannot be converted to any primitive type: {Int, Float, Bool, Char}");
            System.exit(0);
        }



        assignVariavel(lval.getId(), exp, lval.getIndexes(), lval.getField());


        return null;
    }

    @Override
    public Object visit(PrintNode node) {

        //System.out.println("PRINT NODE");

        Object value = node.getExp().accept(this);

        if(value.equals('\\')){
            System.out.println("");
            return null;
        }
        //System.out.println("Value: " +  value);

        if(value instanceof LValueNode){
            LValueNode lval = (LValueNode) value;
            //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());
            value = acessaVariavel(lval.getId(), lval.getIndexes(), node, lval.getField());
        }
        if(value == null){
            System.err.println("Line: " + node.getLine() + ":" + node.getColumn() + " error: print cannot be applied non-primitive types: null");
            System.exit(0);
        }

        System.out.print(value);
        return null;
    }

    @Override
    public Object visit(ReturnNode node) {
        //System.out.println("RETORNANDO");

        //atribui o proprio nó ao actualreturn, sera trabalhado dentro das funções de CallNode e FuncCallNode
        actualReturn = node;

        //não retorna nada
        return null;
    }

    @Override
    public Object visit(AssignNode node) {  

        //System.out.println("Registrando variável" + node.getLvalue().getId());

        LValueNode lvalue = (LValueNode) node.getLvalue().accept(this);

        ExpNode exp = node.getExp();

        if(exp instanceof FuncCallNode){
            FuncCallNode fc = (FuncCallNode)node.getExp();
            Object v = fc.accept(this);
            if(v instanceof ExpNode)
                exp = (ExpNode) fc.accept(this);
        }

        //Trata quando o assign chama um NewNode no ExpNode
        if(exp instanceof NewNode && lvalue.getField().isEmpty()){
            NewNodeAux(lvalue.getId(), (NewNode)exp, null);
            return null;
        }   

        if(lvalue.getIndexes().isEmpty() && lvalue.getField().isEmpty()){ //lvalue -> ID
            assignVariavel(lvalue.getId(), node.getExp(), null, lvalue.getField());
            return null;
        }
        if(!lvalue.getIndexes().isEmpty()){ //lvalue -> lvalue[ exp ]
            assignVariavel(lvalue.getId(), node.getExp(), lvalue.getIndexes(), lvalue.getField());
            return null;
        }
        if(!lvalue.getField().isEmpty()){ //lvalue -> lvalue.ID
            assignVariavel(lvalue.getId(), node.getExp(), null, lvalue.getField());
            return null;
        }
        
        return null;
    }

    public void NewNodeAux(String id, NewNode expNode, String lastType){

        NewNode newNode = (NewNode) expNode.accept(this);

        //System.out.println("Type: "  + newNode.getType().getStringType());

        if(newNode.getExps().size() != newNode.getNumArray()){
            System.err.println("Error: Tamanho de armazenamento de "  + id +  " não é conhecido"
            + "(" + expNode.getLine() +"," + expNode.getColumn()+')');
            System.exit(0);
        }

        List<Object> indices = new ArrayList<>();
        for(ExpNode exp : newNode.getExps()){
            if(exp != null){
                Object value = exp.accept(this); //Resolve a expressão

                if(value instanceof LValueNode){ //Resolve LValue para um Valor
                    LValueNode lval = (LValueNode) value;
                    //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());
                    value = acessaVariavel(lval.getId(), lval.getIndexes(), expNode, lval.getField());
                }
                if(value instanceof Integer){ //Define que apenas Inteiros podem ser tamanhos de vetores
                    if((int) value > 0)
                        indices.add(value);
                    else{ //Define que apenas inteiros maiores que 0 podem ser Tamanhos de vetores
                        System.err.println("Error: Tamanho de vetores precisam ser maiores que 0"
                        + "(" + expNode.getLine() +"," + expNode.getColumn()+')');
                        System.exit(0);
                    }
                }
                else{
                    System.err.println("Error: Tamanho de vetores precisam ser do tipo Int"
                    + "(" + expNode.getLine() +"," + expNode.getColumn()+')');
                    System.exit(0);
                }
            }
        }

        String type = newNode.getType().getStringType();

        Boolean achei = false;
        for(int j = 0; j < primitiveTypes.length; j++){ //verifica se o tipo é um tipo primitivo
            if(type.equals(primitiveTypes[j])){ //se for igual, adicionamos o field
                registrarNewVariavel(id, newNode.getType().getStringType(), indices);
                achei = true;
                break;
            } 
        }
        if(!achei){
            //Caso o tipo não seja um tipo primitivo:
            if(!dataTypes.containsKey(type)){//verifica se type existe no map de 'datas', se não existir, emite erro
                System.err.println("Error: Tipo " + type + " desconhecido."
                + "(" + expNode.getLine() +"," + expNode.getColumn()+')');
                System.exit(0);
            }else{ //se existir, adiciona no mapa 
                registrarNewVariavel(id, newNode.getType().getStringType(), indices);
            }
        }

        if(!indices.isEmpty()){ //esta criando um array
            for(Object indice : indices){
                type = type + "[]";
            }
        }

        if(lastType != null){
            if(!lastType.equals(type)){
                System.err.println("Error: Valor inserido inválido. Type mismatch("+expNode.getLine()+','+expNode.getColumn()+')' 
                + ". Cannot convert " + type + " to " + lastType);
                System.exit(0);
            }
        }


        //registrar a variavel 
        //registrarNewVariavel(id, newNode.getType().getStringType(), indices);

        return;
    }

    @SuppressWarnings("unused")
    @Override
    public Object visit(CallNode node) {

        //System.out.println("CALLNODE");

        String functioncalling = actualFuncName; //guarda o nome da função que esta chamando a outra


        String functionName = node.getFunctionName();
        FunNode function = functions.get(functionName); 
        
        if (function == null) { //verifica se a função que queremos acessar existe.ad cdf
            System.err.println("Error: Função não definida: " + functionName);
            System.exit(0);
        }

        //Se ela existe: Coletamos os valores necessarios
        List<ExpNode> args = node.getArgs(); //argumentos que vamos passar como parametro
        List<ParamNode> params = function.getParams(); //parametros da função correspondente

        List<TypeNode> returnTypes = function.getReturnTypes(); //Lista de tipos que a função se propoe a retornar
        List<LValueNode> returnValues = node.getReturnValues(); //variaveis que vamos atribuir os valores de retorno da função <lvalue*>

        //=========================================================== Passagem de parametros ===========================================================
        //se o numero de parametros for diferente do numero de argumentos: Erro
        if (args.size() != params.size()) {
            System.err.println("Error: Número de argumentos não corresponde ao número de parâmetros");
            System.exit(0);
        }

        for(int i = 0; i < args.size(); i++){ //Para cada argumento
            Object value = args.get(i).accept(this); //verificamos o resultado desse argumento

            //se o resultado for um Lvalue
            if(value instanceof LValueNode){ //Se for um Lvalue, pegamos o tipo de lvalue e comparamos com a do parametro
                LValueNode lval = (LValueNode) value;
                //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());

                //Verifica se a função atual ja esta registrada no hashmap
                if(!superMap.containsKey(actualFuncName)){ //caso o superMap não possua a função atual como chave
                    System.err.println("Uso de variável não incializada. Não permitido("+node.getLine()+','+node.getColumn()+')');
                    System.exit(0);
                }

                ArrayList<SuperMap> newList = superMap.get(actualFuncName); //recebe lista atual de SuperMaps da função atual

                if(newList.isEmpty()){ //Se a lista esta vazia, erro
                    System.err.println("Uso de variável não incializada. Não permitido("+node.getLine()+','+node.getColumn()+')');
                    System.exit(0);
                }
                else{ //caso contrario, itere sobre a lista para verificar se a variavel pode ser adicionada
                    for(int j = 0; j < newList.size(); j++){
                        //System.out.println(lval.getId());
                        if(lval.getId().equals(newList.get(j).getID())){ //se a variavel ja existe no ambiente atual

                            //comparar o tipo com o do parâmetro
                            Boolean mesmotipo = false;
                            if(newList.get(j).getType().equals(params.get(i).getType().getStringType())){
                                mesmotipo = true;
                                //Se os tipos forem iguais, precisamos copiar tudo do argumento para o parâmetro
                                ArrayList<SuperMap> paramsList = superMap.get(functionName); //Acessamos a lista de supermap da função que estamos chamando
                                //Ja sabemos que ela existe pois comparamos o numero de parametros com argumentos.

                                for(int k = 0; k < paramsList.size(); k++){ //percorre a lista para achar o id do parametro
                                    if(paramsList.get(k).getID().equals(params.get(i).getId())){
                                        //Em java, quando você passa um objeto para um método, você está passando uma referência para esse objeto
                                        SuperValue sv = new SuperValue(newList.get(j).getValue()); //Então precisamos criar um novo super value, caso contrario qualuqer alteraççao dentro da função tambem alterara o original
                                        paramsList.get(k).setSuperValue(sv); //atribui o SuperValue do Argumento ao Parametro
                                    }
                                }
                            }
                            if(!mesmotipo){
                                System.err.println("Error: Valor passado como parâmetro inválido. Type mismatch("+node.getLine()+','+node.getColumn()+')' 
                                + ". Cannot convert " + newList.get(j).getType() + " to " + params.get(i).getType().getStringType());
                                System.exit(0);
                            }
                        }
                    }
                }
            }
            else if(value instanceof NewNode){ //Se for um NewNode, comparamos se o tipo do new é igual ao tipo do parametro
                NewNode nNode = (NewNode) value;

                //concatena colchetes numa string tipo
                String tipo = nNode.getType().getStringType();
                for(int j = 0; j < nNode.getNumArray(); j++){
                    tipo = tipo + "[]";
                }

                if(params.get(i).getType().getStringType().equals(tipo)){
                    actualFuncName = functionName;
                    
                    NewNode newNode = (NewNode) nNode.accept(this);

                    //System.out.println("Type: "  + newNode.getType().getStringType());
            
                    if(newNode.getExps().size() != newNode.getNumArray()){
                        System.err.println("Error: Tamanho de armazenamento de não é conhecido"
                        + "(" + nNode.getLine() +"," + nNode.getColumn()+')');
                        System.exit(0);
                    }
            
                    List<Object> indices = new ArrayList<>();
                    for(ExpNode exp : newNode.getExps()){
                        if(exp != null){
                            Object indice = exp.accept(this); //Resolve a expressão
            
                            if(indice instanceof LValueNode){ //Resolve LValue para um Valor
                                LValueNode lval = (LValueNode) indice;
                                //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());
                                indice = acessaVariavel(lval.getId(), lval.getIndexes(), nNode, lval.getField());
                            }
                            if(indice instanceof Integer){ //Define que apenas Inteiros podem ser tamanhos de vetores
                                if((int) indice > 0)
                                    indices.add(indice);
                                else{ //Define que apenas inteiros maiores que 0 podem ser Tamanhos de vetores
                                    System.err.println("Error: Tamanho de vetores precisam ser maiores que 0"
                                    + "(" + nNode.getLine() +"," + nNode.getColumn()+')');
                                    System.exit(0);
                                }
                            }
                            else{
                                System.err.println("Error: Tamanho de vetores precisam ser do tipo Int"
                                + "(" + nNode.getLine() +"," + nNode.getColumn()+')');
                                System.exit(0);
                            }
                        }
                    }
            
                    String type = newNode.getType().getStringType();
            
                    Boolean achei = false;
                    for(int j = 0; j < primitiveTypes.length; j++){ //verifica se o tipo é um tipo primitivo
                        if(type.equals(primitiveTypes[j])){ //se for igual, adicionamos o field
                            //registrarNewVariavel(id, newNode.getType().getStringType(), indices);
                            registrarNewVariavelParam(params.get(i).getId(), newNode.getType().getStringType(), indices);
                            achei = true;
                            break;
                        } 
                    }
                    if(!achei){
                        //Caso o tipo não seja um tipo primitivo:
                        if(!dataTypes.containsKey(type)){//verifica se type existe no map de 'datas', se não existir, emite erro
                            System.err.println("Error: Tipo " + type + " desconhecido."
                            + "(" + nNode.getLine() +"," + nNode.getColumn()+')');
                            System.exit(0);
                        }else{ //se existir, adiciona no mapa 
                            //registrarNewVariavel(id, newNode.getType().getStringType(), indices);
                            registrarNewVariavelParam(params.get(i).getId(), newNode.getType().getStringType(), indices);
                        }
                    }
            
                    if(!indices.isEmpty()){ //esta criando um array
                        for(Object indice : indices){
                            type = type + "[]";
                        }
                    }        
                }
                else{
                    System.err.println("Error: Valor passado como parâmetro inválido. Type mismatch("+node.getLine()+','+node.getColumn()+')' 
                    + ". Cannot convert " + tipo + " to " + params.get(i).getType().getStringType());
                    System.exit(0);
                }
            }
            else{//Essa area trata caso o value seja um numero, uma booleana ou um char
                //Essa area trata caso o value seja um numero, uma booleana ou um char
                String tipo = "";
                if(value instanceof Integer){
                    tipo = "Int";
                }else if(value instanceof Float || value instanceof Double){
                    tipo = "Float";
                }else if(value instanceof Boolean){
                    tipo = "Bool";
                }else if(value instanceof Character){
                    String aux = (String) value;
                    value = aux.charAt(0);
                    tipo = "Char";
                }

                //--> Comparamos para saber o tipo bate com o do parametro
                if(params.get(i).getType().getStringType().equals(tipo)){
                    ArrayList<SuperMap> newList = superMap.get(node.getFunctionName()); //recebe lista da função que esta sendo chamada

                    if(!newList.isEmpty()){ //Se a lista não esta vazia
                        for(int j = 0; j < newList.size(); j++){
                            if(params.get(i).getId().equals(newList.get(j).getID())){ //se a variavel do ambiente for a mesma de ParamName
                                if(newList.get(i).getType().equals(actualType)) //verifica se o tipo passado como parametro é o mesmo do parâmetro
                                    newList.get(i).setValue(value,null); //atualiza seu valor
                                else{
                                    System.err.println("Error: Valor passado como parâmetro inválido. Type mismatch("+node.getLine()+','+node.getColumn()+')' 
                                    + ". Cannot convert " + actualType + " to " + newList.get(i).getType());
                                    System.exit(0);
                                }
                                break;
                            }
                        }
                    }
                }
                else{
                    System.err.println("Error: Valor passado como parâmetro inválido. Type mismatch("+node.getLine()+','+node.getColumn()+')' 
                    + ". Cannot convert " + tipo + " to " + params.get(i).getType().getStringType());
                    System.exit(0);   
                }

            }

            

            //System.out.println(value);
        }
        //==============================================================================================================================================


        //============================================================= Verifica Retornos ==============================================================
        //Verifica se a chamada dispoe o mesmo numero de variaveis que a função se propoe a retornar
        if(returnValues.size() != returnTypes.size()){
            System.err.println("Line: " + node.getLine() + ':' + node.getColumn() + " número de retornos esperado diferente do número de retornos de " 
            + functionName);
            System.exit(0);
        }

        //Antes de executar os comandos, usa a função chamada como atual
        actualFuncName = functionName;

        //Precisamos setar o actualReturn para null, para não acabar usando return de outras funções
        actualReturn = null;

        //executa os comandos da função chamada, ao encontrar return, para a execução dos cmds
        BlockNode bn = new BlockNode(function.getLine(), function.getColumn(), function.getCmds());
        bn.accept(this);

        //Vamos procurar um return node nos cmds da função:
        ReturnNode returnNode = null;

        if(actualReturn != null){ //existia um return acessivel dentro dos cmds
            returnNode = actualReturn; //atribuimos o return encontrado a nossa variavel para trabalharmos em cima dele
        }

        if(returnNode != null){
            if(returnTypes.size() == 0){ //achou um returnNode em função sem retorno
                System.err.println("Line: " + node.getLine() + ':' + node.getColumn() + " error: void method cannot return a value");
                System.exit(0);
            }
            //Verifica se o returnNode esta retornando a mesma quantidade de valores que a função se dispoe a retornar
            if(returnNode.getExps().size() != returnTypes.size()){
                System.err.println("Line: " + returnNode.getLine() + ':' + returnNode.getColumn() + " número de retornos diferente do número de retornos de " 
                + functionName);
                System.exit(0);
            }
        }
        else{
            if(returnTypes.size() > 0){ //não achou um returnNode em função que se propoe a retornar
                System.err.println("Line: " + node.getLine() + ':' + node.getColumn() + " error: missing return statement");
                System.exit(0);
            }
        }

        //Garantir que os valores retornados seguem os tipos propostos:
        int indexDeRetorno = 0;
        for(ExpNode exp : returnNode.getExps()){ //para cada retorno do returnNode
            Object value = exp.accept(this); //resolve a exp
            String tipo = "";
            if(value instanceof LValueNode){ //verifica o tipo de LValue
                LValueNode lval = (LValueNode) value;

                tipo = acessaVariavelType(lval.getId(), lval.getIndexes(), returnNode, lval.getField());
            }
            else if(value instanceof NewNode){
                NewNode newNode = (NewNode) value;
                tipo = newNode.getType().getStringType();
            }
            else{ //caso não seja lvalue ou newNode, o tipo pode apenas ser primitivo
                if(value instanceof Integer){
                    tipo = "Int";
                }else if(value instanceof Float || value instanceof Double){
                    tipo = "Float";
                }else if(value instanceof Boolean){
                    tipo = "Bool";
                }else if(value instanceof Character){
                    tipo = "Char";
                }
            }
            
            //verifica se o tipo é igual ao tipo de retorno proposto:
            if(!tipo.equals(returnTypes.get(indexDeRetorno).getStringType())){
                System.err.println("Line " + node.getLine() + ":" + node.getColumn() + 
                " error: incompatible types: " + tipo + " cannot be converted to " + returnTypes.get(indexDeRetorno).getStringType());
                System.exit(0);
            }

            indexDeRetorno++;
        }
        
        //Ja garantimos a integridade do return, precisamos agora atribuir os valores
        if(returnNode != null){
            List<ExpNode> exps = returnNode.getExps();
            int tipoAtual = 0;
            for(ExpNode exp : exps){ //para cada exp na lista de retornos

                actualFuncName = functionName; //Usamos ara garantir o acesso na função correta

                Object value = exp.accept(this); //Se for BinOp, UnOp, mesmo usando variaveis, retorna o valor correto

                /* 
                if(value instanceof LValueNode){ //Se for LValue, precisa acessar o valor
                    LValueNode lval = (LValueNode) value;
                    //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());
                    value = acessaVariavel(lval.getId(), lval.getIndexes(), node, lval.getField());
                }*/

                if(value instanceof LValueNode){ //Se for um Lvalue, pegamos o tipo de lvalue e comparamos com a do retorno
                    LValueNode lval = (LValueNode) value;
                    //System.out.println("ID DO RETORN: " + lval.getId());
                    //System.out.println("INDEXES DO RETORN: " + lval.getIndexes());

                    //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());
                    LValueNode lvalueQueRecebe = returnValues.get(tipoAtual);
                    //System.out.println("ID QUE RECEBE: " + lvalueQueRecebe.getId());

                    //Verifica se a função que chamou ja esta registrada no hashmap
                    if(!superMap.containsKey(functioncalling)){
                        superMap.put(functioncalling, new ArrayList<>()); //cria um enviroment novo sob a função atual e cria uma lista de SuperMaps
                    }

                    //recebe a lista de SuperMaps da função que chamou
                    ArrayList<SuperMap> funcList = superMap.get(functioncalling); 
                    //System.out.println(funcList);

                    if(funcList.isEmpty()){ //Se a lista esta vazia
                        //System.out.println(lvalueQueRecebe.getId() + " Não existe");
                        //cria um novo objeto SuperMap para salvar a variavel, seu valor, seu tipo e seu scopo ===========================================================
                        ArrayList<SuperMap> returnList = superMap.get(functionName); //Acessamos a lista de supermap da função que estamos chamando
                        //Ja sabemos que ela existe pois comparamos o numero de parametros com argumentos.
                        for(int k = 0; k < returnList.size(); k++){ //percorre a lista para achar o id do parametro
                            if(returnList.get(k).getID().equals(lval.getId())){ //encontramos o id do retorno

                                Object valorrr;
                                if(!lval.getIndexes().isEmpty()){//estamos pegando um valor especifico de um array
                                    valorrr = acessaVariavel(lval.getId(), lval.getIndexes(), returnNode, lval.getField());

                                    SuperValue sv = new SuperValue(valorrr, new ArrayList<>(), new ArrayList<>());

                                    //cria o objeto supermap para ser inserido no hashMap
                                    SuperMap supe = new SuperMap(lvalueQueRecebe.getId(), returnTypes.get(tipoAtual).getStringType(), sv, actualScope);
    
                                    funcList.add(supe);
                                    break;
                                }
                                if(!lval.getField().isEmpty()){
                                    valorrr = acessaVariavel(lval.getId(), lval.getIndexes(), returnNode, lval.getField());

                                    SuperValue sv = new SuperValue(valorrr, new ArrayList<>(), new ArrayList<>());

                                    //cria o objeto supermap para ser inserido no hashMap
                                    SuperMap supe = new SuperMap(lvalueQueRecebe.getId(), returnTypes.get(tipoAtual).getStringType(), sv, actualScope);
    
                                    funcList.add(supe);
                                    break;
                                }

                                SuperValue sv = new SuperValue(returnList.get(k).getValue()); 
    
                                //cria o objeto supermap para ser inserido no hashMap
                                SuperMap supe = new SuperMap(lvalueQueRecebe.getId(), returnTypes.get(tipoAtual).getStringType(), sv, actualScope);
    
                                funcList.add(supe);
                                break;
                            }
                        }
                    }
                    else{ //caso contrario, itere sobre a lista para verificar se a variavel pode ser adicionada
                        Boolean existe = false;
                        for(int j = 0; j < funcList.size(); j++){
                            //System.out.println("CHEGOU ID" + lvalueQueRecebe.getId());
                            //System.out.println("Func ID " + funcList.get(j).getID());
                            if(lvalueQueRecebe.getId().equals(funcList.get(j).getID())){ //se a variavel id ja existe no ambiente da função que chamou
                                existe = true;
                                //comparar o tipo com o do parâmetro
                                Boolean mesmotipo = false;
                                //System.out.println("tipoAtual " + tipoAtual);
                                //System.out.println("Tipo de " +  lvalueQueRecebe.getId() + " :" + funcList.get(j).getType());
                                //System.out.println("Tipo do retorno : " + returnTypes.get(tipoAtual).getStringType());
                                if(funcList.get(j).getType().equals(returnTypes.get(tipoAtual).getStringType())){
                                    mesmotipo = true;
                                    //Se os tipos forem iguais, precisamos copiar tudo do argumento para o parâmetro
                                    ArrayList<SuperMap> returnList = superMap.get(functionName); //Acessamos a lista de supermap da função que estamos chamando
                                    //Ja sabemos que ela existe pois comparamos o numero de parametros com argumentos.
                                    


                                    for(int k = 0; k < returnList.size(); k++){ //percorre a lista para achar o id do parametro
                                        //System.out.println("Id do retorno: " + lval.getId());
                                        //System.out.println("ID NA LISTA: " + returnList.get(k).getID());
                                        if(returnList.get(k).getID().equals(lval.getId())){ //encontramos o id do retorno

                                            Object valorrr;
                                            if(!lval.getIndexes().isEmpty()){//estamos pegando um valor especifico de um array
                                                valorrr = acessaVariavel(lval.getId(), lval.getIndexes(), returnNode, lval.getField());

                                                //System.out.println(valorrr);

                                                funcList.get(j).setValue(valorrr, null);
                                                break;
                                            }
                                            if(!lval.getField().isEmpty()){
                                                valorrr = acessaVariavel(lval.getId(), lval.getIndexes(), returnNode, lval.getField());

                                                funcList.get(j).setValue(valorrr, null);
                                                break;
                                            }

                                            //Em java, quando você passa um objeto para um método, você está passando uma referência para esse objeto
                                            SuperValue sv = new SuperValue(returnList.get(k).getValue()); 
                                            //Então precisamos criar um novo super value, caso contrario qualuqer alteraççao dentro da função tambem 
                                            //alterara o original

                                            funcList.get(j).setSuperValue(sv); //atribui o SuperValue do return a variavel
                                            break;

                                        }
                                    }
                                    
                                }
                                if(!mesmotipo){
                                    System.err.println("Error: Valor passado de retorno inválido. Type mismatch("+node.getLine()+','+node.getColumn()+')' 
                                    + ". Cannot convert " + returnTypes.get(tipoAtual).getStringType() + " to " + funcList.get(j).getType());
                                    System.exit(0);
                                }
                            }
                        }
                        if(!existe){
                            //System.out.println(lvalueQueRecebe.getId() + " Não existe");
                            //cria um novo objeto SuperMap para salvar a variavel, seu valor, seu tipo e seu scopo ===========================================================
                            ArrayList<SuperMap> returnList = superMap.get(functionName); //Acessamos a lista de supermap da função que estamos chamando
                            //Ja sabemos que ela existe pois comparamos o numero de parametros com argumentos.
                            for(int k = 0; k < returnList.size(); k++){ //percorre a lista para achar o id do parametro
                                if(returnList.get(k).getID().equals(lval.getId())){ //encontramos o id do retorno

                                    Object valorrr;
                                    if(!lval.getIndexes().isEmpty()){//estamos pegando um valor especifico de um array
                                        valorrr = acessaVariavel(lval.getId(), lval.getIndexes(), returnNode, lval.getField());

                                        SuperValue sv = new SuperValue(valorrr, new ArrayList<>(), new ArrayList<>());

                                        //cria o objeto supermap para ser inserido no hashMap
                                        SuperMap supe = new SuperMap(lvalueQueRecebe.getId(), returnTypes.get(tipoAtual).getStringType(), sv, actualScope);
        
                                        funcList.add(supe);
                                        break;
                                    }
                                    if(!lval.getField().isEmpty()){
                                        valorrr = acessaVariavel(lval.getId(), lval.getIndexes(), returnNode, lval.getField());

                                        SuperValue sv = new SuperValue(valorrr, new ArrayList<>(), new ArrayList<>());

                                        //cria o objeto supermap para ser inserido no hashMap
                                        SuperMap supe = new SuperMap(lvalueQueRecebe.getId(), returnTypes.get(tipoAtual).getStringType(), sv, actualScope);
        
                                        funcList.add(supe);
                                        break;
                                    }

                                    SuperValue sv = new SuperValue(returnList.get(k).getValue()); 
        
                                    //cria o objeto supermap para ser inserido no hashMap
                                    SuperMap supe = new SuperMap(lvalueQueRecebe.getId(), returnTypes.get(tipoAtual).getStringType(), sv, actualScope);
        
                                    funcList.add(supe);
                                    break;
                                }
                            }
                        }
                    }
                }
                else if(value instanceof NewNode){
                    actualFuncName = functioncalling;
                    LValueNode lvalueQueRecebe = returnValues.get(tipoAtual);
                    NewNode nn = (NewNode) value;
                    NewNodeAux(lvalueQueRecebe.getId(), (NewNode)value, nn.getType().getStringType());
                }
                else{ //caso não seja lvalue ou newNode, o tipo pode apenas ser primitivo
                    //System.out.println(value);

                    //Descobrir o tipo dos retornos e verificar se é igual ao tipo porposto
                    ExpNode newExp = new ExpNode(exp.getLine(), exp.getColumn());

                    String tipo = "";
                    if(value instanceof Integer){
                        tipo = "Int";
                        newExp = new IntNode(tipoAtual, tipoAtual, (Integer)value);
                    }else if(value instanceof Float || value instanceof Double){
                        tipo = "Float";
                        newExp = new FloatNode(tipoAtual, tipoAtual, (Float)value);
                    }else if(value instanceof Boolean){
                        tipo = "Bool";
                        newExp = new BoolNode(tipoAtual, tipoAtual, (Boolean)value);
                    }else if(value instanceof Character){
                        char ch = (char) value;
                        tipo = "Char";
                        newExp = new CharNode(tipoAtual, tipoAtual, ch);
                    }

                    //System.out.println("tipo do retorno: " + tipo);
                    //System.out.println("tipo esperado: " + returnTypes.get(tipoAtual).getStringType());

                    if(tipo.equals(returnTypes.get(tipoAtual).getStringType())){

                        LValueNode lvalue = returnValues.get(tipoAtual);

                        Object valor = newExp.accept(this);
                        //System.out.println(valor);

                        //System.out.println("ID: " + lvalue.getId());

                        //Precisamos falar que ele vai procurar na função que chamou a função
                        actualFuncName = functioncalling;
                        //Para procurar a variavel no ambiente correto
                        if(lvalue.getIndexes().isEmpty() && lvalue.getField().isEmpty()){ //lvalue -> ID
                            assignVariavel(lvalue.getId(), newExp, null, lvalue.getField());
                        }
                        if(!lvalue.getIndexes().isEmpty()){ //lvalue -> lvalue[ exp ]
                            assignVariavel(lvalue.getId(), newExp, lvalue.getIndexes(), lvalue.getField());
                        }
                        if(!lvalue.getField().isEmpty()){ //lvalue -> lvalue.ID
                            assignVariavel(lvalue.getId(), newExp, null, lvalue.getField());
                        }
                    }
                    else{
                        System.err.println("Line: " + node.getLine() + ':' + node.getColumn() + " error: incompatible types: " + tipo 
                        + " cannot be converted to " + returnTypes.get(tipoAtual).getStringType());
                        System.exit(0);
                    }
                }
                
                tipoAtual++;
            }
        }

        //Após executar os comandos, voltar para a função que chamou
        actualFuncName = functioncalling;

        return null;
    }

    @Override
    public Object visit(ExpsNode node) {

        //System.out.println("EXPSNODE");

        // Avalia todas as expressões e retorna o resultado da última
        Object result = null;
        for (ExpNode exp : node.getExpressions()) {
            result = exp.accept(this);
        }
        return result;
    }

    @Override
    public Object visit(LValueNode node) {

        if(node.getLvalue() == null){ //lvalue -> ID
            return node;
        }
        if(node.getIndice() != null){ //lvalue -> lvalue [ exp ] | lvalue.ID
            LValueNode lvalue = (LValueNode) node.getLvalue().accept(this);

            ArrayList<String> fields = new ArrayList<>(); //carrega os fields para o proximo node
            if(!lvalue.getField().isEmpty()){
                fields.addAll(lvalue.getField());
            }

            ArrayList<Object> indexes = new ArrayList<>(); //carrega os indexes para o proximo node
            if(!lvalue.getIndexes().isEmpty()){ //recebe os indexes de forma recursiva
                indexes.addAll(lvalue.getIndexes());
            }

            Object index = node.getIndice().accept(this);
            if(index instanceof LValueNode){
                LValueNode lval = (LValueNode) index;
                //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());
                index = acessaVariavel(lval.getId(), lval.getIndexes(), node, lval.getField());
            }
            if(index instanceof Integer){
                indexes.add(index);
            }
            else{
                System.err.println("Error: Indices de vetores precisam ser do tipo Int"
                + "(" + node.getLine() +"," + node.getColumn()+')');
                System.exit(0);
            }

            return new LValueNode(node.getLine(), node.getColumn(), lvalue.getId(), null, null, fields, indexes);
        }
        else{
            
            LValueNode lvalue = (LValueNode) node.getLvalue().accept(this);

            ArrayList<String> fields = new ArrayList<>(); //carrega os fields para o proximo node
            if(!lvalue.getField().isEmpty()){
                fields.addAll(lvalue.getField());
            }

            ArrayList<Object> indexes = new ArrayList<>(); //carrega os indexes para o proximo node
            if(!lvalue.getIndexes().isEmpty()){ //recebe os indexes de forma recursiva
                indexes.addAll(lvalue.getIndexes());
            }
            String field = node.getField().get(0);
            fields.add(field);
            return new LValueNode(node.getLine(), node.getColumn(), lvalue.getId(), null, null, fields, indexes);
        }
    }

    @Override
    public Object visit(BinOpNode node) {

        //System.out.println("Binary Operation");
        Object left = node.getLeft().accept(this);
        Object right = node.getRight().accept(this);

        if(left instanceof LValueNode){
            LValueNode lval = (LValueNode) left;
            //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());
            left = acessaVariavel(lval.getId(), lval.getIndexes(), node, lval.getField());
        }
        if(right instanceof LValueNode){
            LValueNode lval = (LValueNode) right;
            //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());
            right = acessaVariavel(lval.getId(), lval.getIndexes(), node, lval.getField());
        }

        switch (node.getOp()) { // exp + exp
            case "+":
                if(left instanceof Integer && right instanceof Integer){
                    return (Integer) left + (Integer) right;
                }
                else if(left instanceof Float && right instanceof Float){
                    return (Float) left + (Float) right;
                }
                else{
                    System.err.println("Uso incorreto do operador '+'. Finalizando programa!");
                    System. exit(0);
                }
            case "-": // exp - exp
                if(left instanceof Integer && right instanceof Integer)
                    return (Integer) left - (Integer) right;
                else if(left instanceof Float && right instanceof Float){
                    return (Float) left - (Float) right;
                }
                else{
                    System.err.println("Uso incorreto do operador '-'. Finalizando programa!");
                    System. exit(0);
                }
            case "*": // exp * exp
                if(left instanceof Integer && right instanceof Integer)
                    return (Integer) left * (Integer) right;
                else if(left instanceof Float && right instanceof Float){
                    return (Float) left * (Float) right;
                }
                else{
                    System.err.println("Uso incorreto do operador '*'. Finalizando programa!");
                    System. exit(0);
                }
            case "/": // exp / exp
                if(left instanceof Integer && right instanceof Integer)
                    return (Integer) left / (Integer) right;
                else if(left instanceof Float && right instanceof Float){
                    return (Float) left / (Float) right;
                }
                else{
                    System.err.println("Uso incorreto do operador '/'. Finalizando programa!");
                    System. exit(0);
                }
            
            //---------------------------------------------------------------------------------------
            case "%": // exp % exp
                if(left instanceof Integer && right instanceof Integer)
                    return (Integer) left % (Integer) right;
                else{
                    System.err.println("Uso incorreto do operador '%'. Finalizando programa!");
                    System. exit(0);
                }
            //---------------------------------------------------------------------------------------
            case "==": // exp == exp
                return left.equals(right);
            case "!=": // exp != exp
                return !left.equals(right);
            case "<": // exp < exp
                if(left instanceof Integer && right instanceof Integer)
                    return (Integer) left < (Integer) right;
                else if(left instanceof Float && right instanceof Float){
                    return (Float) left < (Float) right;
                }
                else if(left instanceof Character && right instanceof Character){
                    return (Character) left < (Character) right;
                }
                else{
                    System.err.println("Uso incorreto do operador '<'. Finalizando programa!");
                    System. exit(0);
                }
            //---------------------------------------------------------------------------------------
            case "&&":  // exp && exp
                if(left instanceof Boolean && right instanceof Boolean)
                    return (Boolean) left && (Boolean) right;
                else{
                    System.err.println("Uso incorreto do operador '&&'. Finalizando programa!");
                    System. exit(0);
                }
            default:
                System.err.println("Error: Operador desconhecido: " + node.getOp());
                System.exit(0);
                throw new RuntimeException();
        }
    }

    @Override
    public Object visit(UnOpNode node) {

        //System.out.println("Unitary Operation");

        Object value = node.getExpressions().accept(this);

        if(value instanceof LValueNode){
            LValueNode lval = (LValueNode) value;
            //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());
            value = acessaVariavel(lval.getId(), lval.getIndexes(), node, lval.getField());
        }

        switch (node.getOp()) {
            case "!": //!exp
                if(value instanceof Boolean)
                    return !(Boolean) value; 
                    else{
                        System.err.println("Uso incorreto do operador '!value'. Finalizando programa!");
                        System. exit(0);
                    }
            case "-": //-exp
                if(value instanceof Integer)
                    return -(Integer) value;
                else if(value instanceof Float){
                    return -(Float) value;
                }
                else{
                    System.err.println("Uso incorreto do operador '- value'. Finalizando programa!");
                    System. exit(0);
                }
            default:
                System.err.println("Error: Operador desconhecido: " + node.getOp());
                System.exit(0);
                throw new RuntimeException();
        }
    }

    @Override
    public Object visit(BoolNode node) {

        //System.out.println("BoolNode");

        actualType = "Bool";
        return node.getValue();
    }

    @Override
    public Object visit(NullNode node) {

        //System.out.println("NullNode");

        return null;
    }

    @Override
    public Object visit(IntNode node) {

        //System.out.println("IntNode");

        actualType = "Int";

        return node.getValue();
    }

    @Override
    public Object visit(FloatNode node) {

        //System.out.println("FloatNode");

        actualType = "Float";

        return node.getValue();
    }

    @Override
    public Object visit(CharNode node) {

        //System.out.println("CharNode");

        actualType = "Char";

        return node.getValue();
    }

    @Override
    public Object visit(NewNode node) {

        return node;
    }

    @Override
    public Object visit(FuncCallNode node) {

        //System.out.println("FuncCallNode");

        String functioncalling = actualFuncName; //guarda o nome da função que esta chamando a outra


        String functionName = node.getFunctionName();
        FunNode function = functions.get(functionName); 
        
        if (function == null) { //verifica se a função que queremos acessar existe.ad cdf
            System.err.println("Error: Função não definida: " + functionName);
            System.exit(0);
        }

        //Se ela existe: Coletamos os valores necessarios
        List<ExpNode> args = node.getArgs(); //argumentos que vamos passar como parametro
        List<ParamNode> params = function.getParams(); //parametros da função correspondente

        List<TypeNode> returnTypes = function.getReturnTypes(); //Lista de tipos que a função se propoe a retornar
        int index = -1;

        //Checar integridade de Index: =================================================================================================================
        Object indexValue = node.getIndex().accept(this); //visitamos a expressão que faz referencia ao index
        if(indexValue instanceof LValueNode){ //resolvemos LValue para um valor
            LValueNode lval = (LValueNode) indexValue;
            //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());
            indexValue = acessaVariavel(lval.getId(), lval.getIndexes(), node, lval.getField());
        }
        if(indexValue instanceof Integer){ //se value for um inteiro
            index = (Integer) indexValue;
            if(index < 0 || index >= returnTypes.size()){ //Se value for menor do que 0 || Se value >= returnTypes.size(): ERRO:out of bounds
                System.err.println("line " + node.getLine() + ":" + node.getColumn() + 
                " Index " + index + " out of bounds for length " + returnTypes.size());
                System.exit(0);
            }
        }
        else{ //se value não for um inteiro: erro
            System.err.println("line " + node.getLine() + ":" + node.getColumn() + 
            " error: incompatible types: index must be Int");
            System.exit(0);
        }
        //==============================================================================================================================================


        //=========================================================== Passagem de parametros ===========================================================
        //se o numero de parametros for diferente do numero de argumentos: Erro
        if (args.size() != params.size()) {
            System.err.println("Error: Número de argumentos não corresponde ao número de parâmetros");
            System.exit(0);
        }

        for(int i = 0; i < args.size(); i++){ //Para cada argumento
            Object value = args.get(i).accept(this); //verificamos o resultado desse argumento

            //se o resultado for um Lvalue
            if(value instanceof LValueNode){ //Se for um Lvalue, pegamos o tipo de lvalue e comparamos com a do parametro
                LValueNode lval = (LValueNode) value;
                //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());

                //Verifica se a função atual ja esta registrada no hashmap
                if(!superMap.containsKey(actualFuncName)){ //caso o superMap não possua a função atual como chave
                    System.err.println("Uso de variável não incializada. Não permitido("+node.getLine()+','+node.getColumn()+')');
                    System.exit(0);
                }

                ArrayList<SuperMap> newList = superMap.get(actualFuncName); //recebe lista atual de SuperMaps da função atual

                if(newList.isEmpty()){ //Se a lista esta vazia, erro
                    System.err.println("Uso de variável não incializada. Não permitido("+node.getLine()+','+node.getColumn()+')');
                    System.exit(0);
                }
                else{ //caso contrario, itere sobre a lista para verificar se a variavel pode ser adicionada
                    for(int j = 0; j < newList.size(); j++){
                        //System.out.println(lval.getId());
                        if(lval.getId().equals(newList.get(j).getID())){ //se a variavel ja existe no ambiente atual

                            //comparar o tipo com o do parâmetro
                            Boolean mesmotipo = false;
                            if(newList.get(j).getType().equals(params.get(i).getType().getStringType())){
                                mesmotipo = true;
                                //Se os tipos forem iguais, precisamos copiar tudo do argumento para o parâmetro
                                ArrayList<SuperMap> paramsList = superMap.get(functionName); //Acessamos a lista de supermap da função que estamos chamando
                                //Ja sabemos que ela existe pois comparamos o numero de parametros com argumentos.

                                for(int k = 0; k < paramsList.size(); k++){ //percorre a lista para achar o id do parametro
                                    if(paramsList.get(k).getID().equals(params.get(i).getId())){
                                        //Em java, quando você passa um objeto para um método, você está passando uma referência para esse objeto
                                        SuperValue sv = new SuperValue(newList.get(j).getValue()); //Então precisamos criar um novo super value, caso contrario qualuqer alteraççao dentro da função tambem alterara o original
                                        paramsList.get(k).setSuperValue(sv); //atribui o SuperValue do Argumento ao Parametro
                                    }
                                }
                            }
                            if(!mesmotipo){
                                System.err.println("Error: Valor passado como parâmetro inválido. Type mismatch("+node.getLine()+','+node.getColumn()+')' 
                                + ". Cannot convert " + newList.get(j).getType() + " to " + params.get(i).getType().getStringType());
                                System.exit(0);
                            }
                        }
                    }
                }
            }
            else if(value instanceof NewNode){ //Se for um NewNode, comparamos se o tipo do new é igual ao tipo do parametro
                NewNode nNode = (NewNode) value;

                //concatena colchetes numa string tipo
                String tipo = nNode.getType().getStringType();
                for(int j = 0; j < nNode.getNumArray(); j++){
                    tipo = tipo + "[]";
                }

                if(params.get(i).getType().getStringType().equals(tipo)){
                    actualFuncName = functionName;
                    
                    NewNode newNode = (NewNode) nNode.accept(this);

                    //System.out.println("Type: "  + newNode.getType().getStringType());
            
                    if(newNode.getExps().size() != newNode.getNumArray()){
                        System.err.println("Error: Tamanho de armazenamento de não é conhecido"
                        + "(" + nNode.getLine() +"," + nNode.getColumn()+')');
                        System.exit(0);
                    }
            
                    List<Object> indices = new ArrayList<>();
                    for(ExpNode exp : newNode.getExps()){
                        if(exp != null){
                            Object indice = exp.accept(this); //Resolve a expressão
            
                            if(indice instanceof LValueNode){ //Resolve LValue para um Valor
                                LValueNode lval = (LValueNode) indice;
                                //System.out.println("ID: " + lval.getId() + " Indices: " + lval.getIndexes() + " Fields: " + lval.getField());
                                indice = acessaVariavel(lval.getId(), lval.getIndexes(), nNode, lval.getField());
                            }
                            if(indice instanceof Integer){ //Define que apenas Inteiros podem ser tamanhos de vetores
                                if((int) indice > 0)
                                    indices.add(indice);
                                else{ //Define que apenas inteiros maiores que 0 podem ser Tamanhos de vetores
                                    System.err.println("Error: Tamanho de vetores precisam ser maiores que 0"
                                    + "(" + nNode.getLine() +"," + nNode.getColumn()+')');
                                    System.exit(0);
                                }
                            }
                            else{
                                System.err.println("Error: Tamanho de vetores precisam ser do tipo Int"
                                + "(" + nNode.getLine() +"," + nNode.getColumn()+')');
                                System.exit(0);
                            }
                        }
                    }
            
                    String type = newNode.getType().getStringType();
            
                    Boolean achei = false;
                    for(int j = 0; j < primitiveTypes.length; j++){ //verifica se o tipo é um tipo primitivo
                        if(type.equals(primitiveTypes[j])){ //se for igual, adicionamos o field
                            //registrarNewVariavel(id, newNode.getType().getStringType(), indices);
                            registrarNewVariavelParam(params.get(i).getId(), newNode.getType().getStringType(), indices);
                            achei = true;
                            break;
                        } 
                    }
                    if(!achei){
                        //Caso o tipo não seja um tipo primitivo:
                        if(!dataTypes.containsKey(type)){//verifica se type existe no map de 'datas', se não existir, emite erro
                            System.err.println("Error: Tipo " + type + " desconhecido."
                            + "(" + nNode.getLine() +"," + nNode.getColumn()+')');
                            System.exit(0);
                        }else{ //se existir, adiciona no mapa 
                            //registrarNewVariavel(id, newNode.getType().getStringType(), indices);
                            registrarNewVariavelParam(params.get(i).getId(), newNode.getType().getStringType(), indices);
                        }
                    }
            
                    if(!indices.isEmpty()){ //esta criando um array
                        for(Object indice : indices){
                            type = type + "[]";
                        }
                    }        
                }
                else{
                    System.err.println("Error: Valor passado como parâmetro inválido. Type mismatch("+node.getLine()+','+node.getColumn()+')' 
                    + ". Cannot convert " + tipo + " to " + params.get(i).getType().getStringType());
                    System.exit(0);
                }
            }
            else{//Essa area trata caso o value seja um numero, uma booleana ou um char
                //Essa area trata caso o value seja um numero, uma booleana ou um char
                String tipo = "";
                if(value instanceof Integer){
                    tipo = "Int";
                }else if(value instanceof Float || value instanceof Double){
                    tipo = "Float";
                }else if(value instanceof Boolean){
                    tipo = "Bool";
                }else if(value instanceof Character){
                    String aux = (String) value;
                    value = aux.charAt(0);
                    tipo = "Char";
                }

                //--> Comparamos para saber o tipo bate com o do parametro
                if(params.get(i).getType().getStringType().equals(tipo)){
                    ArrayList<SuperMap> newList = superMap.get(node.getFunctionName()); //recebe lista da função que esta sendo chamada

                    if(!newList.isEmpty()){ //Se a lista não esta vazia
                        for(int j = 0; j < newList.size(); j++){
                            if(params.get(i).getId().equals(newList.get(j).getID())){ //se a variavel do ambiente for a mesma de ParamName
                                if(newList.get(i).getType().equals(actualType)) //verifica se o tipo passado como parametro é o mesmo do parâmetro
                                    newList.get(i).setValue(value,null); //atualiza seu valor
                                else{
                                    System.err.println("Error: Valor passado como parâmetro inválido. Type mismatch("+node.getLine()+','+node.getColumn()+')' 
                                    + ". Cannot convert " + actualType + " to " + newList.get(i).getType());
                                    System.exit(0);
                                }
                                break;
                            }
                        }
                    }
                }
                else{
                    System.err.println("Error: Valor passado como parâmetro inválido. Type mismatch("+node.getLine()+','+node.getColumn()+')' 
                    + ". Cannot convert " + tipo + " to " + params.get(i).getType().getStringType());
                    System.exit(0);   
                }

            }

            

            //System.out.println(value);
        }
        //==============================================================================================================================================

        //Antes de executar os comandos, usa a função chamada como atual
        actualFuncName = functionName;

        //Precisamos setar o actualReturn para null, para não acabar usando return de outras funções
        actualReturn = null;

        //executa os comandos da função chamada
        BlockNode bn = new BlockNode(function.getLine(), function.getColumn(), function.getCmds());
        bn.accept(this);

        //Vamos procurar um return node nos cmds da função:
        ReturnNode returnNode = null;

        if(actualReturn != null){ //existia um return acessivel dentro dos cmds
            returnNode = actualReturn; //atribuimos o return encontrado a nossa variavel para trabalharmos em cima dele
        }

        if(returnNode != null){
            if(returnTypes.size() == 0){ //achou um returnNode em função sem retorno
                System.err.println("Line: " + node.getLine() + ':' + node.getColumn() + " error: void method cannot return a value");
                System.exit(0);
            }
            //Verifica se o returnNode esta retornando a mesma quantidade de valores que a função se dispoe a retornar
            if(returnNode.getExps().size() != returnTypes.size()){
                System.err.println("Line: " + returnNode.getLine() + ':' + returnNode.getColumn() + " número de retornos diferente do número de retornos de " 
                + functionName);
                System.exit(0);
            }
        }
        else{
            if(returnTypes.size() > 0){ //não achou um returnNode em função que se propoe a retornar
                System.err.println("Line: " + node.getLine() + ':' + node.getColumn() + " error: missing return statement");
                System.exit(0);
            }
        }
                
        System.out.println("EXPS de return: " + returnNode.getExps());

        //Garantir que os valores retornados seguem os tipos propostos:
        int indexDeRetorno = 0;
        for(ExpNode exp : returnNode.getExps()){ //para cada retorno do returnNode
            Object value = exp.accept(this); //resolve a exp
            String tipo = "";
            if(value instanceof LValueNode){
                LValueNode lval = (LValueNode) value;

                tipo = acessaVariavelType(lval.getId(), lval.getIndexes(), returnNode, lval.getField());
            }
            else if(value instanceof NewNode){
                NewNode newNode = (NewNode) value;
                tipo = newNode.getType().getStringType();
            }
            else{ //caso não seja lvalue ou newNode, o tipo pode apenas ser primitivo
                if(value instanceof Integer){
                    tipo = "Int";
                }else if(value instanceof Float || value instanceof Double){
                    tipo = "Float";
                }else if(value instanceof Boolean){
                    tipo = "Bool";
                }else if(value instanceof Character){
                    tipo = "Char";
                }
            }
            
            //verifica se o tipo é igual ao tipo de retorno proposto:
            if(!tipo.equals(returnTypes.get(indexDeRetorno).getStringType())){
                System.err.println("Line " + node.getLine() + ":" + node.getColumn() + 
                " error: incompatible types: " + tipo + " cannot be converted to " + returnTypes.get(indexDeRetorno).getStringType());
                System.exit(0);
            }

            indexDeRetorno++;
        }

        //Ja verificamos a integridade do return, então:
        Object retornoDaExp = returnNode.getExps().get(index).accept(this);

        System.out.println("retorno usado " + retornoDaExp);

        if(retornoDaExp instanceof LValueNode){
            LValueNode lval = (LValueNode) retornoDaExp;

            retornoDaExp = acessaVariavel(lval.getId(), lval.getIndexes(), node, lval.getField());
            
        }

        if(retornoDaExp instanceof Integer){
            actualType = "Int";
        }else if(retornoDaExp instanceof Float || retornoDaExp instanceof Double){
            actualType = "Float";
        }else if(retornoDaExp instanceof Boolean){
            actualType = "Bool";
        }else if(retornoDaExp instanceof Character){
            actualType = "Char";
        }

        //Após executar os comandos, voltar para a função que chamou
        actualFuncName = functioncalling;

        System.out.println("WTF: " + retornoDaExp);

        return retornoDaExp;
    }

    @Override
    public Object visit(DefNode node) {

        //System.out.println("DefNode");

        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public Object visit(ExpNode node) {

        //System.out.println("ExpNode");
        return null;
    }

    @Override
    public Object visit(BlockNode node) {

        //System.out.println("BlockNode");

        //seta o return Atual para null
        actualReturn = null;

        // Processar bloco de comandos
        for (CmdNode cmd : node.getCmds()) {
            if(cmd instanceof ReturnNode){
                cmd.accept(this);
                break; //interrompe a execução dos comandos
            }

            if(cmd instanceof IfNode){ //caso encontre um If, cria um novo scopo
                actualScope++; //usa o novo scopo como atual
                cmd.accept(this);
                actualScope--; //ao sair do if volta ao scope anterior
                if(actualReturn != null){
                    //encontrou um return, precisa dar break
                    break;
                }
            }
            else if(cmd instanceof IterateNode){ //mesma logica do If com o iterate
                actualScope++; 
                cmd.accept(this);
                actualScope--;
                if(actualReturn != null){
                    //encontrou um return, precisa dar break
                    break;
                }
            }
            else
                cmd.accept(this);
        }
        return null;
    }

    // Codigo para ler input do usuario, Auxiliar para o Print
    private Object readInput() {
        Scanner scanner = new Scanner(System.in);
        String myInput = scanner.nextLine();
        try{
            int a = Integer.parseInt(myInput.trim());
            return a;
        }
        catch(Exception ex)
        {
            try{
                Float f = Float.parseFloat(myInput.trim());
                return f;
            }
            catch(Exception e)
            {
                try{
                    if(myInput.equals("true") || myInput.equals("false")){
                        Boolean b = Boolean.parseBoolean(myInput);
                        return b;
                    }                 
                } catch(Exception i)
                {
                    Character c = myInput.charAt(0);
                    return c;
                }
            }
        }

        return myInput;
    }

    public void printEnv() {

        Boolean hasMain = functions.containsKey("main");


        if(hasMain){
            actualFuncName = "main";
            FunNode mainFunction = functions.get("main");
            BlockNode bn = new BlockNode(mainFunction.getLine(), mainFunction.getColumn(), mainFunction.getCmds());
            bn.accept(this);
        }
        if(!hasMain){ //caso não haja função main, throw error
            System.err.println("Error: Ausência da função main(), por favor implemente uma função main()");
            System.exit(0);
        }

        System.out.println("\n\n === Estado Atual do Interpretador ===");
        
        //Exibir datas definidas
        System.out.println("Datas:");
        for(Map.Entry<String, DataDefinition> entry : dataTypes.entrySet()){
            System.out.printf("| %-10s | %-10s | %-10s | %n",  entry.getKey(), "Variable", "Type");
            for (Map.Entry<String, String> field : entry.getValue().getFields().entrySet()) {
                System.out.printf("| %-10s | %-10s | %-10s | %n",  "", field.getKey(), field.getValue());
            }                 
        }
        
        System.out.println("");

        //Exibição do novo HashMap com SuperMap
        System.out.println("Mapeamento:");
        for(Map.Entry<String, ArrayList<SuperMap>> entry : superMap.entrySet()){
            System.out.println("");
            System.out.printf("| %-30s | %-30s | %-30s | %-30s | %-30s | %n",  entry.getKey(), "Variable", "Type", "Value", "Scope");
            for(SuperMap map : entry.getValue()){
                String values = "";
                if(map.getValue().getSuperValues().isEmpty()){
                    values = " " + map.getValue().getValue();
                }else{
                    values = "{" + LerSuperValue(map.getValue()); 
                    values = values + '}';
                }              
                System.out.printf("| %-30s | %-30s | %-30s | %-30s | %-30s | %n",  "", map.getID(), map.getType(), values, map.getScope());
            }
        }

        //Exibir funções definidas
        System.out.println("\nFunções Definidas:");
        for (Map.Entry<String, FunNode> entry : functions.entrySet()) {
            String params = "";

            for(int i = 0; i < entry.getValue().getParams().size(); i++){
                SuperNode sup = entry.getValue().getParams().get(i).getType();
                if(sup instanceof BTypeNode){
                    BTypeNode btype = (BTypeNode) sup;
                    params += entry.getValue().getParams().get(i).getId() + " :: " + btype.getBtype();
                }
                else{
                    TypeNode type = entry.getValue().getParams().get(i).getType();
                    
                    params += entry.getValue().getParams().get(i).getId() + " :: " + type.getStringType();
                }

                if(i < entry.getValue().getParams().size() - 1)
                    params += ", ";
            }

            System.out.println(entry.getKey() + '(' + params + ')');
        }
        System.out.println("=====================================");
    }
    public String LerSuperValue(SuperValue supe){ //chamado no primeiro indice
        String value = "";
        for (SuperValue sv : supe.getSuperValues()) {
            value = value + sv.getValue() + ", ";
            value = value + LerSuperValue(sv);
        }
        return value;
    }
}
