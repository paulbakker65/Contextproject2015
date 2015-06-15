// Generated from AnalysisLang.g4 by ANTLR 4.5
package net.tudelft.hi.e.script;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AnalysisLangLexer extends Lexer {

  static {
    RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION);
  }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache
          = new PredictionContextCache();
  public static final int T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5
          = 6, T__6 = 7, T__7 = 8, T__8 = 9,
          T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15
          = 16, T__16 = 17,
          T__17 = 18, T__18 = 19, T__19 = 20, T__20 = 21, T__21 = 22, T__22 = 23, T__23
          = 24,
          T__24 = 25, ID = 26, EQ = 27, NEQ = 28, GEQ = 29, G = 30, LEQ = 31, L
          = 32, MULTIPLY = 33,
          DIVIDE = 34, PLUS = 35, MINUS = 36, MODULO = 37, NUMBER = 38, WS = 39, STRING
          = 40,
          CHAR = 41;
  public static String[] modeNames = {
    "DEFAULT_MODE"
  };

  public static final String[] ruleNames = {
    "T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8",
    "T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16",
    "T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24",
    "ID", "EQ", "NEQ", "GEQ", "G", "LEQ", "L", "MULTIPLY", "DIVIDE", "PLUS",
    "MINUS", "MODULO", "NUMBER", "INT", "FLOAT", "WS", "STRING", "CHAR",
    "EXPONENT",
    "HEX_NUM", "ESC_SEQ"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'BETWEEN'", "'CHUNK'", "'CODE'", "'COMPARE'", "'COMPUTE'",
    "'CONNECT'",
    "'CONSTRAINT'", "'CONVERT'", "'LSA'", "'USING'", "'YEAR'", "'MONTH'",
    "'DAY'", "'ON'", "'AS'", "'<-'", "'TO'", "'['", "']'", "'].['", "'{'",
    "'}'", "'AND'", "'DATE('", "')'", null, "'=='", "'!='", "'>='", "'>'",
    "'<='", "'<'", "'*'", "'/'", "'+'", "'-'", "'%'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, null, null, null, null, null, null, null, null, null,
    null, null, null, null, null, null, null, null, null, null, null, null,
    null, null, "ID", "EQ", "NEQ", "GEQ", "G", "LEQ", "L", "MULTIPLY", "DIVIDE",
    "PLUS", "MINUS", "MODULO", "NUMBER", "WS", "STRING", "CHAR"
  };
  public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES,
          _SYMBOLIC_NAMES);

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
    _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA,
            _sharedContextCache);
  }

  @Override
  public String getGrammarFileName() {
    return "AnalysisLang.g4";
  }

  @Override
  public String[] getRuleNames() {
    return ruleNames;
  }

  @Override
  public String getSerializedATN() {
    return _serializedATN;
  }

  @Override
  public String[] getModeNames() {
    return modeNames;
  }

  @Override
  public ATN getATN() {
    return _ATN;
  }

  public static final String _serializedATN
          = "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2+\u0147\b\1\4\2\t"
          + "\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"
          + "\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"
          + "\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"
          + "\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"
          + "\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"
          + ",\t,\4-\t-\4.\t.\4/\t/\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3"
          + "\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"
          + "\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b"
          + "\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3"
          + "\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3"
          + "\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21"
          + "\3\21\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26"
          + "\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32"
          + "\3\33\3\33\7\33\u00dd\n\33\f\33\16\33\u00e0\13\33\3\34\3\34\3\34\3\35"
          + "\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3 \3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3"
          + "$\3%\3%\3&\3&\3\'\3\'\5\'\u00fe\n\'\3(\6(\u0101\n(\r(\16(\u0102\3)\6)"
          + "\u0106\n)\r)\16)\u0107\3)\3)\7)\u010c\n)\f)\16)\u010f\13)\3)\5)\u0112"
          + "\n)\3)\3)\6)\u0116\n)\r)\16)\u0117\3)\5)\u011b\n)\3)\6)\u011e\n)\r)\16"
          + ")\u011f\3)\5)\u0123\n)\3*\3*\3*\3*\3+\3+\3+\7+\u012c\n+\f+\16+\u012f\13"
          + "+\3+\3+\3,\3,\3,\5,\u0136\n,\3,\3,\3-\3-\5-\u013c\n-\3-\6-\u013f\n-\r"
          + "-\16-\u0140\3.\3.\3/\3/\3/\2\2\60\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"
          + "\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"
          + "\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O\2Q\2S)U*W+Y\2[\2"
          + "]\2\3\2\13\5\2C\\aac|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\4\2$$^^\4\2))"
          + "^^\4\2GGgg\4\2--//\5\2\62;CHch\n\2$$))^^ddhhppttvv\u0151\2\3\3\2\2\2\2"
          + "\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"
          + "\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"
          + "\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"
          + "\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2"
          + "\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2"
          + "\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2"
          + "K\3\2\2\2\2M\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\3_\3\2\2\2\5g\3"
          + "\2\2\2\7m\3\2\2\2\tr\3\2\2\2\13z\3\2\2\2\r\u0082\3\2\2\2\17\u008a\3\2"
          + "\2\2\21\u0095\3\2\2\2\23\u009d\3\2\2\2\25\u00a1\3\2\2\2\27\u00a7\3\2\2"
          + "\2\31\u00ac\3\2\2\2\33\u00b2\3\2\2\2\35\u00b6\3\2\2\2\37\u00b9\3\2\2\2"
          + "!\u00bc\3\2\2\2#\u00bf\3\2\2\2%\u00c2\3\2\2\2\'\u00c4\3\2\2\2)\u00c6\3"
          + "\2\2\2+\u00ca\3\2\2\2-\u00cc\3\2\2\2/\u00ce\3\2\2\2\61\u00d2\3\2\2\2\63"
          + "\u00d8\3\2\2\2\65\u00da\3\2\2\2\67\u00e1\3\2\2\29\u00e4\3\2\2\2;\u00e7"
          + "\3\2\2\2=\u00ea\3\2\2\2?\u00ec\3\2\2\2A\u00ef\3\2\2\2C\u00f1\3\2\2\2E"
          + "\u00f3\3\2\2\2G\u00f5\3\2\2\2I\u00f7\3\2\2\2K\u00f9\3\2\2\2M\u00fd\3\2"
          + "\2\2O\u0100\3\2\2\2Q\u0122\3\2\2\2S\u0124\3\2\2\2U\u0128\3\2\2\2W\u0132"
          + "\3\2\2\2Y\u0139\3\2\2\2[\u0142\3\2\2\2]\u0144\3\2\2\2_`\7D\2\2`a\7G\2"
          + "\2ab\7V\2\2bc\7Y\2\2cd\7G\2\2de\7G\2\2ef\7P\2\2f\4\3\2\2\2gh\7E\2\2hi"
          + "\7J\2\2ij\7W\2\2jk\7P\2\2kl\7M\2\2l\6\3\2\2\2mn\7E\2\2no\7Q\2\2op\7F\2"
          + "\2pq\7G\2\2q\b\3\2\2\2rs\7E\2\2st\7Q\2\2tu\7O\2\2uv\7R\2\2vw\7C\2\2wx"
          + "\7T\2\2xy\7G\2\2y\n\3\2\2\2z{\7E\2\2{|\7Q\2\2|}\7O\2\2}~\7R\2\2~\177\7"
          + "W\2\2\177\u0080\7V\2\2\u0080\u0081\7G\2\2\u0081\f\3\2\2\2\u0082\u0083"
          + "\7E\2\2\u0083\u0084\7Q\2\2\u0084\u0085\7P\2\2\u0085\u0086\7P\2\2\u0086"
          + "\u0087\7G\2\2\u0087\u0088\7E\2\2\u0088\u0089\7V\2\2\u0089\16\3\2\2\2\u008a"
          + "\u008b\7E\2\2\u008b\u008c\7Q\2\2\u008c\u008d\7P\2\2\u008d\u008e\7U\2\2"
          + "\u008e\u008f\7V\2\2\u008f\u0090\7T\2\2\u0090\u0091\7C\2\2\u0091\u0092"
          + "\7K\2\2\u0092\u0093\7P\2\2\u0093\u0094\7V\2\2\u0094\20\3\2\2\2\u0095\u0096"
          + "\7E\2\2\u0096\u0097\7Q\2\2\u0097\u0098\7P\2\2\u0098\u0099\7X\2\2\u0099"
          + "\u009a\7G\2\2\u009a\u009b\7T\2\2\u009b\u009c\7V\2\2\u009c\22\3\2\2\2\u009d"
          + "\u009e\7N\2\2\u009e\u009f\7U\2\2\u009f\u00a0\7C\2\2\u00a0\24\3\2\2\2\u00a1"
          + "\u00a2\7W\2\2\u00a2\u00a3\7U\2\2\u00a3\u00a4\7K\2\2\u00a4\u00a5\7P\2\2"
          + "\u00a5\u00a6\7I\2\2\u00a6\26\3\2\2\2\u00a7\u00a8\7[\2\2\u00a8\u00a9\7"
          + "G\2\2\u00a9\u00aa\7C\2\2\u00aa\u00ab\7T\2\2\u00ab\30\3\2\2\2\u00ac\u00ad"
          + "\7O\2\2\u00ad\u00ae\7Q\2\2\u00ae\u00af\7P\2\2\u00af\u00b0\7V\2\2\u00b0"
          + "\u00b1\7J\2\2\u00b1\32\3\2\2\2\u00b2\u00b3\7F\2\2\u00b3\u00b4\7C\2\2\u00b4"
          + "\u00b5\7[\2\2\u00b5\34\3\2\2\2\u00b6\u00b7\7Q\2\2\u00b7\u00b8\7P\2\2\u00b8"
          + "\36\3\2\2\2\u00b9\u00ba\7C\2\2\u00ba\u00bb\7U\2\2\u00bb \3\2\2\2\u00bc"
          + "\u00bd\7>\2\2\u00bd\u00be\7/\2\2\u00be\"\3\2\2\2\u00bf\u00c0\7V\2\2\u00c0"
          + "\u00c1\7Q\2\2\u00c1$\3\2\2\2\u00c2\u00c3\7]\2\2\u00c3&\3\2\2\2\u00c4\u00c5"
          + "\7_\2\2\u00c5(\3\2\2\2\u00c6\u00c7\7_\2\2\u00c7\u00c8\7\60\2\2\u00c8\u00c9"
          + "\7]\2\2\u00c9*\3\2\2\2\u00ca\u00cb\7}\2\2\u00cb,\3\2\2\2\u00cc\u00cd\7"
          + "\177\2\2\u00cd.\3\2\2\2\u00ce\u00cf\7C\2\2\u00cf\u00d0\7P\2\2\u00d0\u00d1"
          + "\7F\2\2\u00d1\60\3\2\2\2\u00d2\u00d3\7F\2\2\u00d3\u00d4\7C\2\2\u00d4\u00d5"
          + "\7V\2\2\u00d5\u00d6\7G\2\2\u00d6\u00d7\7*\2\2\u00d7\62\3\2\2\2\u00d8\u00d9"
          + "\7+\2\2\u00d9\64\3\2\2\2\u00da\u00de\t\2\2\2\u00db\u00dd\t\3\2\2\u00dc"
          + "\u00db\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2"
          + "\2\2\u00df\66\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1\u00e2\7?\2\2\u00e2\u00e3"
          + "\7?\2\2\u00e38\3\2\2\2\u00e4\u00e5\7#\2\2\u00e5\u00e6\7?\2\2\u00e6:\3"
          + "\2\2\2\u00e7\u00e8\7@\2\2\u00e8\u00e9\7?\2\2\u00e9<\3\2\2\2\u00ea\u00eb"
          + "\7@\2\2\u00eb>\3\2\2\2\u00ec\u00ed\7>\2\2\u00ed\u00ee\7?\2\2\u00ee@\3"
          + "\2\2\2\u00ef\u00f0\7>\2\2\u00f0B\3\2\2\2\u00f1\u00f2\7,\2\2\u00f2D\3\2"
          + "\2\2\u00f3\u00f4\7\61\2\2\u00f4F\3\2\2\2\u00f5\u00f6\7-\2\2\u00f6H\3\2"
          + "\2\2\u00f7\u00f8\7/\2\2\u00f8J\3\2\2\2\u00f9\u00fa\7\'\2\2\u00faL\3\2"
          + "\2\2\u00fb\u00fe\5O(\2\u00fc\u00fe\5Q)\2\u00fd\u00fb\3\2\2\2\u00fd\u00fc"
          + "\3\2\2\2\u00feN\3\2\2\2\u00ff\u0101\4\62;\2\u0100\u00ff\3\2\2\2\u0101"
          + "\u0102\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103P\3\2\2\2"
          + "\u0104\u0106\4\62;\2\u0105\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0105"
          + "\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010d\7\60\2\2"
          + "\u010a\u010c\4\62;\2\u010b\u010a\3\2\2\2\u010c\u010f\3\2\2\2\u010d\u010b"
          + "\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u0110"
          + "\u0112\5Y-\2\u0111\u0110\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0123\3\2\2"
          + "\2\u0113\u0115\7\60\2\2\u0114\u0116\4\62;\2\u0115\u0114\3\2\2\2\u0116"
          + "\u0117\3\2\2\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u011a\3\2"
          + "\2\2\u0119\u011b\5Y-\2\u011a\u0119\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u0123"
          + "\3\2\2\2\u011c\u011e\4\62;\2\u011d\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f"
          + "\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0123\5Y"
          + "-\2\u0122\u0105\3\2\2\2\u0122\u0113\3\2\2\2\u0122\u011d\3\2\2\2\u0123"
          + "R\3\2\2\2\u0124\u0125\t\4\2\2\u0125\u0126\3\2\2\2\u0126\u0127\b*\2\2\u0127"
          + "T\3\2\2\2\u0128\u012d\7$\2\2\u0129\u012c\5]/\2\u012a\u012c\n\5\2\2\u012b"
          + "\u0129\3\2\2\2\u012b\u012a\3\2\2\2\u012c\u012f\3\2\2\2\u012d\u012b\3\2"
          + "\2\2\u012d\u012e\3\2\2\2\u012e\u0130\3\2\2\2\u012f\u012d\3\2\2\2\u0130"
          + "\u0131\7$\2\2\u0131V\3\2\2\2\u0132\u0135\7)\2\2\u0133\u0136\5]/\2\u0134"
          + "\u0136\n\6\2\2\u0135\u0133\3\2\2\2\u0135\u0134\3\2\2\2\u0136\u0137\3\2"
          + "\2\2\u0137\u0138\7)\2\2\u0138X\3\2\2\2\u0139\u013b\t\7\2\2\u013a\u013c"
          + "\t\b\2\2\u013b\u013a\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013e\3\2\2\2\u013d"
          + "\u013f\4\62;\2\u013e\u013d\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u013e\3\2"
          + "\2\2\u0140\u0141\3\2\2\2\u0141Z\3\2\2\2\u0142\u0143\t\t\2\2\u0143\\\3"
          + "\2\2\2\u0144\u0145\7^\2\2\u0145\u0146\t\n\2\2\u0146^\3\2\2\2\22\2\u00de"
          + "\u00fd\u0102\u0107\u010d\u0111\u0117\u011a\u011f\u0122\u012b\u012d\u0135"
          + "\u013b\u0140\3\2\3\2";
  public static final ATN _ATN = new ATNDeserializer().deserialize(
          _serializedATN.toCharArray());

  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}