package scriptlang.extra;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import operations.FilterOperation;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;
import org.junit.Test;

import table.value.NumberValue;
import table.value.Value;
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
    listener = new ALListener();
  }

  @Test
  public void testALListener() {
    ALListener aListener = new ALListener();
    assertNotEquals(null, aListener);
    assertNotEquals(null, aListener.opList);
    assertEquals(0, aListener.opList.size());
  }

  @Test
  public void testEnterChunk_paramChunk_paramContext() {
    // Input CHUNK [field] USING > 10 AND < 20
    ANTLRInputStream input = new ANTLRInputStream("CHUNK [field] USING > 10 AND < 20");
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    
    ArrayList<OperationSpec> operationList = listener.getOpList();
    
    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());
    
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.CHUNK);
    op.addOperationOperand("field");
    op.addOperationOperand(new NumberValue(10));
    op.addOperationOperand(new NumberValue(20));
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterCode_paramCode_paramContext() {
    // Input CODE [field] ON > 10
    ANTLRInputStream input = new ANTLRInputStream("CODE [field] ON > 10");
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    ArrayList<OperationSpec> operationList = listener.getOpList();
    
    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());
    
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.CODE);
    op.addOperationOperand("field");
    op.addOperationOperand(new Condition(CompareOperator.G, new NumberValue(10)));
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterConnect_paramConnect_paramContext() {
    // Input CONNECT [field] TO [field2]
    ANTLRInputStream input = new ANTLRInputStream("CONNECT [field] TO [field2]");
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    
    ArrayList<OperationSpec> operationList = listener.getOpList();
    
    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());
    
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.CONNECT);
    op.addOperationOperand("field");
    op.addOperationOperand("field2");
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterCompare_paramCompare_paramContext() {
    ANTLRInputStream input = new ANTLRInputStream("COMPARE [field] == [field2]");
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    
    ArrayList<OperationSpec> operationList = listener.getOpList();
    
    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());
    
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.COMPARE);
    op.addOperationOperand("field");
    op.addOperationOperand(FilterOperation.ConstraintComparatorEnum.EQ);
    op.addOperationOperand("field2");
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterConstraint_paramConstraint_paramContext() {
    // Input CONSTRAINT [field] < 10
    ANTLRInputStream input = new ANTLRInputStream("CONSTRAINT [field] < 10");
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    
    ArrayList<OperationSpec> operationList = listener.getOpList();
    
    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());
    
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.CONSTRAINT);
    op.addOperationOperand("field");
    op.addOperationOperand(FilterOperation.ConstraintComparatorEnum.L);
    op.addOperationOperand(new NumberValue(10));
    assertEquals(op, operationList.get(0));
  }
  
  @Test
  public void testEnterConstraint_paramConstraint_paramContext_2() {
    // Input CONSTRAINT [field] <= [field]
    ANTLRInputStream input = new ANTLRInputStream("CONSTRAINT [field] <= [field2]");
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    
    ArrayList<OperationSpec> operationList = listener.getOpList();
    
    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());
    
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.CONSTRAINT);
    op.addOperationOperand("field");
    op.addOperationOperand(FilterOperation.ConstraintComparatorEnum.LEQ);
    op.addOperationOperand("field2");

    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterConvert_paramConvert_paramContext() {
    // Input CONVERT [field] TO [field2] * [field3]
    ANTLRInputStream input = new ANTLRInputStream("CONVERT [field] TO [field2] * [field3]");
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    
    ArrayList<OperationSpec> operationList = listener.getOpList();
    
    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());
    
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.CONVERT);
    op.addOperationOperand("field");
    op.addOperationOperand(new Formula("field2", CalcOperator.MULTIPLY, "field3"));

    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterCompute_paramCompute_paramContext() {
    // Input COMPUTE [field] <- [field2] * 10
    ANTLRInputStream input = new ANTLRInputStream("COMPUTE [field] <- [field2] * 10");
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    
    ArrayList<OperationSpec> operationList = listener.getOpList();
    
    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());
    
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.COMPUTE);
    op.addOperationOperand("field");
    op.addOperationOperand(new Formula("field2", CalcOperator.MULTIPLY, new NumberValue(10)));

    assertEquals(op, operationList.get(0));
  }
}
