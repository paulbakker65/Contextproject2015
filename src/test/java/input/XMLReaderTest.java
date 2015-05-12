package input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.Test;

public class XMLReaderTest {

	@Test
	public void correctXMLTest() {
		try {
			Settings settings = XMLReader.readXMLFile("/correct_xml.xml");
				
			assertEquals(7, settings.getStartLine());
			assertEquals(";", settings.getDelimiter());
			assertTrue(settings.getColumns().get(0) instanceof StringColumn);
			assertTrue(settings.getColumns().get(1) instanceof NumberColumn);
			assertTrue(settings.getColumns().get(2) instanceof DateColumn);
		} catch (WrongXMLException e) {
			fail("No exception expected!");
		}
	}
	
	@Test(expected=WrongXMLException.class)
	public void wrongStartLineXMLTest() throws WrongXMLException {		
		@SuppressWarnings("unused")
		Settings settings = XMLReader.readXMLFile("/wrong_startline.xml");		
	}
	
	@Test(expected=WrongXMLException.class)
	public void noStartLineXMLTest() throws WrongXMLException {		
		@SuppressWarnings("unused")
		Settings settings = XMLReader.readXMLFile("/no_startline.xml");		
	}
	
	@Test(expected=WrongXMLException.class)
	public void noDelimiterXMLTest() throws WrongXMLException {		
		@SuppressWarnings("unused")
		Settings settings = XMLReader.readXMLFile("/no_delimiter.xml");		
	}
	
	@Test(expected=WrongXMLException.class)
	public void noFormatXMLTest() throws WrongXMLException {		
		@SuppressWarnings("unused")
		Settings settings = XMLReader.readXMLFile("/no_format.xml");		
	}
	
	@Test(expected=WrongXMLException.class)
	public void noNameXMLTest() throws WrongXMLException {		
		@SuppressWarnings("unused")
		Settings settings = XMLReader.readXMLFile("/no_name.xml");		
	}
	
	@Test(expected=WrongXMLException.class)
	public void noSettingsXMLTest() throws WrongXMLException {		
		@SuppressWarnings("unused")
		Settings settings = XMLReader.readXMLFile("/no_settings.xml");		
	}
	
	@Test(expected=WrongXMLException.class)
	public void wrongTypeXMLTest() throws WrongXMLException {		
		@SuppressWarnings("unused")
		Settings settings = XMLReader.readXMLFile("/wrong_type.xml");		
	}
	
	@Test(expected=WrongXMLException.class)
	public void wrongConfigXMLTest() throws WrongXMLException {		
		@SuppressWarnings("unused")
		Settings settings = XMLReader.readXMLFile("/wrong_xml_config.xml");		
	}
	
	@Test(expected=WrongXMLException.class)
	public void emptyFormatXMLTest() throws WrongXMLException {		
		@SuppressWarnings("unused")
		Settings settings = XMLReader.readXMLFile("/empty_format.xml");		
	}
	
	@Test(expected=WrongXMLException.class)
	public void emptyNameXMLTest() throws WrongXMLException {		
		@SuppressWarnings("unused")
		Settings settings = XMLReader.readXMLFile("/empty_name.xml");		
	}
	
	@Test(expected=WrongXMLException.class)
	public void noFormatTimeXMLTest() throws WrongXMLException {		
		@SuppressWarnings("unused")
		Settings settings = XMLReader.readXMLFile("/no_format_time.xml");		
	}
	
	@Test(expected=WrongXMLException.class)
	public void noTargetTimeXMLTest() throws WrongXMLException {		
		@SuppressWarnings("unused")
		Settings settings = XMLReader.readXMLFile("/no_target_time.xml");		
	}
	
	@Test(expected=WrongXMLException.class)
	public void wrongTargetTimeXMLTest() throws WrongXMLException {		
		@SuppressWarnings("unused")
		Settings settings = XMLReader.readXMLFile("/wrong_target_time.xml");		
	}	
		
	@Test
	public void toStringTest() {
		Settings settings = new Settings();
		settings.addColumn(new StringColumn("col"));
		settings.addColumn(new NumberColumn("col2"));
		
		DateColumn dc = new DateColumn("col3", "yyMMdd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");	
		settings.addColumn(dc);
		
		String res = "startLine:\t" + 1 + "\n";
		res += "delimiter:\t\"" + "," + "\"\n";
		res += "columns:\tname: col,\ttype: text\n\t\t";
		res += "name: col2,\ttype: number\n\t\t";
		res += "name: col3,\ttype: date,\tformat: yyMMdd\n\t\t";
		assertEquals(res, settings.toString());
		assertEquals(sdf, dc.getFormat());		
	}
}
