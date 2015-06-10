package net.tudelft.hi.e.input;

import java.util.ArrayList;
import java.util.List;
import net.tudelft.hi.e.common.exceptions.WrongXmlException;
import net.tudelft.hi.e.data.Column;
import org.w3c.dom.Element;

/**
 * Settings class containing all program-wide settings.
 *
 */
public class Settings {
  private int startLine;
  private String delimiter;
  private String name;
  private List<Column> columns;

  /**
   * Creates a new Settings object.
   */
  public Settings() {
    startLine = 1;
    delimiter = ",";
    setColumns(new ArrayList<Column>());
  }

  public void addColumn(final Column column) {
    columns.add(column);
  }

  /**
   * @see java.lang.Object#equals(Object)
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final Settings that = (Settings) obj;
    if (this.delimiter.equals(that.delimiter) && this.startLine == that.startLine
        && this.columns.equals(that.columns)) {
      return true;
    }
    return false;
  }

  public List<Column> getColumns() {
    return columns;
  }

  public String getDelimiter() {
    return delimiter;
  }

  public String getName() {
    return name;
  }

  public int getStartLine() {
    return startLine;
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + columns.hashCode();
    result = prime * result + delimiter.hashCode();
    result = prime * result + startLine;
    return result;
  }

  /**
   * Reads settings from
   *
   * @param element The element to be read.
   * @throws WrongXmlException If the XML file isn't valid a WrongXmlException is thrown.
   */
  public void readSettings(final Element element) throws WrongXmlException {
    if (!"settings".equals(element.getNodeName())) {
      throw new WrongXmlException("Root element wrong! Expected: settings, actual: "
          + element.getNodeName());
    }

    final String startLineFromElement = element.getAttribute("startLine");

    if (startLineFromElement.isEmpty()) {
      throw new WrongXmlException("No startLine specified!");
    }

    try {
      setStartLine(Integer.parseInt(startLineFromElement));
    } catch (final NumberFormatException e) {
      throw new WrongXmlException("StartLine should be a number!");
    }

    final String delimiterFromElement = element.getAttribute("delimiter");

    if (delimiterFromElement.isEmpty()) {
      throw new WrongXmlException("No delimiter specified!");
    }

    setDelimiter(delimiterFromElement);

    final String nameFromElement = element.getAttribute("name");

    if (nameFromElement.isEmpty()) {
      throw new WrongXmlException("No table name specified!");
    }

    setName(nameFromElement);
  }

  public void setColumns(final List<Column> columns) {
    this.columns = columns;
  }

  public void setDelimiter(final String delimiter) {
    this.delimiter = delimiter;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setStartLine(final int startLine) {
    this.startLine = startLine;
  }

  @Override
  public String toString() {
    final StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("startLine:\t" + startLine + "\n");
    stringBuilder.append("delimiter:\t\"" + delimiter + "\"\n");
    stringBuilder.append("columns:\t");

    for (final Column column : columns) {
      stringBuilder.append(column.toString());
      stringBuilder.append("\n\t\t");
    }

    return stringBuilder.toString();
  }
}
