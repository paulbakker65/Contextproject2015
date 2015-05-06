package input;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import table.Record;
import table.Table;

public class CSVReaderTest {

	Record dummyrow;
	Record dummyrow1;
	Record dummyrow2;
	Table dummydb;
	
	Settings settings;
	Table table;
	
	@Before
	public void setUp() throws WrongXMLException{
		dummyrow = new Record();
		dummyrow.put("fruit", "appel");
		dummyrow.put("groente", "aardappel");
		dummyrow.put("saus", "appelmoes");
		
		dummyrow1 = new Record();
		dummyrow1.put("groente", "wortel");
		dummyrow1.put("saus", "mayonaise");
		
		dummyrow2 = new Record();
		dummyrow2.put("fruit", "banaan");
		dummyrow2.put("groente", "bloemkool");
		
		dummydb = new Table();
		dummydb.add(dummyrow);
		dummydb.add(dummyrow1);
		dummydb.add(dummyrow2);
		
		settings = XMLReader.readXMLFile("/settings.xml");
	}
	
	@Test
	public void goodWeatherTest() throws IOException {
		table = CSVReader.read("/csvexample.csv", settings);
		assertEquals(table.get(0), dummyrow);
		assertEquals(table.get(1), dummyrow1);
		assertEquals(table.get(2), dummyrow2);
	}
	
}
