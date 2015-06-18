/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package net.tudelft.hi.e.script;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import net.tudelft.hi.e.common.enums.ChunkType;
import net.tudelft.hi.e.common.enums.CompareOperator;
import net.tudelft.hi.e.common.enums.ComputeOperator;
import net.tudelft.hi.e.common.exceptions.ExceptionHandler;
import net.tudelft.hi.e.common.exceptions.TableNotFoundException;
import net.tudelft.hi.e.computation.*;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.input.Input;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.font.Script;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author mawdegroot
 */
public class ScriptVisitorTest {

  private List<Table> tables;

  private ScriptVisitor visitor;

  public ScriptVisitorTest() {}

  @Before
  public void setUp() {
    tables = new ArrayList<Table>();
    for (int i = 0; i < 4; i++) {
      Table t = new Table();
      t.setName("table" + i);
      tables.add(t);
    }
    visitor = new ScriptVisitor(tables);
  }

  @After
  public void tearDown() {
    Input.clean();
    visitor = null;
    tables.clear();
    ExceptionHandler.clear();
  }

  /**
   * Test of getOperationList method, of class ScriptVisitor.
   */
  @Test
  public final void testGetOperationList() {
    final List<Operation> actual = visitor.getOperationList();
    final List<Operation> expected = new ScriptVisitor(tables).getOperationList();
    assertEquals("size should be equal", actual.size(), expected.size());
    assertEquals("hashcode should be equal", actual.hashCode(), expected.hashCode());
    assertEquals("they should be completely equal", actual, expected);
  }

  /**
   * Test of visitBetween_operation method, of class ScriptVisitor.
   */
  @Test
  public void testBetweenOperation() {
    // : eventcol=field datecol1=field datecol2=field value1=value value2=value
    final ANTLRInputStream input =
        new ANTLRInputStream(
            "BETWEEN [table0].[eventfield] [table0].[date1] [table0].[date2] 10 20");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());

    BetweenOperation expected =
        new BetweenOperation(tables.get(0), "date1", "date2", new NumberValue(10), new NumberValue(
            20));

    List<Operation> parsedOperationList = visitor.getOperationList();

    assertEquals(1, parsedOperationList.size());
    assertEquals(expected.getResult(), parsedOperationList.get(0).getResult());
    expected.execute();
    parsedOperationList.get(0).execute();
    assertEquals(expected.getResult(), parsedOperationList.get(0).getResult());
  }

  /**
   * Test of visitChunk_param method, of class ScriptVisitor.
   */
  @Test
  public void testChunkOperation() {
    final ANTLRInputStream input = new ANTLRInputStream("CHUNK [table0].[field] USING MONTH 1");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());

    ChunkingOperation expected = new ChunkingOperation(tables.get(0), "field", ChunkType.MONTH);

    List<Operation> parsedOperationList = visitor.getOperationList();

    assertEquals(1, parsedOperationList.size());
    assertEquals(expected.getResult(), parsedOperationList.get(0).getResult());
    expected.execute();
    parsedOperationList.get(0).execute();
    assertEquals(expected.getResult(), parsedOperationList.get(0).getResult());
  }

  /**
   * Test of visitCode_operation method, of class ScriptVisitor.
   */
  @Test
  public void testCodeOperation() {
    final ANTLRInputStream input =
        new ANTLRInputStream("CODE [table0] ON { 1 [table0].[field] > 10 } AS \"tralala\"");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());

    ArrayList<PatternDescription> patternList = new ArrayList<PatternDescription>();
    patternList.add(new PatternDescription(new SingleCount(1), new RecordMatchesConditionCondition(
        "field", new Condition(CompareOperator.G, new NumberValue(10)))));
    CodingOperation expected =
        new CodingOperation(tables.get(0), PatternFactory.createPattern(patternList), "tralala");

    List<Operation> parsedOperationList = visitor.getOperationList();

    assertEquals(1, parsedOperationList.size());
    assertEquals(expected.getResult(), parsedOperationList.get(0).getResult());
    expected.execute();
    parsedOperationList.get(0).execute();
    assertEquals(expected.getResult().getName(), parsedOperationList.get(0).getResult().getName());
    assertEquals(expected.getResult().getChunks(), parsedOperationList.get(0).getResult()
        .getChunks());
    assertEquals(expected.getResult().getCodes(), parsedOperationList.get(0).getResult().getCodes());
    assertEquals(expected.getResult(), parsedOperationList.get(0).getResult());
  }

  /**
   * Test of visitCompare_operation method, of class ScriptVisitor.
   */
  @Test
  public void testCompareOperation() {

  }

  /**
   * Test of visitCompute_operation method, of class ScriptVisitor.
   */
  @Test
  public void testComputeOperation() {
    final ANTLRInputStream input = new ANTLRInputStream("COMPUTE [table0] AVG() [table0].[field]");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());

    ComputeOperation expected = new ComputeOperation(tables.get(0), ComputeOperator.AVG, "field");
    List<Operation> parsedOperationList = visitor.getOperationList();

    assertEquals(1, parsedOperationList.size());
    assertEquals(expected.getResult(), parsedOperationList.get(0).getResult());
    expected.execute();
    parsedOperationList.get(0).execute();
    assertEquals(expected.getResult(), parsedOperationList.get(0).getResult());
  }

  /**
   * Test of visitConnect_operation method, of class ScriptVisitor.
   */
  @Test
  public void testConnectOperation() {
    final ANTLRInputStream input =
        new ANTLRInputStream("CONNECT [table0].[field1] TO [table1].[field1]");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());

    ConnectionOperation expected =
        new ConnectionOperation(tables.get(0), tables.get(1), "field1", "field1");
    List<Operation> parsedOperationList = visitor.getOperationList();

    assertEquals(1, parsedOperationList.size());
    assertEquals(expected.getResult(), parsedOperationList.get(0).getResult());
    expected.execute();
    parsedOperationList.get(0).execute();
    assertEquals(expected.getResult(), parsedOperationList.get(0).getResult());
  }

  /**
   * Test of visitConstraint_operation method, of class ScriptVisitor.
   */
  @Test
  public void testConstraintOperation() {
    final ANTLRInputStream input = new ANTLRInputStream("CONSTRAINT [table1].[field1] > 10");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());

    ConstraintOperation expected =
        new ConstraintOperation(tables.get(1), "field1", CompareOperator.G, new NumberValue(10));

    List<Operation> parsedOperationList = visitor.getOperationList();

    assertEquals(1, parsedOperationList.size());
    assertEquals(expected.getResult(), parsedOperationList.get(0).getResult());
    expected.execute();
    parsedOperationList.get(0).execute();
    assertEquals(expected.getResult(), parsedOperationList.get(0).getResult());
  }

  /**
   * Test of visitForeach_chunk_operation method, of class ScriptVisitor.
   */
  @Test
  public void testForeachChunkOperation() {
    final ANTLRInputStream input =
        new ANTLRInputStream("FOR EACH CHUNK [table0] 1 CONSTRAINT [table0].[field3] == 10");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());

    ForEachChunkOperation expected =
        new ForEachChunkOperation(tables.get(0), 1, new ConstraintOperation(tables.get(0),
            "field3", CompareOperator.EQ, new NumberValue(10)));

    List<Operation> parsedOperationList = visitor.getOperationList();

    assertEquals(1, parsedOperationList.size());
    assertEquals(expected.getResult(), parsedOperationList.get(0).getResult());
    expected.execute();
    parsedOperationList.get(0).execute();
    assertEquals(expected.getResult(), parsedOperationList.get(0).getResult());
  }

  @Test
  public void testBetweenTableNotFound() {
    final ANTLRInputStream input =
        new ANTLRInputStream("BETWEEN [table].[eventfield] [table0].[date1] [table0].[date2] 10 20");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());
    ExceptionHandler handler = ExceptionHandler.getExceptionHandlerInstance();
    assertEquals(1, handler.getLogRecords().size());
    assertEquals("Table \"table\" not found", handler.getLogRecords().get(0).getMessage());
  }

  @Test
  public void testConstraintTableNotFound() {
    final ANTLRInputStream input =
        new ANTLRInputStream("CONSTRAINT [table].[field3] == 10");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());
    ExceptionHandler handler = ExceptionHandler.getExceptionHandlerInstance();
    assertEquals(1, handler.getLogRecords().size());
    assertEquals("Table \"table\" not found", handler.getLogRecords().get(0).getMessage());
  }

  @Test
  public void testChunkingTableNotFound() {
    final ANTLRInputStream input =
        new ANTLRInputStream("CHUNK [table].[field] USING MONTH 1");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());
    ExceptionHandler handler = ExceptionHandler.getExceptionHandlerInstance();
    assertEquals(1, handler.getLogRecords().size());
    assertEquals("Table \"table\" not found", handler.getLogRecords().get(0).getMessage());
  }

  @Test
  public void testCodingTableNotFound() {
    final ANTLRInputStream input =
        new ANTLRInputStream("CODE [table] ON { 1 [table0].[field] > 10 } AS \"tralala\"");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());
    ExceptionHandler handler = ExceptionHandler.getExceptionHandlerInstance();
    assertEquals(1, handler.getLogRecords().size());
    assertEquals("Table \"table\" not found", handler.getLogRecords().get(0).getMessage());
  }

  @Test
  public void testComputeTableNotFound() {
    final ANTLRInputStream input =
        new ANTLRInputStream("COMPUTE [table] AVG() [table0].[field]");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());
    ExceptionHandler handler = ExceptionHandler.getExceptionHandlerInstance();
    assertEquals(1, handler.getLogRecords().size());
    assertEquals("Table \"table\" not found", handler.getLogRecords().get(0).getMessage());
  }

  @Test
  public void testConnectionTableNotFound() {
    final ANTLRInputStream input =
        new ANTLRInputStream("CONNECT [table].[field] TO [table0].[field]");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());
    ExceptionHandler handler = ExceptionHandler.getExceptionHandlerInstance();
    assertEquals(1, handler.getLogRecords().size());
    assertEquals("Table \"table\" not found", handler.getLogRecords().get(0).getMessage());
  }

  @Test
  public void testForEachChunkTableNotFound() {
    final ANTLRInputStream input =
        new ANTLRInputStream("FOR EACH CHUNK [table] 1 CONSTRAINT [table].[field3] == 10");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());
    ExceptionHandler handler = ExceptionHandler.getExceptionHandlerInstance();
    assertEquals(1, handler.getLogRecords().size());
    assertEquals("Table \"table\" not found", handler.getLogRecords().get(0).getMessage());
  }

  @Test
  public void testLsaTableNotFound() {
    final ANTLRInputStream input =
        new ANTLRInputStream("LSA [table].[field] 10 20 10 20");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());
    ExceptionHandler handler = ExceptionHandler.getExceptionHandlerInstance();
    assertEquals(1, handler.getLogRecords().size());
    assertEquals("Table \"table\" not found", handler.getLogRecords().get(0).getMessage());
  }

}
