package net.tudelft.hi.e.gui;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;


public class TaskTest {
  File outputdir = new File("src/test/resources/outputTaskTest");
  private static final String[] argv = {
    "-f", "src/test/resources/test1.csv", "src/test/resources/test1.xml",
    "-f", "src/test/resources/test2.csv", "src/test/resources/test2.xml",
    "-o", "src/test/resources/outputTaskTest",
    "-s", "src/test/resources/script.txt",
    "-nogui"};
  
  private Table table;
  
  /**
   * Adds all the files to the input using parseCommandLine and executes the task.
   */
  @Before
  public void setUp() {
    cleanUp();
    
    assertTrue(Main.parseCommandline(argv));
    
    Task task = new Task();
    task.doInBackground();
    
    table = task.getTable(1);
  }
  
  @After
  public void cleanUp() {
    outputdir.delete();
  }


  /**
   * Checks if the output table is correct.
   */
  @Test
  public void checkResultTable() {    
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
  @Test
  public void checkOutputFiles() {
    assertTrue(outputdir.exists() && outputdir.isDirectory());
    String[] expectedfiles = {"output_test1.csv", "output_test1.ser", "output_test1.xml", 
        "output_test2.csv", "output_test2.ser", "output_test2.xml"};
    List<String> actualfiles = Arrays.asList(outputdir.list());
    for (String file : expectedfiles) {
      assertTrue(actualfiles.contains(file));
    }
  }  
}
