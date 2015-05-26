package scriptlang;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.pattern.ParseTreeMatch;

import scriptlang.AnalysisLangParser.ParseContext;
import scriptlang.scriptlangObjects.ALListener;
import scriptlang.scriptlangObjects.OperationSpec;

public class TestClass {
  
  public static void main(String[] args) throws Exception {
    // create a CharStream that reads from standard input
    ANTLRInputStream input = new ANTLRFileStream("inputfile");

    // create a lexer that feeds off of input CharStream
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);

    // create a buffer of tokens pulled from the lexer
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    // create a parser that feeds off the tokens buffer
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    
    ALListener listener = new ALListener();
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    
    ArrayList<OperationSpec> opList = listener.getOpList();
    
    for (int i = 0; i < opList.size(); i++) {
      OperationSpec cur = opList.get(i);
      System.out.println("Type: " + cur.operationType + ", operands: " + cur.operandList + "...");
    }
  }
}