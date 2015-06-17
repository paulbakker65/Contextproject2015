// Generated from /Users/mawdegroot/git/Contextproject2015/script/src/main/antlr4/AnalysisLang.g4 by ANTLR 4.5
package net.tudelft.hi.e.script;

import net.tudelft.hi.e.common.enums.CalcOperator;
import net.tudelft.hi.e.common.enums.CompareOperator;
import net.tudelft.hi.e.common.enums.ComputeOperator;
import net.tudelft.hi.e.computation.*;
import net.tudelft.hi.e.data.Value;
import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.StringValue;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

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
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, ID=24, EQ=25, 
		NEQ=26, GEQ=27, G=28, LEQ=29, L=30, MULTIPLY=31, DIVIDE=32, PLUS=33, MINUS=34, 
		MODULO=35, AVG=36, COUNT=37, MAX=38, MIN=39, SUM=40, STDDEV=41, NUMBER=42, 
		WS=43, STRING=44, CHAR=45;
	public static final int
		RULE_parse = 0, RULE_operation = 1, RULE_between_operation = 2, RULE_chunk_operation = 3, 
		RULE_foreach_chunk_operation = 4, RULE_code_operation = 5, RULE_compute_operation = 6, 
		RULE_connect_operation = 7, RULE_constraint_operation = 8, RULE_lsa_operation = 9, 
		RULE_between_param = 10, RULE_chunk_param = 11, RULE_chunk_type = 12, 
		RULE_foreach_chunk_param = 13, RULE_code_param = 14, RULE_compute_param = 15, 
		RULE_connect_param = 16, RULE_constraint_param = 17, RULE_lsa_param = 18, 
		RULE_table = 19, RULE_field = 20, RULE_pattern = 21, RULE_record_condition = 22, 
		RULE_count_pattern = 23, RULE_number = 24, RULE_compare_operator = 25, 
		RULE_calc_operator = 26, RULE_compute_operator = 27, RULE_condition = 28, 
		RULE_value = 29, RULE_date = 30, RULE_text = 31;
	public static final String[] ruleNames = {
		"parse", "operation", "between_operation", "chunk_operation", "foreach_chunk_operation", 
		"code_operation", "compute_operation", "connect_operation", "constraint_operation", 
		"lsa_operation", "between_param", "chunk_param", "chunk_type", "foreach_chunk_param", 
		"code_param", "compute_param", "connect_param", "constraint_param", "lsa_param", 
		"table", "field", "pattern", "record_condition", "count_pattern", "number", 
		"compare_operator", "calc_operator", "compute_operator", "condition", 
		"value", "date", "text"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'BETWEEN'", "'CHUNK'", "'FOR EACH CHUNK'", "'CODE'", "'COMPUTE'", 
		"'CONNECT'", "'CONSTRAINT'", "'LSA'", "'USING'", "'YEAR'", "'MONTH'", 
		"'DAY'", "'ON'", "'AS'", "'TO'", "'['", "']'", "'].['", "'{'", "'}'", 
		"'AND'", "'DATE('", "')'", null, "'=='", "'!='", "'>='", "'>'", "'<='", 
		"'<'", "'*'", "'/'", "'+'", "'-'", "'%'", "'AVG()'", "'COUNT()'", "'MAX()'", 
		"'MIN()'", "'SUM()'", "'STDDEV()'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"ID", "EQ", "NEQ", "GEQ", "G", "LEQ", "L", "MULTIPLY", "DIVIDE", "PLUS", 
		"MINUS", "MODULO", "AVG", "COUNT", "MAX", "MIN", "SUM", "STDDEV", "NUMBER", 
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
	@NotNull
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitParse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7))) != 0)) {
				{
				{
				setState(64); 
				operation();
				}
				}
				setState(69);
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
		public Foreach_chunk_operationContext foreach_chunk_operation() {
			return getRuleContext(Foreach_chunk_operationContext.class,0);
		}
		public Code_operationContext code_operation() {
			return getRuleContext(Code_operationContext.class,0);
		}
		public Connect_operationContext connect_operation() {
			return getRuleContext(Connect_operationContext.class,0);
		}
		public Constraint_operationContext constraint_operation() {
			return getRuleContext(Constraint_operationContext.class,0);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_operation);
		try {
			setState(78);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(70); 
				between_operation();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(71); 
				chunk_operation();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(72); 
				foreach_chunk_operation();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 4);
				{
				setState(73); 
				code_operation();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 5);
				{
				setState(74); 
				connect_operation();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 6);
				{
				setState(75); 
				constraint_operation();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 7);
				{
				setState(76); 
				compute_operation();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 8);
				{
				setState(77); 
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitBetween_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Between_operationContext between_operation() throws RecognitionException {
		Between_operationContext _localctx = new Between_operationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_between_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); 
			match(T__0);
			setState(81); 
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitChunk_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Chunk_operationContext chunk_operation() throws RecognitionException {
		Chunk_operationContext _localctx = new Chunk_operationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_chunk_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83); 
			match(T__1);
			setState(84); 
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

	public static class Foreach_chunk_operationContext extends ParserRuleContext {
		public Foreach_chunk_paramContext param;
		public Foreach_chunk_paramContext foreach_chunk_param() {
			return getRuleContext(Foreach_chunk_paramContext.class,0);
		}
		public Foreach_chunk_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreach_chunk_operation; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitForeach_chunk_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Foreach_chunk_operationContext foreach_chunk_operation() throws RecognitionException {
		Foreach_chunk_operationContext _localctx = new Foreach_chunk_operationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_foreach_chunk_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86); 
			match(T__2);
			setState(87); 
			((Foreach_chunk_operationContext)_localctx).param = foreach_chunk_param();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitCode_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Code_operationContext code_operation() throws RecognitionException {
		Code_operationContext _localctx = new Code_operationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_code_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89); 
			match(T__3);
			setState(90); 
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitCompute_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compute_operationContext compute_operation() throws RecognitionException {
		Compute_operationContext _localctx = new Compute_operationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_compute_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92); 
			match(T__4);
			setState(93); 
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitConnect_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Connect_operationContext connect_operation() throws RecognitionException {
		Connect_operationContext _localctx = new Connect_operationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_connect_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95); 
			match(T__5);
			setState(96); 
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitConstraint_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Constraint_operationContext constraint_operation() throws RecognitionException {
		Constraint_operationContext _localctx = new Constraint_operationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_constraint_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98); 
			match(T__6);
			setState(99); 
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitLsa_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lsa_operationContext lsa_operation() throws RecognitionException {
		Lsa_operationContext _localctx = new Lsa_operationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_lsa_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); 
			match(T__7);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitBetween_param(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Between_paramContext between_param() throws RecognitionException {
		Between_paramContext _localctx = new Between_paramContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_between_param);
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
		public Chunk_typeContext type;
		public NumberContext numberparam;
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public Chunk_typeContext chunk_type() {
			return getRuleContext(Chunk_typeContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public Chunk_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chunk_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitChunk_param(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Chunk_paramContext chunk_param() throws RecognitionException {
		Chunk_paramContext _localctx = new Chunk_paramContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_chunk_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117); 
			((Chunk_paramContext)_localctx).fieldparam = field();
			setState(118); 
			match(T__8);
			setState(119); 
			((Chunk_paramContext)_localctx).type = chunk_type();
			setState(120); 
			((Chunk_paramContext)_localctx).numberparam = number();
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

	public static class Chunk_typeContext extends ParserRuleContext {
		public int i;
		public Chunk_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chunk_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitChunk_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Chunk_typeContext chunk_type() throws RecognitionException {
		Chunk_typeContext _localctx = new Chunk_typeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_chunk_type);
		try {
			setState(128);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(122); 
				match(T__9);
				 ((Chunk_typeContext)_localctx).i =  0; 
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(124); 
				match(T__10);
				 ((Chunk_typeContext)_localctx).i =  1; 
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 3);
				{
				setState(126); 
				match(T__11);
				 ((Chunk_typeContext)_localctx).i =  2; 
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

	public static class Foreach_chunk_paramContext extends ParserRuleContext {
		public TableContext tableparam;
		public NumberContext levelparam;
		public OperationContext operationparam;
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public Foreach_chunk_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreach_chunk_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitForeach_chunk_param(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Foreach_chunk_paramContext foreach_chunk_param() throws RecognitionException {
		Foreach_chunk_paramContext _localctx = new Foreach_chunk_paramContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_foreach_chunk_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130); 
			((Foreach_chunk_paramContext)_localctx).tableparam = table();
			setState(131); 
			((Foreach_chunk_paramContext)_localctx).levelparam = number();
			setState(132); 
			((Foreach_chunk_paramContext)_localctx).operationparam = operation();
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
		public TableContext tableparam;
		public PatternContext patternparam;
		public TextContext codenameparam;
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Code_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitCode_param(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Code_paramContext code_param() throws RecognitionException {
		Code_paramContext _localctx = new Code_paramContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_code_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134); 
			((Code_paramContext)_localctx).tableparam = table();
			setState(135); 
			match(T__12);
			setState(136); 
			((Code_paramContext)_localctx).patternparam = pattern();
			setState(137); 
			match(T__13);
			setState(138); 
			((Code_paramContext)_localctx).codenameparam = text();
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
		public TableContext tableparam;
		public Compute_operatorContext computeopparam;
		public FieldContext fieldparam;
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public Compute_operatorContext compute_operator() {
			return getRuleContext(Compute_operatorContext.class,0);
		}
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public Compute_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compute_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitCompute_param(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compute_paramContext compute_param() throws RecognitionException {
		Compute_paramContext _localctx = new Compute_paramContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_compute_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140); 
			((Compute_paramContext)_localctx).tableparam = table();
			setState(141); 
			((Compute_paramContext)_localctx).computeopparam = compute_operator();
			setState(142); 
			((Compute_paramContext)_localctx).fieldparam = field();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitConnect_param(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Connect_paramContext connect_param() throws RecognitionException {
		Connect_paramContext _localctx = new Connect_paramContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_connect_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144); 
			((Connect_paramContext)_localctx).fieldparam = field();
			setState(145); 
			match(T__14);
			setState(146); 
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
		public ValueContext valueparam;
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitConstraint_param(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Constraint_paramContext constraint_param() throws RecognitionException {
		Constraint_paramContext _localctx = new Constraint_paramContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_constraint_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148); 
			((Constraint_paramContext)_localctx).fieldparam = field();
			setState(149); 
			((Constraint_paramContext)_localctx).opparam = compare_operator();
			setState(150); 
			((Constraint_paramContext)_localctx).valueparam = value();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitLsa_param(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lsa_paramContext lsa_param() throws RecognitionException {
		Lsa_paramContext _localctx = new Lsa_paramContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_lsa_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152); 
			((Lsa_paramContext)_localctx).fieldparam = field();
			setState(153); 
			((Lsa_paramContext)_localctx).from = number();
			setState(154); 
			((Lsa_paramContext)_localctx).to = number();
			setState(155); 
			((Lsa_paramContext)_localctx).key = value();
			setState(156); 
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

	public static class TableContext extends ParserRuleContext {
		public String tablename;
		public Token tablenameparam;
		public TerminalNode ID() { return getToken(AnalysisLangParser.ID, 0); }
		public TableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableContext table() throws RecognitionException {
		TableContext _localctx = new TableContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_table);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158); 
			match(T__15);
			setState(159); 
			((TableContext)_localctx).tablenameparam = match(ID);
			setState(160); 
			match(T__16);
			 ((TableContext)_localctx).tablename =  (((TableContext)_localctx).tablenameparam!=null?((TableContext)_localctx).tablenameparam.getText():null);  
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163); 
			match(T__15);
			setState(164); 
			((FieldContext)_localctx).tablenameparam = match(ID);
			setState(165); 
			match(T__17);
			setState(166); 
			((FieldContext)_localctx).fieldnameparam = match(ID);
			setState(167); 
			match(T__16);
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

	public static class PatternContext extends ParserRuleContext {
		public ArrayList<PatternDescription> patterndesc;
		public Count_patternContext countparam;
		public Record_conditionContext recordconditionparam;
		public List<Count_patternContext> count_pattern() {
			return getRuleContexts(Count_patternContext.class);
		}
		public Count_patternContext count_pattern(int i) {
			return getRuleContext(Count_patternContext.class,i);
		}
		public List<Record_conditionContext> record_condition() {
			return getRuleContexts(Record_conditionContext.class);
		}
		public Record_conditionContext record_condition(int i) {
			return getRuleContext(Record_conditionContext.class,i);
		}
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_pattern);

		  ((PatternContext)_localctx).patterndesc =  new ArrayList<PatternDescription>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(170); 
				match(T__18);
				setState(171); 
				((PatternContext)_localctx).countparam = count_pattern();
				setState(172); 
				((PatternContext)_localctx).recordconditionparam = record_condition();
				setState(173); 
				match(T__19);
				 _localctx.patterndesc.add(new PatternDescription(((PatternContext)_localctx).countparam.count, ((PatternContext)_localctx).recordconditionparam.recordcondition)); 
				}
				}
				setState(178); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__18 );
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

	public static class Record_conditionContext extends ParserRuleContext {
		public RecordCondition recordcondition;
		public TableContext tableparam;
		public FieldContext fieldparam;
		public FieldContext field;
		public ConditionContext conditionparam;
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public Record_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_record_condition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitRecord_condition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Record_conditionContext record_condition() throws RecognitionException {
		Record_conditionContext _localctx = new Record_conditionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_record_condition);
		try {
			setState(187);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(180); 
				((Record_conditionContext)_localctx).tableparam = table();
				 ((Record_conditionContext)_localctx).recordcondition =  new RecordOccurrenceCondition(((Record_conditionContext)_localctx).tableparam.tablename); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(183); 
				((Record_conditionContext)_localctx).fieldparam = ((Record_conditionContext)_localctx).field = field();
				setState(184); 
				((Record_conditionContext)_localctx).conditionparam = condition();
				 ((Record_conditionContext)_localctx).recordcondition =  new RecordMatchesConditionCondition(((Record_conditionContext)_localctx).field.fieldname, ((Record_conditionContext)_localctx).conditionparam.cond); 
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

	public static class Count_patternContext extends ParserRuleContext {
		public Count count;
		public Token wildcard;
		public Token numberparam;
		public TerminalNode NUMBER() { return getToken(AnalysisLangParser.NUMBER, 0); }
		public Count_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_count_pattern; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitCount_pattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Count_patternContext count_pattern() throws RecognitionException {
		Count_patternContext _localctx = new Count_patternContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_count_pattern);
		try {
			setState(193);
			switch (_input.LA(1)) {
			case MULTIPLY:
				enterOuterAlt(_localctx, 1);
				{
				setState(189); 
				((Count_patternContext)_localctx).wildcard = match(MULTIPLY);
				 ((Count_patternContext)_localctx).count =  new MultipleCount(); 
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(191); 
				((Count_patternContext)_localctx).numberparam = match(NUMBER);
				 ((Count_patternContext)_localctx).count =  new SingleCount((((Count_patternContext)_localctx).numberparam!=null?Integer.valueOf(((Count_patternContext)_localctx).numberparam.getText()):0)); 
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

	public static class NumberContext extends ParserRuleContext {
		public Value val;
		public Token numparam;
		public TerminalNode NUMBER() { return getToken(AnalysisLangParser.NUMBER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195); 
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitCompare_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compare_operatorContext compare_operator() throws RecognitionException {
		Compare_operatorContext _localctx = new Compare_operatorContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_compare_operator);
		try {
			setState(210);
			switch (_input.LA(1)) {
			case EQ:
				enterOuterAlt(_localctx, 1);
				{
				setState(198); 
				((Compare_operatorContext)_localctx).opparam = match(EQ);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.EQ;   
				}
				break;
			case NEQ:
				enterOuterAlt(_localctx, 2);
				{
				setState(200); 
				((Compare_operatorContext)_localctx).opparam = match(NEQ);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.NEQ;  
				}
				break;
			case GEQ:
				enterOuterAlt(_localctx, 3);
				{
				setState(202); 
				((Compare_operatorContext)_localctx).opparam = match(GEQ);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.GEQ;  
				}
				break;
			case G:
				enterOuterAlt(_localctx, 4);
				{
				setState(204); 
				((Compare_operatorContext)_localctx).opparam = match(G);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.G;    
				}
				break;
			case LEQ:
				enterOuterAlt(_localctx, 5);
				{
				setState(206); 
				((Compare_operatorContext)_localctx).opparam = match(LEQ);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.LEQ;  
				}
				break;
			case L:
				enterOuterAlt(_localctx, 6);
				{
				setState(208); 
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitCalc_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Calc_operatorContext calc_operator() throws RecognitionException {
		Calc_operatorContext _localctx = new Calc_operatorContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_calc_operator);
		try {
			setState(222);
			switch (_input.LA(1)) {
			case MULTIPLY:
				enterOuterAlt(_localctx, 1);
				{
				setState(212); 
				((Calc_operatorContext)_localctx).opparam = match(MULTIPLY);
				 ((Calc_operatorContext)_localctx).op =  CalcOperator.MULTIPLY;
				}
				break;
			case DIVIDE:
				enterOuterAlt(_localctx, 2);
				{
				setState(214); 
				((Calc_operatorContext)_localctx).opparam = match(DIVIDE);
				 ((Calc_operatorContext)_localctx).op =  CalcOperator.DIVIDE;  
				}
				break;
			case PLUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(216); 
				((Calc_operatorContext)_localctx).opparam = match(PLUS);
				 ((Calc_operatorContext)_localctx).op =  CalcOperator.PLUS;    
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 4);
				{
				setState(218); 
				((Calc_operatorContext)_localctx).opparma = match(MINUS);
				 ((Calc_operatorContext)_localctx).op =  CalcOperator.MINUS;   
				}
				break;
			case MODULO:
				enterOuterAlt(_localctx, 5);
				{
				setState(220); 
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

	public static class Compute_operatorContext extends ParserRuleContext {
		public ComputeOperator op;
		public Token opparam;
		public TerminalNode AVG() { return getToken(AnalysisLangParser.AVG, 0); }
		public TerminalNode COUNT() { return getToken(AnalysisLangParser.COUNT, 0); }
		public TerminalNode MAX() { return getToken(AnalysisLangParser.MAX, 0); }
		public TerminalNode MIN() { return getToken(AnalysisLangParser.MIN, 0); }
		public TerminalNode STDDEV() { return getToken(AnalysisLangParser.STDDEV, 0); }
		public TerminalNode SUM() { return getToken(AnalysisLangParser.SUM, 0); }
		public Compute_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compute_operator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitCompute_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compute_operatorContext compute_operator() throws RecognitionException {
		Compute_operatorContext _localctx = new Compute_operatorContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_compute_operator);
		try {
			setState(236);
			switch (_input.LA(1)) {
			case AVG:
				enterOuterAlt(_localctx, 1);
				{
				setState(224); 
				((Compute_operatorContext)_localctx).opparam = match(AVG);
				 ((Compute_operatorContext)_localctx).op =  ComputeOperator.AVG;    
				}
				break;
			case COUNT:
				enterOuterAlt(_localctx, 2);
				{
				setState(226); 
				((Compute_operatorContext)_localctx).opparam = match(COUNT);
				 ((Compute_operatorContext)_localctx).op =  ComputeOperator.COUNT;  
				}
				break;
			case MAX:
				enterOuterAlt(_localctx, 3);
				{
				setState(228); 
				((Compute_operatorContext)_localctx).opparam = match(MAX);
				 ((Compute_operatorContext)_localctx).op =  ComputeOperator.MAX;    
				}
				break;
			case MIN:
				enterOuterAlt(_localctx, 4);
				{
				setState(230); 
				((Compute_operatorContext)_localctx).opparam = match(MIN);
				 ((Compute_operatorContext)_localctx).op =  ComputeOperator.MIN;    
				}
				break;
			case STDDEV:
				enterOuterAlt(_localctx, 5);
				{
				setState(232); 
				((Compute_operatorContext)_localctx).opparam = match(STDDEV);
				 ((Compute_operatorContext)_localctx).op =  ComputeOperator.STDEV; 
				}
				break;
			case SUM:
				enterOuterAlt(_localctx, 6);
				{
				setState(234); 
				((Compute_operatorContext)_localctx).opparam = match(SUM);
				 ((Compute_operatorContext)_localctx).op =  ComputeOperator.SUM;    
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_condition);
		try {
			setState(248);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(238); 
				((ConditionContext)_localctx).opparam = compare_operator();
				setState(239); 
				((ConditionContext)_localctx).valueparam = value();
				 ((ConditionContext)_localctx).cond =  new Condition(((ConditionContext)_localctx).opparam.op, ((ConditionContext)_localctx).valueparam.val); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(242); 
				((ConditionContext)_localctx).opparam = compare_operator();
				setState(243); 
				((ConditionContext)_localctx).valueparam = value();
				setState(244); 
				match(T__20);
				setState(245); 
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_value);
		try {
			setState(259);
			switch (_input.LA(1)) {
			case T__21:
				enterOuterAlt(_localctx, 1);
				{
				setState(250); 
				((ValueContext)_localctx).dataparam = date();
				 ((ValueContext)_localctx).val =  ((ValueContext)_localctx).dataparam.val; 
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(253); 
				((ValueContext)_localctx).numparam = number();
				 ((ValueContext)_localctx).val =  ((ValueContext)_localctx).numparam.val;  
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(256); 
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitDate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DateContext date() throws RecognitionException {
		DateContext _localctx = new DateContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_date);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261); 
			match(T__21);
			setState(262); 
			((DateContext)_localctx).yearparam = match(NUMBER);
			setState(263); 
			match(MINUS);
			setState(264); 
			((DateContext)_localctx).monthparam = match(NUMBER);
			setState(265); 
			match(MINUS);
			setState(266); 
			((DateContext)_localctx).dayparam = match(NUMBER);
			setState(267); 
			match(T__22);

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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TextContext text() throws RecognitionException {
		TextContext _localctx = new TextContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_text);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270); 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3/\u0114\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\7\2D\n\2\f\2\16\2G\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3Q\n"+
		"\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\5\fv\n\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\5\16"+
		"\u0083\n\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\6\27\u00b3\n\27\r\27\16\27\u00b4\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\5\30\u00be\n\30\3\31\3\31\3\31\3\31\5\31"+
		"\u00c4\n\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\5\33\u00d5\n\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\5\34\u00e1\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\5\35\u00ef\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\5\36\u00fb\n\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\5\37\u0106\n\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\2\2\"\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@\2\2\u0112\2"+
		"E\3\2\2\2\4P\3\2\2\2\6R\3\2\2\2\bU\3\2\2\2\nX\3\2\2\2\f[\3\2\2\2\16^\3"+
		"\2\2\2\20a\3\2\2\2\22d\3\2\2\2\24g\3\2\2\2\26u\3\2\2\2\30w\3\2\2\2\32"+
		"\u0082\3\2\2\2\34\u0084\3\2\2\2\36\u0088\3\2\2\2 \u008e\3\2\2\2\"\u0092"+
		"\3\2\2\2$\u0096\3\2\2\2&\u009a\3\2\2\2(\u00a0\3\2\2\2*\u00a5\3\2\2\2,"+
		"\u00b2\3\2\2\2.\u00bd\3\2\2\2\60\u00c3\3\2\2\2\62\u00c5\3\2\2\2\64\u00d4"+
		"\3\2\2\2\66\u00e0\3\2\2\28\u00ee\3\2\2\2:\u00fa\3\2\2\2<\u0105\3\2\2\2"+
		">\u0107\3\2\2\2@\u0110\3\2\2\2BD\5\4\3\2CB\3\2\2\2DG\3\2\2\2EC\3\2\2\2"+
		"EF\3\2\2\2F\3\3\2\2\2GE\3\2\2\2HQ\5\6\4\2IQ\5\b\5\2JQ\5\n\6\2KQ\5\f\7"+
		"\2LQ\5\20\t\2MQ\5\22\n\2NQ\5\16\b\2OQ\5\24\13\2PH\3\2\2\2PI\3\2\2\2PJ"+
		"\3\2\2\2PK\3\2\2\2PL\3\2\2\2PM\3\2\2\2PN\3\2\2\2PO\3\2\2\2Q\5\3\2\2\2"+
		"RS\7\3\2\2ST\5\26\f\2T\7\3\2\2\2UV\7\4\2\2VW\5\30\r\2W\t\3\2\2\2XY\7\5"+
		"\2\2YZ\5\34\17\2Z\13\3\2\2\2[\\\7\6\2\2\\]\5\36\20\2]\r\3\2\2\2^_\7\7"+
		"\2\2_`\5 \21\2`\17\3\2\2\2ab\7\b\2\2bc\5\"\22\2c\21\3\2\2\2de\7\t\2\2"+
		"ef\5$\23\2f\23\3\2\2\2gh\7\n\2\2hi\5&\24\2i\25\3\2\2\2jk\5*\26\2kl\5*"+
		"\26\2lm\5*\26\2mn\5<\37\2no\5<\37\2ov\3\2\2\2pq\5*\26\2qr\5*\26\2rs\5"+
		"<\37\2st\5<\37\2tv\3\2\2\2uj\3\2\2\2up\3\2\2\2v\27\3\2\2\2wx\5*\26\2x"+
		"y\7\13\2\2yz\5\32\16\2z{\5\62\32\2{\31\3\2\2\2|}\7\f\2\2}\u0083\b\16\1"+
		"\2~\177\7\r\2\2\177\u0083\b\16\1\2\u0080\u0081\7\16\2\2\u0081\u0083\b"+
		"\16\1\2\u0082|\3\2\2\2\u0082~\3\2\2\2\u0082\u0080\3\2\2\2\u0083\33\3\2"+
		"\2\2\u0084\u0085\5(\25\2\u0085\u0086\5\62\32\2\u0086\u0087\5\4\3\2\u0087"+
		"\35\3\2\2\2\u0088\u0089\5(\25\2\u0089\u008a\7\17\2\2\u008a\u008b\5,\27"+
		"\2\u008b\u008c\7\20\2\2\u008c\u008d\5@!\2\u008d\37\3\2\2\2\u008e\u008f"+
		"\5(\25\2\u008f\u0090\58\35\2\u0090\u0091\5*\26\2\u0091!\3\2\2\2\u0092"+
		"\u0093\5*\26\2\u0093\u0094\7\21\2\2\u0094\u0095\5*\26\2\u0095#\3\2\2\2"+
		"\u0096\u0097\5*\26\2\u0097\u0098\5\64\33\2\u0098\u0099\5<\37\2\u0099%"+
		"\3\2\2\2\u009a\u009b\5*\26\2\u009b\u009c\5\62\32\2\u009c\u009d\5\62\32"+
		"\2\u009d\u009e\5<\37\2\u009e\u009f\5<\37\2\u009f\'\3\2\2\2\u00a0\u00a1"+
		"\7\22\2\2\u00a1\u00a2\7\32\2\2\u00a2\u00a3\7\23\2\2\u00a3\u00a4\b\25\1"+
		"\2\u00a4)\3\2\2\2\u00a5\u00a6\7\22\2\2\u00a6\u00a7\7\32\2\2\u00a7\u00a8"+
		"\7\24\2\2\u00a8\u00a9\7\32\2\2\u00a9\u00aa\7\23\2\2\u00aa\u00ab\b\26\1"+
		"\2\u00ab+\3\2\2\2\u00ac\u00ad\7\25\2\2\u00ad\u00ae\5\60\31\2\u00ae\u00af"+
		"\5.\30\2\u00af\u00b0\7\26\2\2\u00b0\u00b1\b\27\1\2\u00b1\u00b3\3\2\2\2"+
		"\u00b2\u00ac\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5"+
		"\3\2\2\2\u00b5-\3\2\2\2\u00b6\u00b7\5(\25\2\u00b7\u00b8\b\30\1\2\u00b8"+
		"\u00be\3\2\2\2\u00b9\u00ba\5*\26\2\u00ba\u00bb\5:\36\2\u00bb\u00bc\b\30"+
		"\1\2\u00bc\u00be\3\2\2\2\u00bd\u00b6\3\2\2\2\u00bd\u00b9\3\2\2\2\u00be"+
		"/\3\2\2\2\u00bf\u00c0\7!\2\2\u00c0\u00c4\b\31\1\2\u00c1\u00c2\7,\2\2\u00c2"+
		"\u00c4\b\31\1\2\u00c3\u00bf\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\61\3\2\2"+
		"\2\u00c5\u00c6\7,\2\2\u00c6\u00c7\b\32\1\2\u00c7\63\3\2\2\2\u00c8\u00c9"+
		"\7\33\2\2\u00c9\u00d5\b\33\1\2\u00ca\u00cb\7\34\2\2\u00cb\u00d5\b\33\1"+
		"\2\u00cc\u00cd\7\35\2\2\u00cd\u00d5\b\33\1\2\u00ce\u00cf\7\36\2\2\u00cf"+
		"\u00d5\b\33\1\2\u00d0\u00d1\7\37\2\2\u00d1\u00d5\b\33\1\2\u00d2\u00d3"+
		"\7 \2\2\u00d3\u00d5\b\33\1\2\u00d4\u00c8\3\2\2\2\u00d4\u00ca\3\2\2\2\u00d4"+
		"\u00cc\3\2\2\2\u00d4\u00ce\3\2\2\2\u00d4\u00d0\3\2\2\2\u00d4\u00d2\3\2"+
		"\2\2\u00d5\65\3\2\2\2\u00d6\u00d7\7!\2\2\u00d7\u00e1\b\34\1\2\u00d8\u00d9"+
		"\7\"\2\2\u00d9\u00e1\b\34\1\2\u00da\u00db\7#\2\2\u00db\u00e1\b\34\1\2"+
		"\u00dc\u00dd\7$\2\2\u00dd\u00e1\b\34\1\2\u00de\u00df\7%\2\2\u00df\u00e1"+
		"\b\34\1\2\u00e0\u00d6\3\2\2\2\u00e0\u00d8\3\2\2\2\u00e0\u00da\3\2\2\2"+
		"\u00e0\u00dc\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1\67\3\2\2\2\u00e2\u00e3"+
		"\7&\2\2\u00e3\u00ef\b\35\1\2\u00e4\u00e5\7\'\2\2\u00e5\u00ef\b\35\1\2"+
		"\u00e6\u00e7\7(\2\2\u00e7\u00ef\b\35\1\2\u00e8\u00e9\7)\2\2\u00e9\u00ef"+
		"\b\35\1\2\u00ea\u00eb\7+\2\2\u00eb\u00ef\b\35\1\2\u00ec\u00ed\7*\2\2\u00ed"+
		"\u00ef\b\35\1\2\u00ee\u00e2\3\2\2\2\u00ee\u00e4\3\2\2\2\u00ee\u00e6\3"+
		"\2\2\2\u00ee\u00e8\3\2\2\2\u00ee\u00ea\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ef"+
		"9\3\2\2\2\u00f0\u00f1\5\64\33\2\u00f1\u00f2\5<\37\2\u00f2\u00f3\b\36\1"+
		"\2\u00f3\u00fb\3\2\2\2\u00f4\u00f5\5\64\33\2\u00f5\u00f6\5<\37\2\u00f6"+
		"\u00f7\7\27\2\2\u00f7\u00f8\5:\36\2\u00f8\u00f9\b\36\1\2\u00f9\u00fb\3"+
		"\2\2\2\u00fa\u00f0\3\2\2\2\u00fa\u00f4\3\2\2\2\u00fb;\3\2\2\2\u00fc\u00fd"+
		"\5> \2\u00fd\u00fe\b\37\1\2\u00fe\u0106\3\2\2\2\u00ff\u0100\5\62\32\2"+
		"\u0100\u0101\b\37\1\2\u0101\u0106\3\2\2\2\u0102\u0103\5@!\2\u0103\u0104"+
		"\b\37\1\2\u0104\u0106\3\2\2\2\u0105\u00fc\3\2\2\2\u0105\u00ff\3\2\2\2"+
		"\u0105\u0102\3\2\2\2\u0106=\3\2\2\2\u0107\u0108\7\30\2\2\u0108\u0109\7"+
		",\2\2\u0109\u010a\7$\2\2\u010a\u010b\7,\2\2\u010b\u010c\7$\2\2\u010c\u010d"+
		"\7,\2\2\u010d\u010e\7\31\2\2\u010e\u010f\b \1\2\u010f?\3\2\2\2\u0110\u0111"+
		"\7.\2\2\u0111\u0112\b!\1\2\u0112A\3\2\2\2\16EPu\u0082\u00b4\u00bd\u00c3"+
		"\u00d4\u00e0\u00ee\u00fa\u0105";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}