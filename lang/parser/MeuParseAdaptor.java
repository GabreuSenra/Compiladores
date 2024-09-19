/*Trabalho 2 - Teoria dos Compiladores (DCC045) 
 * Alunos: Gabriel Pereira Senra Soares - 201935006
 *         Lucas Castro Carvalho - 201835015
*/

package lang.parser;

import java.io.IOException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import lang.ast.SuperNode;
import lang.parser.LangLexer;
import lang.parser.LangParser;

public class MeuParseAdaptor implements ParseAdaptor {
    @Override
    public SuperNode parseFile(String path) {
        try {
            // Cria o lexer a partir do arquivo de entrada
            LangLexer lexer = new LangLexer(CharStreams.fromFileName(path));
            
            // Cria o token stream a partir do lexer
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            
            // Cria o parser a partir do token stream
            LangParser parser = new LangParser(tokens);
            
            parser.removeErrorListeners();
            ErrorListener errorListener = new ErrorListener();
            parser.addErrorListener(errorListener);

            // Faz o parse da regra de entrada 
            LangParser.ProgramContext tree = parser.program();

            CustomVisitor visitor = new CustomVisitor();

            SuperNode ast = visitor.visit(tree);

            return ast;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
