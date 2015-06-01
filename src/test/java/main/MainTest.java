package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import input.Input;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
   * Test -f data file settings file.
   */
  @Test
  public void testParseCommandlineDataFile() {
    String commandline = "-f " + filepath + "csvexample.csv " + filepath + "settings.xml";
    String[] argv = commandline.split(" ");
    boolean actual = Main.parseCommandline(argv);
    assertEquals(true, actual);
    assertEquals(1, Input.getFiles().size());
    assertEquals("csvexample.csv", Input.getFiles().get(0).getDatafile().getName());
    assertEquals("settings.xml", Input.getFiles().get(0).getSettingsfile().getName());

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
   * Test error in command line.
   */
  @Test
  public void testParseCommandlineErrorCmd() {
    final PrintStream bak = System.out;
    System.setOut(new PrintStream(outContent));
    final String commandline = "-o " + filepath + "output" + " -f " + filepath + "csvexample.csv";
    final String[] argv = commandline.split(" ");
    final boolean actual = Main.parseCommandline(argv);
    assertEquals(false, actual);
    assertTrue(outContent.toString().contains("Error in program arguments."));
    System.setOut(bak);
  }

  /**
   * Test -o output directory.
   */
  @Test
  public void testParseCommandlineOutputDir() {
    final String commandline = "-o " + filepath + "output";
    final String[] argv = commandline.split(" ");
    final boolean actual = Main.parseCommandline(argv);
    assertEquals(true, actual);
    assertFalse(Input.hasFiles());
    assertTrue(Input.hasOutput());
    assertFalse(Input.hasScript());
  }

  /**
   * Test -s script.
   */
  @Test
  public void testParseCommandlineScriptFile() {
    String commandline = "-s " + filepath + "script.txt";
    String[] argv = commandline.split(" ");
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

}
