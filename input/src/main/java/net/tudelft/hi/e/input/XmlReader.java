package net.tudelft.hi.e.input;

import net.tudelft.hi.e.common.exceptions.WrongXmlException;
import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.DateColumn;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Class for reading and parsing an xml file.
 *
 */
public class XmlReader {

  private XmlReader() {}

  private static void checkDuplicateColumns(final List<Column> columns) throws WrongXmlException {
    final Set<String> columnsSet = new HashSet<String>();

    for (final Column column : columns) {
      columnsSet.add(column.getName());
    }

    if (columnsSet.size() != columns.size()) {
      throw new WrongXmlException("Duplicate column names found!");
    }
  }

  private static void readColumns(final Settings settings, final NodeList columns)
      throws WrongXmlException {
    final List<String> checkContains = new ArrayList<String>();

    for (int i = 0; i < columns.getLength(); i++) {
      final Node node = columns.item(i);

      // Let each column decide how it is specified.
      final Column newColumn = Column.readColumn((Element) node);

      // If a time column is present, the target column should be present as
      // well.
      if (newColumn instanceof DateColumn && ((DateColumn) newColumn).isTime()) {
        checkContains.add(((DateColumn) newColumn).getTargetDate());
      }

      settings.addColumn(newColumn);
    }

    for (final Column column : settings.getColumns()) {
      if (column instanceof DateColumn) {
        checkContains.remove(column.getName());
      }
    }

    // If the list is not empty, one or more target column are not present.
    if (!checkContains.isEmpty()) {
      throw new WrongXmlException("Target column(s) not found!");
    }
  }

  /**
   * Reads an xml file and transforms it into a Settings object.
   *
   * @param xmlFile the filename.
   * @return a Settings object representing the file.
   * @throws WrongXmlException when the file is wrongly formatted.
   */
  public static Settings readXmlFile(final String xmlFile) throws WrongXmlException {
    final Settings res = new Settings();

    try {
      // Parse the file.
      final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

      final File file = new File(xmlFile);
      final Document doc = documentBuilder.parse(file);

      // Get the root element.
      final Element rootElement = doc.getDocumentElement();
      rootElement.normalize();

      // Let Settings decide what needs to be present.
      res.readSettings(rootElement);

      // Get the list of columns
      final NodeList columns = doc.getElementsByTagName("column");
      readColumns(res, columns);

      // Check if duplicate column names exist.
      checkDuplicateColumns(res.getColumns());
    } catch (SAXException | IOException | ParserConfigurationException ex) {
      throw new WrongXmlException(ex);
    }
    return res;
  }
}
