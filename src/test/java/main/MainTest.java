package main;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Main class.
 */
public class MainTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  String filepath = "src/test/resources/";
  
  @Before
  public void setUp() throws Exception {
    Input.clean();
  }

  /**
   * Test -f <data file> <settings file>.
   */
  @Test
  public void testParseCommandlineDataFile() {
    String commandline = "-f " + filepath + "csvexample.csv " + filepath + "settings.xml";
    String argv[] = commandline.split(" ");
    boolean actual = Main.parseCommandline(argv);
    assertEquals(true, actual);
    assertEquals(1, Input.files.size());
    assertEquals("csvexample.csv", Input.files.get(0).getDatafile().getName());
    assertEquals("settings.xml", Input.files.get(0).getSettingsfile().getName());
    
    Input.clean();
    
    commandline = "-f " + filepath + "notexisting.csv " + filepath + "notexisting.xml";
    argv = commandline.split(" ");
    actual = Main.parseCommandline(argv);
    assertEquals(false, actual);
    assertFalse(Input.hasFiles());
    assertFalse(Input.hasOutput());
    assertFalse(Input.hasScript());
  }
  
  /**
   * Test -s <script>.
   */
  @Test
  public void testParseCommandlineScriptFile() {
    String commandline = "-s " + filepath + "script.txt";
    String argv[] = commandline.split(" ");
    boolean actual = Main.parseCommandline(argv);
    assertEquals(true, actual);
    assertFalse(Input.hasFiles());
    assertFalse(Input.hasOutput());
    assertTrue(Input.hasScript());
    
    Input.clean();
    
    commandline = "-s " + filepath + "notexisting.txt";
    argv = commandline.split(" ");
    actual = Main.parseCommandline(argv);
    assertEquals(false, actual);
    assertFalse(Input.hasFiles());
    assertFalse(Input.hasOutput());
    assertFalse(Input.hasScript());
  }
  
  /**
   * Test -o <output directory>.
   */
  @Test
  public void testParseCommandlineOutputDir() {
    String commandline = "-o " + filepath + "output";
    String argv[] = commandline.split(" ");
    boolean actual = Main.parseCommandline(argv);
    assertEquals(true, actual);
    assertFalse(Input.hasFiles());
    assertTrue(Input.hasOutput());
    assertFalse(Input.hasScript());
  }
  
  
  /**
   * Test error in command line.
   */
  @Test
  public void testParseCommandlineErrorCMD() {
    PrintStream bak = System.out;
    System.setOut(new PrintStream(outContent));
    String commandline = "-o " + filepath + "output" + " -f " + filepath + "csvexample.csv";
    String argv[] = commandline.split(" ");
    boolean actual = Main.parseCommandline(argv);
    assertEquals(false, actual);
    assertTrue(outContent.toString().contains("Error in program arguments."));
    System.setOut(bak);
  }
  
}
