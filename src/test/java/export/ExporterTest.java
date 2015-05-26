package export;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import input.WrongXMLException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import table.Record;
import table.Table;
import table.value.StringValue;

/**
 * ExporterTest class testing the export.Export class.
 * 
 */
public class ExporterTest {
	
	Record dummyrow1;
	Record dummyrow2;
	Table dummydb;
	
	Set<String> cols;
	
	@Before
	public void setUp() throws WrongXMLException{
		dummyrow1 = new Record();
		dummyrow1.put("groente", new StringValue("wortel"));
		dummyrow1.put("saus", new StringValue("mayonaise"));
		
		dummyrow2 = new Record();
		dummyrow2.put("fruit", new StringValue("banaan"));
		dummyrow2.put("groente", new StringValue("bloemkool"));
		
		dummydb = new Table();
		dummydb.add(dummyrow1);
		dummydb.add(dummyrow2);
		
		cols = new TreeSet<String>();
		cols.add("fruit");
		cols.add("groente");
		cols.add("saus");


	}
	
	@Test
	public void testExport() throws IOException {
    String expected = "\"fruit\";\"groente\";\"saus\"\n\"\";\"wortel\""
        + ";\"mayonaise\"\n\"banaan\";\"bloemkool\";\"\"\n";
		//"fruit", "groente", "saus"
		//"", "wortel", "mayonaise"
		//"banaan", "bloemkool", ""
		
		
		StringWriter w = new StringWriter();
		Exporter.export(dummydb, w);
		assertEquals(expected, w.toString());
	}

	@Test
	public void testGenerateRow1() {
		String[] expected = {"", "wortel", "mayonaise"};
		assertArrayEquals(expected, Exporter.generateRow(dummyrow1, cols));
	}
	
	@Test
	public void testGenerateRow2() {
		String[] expected = {"banaan", "bloemkool", ""};
		assertArrayEquals(expected, Exporter.generateRow(dummyrow2, cols));
	}
}
