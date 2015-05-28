// Generated from AnalysisLang.g4 by ANTLR 4.5
package scriptlang;

import scriptlang.extra.*;
import enums.*;
import table.value.*;
import operations.FilterOperation;
import java.util.*;
import java.text.*;

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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, ID=16, EQ=17, 
		NEQ=18, GEQ=19, G=20, LEQ=21, L=22, MULTIPLY=23, DIVIDE=24, PLUS=25, MINUS=26, 
		MODULO=27, NUMBER=28, WS=29, STRING=30, CHAR=31;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "ID", "EQ", "NEQ", 
		"GEQ", "G", "LEQ", "L", "MULTIPLY", "DIVIDE", "PLUS", "MINUS", "MODULO", 
		"NUMBER", "INT", "FLOAT", "WS", "STRING", "CHAR", "EXPONENT", "HEX_NUM", 
		"ESC_SEQ"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2!\u0109\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3"+
		"\20\3\20\3\20\3\20\3\21\3\21\7\21\u009f\n\21\f\21\16\21\u00a2\13\21\3"+
		"\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3"+
		"\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\5"+
		"\35\u00c0\n\35\3\36\6\36\u00c3\n\36\r\36\16\36\u00c4\3\37\6\37\u00c8\n"+
		"\37\r\37\16\37\u00c9\3\37\3\37\7\37\u00ce\n\37\f\37\16\37\u00d1\13\37"+
		"\3\37\5\37\u00d4\n\37\3\37\3\37\6\37\u00d8\n\37\r\37\16\37\u00d9\3\37"+
		"\5\37\u00dd\n\37\3\37\6\37\u00e0\n\37\r\37\16\37\u00e1\3\37\5\37\u00e5"+
		"\n\37\3 \3 \3 \3 \3!\3!\3!\7!\u00ee\n!\f!\16!\u00f1\13!\3!\3!\3\"\3\""+
		"\3\"\5\"\u00f8\n\"\3\"\3\"\3#\3#\5#\u00fe\n#\3#\6#\u0101\n#\r#\16#\u0102"+
		"\3$\3$\3%\3%\3%\2\2&\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\2=\2?\37A C!E\2G\2I\2\3\2\13\5\2C\\aac|\6\2\62;C\\a"+
		"ac|\5\2\13\f\17\17\"\"\4\2$$^^\4\2))^^\4\2GGgg\4\2--//\5\2\62;CHch\n\2"+
		"$$))^^ddhhppttvv\u0113\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3K\3\2\2\2\5Q\3\2\2"+
		"\2\7V\3\2\2\2\t^\3\2\2\2\13f\3\2\2\2\rq\3\2\2\2\17y\3\2\2\2\21\u0081\3"+
		"\2\2\2\23\u0087\3\2\2\2\25\u008a\3\2\2\2\27\u008d\3\2\2\2\31\u0090\3\2"+
		"\2\2\33\u0092\3\2\2\2\35\u0096\3\2\2\2\37\u0098\3\2\2\2!\u009c\3\2\2\2"+
		"#\u00a3\3\2\2\2%\u00a6\3\2\2\2\'\u00a9\3\2\2\2)\u00ac\3\2\2\2+\u00ae\3"+
		"\2\2\2-\u00b1\3\2\2\2/\u00b3\3\2\2\2\61\u00b5\3\2\2\2\63\u00b7\3\2\2\2"+
		"\65\u00b9\3\2\2\2\67\u00bb\3\2\2\29\u00bf\3\2\2\2;\u00c2\3\2\2\2=\u00e4"+
		"\3\2\2\2?\u00e6\3\2\2\2A\u00ea\3\2\2\2C\u00f4\3\2\2\2E\u00fb\3\2\2\2G"+
		"\u0104\3\2\2\2I\u0106\3\2\2\2KL\7E\2\2LM\7J\2\2MN\7W\2\2NO\7P\2\2OP\7"+
		"M\2\2P\4\3\2\2\2QR\7E\2\2RS\7Q\2\2ST\7F\2\2TU\7G\2\2U\6\3\2\2\2VW\7E\2"+
		"\2WX\7Q\2\2XY\7P\2\2YZ\7P\2\2Z[\7G\2\2[\\\7E\2\2\\]\7V\2\2]\b\3\2\2\2"+
		"^_\7E\2\2_`\7Q\2\2`a\7O\2\2ab\7R\2\2bc\7C\2\2cd\7T\2\2de\7G\2\2e\n\3\2"+
		"\2\2fg\7E\2\2gh\7Q\2\2hi\7P\2\2ij\7U\2\2jk\7V\2\2kl\7T\2\2lm\7C\2\2mn"+
		"\7K\2\2no\7P\2\2op\7V\2\2p\f\3\2\2\2qr\7E\2\2rs\7Q\2\2st\7P\2\2tu\7X\2"+
		"\2uv\7G\2\2vw\7T\2\2wx\7V\2\2x\16\3\2\2\2yz\7E\2\2z{\7Q\2\2{|\7O\2\2|"+
		"}\7R\2\2}~\7W\2\2~\177\7V\2\2\177\u0080\7G\2\2\u0080\20\3\2\2\2\u0081"+
		"\u0082\7W\2\2\u0082\u0083\7U\2\2\u0083\u0084\7K\2\2\u0084\u0085\7P\2\2"+
		"\u0085\u0086\7I\2\2\u0086\22\3\2\2\2\u0087\u0088\7Q\2\2\u0088\u0089\7"+
		"P\2\2\u0089\24\3\2\2\2\u008a\u008b\7V\2\2\u008b\u008c\7Q\2\2\u008c\26"+
		"\3\2\2\2\u008d\u008e\7>\2\2\u008e\u008f\7/\2\2\u008f\30\3\2\2\2\u0090"+
		"\u0091\7]\2\2\u0091\32\3\2\2\2\u0092\u0093\7_\2\2\u0093\u0094\7\60\2\2"+
		"\u0094\u0095\7]\2\2\u0095\34\3\2\2\2\u0096\u0097\7_\2\2\u0097\36\3\2\2"+
		"\2\u0098\u0099\7C\2\2\u0099\u009a\7P\2\2\u009a\u009b\7F\2\2\u009b \3\2"+
		"\2\2\u009c\u00a0\t\2\2\2\u009d\u009f\t\3\2\2\u009e\u009d\3\2\2\2\u009f"+
		"\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\"\3\2\2\2"+
		"\u00a2\u00a0\3\2\2\2\u00a3\u00a4\7?\2\2\u00a4\u00a5\7?\2\2\u00a5$\3\2"+
		"\2\2\u00a6\u00a7\7#\2\2\u00a7\u00a8\7?\2\2\u00a8&\3\2\2\2\u00a9\u00aa"+
		"\7@\2\2\u00aa\u00ab\7?\2\2\u00ab(\3\2\2\2\u00ac\u00ad\7@\2\2\u00ad*\3"+
		"\2\2\2\u00ae\u00af\7>\2\2\u00af\u00b0\7?\2\2\u00b0,\3\2\2\2\u00b1\u00b2"+
		"\7>\2\2\u00b2.\3\2\2\2\u00b3\u00b4\7,\2\2\u00b4\60\3\2\2\2\u00b5\u00b6"+
		"\7\61\2\2\u00b6\62\3\2\2\2\u00b7\u00b8\7-\2\2\u00b8\64\3\2\2\2\u00b9\u00ba"+
		"\7/\2\2\u00ba\66\3\2\2\2\u00bb\u00bc\7\'\2\2\u00bc8\3\2\2\2\u00bd\u00c0"+
		"\5;\36\2\u00be\u00c0\5=\37\2\u00bf\u00bd\3\2\2\2\u00bf\u00be\3\2\2\2\u00c0"+
		":\3\2\2\2\u00c1\u00c3\4\62;\2\u00c2\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2"+
		"\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5<\3\2\2\2\u00c6\u00c8\4"+
		"\62;\2\u00c7\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9"+
		"\u00ca\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cf\7\60\2\2\u00cc\u00ce\4"+
		"\62;\2\u00cd\u00cc\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf"+
		"\u00d0\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00d4\5E"+
		"#\2\u00d3\u00d2\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00e5\3\2\2\2\u00d5"+
		"\u00d7\7\60\2\2\u00d6\u00d8\4\62;\2\u00d7\u00d6\3\2\2\2\u00d8\u00d9\3"+
		"\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00dc\3\2\2\2\u00db"+
		"\u00dd\5E#\2\u00dc\u00db\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00e5\3\2\2"+
		"\2\u00de\u00e0\4\62;\2\u00df\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00df"+
		"\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e5\5E#\2\u00e4"+
		"\u00c7\3\2\2\2\u00e4\u00d5\3\2\2\2\u00e4\u00df\3\2\2\2\u00e5>\3\2\2\2"+
		"\u00e6\u00e7\t\4\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e9\b \2\2\u00e9@\3\2"+
		"\2\2\u00ea\u00ef\7$\2\2\u00eb\u00ee\5I%\2\u00ec\u00ee\n\5\2\2\u00ed\u00eb"+
		"\3\2\2\2\u00ed\u00ec\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef"+
		"\u00f0\3\2\2\2\u00f0\u00f2\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00f3\7$"+
		"\2\2\u00f3B\3\2\2\2\u00f4\u00f7\7)\2\2\u00f5\u00f8\5I%\2\u00f6\u00f8\n"+
		"\6\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9"+
		"\u00fa\7)\2\2\u00faD\3\2\2\2\u00fb\u00fd\t\7\2\2\u00fc\u00fe\t\b\2\2\u00fd"+
		"\u00fc\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0100\3\2\2\2\u00ff\u0101\4\62"+
		";\2\u0100\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0100\3\2\2\2\u0102"+
		"\u0103\3\2\2\2\u0103F\3\2\2\2\u0104\u0105\t\t\2\2\u0105H\3\2\2\2\u0106"+
		"\u0107\7^\2\2\u0107\u0108\t\n\2\2\u0108J\3\2\2\2\22\2\u00a0\u00bf\u00c4"+
		"\u00c9\u00cf\u00d3\u00d9\u00dc\u00e1\u00e4\u00ed\u00ef\u00f7\u00fd\u0102"+
		"\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
