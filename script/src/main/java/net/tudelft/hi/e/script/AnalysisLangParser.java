// Generated from /Users/mawdegroot/git/Contextproject2015/script/src/main/antlr4/AnalysisLang.g4 by ANTLR 4.5

package net.tudelft.hi.e.script;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import net.tudelft.hi.e.common.enums.CalcOperator;
import net.tudelft.hi.e.common.enums.CompareOperator;
import net.tudelft.hi.e.common.enums.ComputeOperator;
import net.tudelft.hi.e.computation.Condition;
import net.tudelft.hi.e.computation.Count;
import net.tudelft.hi.e.computation.Formula;
import net.tudelft.hi.e.computation.MultipleCount;
import net.tudelft.hi.e.computation.PatternDescription;
import net.tudelft.hi.e.computation.RecordCondition;
import net.tudelft.hi.e.computation.RecordMatchesConditionCondition;
import net.tudelft.hi.e.computation.RecordOccurrenceCondition;
import net.tudelft.hi.e.computation.SingleCount;
import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Value;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AnalysisLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9,
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17,
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24,
		T__24=25, ID=26, EQ=27, NEQ=28, GEQ=29, G=30, LEQ=31, L=32, MULTIPLY=33,
		DIVIDE=34, PLUS=35, MINUS=36, MODULO=37, AVG=38, COUNT=39, MAX=40, MIN=41,
		SUM=42, STDDEV=43, NUMBER=44, WS=45, STRING=46, CHAR=47;
	public static final int
		RULE_parse = 0, RULE_operation = 1, RULE_between_operation = 2, RULE_chunk_operation = 3,
		RULE_foreach_chunk_operation = 4, RULE_code_operation = 5, RULE_compare_operation = 6,
		RULE_compute_operation = 7, RULE_connect_operation = 8, RULE_constraint_operation = 9,
		RULE_convert_operation = 10, RULE_lsa_operation = 11, RULE_between_param = 12,
		RULE_chunk_param = 13, RULE_chunk_type = 14, RULE_foreach_chunk_param = 15,
		RULE_code_param = 16, RULE_compare_param = 17, RULE_compute_param = 18,
		RULE_connect_param = 19, RULE_constraint_param = 20, RULE_convert_param = 21,
		RULE_lsa_param = 22, RULE_table = 23, RULE_field = 24, RULE_pattern = 25,
		RULE_record_condition = 26, RULE_count_pattern = 27, RULE_number = 28,
		RULE_compare_operator = 29, RULE_calc_operator = 30, RULE_compute_operator = 31,
		RULE_formula = 32, RULE_condition = 33, RULE_range = 34, RULE_value = 35,
		RULE_date = 36, RULE_text = 37;
	public static final String[] ruleNames = {
		"parse", "operation", "between_operation", "chunk_operation", "foreach_chunk_operation",
		"code_operation", "compare_operation", "compute_operation", "connect_operation",
		"constraint_operation", "convert_operation", "lsa_operation", "between_param",
		"chunk_param", "chunk_type", "foreach_chunk_param", "code_param", "compare_param",
		"compute_param", "connect_param", "constraint_param", "convert_param",
		"lsa_param", "table", "field", "pattern", "record_condition", "count_pattern",
		"number", "compare_operator", "calc_operator", "compute_operator", "formula",
		"condition", "range", "value", "date", "text"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'BETWEEN'", "'CHUNK'", "'FOR EACH CHUNK'", "'CODE'", "'COMPARE'",
		"'COMPUTE'", "'CONNECT'", "'CONSTRAINT'", "'CONVERT'", "'LSA'", "'USING'",
		"'YEAR'", "'MONTH'", "'DAY'", "'ON'", "'AS'", "'TO'", "'['", "']'", "'].['",
		"'{'", "'}'", "'AND'", "'DATE('", "')'", null, "'=='", "'!='", "'>='",
		"'>'", "'<='", "'<'", "'*'", "'/'", "'+'", "'-'", "'%'", "'AVG()'", "'COUNT()'",
		"'MAX()'", "'MIN()'", "'SUM()'", "'STDDEV()'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null,
		null, null, null, null, null, null, null, null, null, null, null, null,
		null, null, "ID", "EQ", "NEQ", "GEQ", "G", "LEQ", "L", "MULTIPLY", "DIVIDE",
		"PLUS", "MINUS", "MODULO", "AVG", "COUNT", "MAX", "MIN", "SUM", "STDDEV",
		"NUMBER", "WS", "STRING", "CHAR"
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
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9))) != 0)) {
				{
				{
				setState(76);
				operation();
				}
				}
				setState(81);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_operation);
		try {
			setState(92);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				between_operation();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				chunk_operation();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(84);
				foreach_chunk_operation();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 4);
				{
				setState(85);
				code_operation();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 5);
				{
				setState(86);
				connect_operation();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 6);
				{
				setState(87);
				compare_operation();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 7);
				{
				setState(88);
				constraint_operation();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 8);
				{
				setState(89);
				convert_operation();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 9);
				{
				setState(90);
				compute_operation();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 10);
				{
				setState(91);
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
			setState(94);
			match(T__0);
			setState(95);
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
			setState(97);
			match(T__1);
			setState(98);
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
			setState(100);
			match(T__2);
			setState(101);
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
			setState(103);
			match(T__3);
			setState(104);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitCompare_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compare_operationContext compare_operation() throws RecognitionException {
		Compare_operationContext _localctx = new Compare_operationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_compare_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(T__4);
			setState(107);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitCompute_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compute_operationContext compute_operation() throws RecognitionException {
		Compute_operationContext _localctx = new Compute_operationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_compute_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(T__5);
			setState(110);
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
		enterRule(_localctx, 16, RULE_connect_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(T__6);
			setState(113);
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
		enterRule(_localctx, 18, RULE_constraint_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(T__7);
			setState(116);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitConvert_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Convert_operationContext convert_operation() throws RecognitionException {
		Convert_operationContext _localctx = new Convert_operationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_convert_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(T__8);
			setState(119);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitLsa_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lsa_operationContext lsa_operation() throws RecognitionException {
		Lsa_operationContext _localctx = new Lsa_operationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_lsa_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(T__9);
			setState(122);
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
		enterRule(_localctx, 24, RULE_between_param);
		try {
			setState(135);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				((Between_paramContext)_localctx).eventcol = field();
				setState(125);
				((Between_paramContext)_localctx).datecol1 = field();
				setState(126);
				((Between_paramContext)_localctx).datecol2 = field();
				setState(127);
				((Between_paramContext)_localctx).value1 = value();
				setState(128);
				((Between_paramContext)_localctx).value2 = value();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				((Between_paramContext)_localctx).eventcol = field();
				setState(131);
				((Between_paramContext)_localctx).datecol1 = field();
				setState(132);
				((Between_paramContext)_localctx).value1 = value();
				setState(133);
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
		public Chunk_typeContext type;
		public NumberContext numberparam;
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
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
		enterRule(_localctx, 26, RULE_chunk_param);
		try {
			setState(146);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				((Chunk_paramContext)_localctx).fieldparam = field();
				setState(138);
				match(T__10);
				setState(139);
				((Chunk_paramContext)_localctx).rangeparam = range();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				((Chunk_paramContext)_localctx).fieldparam = field();
				setState(142);
				match(T__10);
				setState(143);
				((Chunk_paramContext)_localctx).type = chunk_type();
				setState(144);
				((Chunk_paramContext)_localctx).numberparam = number();
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
		enterRule(_localctx, 28, RULE_chunk_type);
		try {
			setState(154);
			switch (_input.LA(1)) {
			case T__11:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				match(T__11);
				 ((Chunk_typeContext)_localctx).i =  0;
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(150);
				match(T__12);
				 ((Chunk_typeContext)_localctx).i =  1;
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 3);
				{
				setState(152);
				match(T__13);
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
		enterRule(_localctx, 30, RULE_foreach_chunk_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			((Foreach_chunk_paramContext)_localctx).tableparam = table();
			setState(157);
			((Foreach_chunk_paramContext)_localctx).levelparam = number();
			setState(158);
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
		enterRule(_localctx, 32, RULE_code_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			((Code_paramContext)_localctx).tableparam = table();
			setState(161);
			match(T__14);
			setState(162);
			((Code_paramContext)_localctx).patternparam = pattern();
			setState(163);
			match(T__15);
			setState(164);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitCompare_param(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compare_paramContext compare_param() throws RecognitionException {
		Compare_paramContext _localctx = new Compare_paramContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_compare_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			((Compare_paramContext)_localctx).fieldparam = field();
			setState(167);
			((Compare_paramContext)_localctx).opparam = compare_operator();
			setState(168);
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
		enterRule(_localctx, 36, RULE_compute_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			((Compute_paramContext)_localctx).tableparam = table();
			setState(171);
			((Compute_paramContext)_localctx).computeopparam = compute_operator();
			setState(172);
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
		enterRule(_localctx, 38, RULE_connect_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			((Connect_paramContext)_localctx).fieldparam = field();
			setState(175);
			match(T__16);
			setState(176);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitConstraint_param(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Constraint_paramContext constraint_param() throws RecognitionException {
		Constraint_paramContext _localctx = new Constraint_paramContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_constraint_param);
		try {
			setState(186);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(178);
				((Constraint_paramContext)_localctx).fieldparam = field();
				setState(179);
				((Constraint_paramContext)_localctx).opparam = compare_operator();
				setState(180);
				((Constraint_paramContext)_localctx).anotherfieldparam = field();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(182);
				((Constraint_paramContext)_localctx).fieldparam = field();
				setState(183);
				((Constraint_paramContext)_localctx).opparam = compare_operator();
				setState(184);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitConvert_param(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Convert_paramContext convert_param() throws RecognitionException {
		Convert_paramContext _localctx = new Convert_paramContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_convert_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			((Convert_paramContext)_localctx).fieldparam = field();
			setState(189);
			match(T__16);
			setState(190);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitLsa_param(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lsa_paramContext lsa_param() throws RecognitionException {
		Lsa_paramContext _localctx = new Lsa_paramContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_lsa_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			((Lsa_paramContext)_localctx).fieldparam = field();
			setState(193);
			((Lsa_paramContext)_localctx).from = number();
			setState(194);
			((Lsa_paramContext)_localctx).to = number();
			setState(195);
			((Lsa_paramContext)_localctx).key = value();
			setState(196);
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
		enterRule(_localctx, 46, RULE_table);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			match(T__17);
			setState(199);
			((TableContext)_localctx).tablenameparam = match(ID);
			setState(200);
			match(T__18);
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
		enterRule(_localctx, 48, RULE_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(T__17);
			setState(204);
			((FieldContext)_localctx).tablenameparam = match(ID);
			setState(205);
			match(T__19);
			setState(206);
			((FieldContext)_localctx).fieldnameparam = match(ID);
			setState(207);
			match(T__18);
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
		enterRule(_localctx, 50, RULE_pattern);

		  ((PatternContext)_localctx).patterndesc =  new ArrayList<PatternDescription>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(210);
				match(T__20);
				setState(211);
				((PatternContext)_localctx).countparam = count_pattern();
				setState(212);
				((PatternContext)_localctx).recordconditionparam = record_condition();
				setState(213);
				match(T__21);
				 _localctx.patterndesc.add(new PatternDescription(((PatternContext)_localctx).countparam.count, ((PatternContext)_localctx).recordconditionparam.recordcondition));
				}
				}
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__20 );
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
		enterRule(_localctx, 52, RULE_record_condition);
		try {
			setState(227);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(220);
				((Record_conditionContext)_localctx).tableparam = table();
				 ((Record_conditionContext)_localctx).recordcondition =  new RecordOccurrenceCondition(((Record_conditionContext)_localctx).tableparam.tablename);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(223);
				((Record_conditionContext)_localctx).fieldparam = ((Record_conditionContext)_localctx).field = field();
				setState(224);
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
		enterRule(_localctx, 54, RULE_count_pattern);
		try {
			setState(233);
			switch (_input.LA(1)) {
			case MULTIPLY:
				enterOuterAlt(_localctx, 1);
				{
				setState(229);
				((Count_patternContext)_localctx).wildcard = match(MULTIPLY);
				 ((Count_patternContext)_localctx).count =  new MultipleCount();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(231);
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
		enterRule(_localctx, 56, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
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
		enterRule(_localctx, 58, RULE_compare_operator);
		try {
			setState(250);
			switch (_input.LA(1)) {
			case EQ:
				enterOuterAlt(_localctx, 1);
				{
				setState(238);
				((Compare_operatorContext)_localctx).opparam = match(EQ);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.EQ;
				}
				break;
			case NEQ:
				enterOuterAlt(_localctx, 2);
				{
				setState(240);
				((Compare_operatorContext)_localctx).opparam = match(NEQ);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.NEQ;
				}
				break;
			case GEQ:
				enterOuterAlt(_localctx, 3);
				{
				setState(242);
				((Compare_operatorContext)_localctx).opparam = match(GEQ);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.GEQ;
				}
				break;
			case G:
				enterOuterAlt(_localctx, 4);
				{
				setState(244);
				((Compare_operatorContext)_localctx).opparam = match(G);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.G;
				}
				break;
			case LEQ:
				enterOuterAlt(_localctx, 5);
				{
				setState(246);
				((Compare_operatorContext)_localctx).opparam = match(LEQ);
				 ((Compare_operatorContext)_localctx).op =  CompareOperator.LEQ;
				}
				break;
			case L:
				enterOuterAlt(_localctx, 6);
				{
				setState(248);
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
		enterRule(_localctx, 60, RULE_calc_operator);
		try {
			setState(262);
			switch (_input.LA(1)) {
			case MULTIPLY:
				enterOuterAlt(_localctx, 1);
				{
				setState(252);
				((Calc_operatorContext)_localctx).opparam = match(MULTIPLY);
				 ((Calc_operatorContext)_localctx).op =  CalcOperator.MULTIPLY;
				}
				break;
			case DIVIDE:
				enterOuterAlt(_localctx, 2);
				{
				setState(254);
				((Calc_operatorContext)_localctx).opparam = match(DIVIDE);
				 ((Calc_operatorContext)_localctx).op =  CalcOperator.DIVIDE;
				}
				break;
			case PLUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(256);
				((Calc_operatorContext)_localctx).opparam = match(PLUS);
				 ((Calc_operatorContext)_localctx).op =  CalcOperator.PLUS;
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 4);
				{
				setState(258);
				((Calc_operatorContext)_localctx).opparma = match(MINUS);
				 ((Calc_operatorContext)_localctx).op =  CalcOperator.MINUS;
				}
				break;
			case MODULO:
				enterOuterAlt(_localctx, 5);
				{
				setState(260);
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
		enterRule(_localctx, 62, RULE_compute_operator);
		try {
			setState(276);
			switch (_input.LA(1)) {
			case AVG:
				enterOuterAlt(_localctx, 1);
				{
				setState(264);
				((Compute_operatorContext)_localctx).opparam = match(AVG);
				 ((Compute_operatorContext)_localctx).op =  ComputeOperator.AVG;
				}
				break;
			case COUNT:
				enterOuterAlt(_localctx, 2);
				{
				setState(266);
				((Compute_operatorContext)_localctx).opparam = match(COUNT);
				 ((Compute_operatorContext)_localctx).op =  ComputeOperator.COUNT;
				}
				break;
			case MAX:
				enterOuterAlt(_localctx, 3);
				{
				setState(268);
				((Compute_operatorContext)_localctx).opparam = match(MAX);
				 ((Compute_operatorContext)_localctx).op =  ComputeOperator.MAX;
				}
				break;
			case MIN:
				enterOuterAlt(_localctx, 4);
				{
				setState(270);
				((Compute_operatorContext)_localctx).opparam = match(MIN);
				 ((Compute_operatorContext)_localctx).op =  ComputeOperator.MIN;
				}
				break;
			case STDDEV:
				enterOuterAlt(_localctx, 5);
				{
				setState(272);
				((Compute_operatorContext)_localctx).opparam = match(STDDEV);
				 ((Compute_operatorContext)_localctx).op =  ComputeOperator.STDEV;
				}
				break;
			case SUM:
				enterOuterAlt(_localctx, 6);
				{
				setState(274);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitFormula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_formula);
		try {
			setState(293);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(278);
				((FormulaContext)_localctx).fieldparam = field();
				setState(279);
				((FormulaContext)_localctx).opparam = calc_operator();
				setState(280);
				((FormulaContext)_localctx).anotherfieldparam = field();
				 ((FormulaContext)_localctx).form =  new Formula(((FormulaContext)_localctx).fieldparam.fieldname, ((FormulaContext)_localctx).opparam.op, ((FormulaContext)_localctx).anotherfieldparam.fieldname);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(283);
				((FormulaContext)_localctx).fieldparam = field();
				setState(284);
				((FormulaContext)_localctx).opparam = calc_operator();
				setState(285);
				((FormulaContext)_localctx).valueparam = number();
				 ((FormulaContext)_localctx).form =  new Formula(((FormulaContext)_localctx).fieldparam.fieldname, ((FormulaContext)_localctx).opparam.op, ((FormulaContext)_localctx).valueparam.val);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(288);
				((FormulaContext)_localctx).fieldparam = field();
				setState(289);
				((FormulaContext)_localctx).opparam = calc_operator();
				setState(290);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_condition);
		try {
			setState(305);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(295);
				((ConditionContext)_localctx).opparam = compare_operator();
				setState(296);
				((ConditionContext)_localctx).valueparam = value();
				 ((ConditionContext)_localctx).cond =  new Condition(((ConditionContext)_localctx).opparam.op, ((ConditionContext)_localctx).valueparam.val);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(299);
				((ConditionContext)_localctx).opparam = compare_operator();
				setState(300);
				((ConditionContext)_localctx).valueparam = value();
				setState(301);
				match(T__22);
				setState(302);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitRange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RangeContext range() throws RecognitionException {
		RangeContext _localctx = new RangeContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			match(G);
			setState(308);
			((RangeContext)_localctx).g = value();
			setState(309);
			match(T__22);
			setState(310);
			match(L);
			setState(311);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalysisLangVisitor ) return ((AnalysisLangVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_value);
		try {
			setState(322);
			switch (_input.LA(1)) {
			case T__23:
				enterOuterAlt(_localctx, 1);
				{
				setState(313);
				((ValueContext)_localctx).dataparam = date();
				 ((ValueContext)_localctx).val =  ((ValueContext)_localctx).dataparam.val;
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(316);
				((ValueContext)_localctx).numparam = number();
				 ((ValueContext)_localctx).val =  ((ValueContext)_localctx).numparam.val;
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(319);
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
		enterRule(_localctx, 72, RULE_date);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			match(T__23);
			setState(325);
			((DateContext)_localctx).yearparam = match(NUMBER);
			setState(326);
			match(MINUS);
			setState(327);
			((DateContext)_localctx).monthparam = match(NUMBER);
			setState(328);
			match(MINUS);
			setState(329);
			((DateContext)_localctx).dayparam = match(NUMBER);
			setState(330);
			match(T__24);

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
		enterRule(_localctx, 74, RULE_text);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\61\u0153\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\7\2P\n\2\f\2\16\2S\13"+
		"\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3_\n\3\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\5\16\u008a\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\5\17\u0095\n\17\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u009d\n"+
		"\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\5\26\u00bd\n\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\6\33\u00db\n\33\r\33\16\33\u00dc\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\5\34\u00e6\n\34\3\35\3\35\3\35\3\35\5\35"+
		"\u00ec\n\35\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\5\37\u00fd\n\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u0109"+
		"\n \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\5!\u0117\n!\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u0128\n\"\3#\3#\3#\3#\3"+
		"#\3#\3#\3#\3#\3#\5#\u0134\n#\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3"+
		"%\3%\5%\u0145\n%\3&\3&\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\2\2(\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJL\2\2"+
		"\u0151\2Q\3\2\2\2\4^\3\2\2\2\6`\3\2\2\2\bc\3\2\2\2\nf\3\2\2\2\fi\3\2\2"+
		"\2\16l\3\2\2\2\20o\3\2\2\2\22r\3\2\2\2\24u\3\2\2\2\26x\3\2\2\2\30{\3\2"+
		"\2\2\32\u0089\3\2\2\2\34\u0094\3\2\2\2\36\u009c\3\2\2\2 \u009e\3\2\2\2"+
		"\"\u00a2\3\2\2\2$\u00a8\3\2\2\2&\u00ac\3\2\2\2(\u00b0\3\2\2\2*\u00bc\3"+
		"\2\2\2,\u00be\3\2\2\2.\u00c2\3\2\2\2\60\u00c8\3\2\2\2\62\u00cd\3\2\2\2"+
		"\64\u00da\3\2\2\2\66\u00e5\3\2\2\28\u00eb\3\2\2\2:\u00ed\3\2\2\2<\u00fc"+
		"\3\2\2\2>\u0108\3\2\2\2@\u0116\3\2\2\2B\u0127\3\2\2\2D\u0133\3\2\2\2F"+
		"\u0135\3\2\2\2H\u0144\3\2\2\2J\u0146\3\2\2\2L\u014f\3\2\2\2NP\5\4\3\2"+
		"ON\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2R\3\3\2\2\2SQ\3\2\2\2T_\5\6\4"+
		"\2U_\5\b\5\2V_\5\n\6\2W_\5\f\7\2X_\5\22\n\2Y_\5\16\b\2Z_\5\24\13\2[_\5"+
		"\26\f\2\\_\5\20\t\2]_\5\30\r\2^T\3\2\2\2^U\3\2\2\2^V\3\2\2\2^W\3\2\2\2"+
		"^X\3\2\2\2^Y\3\2\2\2^Z\3\2\2\2^[\3\2\2\2^\\\3\2\2\2^]\3\2\2\2_\5\3\2\2"+
		"\2`a\7\3\2\2ab\5\32\16\2b\7\3\2\2\2cd\7\4\2\2de\5\34\17\2e\t\3\2\2\2f"+
		"g\7\5\2\2gh\5 \21\2h\13\3\2\2\2ij\7\6\2\2jk\5\"\22\2k\r\3\2\2\2lm\7\7"+
		"\2\2mn\5$\23\2n\17\3\2\2\2op\7\b\2\2pq\5&\24\2q\21\3\2\2\2rs\7\t\2\2s"+
		"t\5(\25\2t\23\3\2\2\2uv\7\n\2\2vw\5*\26\2w\25\3\2\2\2xy\7\13\2\2yz\5,"+
		"\27\2z\27\3\2\2\2{|\7\f\2\2|}\5.\30\2}\31\3\2\2\2~\177\5\62\32\2\177\u0080"+
		"\5\62\32\2\u0080\u0081\5\62\32\2\u0081\u0082\5H%\2\u0082\u0083\5H%\2\u0083"+
		"\u008a\3\2\2\2\u0084\u0085\5\62\32\2\u0085\u0086\5\62\32\2\u0086\u0087"+
		"\5H%\2\u0087\u0088\5H%\2\u0088\u008a\3\2\2\2\u0089~\3\2\2\2\u0089\u0084"+
		"\3\2\2\2\u008a\33\3\2\2\2\u008b\u008c\5\62\32\2\u008c\u008d\7\r\2\2\u008d"+
		"\u008e\5F$\2\u008e\u0095\3\2\2\2\u008f\u0090\5\62\32\2\u0090\u0091\7\r"+
		"\2\2\u0091\u0092\5\36\20\2\u0092\u0093\5:\36\2\u0093\u0095\3\2\2\2\u0094"+
		"\u008b\3\2\2\2\u0094\u008f\3\2\2\2\u0095\35\3\2\2\2\u0096\u0097\7\16\2"+
		"\2\u0097\u009d\b\20\1\2\u0098\u0099\7\17\2\2\u0099\u009d\b\20\1\2\u009a"+
		"\u009b\7\20\2\2\u009b\u009d\b\20\1\2\u009c\u0096\3\2\2\2\u009c\u0098\3"+
		"\2\2\2\u009c\u009a\3\2\2\2\u009d\37\3\2\2\2\u009e\u009f\5\60\31\2\u009f"+
		"\u00a0\5:\36\2\u00a0\u00a1\5\4\3\2\u00a1!\3\2\2\2\u00a2\u00a3\5\60\31"+
		"\2\u00a3\u00a4\7\21\2\2\u00a4\u00a5\5\64\33\2\u00a5\u00a6\7\22\2\2\u00a6"+
		"\u00a7\5L\'\2\u00a7#\3\2\2\2\u00a8\u00a9\5\62\32\2\u00a9\u00aa\5<\37\2"+
		"\u00aa\u00ab\5\62\32\2\u00ab%\3\2\2\2\u00ac\u00ad\5\60\31\2\u00ad\u00ae"+
		"\5@!\2\u00ae\u00af\5\62\32\2\u00af\'\3\2\2\2\u00b0\u00b1\5\62\32\2\u00b1"+
		"\u00b2\7\23\2\2\u00b2\u00b3\5\62\32\2\u00b3)\3\2\2\2\u00b4\u00b5\5\62"+
		"\32\2\u00b5\u00b6\5<\37\2\u00b6\u00b7\5\62\32\2\u00b7\u00bd\3\2\2\2\u00b8"+
		"\u00b9\5\62\32\2\u00b9\u00ba\5<\37\2\u00ba\u00bb\5H%\2\u00bb\u00bd\3\2"+
		"\2\2\u00bc\u00b4\3\2\2\2\u00bc\u00b8\3\2\2\2\u00bd+\3\2\2\2\u00be\u00bf"+
		"\5\62\32\2\u00bf\u00c0\7\23\2\2\u00c0\u00c1\5B\"\2\u00c1-\3\2\2\2\u00c2"+
		"\u00c3\5\62\32\2\u00c3\u00c4\5:\36\2\u00c4\u00c5\5:\36\2\u00c5\u00c6\5"+
		"H%\2\u00c6\u00c7\5H%\2\u00c7/\3\2\2\2\u00c8\u00c9\7\24\2\2\u00c9\u00ca"+
		"\7\34\2\2\u00ca\u00cb\7\25\2\2\u00cb\u00cc\b\31\1\2\u00cc\61\3\2\2\2\u00cd"+
		"\u00ce\7\24\2\2\u00ce\u00cf\7\34\2\2\u00cf\u00d0\7\26\2\2\u00d0\u00d1"+
		"\7\34\2\2\u00d1\u00d2\7\25\2\2\u00d2\u00d3\b\32\1\2\u00d3\63\3\2\2\2\u00d4"+
		"\u00d5\7\27\2\2\u00d5\u00d6\58\35\2\u00d6\u00d7\5\66\34\2\u00d7\u00d8"+
		"\7\30\2\2\u00d8\u00d9\b\33\1\2\u00d9\u00db\3\2\2\2\u00da\u00d4\3\2\2\2"+
		"\u00db\u00dc\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\65"+
		"\3\2\2\2\u00de\u00df\5\60\31\2\u00df\u00e0\b\34\1\2\u00e0\u00e6\3\2\2"+
		"\2\u00e1\u00e2\5\62\32\2\u00e2\u00e3\5D#\2\u00e3\u00e4\b\34\1\2\u00e4"+
		"\u00e6\3\2\2\2\u00e5\u00de\3\2\2\2\u00e5\u00e1\3\2\2\2\u00e6\67\3\2\2"+
		"\2\u00e7\u00e8\7#\2\2\u00e8\u00ec\b\35\1\2\u00e9\u00ea\7.\2\2\u00ea\u00ec"+
		"\b\35\1\2\u00eb\u00e7\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec9\3\2\2\2\u00ed"+
		"\u00ee\7.\2\2\u00ee\u00ef\b\36\1\2\u00ef;\3\2\2\2\u00f0\u00f1\7\35\2\2"+
		"\u00f1\u00fd\b\37\1\2\u00f2\u00f3\7\36\2\2\u00f3\u00fd\b\37\1\2\u00f4"+
		"\u00f5\7\37\2\2\u00f5\u00fd\b\37\1\2\u00f6\u00f7\7 \2\2\u00f7\u00fd\b"+
		"\37\1\2\u00f8\u00f9\7!\2\2\u00f9\u00fd\b\37\1\2\u00fa\u00fb\7\"\2\2\u00fb"+
		"\u00fd\b\37\1\2\u00fc\u00f0\3\2\2\2\u00fc\u00f2\3\2\2\2\u00fc\u00f4\3"+
		"\2\2\2\u00fc\u00f6\3\2\2\2\u00fc\u00f8\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fd"+
		"=\3\2\2\2\u00fe\u00ff\7#\2\2\u00ff\u0109\b \1\2\u0100\u0101\7$\2\2\u0101"+
		"\u0109\b \1\2\u0102\u0103\7%\2\2\u0103\u0109\b \1\2\u0104\u0105\7&\2\2"+
		"\u0105\u0109\b \1\2\u0106\u0107\7\'\2\2\u0107\u0109\b \1\2\u0108\u00fe"+
		"\3\2\2\2\u0108\u0100\3\2\2\2\u0108\u0102\3\2\2\2\u0108\u0104\3\2\2\2\u0108"+
		"\u0106\3\2\2\2\u0109?\3\2\2\2\u010a\u010b\7(\2\2\u010b\u0117\b!\1\2\u010c"+
		"\u010d\7)\2\2\u010d\u0117\b!\1\2\u010e\u010f\7*\2\2\u010f\u0117\b!\1\2"+
		"\u0110\u0111\7+\2\2\u0111\u0117\b!\1\2\u0112\u0113\7-\2\2\u0113\u0117"+
		"\b!\1\2\u0114\u0115\7,\2\2\u0115\u0117\b!\1\2\u0116\u010a\3\2\2\2\u0116"+
		"\u010c\3\2\2\2\u0116\u010e\3\2\2\2\u0116\u0110\3\2\2\2\u0116\u0112\3\2"+
		"\2\2\u0116\u0114\3\2\2\2\u0117A\3\2\2\2\u0118\u0119\5\62\32\2\u0119\u011a"+
		"\5> \2\u011a\u011b\5\62\32\2\u011b\u011c\b\"\1\2\u011c\u0128\3\2\2\2\u011d"+
		"\u011e\5\62\32\2\u011e\u011f\5> \2\u011f\u0120\5:\36\2\u0120\u0121\b\""+
		"\1\2\u0121\u0128\3\2\2\2\u0122\u0123\5\62\32\2\u0123\u0124\5> \2\u0124"+
		"\u0125\5B\"\2\u0125\u0126\b\"\1\2\u0126\u0128\3\2\2\2\u0127\u0118\3\2"+
		"\2\2\u0127\u011d\3\2\2\2\u0127\u0122\3\2\2\2\u0128C\3\2\2\2\u0129\u012a"+
		"\5<\37\2\u012a\u012b\5H%\2\u012b\u012c\b#\1\2\u012c\u0134\3\2\2\2\u012d"+
		"\u012e\5<\37\2\u012e\u012f\5H%\2\u012f\u0130\7\31\2\2\u0130\u0131\5D#"+
		"\2\u0131\u0132\b#\1\2\u0132\u0134\3\2\2\2\u0133\u0129\3\2\2\2\u0133\u012d"+
		"\3\2\2\2\u0134E\3\2\2\2\u0135\u0136\7 \2\2\u0136\u0137\5H%\2\u0137\u0138"+
		"\7\31\2\2\u0138\u0139\7\"\2\2\u0139\u013a\5H%\2\u013aG\3\2\2\2\u013b\u013c"+
		"\5J&\2\u013c\u013d\b%\1\2\u013d\u0145\3\2\2\2\u013e\u013f\5:\36\2\u013f"+
		"\u0140\b%\1\2\u0140\u0145\3\2\2\2\u0141\u0142\5L\'\2\u0142\u0143\b%\1"+
		"\2\u0143\u0145\3\2\2\2\u0144\u013b\3\2\2\2\u0144\u013e\3\2\2\2\u0144\u0141"+
		"\3\2\2\2\u0145I\3\2\2\2\u0146\u0147\7\32\2\2\u0147\u0148\7.\2\2\u0148"+
		"\u0149\7&\2\2\u0149\u014a\7.\2\2\u014a\u014b\7&\2\2\u014b\u014c\7.\2\2"+
		"\u014c\u014d\7\33\2\2\u014d\u014e\b&\1\2\u014eK\3\2\2\2\u014f\u0150\7"+
		"\60\2\2\u0150\u0151\b\'\1\2\u0151M\3\2\2\2\21Q^\u0089\u0094\u009c\u00bc"+
		"\u00dc\u00e5\u00eb\u00fc\u0108\u0116\u0127\u0133\u0144";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}