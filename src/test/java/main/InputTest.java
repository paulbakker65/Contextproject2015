package main;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests Input class functionality.
 */
public class InputTest {
  String resource = "src/test/resources/";
  File script = new File(resource + "script.txt");
  File output = new File(resource + "test_output_dir");
  File file = new File(resource + "csvexample.csv");
  File settings = new File(resource + "settings.xml");
  File settingsxmlerror = new File(resource + "settings_xmlerror.xml");
  File notexisting = new File(resource + "notexisting.txt");
  
  @Before
  public void setUp() throws Exception {
    Input.clean();
  }

  @Test
  public void testExists() {
    assertTrue(Input.exists(script));
    assertTrue(Input.exists(file));
    assertTrue(Input.exists(settings));
    assertEquals(false, Input.exists(notexisting));
  }

  @Test
  public void testSetScriptFile() {
    assertNull(Input.getScriptFile());
    assertFalse(Input.setScriptFile(notexisting));
    assertNull(Input.getScriptFile());
    assertTrue(Input.setScriptFile(script));
    assertEquals(script, Input.getScriptFile());
  }

  /**
   * Test directory creation.
   */
  @Test
  public void testSetOutputDir1() {
    if (output.exists()){
      output.delete();
    }
    assertNull(Input.getOutputDir());
    assertTrue(Input.setOutputDir(output));
    output.delete();
  }
  
  /**
   * Test output directory is a file.
   */
  @Test
  public void testSetOutputDir2() {
    assertFalse(Input.setOutputDir(script));
  }

  @Test
  public void testAddDataFile1() throws Exception {
    assertTrue(Input.getFiles().isEmpty());
    Input.addDataFile(file, settings);
    assertTrue(1 == Input.getFiles().size());
  }

  /**
   * Tests data file not found.
   */
  @Test(expected = Exception.class)
  public void testAddDataFile2() throws Exception {
    assertTrue(Input.getFiles().isEmpty());
    Input.addDataFile(notexisting, settings);
    assertTrue(Input.getFiles().isEmpty());
  }
    
  /**
   * Tests settings file not found.
   */
  @Test(expected = Exception.class)
  public void testAddDataFile3() throws Exception {
    assertTrue(Input.getFiles().isEmpty());
    Input.addDataFile(file, notexisting);
    assertTrue(Input.getFiles().isEmpty());
  }
  
  /**
   * Tests XML error in settings file.
   */
  @Test
  public void testAddDataFile4(){
    assertTrue(Input.getFiles().isEmpty());
    try {
      Input.addDataFile(file, settingsxmlerror);
    } catch (Exception e) {
      assertEquals("XML parse error: Error parsing settings file!", e.getMessage());
    }
    assertTrue(Input.getFiles().isEmpty());
  }
  
  @Test
  public void testHasScript() {
    assertFalse(Input.hasScript());
    Input.setScriptFile(script);
    assertTrue(Input.hasScript());
  }

  @Test
  public void testHasOutput() {
    assertFalse(Input.hasOutput());
    Input.setOutputDir(output);
    assertTrue(Input.hasOutput());
  }

  @Test
  public void testHasFiles() throws Exception {
    assertFalse(Input.hasFiles());
    Input.addDataFile(file, settings);
    assertTrue(Input.hasFiles());
  }

}