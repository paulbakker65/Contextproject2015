package net.tudelft.hi.e.gui;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;

import org.junit.Before;
import org.junit.Test;

import java.io.File;


public class TaskTest {
  File outputdir = new File("src/test/resources/outputTaskTest");
  private static final String[] argv = {
    "-f", "src/test/resources/test1.csv", "src/test/resources/test1.xml",
    "-f", "src/test/resources/test2.csv", "src/test/resources/test2.xml",
    "-o", "src/test/resources/outputTaskTest",
    "-s", "src/test/resources/script.txt",
    "-nogui"};
  
  @Before
  public void setUp() throws Exception {
    outputdir.delete();
  }

  
  @Test
  public void testTask() {
    assertTrue(Main.parseCommandline(argv));
    
    Task task = new Task();
    task.doInBackground();
    
    checkOutputFiles();
    
    checkResult(task.getTable(1));
  }

  /**
   * Executes the connection operation and checks if the output of the test is correct.
   */
  public void checkResult(Table table) {    
    assertEquals(20, table.size());
    double previous = 0;
    for (final Record v : table) {
      Value value = v.get("number1");
      if (value.isNull()) {
        value = v.get("number2");
      }
      final NumberValue number = (NumberValue) value;
      Double current = number.getValue();
      assertTrue(current >= previous);
      previous = number.getValue();

      final NumberValue nv = (NumberValue) v.get("number");
      current = nv.getValue();
      assertTrue(current >= previous);
    }
  }
  
  /**
   * Checks if all the expected files are created.
   */
  public void checkOutputFiles() {
    assertTrue(outputdir.exists() && outputdir.isDirectory());
    String[] expected = {"output_test1.csv", "output_test1.ser", "output_test1.xml", 
        "output_test2.csv", "output_test2.ser", "output_test2.xml"};
    assertArrayEquals(expected, outputdir.list());
  }
  
  
}
