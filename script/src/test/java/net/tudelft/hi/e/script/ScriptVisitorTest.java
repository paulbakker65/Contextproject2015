/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tudelft.hi.e.script;

import java.util.ArrayList;
import java.util.List;
import net.tudelft.hi.e.common.enums.ChunkType;
import net.tudelft.hi.e.common.enums.CompareOperator;
import net.tudelft.hi.e.computation.BetweenOperation;
import net.tudelft.hi.e.computation.ChunkingOperation;
import net.tudelft.hi.e.computation.ConstraintOperation;
import net.tudelft.hi.e.computation.Operation;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Table;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mawdegroot
 */
public class ScriptVisitorTest {

  private List<Table> tables;

  private ScriptVisitor visitor;

  public ScriptVisitorTest() {
  }

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

  /**
   * Test of getOperationList method, of class ScriptVisitor.
   */
  @Test
  public final void testGetOperationList() {
    final List<Operation> actual = visitor.getOperationList();
    final List<Operation> expected = new ScriptVisitor(tables).
        getOperationList();
    assertEquals("size should be equal", actual.size(), expected.size());
    assertEquals("hashcode should be equal", actual.hashCode(), expected.
        hashCode());
    assertEquals("they should be completely equal", actual, expected);
  }

  /**
   * Test of visitBetween_operation method, of class ScriptVisitor.
   */
  @Test
  public void testBetweenOperation() {
    //: eventcol=field datecol1=field datecol2=field value1=value value2=value
    final ANTLRInputStream input = new ANTLRInputStream(
        "BETWEEN [table0].[eventfield] [table0].[date1] [table0].[date2] 10 20");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());

    BetweenOperation expected = new BetweenOperation(tables.get(0), "date1",
        "date2", new NumberValue(10), new NumberValue(20));

    List<Operation> parsedOperationList = visitor.getOperationList();

    assertEquals(1, parsedOperationList.size());
    assertEquals(expected, parsedOperationList.get(0));
  }

  /**
   * Test of visitCalc_operator method, of class ScriptVisitor.
   */
  @Test
  public void testCalcOperator() {
  }

  /**
   * Test of visitChunk_param method, of class ScriptVisitor.
   */
  @Test
  public void testChunkOperation() {
    final ANTLRInputStream input = new ANTLRInputStream(
        "CHUNK [table0].[field] USING MONTH 1");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());

    ChunkingOperation expected = new ChunkingOperation(tables.get(0), "field",
        ChunkType.MONTH);

    List<Operation> parsedOperationList = visitor.getOperationList();

    assertEquals(1, parsedOperationList.size());
    System.out.println(parsedOperationList.get(0));
    assertEquals(expected, parsedOperationList.get(0));
  }

  /**
   * Test of visitCode_operation method, of class ScriptVisitor.
   */
  @Test
  public void testCodeOperation() {
  }

  /**
   * Test of visitCompare_operation method, of class ScriptVisitor.
   */
  @Test
  public void testCompareOperation() {
  }

  /**
   * Test of visitCompare_operator method, of class ScriptVisitor.
   */
  @Test
  public void testCompareOperator() {
  }

  /**
   * Test of visitCompute_operation method, of class ScriptVisitor.
   */
  @Test
  public void testComputeOperation() {
  }

  /**
   * Test of visitCondition method, of class ScriptVisitor.
   */
  @Test
  public void testCondition() {
  }

  /**
   * Test of visitConnect_operation method, of class ScriptVisitor.
   */
  @Test
  public void testConnectOperation() {
  }


  /**
   * Test of visitConstraint_operation method, of class ScriptVisitor.
   */
  @Test
  public void testConstraintOperation() {
    final ANTLRInputStream input = new ANTLRInputStream(
        "CONSTRAINT [table1].[field1] > 10");
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    visitor.visit(parser.parse());

    ConstraintOperation expected = new ConstraintOperation(tables.get(1),
        "field1",
        CompareOperator.G,
        new NumberValue(10));

    List<Operation> parsedOperationList = visitor.getOperationList();

    assertEquals(1, parsedOperationList.size());
    assertEquals(expected, parsedOperationList.get(0));
  }

  /**
   * Test of visitConvert_operation method, of class ScriptVisitor.
   */
  @Test
  public void testConvertOperation() {
  }

  /**
   * Test of visitCount_pattern method, of class ScriptVisitor.
   */
  @Test
  public void testCountPattern() {
  }

  /**
   * Test of visitDate method, of class ScriptVisitor.
   */
  @Test
  public void testDate() {
  }

  /**
   * Test of visitField method, of class ScriptVisitor.
   */
  @Test
  public void testField() {
  }

  /**
   * Test of visitForeach_chunk_operation method, of class ScriptVisitor.
   */
  @Test
  public void testForeachChunkOperation() {
  }

  /**
   * Test of visitFormula method, of class ScriptVisitor.
   */
  @Test
  public void testFormula() {
  }

  /**
   * Test of visitLsa_operation method, of class ScriptVisitor.
   */
  @Test
  public void testLsaOperation() {
  }

  /**
   * Test of visitNumber method, of class ScriptVisitor.
   */
  @Test
  public void testNumber() {
  }

  /**
   * Test of visitOperation method, of class ScriptVisitor.
   */
  @Test
  public void testOperation() {
  }

  /**
   * Test of visitParse method, of class ScriptVisitor.
   */
  @Test
  public void testParse() {
  }

  /**
   * Test of visitPattern method, of class ScriptVisitor.
   */
  @Test
  public void testPattern() {
  }

  /**
   * Test of visitRange method, of class ScriptVisitor.
   */
  @Test
  public void testRange() {
  }

  /**
   * Test of visitRecord_condition method, of class ScriptVisitor.
   */
  @Test
  public void testRecordCondition() {
  }

  /**
   * Test of visitTable method, of class ScriptVisitor.
   */
  @Test
  public void testTable() {
  }

  /**
   * Test of visitText method, of class ScriptVisitor.
   */
  @Test
  public void testText() {
  }

  /**
   * Test of visitValue method, of class ScriptVisitor.
   */
  @Test
  public void testValue() {
  }
}
