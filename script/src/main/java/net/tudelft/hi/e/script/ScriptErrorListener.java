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

  /**
   * Exception List containing the exception string messages.
   */
  private List<String> exceptionList;

  /**
   * Standard script error listener constructor.
   */
  public ScriptErrorListener() {
    super();
    exceptionList = new ArrayList<>();
  }

  /**
   * Overriden syntaxError method that catches parse errors from the script.
   * @param recognizer ANTLR recognizer.
   * @param offendingSymbol the symbol that errored.
   * @param line the line where it errored.
   * @param charPositionInLine the position in the line where it errored.
   * @param msg the exception message it throwed.
   * @param e the exception that was thrown.
   */
  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, java.lang.Object offendingSymbol, int line,
      int charPositionInLine, java.lang.String msg, RecognitionException e) {
    exceptionList.add(msg);
  }

  /**
   * Get the list of exception strings.
   * @return the list of exceptions in string format.
   */
  public List<String> getExceptionList() {
    return exceptionList;
  }
}
