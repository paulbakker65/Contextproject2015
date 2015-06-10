package script;

import java.util.ArrayList;
import net.tudelft.hi.e.common.enums.CalcOperator;
import net.tudelft.hi.e.common.enums.CompareOperator;
import net.tudelft.hi.e.computation.PatternDescription;
import net.tudelft.hi.e.computation.RecordMatchesConditionCondition;
import net.tudelft.hi.e.computation.RecordOccurrenceCondition;
import net.tudelft.hi.e.computation.SingleCount;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.script.AnalysisLangLexer;
import net.tudelft.hi.e.script.AnalysisLangParser;
import net.tudelft.hi.e.computation.Condition;
import net.tudelft.hi.e.computation.Formula;
import net.tudelft.hi.e.script.OperationSpec;
import net.tudelft.hi.e.script.OperationSpec.OperationType;
import net.tudelft.hi.e.script.ScriptListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing the ScriptListener class.
 */
public class ScriptListenerTest {

  ScriptListener listener;

  @Before
  public void setUp() {
    listener = new ScriptListener(new ArrayList<Table>());
  }

  @Test
  public void testScriptListener() {
    final ScriptListener alistener = new ScriptListener(new ArrayList<Table>());
    assertNotEquals(null, alistener);
    assertNotEquals(null, alistener.getOpList());
    assertEquals(0, alistener.getOpList().size());
  }

  @Test
  public void testEnterChunk_paramChunk_paramContext() {
    // Input CHUNK [field] USING > 10 AND < 20
    final ANTLRInputStream input =
        new ANTLRInputStream("CHUNK [table].[field] USING > 10 AND < 20");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());

    final ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    final OperationSpec op = new OperationSpec(new ArrayList<Table>());
    op.setOperationType(OperationType.CHUNK);
    op.addOperationOperand("table");
    op.addOperationOperand("field");
    op.addOperationOperand(new NumberValue(10));
    op.addOperationOperand(new NumberValue(20));
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterCode_paramCode_paramContext_singlepattern() {
    // Input CODE [field] ON > 10
    final ANTLRInputStream input =
        new ANTLRInputStream(
            "CODE [table] ON { 1 [table].[field] > 10 } AS \"tralala\"");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    final ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    final OperationSpec op = new OperationSpec(new ArrayList<Table>());
    op.setOperationType(OperationType.CODE);
    op.addOperationOperand("table");

    ArrayList<PatternDescription> patternList =
        new ArrayList<PatternDescription>();
    patternList.add(new PatternDescription(new SingleCount(1),
        new RecordMatchesConditionCondition("field", new Condition(
            CompareOperator.G, new NumberValue(10)))));

    op.addOperationOperand(patternList);
    op.addOperationOperand("tralala");
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterCode_paramCode_paramContext_multipattern() {
    final ANTLRInputStream input =
        new ANTLRInputStream(
            "CODE [table] ON { 1 [website] } { 1 [table].[field] > 10 } AS \"tralala\"");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    final ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    final OperationSpec op = new OperationSpec(new ArrayList<Table>());
    op.setOperationType(OperationType.CODE);
    op.addOperationOperand("table");

    ArrayList<PatternDescription> patternList =
        new ArrayList<PatternDescription>();
    patternList.add(new PatternDescription(new SingleCount(1),
        new RecordOccurrenceCondition("website")));
    patternList.add(new PatternDescription(new SingleCount(1),
        new RecordMatchesConditionCondition("field", new Condition(
            CompareOperator.G, new NumberValue(10)))));

    op.addOperationOperand(patternList);
    op.addOperationOperand("tralala");
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterCode_paramCode_paramContext_multipattern_2() {
    // Input CODE [field] ON > 10
    final ANTLRInputStream input =
        new ANTLRInputStream(
            "CODE [table] ON { 1 [table].[field] > 10 } { 1 [website] } AS \"tralala\"");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    final ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    final OperationSpec op = new OperationSpec(new ArrayList<Table>());
    op.setOperationType(OperationType.CODE);
    op.addOperationOperand("table");

    ArrayList<PatternDescription> patternList =
        new ArrayList<PatternDescription>();
    patternList.add(new PatternDescription(new SingleCount(1),
        new RecordMatchesConditionCondition("field", new Condition(
            CompareOperator.G, new NumberValue(10)))));
    patternList.add(new PatternDescription(new SingleCount(1),
        new RecordOccurrenceCondition("website")));

    op.addOperationOperand(patternList);
    op.addOperationOperand("tralala");
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterCompare_paramCompare_paramContext() {
    final ANTLRInputStream input =
        new ANTLRInputStream("COMPARE [table].[field] == [table2].[field2]");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());

    final ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    final OperationSpec op = new OperationSpec(new ArrayList<Table>());
    op.setOperationType(OperationType.COMPARE);
    op.addOperationOperand("table");
    op.addOperationOperand("field");
    op.addOperationOperand(CompareOperator.EQ);
    op.addOperationOperand("table2");
    op.addOperationOperand("field2");
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterCompute_paramCompute_paramContext() {
    // Input COMPUTE [field] <- [field2] * 10
    final ANTLRInputStream input =
        new ANTLRInputStream(
            "COMPUTE [table].[field] <- [table2].[field2] * 10");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());

    final ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    final OperationSpec op = new OperationSpec(new ArrayList<Table>());
    op.setOperationType(OperationType.COMPUTE);
    op.addOperationOperand("table");
    op.addOperationOperand("field");
    op.addOperationOperand(new Formula("field2", CalcOperator.MULTIPLY,
        new NumberValue(10)));

    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterConnect_paramConnect_paramContext() {
    // Input CONNECT [field] TO [field2]
    final ANTLRInputStream input =
        new ANTLRInputStream("CONNECT [table].[field] TO [table2].[field2]");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());

    final ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    final OperationSpec op = new OperationSpec(new ArrayList<Table>());
    op.setOperationType(OperationType.CONNECT);
    op.addOperationOperand("table");
    op.addOperationOperand("field");
    op.addOperationOperand("table2");
    op.addOperationOperand("field2");
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterConstraint_paramConstraint_paramContext() {
    // Input CONSTRAINT [field] < 10
    final ANTLRInputStream input =
        new ANTLRInputStream("CONSTRAINT [table].[field] < 10");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());

    final ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    final OperationSpec op = new OperationSpec(new ArrayList<Table>());
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
    final ANTLRInputStream input =
        new ANTLRInputStream("CONSTRAINT [table].[field]"
            + " <= [table2].[field2]");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());

    final ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    final OperationSpec op = new OperationSpec(new ArrayList<Table>());
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
    final ANTLRInputStream input =
        new ANTLRInputStream(
            "CONVERT [table].[field] TO [table2].[field2] * [table2].[field3]");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());

    final ArrayList<OperationSpec> operationList = listener.getOpList();

    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());

    final OperationSpec op = new OperationSpec(new ArrayList<Table>());
    op.setOperationType(OperationType.CONVERT);
    op.addOperationOperand("table");
    op.addOperationOperand("field");
    op.addOperationOperand(new Formula("field2", CalcOperator.MULTIPLY,
        "field3"));

    assertEquals(op, operationList.get(0));
  }
}
