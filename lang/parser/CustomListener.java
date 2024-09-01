/*Trabalho 2 - Teoria dos Compiladores (DCC045) 
 * Alunos: Gabriel Pereira Senra Soares - 201935006
 *         Lucas Castro Carvalho - 201835015
*/

package lang.parser;

import lang.ast.SuperNode;
import lang.ast.SuperNodeImpl;
import lang.parser.LangBaseListener;
import lang.parser.LangParser;

public class CustomListener extends LangBaseListener{
    private SuperNodeImpl result;

    @Override
    public void enterProgram(LangParser.ProgramContext ctx) {
        // Criar uma nova instância de SuperNodeImpl
        result = new SuperNodeImpl(0, 0);

        // Preencher os dados do SuperNodeImpl a partir do ctx
        result.setLine(ctx.getStart().getLine());
        result.setColumn(ctx.getStart().getCharPositionInLine());
    }

    public SuperNode getResult() {
        return result;
    }

}
