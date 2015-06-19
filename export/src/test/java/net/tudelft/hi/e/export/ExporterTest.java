package net.tudelft.hi.e.export;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import net.tudelft.hi.e.common.exceptions.TableNotFoundException;
import net.tudelft.hi.e.data.Chunk;
import net.tudelft.hi.e.data.Code;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.TableFile;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * ExporterTest class testing the export.Export class.
 *
 */
public class ExporterTest {

  Record dummyrow1;
  Record dummyrow2;
  Table dummydb;
  Table dummydb2;

  List<String> cols;

  /**
   * Creates a table to give to the exporter.
   *
   */
  @Before
  public void setUp() {
    dummyrow1 = new Record();
    dummyrow1.put("groente", new StringValue("wortel"));
    dummyrow1.put("saus", new StringValue("mayonaise"));

    dummyrow2 = new Record();
    dummyrow2.put("fruit", new StringValue("banaan"));
    dummyrow2.put("groente", new StringValue("bloemkool"));

    dummydb = new Table();
    dummydb.add(dummyrow1);
    dummydb.add(dummyrow2);

    Chunk chunk0 = new Chunk(0, "Chunk 0");
    chunk0.add(dummyrow1);
    Chunk chunk1 = new Chunk(1, "Chunk 1");
    chunk1.add(dummyrow2);

    Code code = new Code("codeName");
    Table event = new Table(dummydb, false);
    code.addEvent(event);

    dummydb2 = new Table(dummydb);
    dummydb.addChunk(chunk0);
    dummydb.addChunk(chunk1);
    dummydb.addCode(code);

    cols = new ArrayList<String>();
    cols.add("fruit");
    cols.add("groente");
    cols.add("saus");

  }

  @Test
  public void testExportWithChunkCodes() throws IOException {
    Exporter.setAddCodeFrequency(false);
    final String expected =
        "\"fruit\";\"groente\";\"saus\";\"Chunks 0\";\"Code\"\n"
            + "\"\";\"wortel\";\"mayonaise\";\"Chunk 0\";\"codeName\"\n"
            + "\"banaan\";\"bloemkool\";\"\";\"Chunk 1\";\"codeName\"\n";

    final StringWriter stringWriter = new StringWriter();
    Exporter.export(dummydb, stringWriter);
    assertEquals(expected, stringWriter.toString());
  }

  @Test
  public void testExportWithoutChunkCodes() throws IOException {
    Exporter.setAddCodeFrequency(false);
    final String expected =
        "\"fruit\";\"groente\";\"saus\"\n" + "\"\";\"wortel\";\"mayonaise\"\n"
            + "\"banaan\";\"bloemkool\";\"\"\n";

    final StringWriter stringWriter = new StringWriter();
    Exporter.export(dummydb2, stringWriter);
    assertEquals(expected, stringWriter.toString());
  }

  @Test
  public void testExportWithCodeRecord() throws IOException {
    Exporter.setAddCodeFrequency(true);
    final String expected =
        "\"fruit\";\"groente\";\"saus\";\"Chunks 0\";\"Code\"\n"
            + "\"\";\"\";\"\";\"\";\"codeName=1\"\n"
            + "\"\";\"wortel\";\"mayonaise\";\"Chunk 0\";\"codeName\"\n"
            + "\"banaan\";\"bloemkool\";\"\";\"Chunk 1\";\"codeName\"\n";

    final StringWriter stringWriter = new StringWriter();
    Exporter.export(dummydb, stringWriter);
    assertEquals(expected, stringWriter.toString());
  }

  @Test
  public void testGenerateRow1() {
    final String[] expected = { "", "wortel", "mayonaise" };
    assertArrayEquals(expected, Exporter.generateRow(dummyrow1, cols));
  }

  @Test
  public void testGenerateRow2() {
    final String[] expected = { "banaan", "bloemkool", "" };
    assertArrayEquals(expected, Exporter.generateRow(dummyrow2, cols));
  }

  @Test
  public void testExportSer() throws IOException, TableNotFoundException {
    Exporter.export(dummydb2, "src/test/resources/testExport", ".csv");

    File exportFile = new File("src/test/resources/testExport.csv");
    assertTrue(exportFile.exists());

    Table dummyTable = TableFile.readTable("src/test/resources/testExport");
    assertEquals(dummydb2, dummyTable);
  }

  @Test
  public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException,
      InvocationTargetException, InstantiationException {
    Constructor<Exporter> constructor = Exporter.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
    constructor.newInstance();
  }
  
  @Test
  public void testGetterSetterDelimiter() {
    char beginDelimiter = Exporter.getDelimiter();
    Exporter.setDelimiter(';');
    assertEquals(';', Exporter.getDelimiter());
    Exporter.setDelimiter(',');
    assertEquals(',', Exporter.getDelimiter());
    Exporter.setDelimiter(beginDelimiter);
  }
}
