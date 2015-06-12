package xmleditor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * A model that represents a settings file. It can be used as model in a table.
 * Other properties, such as start line or delimiter, can be given via the constructor.
 * Finally there is an export to xml stream.
 */
public class ColumnSettingsTableModel extends AbstractTableModel {

  private static String[] attributes = new String[] { "name", "type", "format", "example" };

  private static final long serialVersionUID = 1L;

  private Document doc;

  private Element settings;

  private List<Element> cols;

  /**
   * Creates a new column settings model.
   * @param colnames name of the columns
   * @param firstrow example values of the first row
   * @param delim delimiter, only for csv
   * @param startLine where the actual data begins
   * @throws ParserConfigurationException exception
   */
  public ColumnSettingsTableModel(String[] colnames, String[] firstrow, String delim,
      String startLine) throws ParserConfigurationException {
    super();

    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    // root elements
    doc = docBuilder.newDocument();

    settings = doc.createElement("settings");
    settings.setAttribute("delimiter", delim);
    settings.setAttribute("startLine", startLine);

    for (int i = 0; i < colnames.length; i++) {
      Element colelem = doc.createElement("column");
      colelem.setAttribute("name", colnames[i]);
      colelem.setAttribute("type", "text");
      if (i < firstrow.length) {
        colelem.setAttribute("example", firstrow[i]);
      }

      settings.appendChild(colelem);
    }

    doc.appendChild(settings);

    regenCols();

  }

  private void regenCols() {
    NodeList colnodes = settings.getElementsByTagName("column");
    cols = new ArrayList<Element>();
    for (int i = 0; i < colnodes.getLength(); i++) {
      cols.add((Element) colnodes.item(i));
    }
  }

  @Override
  public int getRowCount() {
    return cols.size();
  }

  @Override
  public int getColumnCount() {
    return attributes.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    return cols.get(rowIndex).getAttribute(attributes[columnIndex]);
  }

  @Override
  public String getColumnName(int col) {
    return attributes[col];
  }

  @Override
  public boolean isCellEditable(int row, int col) {
    return col != 3;
  }

  @Override
  public void setValueAt(Object value, int row, int col) {
    String att = attributes[col];
    String val = (String) value;
    if (att == "type" && !"text".equals(val) && !"number".equals(val) && !"date".equals(val)) {
      val = "text";
    }

    cols.get(row).setAttribute(att, val);
  }

  /**
   * Exports the Settings Model to an outputstream.
   * 
   * @param out
   *          the outputstream
   * @param name
   *          name of the table for internal use.
   * @throws TransformerException
   *           exception
   */
  public void export(OutputStream out, String name) throws TransformerException {
    settings.setAttribute("name", name);
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(out);
    transformer.transform(source, result);
  }

}