// Generated from AnalysisLang.g4 by ANTLR 4.5
package scriptlang;

import scriptlang.extra.*;
import enums.*;
import table.value.*;
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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, ID=20, EQ=21, NEQ=22, GEQ=23, G=24, LEQ=25, L=26, 
		MULTIPLY=27, DIVIDE=28, PLUS=29, MINUS=30, MODULO=31, NUMBER=32, WS=33, 
		STRING=34, CHAR=35;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "ID", "EQ", "NEQ", "GEQ", "G", "LEQ", "L", "MULTIPLY", 
		"DIVIDE", "PLUS", "MINUS", "MODULO", "NUMBER", "INT", "FLOAT", "WS", "STRING", 
		"CHAR", "EXPONENT", "HEX_NUM", "ESC_SEQ"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2%\u0125\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\3\25\3\25\7\25\u00bb\n\25\f\25\16\25\u00be\13\25\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34"+
		"\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\5!\u00dc\n!\3\"\6\"\u00df\n"+
		"\"\r\"\16\"\u00e0\3#\6#\u00e4\n#\r#\16#\u00e5\3#\3#\7#\u00ea\n#\f#\16"+
		"#\u00ed\13#\3#\5#\u00f0\n#\3#\3#\6#\u00f4\n#\r#\16#\u00f5\3#\5#\u00f9"+
		"\n#\3#\6#\u00fc\n#\r#\16#\u00fd\3#\5#\u0101\n#\3$\3$\3$\3$\3%\3%\3%\7"+
		"%\u010a\n%\f%\16%\u010d\13%\3%\3%\3&\3&\3&\5&\u0114\n&\3&\3&\3\'\3\'\5"+
		"\'\u011a\n\'\3\'\6\'\u011d\n\'\r\'\16\'\u011e\3(\3(\3)\3)\3)\2\2*\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C\2E\2G#I$K%M\2O\2Q\2\3\2\13\5\2C\\aac|\6\2\62;C\\aac|\5\2\13\f\17"+
		"\17\"\"\4\2$$^^\4\2))^^\4\2GGgg\4\2--//\5\2\62;CHch\n\2$$))^^ddhhpptt"+
		"vv\u012f\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2"+
		"\2K\3\2\2\2\3S\3\2\2\2\5[\3\2\2\2\7a\3\2\2\2\tf\3\2\2\2\13n\3\2\2\2\r"+
		"v\3\2\2\2\17~\3\2\2\2\21\u0089\3\2\2\2\23\u0091\3\2\2\2\25\u0095\3\2\2"+
		"\2\27\u009b\3\2\2\2\31\u009e\3\2\2\2\33\u00a1\3\2\2\2\35\u00a4\3\2\2\2"+
		"\37\u00a6\3\2\2\2!\u00aa\3\2\2\2#\u00ac\3\2\2\2%\u00b0\3\2\2\2\'\u00b6"+
		"\3\2\2\2)\u00b8\3\2\2\2+\u00bf\3\2\2\2-\u00c2\3\2\2\2/\u00c5\3\2\2\2\61"+
		"\u00c8\3\2\2\2\63\u00ca\3\2\2\2\65\u00cd\3\2\2\2\67\u00cf\3\2\2\29\u00d1"+
		"\3\2\2\2;\u00d3\3\2\2\2=\u00d5\3\2\2\2?\u00d7\3\2\2\2A\u00db\3\2\2\2C"+
		"\u00de\3\2\2\2E\u0100\3\2\2\2G\u0102\3\2\2\2I\u0106\3\2\2\2K\u0110\3\2"+
		"\2\2M\u0117\3\2\2\2O\u0120\3\2\2\2Q\u0122\3\2\2\2ST\7D\2\2TU\7G\2\2UV"+
		"\7V\2\2VW\7Y\2\2WX\7G\2\2XY\7G\2\2YZ\7P\2\2Z\4\3\2\2\2[\\\7E\2\2\\]\7"+
		"J\2\2]^\7W\2\2^_\7P\2\2_`\7M\2\2`\6\3\2\2\2ab\7E\2\2bc\7Q\2\2cd\7F\2\2"+
		"de\7G\2\2e\b\3\2\2\2fg\7E\2\2gh\7Q\2\2hi\7O\2\2ij\7R\2\2jk\7C\2\2kl\7"+
		"T\2\2lm\7G\2\2m\n\3\2\2\2no\7E\2\2op\7Q\2\2pq\7O\2\2qr\7R\2\2rs\7W\2\2"+
		"st\7V\2\2tu\7G\2\2u\f\3\2\2\2vw\7E\2\2wx\7Q\2\2xy\7P\2\2yz\7P\2\2z{\7"+
		"G\2\2{|\7E\2\2|}\7V\2\2}\16\3\2\2\2~\177\7E\2\2\177\u0080\7Q\2\2\u0080"+
		"\u0081\7P\2\2\u0081\u0082\7U\2\2\u0082\u0083\7V\2\2\u0083\u0084\7T\2\2"+
		"\u0084\u0085\7C\2\2\u0085\u0086\7K\2\2\u0086\u0087\7P\2\2\u0087\u0088"+
		"\7V\2\2\u0088\20\3\2\2\2\u0089\u008a\7E\2\2\u008a\u008b\7Q\2\2\u008b\u008c"+
		"\7P\2\2\u008c\u008d\7X\2\2\u008d\u008e\7G\2\2\u008e\u008f\7T\2\2\u008f"+
		"\u0090\7V\2\2\u0090\22\3\2\2\2\u0091\u0092\7N\2\2\u0092\u0093\7U\2\2\u0093"+
		"\u0094\7C\2\2\u0094\24\3\2\2\2\u0095\u0096\7W\2\2\u0096\u0097\7U\2\2\u0097"+
		"\u0098\7K\2\2\u0098\u0099\7P\2\2\u0099\u009a\7I\2\2\u009a\26\3\2\2\2\u009b"+
		"\u009c\7Q\2\2\u009c\u009d\7P\2\2\u009d\30\3\2\2\2\u009e\u009f\7>\2\2\u009f"+
		"\u00a0\7/\2\2\u00a0\32\3\2\2\2\u00a1\u00a2\7V\2\2\u00a2\u00a3\7Q\2\2\u00a3"+
		"\34\3\2\2\2\u00a4\u00a5\7]\2\2\u00a5\36\3\2\2\2\u00a6\u00a7\7_\2\2\u00a7"+
		"\u00a8\7\60\2\2\u00a8\u00a9\7]\2\2\u00a9 \3\2\2\2\u00aa\u00ab\7_\2\2\u00ab"+
		"\"\3\2\2\2\u00ac\u00ad\7C\2\2\u00ad\u00ae\7P\2\2\u00ae\u00af\7F\2\2\u00af"+
		"$\3\2\2\2\u00b0\u00b1\7F\2\2\u00b1\u00b2\7C\2\2\u00b2\u00b3\7V\2\2\u00b3"+
		"\u00b4\7G\2\2\u00b4\u00b5\7*\2\2\u00b5&\3\2\2\2\u00b6\u00b7\7+\2\2\u00b7"+
		"(\3\2\2\2\u00b8\u00bc\t\2\2\2\u00b9\u00bb\t\3\2\2\u00ba\u00b9\3\2\2\2"+
		"\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd*\3"+
		"\2\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c0\7?\2\2\u00c0\u00c1\7?\2\2\u00c1"+
		",\3\2\2\2\u00c2\u00c3\7#\2\2\u00c3\u00c4\7?\2\2\u00c4.\3\2\2\2\u00c5\u00c6"+
		"\7@\2\2\u00c6\u00c7\7?\2\2\u00c7\60\3\2\2\2\u00c8\u00c9\7@\2\2\u00c9\62"+
		"\3\2\2\2\u00ca\u00cb\7>\2\2\u00cb\u00cc\7?\2\2\u00cc\64\3\2\2\2\u00cd"+
		"\u00ce\7>\2\2\u00ce\66\3\2\2\2\u00cf\u00d0\7,\2\2\u00d08\3\2\2\2\u00d1"+
		"\u00d2\7\61\2\2\u00d2:\3\2\2\2\u00d3\u00d4\7-\2\2\u00d4<\3\2\2\2\u00d5"+
		"\u00d6\7/\2\2\u00d6>\3\2\2\2\u00d7\u00d8\7\'\2\2\u00d8@\3\2\2\2\u00d9"+
		"\u00dc\5C\"\2\u00da\u00dc\5E#\2\u00db\u00d9\3\2\2\2\u00db\u00da\3\2\2"+
		"\2\u00dcB\3\2\2\2\u00dd\u00df\4\62;\2\u00de\u00dd\3\2\2\2\u00df\u00e0"+
		"\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1D\3\2\2\2\u00e2"+
		"\u00e4\4\62;\2\u00e3\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e3\3\2"+
		"\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00eb\7\60\2\2\u00e8"+
		"\u00ea\4\62;\2\u00e9\u00e8\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9\3\2"+
		"\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee"+
		"\u00f0\5M\'\2\u00ef\u00ee\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u0101\3\2"+
		"\2\2\u00f1\u00f3\7\60\2\2\u00f2\u00f4\4\62;\2\u00f3\u00f2\3\2\2\2\u00f4"+
		"\u00f5\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f8\3\2"+
		"\2\2\u00f7\u00f9\5M\'\2\u00f8\u00f7\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9"+
		"\u0101\3\2\2\2\u00fa\u00fc\4\62;\2\u00fb\u00fa\3\2\2\2\u00fc\u00fd\3\2"+
		"\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff"+
		"\u0101\5M\'\2\u0100\u00e3\3\2\2\2\u0100\u00f1\3\2\2\2\u0100\u00fb\3\2"+
		"\2\2\u0101F\3\2\2\2\u0102\u0103\t\4\2\2\u0103\u0104\3\2\2\2\u0104\u0105"+
		"\b$\2\2\u0105H\3\2\2\2\u0106\u010b\7$\2\2\u0107\u010a\5Q)\2\u0108\u010a"+
		"\n\5\2\2\u0109\u0107\3\2\2\2\u0109\u0108\3\2\2\2\u010a\u010d\3\2\2\2\u010b"+
		"\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010e\3\2\2\2\u010d\u010b\3\2"+
		"\2\2\u010e\u010f\7$\2\2\u010fJ\3\2\2\2\u0110\u0113\7)\2\2\u0111\u0114"+
		"\5Q)\2\u0112\u0114\n\6\2\2\u0113\u0111\3\2\2\2\u0113\u0112\3\2\2\2\u0114"+
		"\u0115\3\2\2\2\u0115\u0116\7)\2\2\u0116L\3\2\2\2\u0117\u0119\t\7\2\2\u0118"+
		"\u011a\t\b\2\2\u0119\u0118\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011c\3\2"+
		"\2\2\u011b\u011d\4\62;\2\u011c\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e"+
		"\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011fN\3\2\2\2\u0120\u0121\t\t\2\2"+
		"\u0121P\3\2\2\2\u0122\u0123\7^\2\2\u0123\u0124\t\n\2\2\u0124R\3\2\2\2"+
		"\22\2\u00bc\u00db\u00e0\u00e5\u00eb\u00ef\u00f5\u00f8\u00fd\u0100\u0109"+
		"\u010b\u0113\u0119\u011e\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}