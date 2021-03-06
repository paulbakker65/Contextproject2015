package net.tudelft.hi.e.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import net.tudelft.hi.e.common.exceptions.WrongXmlException;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
  File notafile = new File(resource);

  @Before
  public void setUp() throws Exception {
    Input.clean();
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
   * Test if a file is really a file.
   */
  @Test(expected = IOException.class)
  public void testAddDataFile4() throws Exception {
    assertTrue(Input.getFiles().isEmpty());
    Input.addDataFile(file, notafile);
    assertTrue(Input.getFiles().isEmpty());
  }

  /**
   * Tests XML error in settings file.
   */
  @Test
  public void testAddDataFile5() {
    assertTrue(Input.getFiles().isEmpty());
    try {
      Input.addDataFile(file, settingsxmlerror);
    } catch (final IOException e) {
      assertEquals(WrongXmlException.class, e.getClass());
    }
    assertTrue(Input.getFiles().isEmpty());
  }

  @Test(expected = IOException.class)
  public void testAddDataFile6() throws IOException {
    Input.addDataFile(notafile, settings);
  }

  @Test
  public void testExists() {
    assertTrue(Input.exists(script));
    assertTrue(Input.exists(file));
    assertTrue(Input.exists(settings));
    assertEquals(false, Input.exists(notexisting));
  }

  @Test
  public void testHasFiles() throws Exception {
    assertFalse(Input.hasFiles());
    Input.addDataFile(file, settings);
    assertTrue(Input.hasFiles());
  }

  @Test
  public void testHasFiles2() throws Exception {
    assertFalse(Input.hasFiles());
    List<DataFile> list = new ArrayList<>();
    DataFile df = new DataFile(file, settings);
    list.add(df);
    Input.setFiles(list);
    assertTrue(Input.hasFiles());
  }

  @Test
  public void testHasOutput() {
    assertFalse(Input.hasOutput());
    Input.setOutputDir(output);
    assertTrue(Input.hasOutput());
  }

  @Test
  public void testHasScript() {
    assertFalse(Input.hasScript());
    Input.setScriptFile(script);
    assertTrue(Input.hasScript());
  }

  /**
   * Test directory creation.
   */
  @Test
  public void testSetOutputDir1() {
    if (output.exists()) {
      output.delete();
    }
    assertNull(Input.getOutputDir());
    assertTrue(Input.setOutputDir(output));
    assertFalse(Input.setOutputDir(null));
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
  public void testSetScriptFile() {
    assertNull(Input.getScriptFile());
    assertFalse(Input.setScriptFile(notexisting));
    assertNull(Input.getScriptFile());
    assertTrue(Input.setScriptFile(script));
    assertFalse(Input.setScriptFile(null));
    assertEquals(script, Input.getScriptFile());
  }

}
