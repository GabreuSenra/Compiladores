// Generated from /home/kali/Downloads/code/Lang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class LangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		EQ=1, EQEQ=2, DIFF=3, PLUS=4, MINUS=5, MULT=6, DIV=7, MOD=8, GREATER=9, 
		LESS=10, NOT=11, AND=12, LP=13, RP=14, L_BRACKET=15, R_BRACKET=16, L_BRACE=17, 
		R_BRACE=18, DOT=19, TYPE_DEF=20, RETURN_TYPE=21, COMMA=22, SEMI=23, NULL=24, 
		BOOLEAN=25, ABSTRACT=26, BREAK=27, DATA_DEF=28, RETURN=29, INT_N=30, BOOL=31, 
		FLOAT_N=32, CHAR_N=33, BOOL_TRUE=34, BOOL_FALSE=35, IF=36, ELSE=37, PRINT=38, 
		READ=39, NEW=40, ITERATE=41, InputCharacter=42, FimDeLinha=43, Branco=44, 
		ID=45, INT=46, FLOAT=47, CHAR=48, WS=49, COMMENT=50, MLCOMMENT=51;
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
			null, "'='", "'=='", "'!='", "'+'", "'-'", "'*'", "'/'", "'%'", "'>'", 
			"'<'", "'!'", "'&&'", "'('", "')'", "'['", "']'", "'{'", "'}'", "'.'", 
			"'::'", "':'", "','", "';'", "'null'", "'boolean'", "'abstract'", "'break'", 
			"'data'", "'return'", "'Int'", "'Bool'", "'Float'", "'Char'", "'true'", 
			"'false'", "'if'", "'else'", "'print'", "'read'", "'new'", "'iterate'", 
			"'[^\\r|\\n]'", "'\\r|\\n|\\r\\n'", "'\\r|\\n|\\r\\n |[ \\t\\f]'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "EQ", "EQEQ", "DIFF", "PLUS", "MINUS", "MULT", "DIV", "MOD", "GREATER", 
			"LESS", "NOT", "AND", "LP", "RP", "L_BRACKET", "R_BRACKET", "L_BRACE", 
			"R_BRACE", "DOT", "TYPE_DEF", "RETURN_TYPE", "COMMA", "SEMI", "NULL", 
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
		public List<DefContext> def() {
			return getRuleContexts(DefContext.class);
		}
		public DefContext def(int i) {
			return getRuleContext(DefContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(26);
				def();
				}
				}
				setState(29); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DATA_DEF || _la==ID );
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
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_def);
		try {
			setState(33);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DATA_DEF:
				enterOuterAlt(_localctx, 1);
				{
				setState(31);
				data();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
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
	}

	public final DataContext data() throws RecognitionException {
		DataContext _localctx = new DataContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_data);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(DATA_DEF);
			setState(36);
			match(ID);
			setState(37);
			match(L_BRACE);
			setState(39); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(38);
				decl();
				}
				}
				setState(41); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(43);
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
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(ID);
			setState(46);
			match(TYPE_DEF);
			setState(47);
			type(0);
			setState(48);
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
	}

	public final FunContext fun() throws RecognitionException {
		FunContext _localctx = new FunContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_fun);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(ID);
			setState(51);
			match(LP);
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(52);
				params();
				}
			}

			setState(55);
			match(RP);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURN_TYPE) {
				{
				setState(56);
				match(RETURN_TYPE);
				setState(57);
				type(0);
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(58);
					match(COMMA);
					setState(59);
					type(0);
					}
					}
					setState(64);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(67);
			match(L_BRACE);
			setState(69); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(68);
				cmd();
				}
				}
				setState(71); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 38277285543936L) != 0) );
			setState(73);
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
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			param();
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(76);
				match(COMMA);
				setState(77);
				param();
				}
				}
				setState(82);
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
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(ID);
			setState(84);
			match(TYPE_DEF);
			setState(85);
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
			setState(88);
			btype();
			}
			_ctx.stop = _input.LT(-1);
			setState(95);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(90);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(91);
					match(L_BRACKET);
					setState(92);
					match(R_BRACKET);
					}
					} 
				}
				setState(97);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
	}

	public final BtypeContext btype() throws RecognitionException {
		BtypeContext _localctx = new BtypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_btype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
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
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmd);
		int _la;
		try {
			setState(171);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(100);
				match(L_BRACE);
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 38277285543936L) != 0)) {
					{
					{
					setState(101);
					cmd();
					}
					}
					setState(106);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(107);
				match(R_BRACE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				match(IF);
				setState(109);
				match(LP);
				setState(110);
				exp(0);
				setState(111);
				match(RP);
				setState(112);
				cmd();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(114);
				match(IF);
				setState(115);
				match(LP);
				setState(116);
				exp(0);
				setState(117);
				match(RP);
				setState(118);
				cmd();
				setState(121);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(119);
					match(ELSE);
					setState(120);
					cmd();
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(123);
				match(ITERATE);
				setState(124);
				match(LP);
				setState(125);
				exp(0);
				setState(126);
				match(RP);
				setState(127);
				cmd();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(129);
				match(READ);
				setState(130);
				lvalue(0);
				setState(131);
				match(SEMI);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(133);
				match(PRINT);
				setState(134);
				exp(0);
				setState(135);
				match(SEMI);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(137);
				match(RETURN);
				setState(138);
				exp(0);
				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(139);
					match(COMMA);
					setState(140);
					exp(0);
					}
					}
					setState(145);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(146);
				match(SEMI);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(148);
				lvalue(0);
				setState(149);
				match(EQ);
				setState(150);
				exp(0);
				setState(151);
				match(SEMI);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(153);
				match(ID);
				setState(154);
				match(LP);
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 528916649355296L) != 0)) {
					{
					setState(155);
					exps();
					}
				}

				setState(158);
				match(RP);
				setState(159);
				match(LESS);
				setState(160);
				lvalue(0);
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(161);
					match(COMMA);
					setState(162);
					lvalue(0);
					}
					}
					setState(167);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(168);
				match(GREATER);
				setState(169);
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
		public TerminalNode NULL() { return getToken(LangParser.NULL, 0); }
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
			setState(209);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(174);
				match(NOT);
				setState(175);
				exp(12);
				}
				break;
			case 2:
				{
				setState(176);
				match(MINUS);
				setState(177);
				exp(11);
				}
				break;
			case 3:
				{
				setState(178);
				match(BOOL_TRUE);
				}
				break;
			case 4:
				{
				setState(179);
				match(BOOL_FALSE);
				}
				break;
			case 5:
				{
				setState(180);
				match(NULL);
				}
				break;
			case 6:
				{
				setState(181);
				match(INT);
				}
				break;
			case 7:
				{
				setState(182);
				match(FLOAT);
				}
				break;
			case 8:
				{
				setState(183);
				match(CHAR);
				}
				break;
			case 9:
				{
				setState(184);
				lvalue(0);
				}
				break;
			case 10:
				{
				setState(185);
				match(LP);
				setState(186);
				exp(0);
				setState(187);
				match(RP);
				}
				break;
			case 11:
				{
				setState(189);
				match(NEW);
				setState(190);
				type(0);
				setState(195);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(191);
					match(L_BRACKET);
					setState(192);
					exp(0);
					setState(193);
					match(R_BRACKET);
					}
					break;
				}
				}
				break;
			case 12:
				{
				setState(197);
				match(ID);
				setState(198);
				match(LP);
				setState(200);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 528916649355296L) != 0)) {
					{
					setState(199);
					exps();
					}
				}

				setState(202);
				match(RP);
				setState(207);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(203);
					match(L_BRACKET);
					setState(204);
					exp(0);
					setState(205);
					match(R_BRACKET);
					}
					break;
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(216);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_exp);
					setState(211);
					if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
					setState(212);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 5628L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(213);
					exp(14);
					}
					} 
				}
				setState(218);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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
			setState(220);
			match(ID);
			}
			_ctx.stop = _input.LT(-1);
			setState(232);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(230);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
					case 1:
						{
						_localctx = new LvalueContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(222);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(223);
						match(L_BRACKET);
						setState(224);
						exp(0);
						setState(225);
						match(R_BRACKET);
						}
						break;
					case 2:
						{
						_localctx = new LvalueContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(227);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(228);
						match(DOT);
						setState(229);
						match(ID);
						}
						break;
					}
					} 
				}
				setState(234);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
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
	}

	public final ExpsContext exps() throws RecognitionException {
		ExpsContext _localctx = new ExpsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_exps);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			exp(0);
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(236);
				match(COMMA);
				setState(237);
				exp(0);
				}
				}
				setState(242);
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
		"\u0004\u00013\u00f4\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0001\u0000\u0004\u0000\u001c\b\u0000\u000b\u0000\f\u0000\u001d"+
		"\u0001\u0001\u0001\u0001\u0003\u0001\"\b\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0004\u0002(\b\u0002\u000b\u0002\f\u0002)\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u00046\b\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004=\b"+
		"\u0004\n\u0004\f\u0004@\t\u0004\u0003\u0004B\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0004\u0004F\b\u0004\u000b\u0004\f\u0004G\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005O\b\u0005\n\u0005\f\u0005"+
		"R\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007"+
		"^\b\u0007\n\u0007\f\u0007a\t\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0005"+
		"\tg\b\t\n\t\f\tj\t\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\tz\b"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0005\t\u008e\b\t\n\t\f\t\u0091\t\t\u0001\t\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u009d\b\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005\t\u00a4\b\t\n\t\f\t\u00a7\t\t"+
		"\u0001\t\u0001\t\u0001\t\u0003\t\u00ac\b\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u00c4\b\n\u0001\n\u0001\n\u0001\n\u0003\n\u00c9\b\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00d0\b\n\u0003\n\u00d2\b\n\u0001"+
		"\n\u0001\n\u0001\n\u0005\n\u00d7\b\n\n\n\f\n\u00da\t\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u00e7\b\u000b\n"+
		"\u000b\f\u000b\u00ea\t\u000b\u0001\f\u0001\f\u0001\f\u0005\f\u00ef\b\f"+
		"\n\f\f\f\u00f2\t\f\u0001\f\u0000\u0003\u000e\u0014\u0016\r\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u0000\u0002\u0002"+
		"\u0000\u001e!--\u0003\u0000\u0002\b\n\n\f\f\u010e\u0000\u001b\u0001\u0000"+
		"\u0000\u0000\u0002!\u0001\u0000\u0000\u0000\u0004#\u0001\u0000\u0000\u0000"+
		"\u0006-\u0001\u0000\u0000\u0000\b2\u0001\u0000\u0000\u0000\nK\u0001\u0000"+
		"\u0000\u0000\fS\u0001\u0000\u0000\u0000\u000eW\u0001\u0000\u0000\u0000"+
		"\u0010b\u0001\u0000\u0000\u0000\u0012\u00ab\u0001\u0000\u0000\u0000\u0014"+
		"\u00d1\u0001\u0000\u0000\u0000\u0016\u00db\u0001\u0000\u0000\u0000\u0018"+
		"\u00eb\u0001\u0000\u0000\u0000\u001a\u001c\u0003\u0002\u0001\u0000\u001b"+
		"\u001a\u0001\u0000\u0000\u0000\u001c\u001d\u0001\u0000\u0000\u0000\u001d"+
		"\u001b\u0001\u0000\u0000\u0000\u001d\u001e\u0001\u0000\u0000\u0000\u001e"+
		"\u0001\u0001\u0000\u0000\u0000\u001f\"\u0003\u0004\u0002\u0000 \"\u0003"+
		"\b\u0004\u0000!\u001f\u0001\u0000\u0000\u0000! \u0001\u0000\u0000\u0000"+
		"\"\u0003\u0001\u0000\u0000\u0000#$\u0005\u001c\u0000\u0000$%\u0005-\u0000"+
		"\u0000%\'\u0005\u0011\u0000\u0000&(\u0003\u0006\u0003\u0000\'&\u0001\u0000"+
		"\u0000\u0000()\u0001\u0000\u0000\u0000)\'\u0001\u0000\u0000\u0000)*\u0001"+
		"\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000+,\u0005\u0012\u0000\u0000"+
		",\u0005\u0001\u0000\u0000\u0000-.\u0005-\u0000\u0000./\u0005\u0014\u0000"+
		"\u0000/0\u0003\u000e\u0007\u000001\u0005\u0017\u0000\u00001\u0007\u0001"+
		"\u0000\u0000\u000023\u0005-\u0000\u000035\u0005\r\u0000\u000046\u0003"+
		"\n\u0005\u000054\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u000067\u0001"+
		"\u0000\u0000\u00007A\u0005\u000e\u0000\u000089\u0005\u0015\u0000\u0000"+
		"9>\u0003\u000e\u0007\u0000:;\u0005\u0016\u0000\u0000;=\u0003\u000e\u0007"+
		"\u0000<:\u0001\u0000\u0000\u0000=@\u0001\u0000\u0000\u0000><\u0001\u0000"+
		"\u0000\u0000>?\u0001\u0000\u0000\u0000?B\u0001\u0000\u0000\u0000@>\u0001"+
		"\u0000\u0000\u0000A8\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000"+
		"BC\u0001\u0000\u0000\u0000CE\u0005\u0011\u0000\u0000DF\u0003\u0012\t\u0000"+
		"ED\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000"+
		"\u0000GH\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000IJ\u0005\u0012"+
		"\u0000\u0000J\t\u0001\u0000\u0000\u0000KP\u0003\f\u0006\u0000LM\u0005"+
		"\u0016\u0000\u0000MO\u0003\f\u0006\u0000NL\u0001\u0000\u0000\u0000OR\u0001"+
		"\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000"+
		"Q\u000b\u0001\u0000\u0000\u0000RP\u0001\u0000\u0000\u0000ST\u0005-\u0000"+
		"\u0000TU\u0005\u0014\u0000\u0000UV\u0003\u000e\u0007\u0000V\r\u0001\u0000"+
		"\u0000\u0000WX\u0006\u0007\uffff\uffff\u0000XY\u0003\u0010\b\u0000Y_\u0001"+
		"\u0000\u0000\u0000Z[\n\u0002\u0000\u0000[\\\u0005\u000f\u0000\u0000\\"+
		"^\u0005\u0010\u0000\u0000]Z\u0001\u0000\u0000\u0000^a\u0001\u0000\u0000"+
		"\u0000_]\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`\u000f\u0001"+
		"\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000bc\u0007\u0000\u0000\u0000"+
		"c\u0011\u0001\u0000\u0000\u0000dh\u0005\u0011\u0000\u0000eg\u0003\u0012"+
		"\t\u0000fe\u0001\u0000\u0000\u0000gj\u0001\u0000\u0000\u0000hf\u0001\u0000"+
		"\u0000\u0000hi\u0001\u0000\u0000\u0000ik\u0001\u0000\u0000\u0000jh\u0001"+
		"\u0000\u0000\u0000k\u00ac\u0005\u0012\u0000\u0000lm\u0005$\u0000\u0000"+
		"mn\u0005\r\u0000\u0000no\u0003\u0014\n\u0000op\u0005\u000e\u0000\u0000"+
		"pq\u0003\u0012\t\u0000q\u00ac\u0001\u0000\u0000\u0000rs\u0005$\u0000\u0000"+
		"st\u0005\r\u0000\u0000tu\u0003\u0014\n\u0000uv\u0005\u000e\u0000\u0000"+
		"vy\u0003\u0012\t\u0000wx\u0005%\u0000\u0000xz\u0003\u0012\t\u0000yw\u0001"+
		"\u0000\u0000\u0000yz\u0001\u0000\u0000\u0000z\u00ac\u0001\u0000\u0000"+
		"\u0000{|\u0005)\u0000\u0000|}\u0005\r\u0000\u0000}~\u0003\u0014\n\u0000"+
		"~\u007f\u0005\u000e\u0000\u0000\u007f\u0080\u0003\u0012\t\u0000\u0080"+
		"\u00ac\u0001\u0000\u0000\u0000\u0081\u0082\u0005\'\u0000\u0000\u0082\u0083"+
		"\u0003\u0016\u000b\u0000\u0083\u0084\u0005\u0017\u0000\u0000\u0084\u00ac"+
		"\u0001\u0000\u0000\u0000\u0085\u0086\u0005&\u0000\u0000\u0086\u0087\u0003"+
		"\u0014\n\u0000\u0087\u0088\u0005\u0017\u0000\u0000\u0088\u00ac\u0001\u0000"+
		"\u0000\u0000\u0089\u008a\u0005\u001d\u0000\u0000\u008a\u008f\u0003\u0014"+
		"\n\u0000\u008b\u008c\u0005\u0016\u0000\u0000\u008c\u008e\u0003\u0014\n"+
		"\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008e\u0091\u0001\u0000\u0000"+
		"\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000"+
		"\u0000\u0090\u0092\u0001\u0000\u0000\u0000\u0091\u008f\u0001\u0000\u0000"+
		"\u0000\u0092\u0093\u0005\u0017\u0000\u0000\u0093\u00ac\u0001\u0000\u0000"+
		"\u0000\u0094\u0095\u0003\u0016\u000b\u0000\u0095\u0096\u0005\u0001\u0000"+
		"\u0000\u0096\u0097\u0003\u0014\n\u0000\u0097\u0098\u0005\u0017\u0000\u0000"+
		"\u0098\u00ac\u0001\u0000\u0000\u0000\u0099\u009a\u0005-\u0000\u0000\u009a"+
		"\u009c\u0005\r\u0000\u0000\u009b\u009d\u0003\u0018\f\u0000\u009c\u009b"+
		"\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009e"+
		"\u0001\u0000\u0000\u0000\u009e\u009f\u0005\u000e\u0000\u0000\u009f\u00a0"+
		"\u0005\n\u0000\u0000\u00a0\u00a5\u0003\u0016\u000b\u0000\u00a1\u00a2\u0005"+
		"\u0016\u0000\u0000\u00a2\u00a4\u0003\u0016\u000b\u0000\u00a3\u00a1\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a7\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001"+
		"\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a8\u0001"+
		"\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005"+
		"\t\u0000\u0000\u00a9\u00aa\u0005\u0017\u0000\u0000\u00aa\u00ac\u0001\u0000"+
		"\u0000\u0000\u00abd\u0001\u0000\u0000\u0000\u00abl\u0001\u0000\u0000\u0000"+
		"\u00abr\u0001\u0000\u0000\u0000\u00ab{\u0001\u0000\u0000\u0000\u00ab\u0081"+
		"\u0001\u0000\u0000\u0000\u00ab\u0085\u0001\u0000\u0000\u0000\u00ab\u0089"+
		"\u0001\u0000\u0000\u0000\u00ab\u0094\u0001\u0000\u0000\u0000\u00ab\u0099"+
		"\u0001\u0000\u0000\u0000\u00ac\u0013\u0001\u0000\u0000\u0000\u00ad\u00ae"+
		"\u0006\n\uffff\uffff\u0000\u00ae\u00af\u0005\u000b\u0000\u0000\u00af\u00d2"+
		"\u0003\u0014\n\f\u00b0\u00b1\u0005\u0005\u0000\u0000\u00b1\u00d2\u0003"+
		"\u0014\n\u000b\u00b2\u00d2\u0005\"\u0000\u0000\u00b3\u00d2\u0005#\u0000"+
		"\u0000\u00b4\u00d2\u0005\u0018\u0000\u0000\u00b5\u00d2\u0005.\u0000\u0000"+
		"\u00b6\u00d2\u0005/\u0000\u0000\u00b7\u00d2\u00050\u0000\u0000\u00b8\u00d2"+
		"\u0003\u0016\u000b\u0000\u00b9\u00ba\u0005\r\u0000\u0000\u00ba\u00bb\u0003"+
		"\u0014\n\u0000\u00bb\u00bc\u0005\u000e\u0000\u0000\u00bc\u00d2\u0001\u0000"+
		"\u0000\u0000\u00bd\u00be\u0005(\u0000\u0000\u00be\u00c3\u0003\u000e\u0007"+
		"\u0000\u00bf\u00c0\u0005\u000f\u0000\u0000\u00c0\u00c1\u0003\u0014\n\u0000"+
		"\u00c1\u00c2\u0005\u0010\u0000\u0000\u00c2\u00c4\u0001\u0000\u0000\u0000"+
		"\u00c3\u00bf\u0001\u0000\u0000\u0000\u00c3\u00c4\u0001\u0000\u0000\u0000"+
		"\u00c4\u00d2\u0001\u0000\u0000\u0000\u00c5\u00c6\u0005-\u0000\u0000\u00c6"+
		"\u00c8\u0005\r\u0000\u0000\u00c7\u00c9\u0003\u0018\f\u0000\u00c8\u00c7"+
		"\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000\u00c9\u00ca"+
		"\u0001\u0000\u0000\u0000\u00ca\u00cf\u0005\u000e\u0000\u0000\u00cb\u00cc"+
		"\u0005\u000f\u0000\u0000\u00cc\u00cd\u0003\u0014\n\u0000\u00cd\u00ce\u0005"+
		"\u0010\u0000\u0000\u00ce\u00d0\u0001\u0000\u0000\u0000\u00cf\u00cb\u0001"+
		"\u0000\u0000\u0000\u00cf\u00d0\u0001\u0000\u0000\u0000\u00d0\u00d2\u0001"+
		"\u0000\u0000\u0000\u00d1\u00ad\u0001\u0000\u0000\u0000\u00d1\u00b0\u0001"+
		"\u0000\u0000\u0000\u00d1\u00b2\u0001\u0000\u0000\u0000\u00d1\u00b3\u0001"+
		"\u0000\u0000\u0000\u00d1\u00b4\u0001\u0000\u0000\u0000\u00d1\u00b5\u0001"+
		"\u0000\u0000\u0000\u00d1\u00b6\u0001\u0000\u0000\u0000\u00d1\u00b7\u0001"+
		"\u0000\u0000\u0000\u00d1\u00b8\u0001\u0000\u0000\u0000\u00d1\u00b9\u0001"+
		"\u0000\u0000\u0000\u00d1\u00bd\u0001\u0000\u0000\u0000\u00d1\u00c5\u0001"+
		"\u0000\u0000\u0000\u00d2\u00d8\u0001\u0000\u0000\u0000\u00d3\u00d4\n\r"+
		"\u0000\u0000\u00d4\u00d5\u0007\u0001\u0000\u0000\u00d5\u00d7\u0003\u0014"+
		"\n\u000e\u00d6\u00d3\u0001\u0000\u0000\u0000\u00d7\u00da\u0001\u0000\u0000"+
		"\u0000\u00d8\u00d6\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000"+
		"\u0000\u00d9\u0015\u0001\u0000\u0000\u0000\u00da\u00d8\u0001\u0000\u0000"+
		"\u0000\u00db\u00dc\u0006\u000b\uffff\uffff\u0000\u00dc\u00dd\u0005-\u0000"+
		"\u0000\u00dd\u00e8\u0001\u0000\u0000\u0000\u00de\u00df\n\u0002\u0000\u0000"+
		"\u00df\u00e0\u0005\u000f\u0000\u0000\u00e0\u00e1\u0003\u0014\n\u0000\u00e1"+
		"\u00e2\u0005\u0010\u0000\u0000\u00e2\u00e7\u0001\u0000\u0000\u0000\u00e3"+
		"\u00e4\n\u0001\u0000\u0000\u00e4\u00e5\u0005\u0013\u0000\u0000\u00e5\u00e7"+
		"\u0005-\u0000\u0000\u00e6\u00de\u0001\u0000\u0000\u0000\u00e6\u00e3\u0001"+
		"\u0000\u0000\u0000\u00e7\u00ea\u0001\u0000\u0000\u0000\u00e8\u00e6\u0001"+
		"\u0000\u0000\u0000\u00e8\u00e9\u0001\u0000\u0000\u0000\u00e9\u0017\u0001"+
		"\u0000\u0000\u0000\u00ea\u00e8\u0001\u0000\u0000\u0000\u00eb\u00f0\u0003"+
		"\u0014\n\u0000\u00ec\u00ed\u0005\u0016\u0000\u0000\u00ed\u00ef\u0003\u0014"+
		"\n\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ef\u00f2\u0001\u0000\u0000"+
		"\u0000\u00f0\u00ee\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000\u0000"+
		"\u0000\u00f1\u0019\u0001\u0000\u0000\u0000\u00f2\u00f0\u0001\u0000\u0000"+
		"\u0000\u0017\u001d!)5>AGP_hy\u008f\u009c\u00a5\u00ab\u00c3\u00c8\u00cf"+
		"\u00d1\u00d8\u00e6\u00e8\u00f0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}