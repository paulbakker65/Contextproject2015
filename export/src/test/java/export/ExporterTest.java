package export;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import net.tudelft.hi.e.data.Chunk;
import net.tudelft.hi.e.data.Code;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.export.Exporter;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
    Table event = (Table) dummydb.clone();
    code.addEvent(event);

    dummydb2 = (Table) dummydb.clone();
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
        "\"fruit\";\"groente\";\"saus\";\"Chunk\";\"Code\"\n"
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
        "\"fruit\";\"groente\";\"saus\";\"Chunk\";\"Code\"\n"
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
}
