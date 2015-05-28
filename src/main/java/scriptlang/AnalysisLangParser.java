// Generated from AnalysisLang.g4 by ANTLR 4.5
package scriptlang;

import scriptlang.extra.*;
import enums.*;
import table.value.*;
import operations.FilterOperation;
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
public class AnalysisLangParser extends org.antlr.v4.runtime.Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, ID=16, EQ=17, 
		NEQ=18, GEQ=19, G=20, LEQ=21, L=22, MULTIPLY=23, DIVIDE=24, PLUS=25, MINUS=26, 
		MODULO=27, NUMBER=28, WS=29, STRING=30, CHAR=31;
	public static final int
		RULE_parse = 0, RULE_operation = 1, RULE_chunk_operation = 2, RULE_code_operation = 3, 
		RULE_connect_operation = 4, RULE_compare_operation = 5, RULE_constraint_operation = 6, 
		RULE_convert_operation = 7, RULE_compute_operation = 8, RULE_chunk_param = 9, 
		RULE_code_param = 10, RULE_connect_param = 11, RULE_compare_param = 12, 
		RULE_constraint_param = 13, RULE_convert_param = 14, RULE_compute_param = 15, 
		RULE_field = 16, RULE_number = 17, RULE_compare_operator = 18, RULE_calc_operator = 19, 
		RULE_formula = 20, RULE_condition = 21, RULE_range = 22, RULE_value = 23, 
		RULE_text = 24;
	public static final String[] ruleNames = {
		"parse", "operation", "chunk_operation", "code_operation", "connect_operation", 
		"compare_operation", "constraint_operation", "convert_operation", "compute_operation", 
		"chunk_param", "code_param", "connect_param", "compare_param", "constraint_param", 
		"convert_param", "compute_param", "field", "number", "compare_operator", 
		"calc_operator", "formula", "condition", "range", "value", "text"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'CHUNK'", "'CODE'", "'CONNECT'", "'COMPARE'", "'CONSTRAINT'", "'CONVERT'", 
		"'COMPUTE'", "'USING'", "'ON'", "'TO'", "'<-'", "'['", "'].['", "']'", 
		"'AND'", null, "'=='", "'!='", "'>='", "'>'", "'<='", "'<'", "'*'", "'/'", 
		"'+'", "'-'", "'%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "ID", "EQ", "NEQ", "GEQ", "G", "LEQ", "L", "MULTIPLY", 
		"DIVIDE", "PLUS", "MINUS", "MODULO", "NUMBER", "WS", "STRING", "CHAR"
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
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6))) != 0)) {
				{
				{
				setState(50);
				operation();
				}
				}
				setState(55);
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
			setState(63);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				chunk_operation();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				code_operation();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(58);
				connect_operation();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 4);
				{
				setState(59);
				compare_operation();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 5);
				{
				setState(60);
				constraint_operation();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 6);
				{
				setState(61);
				convert_operation();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 7);
				{
				setState(62);
				compute_operation();
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
		enterRule(_localctx, 4, RULE_chunk_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(T__0);
			setState(66);
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
		enterRule(_localctx, 6, RULE_code_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(T__1);
			setState(69);
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
		enterRule(_localctx, 8, RULE_connect_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(T__2);
			setState(72);
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
			setState(74);
			match(T__3);
			setState(75);
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
		enterRule(_localctx, 12, RULE_constraint_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(T__4);
			setState(78);
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
		enterRule(_localctx, 14, RULE_convert_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__5);
			setState(81);
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
		enterRule(_localctx, 16, RULE_compute_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(T__6);
			setState(84);
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
		enterRule(_localctx, 18, RULE_chunk_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			((Chunk_paramContext)_localctx).fieldparam = field();
			setState(87);
			match(T__7);
			setState(88);
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
		enterRule(_localctx, 20, RULE_code_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			((Code_paramContext)_localctx).fieldparam = field();
			setState(91);
			match(T__8);
			setState(92);
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
		enterRule(_localctx, 22, RULE_connect_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			((Connect_paramContext)_localctx).fieldparam = field();
			setState(95);
			match(T__9);
			setState(96);
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
		enterRule(_localctx, 24, RULE_compare_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			((Compare_paramContext)_localctx).fieldparam = field();
			setState(99);
			((Compare_paramContext)_localctx).opparam = compare_operator();
			setState(100);
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
		enterRule(_localctx, 26, RULE_constraint_param);
		try {
			setState(110);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				((Constraint_paramContext)_localctx).fieldparam = field();
				setState(103);
				((Constraint_paramContext)_localctx).opparam = compare_operator();
				setState(104);
				((Constraint_paramContext)_localctx).anotherfieldparam = field();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(106);
				((Constraint_paramContext)_localctx).fieldparam = field();
				setState(107);
				((Constraint_paramContext)_localctx).opparam = compare_operator();
				setState(108);
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
		enterRule(_localctx, 28, RULE_convert_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			((Convert_paramContext)_localctx).fieldparam = field();
			setState(113);
			match(T__9);
			setState(114);
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
			setState(116);
			((Compute_paramContext)_localctx).fieldparam = field();
			setState(117);
			match(T__10);
			setState(118);
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
		enterRule(_localctx, 32, RULE_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(T__11);
			setState(121);
			((FieldContext)_localctx).tablenameparam = match(ID);
			setState(122);
			match(T__12);
			setState(123);
			((FieldContext)_localctx).fieldnameparam = match(ID);
			setState(124);
			match(T__13);
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
		enterRule(_localctx, 34, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
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
		enterRule(_localctx, 36, RULE_compare_operator);
		try {
			setState(142);
			switch (_input.LA(1)) {
			case EQ:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				((Compare_operatorContext)_localctx).opparam = match(EQ);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.EQ;   
				}
				break;
			case NEQ:
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				((Compare_operatorContext)_localctx).opparam = match(NEQ);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.NEQ;  
				}
				break;
			case GEQ:
				enterOuterAlt(_localctx, 3);
				{
				setState(134);
				((Compare_operatorContext)_localctx).opparam = match(GEQ);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.GEQ;  
				}
				break;
			case G:
				enterOuterAlt(_localctx, 4);
				{
				setState(136);
				((Compare_operatorContext)_localctx).opparam = match(G);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.G;    
				}
				break;
			case LEQ:
				enterOuterAlt(_localctx, 5);
				{
				setState(138);
				((Compare_operatorContext)_localctx).opparam = match(LEQ);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.LEQ;  
				}
				break;
			case L:
				enterOuterAlt(_localctx, 6);
				{
				setState(140);
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
		enterRule(_localctx, 38, RULE_calc_operator);
		try {
			setState(154);
			switch (_input.LA(1)) {
			case MULTIPLY:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				((Calc_operatorContext)_localctx).opparam = match(MULTIPLY);
				 ((Calc_operatorContext)_localctx).op =  CalcOperator.MULTIPLY;
				}
				break;
			case DIVIDE:
				enterOuterAlt(_localctx, 2);
				{
				setState(146);
				((Calc_operatorContext)_localctx).opparam = match(DIVIDE);
				 ((Calc_operatorContext)_localctx).op =  CalcOperator.DIVIDE;  
				}
				break;
			case PLUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(148);
				((Calc_operatorContext)_localctx).opparam = match(PLUS);
				 ((Calc_operatorContext)_localctx).op =  CalcOperator.PLUS;    
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 4);
				{
				setState(150);
				((Calc_operatorContext)_localctx).opparma = match(MINUS);
				 ((Calc_operatorContext)_localctx).op =  CalcOperator.MINUS;   
				}
				break;
			case MODULO:
				enterOuterAlt(_localctx, 5);
				{
				setState(152);
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
		enterRule(_localctx, 40, RULE_formula);
		try {
			setState(171);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(156);
				((FormulaContext)_localctx).fieldparam = field();
				setState(157);
				((FormulaContext)_localctx).opparam = calc_operator();
				setState(158);
				((FormulaContext)_localctx).anotherfieldparam = field();
				 ((FormulaContext)_localctx).form =  new Formula(((FormulaContext)_localctx).fieldparam.fieldname, ((FormulaContext)_localctx).opparam.op, ((FormulaContext)_localctx).anotherfieldparam.fieldname); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				((FormulaContext)_localctx).fieldparam = field();
				setState(162);
				((FormulaContext)_localctx).opparam = calc_operator();
				setState(163);
				((FormulaContext)_localctx).valueparam = number();
				 ((FormulaContext)_localctx).form =  new Formula(((FormulaContext)_localctx).fieldparam.fieldname, ((FormulaContext)_localctx).opparam.op, ((FormulaContext)_localctx).valueparam.val); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(166);
				((FormulaContext)_localctx).fieldparam = field();
				setState(167);
				((FormulaContext)_localctx).opparam = calc_operator();
				setState(168);
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
		enterRule(_localctx, 42, RULE_condition);
		try {
			setState(183);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(173);
				((ConditionContext)_localctx).opparam = compare_operator();
				setState(174);
				((ConditionContext)_localctx).valueparam = value();
				 ((ConditionContext)_localctx).cond =  new Condition(((ConditionContext)_localctx).opparam.op, ((ConditionContext)_localctx).valueparam.val); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
				((ConditionContext)_localctx).opparam = compare_operator();
				setState(178);
				((ConditionContext)_localctx).valueparam = value();
				setState(179);
				match(T__14);
				setState(180);
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
		enterRule(_localctx, 44, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(G);
			setState(186);
			((RangeContext)_localctx).g = value();
			setState(187);
			match(T__14);
			setState(188);
			match(L);
			setState(189);
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
		public NumberContext numparam;
		public TextContext stringparam;
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
		enterRule(_localctx, 46, RULE_value);
		try {
			setState(197);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(191);
				((ValueContext)_localctx).numparam = number();
				 ((ValueContext)_localctx).val =  ((ValueContext)_localctx).numparam.val;  
				}
				break;
			case EOF:
			case T__0:
			case T__1:
			case T__2:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
			case T__14:
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				((ValueContext)_localctx).stringparam = text();
				 ((ValueContext)_localctx).val =  new StringValue((((ValueContext)_localctx).stringparam!=null?_input.getText(((ValueContext)_localctx).stringparam.start,((ValueContext)_localctx).stringparam.stop):null)); 
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

	public static class TextContext extends ParserRuleContext {
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
		enterRule(_localctx, 48, RULE_text);
		try {
			enterOuterAlt(_localctx, 1);
			{
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3!\u00cc\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\7\2\66\n\2\f\2\16\29\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5"+
		"\3B\n\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17"+
		"q\n\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\5\24\u0091\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\5\25\u009d\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u00ae\n\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\5\27\u00ba\n\27\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u00c8\n\31\3\32\3\32\3\32\2\2\33\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2\2\u00c7\2\67\3"+
		"\2\2\2\4A\3\2\2\2\6C\3\2\2\2\bF\3\2\2\2\nI\3\2\2\2\fL\3\2\2\2\16O\3\2"+
		"\2\2\20R\3\2\2\2\22U\3\2\2\2\24X\3\2\2\2\26\\\3\2\2\2\30`\3\2\2\2\32d"+
		"\3\2\2\2\34p\3\2\2\2\36r\3\2\2\2 v\3\2\2\2\"z\3\2\2\2$\u0081\3\2\2\2&"+
		"\u0090\3\2\2\2(\u009c\3\2\2\2*\u00ad\3\2\2\2,\u00b9\3\2\2\2.\u00bb\3\2"+
		"\2\2\60\u00c7\3\2\2\2\62\u00c9\3\2\2\2\64\66\5\4\3\2\65\64\3\2\2\2\66"+
		"9\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28\3\3\2\2\29\67\3\2\2\2:B\5\6\4\2;"+
		"B\5\b\5\2<B\5\n\6\2=B\5\f\7\2>B\5\16\b\2?B\5\20\t\2@B\5\22\n\2A:\3\2\2"+
		"\2A;\3\2\2\2A<\3\2\2\2A=\3\2\2\2A>\3\2\2\2A?\3\2\2\2A@\3\2\2\2B\5\3\2"+
		"\2\2CD\7\3\2\2DE\5\24\13\2E\7\3\2\2\2FG\7\4\2\2GH\5\26\f\2H\t\3\2\2\2"+
		"IJ\7\5\2\2JK\5\30\r\2K\13\3\2\2\2LM\7\6\2\2MN\5\32\16\2N\r\3\2\2\2OP\7"+
		"\7\2\2PQ\5\34\17\2Q\17\3\2\2\2RS\7\b\2\2ST\5\36\20\2T\21\3\2\2\2UV\7\t"+
		"\2\2VW\5 \21\2W\23\3\2\2\2XY\5\"\22\2YZ\7\n\2\2Z[\5.\30\2[\25\3\2\2\2"+
		"\\]\5\"\22\2]^\7\13\2\2^_\5,\27\2_\27\3\2\2\2`a\5\"\22\2ab\7\f\2\2bc\5"+
		"\"\22\2c\31\3\2\2\2de\5\"\22\2ef\5&\24\2fg\5\"\22\2g\33\3\2\2\2hi\5\""+
		"\22\2ij\5&\24\2jk\5\"\22\2kq\3\2\2\2lm\5\"\22\2mn\5&\24\2no\5\60\31\2"+
		"oq\3\2\2\2ph\3\2\2\2pl\3\2\2\2q\35\3\2\2\2rs\5\"\22\2st\7\f\2\2tu\5*\26"+
		"\2u\37\3\2\2\2vw\5\"\22\2wx\7\r\2\2xy\5*\26\2y!\3\2\2\2z{\7\16\2\2{|\7"+
		"\22\2\2|}\7\17\2\2}~\7\22\2\2~\177\7\20\2\2\177\u0080\b\22\1\2\u0080#"+
		"\3\2\2\2\u0081\u0082\7\36\2\2\u0082\u0083\b\23\1\2\u0083%\3\2\2\2\u0084"+
		"\u0085\7\23\2\2\u0085\u0091\b\24\1\2\u0086\u0087\7\24\2\2\u0087\u0091"+
		"\b\24\1\2\u0088\u0089\7\25\2\2\u0089\u0091\b\24\1\2\u008a\u008b\7\26\2"+
		"\2\u008b\u0091\b\24\1\2\u008c\u008d\7\27\2\2\u008d\u0091\b\24\1\2\u008e"+
		"\u008f\7\30\2\2\u008f\u0091\b\24\1\2\u0090\u0084\3\2\2\2\u0090\u0086\3"+
		"\2\2\2\u0090\u0088\3\2\2\2\u0090\u008a\3\2\2\2\u0090\u008c\3\2\2\2\u0090"+
		"\u008e\3\2\2\2\u0091\'\3\2\2\2\u0092\u0093\7\31\2\2\u0093\u009d\b\25\1"+
		"\2\u0094\u0095\7\32\2\2\u0095\u009d\b\25\1\2\u0096\u0097\7\33\2\2\u0097"+
		"\u009d\b\25\1\2\u0098\u0099\7\34\2\2\u0099\u009d\b\25\1\2\u009a\u009b"+
		"\7\35\2\2\u009b\u009d\b\25\1\2\u009c\u0092\3\2\2\2\u009c\u0094\3\2\2\2"+
		"\u009c\u0096\3\2\2\2\u009c\u0098\3\2\2\2\u009c\u009a\3\2\2\2\u009d)\3"+
		"\2\2\2\u009e\u009f\5\"\22\2\u009f\u00a0\5(\25\2\u00a0\u00a1\5\"\22\2\u00a1"+
		"\u00a2\b\26\1\2\u00a2\u00ae\3\2\2\2\u00a3\u00a4\5\"\22\2\u00a4\u00a5\5"+
		"(\25\2\u00a5\u00a6\5$\23\2\u00a6\u00a7\b\26\1\2\u00a7\u00ae\3\2\2\2\u00a8"+
		"\u00a9\5\"\22\2\u00a9\u00aa\5(\25\2\u00aa\u00ab\5*\26\2\u00ab\u00ac\b"+
		"\26\1\2\u00ac\u00ae\3\2\2\2\u00ad\u009e\3\2\2\2\u00ad\u00a3\3\2\2\2\u00ad"+
		"\u00a8\3\2\2\2\u00ae+\3\2\2\2\u00af\u00b0\5&\24\2\u00b0\u00b1\5\60\31"+
		"\2\u00b1\u00b2\b\27\1\2\u00b2\u00ba\3\2\2\2\u00b3\u00b4\5&\24\2\u00b4"+
		"\u00b5\5\60\31\2\u00b5\u00b6\7\21\2\2\u00b6\u00b7\5,\27\2\u00b7\u00b8"+
		"\b\27\1\2\u00b8\u00ba\3\2\2\2\u00b9\u00af\3\2\2\2\u00b9\u00b3\3\2\2\2"+
		"\u00ba-\3\2\2\2\u00bb\u00bc\7\26\2\2\u00bc\u00bd\5\60\31\2\u00bd\u00be"+
		"\7\21\2\2\u00be\u00bf\7\30\2\2\u00bf\u00c0\5\60\31\2\u00c0/\3\2\2\2\u00c1"+
		"\u00c2\5$\23\2\u00c2\u00c3\b\31\1\2\u00c3\u00c8\3\2\2\2\u00c4\u00c5\5"+
		"\62\32\2\u00c5\u00c6\b\31\1\2\u00c6\u00c8\3\2\2\2\u00c7\u00c1\3\2\2\2"+
		"\u00c7\u00c4\3\2\2\2\u00c8\61\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\63\3\2"+
		"\2\2\n\67Ap\u0090\u009c\u00ad\u00b9\u00c7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
