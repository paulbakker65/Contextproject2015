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

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AnalysisLangLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "ID", "EQ", "NEQ", 
		"GEQ", "G", "LEQ", "L", "MULTIPLY", "DIVIDE", "PLUS", "MINUS", "MODULO", 
		"AVG", "COUNT", "MAX", "MIN", "SUM", "STDDEV", "NUMBER", "INT", "FLOAT", 
		"WS", "STRING", "CHAR", "EXPONENT", "HEX_NUM", "ESC_SEQ"
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


	public AnalysisLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "AnalysisLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2/\u0174\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23"+
		"\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\30\3\30\3\31\3\31\7\31\u00e1\n\31\f\31\16\31\u00e4\13\31\3\32"+
		"\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37"+
		"\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3"+
		"&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3"+
		")\3*\3*\3*\3*\3*\3*\3*\3*\3*\3+\3+\5+\u012b\n+\3,\6,\u012e\n,\r,\16,\u012f"+
		"\3-\6-\u0133\n-\r-\16-\u0134\3-\3-\7-\u0139\n-\f-\16-\u013c\13-\3-\5-"+
		"\u013f\n-\3-\3-\6-\u0143\n-\r-\16-\u0144\3-\5-\u0148\n-\3-\6-\u014b\n"+
		"-\r-\16-\u014c\3-\5-\u0150\n-\3.\3.\3.\3.\3/\3/\3/\7/\u0159\n/\f/\16/"+
		"\u015c\13/\3/\3/\3\60\3\60\3\60\5\60\u0163\n\60\3\60\3\60\3\61\3\61\5"+
		"\61\u0169\n\61\3\61\6\61\u016c\n\61\r\61\16\61\u016d\3\62\3\62\3\63\3"+
		"\63\3\63\2\2\64\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W\2Y\2[-]._/a\2c\2e\2\3\2"+
		"\13\5\2C\\aac|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\4\2$$^^\4\2))^^\4\2G"+
		"Ggg\4\2--//\5\2\62;CHch\n\2$$))^^ddhhppttvv\u017e\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2[\3\2\2\2\2"+
		"]\3\2\2\2\2_\3\2\2\2\3g\3\2\2\2\5o\3\2\2\2\7u\3\2\2\2\t\u0084\3\2\2\2"+
		"\13\u0089\3\2\2\2\r\u0091\3\2\2\2\17\u0099\3\2\2\2\21\u00a4\3\2\2\2\23"+
		"\u00a8\3\2\2\2\25\u00ae\3\2\2\2\27\u00b3\3\2\2\2\31\u00b9\3\2\2\2\33\u00bd"+
		"\3\2\2\2\35\u00c0\3\2\2\2\37\u00c3\3\2\2\2!\u00c6\3\2\2\2#\u00c8\3\2\2"+
		"\2%\u00ca\3\2\2\2\'\u00ce\3\2\2\2)\u00d0\3\2\2\2+\u00d2\3\2\2\2-\u00d6"+
		"\3\2\2\2/\u00dc\3\2\2\2\61\u00de\3\2\2\2\63\u00e5\3\2\2\2\65\u00e8\3\2"+
		"\2\2\67\u00eb\3\2\2\29\u00ee\3\2\2\2;\u00f0\3\2\2\2=\u00f3\3\2\2\2?\u00f5"+
		"\3\2\2\2A\u00f7\3\2\2\2C\u00f9\3\2\2\2E\u00fb\3\2\2\2G\u00fd\3\2\2\2I"+
		"\u00ff\3\2\2\2K\u0105\3\2\2\2M\u010d\3\2\2\2O\u0113\3\2\2\2Q\u0119\3\2"+
		"\2\2S\u011f\3\2\2\2U\u012a\3\2\2\2W\u012d\3\2\2\2Y\u014f\3\2\2\2[\u0151"+
		"\3\2\2\2]\u0155\3\2\2\2_\u015f\3\2\2\2a\u0166\3\2\2\2c\u016f\3\2\2\2e"+
		"\u0171\3\2\2\2gh\7D\2\2hi\7G\2\2ij\7V\2\2jk\7Y\2\2kl\7G\2\2lm\7G\2\2m"+
		"n\7P\2\2n\4\3\2\2\2op\7E\2\2pq\7J\2\2qr\7W\2\2rs\7P\2\2st\7M\2\2t\6\3"+
		"\2\2\2uv\7H\2\2vw\7Q\2\2wx\7T\2\2xy\7\"\2\2yz\7G\2\2z{\7C\2\2{|\7E\2\2"+
		"|}\7J\2\2}~\7\"\2\2~\177\7E\2\2\177\u0080\7J\2\2\u0080\u0081\7W\2\2\u0081"+
		"\u0082\7P\2\2\u0082\u0083\7M\2\2\u0083\b\3\2\2\2\u0084\u0085\7E\2\2\u0085"+
		"\u0086\7Q\2\2\u0086\u0087\7F\2\2\u0087\u0088\7G\2\2\u0088\n\3\2\2\2\u0089"+
		"\u008a\7E\2\2\u008a\u008b\7Q\2\2\u008b\u008c\7O\2\2\u008c\u008d\7R\2\2"+
		"\u008d\u008e\7W\2\2\u008e\u008f\7V\2\2\u008f\u0090\7G\2\2\u0090\f\3\2"+
		"\2\2\u0091\u0092\7E\2\2\u0092\u0093\7Q\2\2\u0093\u0094\7P\2\2\u0094\u0095"+
		"\7P\2\2\u0095\u0096\7G\2\2\u0096\u0097\7E\2\2\u0097\u0098\7V\2\2\u0098"+
		"\16\3\2\2\2\u0099\u009a\7E\2\2\u009a\u009b\7Q\2\2\u009b\u009c\7P\2\2\u009c"+
		"\u009d\7U\2\2\u009d\u009e\7V\2\2\u009e\u009f\7T\2\2\u009f\u00a0\7C\2\2"+
		"\u00a0\u00a1\7K\2\2\u00a1\u00a2\7P\2\2\u00a2\u00a3\7V\2\2\u00a3\20\3\2"+
		"\2\2\u00a4\u00a5\7N\2\2\u00a5\u00a6\7U\2\2\u00a6\u00a7\7C\2\2\u00a7\22"+
		"\3\2\2\2\u00a8\u00a9\7W\2\2\u00a9\u00aa\7U\2\2\u00aa\u00ab\7K\2\2\u00ab"+
		"\u00ac\7P\2\2\u00ac\u00ad\7I\2\2\u00ad\24\3\2\2\2\u00ae\u00af\7[\2\2\u00af"+
		"\u00b0\7G\2\2\u00b0\u00b1\7C\2\2\u00b1\u00b2\7T\2\2\u00b2\26\3\2\2\2\u00b3"+
		"\u00b4\7O\2\2\u00b4\u00b5\7Q\2\2\u00b5\u00b6\7P\2\2\u00b6\u00b7\7V\2\2"+
		"\u00b7\u00b8\7J\2\2\u00b8\30\3\2\2\2\u00b9\u00ba\7F\2\2\u00ba\u00bb\7"+
		"C\2\2\u00bb\u00bc\7[\2\2\u00bc\32\3\2\2\2\u00bd\u00be\7Q\2\2\u00be\u00bf"+
		"\7P\2\2\u00bf\34\3\2\2\2\u00c0\u00c1\7C\2\2\u00c1\u00c2\7U\2\2\u00c2\36"+
		"\3\2\2\2\u00c3\u00c4\7V\2\2\u00c4\u00c5\7Q\2\2\u00c5 \3\2\2\2\u00c6\u00c7"+
		"\7]\2\2\u00c7\"\3\2\2\2\u00c8\u00c9\7_\2\2\u00c9$\3\2\2\2\u00ca\u00cb"+
		"\7_\2\2\u00cb\u00cc\7\60\2\2\u00cc\u00cd\7]\2\2\u00cd&\3\2\2\2\u00ce\u00cf"+
		"\7}\2\2\u00cf(\3\2\2\2\u00d0\u00d1\7\177\2\2\u00d1*\3\2\2\2\u00d2\u00d3"+
		"\7C\2\2\u00d3\u00d4\7P\2\2\u00d4\u00d5\7F\2\2\u00d5,\3\2\2\2\u00d6\u00d7"+
		"\7F\2\2\u00d7\u00d8\7C\2\2\u00d8\u00d9\7V\2\2\u00d9\u00da\7G\2\2\u00da"+
		"\u00db\7*\2\2\u00db.\3\2\2\2\u00dc\u00dd\7+\2\2\u00dd\60\3\2\2\2\u00de"+
		"\u00e2\t\2\2\2\u00df\u00e1\t\3\2\2\u00e0\u00df\3\2\2\2\u00e1\u00e4\3\2"+
		"\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\62\3\2\2\2\u00e4\u00e2"+
		"\3\2\2\2\u00e5\u00e6\7?\2\2\u00e6\u00e7\7?\2\2\u00e7\64\3\2\2\2\u00e8"+
		"\u00e9\7#\2\2\u00e9\u00ea\7?\2\2\u00ea\66\3\2\2\2\u00eb\u00ec\7@\2\2\u00ec"+
		"\u00ed\7?\2\2\u00ed8\3\2\2\2\u00ee\u00ef\7@\2\2\u00ef:\3\2\2\2\u00f0\u00f1"+
		"\7>\2\2\u00f1\u00f2\7?\2\2\u00f2<\3\2\2\2\u00f3\u00f4\7>\2\2\u00f4>\3"+
		"\2\2\2\u00f5\u00f6\7,\2\2\u00f6@\3\2\2\2\u00f7\u00f8\7\61\2\2\u00f8B\3"+
		"\2\2\2\u00f9\u00fa\7-\2\2\u00faD\3\2\2\2\u00fb\u00fc\7/\2\2\u00fcF\3\2"+
		"\2\2\u00fd\u00fe\7\'\2\2\u00feH\3\2\2\2\u00ff\u0100\7C\2\2\u0100\u0101"+
		"\7X\2\2\u0101\u0102\7I\2\2\u0102\u0103\7*\2\2\u0103\u0104\7+\2\2\u0104"+
		"J\3\2\2\2\u0105\u0106\7E\2\2\u0106\u0107\7Q\2\2\u0107\u0108\7W\2\2\u0108"+
		"\u0109\7P\2\2\u0109\u010a\7V\2\2\u010a\u010b\7*\2\2\u010b\u010c\7+\2\2"+
		"\u010cL\3\2\2\2\u010d\u010e\7O\2\2\u010e\u010f\7C\2\2\u010f\u0110\7Z\2"+
		"\2\u0110\u0111\7*\2\2\u0111\u0112\7+\2\2\u0112N\3\2\2\2\u0113\u0114\7"+
		"O\2\2\u0114\u0115\7K\2\2\u0115\u0116\7P\2\2\u0116\u0117\7*\2\2\u0117\u0118"+
		"\7+\2\2\u0118P\3\2\2\2\u0119\u011a\7U\2\2\u011a\u011b\7W\2\2\u011b\u011c"+
		"\7O\2\2\u011c\u011d\7*\2\2\u011d\u011e\7+\2\2\u011eR\3\2\2\2\u011f\u0120"+
		"\7U\2\2\u0120\u0121\7V\2\2\u0121\u0122\7F\2\2\u0122\u0123\7F\2\2\u0123"+
		"\u0124\7G\2\2\u0124\u0125\7X\2\2\u0125\u0126\7*\2\2\u0126\u0127\7+\2\2"+
		"\u0127T\3\2\2\2\u0128\u012b\5W,\2\u0129\u012b\5Y-\2\u012a\u0128\3\2\2"+
		"\2\u012a\u0129\3\2\2\2\u012bV\3\2\2\2\u012c\u012e\4\62;\2\u012d\u012c"+
		"\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130"+
		"X\3\2\2\2\u0131\u0133\4\62;\2\u0132\u0131\3\2\2\2\u0133\u0134\3\2\2\2"+
		"\u0134\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u013a"+
		"\7\60\2\2\u0137\u0139\4\62;\2\u0138\u0137\3\2\2\2\u0139\u013c\3\2\2\2"+
		"\u013a\u0138\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013e\3\2\2\2\u013c\u013a"+
		"\3\2\2\2\u013d\u013f\5a\61\2\u013e\u013d\3\2\2\2\u013e\u013f\3\2\2\2\u013f"+
		"\u0150\3\2\2\2\u0140\u0142\7\60\2\2\u0141\u0143\4\62;\2\u0142\u0141\3"+
		"\2\2\2\u0143\u0144\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145"+
		"\u0147\3\2\2\2\u0146\u0148\5a\61\2\u0147\u0146\3\2\2\2\u0147\u0148\3\2"+
		"\2\2\u0148\u0150\3\2\2\2\u0149\u014b\4\62;\2\u014a\u0149\3\2\2\2\u014b"+
		"\u014c\3\2\2\2\u014c\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014e\3\2"+
		"\2\2\u014e\u0150\5a\61\2\u014f\u0132\3\2\2\2\u014f\u0140\3\2\2\2\u014f"+
		"\u014a\3\2\2\2\u0150Z\3\2\2\2\u0151\u0152\t\4\2\2\u0152\u0153\3\2\2\2"+
		"\u0153\u0154\b.\2\2\u0154\\\3\2\2\2\u0155\u015a\7$\2\2\u0156\u0159\5e"+
		"\63\2\u0157\u0159\n\5\2\2\u0158\u0156\3\2\2\2\u0158\u0157\3\2\2\2\u0159"+
		"\u015c\3\2\2\2\u015a\u0158\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u015d\3\2"+
		"\2\2\u015c\u015a\3\2\2\2\u015d\u015e\7$\2\2\u015e^\3\2\2\2\u015f\u0162"+
		"\7)\2\2\u0160\u0163\5e\63\2\u0161\u0163\n\6\2\2\u0162\u0160\3\2\2\2\u0162"+
		"\u0161\3\2\2\2\u0163\u0164\3\2\2\2\u0164\u0165\7)\2\2\u0165`\3\2\2\2\u0166"+
		"\u0168\t\7\2\2\u0167\u0169\t\b\2\2\u0168\u0167\3\2\2\2\u0168\u0169\3\2"+
		"\2\2\u0169\u016b\3\2\2\2\u016a\u016c\4\62;\2\u016b\u016a\3\2\2\2\u016c"+
		"\u016d\3\2\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016eb\3\2\2\2"+
		"\u016f\u0170\t\t\2\2\u0170d\3\2\2\2\u0171\u0172\7^\2\2\u0172\u0173\t\n"+
		"\2\2\u0173f\3\2\2\2\22\2\u00e2\u012a\u012f\u0134\u013a\u013e\u0144\u0147"+
		"\u014c\u014f\u0158\u015a\u0162\u0168\u016d\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}