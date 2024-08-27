package lang.parser;

// Generated from Lang.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LangParser}.
 */
public interface LangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(LangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(LangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#def}.
	 * @param ctx the parse tree
	 */
	void enterDef(LangParser.DefContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#def}.
	 * @param ctx the parse tree
	 */
	void exitDef(LangParser.DefContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#data}.
	 * @param ctx the parse tree
	 */
	void enterData(LangParser.DataContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#data}.
	 * @param ctx the parse tree
	 */
	void exitData(LangParser.DataContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(LangParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(LangParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#fun}.
	 * @param ctx the parse tree
	 */
	void enterFun(LangParser.FunContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#fun}.
	 * @param ctx the parse tree
	 */
	void exitFun(LangParser.FunContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(LangParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(LangParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(LangParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(LangParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(LangParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(LangParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#btype}.
	 * @param ctx the parse tree
	 */
	void enterBtype(LangParser.BtypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#btype}.
	 * @param ctx the parse tree
	 */
	void exitBtype(LangParser.BtypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(LangParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(LangParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(LangParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(LangParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterLvalue(LangParser.LvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitLvalue(LangParser.LvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#exps}.
	 * @param ctx the parse tree
	 */
	void enterExps(LangParser.ExpsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#exps}.
	 * @param ctx the parse tree
	 */
	void exitExps(LangParser.ExpsContext ctx);
}