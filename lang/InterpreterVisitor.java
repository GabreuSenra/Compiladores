package lang;

import lang.ast.*;
import lang.parser.LangBaseVisitor;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.misc.EscapeSequenceParsing.Result.Type;
import org.antlr.v4.parse.GrammarTreeVisitor.outerAlternative_return;

import java.util.Scanner;

public class InterpreterVisitor extends LangBaseVisitor<Object> implements Visitor<Object> {

    private Map<String, Object> variables = new HashMap<>(); //mantem registro de todas as variaveis
    private Map<String, FunNode> functions = new HashMap<>(); //mantem registro de todas as funções
    private Stack<Map<String, Object>> scopes = new Stack<>(); //pilha de escopos 
    public int actualScope = -1;

    private String variableIdAux = ""; //auxilia na criação de variaveis parâmetros, ex: f(x :: Int)

    public InterpreterVisitor() { 
        System.out.println("=====================================");
        System.out.println("\n\nInicializando o Interpretador");
        System.out.println("Saída do CMD: \n");
        variables = new HashMap<>();
        functions = new HashMap<>();
        scopes = new Stack<>();
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
                throw new IllegalArgumentException("Unexpected node type: " + def.getClass().getName());
            }
        }
        return null;
    }
    
    @Override
    public Object visit(DataNode node) {

        //System.out.println("DATA NODE");

        scopes.push(new HashMap<String, Object>()); //Cria um novo scopo com um HashMap vazio
        actualScope++; 

        String id = node.getId();
        List<DeclNode> declarations = new ArrayList<>();
        //System.out.println(node.getDeclarations().size());
        for(DeclNode declNode : node.getDeclarations()){
            declarations.add( (DeclNode) declNode.accept(this));
        }

        return new DataNode(node.getLine(), node.getColumn(), id, declarations);
    }

    @Override
    public Object visit(DeclNode node) {

        //System.out.println("DECL NODE");

        String id = node.getId();
        //System.out.println(node.getId());

        setAux(id);

        TypeNode type = node.getType();
        //System.out.println(node.getType());
        
        type.accept(this);

        return new DeclNode(node.getLine(), node.getColumn(), id, type);
    }

    @Override
    public Object visit(FunNode node) {
        // Registra a função no mapa de funções usando o nome da função como chave
        //System.out.println("Registrando função: " + node.getId());
        functions.put(node.getId(), node);

        scopes.push(new HashMap<String, Object>()); //Cria um novo scopo com um HashMap vazio
        actualScope++; 

        ParamsNode pn = new ParamsNode(node.getLine(), node.getColumn(), node.getParams());
        pn.accept(this);

        BlockNode bn = new BlockNode(node.getLine(), node.getColumn(), node.getCmds());
        bn.accept(this);

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
        TypeNode type = node.getType();
        type.accept(this);

        return new ParamNode(node.getLine(), node.getColumn(), id, type);
    }

    @Override
    public Object visit(TypeNode node) {
        //System.out.println("TYPE NODE");

        if((BTypeNode)node.getType() != null){ //se for um BType
            return (BTypeNode)node.getType().accept(this); //visita BType
        }
        else if((TypeNode)node.getType() != null){//se for outro Type, ex: type []
            SuperNode innerType = node.getType(); 
            return new TypeNode(node.getLine(), node.getColumn(), innerType); //retorna o type para recursão
        }
        return null;
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

        //System.out.println("BTYPE NODE");

        //System.out.println(node.getBtype());

        String btypeText = node.getBtype();

        //System.out.println("Registrando variável" + getAux());

        //Registra variavel no Scopo Global
        Object value = initializeDefaultValue(node.getBtype());
        variables.put(getAux(), value);

        //registra variavel no scopo atual       
        Map<String, Object> currentScope = scopes.get(actualScope);
        currentScope.put(getAux(), value);


        // Apenas retorna o tipo base como string
        return new BTypeNode(node.getLine(), node.getColumn(), btypeText);
    }


    // Método auxiliar para inicializar um valor padrão com base no tipo de dado
    private Object initializeDefaultValue(String type) {
        switch (type) {
            case "Int":
                return 0;
            case "Float":
                return 0.0;
            case "Char":
                return "";
            case "Bool":
                return false;
            default:
                return null; // Para tipos desconhecidos ou tipos de objeto
        }
    }


    @Override
    public Object visit(CmdNode node) {
        //System.out.println("CMD NODE");

        return null;
    }

    @Override
    public Object visit(IfNode node) {

        //System.out.println("IF NODE");

        Boolean condition = (Boolean) node.getCondition().accept(this);
        if (condition) {
            return node.getThenCmd().accept(this);
        } else if (node.getElseCmd() != null) {
            return node.getElseCmd().accept(this);
        }
        return null;
    }

    @Override
    public Object visit(IterateNode node) {

        //System.out.println("ITERATE NODE");

        Integer times = (Integer) node.getCondition().accept(this);
        for (int i = 0; i < times; i++) {
            node.getBody().accept(this);
        }
        return null;
    }

    @Override
    public Object visit(ReadNode node) {

        //System.out.println("READ NODE");

        Object value = readInput(); //Lê a entrada do usuario
        variables.put(node.getLvalue().getId(), value); //registra no scopo global

        //registra no scopo atual
        Map<String, Object> currentScope = scopes.get(actualScope);
        currentScope.put(node.getLvalue().getId(), value);
        return null;
    }

    @Override
    public Object visit(PrintNode node) {

        //System.out.println("PRINT NODE");

        Object value = node.getExp().accept(this);
        System.out.println(value);
        return null;
    }

    @Override
    public Object visit(ReturnNode node) {
        //System.out.println("RETORNANDO");

        // Apenas retorna a expressão de retorno, captura será feita pelo chamador
        if (node.getExps().size() == 1) {
            return node.getExps().get(0).accept(this);
        }

        // Caso tenha múltiplos retornos, cria uma lista para armazenar os valores
        List<Object> returnValues = new ArrayList<>();

        // Itera sobre todas as expressões de retorno e as avalia
        for (ExpNode exp : node.getExps()) {
            Object value = exp.accept(this); // Avalia a expressão e captura o valor
            returnValues.add(value); // Adiciona o valor à lista de retornos
        }

        //retorna a lista de valores
        return returnValues;
    }

    @Override
    public Object visit(AssignNode node) {  

        //System.out.println("Registrando variável" + node.getLvalue().getId());

        if(node.getExp() instanceof NewNode){
            setAux(node.getLvalue().getId());
            node.getExp().accept(this);
        }
        else{
            //registra no scopo global
            Object value = node.getExp().accept(this);
            variables.put(node.getLvalue().getId(), value);

            //registra no scopo atual
            Map<String, Object> currentScope = scopes.get(actualScope);
            currentScope.put(node.getLvalue().getId(), value);
        }

        return null;
    }

    @Override
    public Object visit(CallNode node) {

        //System.out.println("CALLNODE");

        String functionName = node.getFunctionName();
        FunNode function = functions.get(functionName);
        if (function == null) {
            throw new RuntimeException("Função não definida: " + functionName);
        }

        List<ExpNode> args = node.getArgs();
        List<ParamNode> params = function.getParams();
        List<LValueNode> returnValues = node.getReturnValues();

        if (args.size() != params.size()) {
            throw new RuntimeException("Número de argumentos não corresponde ao número de parâmetros");
        }

        //itera por cada parametro e atualiza o valor que o parametro retem
        for (int i = 0; i < args.size(); i++) {
            String paramName = params.get(i).getId();
            //System.out.println(paramName);
            Object paramValue = args.get(i).accept(this);
            //System.out.println(paramValue);

            //Acessa a variavel que foi inicializada na criação da função e atribui o valor passado como parâmetro (Escopo Global)
            variables.put(paramName, paramValue);

            //registra no scopo atual
            Map<String, Object> currentScope = scopes.get(actualScope - 1);
            currentScope.put(paramName, paramValue);
        }

        List<CmdNode> cmds = function.getCmds();
        Object result = null;
        for (CmdNode cmd : cmds) {
            result = cmd.accept(this); // Executa cada comando
        }

        //cria uma lista com o valor dos retornos
        List<Object> returnValuesList = new ArrayList<>();
        if (result instanceof List<?>) {
            returnValuesList = (List<Object>) result;
        } else {
            returnValuesList.add(result);
        }

        // Verificar se o número de valores de retorno corresponde ao esperado
        if (returnValues.size() != returnValuesList.size()) {
            throw new RuntimeException("Número de valores de retorno não corresponde ao número esperado");
        }

        // Atribuir os valores de retorno às variáveis
        for (int i = 0; i < returnValues.size(); i++) {
            LValueNode lvalue = returnValues.get(i);
            Object returnValue = returnValuesList.get(i);

            // Armazenar o valor de retorno na variável correspondente
            variables.put(lvalue.getId(), returnValue);

            // Armazena no scopo atual
            Map<String, Object> currentScope = scopes.get(actualScope);
            currentScope.put(lvalue.getId(), returnValue);
        }

        return result;
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
        //System.out.println("LValue");

        // Assume que o LValue é simplesmente o nome da variável
        String varName = node.getId();

        
        if (scopes.isEmpty()) {
            return variables.get(varName);
        } else {
            return scopes.peek().getOrDefault(varName, variables.get(varName));
        }
    }

    @Override
    public Object visit(BinOpNode node) {

        //System.out.println("Binary Operation");
        Object left = node.getLeft().accept(this);
        Object right = node.getRight().accept(this);
        switch (node.getOp()) { // exp + exp
            case "+":
                if(left instanceof Integer && right instanceof Integer)
                    return (Integer) left + (Integer) right;
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
                throw new RuntimeException("Operador desconhecido: " + node.getOp());
        }
    }

    @Override
    public Object visit(UnOpNode node) {

        //System.out.println("Unitary Operation");

        Object value = node.getExpressions().accept(this);
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
                throw new RuntimeException("Operador desconhecido: " + node.getOp());
        }
    }

    @Override
    public Object visit(BoolNode node) {

        //System.out.println("BoolNode");

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

        return node.getValue();
    }

    @Override
    public Object visit(FloatNode node) {

        //System.out.println("FloatNode");

        return node.getValue();
    }

    @Override
    public Object visit(CharNode node) {

        //System.out.println("CharNode");

        return node.getValue();
    }

    @Override
    public Object visit(NewNode node) {

        System.out.println("NewNode");

        TypeNode type = node.getType();
        type.accept(this);

        for(ExpNode exp : node.getExps()){
            if(exp != null){
                exp.accept(this);
            }
        }

        return null;
    }

    @Override
    public Object visit(FuncCallNode node) {

        //System.out.println("FuncCallNode");

        String functionName = node.getFunctionName();
        FunNode function = functions.get(functionName);
        if (function == null) {
            throw new RuntimeException("Função não definida: " + functionName);
        }

        List<ExpNode> args = node.getArgs();
        List<ParamNode> params = function.getParams();

        if (args.size() != params.size()) {
            throw new RuntimeException("Número de argumentos não corresponde ao número de parâmetros");
        }

        // Cria novo escopo para a chamada de função
        Map<String, Object> newScope = new HashMap<>();
        for (int i = 0; i < args.size(); i++) {
            String paramName = params.get(i).getId();
            Object paramValue = args.get(i).accept(this);
            newScope.put(paramName, paramValue);
        }
        scopes.push(newScope);

        List<CmdNode> cmds = function.getCmds();
        Object result = null;
        for (CmdNode cmd : cmds) {
            result = cmd.accept(this); // Executa cada comando
        }

        return result;
    }

    @Override
    public Object visit(DefNode node) {

        //System.out.println("DefNode");

        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public Object visit(ExpNode node) {

        System.out.println("ExpNode");



        return null;
    }

    @Override
    public Object visit(BlockNode node) {

        //System.out.println("BlockNode");

        // Processar bloco de comandos
        for (CmdNode cmd : node.getCmds()) {
            if(cmd instanceof IfNode){ //caso encontre um If, cria um novo scopo
                scopes.push(new HashMap<String, Object>()); //Cria um novo scopo com um HashMap vazio
                actualScope++; //usa o novo scopo como atual
                cmd.accept(this);
                actualScope--; //ao sair do if volta ao scope anterior
            }
            else if(cmd instanceof IterateNode){ //mesma logica do If com o iterate
                scopes.push(new HashMap<String, Object>()); //Cria um novo scopo com um HashMap vazio
                actualScope++; 
                cmd.accept(this);
                actualScope--;
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
        return myInput;
    }

    public void printEnv() {
        System.out.println("\n\n === Estado Atual do Interpretador ===");
    
        // Exibir o escopo global
        System.out.println("Escopo Global:");
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        // Exibir escopos aninhados (se houver)
        if (!scopes.isEmpty()) {
            System.out.println("\nEscopos Aninhados:");
            for (int i = scopes.size() - 1; i >= 0; i--) {
                Map<String, Object> scope = scopes.get(i);
                System.out.println("Escopo #" + (i + 1) + ":");
                for (Map.Entry<String, Object> entry : scope.entrySet()) {
                    System.out.println("  " + entry.getKey() + " = " + entry.getValue());
                }
            }
        }

        // Exibir funções definidas
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
                    BTypeNode btype = (BTypeNode) type.getType();
                    params += entry.getValue().getParams().get(i).getId() + " :: " + btype.getBtype() + "[]";
                }

                if(i < entry.getValue().getParams().size() - 1)
                    params += ", ";
            }

            System.out.println(entry.getKey() + '(' + params + ')');
        }
        System.out.println("=====================================");
    }
}
