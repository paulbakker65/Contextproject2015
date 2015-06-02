// Generated from AnalysisLang.g4 by ANTLR 4.5
package scriptlang;

import scriptlang.extra.*;
import enums.*;
import table.value.*;
import java.util.*;
import java.text.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AnalysisLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, ID=20, EQ=21, NEQ=22, GEQ=23, G=24, LEQ=25, L=26, 
		MULTIPLY=27, DIVIDE=28, PLUS=29, MINUS=30, MODULO=31, NUMBER=32, WS=33, 
		STRING=34, CHAR=35;
	public static final int
		RULE_parse = 0, RULE_operation = 1, RULE_between_operation = 2, RULE_chunk_operation = 3, 
		RULE_code_operation = 4, RULE_compare_operation = 5, RULE_compute_operation = 6, 
		RULE_connect_operation = 7, RULE_constraint_operation = 8, RULE_convert_operation = 9, 
		RULE_lsa_operation = 10, RULE_between_param = 11, RULE_chunk_param = 12, 
		RULE_code_param = 13, RULE_compare_param = 14, RULE_compute_param = 15, 
		RULE_connect_param = 16, RULE_constraint_param = 17, RULE_convert_param = 18, 
		RULE_lsa_param = 19, RULE_field = 20, RULE_number = 21, RULE_compare_operator = 22, 
		RULE_calc_operator = 23, RULE_formula = 24, RULE_condition = 25, RULE_range = 26, 
		RULE_value = 27, RULE_date = 28, RULE_text = 29;
	public static final String[] ruleNames = {
		"parse", "operation", "between_operation", "chunk_operation", "code_operation", 
		"compare_operation", "compute_operation", "connect_operation", "constraint_operation", 
		"convert_operation", "lsa_operation", "between_param", "chunk_param", 
		"code_param", "compare_param", "compute_param", "connect_param", "constraint_param", 
		"convert_param", "lsa_param", "field", "number", "compare_operator", "calc_operator", 
		"formula", "condition", "range", "value", "date", "text"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'BETWEEN'", "'CHUNK'", "'CODE'", "'COMPARE'", "'COMPUTE'", "'CONNECT'", 
		"'CONSTRAINT'", "'CONVERT'", "'LSA'", "'USING'", "'ON'", "'<-'", "'TO'", 
		"'['", "'].['", "']'", "'AND'", "'DATE('", "')'", null, "'=='", "'!='", 
		"'>='", "'>'", "'<='", "'<'", "'*'", "'/'", "'+'", "'-'", "'%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, "ID", "EQ", "NEQ", "GEQ", 
		"G", "LEQ", "L", "MULTIPLY", "DIVIDE", "PLUS", "MINUS", "MODULO", "NUMBER", 
		"WS", "STRING", "CHAR"
	};
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
	public String getGrammarFileName() { return "AnalysisLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AnalysisLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ParseContext extends ParserRuleContext {
		public List<OperationContext> operation() {
			return getRuleContexts(OperationContext.class);
		}
		public OperationContext operation(int i) {
			return getRuleContext(OperationContext.class,i);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitParse(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8))) != 0)) {
				{
				{
				setState(60);
				operation();
				}
				}
				setState(65);
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

	public static class OperationContext extends ParserRuleContext {
		public Between_operationContext between_operation() {
			return getRuleContext(Between_operationContext.class,0);
		}
		public Chunk_operationContext chunk_operation() {
			return getRuleContext(Chunk_operationContext.class,0);
		}
		public Code_operationContext code_operation() {
			return getRuleContext(Code_operationContext.class,0);
		}
		public Connect_operationContext connect_operation() {
			return getRuleContext(Connect_operationContext.class,0);
		}
		public Compare_operationContext compare_operation() {
			return getRuleContext(Compare_operationContext.class,0);
		}
		public Constraint_operationContext constraint_operation() {
			return getRuleContext(Constraint_operationContext.class,0);
		}
		public Convert_operationContext convert_operation() {
			return getRuleContext(Convert_operationContext.class,0);
		}
		public Compute_operationContext compute_operation() {
			return getRuleContext(Compute_operationContext.class,0);
		}
		public Lsa_operationContext lsa_operation() {
			return getRuleContext(Lsa_operationContext.class,0);
		}
		public OperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitOperation(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_operation);
		try {
			setState(75);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				between_operation();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				chunk_operation();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				code_operation();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(69);
				connect_operation();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 5);
				{
				setState(70);
				compare_operation();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 6);
				{
				setState(71);
				constraint_operation();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 7);
				{
				setState(72);
				convert_operation();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 8);
				{
				setState(73);
				compute_operation();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 9);
				{
				setState(74);
				lsa_operation();
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

	public static class Between_operationContext extends ParserRuleContext {
		public Between_paramContext param;
		public Between_paramContext between_param() {
			return getRuleContext(Between_paramContext.class,0);
		}
		public Between_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_between_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterBetween_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitBetween_operation(this);
		}
	}

	public final Between_operationContext between_operation() throws RecognitionException {
		Between_operationContext _localctx = new Between_operationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_between_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(T__0);
			setState(78);
			((Between_operationContext)_localctx).param = between_param();
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

	public static class Chunk_operationContext extends ParserRuleContext {
		public Chunk_paramContext param;
		public Chunk_paramContext chunk_param() {
			return getRuleContext(Chunk_paramContext.class,0);
		}
		public Chunk_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chunk_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterChunk_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitChunk_operation(this);
		}
	}

	public final Chunk_operationContext chunk_operation() throws RecognitionException {
		Chunk_operationContext _localctx = new Chunk_operationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_chunk_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__1);
			setState(81);
			((Chunk_operationContext)_localctx).param = chunk_param();
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

	public static class Code_operationContext extends ParserRuleContext {
		public Code_paramContext param;
		public Code_paramContext code_param() {
			return getRuleContext(Code_paramContext.class,0);
		}
		public Code_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterCode_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitCode_operation(this);
		}
	}

	public final Code_operationContext code_operation() throws RecognitionException {
		Code_operationContext _localctx = new Code_operationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_code_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(T__2);
			setState(84);
			((Code_operationContext)_localctx).param = code_param();
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

	public static class Compare_operationContext extends ParserRuleContext {
		public Compare_paramContext param;
		public Compare_paramContext compare_param() {
			return getRuleContext(Compare_paramContext.class,0);
		}
		public Compare_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compare_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterCompare_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitCompare_operation(this);
		}
	}

	public final Compare_operationContext compare_operation() throws RecognitionException {
		Compare_operationContext _localctx = new Compare_operationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_compare_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(T__3);
			setState(87);
			((Compare_operationContext)_localctx).param = compare_param();
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

	public static class Compute_operationContext extends ParserRuleContext {
		public Compute_paramContext param;
		public Compute_paramContext compute_param() {
			return getRuleContext(Compute_paramContext.class,0);
		}
		public Compute_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compute_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterCompute_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitCompute_operation(this);
		}
	}

	public final Compute_operationContext compute_operation() throws RecognitionException {
		Compute_operationContext _localctx = new Compute_operationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_compute_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(T__4);
			setState(90);
			((Compute_operationContext)_localctx).param = compute_param();
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

	public static class Connect_operationContext extends ParserRuleContext {
		public Connect_paramContext param;
		public Connect_paramContext connect_param() {
			return getRuleContext(Connect_paramContext.class,0);
		}
		public Connect_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_connect_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterConnect_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitConnect_operation(this);
		}
	}

	public final Connect_operationContext connect_operation() throws RecognitionException {
		Connect_operationContext _localctx = new Connect_operationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_connect_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(T__5);
			setState(93);
			((Connect_operationContext)_localctx).param = connect_param();
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

	public static class Constraint_operationContext extends ParserRuleContext {
		public Constraint_paramContext param;
		public Constraint_paramContext constraint_param() {
			return getRuleContext(Constraint_paramContext.class,0);
		}
		public Constraint_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterConstraint_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitConstraint_operation(this);
		}
	}

	public final Constraint_operationContext constraint_operation() throws RecognitionException {
		Constraint_operationContext _localctx = new Constraint_operationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_constraint_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(T__6);
			setState(96);
			((Constraint_operationContext)_localctx).param = constraint_param();
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

	public static class Convert_operationContext extends ParserRuleContext {
		public Convert_paramContext param;
		public Convert_paramContext convert_param() {
			return getRuleContext(Convert_paramContext.class,0);
		}
		public Convert_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_convert_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterConvert_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitConvert_operation(this);
		}
	}

	public final Convert_operationContext convert_operation() throws RecognitionException {
		Convert_operationContext _localctx = new Convert_operationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_convert_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(T__7);
			setState(99);
			((Convert_operationContext)_localctx).param = convert_param();
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

	public static class Lsa_operationContext extends ParserRuleContext {
		public Lsa_paramContext param;
		public Lsa_paramContext lsa_param() {
			return getRuleContext(Lsa_paramContext.class,0);
		}
		public Lsa_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lsa_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterLsa_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitLsa_operation(this);
		}
	}

	public final Lsa_operationContext lsa_operation() throws RecognitionException {
		Lsa_operationContext _localctx = new Lsa_operationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_lsa_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(T__8);
			setState(102);
			((Lsa_operationContext)_localctx).param = lsa_param();
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

	public static class Between_paramContext extends ParserRuleContext {
		public FieldContext eventcol;
		public FieldContext datecol1;
		public FieldContext datecol2;
		public ValueContext value1;
		public ValueContext value2;
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public Between_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_between_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterBetween_param(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitBetween_param(this);
		}
	}

	public final Between_paramContext between_param() throws RecognitionException {
		Between_paramContext _localctx = new Between_paramContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_between_param);
		try {
			setState(115);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				((Between_paramContext)_localctx).eventcol = field();
				setState(105);
				((Between_paramContext)_localctx).datecol1 = field();
				setState(106);
				((Between_paramContext)_localctx).datecol2 = field();
				setState(107);
				((Between_paramContext)_localctx).value1 = value();
				setState(108);
				((Between_paramContext)_localctx).value2 = value();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(110);
				((Between_paramContext)_localctx).eventcol = field();
				setState(111);
				((Between_paramContext)_localctx).datecol1 = field();
				setState(112);
				((Between_paramContext)_localctx).value1 = value();
				setState(113);
				((Between_paramContext)_localctx).value2 = value();
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

	public static class Chunk_paramContext extends ParserRuleContext {
		public FieldContext fieldparam;
		public RangeContext rangeparam;
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public Chunk_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chunk_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterChunk_param(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitChunk_param(this);
		}
	}

	public final Chunk_paramContext chunk_param() throws RecognitionException {
		Chunk_paramContext _localctx = new Chunk_paramContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_chunk_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			((Chunk_paramContext)_localctx).fieldparam = field();
			setState(118);
			match(T__9);
			setState(119);
			((Chunk_paramContext)_localctx).rangeparam = range();
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

	public static class Code_paramContext extends ParserRuleContext {
		public FieldContext fieldparam;
		public ConditionContext conditionparam;
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public Code_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterCode_param(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitCode_param(this);
		}
	}

	public final Code_paramContext code_param() throws RecognitionException {
		Code_paramContext _localctx = new Code_paramContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_code_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			((Code_paramContext)_localctx).fieldparam = field();
			setState(122);
			match(T__10);
			setState(123);
			((Code_paramContext)_localctx).conditionparam = condition();
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

	public static class Compare_paramContext extends ParserRuleContext {
		public FieldContext fieldparam;
		public Compare_operatorContext opparam;
		public FieldContext anotherfieldparam;
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public Compare_operatorContext compare_operator() {
			return getRuleContext(Compare_operatorContext.class,0);
		}
		public Compare_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compare_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterCompare_param(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitCompare_param(this);
		}
	}

	public final Compare_paramContext compare_param() throws RecognitionException {
		Compare_paramContext _localctx = new Compare_paramContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_compare_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			((Compare_paramContext)_localctx).fieldparam = field();
			setState(126);
			((Compare_paramContext)_localctx).opparam = compare_operator();
			setState(127);
			((Compare_paramContext)_localctx).anotherfieldparam = field();
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

	public static class Compute_paramContext extends ParserRuleContext {
		public FieldContext fieldparam;
		public FormulaContext formulaparam;
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public Compute_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compute_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterCompute_param(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitCompute_param(this);
		}
	}

	public final Compute_paramContext compute_param() throws RecognitionException {
		Compute_paramContext _localctx = new Compute_paramContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_compute_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			((Compute_paramContext)_localctx).fieldparam = field();
			setState(130);
			match(T__11);
			setState(131);
			((Compute_paramContext)_localctx).formulaparam = formula();
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

	public static class Connect_paramContext extends ParserRuleContext {
		public FieldContext fieldparam;
		public FieldContext anotherfieldparam;
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public Connect_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_connect_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterConnect_param(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitConnect_param(this);
		}
	}

	public final Connect_paramContext connect_param() throws RecognitionException {
		Connect_paramContext _localctx = new Connect_paramContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_connect_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			((Connect_paramContext)_localctx).fieldparam = field();
			setState(134);
			match(T__12);
			setState(135);
			((Connect_paramContext)_localctx).anotherfieldparam = field();
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

	public static class Constraint_paramContext extends ParserRuleContext {
		public FieldContext fieldparam;
		public Compare_operatorContext opparam;
		public FieldContext anotherfieldparam;
		public ValueContext valueparam;
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public Compare_operatorContext compare_operator() {
			return getRuleContext(Compare_operatorContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public Constraint_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterConstraint_param(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitConstraint_param(this);
		}
	}

	public final Constraint_paramContext constraint_param() throws RecognitionException {
		Constraint_paramContext _localctx = new Constraint_paramContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_constraint_param);
		try {
			setState(145);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				((Constraint_paramContext)_localctx).fieldparam = field();
				setState(138);
				((Constraint_paramContext)_localctx).opparam = compare_operator();
				setState(139);
				((Constraint_paramContext)_localctx).anotherfieldparam = field();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				((Constraint_paramContext)_localctx).fieldparam = field();
				setState(142);
				((Constraint_paramContext)_localctx).opparam = compare_operator();
				setState(143);
				((Constraint_paramContext)_localctx).valueparam = value();
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

	public static class Convert_paramContext extends ParserRuleContext {
		public FieldContext fieldparam;
		public FormulaContext formulaparam;
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public Convert_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_convert_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterConvert_param(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitConvert_param(this);
		}
	}

	public final Convert_paramContext convert_param() throws RecognitionException {
		Convert_paramContext _localctx = new Convert_paramContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_convert_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			((Convert_paramContext)_localctx).fieldparam = field();
			setState(148);
			match(T__12);
			setState(149);
			((Convert_paramContext)_localctx).formulaparam = formula();
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

	public static class Lsa_paramContext extends ParserRuleContext {
		public FieldContext fieldparam;
		public NumberContext from;
		public NumberContext to;
		public ValueContext key;
		public ValueContext target;
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public Lsa_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lsa_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterLsa_param(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitLsa_param(this);
		}
	}

	public final Lsa_paramContext lsa_param() throws RecognitionException {
		Lsa_paramContext _localctx = new Lsa_paramContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_lsa_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			((Lsa_paramContext)_localctx).fieldparam = field();
			setState(152);
			((Lsa_paramContext)_localctx).from = number();
			setState(153);
			((Lsa_paramContext)_localctx).to = number();
			setState(154);
			((Lsa_paramContext)_localctx).key = value();
			setState(155);
			((Lsa_paramContext)_localctx).target = value();
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

	public static class FieldContext extends ParserRuleContext {
		public String tablename;
		public String fieldname;
		public Token tablenameparam;
		public Token fieldnameparam;
		public List<TerminalNode> ID() { return getTokens(AnalysisLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(AnalysisLangParser.ID, i);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitField(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(T__13);
			setState(158);
			((FieldContext)_localctx).tablenameparam = match(ID);
			setState(159);
			match(T__14);
			setState(160);
			((FieldContext)_localctx).fieldnameparam = match(ID);
			setState(161);
			match(T__15);
			 ((FieldContext)_localctx).fieldname =  (((FieldContext)_localctx).fieldnameparam!=null?((FieldContext)_localctx).fieldnameparam.getText():null);
			                                                      ((FieldContext)_localctx).tablename =  (((FieldContext)_localctx).tablenameparam!=null?((FieldContext)_localctx).tablenameparam.getText():null); 
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

	public static class NumberContext extends ParserRuleContext {
		public Value val;
		public Token numparam;
		public TerminalNode NUMBER() { return getToken(AnalysisLangParser.NUMBER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			((NumberContext)_localctx).numparam = match(NUMBER);
			 ((NumberContext)_localctx).val =  new NumberValue((((NumberContext)_localctx).numparam!=null?Integer.valueOf(((NumberContext)_localctx).numparam.getText()):0)); 
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

	public static class Compare_operatorContext extends ParserRuleContext {
		public CompareOperator op;
		public Token opparam;
		public TerminalNode EQ() { return getToken(AnalysisLangParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(AnalysisLangParser.NEQ, 0); }
		public TerminalNode GEQ() { return getToken(AnalysisLangParser.GEQ, 0); }
		public TerminalNode G() { return getToken(AnalysisLangParser.G, 0); }
		public TerminalNode LEQ() { return getToken(AnalysisLangParser.LEQ, 0); }
		public TerminalNode L() { return getToken(AnalysisLangParser.L, 0); }
		public Compare_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compare_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterCompare_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitCompare_operator(this);
		}
	}

	public final Compare_operatorContext compare_operator() throws RecognitionException {
		Compare_operatorContext _localctx = new Compare_operatorContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_compare_operator);
		try {
			setState(179);
			switch (_input.LA(1)) {
			case EQ:
				enterOuterAlt(_localctx, 1);
				{
				setState(167);
				((Compare_operatorContext)_localctx).opparam = match(EQ);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.EQ;   
				}
				break;
			case NEQ:
				enterOuterAlt(_localctx, 2);
				{
				setState(169);
				((Compare_operatorContext)_localctx).opparam = match(NEQ);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.NEQ;  
				}
				break;
			case GEQ:
				enterOuterAlt(_localctx, 3);
				{
				setState(171);
				((Compare_operatorContext)_localctx).opparam = match(GEQ);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.GEQ;  
				}
				break;
			case G:
				enterOuterAlt(_localctx, 4);
				{
				setState(173);
				((Compare_operatorContext)_localctx).opparam = match(G);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.G;    
				}
				break;
			case LEQ:
				enterOuterAlt(_localctx, 5);
				{
				setState(175);
				((Compare_operatorContext)_localctx).opparam = match(LEQ);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.LEQ;  
				}
				break;
			case L:
				enterOuterAlt(_localctx, 6);
				{
				setState(177);
				((Compare_operatorContext)_localctx).opparam = match(L);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.L;    
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

	public static class Calc_operatorContext extends ParserRuleContext {
		public CalcOperator op;
		public Token opparam;
		public Token opparma;
		public TerminalNode MULTIPLY() { return getToken(AnalysisLangParser.MULTIPLY, 0); }
		public TerminalNode DIVIDE() { return getToken(AnalysisLangParser.DIVIDE, 0); }
		public TerminalNode PLUS() { return getToken(AnalysisLangParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(AnalysisLangParser.MINUS, 0); }
		public TerminalNode MODULO() { return getToken(AnalysisLangParser.MODULO, 0); }
		public Calc_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calc_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterCalc_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitCalc_operator(this);
		}
	}

	public final Calc_operatorContext calc_operator() throws RecognitionException {
		Calc_operatorContext _localctx = new Calc_operatorContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_calc_operator);
		try {
			setState(191);
			switch (_input.LA(1)) {
			case MULTIPLY:
				enterOuterAlt(_localctx, 1);
				{
				setState(181);
				((Calc_operatorContext)_localctx).opparam = match(MULTIPLY);
				 ((Calc_operatorContext)_localctx).op =  CalcOperator.MULTIPLY;
				}
				break;
			case DIVIDE:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
				((Calc_operatorContext)_localctx).opparam = match(DIVIDE);
				 ((Calc_operatorContext)_localctx).op =  CalcOperator.DIVIDE;  
				}
				break;
			case PLUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(185);
				((Calc_operatorContext)_localctx).opparam = match(PLUS);
				 ((Calc_operatorContext)_localctx).op =  CalcOperator.PLUS;    
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 4);
				{
				setState(187);
				((Calc_operatorContext)_localctx).opparma = match(MINUS);
				 ((Calc_operatorContext)_localctx).op =  CalcOperator.MINUS;   
				}
				break;
			case MODULO:
				enterOuterAlt(_localctx, 5);
				{
				setState(189);
				((Calc_operatorContext)_localctx).opparam = match(MODULO);
				 ((Calc_operatorContext)_localctx).op =  CalcOperator.MODULO;  
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

	public static class FormulaContext extends ParserRuleContext {
		public Formula form;
		public FieldContext fieldparam;
		public Calc_operatorContext opparam;
		public FieldContext anotherfieldparam;
		public NumberContext valueparam;
		public FormulaContext formulaparam;
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public Calc_operatorContext calc_operator() {
			return getRuleContext(Calc_operatorContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitFormula(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_formula);
		try {
			setState(208);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(193);
				((FormulaContext)_localctx).fieldparam = field();
				setState(194);
				((FormulaContext)_localctx).opparam = calc_operator();
				setState(195);
				((FormulaContext)_localctx).anotherfieldparam = field();
				 ((FormulaContext)_localctx).form =  new Formula(((FormulaContext)_localctx).fieldparam.fieldname, ((FormulaContext)_localctx).opparam.op, ((FormulaContext)_localctx).anotherfieldparam.fieldname); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(198);
				((FormulaContext)_localctx).fieldparam = field();
				setState(199);
				((FormulaContext)_localctx).opparam = calc_operator();
				setState(200);
				((FormulaContext)_localctx).valueparam = number();
				 ((FormulaContext)_localctx).form =  new Formula(((FormulaContext)_localctx).fieldparam.fieldname, ((FormulaContext)_localctx).opparam.op, ((FormulaContext)_localctx).valueparam.val); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(203);
				((FormulaContext)_localctx).fieldparam = field();
				setState(204);
				((FormulaContext)_localctx).opparam = calc_operator();
				setState(205);
				((FormulaContext)_localctx).formulaparam = formula();
				 ((FormulaContext)_localctx).form =  new Formula(((FormulaContext)_localctx).fieldparam.fieldname, ((FormulaContext)_localctx).opparam.op, ((FormulaContext)_localctx).formulaparam.form); 
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

	public static class ConditionContext extends ParserRuleContext {
		public Condition cond;
		public Compare_operatorContext opparam;
		public ValueContext valueparam;
		public ConditionContext anothercond;
		public Compare_operatorContext compare_operator() {
			return getRuleContext(Compare_operatorContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_condition);
		try {
			setState(220);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(210);
				((ConditionContext)_localctx).opparam = compare_operator();
				setState(211);
				((ConditionContext)_localctx).valueparam = value();
				 ((ConditionContext)_localctx).cond =  new Condition(((ConditionContext)_localctx).opparam.op, ((ConditionContext)_localctx).valueparam.val); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(214);
				((ConditionContext)_localctx).opparam = compare_operator();
				setState(215);
				((ConditionContext)_localctx).valueparam = value();
				setState(216);
				match(T__16);
				setState(217);
				((ConditionContext)_localctx).anothercond = condition();
				 ((ConditionContext)_localctx).cond =  new Condition(((ConditionContext)_localctx).opparam.op, ((ConditionContext)_localctx).valueparam.val); 
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

	public static class RangeContext extends ParserRuleContext {
		public ValueContext g;
		public ValueContext l;
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public RangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitRange(this);
		}
	}

	public final RangeContext range() throws RecognitionException {
		RangeContext _localctx = new RangeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(G);
			setState(223);
			((RangeContext)_localctx).g = value();
			setState(224);
			match(T__16);
			setState(225);
			match(L);
			setState(226);
			((RangeContext)_localctx).l = value();
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

	public static class ValueContext extends ParserRuleContext {
		public Value val;
		public DateContext dataparam;
		public NumberContext numparam;
		public TextContext stringparam;
		public DateContext date() {
			return getRuleContext(DateContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_value);
		try {
			setState(237);
			switch (_input.LA(1)) {
			case T__17:
				enterOuterAlt(_localctx, 1);
				{
				setState(228);
				((ValueContext)_localctx).dataparam = date();
				 ((ValueContext)_localctx).val =  ((ValueContext)_localctx).dataparam.val; 
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(231);
				((ValueContext)_localctx).numparam = number();
				 ((ValueContext)_localctx).val =  ((ValueContext)_localctx).numparam.val;  
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(234);
				((ValueContext)_localctx).stringparam = text();
				 ((ValueContext)_localctx).val =  ((ValueContext)_localctx).stringparam.val; 
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

	public static class DateContext extends ParserRuleContext {
		public Value val;
		public Token yearparam;
		public Token monthparam;
		public Token dayparam;
		public List<TerminalNode> NUMBER() { return getTokens(AnalysisLangParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(AnalysisLangParser.NUMBER, i);
		}
		public DateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_date; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterDate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitDate(this);
		}
	}

	public final DateContext date() throws RecognitionException {
		DateContext _localctx = new DateContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_date);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			match(T__17);
			setState(240);
			((DateContext)_localctx).yearparam = match(NUMBER);
			setState(241);
			match(MINUS);
			setState(242);
			((DateContext)_localctx).monthparam = match(NUMBER);
			setState(243);
			match(MINUS);
			setState(244);
			((DateContext)_localctx).dayparam = match(NUMBER);
			setState(245);
			match(T__18);
			 
			                                           GregorianCalendar calObj = new GregorianCalendar();
			                                           calObj.set((((DateContext)_localctx).yearparam!=null?Integer.valueOf(((DateContext)_localctx).yearparam.getText()):0), (((DateContext)_localctx).monthparam!=null?Integer.valueOf(((DateContext)_localctx).monthparam.getText()):0), (((DateContext)_localctx).dayparam!=null?Integer.valueOf(((DateContext)_localctx).dayparam.getText()):0)); 
			                                           ((DateContext)_localctx).val =  new DateValue(calObj); 
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

	public static class TextContext extends ParserRuleContext {
		public Value val;
		public Token stringparam;
		public TerminalNode STRING() { return getToken(AnalysisLangParser.STRING, 0); }
		public TextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).enterText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnalysisLangListener ) ((AnalysisLangListener)listener).exitText(this);
		}
	}

	public final TextContext text() throws RecognitionException {
		TextContext _localctx = new TextContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_text);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			((TextContext)_localctx).stringparam = match(STRING);
			 String text = (((TextContext)_localctx).stringparam!=null?((TextContext)_localctx).stringparam.getText():null);
			                          text = text.substring(1, text.length()-1);
			                          ((TextContext)_localctx).val =  new StringValue(text);

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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3%\u00fe\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\7\2@"+
		"\n\2\f\2\16\2C\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3N\n\3\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\5\rv\n\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\5\23\u0094\n\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u00b6\n\30"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u00c2\n\31\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\5\32\u00d3\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33"+
		"\u00df\n\33\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\5\35\u00f0\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\37\3\37\3\37\3\37\2\2 \2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668:<\2\2\u00f8\2A\3\2\2\2\4M\3\2\2\2\6O\3\2\2\2\b"+
		"R\3\2\2\2\nU\3\2\2\2\fX\3\2\2\2\16[\3\2\2\2\20^\3\2\2\2\22a\3\2\2\2\24"+
		"d\3\2\2\2\26g\3\2\2\2\30u\3\2\2\2\32w\3\2\2\2\34{\3\2\2\2\36\177\3\2\2"+
		"\2 \u0083\3\2\2\2\"\u0087\3\2\2\2$\u0093\3\2\2\2&\u0095\3\2\2\2(\u0099"+
		"\3\2\2\2*\u009f\3\2\2\2,\u00a6\3\2\2\2.\u00b5\3\2\2\2\60\u00c1\3\2\2\2"+
		"\62\u00d2\3\2\2\2\64\u00de\3\2\2\2\66\u00e0\3\2\2\28\u00ef\3\2\2\2:\u00f1"+
		"\3\2\2\2<\u00fa\3\2\2\2>@\5\4\3\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2"+
		"\2\2B\3\3\2\2\2CA\3\2\2\2DN\5\6\4\2EN\5\b\5\2FN\5\n\6\2GN\5\20\t\2HN\5"+
		"\f\7\2IN\5\22\n\2JN\5\24\13\2KN\5\16\b\2LN\5\26\f\2MD\3\2\2\2ME\3\2\2"+
		"\2MF\3\2\2\2MG\3\2\2\2MH\3\2\2\2MI\3\2\2\2MJ\3\2\2\2MK\3\2\2\2ML\3\2\2"+
		"\2N\5\3\2\2\2OP\7\3\2\2PQ\5\30\r\2Q\7\3\2\2\2RS\7\4\2\2ST\5\32\16\2T\t"+
		"\3\2\2\2UV\7\5\2\2VW\5\34\17\2W\13\3\2\2\2XY\7\6\2\2YZ\5\36\20\2Z\r\3"+
		"\2\2\2[\\\7\7\2\2\\]\5 \21\2]\17\3\2\2\2^_\7\b\2\2_`\5\"\22\2`\21\3\2"+
		"\2\2ab\7\t\2\2bc\5$\23\2c\23\3\2\2\2de\7\n\2\2ef\5&\24\2f\25\3\2\2\2g"+
		"h\7\13\2\2hi\5(\25\2i\27\3\2\2\2jk\5*\26\2kl\5*\26\2lm\5*\26\2mn\58\35"+
		"\2no\58\35\2ov\3\2\2\2pq\5*\26\2qr\5*\26\2rs\58\35\2st\58\35\2tv\3\2\2"+
		"\2uj\3\2\2\2up\3\2\2\2v\31\3\2\2\2wx\5*\26\2xy\7\f\2\2yz\5\66\34\2z\33"+
		"\3\2\2\2{|\5*\26\2|}\7\r\2\2}~\5\64\33\2~\35\3\2\2\2\177\u0080\5*\26\2"+
		"\u0080\u0081\5.\30\2\u0081\u0082\5*\26\2\u0082\37\3\2\2\2\u0083\u0084"+
		"\5*\26\2\u0084\u0085\7\16\2\2\u0085\u0086\5\62\32\2\u0086!\3\2\2\2\u0087"+
		"\u0088\5*\26\2\u0088\u0089\7\17\2\2\u0089\u008a\5*\26\2\u008a#\3\2\2\2"+
		"\u008b\u008c\5*\26\2\u008c\u008d\5.\30\2\u008d\u008e\5*\26\2\u008e\u0094"+
		"\3\2\2\2\u008f\u0090\5*\26\2\u0090\u0091\5.\30\2\u0091\u0092\58\35\2\u0092"+
		"\u0094\3\2\2\2\u0093\u008b\3\2\2\2\u0093\u008f\3\2\2\2\u0094%\3\2\2\2"+
		"\u0095\u0096\5*\26\2\u0096\u0097\7\17\2\2\u0097\u0098\5\62\32\2\u0098"+
		"\'\3\2\2\2\u0099\u009a\5*\26\2\u009a\u009b\5,\27\2\u009b\u009c\5,\27\2"+
		"\u009c\u009d\58\35\2\u009d\u009e\58\35\2\u009e)\3\2\2\2\u009f\u00a0\7"+
		"\20\2\2\u00a0\u00a1\7\26\2\2\u00a1\u00a2\7\21\2\2\u00a2\u00a3\7\26\2\2"+
		"\u00a3\u00a4\7\22\2\2\u00a4\u00a5\b\26\1\2\u00a5+\3\2\2\2\u00a6\u00a7"+
		"\7\"\2\2\u00a7\u00a8\b\27\1\2\u00a8-\3\2\2\2\u00a9\u00aa\7\27\2\2\u00aa"+
		"\u00b6\b\30\1\2\u00ab\u00ac\7\30\2\2\u00ac\u00b6\b\30\1\2\u00ad\u00ae"+
		"\7\31\2\2\u00ae\u00b6\b\30\1\2\u00af\u00b0\7\32\2\2\u00b0\u00b6\b\30\1"+
		"\2\u00b1\u00b2\7\33\2\2\u00b2\u00b6\b\30\1\2\u00b3\u00b4\7\34\2\2\u00b4"+
		"\u00b6\b\30\1\2\u00b5\u00a9\3\2\2\2\u00b5\u00ab\3\2\2\2\u00b5\u00ad\3"+
		"\2\2\2\u00b5\u00af\3\2\2\2\u00b5\u00b1\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6"+
		"/\3\2\2\2\u00b7\u00b8\7\35\2\2\u00b8\u00c2\b\31\1\2\u00b9\u00ba\7\36\2"+
		"\2\u00ba\u00c2\b\31\1\2\u00bb\u00bc\7\37\2\2\u00bc\u00c2\b\31\1\2\u00bd"+
		"\u00be\7 \2\2\u00be\u00c2\b\31\1\2\u00bf\u00c0\7!\2\2\u00c0\u00c2\b\31"+
		"\1\2\u00c1\u00b7\3\2\2\2\u00c1\u00b9\3\2\2\2\u00c1\u00bb\3\2\2\2\u00c1"+
		"\u00bd\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2\61\3\2\2\2\u00c3\u00c4\5*\26"+
		"\2\u00c4\u00c5\5\60\31\2\u00c5\u00c6\5*\26\2\u00c6\u00c7\b\32\1\2\u00c7"+
		"\u00d3\3\2\2\2\u00c8\u00c9\5*\26\2\u00c9\u00ca\5\60\31\2\u00ca\u00cb\5"+
		",\27\2\u00cb\u00cc\b\32\1\2\u00cc\u00d3\3\2\2\2\u00cd\u00ce\5*\26\2\u00ce"+
		"\u00cf\5\60\31\2\u00cf\u00d0\5\62\32\2\u00d0\u00d1\b\32\1\2\u00d1\u00d3"+
		"\3\2\2\2\u00d2\u00c3\3\2\2\2\u00d2\u00c8\3\2\2\2\u00d2\u00cd\3\2\2\2\u00d3"+
		"\63\3\2\2\2\u00d4\u00d5\5.\30\2\u00d5\u00d6\58\35\2\u00d6\u00d7\b\33\1"+
		"\2\u00d7\u00df\3\2\2\2\u00d8\u00d9\5.\30\2\u00d9\u00da\58\35\2\u00da\u00db"+
		"\7\23\2\2\u00db\u00dc\5\64\33\2\u00dc\u00dd\b\33\1\2\u00dd\u00df\3\2\2"+
		"\2\u00de\u00d4\3\2\2\2\u00de\u00d8\3\2\2\2\u00df\65\3\2\2\2\u00e0\u00e1"+
		"\7\32\2\2\u00e1\u00e2\58\35\2\u00e2\u00e3\7\23\2\2\u00e3\u00e4\7\34\2"+
		"\2\u00e4\u00e5\58\35\2\u00e5\67\3\2\2\2\u00e6\u00e7\5:\36\2\u00e7\u00e8"+
		"\b\35\1\2\u00e8\u00f0\3\2\2\2\u00e9\u00ea\5,\27\2\u00ea\u00eb\b\35\1\2"+
		"\u00eb\u00f0\3\2\2\2\u00ec\u00ed\5<\37\2\u00ed\u00ee\b\35\1\2\u00ee\u00f0"+
		"\3\2\2\2\u00ef\u00e6\3\2\2\2\u00ef\u00e9\3\2\2\2\u00ef\u00ec\3\2\2\2\u00f0"+
		"9\3\2\2\2\u00f1\u00f2\7\24\2\2\u00f2\u00f3\7\"\2\2\u00f3\u00f4\7 \2\2"+
		"\u00f4\u00f5\7\"\2\2\u00f5\u00f6\7 \2\2\u00f6\u00f7\7\"\2\2\u00f7\u00f8"+
		"\7\25\2\2\u00f8\u00f9\b\36\1\2\u00f9;\3\2\2\2\u00fa\u00fb\7$\2\2\u00fb"+
		"\u00fc\b\37\1\2\u00fc=\3\2\2\2\13AMu\u0093\u00b5\u00c1\u00d2\u00de\u00ef";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}