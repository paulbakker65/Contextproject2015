package scriptlang.extra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;
import org.junit.Test;

import enums.CalcOperator;
import enums.CompareOperator;
import table.Table;
import table.value.NumberValue;
import scriptlang.AnalysisLangLexer;
import scriptlang.AnalysisLangParser;
import scriptlang.extra.OperationSpec.OperationType;

/**
 * Testing the ALListener class.
 */
public class ALListenerTest {

  ALListener listener;

  @Before
  public void setUp() {
    listener = new ALListener(new ArrayList<Table>());
  }

  @Test
  public void testALListener() {
    ALListener alistener = new ALListener(new ArrayList<Table>());
    assertNotEquals(null, alistener);
    assertNotEquals(null, alistener.opList);
    assertEquals(0, alistener.opList.size());
  }

  @Test
  public void testEnterChunk_paramChunk_paramContext() {
    // Input CHUNK [field] USING > 10 AND < 20
    ANTLRInputStream input = new ANTLRInputStream("CHUNK [table].[field] USING > 10 AND < 20");
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());

    ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    OperationSpec op = new OperationSpec(new ArrayList<Table>());
    op.setOperationType(OperationType.CHUNK);
    op.addOperationOperand("table");
    op.addOperationOperand("field");
    op.addOperationOperand(new NumberValue(10));
    op.addOperationOperand(new NumberValue(20));
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterCode_paramCode_paramContext() {
    // Input CODE [field] ON > 10
    ANTLRInputStream input = new ANTLRInputStream("CODE [table].[field] ON > 10");
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    OperationSpec op = new OperationSpec(new ArrayList<Table>());
    op.setOperationType(OperationType.CODE);
    op.addOperationOperand("table");
    op.addOperationOperand("field");
    op.addOperationOperand(new Condition(CompareOperator.G, new NumberValue(10)));
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterConnect_paramConnect_paramContext() {
    // Input CONNECT [field] TO [field2]
    ANTLRInputStream input = new ANTLRInputStream("CONNECT [table].[field] TO [table2].[field2]");
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());

    ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    OperationSpec op = new OperationSpec(new ArrayList<Table>());
    op.setOperationType(OperationType.CONNECT);
    op.addOperationOperand("table");
    op.addOperationOperand("field");
    op.addOperationOperand("table2");
    op.addOperationOperand("field2");
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterCompare_paramCompare_paramContext() {
    ANTLRInputStream input = new ANTLRInputStream("COMPARE [table].[field] == [table2].[field2]");
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());

    ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    OperationSpec op = new OperationSpec(new ArrayList<Table>());
    op.setOperationType(OperationType.COMPARE);
    op.addOperationOperand("table");
    op.addOperationOperand("field");
    op.addOperationOperand(CompareOperator.EQ);
    op.addOperationOperand("table2");
    op.addOperationOperand("field2");
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterConstraint_paramConstraint_paramContext() {
    // Input CONSTRAINT [field] < 10
    ANTLRInputStream input = new ANTLRInputStream("CONSTRAINT [table].[field] < 10");
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());

    ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    OperationSpec op = new OperationSpec(new ArrayList<Table>());
    op.setOperationType(OperationType.CONSTRAINT);
    op.addOperationOperand("table");
    op.addOperationOperand("field");
    op.addOperationOperand(CompareOperator.L);
    op.addOperationOperand(new NumberValue(10));
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterConstraint_paramConstraint_paramContext_2() {
    // Input CONSTRAINT [field] <= [field]
    ANTLRInputStream input = new ANTLRInputStream("CONSTRAINT [table].[field]"
        + " <= [table2].[field2]");
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());

    ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    OperationSpec op = new OperationSpec(new ArrayList<Table>());
    op.setOperationType(OperationType.CONSTRAINT);
    op.addOperationOperand("table");
    op.addOperationOperand("field");
    op.addOperationOperand(CompareOperator.LEQ);
    op.addOperationOperand("table2");
    op.addOperationOperand("field2");

    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterConvert_paramConvert_paramContext() {
    // Input CONVERT [field] TO [field2] * [field3]
    ANTLRInputStream input = new ANTLRInputStream(
        "CONVERT [table].[field] TO [table2].[field2] * [table2].[field3]");
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());

    ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    OperationSpec op = new OperationSpec(new ArrayList<Table>());
    op.setOperationType(OperationType.CONVERT);
    op.addOperationOperand("table");
    op.addOperationOperand("field");
    op.addOperationOperand(new Formula("field2", CalcOperator.MULTIPLY, "field3"));

    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterCompute_paramCompute_paramContext() {
    // Input COMPUTE [field] <- [field2] * 10
    ANTLRInputStream input = new ANTLRInputStream(
        "COMPUTE [table].[field] <- [table2].[field2] * 10");
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());

    ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    OperationSpec op = new OperationSpec(new ArrayList<Table>());
    op.setOperationType(OperationType.COMPUTE);
    op.addOperationOperand("table");
    op.addOperationOperand("field");
    op.addOperationOperand(new Formula("field2", CalcOperator.MULTIPLY, new NumberValue(10)));

    assertEquals(op, operationList.get(0));
  }
}
