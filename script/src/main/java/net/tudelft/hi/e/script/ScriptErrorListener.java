package net.tudelft.hi.e.script;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class ScriptErrorListener extends BaseErrorListener {

  private List<String> exceptionList;

  public ScriptErrorListener() {
    super();
    exceptionList = new ArrayList<>();
  }

  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, java.lang.Object offendingSymbol, int line,
      int charPositionInLine, java.lang.String msg, RecognitionException e) {
    exceptionList.add(msg);
  }

  public List<String> getExceptionList() {
    return exceptionList;
  }
}
