package export;

import input.Settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import table.value.Column;
import table.value.DateColumn;
import table.value.TimeColumn;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class SettingsWriter {
  
  /**
   * Generates the xml document and writes it to a file.
   * @param settings The settings file to convert to a xml document.
   * @param file The file path to write the xml document to.
   */
  public static void writeSettings(final Settings settings, final File file) throws Exception {
    Document document = newDocument();
    
    Element settingsNode = addSettings(document, settings);
    
    addColumns(document, settings, settingsNode);
    
    writeXmlFile(document, file);
  }
  
  /**
   * Creates a new xml document.
   */
  private static Document newDocument() throws ParserConfigurationException {
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
    return docBuilder.newDocument();
  }
  
  /**
   * Creates the settings node and sets the required attributes.
   */
  private static Element addSettings(Document document, Settings settings) {
    Element element = document.createElement("settings");
    element.setAttribute("name", settings.getName());
    element.setAttribute("startLine", Integer.toString(settings.getStartLine()));
    element.setAttribute("delimiter", settings.getDelimiter());
    
    document.appendChild(element);
    return element;
  }
  
  /**
   * For each column in the table it adds a column node to the xml.
   */
  private static void addColumns(Document document, Settings settings, Element settingsNode) {
    for (Column column : settings.getColumns()) {
      Element element = createColumn(document, column);
      settingsNode.appendChild(element);
    }
  }
  
  /**
   * Creates a column node for the given column. 
   * Use addColumns to create and add all the columns to the xml from the settings.
   * @param column The column to write create the node for.
   */
  private static Element createColumn(Document document, Column column) {
    final String type = column.getType();
    Element element = document.createElement("column");
    element.setAttribute("name", column.getName()); 
    element.setAttribute("type", type);
    
    if (type.equals("date")) {
      element.setAttribute("format", ((DateColumn)column).getFormatStr());
    } else if (type.equals("time")) {
      element.setAttribute("format", ((TimeColumn)column).getFormatStr());
      element.setAttribute("target", ((TimeColumn)column).getTargetDate());
    }
    return element;
  }
  
  /**
   * Writes the xml document to a file.
   * @param document The xml document.
   * @param file The file path to write the document to.
   */
  private static void writeXmlFile(Document document, File file) throws TransformerException {
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    
    DOMSource source = new DOMSource(document);
    StreamResult result = new StreamResult(file);

    transformer.transform(source, result);
  }
  
  private SettingsWriter() { }

}