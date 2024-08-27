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
            
            // Define um error listener customizado para capturar erros
            parser.removeErrorListeners();
            parser.addErrorListener(new ErrorListener());

            // Faz o parse da regra de entrada (supondo que a regra inicial da gramática seja 'program')
            LangParser.ProgramContext tree = parser.program();
            
            // Aqui você deve converter a árvore de análise em um SuperNode
            CustomListener listener = new CustomListener();
            parser.addParseListener(listener);
            parser.program(); // Inicia o parsing

            return listener.getResult();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
