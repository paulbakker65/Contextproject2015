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

<<<<<<< HEAD
import enums.CalcOperator;
import enums.CompareOperator;
import table.Table;
=======
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
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
<<<<<<< HEAD
    listener = new ALListener(new ArrayList<Table>());
=======
    listener = new ALListener();
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
  }

  @Test
  public void testALListener() {
<<<<<<< HEAD
    ALListener aListener = new ALListener(new ArrayList<Table>());
=======
    ALListener aListener = new ALListener();
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
    assertNotEquals(null, aListener);
    assertNotEquals(null, aListener.opList);
    assertEquals(0, aListener.opList.size());
  }

  @Test
  public void testEnterChunk_paramChunk_paramContext() {
    // Input CHUNK [field] USING > 10 AND < 20
<<<<<<< HEAD
    ANTLRInputStream input = new ANTLRInputStream("CHUNK [table].[field] USING > 10 AND < 20");
=======
    ANTLRInputStream input = new ANTLRInputStream("CHUNK [field] USING > 10 AND < 20");
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    
    ArrayList<OperationSpec> operationList = listener.getOpList();
    
    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());
    
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.CHUNK);
<<<<<<< HEAD
    op.addOperationOperand("table");
=======
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
    op.addOperationOperand("field");
    op.addOperationOperand(new NumberValue(10));
    op.addOperationOperand(new NumberValue(20));
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterCode_paramCode_paramContext() {
    // Input CODE [field] ON > 10
<<<<<<< HEAD
    ANTLRInputStream input = new ANTLRInputStream("CODE [table].[field] ON > 10");
=======
    ANTLRInputStream input = new ANTLRInputStream("CODE [field] ON > 10");
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    ArrayList<OperationSpec> operationList = listener.getOpList();
    
    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());
    
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.CODE);
<<<<<<< HEAD
    op.addOperationOperand("table");
=======
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
    op.addOperationOperand("field");
    op.addOperationOperand(new Condition(CompareOperator.G, new NumberValue(10)));
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterConnect_paramConnect_paramContext() {
    // Input CONNECT [field] TO [field2]
<<<<<<< HEAD
    ANTLRInputStream input = new ANTLRInputStream("CONNECT [table].[field] TO [table2].[field2]");
=======
    ANTLRInputStream input = new ANTLRInputStream("CONNECT [field] TO [field2]");
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    
    ArrayList<OperationSpec> operationList = listener.getOpList();
    
    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());
    
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.CONNECT);
<<<<<<< HEAD
    op.addOperationOperand("table");
    op.addOperationOperand("field");
    op.addOperationOperand("table2");
=======
    op.addOperationOperand("field");
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
    op.addOperationOperand("field2");
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterCompare_paramCompare_paramContext() {
<<<<<<< HEAD
    ANTLRInputStream input = new ANTLRInputStream("COMPARE [table].[field] == [table2].[field2]");
=======
    ANTLRInputStream input = new ANTLRInputStream("COMPARE [field] == [field2]");
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    
    ArrayList<OperationSpec> operationList = listener.getOpList();
    
    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());
    
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.COMPARE);
<<<<<<< HEAD
    op.addOperationOperand("table");
    op.addOperationOperand("field");
    op.addOperationOperand(CompareOperator.EQ);
    op.addOperationOperand("table2");
=======
    op.addOperationOperand("field");
    op.addOperationOperand(FilterOperation.ConstraintComparatorEnum.EQ);
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
    op.addOperationOperand("field2");
    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterConstraint_paramConstraint_paramContext() {
    // Input CONSTRAINT [field] < 10
<<<<<<< HEAD
    ANTLRInputStream input = new ANTLRInputStream("CONSTRAINT [table].[field] < 10");
=======
    ANTLRInputStream input = new ANTLRInputStream("CONSTRAINT [field] < 10");
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    
    ArrayList<OperationSpec> operationList = listener.getOpList();
    
    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());
    
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.CONSTRAINT);
<<<<<<< HEAD
    op.addOperationOperand("table");
    op.addOperationOperand("field");
    op.addOperationOperand(CompareOperator.L);
=======
    op.addOperationOperand("field");
    op.addOperationOperand(FilterOperation.ConstraintComparatorEnum.L);
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
    op.addOperationOperand(new NumberValue(10));
    assertEquals(op, operationList.get(0));
  }
  
  @Test
  public void testEnterConstraint_paramConstraint_paramContext_2() {
    // Input CONSTRAINT [field] <= [field]
<<<<<<< HEAD
    ANTLRInputStream input = new ANTLRInputStream("CONSTRAINT [table].[field] <= [table2].[field2]");
=======
    ANTLRInputStream input = new ANTLRInputStream("CONSTRAINT [field] <= [field2]");
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    
    ArrayList<OperationSpec> operationList = listener.getOpList();
    
    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());
    
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.CONSTRAINT);
<<<<<<< HEAD
    op.addOperationOperand("table");
    op.addOperationOperand("field");
    op.addOperationOperand(CompareOperator.LEQ);
    op.addOperationOperand("table2");
=======
    op.addOperationOperand("field");
    op.addOperationOperand(FilterOperation.ConstraintComparatorEnum.LEQ);
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
    op.addOperationOperand("field2");

    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterConvert_paramConvert_paramContext() {
    // Input CONVERT [field] TO [field2] * [field3]
<<<<<<< HEAD
    ANTLRInputStream input = new ANTLRInputStream("CONVERT [table].[field] TO [table2].[field2] * [table2].[field3]");
=======
    ANTLRInputStream input = new ANTLRInputStream("CONVERT [field] TO [field2] * [field3]");
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    
    ArrayList<OperationSpec> operationList = listener.getOpList();
    
    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());
    
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.CONVERT);
<<<<<<< HEAD
    op.addOperationOperand("table");
=======
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
    op.addOperationOperand("field");
    op.addOperationOperand(new Formula("field2", CalcOperator.MULTIPLY, "field3"));

    assertEquals(op, operationList.get(0));
  }

  @Test
  public void testEnterCompute_paramCompute_paramContext() {
    // Input COMPUTE [field] <- [field2] * 10
<<<<<<< HEAD
    ANTLRInputStream input = new ANTLRInputStream("COMPUTE [table].[field] <- [table2].[field2] * 10");
=======
    ANTLRInputStream input = new ANTLRInputStream("COMPUTE [field] <- [field2] * 10");
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());
    
    ArrayList<OperationSpec> operationList = listener.getOpList();
    
    assertNotEquals(null, operationList);
    assertEquals(1, operationList.size());
    
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.COMPUTE);
<<<<<<< HEAD
    op.addOperationOperand("table");
=======
>>>>>>> 50997cdf89fc9c247b350f685865bb978a2dac47
    op.addOperationOperand("field");
    op.addOperationOperand(new Formula("field2", CalcOperator.MULTIPLY, new NumberValue(10)));

    assertEquals(op, operationList.get(0));
  }
}
