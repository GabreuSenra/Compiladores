/*Trabalho 2 - Teoria dos Compiladores (DCC045) 
 * Alunos: Gabriel Pereira Senra Soares - 201935006
 *         Lucas Castro Carvalho - 201835015
*/

package lang.parser;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.antlr.v4.gui.SystemFontMetrics;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import lang.ast.*;
import lang.parser.LangBaseVisitor;
import lang.parser.LangParser.*;

public class CustomVisitor extends LangBaseVisitor<SuperNode> {

    @Override
    public SuperNode visitProgram(ProgramContext ctx) { 
        if (ctx == null) {
            return null; // Retorna null ou um nó padrão se o tipo não estiver presente
        }

        //System.out.println("(DEPURAÇÃO) Visiting prog: " + ctx.getText());

        List<SuperNode> programs = new ArrayList<>();
        
        /// Visita todos os nós definidos no programa
        for (DefContext defContext : ctx.def()) {
            SuperNode defNode = visitDef(defContext);
            if (defNode != null) {
                programs.add(defNode);
            }
        }
        return new ProgramNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), programs);
    }

    @Override
    public SuperNode visitDef(DefContext ctx) {

        //System.out.println("(DEPURAÇÃO) Visiting def: " + ctx.getText());


        //Visita todos os Defs
        if (ctx.data() != null) {
            return visit(ctx.data()); //visita data
        } else if (ctx.fun() != null) {
            return visit(ctx.fun()); //visita fun
        } else {
            return null;
        }
    }

    @Override
    public SuperNode visitData(DataContext ctx) {
        if (ctx == null) {
            return null; // Retorna null ou um nó padrão se o tipo não estiver presente
        }

        //visita todos os Decl de data
        String id = ctx.ID().getText();
        List<DeclNode> declarations = new ArrayList<>();
        for (DeclContext declCtx : ctx.decl()) {
            declarations.add((DeclNode) visit(declCtx));
        }
        //Cria o nó data
        return new DataNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), id, declarations);
    }

    @Override
    public SuperNode visitDecl(DeclContext ctx) {
        if (ctx == null) {
            return null; // Retorna null ou um nó padrão se o tipo não estiver presente
        }

        //atribui os valores de Decl
        String id = ctx.ID().getText();
        TypeNode type = (TypeNode) visit(ctx.type()); //visita typenode

        //cria o DeclNode com os valores
        return new DeclNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), id, type);
    }

    @Override
    public SuperNode visitFun(FunContext ctx) {
        if (ctx == null) {
            return null; // Retorna null ou um nó padrão se o tipo não estiver presente
        }


        //System.out.println("(DEPURAÇÃO) Visiting fun: " + ctx.getText());


        String id = ctx.ID().getText(); //coleta o ID da função
        List<ParamNode> params = new ArrayList<>(); //cria um array para os parametros
        if (ctx.params() != null) {
            params = ((ParamsNode) visit(ctx.params())).getParams(); //visita os parametros e atribui na lista
        }

        List<TypeNode> returnTypes = new ArrayList<>(); //cria um array para returntypes
        if (ctx.type() != null) {
            for (TypeContext typeCtx : ctx.type()) {
                returnTypes.add((TypeNode) visit(typeCtx)); //visita os types
            }
        }

        List<CmdNode> cmds = new ArrayList<>(); //cria um array para os cmds
        for (CmdContext cmdCtx : ctx.cmd()) {
            //System.out.println(cmdCtx.getText());
            cmds.add((CmdNode) visit(cmdCtx)); //visita os cmds
        }

        //por fim, cria o FunNode com os valores recebidos
        return new FunNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), id, params, returnTypes, cmds);
    }

    @Override
    public SuperNode visitParams(ParamsContext ctx) {
        if (ctx == null) {
            return null; // Retorna null ou um nó padrão se o tipo não estiver presente
        }

        List<ParamNode> params = new ArrayList<>(); //array de parametros
        for (ParamContext paramCtx : ctx.param()) {
            params.add((ParamNode) visit(paramCtx)); //visita cada param e atribui a lista
        }

        //cria o paramNode com os valores recebidos
        return new ParamsNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), params);
    }

    @Override
    public SuperNode visitParam(ParamContext ctx) {
        if (ctx == null) {
            return null; // Retorna null ou um nó padrão se o tipo não estiver presente
        }

        String id = ctx.ID().getText(); //pega o ID
        TypeNode type = (TypeNode) visit(ctx.type()); //visita o type

        //cria o ParamNode com os valores recebidos
        return new ParamNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), id, type);
    }

    @Override
    public SuperNode visitType(LangParser.TypeContext ctx) {
        if (ctx == null) {
            return null;
        }

        if (ctx.btype() != null) {
            // Usa visitBtype para lidar com tipos básicos e visita Btype
            return visitBtype(ctx.btype());
        }
        
        if (ctx.type() != null && ctx.L_BRACKET() != null && ctx.R_BRACKET() != null) {
            SuperNode innerType = visit(ctx.type()); //visita type recursivamente

            //cria outro TypeNode com os valores recebidos recursivamente de innerType
            return new TypeNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), innerType);
        }

        return null; 
    }

    @Override
    public SuperNode visitBtype(LangParser.BtypeContext ctx) {
        if (ctx == null) {
            return null;
        }

        String btypeText = ctx.getText(); //recebe o Tipo: Int, Float, Char, Bool, ID

        //cria o nó BType node com o tipo recebido
        return new BTypeNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), btypeText);
    }

    public SuperNode visitCmd(LangParser.CmdContext ctx) {
        {
            if (ctx == null) {
                return null; // Retorna null ou um nó padrão se o tipo não estiver presente
            }

            //System.out.println("(DEPURAÇÃO)  Visiting command: " + ctx.getText());


            // Comando de bloco
            if (ctx.L_BRACE() != null) {

                //System.out.println("(DEPURAÇÃO)  Visiting: L_BRACE ");

                List<CmdNode> cmds = ctx.cmd().stream()
                    .map(cmdCtx -> (CmdNode) visit(cmdCtx)) //visita cada Cmd
                    .collect(Collectors.toList());

                //cria um nó auxiliar BlockNode para lidar com cada Cmd
                return new BlockNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), cmds);
            }
    
            // Comando if-else
            if (ctx.IF() != null) {

                //System.out.println("(DEPURAÇÃO)  Visiting: IF_ELSE ");

                ExpNode condition = null;
                CmdNode thenCmd = null;
                CmdNode elseCmd = null;

                if(ctx.exp(0) != null){
                    condition = (ExpNode) visit(ctx.exp(0)); //atribui o return da visita ao ExpNode a conditions
                }
                if(ctx.cmd(0) != null){
                    thenCmd = (CmdNode) visit(ctx.cmd(0)); //atribui o return da visita ao CmDNode a thenCmd
                }
                if(ctx.cmd(1) != null){
                    elseCmd = ctx.cmd(1) != null ? (CmdNode) visit(ctx.cmd(1)) : null; //se exisitr else, atribui o return da visita ao CmDNode a elseCmd
                }

                if(condition == null || thenCmd == null){ //Para o programa se não tiver condição no if ou não tiver cmd
                    System.err.println("If Statement sem Conditition ou CommandBlock");
                    return null;
                }

                //cria o nó auxiliar IfNode
                return new IfNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), condition, thenCmd, elseCmd);
            }
    
            // Comando de iteração
            if (ctx.ITERATE() != null) {

                //System.out.println("(DEPURAÇÃO)  Visiting: ITERATE ");

                ExpNode condition = (ExpNode) visit(ctx.exp(0)); //atribui o return da visita ao ExpNode a condition
                CmdNode body = (CmdNode) visit(ctx.cmd(0)); //atribui o return da visita ao Cmd a body

                //cria o nó auxiliar IterateNode
                return new IterateNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), condition, body);
            }
    
            // Comando de leitura
            if (ctx.READ() != null) {

                //System.out.println("(DEPURAÇÃO)  Visiting: READ ");

                LValueNode lvalue = (LValueNode) visit(ctx.lvalue(0)); //atribui o return da visita ao LValue a lvalue

                //cria o nó auxiliar ReadNode
                return new ReadNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), lvalue);
            }
    
            // Comando de impressão
            if (ctx.PRINT() != null) {

                //System.out.println("(DEPURAÇÃO)  Visiting: PRINT ");

                ExpNode exp = (ExpNode) visit(ctx.exp(0));//atribui o return da visita ao ExpNode a exp

                //cria o nó auxiliar PrintNode
                return new PrintNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), exp);
            }
    
            // Comando de retorno
            if (ctx.RETURN() != null) {

                //System.out.println("(DEPURAÇÃO)  Visiting: RETURN ");

                List<ExpNode> exps = new ArrayList<>();
                if (ctx.exp() != null) {  // Verifica se há alguma expressão
                    for (LangParser.ExpContext expCtx : ctx.exp()) {
                        if (expCtx != null) {  // Verifica se cada expressão não é null           
                            exps.add((ExpNode) visitExp(expCtx));
                        }
                    }
                }

                /* 
                for(int i = 0; i < exps.size(); i++){
                    System.out.println(exps.get(i).getExpType());
                }*/

                //cria o nó auxiliar ReturnNode
                return new ReturnNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), exps);
            }
    
            // Comando de atribuição
            if (ctx.EQ() != null) {

                //System.out.println("(DEPURAÇÃO)  Visiting: EQ ");

                //System.out.println(ctx.lvalue(0).getText());
                //System.out.println(ctx.exp(0).getText());


                LValueNode lvalue = (LValueNode) visit(ctx.lvalue(0)); //atribui o return da visita ao Lvalue a lvalue

                //System.out.println("LVALUE SETADO");

                ExpNode exp = (ExpNode) visit(ctx.exp(0)); //atribui o return da visita ao ExpNode a exp

                //System.out.println("EXPNODE SETADO");

                //cria o nó auxiliar AssignNode
                return new AssignNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), lvalue, exp);
            }
    
            // Comando de chamada de função
            if (ctx.ID() != null) {

                //System.out.println("(DEPURAÇÃO)  Visiting: CALL ");

                String functionName = ctx.ID().getText(); //pega o nome da função
                List<ExpNode> args = ctx.exps() != null ?  //se tiver argumentos
                    ctx.exps().exp().stream().map(expCtx -> (ExpNode) visit(expCtx)).collect(Collectors.toList()) //atribui o return da visita ao ExpNode a lista de argumentos
                    : List.of();
                List<LValueNode> returnValues = ctx.lvalue().stream() //mesma logica dos argumentos agora para retornos
                    .map(lvalueCtx -> (LValueNode) visit(lvalueCtx))
                    .collect(Collectors.toList());

                //cria o nó auxiliar CallNode para lidar com chamadas de função
                return new CallNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), functionName, args, returnValues);
            }
    
            return null; // Caso nenhum dos ifs seja atendido, retorna null
        }
    }

    @Override
    public SuperNode visitExp(ExpContext ctx) {

        //System.out.println("ENTROU EXP");
        if (ctx == null) {
            return null; // Retorna null ou um nó padrão se o tipo não estiver presente
        }

        //System.out.println(ctx.getText());


        if (ctx.exp().size() == 2) {

            //System.out.println("FAZENDO OPERAÇÃO");

            ExpNode left = (ExpNode) visit(ctx.exp(0)); //atribui o return da visita ao ExpNode a left
            ExpNode right = (ExpNode) visit(ctx.exp(1)); //atribui o return da visita ao ExpNode a right
            String op = ctx.getChild(1).getText(); // Operador

            //cria o nó auxiliar BinOpNode para tratar operações binarias
            return new BinOpNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), op, left, right);
        } 
        
        if (ctx.NOT() != null || ctx.MINUS() != null) {

            //System.out.println("NOT / MINUS");

            String op = ctx.NOT() != null ? ctx.NOT().getText() : ctx.MINUS().getText(); //verifica se ha presença dos tokens NOT e MINUS
            ExpNode operand = (ExpNode) visit(ctx.exp(0)); //atribui o return da visita ao ExpNode a operand

            //cria o nó auxiliar UnOpNode para tratar operações 
            return new UnOpNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), op, operand);
        }
        if (ctx.BOOL_TRUE() != null || ctx.BOOL_FALSE() != null) {

            //System.out.println("BOOL");

            boolean value = ctx.BOOL_TRUE() != null;

            //cria o nó auxiliar BoolNode para tratar booleanas 
            return new BoolNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), value);
        }

        if (ctx.NULL() != null) {

            //System.out.println("NULL");


            //cria o nó auxiliar NullNode para tratar nulls 
            return new NullNode(ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }

        if (ctx.INT() != null) {

            //System.out.println("INT");

            int value = Integer.parseInt(ctx.INT().getText());

            //cria o nó auxiliar IntNode para tratar inteiros 
            return new IntNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), value);
        }

        if (ctx.FLOAT() != null) {

            //System.out.println("FLOAT");

            float value = Float.parseFloat(ctx.FLOAT().getText());

            //cria o nó auxiliar FloatNode para tratar numeros flutuantes
            return new FloatNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), value);
        }

        if (ctx.CHAR() != null) {

            //System.out.println("CHAR");

            char value = ctx.CHAR().getText().charAt(1); // Remover as aspas

            //cria o nó auxiliar CharNode para tratar Characters
            return new CharNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), value);
        }

        if (ctx.lvalue() != null) {

            //System.out.println("LVALUE");

            return visit(ctx.lvalue()); //visita Lvalue
        }

        if (ctx.ID() == null && ctx.LP() != null && ctx.RP() != null) {

            //System.out.println("LP ou RP");

            //System.out.println(ctx.exp(0));

            return visit(ctx.exp(0)); //visita exp
        }

        
        if (ctx.NEW() != null) {

            //System.out.println("NEW");

            TypeNode type = (TypeNode) visit(ctx.type()); //visita Type
            List<ExpNode> exps = new ArrayList<>();
            for(ExpContext exp : ctx.exp()){
                if(exp != null) { // Verifica se a expressão opcional existe
                    exps.add((ExpNode) visitExp(exp));
                }
            }

            //cria o nó auxiliar NewNode para tratar criação de objetos
            return new NewNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), type, exps);
        }
        
        if (ctx.ID() != null) {

            //System.out.println("FUNCTION CALL");

            String functionName = ctx.ID().getText(); //recebe o nome da função
            List<ExpNode> exps = new ArrayList<>();
            if (ctx.exps() != null) {
                exps = ((ExpsNode) visit(ctx.exps())).getExpressions(); //visita os exps
            }
            ExpNode index = ctx.exp().size() > 0 ? (ExpNode) visit(ctx.exp(0)) : null; //determina o index caso exista

            //cria o nó auxiliar FuncCallNode para tratar chamdas de função com index
            return new FuncCallNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), functionName, exps, index);
        }

        return null; 
    }

    @Override
    public SuperNode visitLvalue(LvalueContext ctx) {

        //System.out.println("LVALUE OPERATIONS");

        //System.out.println( "Contexto:" + ctx.getText());

        String id = "";
        ExpNode index = null;
        List<String> fields = new ArrayList<>();

        if(ctx.ID() != null){
            // Extrair o ID principal (raiz do lvalue)

            id = ctx.ID().getText(); // Raiz do lvalue

            //System.out.println("ID: " + ctx.ID().getText());

            //Retona ID
            return new LValueNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), id, null, index, fields);
        }
        if(ctx.ID() == null && ctx.L_BRACKET() != null){
            // Se houver acesso a array

            LValueNode lvalue = (LValueNode) visit(ctx.lvalue());
            index = (ExpNode) visit(ctx.exp());

            //System.out.println(ctx.lvalue().getText() + '[' + ctx.exp().getText() + ']');

            // Retornar um novo LValueNode com os dados extraídos: lvalue '[' exp ']'
            return new LValueNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), id, lvalue, index, null);
        }
        if(ctx.DOT() != null){
            // Se houver acesso a campo (com ponto)
            LValueNode lvalue = (LValueNode) visit(ctx.lvalue());
            String field = ctx.ID().getText(); // Campo após o ponto
            fields.add(field);

            // Retornar um novo LValueNode com os dados extraídos: lvalue '.' ID 
            return new LValueNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), id, lvalue, null, fields);
        }

        return null; 
    }

    @Override
    public SuperNode visitExps(ExpsContext ctx) {
        if (ctx == null) {
            return null; // Retorna null ou um nó padrão se o tipo não estiver presente
        }


        List<ExpNode> expressions = new ArrayList<>();
        for (ExpContext expCtx : ctx.exp()) {
            expressions.add((ExpNode) visit(expCtx)); //visita cada exp e atribui os retornos a lista
        }

        //cria o nó ExpsNode com os valores adquiridos
        return new ExpsNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), expressions);
    }
}