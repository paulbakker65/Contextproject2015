package net.tudelft.hi.e.export;

import net.tudelft.hi.e.common.exceptions.ExportException;
import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.DateColumn;
import net.tudelft.hi.e.input.Settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

public final class SettingsWriter {

  private SettingsWriter() {
  }

  /**
   * Generates the xml document and writes it to a file.
   *
   * @param settings
   *         The settings file to convert to a xml document.
   * @param file
   *         The file path to write the xml document to.
   *
   * @throws ExportException
   *         if there is an error exporting the data.
   */
  public static void writeSettings(final Settings settings, final File file) throws
          ExportException {
    Document document = null;
    Element settingsNode = null;
    try {
      document = newDocument();
      settingsNode = addSettings(document, settings);
      addColumns(document, settings, settingsNode);
    } catch (ParserConfigurationException ex) {
      throw new ExportException(ex);
    }
    try {
      writeXmlFile(document, file);
    } catch (TransformerException ex) {
      throw new ExportException(ex);
    }
  }

  /**
   * Creates a new xml document.
   */
  private static Document newDocument() throws ParserConfigurationException {
    final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

    return docBuilder.newDocument();
  }

  /**
   * Creates the settings node and sets the required attributes.
   */
  private static Element addSettings(final Document document, final Settings settings) {
    final Element element = document.createElement("settings");
    element.setAttribute("name", settings.getName());
    element.setAttribute("startLine", Integer.toString(settings.getStartLine()));
    element.setAttribute("delimiter", settings.getDelimiter());

    document.appendChild(element);
    return element;
  }

  /**
   * For each column in the table it adds a column node to the xml.
   */
  private static void addColumns(final Document document, final Settings settings,
          final Element settingsNode) {
    for (final Column column : settings.getColumns()) {
      final Element element = createColumn(document, column);
      settingsNode.appendChild(element);
    }
  }

  /**
   * Creates a column node for the given column. Use addColumns to create and add all the columns to
   * the xml from the settings.
   *
   * @param column
   *         The column to write create the node for.
   */
  private static Element createColumn(final Document document, final Column column) {
    final String type = column.getType();
    final Element element = document.createElement("column");
    element.setAttribute("name", column.getName());
    element.setAttribute("type", type);

    if ("date".equals(type)) {
      element.setAttribute("format", ((DateColumn) column).getFormatStr());
      if (((DateColumn) column).isTime()) {
        element.setAttribute("target", ((DateColumn) column).getTargetDate());
      }
    }

    return element;
  }

  /**
   * Writes the xml document to a file.
   *
   * @param document
   *         The xml document.
   * @param file
   *         The file path to write the document to.
   */
  private static void writeXmlFile(final Document document, final File file) throws
          TransformerException {
    final TransformerFactory transformerFactory = TransformerFactory.newInstance();
    final Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

    final DOMSource source = new DOMSource(document);
    final StreamResult result = new StreamResult(file);

    transformer.transform(source, result);
  }

}
