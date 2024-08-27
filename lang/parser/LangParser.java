package lang.parser;

// Generated from Lang.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class LangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, EQ=2, EQEQ=3, DIFF=4, PLUS=5, MINUS=6, MULT=7, DIV=8, MOD=9, GREATER=10, 
		LESS=11, NOT=12, AND=13, LP=14, RP=15, L_BRACKET=16, R_BRACKET=17, L_BRACE=18, 
		R_BRACE=19, DOT=20, TYPE_DEF=21, RETURN_TYPE=22, COMMA=23, SEMI=24, BOOLEAN=25, 
		ABSTRACT=26, BREAK=27, DATA_DEF=28, RETURN=29, INT_N=30, BOOL=31, FLOAT_N=32, 
		CHAR_N=33, BOOL_TRUE=34, BOOL_FALSE=35, IF=36, ELSE=37, PRINT=38, READ=39, 
		NEW=40, ITERATE=41, InputCharacter=42, FimDeLinha=43, Branco=44, ID=45, 
		INT=46, FLOAT=47, CHAR=48, WS=49, COMMENT=50, MLCOMMENT=51;
	public static final int
		RULE_program = 0, RULE_def = 1, RULE_data = 2, RULE_decl = 3, RULE_fun = 4, 
		RULE_params = 5, RULE_param = 6, RULE_type = 7, RULE_btype = 8, RULE_cmd = 9, 
		RULE_exp = 10, RULE_lvalue = 11, RULE_exps = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "def", "data", "decl", "fun", "params", "param", "type", "btype", 
			"cmd", "exp", "lvalue", "exps"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'null'", "'='", "'=='", "'!='", "'+'", "'-'", "'*'", "'/'", "'%'", 
			"'>'", "'<'", "'!'", "'&&'", "'('", "')'", "'['", "']'", "'{'", "'}'", 
			"'.'", "'::'", "':'", "','", "';'", "'boolean'", "'abstract'", "'break'", 
			"'data'", "'return'", "'Int'", "'Bool'", "'Float'", "'Char'", "'true'", 
			"'false'", "'if'", "'else'", "'print'", "'read'", "'new'", "'iterate'", 
			"'[^\\r|\\n]'", "'\\r|\\n|\\r\\n'", "'\\r|\\n|\\r\\n |[ \\t\\f]'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "EQ", "EQEQ", "DIFF", "PLUS", "MINUS", "MULT", "DIV", "MOD", 
			"GREATER", "LESS", "NOT", "AND", "LP", "RP", "L_BRACKET", "R_BRACKET", 
			"L_BRACE", "R_BRACE", "DOT", "TYPE_DEF", "RETURN_TYPE", "COMMA", "SEMI", 
			"BOOLEAN", "ABSTRACT", "BREAK", "DATA_DEF", "RETURN", "INT_N", "BOOL", 
			"FLOAT_N", "CHAR_N", "BOOL_TRUE", "BOOL_FALSE", "IF", "ELSE", "PRINT", 
			"READ", "NEW", "ITERATE", "InputCharacter", "FimDeLinha", "Branco", "ID", 
			"INT", "FLOAT", "CHAR", "WS", "COMMENT", "MLCOMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Lang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public DefContext def() {
			return getRuleContext(DefContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			def();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefContext extends ParserRuleContext {
		public DataContext data() {
			return getRuleContext(DataContext.class,0);
		}
		public FunContext fun() {
			return getRuleContext(FunContext.class,0);
		}
		public DefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitDef(this);
		}
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_def);
		try {
			setState(30);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DATA_DEF:
				enterOuterAlt(_localctx, 1);
				{
				setState(28);
				data();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(29);
				fun();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DataContext extends ParserRuleContext {
		public TerminalNode DATA_DEF() { return getToken(LangParser.DATA_DEF, 0); }
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TerminalNode L_BRACE() { return getToken(LangParser.L_BRACE, 0); }
		public TerminalNode R_BRACE() { return getToken(LangParser.R_BRACE, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public DataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitData(this);
		}
	}

	public final DataContext data() throws RecognitionException {
		DataContext _localctx = new DataContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_data);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(DATA_DEF);
			setState(33);
			match(ID);
			setState(34);
			match(L_BRACE);
			setState(36); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(35);
				decl();
				}
				}
				setState(38); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(40);
			match(R_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TerminalNode TYPE_DEF() { return getToken(LangParser.TYPE_DEF, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(LangParser.SEMI, 0); }
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(ID);
			setState(43);
			match(TYPE_DEF);
			setState(44);
			type(0);
			setState(45);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TerminalNode LP() { return getToken(LangParser.LP, 0); }
		public TerminalNode RP() { return getToken(LangParser.RP, 0); }
		public TerminalNode L_BRACE() { return getToken(LangParser.L_BRACE, 0); }
		public TerminalNode R_BRACE() { return getToken(LangParser.R_BRACE, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode RETURN_TYPE() { return getToken(LangParser.RETURN_TYPE, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LangParser.COMMA, i);
		}
		public FunContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fun; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterFun(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitFun(this);
		}
	}

	public final FunContext fun() throws RecognitionException {
		FunContext _localctx = new FunContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_fun);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(ID);
			setState(48);
			match(LP);
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(49);
				params();
				}
			}

			setState(52);
			match(RP);
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURN_TYPE) {
				{
				setState(53);
				match(RETURN_TYPE);
				setState(54);
				type(0);
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(55);
					match(COMMA);
					setState(56);
					type(0);
					}
					}
					setState(61);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(64);
			match(L_BRACE);
			setState(66); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(65);
				cmd();
				}
				}
				setState(68); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 38277285675008L) != 0) );
			setState(70);
			match(R_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamsContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LangParser.COMMA, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			param();
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(73);
				match(COMMA);
				setState(74);
				param();
				}
				}
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TerminalNode TYPE_DEF() { return getToken(LangParser.TYPE_DEF, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitParam(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(ID);
			setState(81);
			match(TYPE_DEF);
			setState(82);
			type(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public BtypeContext btype() {
			return getRuleContext(BtypeContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode L_BRACKET() { return getToken(LangParser.L_BRACKET, 0); }
		public TerminalNode R_BRACKET() { return getToken(LangParser.R_BRACKET, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(85);
			btype();
			}
			_ctx.stop = _input.LT(-1);
			setState(92);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(87);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(88);
					match(L_BRACKET);
					setState(89);
					match(R_BRACKET);
					}
					} 
				}
				setState(94);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BtypeContext extends ParserRuleContext {
		public TerminalNode INT_N() { return getToken(LangParser.INT_N, 0); }
		public TerminalNode CHAR_N() { return getToken(LangParser.CHAR_N, 0); }
		public TerminalNode BOOL() { return getToken(LangParser.BOOL, 0); }
		public TerminalNode FLOAT_N() { return getToken(LangParser.FLOAT_N, 0); }
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public BtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_btype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterBtype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitBtype(this);
		}
	}

	public final BtypeContext btype() throws RecognitionException {
		BtypeContext _localctx = new BtypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_btype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 35200478216192L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdContext extends ParserRuleContext {
		public TerminalNode L_BRACE() { return getToken(LangParser.L_BRACE, 0); }
		public TerminalNode R_BRACE() { return getToken(LangParser.R_BRACE, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode IF() { return getToken(LangParser.IF, 0); }
		public TerminalNode LP() { return getToken(LangParser.LP, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode RP() { return getToken(LangParser.RP, 0); }
		public TerminalNode ELSE() { return getToken(LangParser.ELSE, 0); }
		public TerminalNode ITERATE() { return getToken(LangParser.ITERATE, 0); }
		public TerminalNode READ() { return getToken(LangParser.READ, 0); }
		public List<LvalueContext> lvalue() {
			return getRuleContexts(LvalueContext.class);
		}
		public LvalueContext lvalue(int i) {
			return getRuleContext(LvalueContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(LangParser.SEMI, 0); }
		public TerminalNode PRINT() { return getToken(LangParser.PRINT, 0); }
		public TerminalNode RETURN() { return getToken(LangParser.RETURN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(LangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LangParser.COMMA, i);
		}
		public TerminalNode EQ() { return getToken(LangParser.EQ, 0); }
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TerminalNode LESS() { return getToken(LangParser.LESS, 0); }
		public TerminalNode GREATER() { return getToken(LangParser.GREATER, 0); }
		public ExpsContext exps() {
			return getRuleContext(ExpsContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmd);
		int _la;
		try {
			setState(168);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				match(L_BRACE);
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 38277285675008L) != 0)) {
					{
					{
					setState(98);
					cmd();
					}
					}
					setState(103);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(104);
				match(R_BRACE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				match(IF);
				setState(106);
				match(LP);
				setState(107);
				exp(0);
				setState(108);
				match(RP);
				setState(109);
				cmd();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(111);
				match(IF);
				setState(112);
				match(LP);
				setState(113);
				exp(0);
				setState(114);
				match(RP);
				setState(115);
				cmd();
				setState(118);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(116);
					match(ELSE);
					setState(117);
					cmd();
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(120);
				match(ITERATE);
				setState(121);
				match(LP);
				setState(122);
				exp(0);
				setState(123);
				match(RP);
				setState(124);
				cmd();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(126);
				match(READ);
				setState(127);
				lvalue(0);
				setState(128);
				match(SEMI);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(130);
				match(PRINT);
				setState(131);
				exp(0);
				setState(132);
				match(SEMI);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(134);
				match(RETURN);
				setState(135);
				exp(0);
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(136);
					match(COMMA);
					setState(137);
					exp(0);
					}
					}
					setState(142);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(143);
				match(SEMI);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(145);
				lvalue(0);
				setState(146);
				match(EQ);
				setState(147);
				exp(0);
				setState(148);
				match(SEMI);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(150);
				match(ID);
				setState(151);
				match(LP);
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 528916632588354L) != 0)) {
					{
					setState(152);
					exps();
					}
				}

				setState(155);
				match(RP);
				setState(156);
				match(LESS);
				setState(157);
				lvalue(0);
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(158);
					match(COMMA);
					setState(159);
					lvalue(0);
					}
					}
					setState(164);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(165);
				match(GREATER);
				setState(166);
				match(SEMI);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(LangParser.NOT, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode MINUS() { return getToken(LangParser.MINUS, 0); }
		public TerminalNode BOOL_TRUE() { return getToken(LangParser.BOOL_TRUE, 0); }
		public TerminalNode BOOL_FALSE() { return getToken(LangParser.BOOL_FALSE, 0); }
		public TerminalNode INT() { return getToken(LangParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(LangParser.FLOAT, 0); }
		public TerminalNode CHAR() { return getToken(LangParser.CHAR, 0); }
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public TerminalNode LP() { return getToken(LangParser.LP, 0); }
		public TerminalNode RP() { return getToken(LangParser.RP, 0); }
		public TerminalNode NEW() { return getToken(LangParser.NEW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode L_BRACKET() { return getToken(LangParser.L_BRACKET, 0); }
		public TerminalNode R_BRACKET() { return getToken(LangParser.R_BRACKET, 0); }
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public ExpsContext exps() {
			return getRuleContext(ExpsContext.class,0);
		}
		public TerminalNode AND() { return getToken(LangParser.AND, 0); }
		public TerminalNode LESS() { return getToken(LangParser.LESS, 0); }
		public TerminalNode EQEQ() { return getToken(LangParser.EQEQ, 0); }
		public TerminalNode DIFF() { return getToken(LangParser.DIFF, 0); }
		public TerminalNode PLUS() { return getToken(LangParser.PLUS, 0); }
		public TerminalNode MULT() { return getToken(LangParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(LangParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(LangParser.MOD, 0); }
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitExp(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState);
		ExpContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(171);
				match(NOT);
				setState(172);
				exp(12);
				}
				break;
			case 2:
				{
				setState(173);
				match(MINUS);
				setState(174);
				exp(11);
				}
				break;
			case 3:
				{
				setState(175);
				match(BOOL_TRUE);
				}
				break;
			case 4:
				{
				setState(176);
				match(BOOL_FALSE);
				}
				break;
			case 5:
				{
				setState(177);
				match(T__0);
				}
				break;
			case 6:
				{
				setState(178);
				match(INT);
				}
				break;
			case 7:
				{
				setState(179);
				match(FLOAT);
				}
				break;
			case 8:
				{
				setState(180);
				match(CHAR);
				}
				break;
			case 9:
				{
				setState(181);
				lvalue(0);
				}
				break;
			case 10:
				{
				setState(182);
				match(LP);
				setState(183);
				exp(0);
				setState(184);
				match(RP);
				}
				break;
			case 11:
				{
				setState(186);
				match(NEW);
				setState(187);
				type(0);
				setState(192);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(188);
					match(L_BRACKET);
					setState(189);
					exp(0);
					setState(190);
					match(R_BRACKET);
					}
					break;
				}
				}
				break;
			case 12:
				{
				setState(194);
				match(ID);
				setState(195);
				match(LP);
				setState(197);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 528916632588354L) != 0)) {
					{
					setState(196);
					exps();
					}
				}

				setState(199);
				match(RP);
				setState(204);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(200);
					match(L_BRACKET);
					setState(201);
					exp(0);
					setState(202);
					match(R_BRACKET);
					}
					break;
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(213);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_exp);
					setState(208);
					if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
					setState(209);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 11256L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(210);
					exp(14);
					}
					} 
				}
				setState(215);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LvalueContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public TerminalNode L_BRACKET() { return getToken(LangParser.L_BRACKET, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode R_BRACKET() { return getToken(LangParser.R_BRACKET, 0); }
		public TerminalNode DOT() { return getToken(LangParser.DOT, 0); }
		public LvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterLvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitLvalue(this);
		}
	}

	public final LvalueContext lvalue() throws RecognitionException {
		return lvalue(0);
	}

	private LvalueContext lvalue(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LvalueContext _localctx = new LvalueContext(_ctx, _parentState);
		LvalueContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_lvalue, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(217);
			match(ID);
			}
			_ctx.stop = _input.LT(-1);
			setState(229);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(227);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new LvalueContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(219);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(220);
						match(L_BRACKET);
						setState(221);
						exp(0);
						setState(222);
						match(R_BRACKET);
						}
						break;
					case 2:
						{
						_localctx = new LvalueContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(224);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(225);
						match(DOT);
						setState(226);
						match(ID);
						}
						break;
					}
					} 
				}
				setState(231);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpsContext extends ParserRuleContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LangParser.COMMA, i);
		}
		public ExpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exps; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterExps(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitExps(this);
		}
	}

	public final ExpsContext exps() throws RecognitionException {
		ExpsContext _localctx = new ExpsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_exps);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			exp(0);
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(233);
				match(COMMA);
				setState(234);
				exp(0);
				}
				}
				setState(239);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return type_sempred((TypeContext)_localctx, predIndex);
		case 10:
			return exp_sempred((ExpContext)_localctx, predIndex);
		case 11:
			return lvalue_sempred((LvalueContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 13);
		}
		return true;
	}
	private boolean lvalue_sempred(LvalueContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 2);
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00013\u00f1\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0003\u0001"+
		"\u001f\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0004\u0002"+
		"%\b\u0002\u000b\u0002\f\u0002&\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u00043\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0005\u0004:\b\u0004\n\u0004\f\u0004=\t\u0004\u0003"+
		"\u0004?\b\u0004\u0001\u0004\u0001\u0004\u0004\u0004C\b\u0004\u000b\u0004"+
		"\f\u0004D\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0005\u0005L\b\u0005\n\u0005\f\u0005O\t\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0005\u0007[\b\u0007\n\u0007\f\u0007^\t\u0007"+
		"\u0001\b\u0001\b\u0001\t\u0001\t\u0005\td\b\t\n\t\f\tg\t\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0003\tw\b\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005\t\u008b\b\t\n\t\f\t\u008e"+
		"\t\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0003\t\u009a\b\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005"+
		"\t\u00a1\b\t\n\t\f\t\u00a4\t\t\u0001\t\u0001\t\u0001\t\u0003\t\u00a9\b"+
		"\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00c1\b\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u00c6\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00cd"+
		"\b\n\u0003\n\u00cf\b\n\u0001\n\u0001\n\u0001\n\u0005\n\u00d4\b\n\n\n\f"+
		"\n\u00d7\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0005\u000b\u00e4\b\u000b\n\u000b\f\u000b\u00e7\t\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0005\f\u00ec\b\f\n\f\f\f\u00ef\t\f\u0001\f\u0000\u0003\u000e"+
		"\u0014\u0016\r\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u0000\u0002\u0002\u0000\u001e!--\u0003\u0000\u0003\t\u000b\u000b"+
		"\r\r\u010a\u0000\u001a\u0001\u0000\u0000\u0000\u0002\u001e\u0001\u0000"+
		"\u0000\u0000\u0004 \u0001\u0000\u0000\u0000\u0006*\u0001\u0000\u0000\u0000"+
		"\b/\u0001\u0000\u0000\u0000\nH\u0001\u0000\u0000\u0000\fP\u0001\u0000"+
		"\u0000\u0000\u000eT\u0001\u0000\u0000\u0000\u0010_\u0001\u0000\u0000\u0000"+
		"\u0012\u00a8\u0001\u0000\u0000\u0000\u0014\u00ce\u0001\u0000\u0000\u0000"+
		"\u0016\u00d8\u0001\u0000\u0000\u0000\u0018\u00e8\u0001\u0000\u0000\u0000"+
		"\u001a\u001b\u0003\u0002\u0001\u0000\u001b\u0001\u0001\u0000\u0000\u0000"+
		"\u001c\u001f\u0003\u0004\u0002\u0000\u001d\u001f\u0003\b\u0004\u0000\u001e"+
		"\u001c\u0001\u0000\u0000\u0000\u001e\u001d\u0001\u0000\u0000\u0000\u001f"+
		"\u0003\u0001\u0000\u0000\u0000 !\u0005\u001c\u0000\u0000!\"\u0005-\u0000"+
		"\u0000\"$\u0005\u0012\u0000\u0000#%\u0003\u0006\u0003\u0000$#\u0001\u0000"+
		"\u0000\u0000%&\u0001\u0000\u0000\u0000&$\u0001\u0000\u0000\u0000&\'\u0001"+
		"\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000()\u0005\u0013\u0000\u0000"+
		")\u0005\u0001\u0000\u0000\u0000*+\u0005-\u0000\u0000+,\u0005\u0015\u0000"+
		"\u0000,-\u0003\u000e\u0007\u0000-.\u0005\u0018\u0000\u0000.\u0007\u0001"+
		"\u0000\u0000\u0000/0\u0005-\u0000\u000002\u0005\u000e\u0000\u000013\u0003"+
		"\n\u0005\u000021\u0001\u0000\u0000\u000023\u0001\u0000\u0000\u000034\u0001"+
		"\u0000\u0000\u00004>\u0005\u000f\u0000\u000056\u0005\u0016\u0000\u0000"+
		"6;\u0003\u000e\u0007\u000078\u0005\u0017\u0000\u00008:\u0003\u000e\u0007"+
		"\u000097\u0001\u0000\u0000\u0000:=\u0001\u0000\u0000\u0000;9\u0001\u0000"+
		"\u0000\u0000;<\u0001\u0000\u0000\u0000<?\u0001\u0000\u0000\u0000=;\u0001"+
		"\u0000\u0000\u0000>5\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000"+
		"?@\u0001\u0000\u0000\u0000@B\u0005\u0012\u0000\u0000AC\u0003\u0012\t\u0000"+
		"BA\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DB\u0001\u0000\u0000"+
		"\u0000DE\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000FG\u0005\u0013"+
		"\u0000\u0000G\t\u0001\u0000\u0000\u0000HM\u0003\f\u0006\u0000IJ\u0005"+
		"\u0017\u0000\u0000JL\u0003\f\u0006\u0000KI\u0001\u0000\u0000\u0000LO\u0001"+
		"\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000"+
		"N\u000b\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000PQ\u0005-\u0000"+
		"\u0000QR\u0005\u0015\u0000\u0000RS\u0003\u000e\u0007\u0000S\r\u0001\u0000"+
		"\u0000\u0000TU\u0006\u0007\uffff\uffff\u0000UV\u0003\u0010\b\u0000V\\"+
		"\u0001\u0000\u0000\u0000WX\n\u0002\u0000\u0000XY\u0005\u0010\u0000\u0000"+
		"Y[\u0005\u0011\u0000\u0000ZW\u0001\u0000\u0000\u0000[^\u0001\u0000\u0000"+
		"\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000]\u000f\u0001"+
		"\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000_`\u0007\u0000\u0000\u0000"+
		"`\u0011\u0001\u0000\u0000\u0000ae\u0005\u0012\u0000\u0000bd\u0003\u0012"+
		"\t\u0000cb\u0001\u0000\u0000\u0000dg\u0001\u0000\u0000\u0000ec\u0001\u0000"+
		"\u0000\u0000ef\u0001\u0000\u0000\u0000fh\u0001\u0000\u0000\u0000ge\u0001"+
		"\u0000\u0000\u0000h\u00a9\u0005\u0013\u0000\u0000ij\u0005$\u0000\u0000"+
		"jk\u0005\u000e\u0000\u0000kl\u0003\u0014\n\u0000lm\u0005\u000f\u0000\u0000"+
		"mn\u0003\u0012\t\u0000n\u00a9\u0001\u0000\u0000\u0000op\u0005$\u0000\u0000"+
		"pq\u0005\u000e\u0000\u0000qr\u0003\u0014\n\u0000rs\u0005\u000f\u0000\u0000"+
		"sv\u0003\u0012\t\u0000tu\u0005%\u0000\u0000uw\u0003\u0012\t\u0000vt\u0001"+
		"\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000w\u00a9\u0001\u0000\u0000"+
		"\u0000xy\u0005)\u0000\u0000yz\u0005\u000e\u0000\u0000z{\u0003\u0014\n"+
		"\u0000{|\u0005\u000f\u0000\u0000|}\u0003\u0012\t\u0000}\u00a9\u0001\u0000"+
		"\u0000\u0000~\u007f\u0005\'\u0000\u0000\u007f\u0080\u0003\u0016\u000b"+
		"\u0000\u0080\u0081\u0005\u0018\u0000\u0000\u0081\u00a9\u0001\u0000\u0000"+
		"\u0000\u0082\u0083\u0005&\u0000\u0000\u0083\u0084\u0003\u0014\n\u0000"+
		"\u0084\u0085\u0005\u0018\u0000\u0000\u0085\u00a9\u0001\u0000\u0000\u0000"+
		"\u0086\u0087\u0005\u001d\u0000\u0000\u0087\u008c\u0003\u0014\n\u0000\u0088"+
		"\u0089\u0005\u0017\u0000\u0000\u0089\u008b\u0003\u0014\n\u0000\u008a\u0088"+
		"\u0001\u0000\u0000\u0000\u008b\u008e\u0001\u0000\u0000\u0000\u008c\u008a"+
		"\u0001\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u008f"+
		"\u0001\u0000\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008f\u0090"+
		"\u0005\u0018\u0000\u0000\u0090\u00a9\u0001\u0000\u0000\u0000\u0091\u0092"+
		"\u0003\u0016\u000b\u0000\u0092\u0093\u0005\u0002\u0000\u0000\u0093\u0094"+
		"\u0003\u0014\n\u0000\u0094\u0095\u0005\u0018\u0000\u0000\u0095\u00a9\u0001"+
		"\u0000\u0000\u0000\u0096\u0097\u0005-\u0000\u0000\u0097\u0099\u0005\u000e"+
		"\u0000\u0000\u0098\u009a\u0003\u0018\f\u0000\u0099\u0098\u0001\u0000\u0000"+
		"\u0000\u0099\u009a\u0001\u0000\u0000\u0000\u009a\u009b\u0001\u0000\u0000"+
		"\u0000\u009b\u009c\u0005\u000f\u0000\u0000\u009c\u009d\u0005\u000b\u0000"+
		"\u0000\u009d\u00a2\u0003\u0016\u000b\u0000\u009e\u009f\u0005\u0017\u0000"+
		"\u0000\u009f\u00a1\u0003\u0016\u000b\u0000\u00a0\u009e\u0001\u0000\u0000"+
		"\u0000\u00a1\u00a4\u0001\u0000\u0000\u0000\u00a2\u00a0\u0001\u0000\u0000"+
		"\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a5\u0001\u0000\u0000"+
		"\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000\u00a5\u00a6\u0005\n\u0000\u0000"+
		"\u00a6\u00a7\u0005\u0018\u0000\u0000\u00a7\u00a9\u0001\u0000\u0000\u0000"+
		"\u00a8a\u0001\u0000\u0000\u0000\u00a8i\u0001\u0000\u0000\u0000\u00a8o"+
		"\u0001\u0000\u0000\u0000\u00a8x\u0001\u0000\u0000\u0000\u00a8~\u0001\u0000"+
		"\u0000\u0000\u00a8\u0082\u0001\u0000\u0000\u0000\u00a8\u0086\u0001\u0000"+
		"\u0000\u0000\u00a8\u0091\u0001\u0000\u0000\u0000\u00a8\u0096\u0001\u0000"+
		"\u0000\u0000\u00a9\u0013\u0001\u0000\u0000\u0000\u00aa\u00ab\u0006\n\uffff"+
		"\uffff\u0000\u00ab\u00ac\u0005\f\u0000\u0000\u00ac\u00cf\u0003\u0014\n"+
		"\f\u00ad\u00ae\u0005\u0006\u0000\u0000\u00ae\u00cf\u0003\u0014\n\u000b"+
		"\u00af\u00cf\u0005\"\u0000\u0000\u00b0\u00cf\u0005#\u0000\u0000\u00b1"+
		"\u00cf\u0005\u0001\u0000\u0000\u00b2\u00cf\u0005.\u0000\u0000\u00b3\u00cf"+
		"\u0005/\u0000\u0000\u00b4\u00cf\u00050\u0000\u0000\u00b5\u00cf\u0003\u0016"+
		"\u000b\u0000\u00b6\u00b7\u0005\u000e\u0000\u0000\u00b7\u00b8\u0003\u0014"+
		"\n\u0000\u00b8\u00b9\u0005\u000f\u0000\u0000\u00b9\u00cf\u0001\u0000\u0000"+
		"\u0000\u00ba\u00bb\u0005(\u0000\u0000\u00bb\u00c0\u0003\u000e\u0007\u0000"+
		"\u00bc\u00bd\u0005\u0010\u0000\u0000\u00bd\u00be\u0003\u0014\n\u0000\u00be"+
		"\u00bf\u0005\u0011\u0000\u0000\u00bf\u00c1\u0001\u0000\u0000\u0000\u00c0"+
		"\u00bc\u0001\u0000\u0000\u0000\u00c0\u00c1\u0001\u0000\u0000\u0000\u00c1"+
		"\u00cf\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005-\u0000\u0000\u00c3\u00c5"+
		"\u0005\u000e\u0000\u0000\u00c4\u00c6\u0003\u0018\f\u0000\u00c5\u00c4\u0001"+
		"\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001"+
		"\u0000\u0000\u0000\u00c7\u00cc\u0005\u000f\u0000\u0000\u00c8\u00c9\u0005"+
		"\u0010\u0000\u0000\u00c9\u00ca\u0003\u0014\n\u0000\u00ca\u00cb\u0005\u0011"+
		"\u0000\u0000\u00cb\u00cd\u0001\u0000\u0000\u0000\u00cc\u00c8\u0001\u0000"+
		"\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000\u00cd\u00cf\u0001\u0000"+
		"\u0000\u0000\u00ce\u00aa\u0001\u0000\u0000\u0000\u00ce\u00ad\u0001\u0000"+
		"\u0000\u0000\u00ce\u00af\u0001\u0000\u0000\u0000\u00ce\u00b0\u0001\u0000"+
		"\u0000\u0000\u00ce\u00b1\u0001\u0000\u0000\u0000\u00ce\u00b2\u0001\u0000"+
		"\u0000\u0000\u00ce\u00b3\u0001\u0000\u0000\u0000\u00ce\u00b4\u0001\u0000"+
		"\u0000\u0000\u00ce\u00b5\u0001\u0000\u0000\u0000\u00ce\u00b6\u0001\u0000"+
		"\u0000\u0000\u00ce\u00ba\u0001\u0000\u0000\u0000\u00ce\u00c2\u0001\u0000"+
		"\u0000\u0000\u00cf\u00d5\u0001\u0000\u0000\u0000\u00d0\u00d1\n\r\u0000"+
		"\u0000\u00d1\u00d2\u0007\u0001\u0000\u0000\u00d2\u00d4\u0003\u0014\n\u000e"+
		"\u00d3\u00d0\u0001\u0000\u0000\u0000\u00d4\u00d7\u0001\u0000\u0000\u0000"+
		"\u00d5\u00d3\u0001\u0000\u0000\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000"+
		"\u00d6\u0015\u0001\u0000\u0000\u0000\u00d7\u00d5\u0001\u0000\u0000\u0000"+
		"\u00d8\u00d9\u0006\u000b\uffff\uffff\u0000\u00d9\u00da\u0005-\u0000\u0000"+
		"\u00da\u00e5\u0001\u0000\u0000\u0000\u00db\u00dc\n\u0002\u0000\u0000\u00dc"+
		"\u00dd\u0005\u0010\u0000\u0000\u00dd\u00de\u0003\u0014\n\u0000\u00de\u00df"+
		"\u0005\u0011\u0000\u0000\u00df\u00e4\u0001\u0000\u0000\u0000\u00e0\u00e1"+
		"\n\u0001\u0000\u0000\u00e1\u00e2\u0005\u0014\u0000\u0000\u00e2\u00e4\u0005"+
		"-\u0000\u0000\u00e3\u00db\u0001\u0000\u0000\u0000\u00e3\u00e0\u0001\u0000"+
		"\u0000\u0000\u00e4\u00e7\u0001\u0000\u0000\u0000\u00e5\u00e3\u0001\u0000"+
		"\u0000\u0000\u00e5\u00e6\u0001\u0000\u0000\u0000\u00e6\u0017\u0001\u0000"+
		"\u0000\u0000\u00e7\u00e5\u0001\u0000\u0000\u0000\u00e8\u00ed\u0003\u0014"+
		"\n\u0000\u00e9\u00ea\u0005\u0017\u0000\u0000\u00ea\u00ec\u0003\u0014\n"+
		"\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00ec\u00ef\u0001\u0000\u0000"+
		"\u0000\u00ed\u00eb\u0001\u0000\u0000\u0000\u00ed\u00ee\u0001\u0000\u0000"+
		"\u0000\u00ee\u0019\u0001\u0000\u0000\u0000\u00ef\u00ed\u0001\u0000\u0000"+
		"\u0000\u0016\u001e&2;>DM\\ev\u008c\u0099\u00a2\u00a8\u00c0\u00c5\u00cc"+
		"\u00ce\u00d5\u00e3\u00e5\u00ed";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}