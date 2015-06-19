package net.tudelft.hi.e.script;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

/**
 * ScriptErrorListener is an ErrorListener to catch Parser errors.
 */
public final class ScriptErrorListener extends BaseErrorListener {

  /**
   * Exception List containing the exception string messages.
   */
  private final List<String> exceptionList;

  /**
   * Standard script error listener constructor.
   */
  public ScriptErrorListener() {
    super();
    this.exceptionList = new ArrayList<>();
  }

  /**
   * Overriden syntaxError method that catches parse errors from the script.
   *
   * @param recognizer
   *         ANTLR recognizer.
   * @param offendingSymbol
   *         the symbol that errored.
   * @param line
   *         the line where it errored.
   * @param charPositionInLine
   *         the position in the line where it errored.
   * @param msg
   *         the exception message it throwed.
   * @param ex
   *         the exception that was thrown.
   */
  @Override
  public void syntaxError(final Recognizer<?, ?> recognizer, final Object offendingSymbol,
          final int line, final int charPositionInLine, final String msg,
          final RecognitionException ex) {
    String newMsg = "Error at line " + line + ", character " + charPositionInLine + ": " + msg;
    this.exceptionList.add(newMsg);
  }

  /**
   * Get the list of exception strings.
   *
   * @return the list of exceptions in string format.
   */
  public List<String> getExceptionList() {
    return this.exceptionList;
  }
}
