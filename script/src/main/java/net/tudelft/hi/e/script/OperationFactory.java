package net.tudelft.hi.e.script;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.tudelft.hi.e.computation.Operation;
import net.tudelft.hi.e.data.Table;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * Operation Factory to create Operations according to the specifications in a
 * script file.
 */
class OperationFactory {

  /**
   * Class wide logger used for logging exceptions.
   */
  static final Logger LOG = Logger.getLogger(OperationFactory.class.
      getName());

  /**
   * Create operations using a input script given as String.
   *
   * @param tableList list of tables on which operations will be executed.
   * @param scriptInput script input.
   * @return list of operations.
   */
  static List<Operation> createOperationsFromString(
      final List<Table> tableList, final String scriptInput) {
    return createOperationsUsingInputStream(tableList, new ANTLRInputStream(
        scriptInput));
  }

  /**
   * Create operations using a input script parsed from a file.
   *
   * @param tableList list of tables on which operations will be executed.
   * @param filePath script file path.
   * @return list of operations.
   */
  static List<Operation> createOperationsFromFile(
      final List<Table> tableList, final String filePath) {
    List<Operation> listOfOperations = null;
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
   *
   * The other methods use this function to do the actual creating.
   *
   * @param tableList list of tables on which operations will be executed.
   * @param inputStream input stream that provides script input.
   * @return list of operations.
   */
  private static List<Operation> createOperationsUsingInputStream(
      final List<Table> tableList, final ANTLRInputStream inputStream) {
    final AnalysisLangLexer lexer = new AnalysisLangLexer(inputStream);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    final ScriptVisitor visitor = new ScriptVisitor(tableList);
    visitor.visit(parser.parse());

    return visitor.getOperationList();
  }

}
