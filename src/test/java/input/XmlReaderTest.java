package input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import table.value.DateColumn;
import table.value.NumberColumn;
import table.value.StringColumn;

import java.text.SimpleDateFormat;

/**
 * XmlReader class testing the input.XmlReader class.
 * 
 */
public class XmlReaderTest {
  String folder = "src/main/resources";

  @Test
  public void correctXmlTest() throws WrongXmlException {
    final Settings settings = XmlReader.readXmlFile(folder + "/correct_xml.xml");

    assertEquals(7, settings.getStartLine());
    assertEquals(";", settings.getDelimiter());
    assertEquals("test", settings.getName());
    assertTrue(settings.getColumns().get(0) instanceof StringColumn);
    assertTrue(settings.getColumns().get(1) instanceof NumberColumn);
    assertTrue(settings.getColumns().get(2) instanceof DateColumn);
  }

  @Test(expected = WrongXmlException.class)
  public void duplicateNamesXmlTest() throws WrongXmlException {
    XmlReader.readXmlFile(folder + "/duplicate_names.xml");
  }

  @Test(expected = WrongXmlException.class)
  public void emptyFormatXmlTest() throws WrongXmlException {
    XmlReader.readXmlFile(folder + "/empty_format.xml");
  }

  @Test(expected = WrongXmlException.class)
  public void emptyNameXmlTest() throws WrongXmlException {
    XmlReader.readXmlFile(folder + "/empty_name.xml");
  }

  @Test(expected = WrongXmlException.class)
  public void noDelimiterXmlTest() throws WrongXmlException {
    XmlReader.readXmlFile(folder + "/no_delimiter.xml");
  }

  @Test(expected = WrongXmlException.class)
  public void noFormatTimeXmlTest() throws WrongXmlException {
    XmlReader.readXmlFile(folder + "/no_format_time.xml");
  }

  @Test(expected = WrongXmlException.class)
  public void noFormatXmlTest() throws WrongXmlException {
    XmlReader.readXmlFile(folder + "/no_format.xml");
  }

  @Test(expected = WrongXmlException.class)
  public void noNameXmlTest() throws WrongXmlException {
    XmlReader.readXmlFile(folder + "/no_name.xml");
  }

  @Test(expected = WrongXmlException.class)
  public void noSettingsXmlTest() throws WrongXmlException {
    XmlReader.readXmlFile(folder + "/no_settings.xml");
  }

  @Test(expected = WrongXmlException.class)
  public void noStartLineXmlTest() throws WrongXmlException {
    XmlReader.readXmlFile(folder + "/no_startline.xml");
  }

  @Test(expected = WrongXmlException.class)
  public void noTableNameXmlTest() throws WrongXmlException {
    XmlReader.readXmlFile(folder + "/no_table_name.xml");
  }

  @Test
  public void toStringTest() {
    final Settings settings = new Settings();
    settings.addColumn(new StringColumn("col"));
    settings.addColumn(new NumberColumn("col2"));

    final DateColumn dc = new DateColumn("col3", "yyMMdd");
    final SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
    settings.addColumn(dc);

    String res = "startLine:\t" + 1 + "\n";
    res += "delimiter:\t\"" + "," + "\"\n";
    res += "columns:\tname: col,\ttype: text\n\t\t";
    res += "name: col2,\ttype: number\n\t\t";
    res += "name: col3,\ttype: date,\tformat: yyMMdd\n\t\t";
    assertEquals(res, settings.toString());
    assertEquals(sdf, dc.getFormat());
  }

  @Test(expected = WrongXmlException.class)
  public void wrongConfigXmlTest() throws WrongXmlException {
    XmlReader.readXmlFile(folder + "/wrong_xml_config.xml");
  }

  @Test(expected = WrongXmlException.class)
  public void wrongStartLineXmlTest() throws WrongXmlException {
    XmlReader.readXmlFile(folder + "/wrong_startline.xml");
  }

  @Test(expected = WrongXmlException.class)
  public void wrongTargetTimeXmlTest() throws WrongXmlException {
    XmlReader.readXmlFile(folder + "/wrong_target_time.xml");
  }

  @Test(expected = WrongXmlException.class)
  public void wrongTypeXmlTest() throws WrongXmlException {
    XmlReader.readXmlFile(folder + "/wrong_type.xml");
  }
  
  @Test(expected = WrongXmlException.class)
  public void excelFormatTimeTest() throws WrongXmlException {
    XmlReader.readXmlFile(folder + "/excel_format_time.xml");
  }
}
