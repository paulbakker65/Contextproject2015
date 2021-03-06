package net.tudelft.hi.e.data;

import net.tudelft.hi.e.common.exceptions.ColumnTypeMismatchException;
import net.tudelft.hi.e.common.exceptions.WrongXmlException;

import org.w3c.dom.Element;

import java.util.Objects;

/**
 * Abstract class for specifying a column type in a table. It specifies how all the values in a
 * column must look like.
 */
public abstract class Column {

  /**
   * Each column must have a name.
   */
  protected String name;

  /**
   * Constructs a new column with a name.
   *
   * @param name the name of the column.
   */
  public Column(final String name) {
    this.setName(name);
  }

  /**
   * Create a Column object using an XML Element.
   *
   * @param element Element to be read.
   * @return Subclass of Column object as specified in Element.
   * @throws WrongXmlException If the element either doesn't contain a name nor contains a correct
   *         type a WrongXmlException is thrown.
   */
  public static Column readColumn(final Element element) throws WrongXmlException {
    final String name = element.getAttribute("name");

    if (name.isEmpty()) {
      throw new WrongXmlException("No name specified!");
    }

    Column res = null;
    final String type = element.getAttribute("type");
    if ("number".equals(type)) {
      res = new NumberColumn(name);
    } else if ("date".equals(type)) {
      res = new DateColumn(name);
    } else if ("string".equals(type) || "".equals(type)) {
      res = new StringColumn(name);
    } else {
      throw new WrongXmlException("Wrong type specified!");
    }

    res.read(element);
    return res;
  }

  /**
   * Convert a string into a value if it satisfies the column's type.
   *
   * @param text the string to convert
   * @return a Value that has the correct type.
   * @throws ColumnTypeMismatchException if the string cannot be converted.
   */
  public abstract Value convertToValue(String text) throws ColumnTypeMismatchException;

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Column other = (Column) obj;
    if (!Objects.equals(this.name, other.name)) {
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
   * @param name the new name of the column.
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
