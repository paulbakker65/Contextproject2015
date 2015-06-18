package net.tudelft.hi.e.script;

import net.tudelft.hi.e.common.exceptions.ExceptionHandler;
import net.tudelft.hi.e.common.exceptions.ParseFailedException;
import net.tudelft.hi.e.computation.Operation;
import net.tudelft.hi.e.data.Table;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Operation Factory to create Operations according to the specifications in a script file.
 */
class OperationFactory {

  /**
   * Class wide logger used for logging exceptions.
   */
  private static final Logger LOG = Logger.getLogger(OperationFactory.class.getName());

  /**
   * Default hidden constructor because this class cannot be instantiated.
   */
  private OperationFactory() {
  }

  /**
   * Create operations using a input script given as String.
   *
   * @param tableList list of tables on which operations will be executed.
   * @param scriptInput script input.
   * @return list of operations.
   */
  static List<Operation> createOperationsFromString(final List<Table> tableList,
      final String scriptInput) throws ParseFailedException {
    ExceptionHandler.replaceHandler(LOG);
    return createOperationsUsingInputStream(tableList, new ANTLRInputStream(scriptInput));
  }

  /**
   * Create operations using a input script parsed from a file.
   *
   * @param tableList list of tables on which operations will be executed.
   * @param filePath script file path.
   * @return list of operations.
   */
  static List<Operation> createOperationsFromFile(final List<Table> tableList, final String filePath)
      throws ParseFailedException {
    ExceptionHandler.replaceHandler(LOG);
    List<Operation> listOfOperations = new ArrayList<Operation>();
    try {
      listOfOperations.addAll(createOperationsUsingInputStream(tableList, new ANTLRFileStream(
          filePath)));
    } catch (IOException ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
    }
    return listOfOperations;
  }

  /**
   * The underlying createOperations method using an ANTLR Input Stream.
   * <p>
   * The other methods use this function to do the actual creating.
   *
   * @param tableList list of tables on which operations will be executed.
   * @param inputStream input stream that provides script input.
   * @return list of operations.
   */
  private static List<Operation> createOperationsUsingInputStream(final List<Table> tableList,
      final ANTLRInputStream inputStream) throws ParseFailedException {
    final AnalysisLangLexer lexer = new AnalysisLangLexer(inputStream);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    final ScriptVisitor visitor = new ScriptVisitor(tableList);

    ScriptErrorListener scriptErrorListener = new ScriptErrorListener();
    parser.addErrorListener(scriptErrorListener);
    visitor.visit(parser.parse());

    StringBuilder stringBuilder = new StringBuilder();
    for (String exMsg : scriptErrorListener.getExceptionList()) {
      stringBuilder.append(exMsg).append("\n");
    }
    if (!scriptErrorListener.getExceptionList().isEmpty()) {
      throw new ParseFailedException(stringBuilder.toString());
    }

    return visitor.getOperationList();
  }
}
