package input;

import java.util.ArrayList;

import org.w3c.dom.Element;

/**
 * Settings class containing all program-wide settings.
 * 
 */
public class Settings {
  private int startLine;
  private String delimiter;
  private ArrayList<Column> columns;

  public Settings() {
    startLine = 1;
    delimiter = ",";
    setColumns(new ArrayList<Column>());
  }

  public int getStartLine() {
    return startLine;
  }

  public void setStartLine(int startLine) {
    this.startLine = startLine;
  }

  public String getDelimiter() {
    return delimiter;
  }

  public void setDelimiter(String delimiter) {
    this.delimiter = delimiter;
  }

  public ArrayList<Column> getColumns() {
    return columns;
  }

  public void setColumns(ArrayList<Column> columns) {
    this.columns = columns;
  }

  public void addColumn(Column column) {
    columns.add(column);
  }

  @Override
  public String toString() {
    StringBuilder b = new StringBuilder();
    b.append("startLine:\t" + startLine + "\n");
    b.append("delimiter:\t\"" + delimiter + "\"\n");
    b.append("columns:\t");
    
    for (Column column : columns) {
      b.append(column.toString());
      b.append("\n\t\t");
    }

    return b.toString();
  }

  public void readSettings(Element element) throws WrongXMLException {
    if (!element.getNodeName().equals("settings")) {
      throw new WrongXMLException("Root element wrong! Expected: settings, actual: "
          + element.getNodeName());
    }

    String startLine = element.getAttribute("startLine");

    if (startLine.isEmpty()) {
      throw new WrongXMLException("No startLine specified!");
    }

    try {
      setStartLine(Integer.parseInt(startLine));
    } catch (NumberFormatException e) {
      throw new WrongXMLException("StartLine should be a number!");
    }

    String delimiter = element.getAttribute("delimiter");

    if (delimiter.isEmpty()) {
      throw new WrongXMLException("No delimiter specified!");
    }

    setDelimiter(delimiter);
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((columns == null) ? 0 : columns.hashCode());
    result = prime * result + ((delimiter == null) ? 0 : delimiter.hashCode());
    result = prime * result + startLine;
    return result;
  }

  /**
   * @see java.lang.Object#equals(Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Settings that = (Settings) obj;
    if (this.delimiter.equals(that.delimiter) && this.startLine == that.startLine
        || this.columns.equals(that.columns)) {
      return true;
    }
    return false;
  }
}
