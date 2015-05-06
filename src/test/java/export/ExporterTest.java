package export;

import static org.junit.Assert.assertEquals;
import input.Column;
import input.Settings;
import input.WrongXMLException;
import input.XMLReader;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import table.Record;
import table.Table;

public class ExporterTest {

	
	Record dummyrow1;
	Record dummyrow2;
	Table dummydb;
	
	
	Settings settings;
	String[] cols;
	
	@Before
	public void setUp() throws WrongXMLException{
		dummyrow1 = new Record();
		dummyrow1.put("groente", "wortel");
		dummyrow1.put("saus", "mayonaise");
		
		dummyrow2 = new Record();
		dummyrow2.put("fruit", "banaan");
		dummyrow2.put("groente", "bloemkool");
		
		dummydb = new Table();
		dummydb.add(dummyrow1);
		dummydb.add(dummyrow2);
		
		settings = XMLReader.readXMLFile("/settings.xml");
		ArrayList<Column> colstemp = settings.getColumns();
		cols = new String[colstemp.size()];
		for(int i = 0; i < colstemp.size(); i++) {
			cols[i] = colstemp.get(i).getName();
		}
	}
	
	@Test
	public void testExport() throws IOException {
		String expected = "\"fruit\";\"groente\";\"saus\"\n\"\";\"wortel\";\"mayonaise\"\n\"banaan\";\"bloemkool\";\"\"\n";
		//"fruit","groente","saus"
		//"","wortel","mayonaise"
		//"banaan","bloemkool",""
		
		
		StringWriter w = new StringWriter();
		Exporter.export(dummydb,w, settings);
		assertEquals(expected, w.toString());
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
