package export;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import database.DataRow;
import database.Database;

public class ExporterTest {

	
	DataRow dummyrow1;
	DataRow dummyrow2;
	Database dummydb;
	String[] cols = {"fruit","groente","saus"};
	
	@Before
	public void setUp(){
		dummyrow1 = new DataRow();
		dummyrow1.addColumn("groente", "wortel");
		dummyrow1.addColumn("saus", "mayonaise");
		
		dummyrow2 = new DataRow();
		dummyrow2.addColumn("fruit", "banaan");
		dummyrow2.addColumn("groente", "bloemkool");
		
		dummydb = new Database();
		dummydb.addRow(dummyrow1);
		dummydb.addRow(dummyrow2);
	}
	
	@Test
	public void testExport() {
		String expected = "\"fruit\",\"groente\",\"saus\"\n\"\",\"wortel\",\"mayonaise\"\n\"banaan\",\"bloemkool\",\"\"\n";
		//"fruit","groente","saus"
		//"","wortel","mayonaise"
		//"banaan","bloemkool",""
		
		
		StringWriter w = new StringWriter();
		Exporter.export(dummydb,w);
		assertEquals(expected, w.toString());
	}

	@Test
	public void testGetAllColumns() {
		assertEquals(new HashSet(Arrays.asList(cols)),Exporter.getAllColumns(dummydb));
	}

	@Test
	public void testGenerateRow1() {
		String[] expected = {"","wortel","mayonaise"};
		assertEquals(expected,Exporter.generateRow(dummyrow1, Arrays.asList(cols)));
	}
	
	@Test
	public void testGenerateRow2() {
		String[] expected = {"banaan","bloemkool",""};
		assertEquals(expected,Exporter.generateRow(dummyrow2, Arrays.asList(cols)));
	}

}
