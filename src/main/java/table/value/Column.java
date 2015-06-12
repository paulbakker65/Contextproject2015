package table.value;

import input.WrongXmlException;

import org.w3c.dom.Element;

/**
 * Abstract class for specifying a column type in a table. It specifies how all the values in a
 * column must look like.
 * 
 * @author Robin
 *
 */
public abstract class Column {

  /**
   * Create a Column object using an XML Element.
   * 
   * @param element
   *          Element to be read.
   * @return Subclass of Column object as specified in Element.
   * @throws WrongXmlException
   *           If the element either doesn't contain a name nor contains a correct type a
   *           WrongXmlException is thrown.
   */
  public static Column readColumn(final Element element) throws WrongXmlException {
    final String name = element.getAttribute("name");

    if (name.isEmpty()) {
      throw new WrongXmlException("No name specified!");
    }

    Column res = null;
    final String type = element.getAttribute("type");
    switch (type) {
      case "number":
        res = new NumberColumn(name);
        break;
      case "date":
        res = new DateColumn(name);
        break;
      case "string":
        res = new StringColumn(name);
        break;
      case "":
        res = new StringColumn(name);
        break;
      default:
        throw new WrongXmlException("Wrong type specified!");
    }

    res.read(element);
    return res;
  }

  /**
   * Each column must have a name.
   */
  protected String name;

  /**
   * Constructs a new column with a name.
   * 
   * @param name
   *          the name of the column.
   */
  public Column(final String name) {
    this.setName(name);
  }

  /**
   * Convert a string into a value if it satisfies the column's type.
   * 
   * @param text
   *          the string to convert
   * @return a Value that has the correct type.
   * @throws ColumnTypeMismatchException
   *           if the string cannot be converted.
   */
  public abstract Value convertToValue(String text) throws ColumnTypeMismatchException;

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Column other = (Column) obj;
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

  /**
   * Returns the name of the column.
   * 
   * @return the name of the column.
   */
  public String getName() {
    return name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  public abstract void read(Element element) throws WrongXmlException;

  /**
   * Gives the column a new name.
   * 
   * @param name
   *          the new name of the column.
   */
  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "name: " + name;
  }

  public abstract String getType();
}
