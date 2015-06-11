package net.tudelft.hi.e.data;

import net.tudelft.hi.e.common.exceptions.WrongXmlException;
import org.w3c.dom.Element;

/**
 * Case class for specifying a column with numbers.
 *
 */
public class NumberColumn extends Column {

  /**
   * Constructs a new NumberColumn.
   *
   * @param name the name of the column.
   */
  public NumberColumn(final String name) {
    super(name);
  }

  @Override
  public Value convertToValue(final String text) throws ColumnTypeMismatchException {
    try {
      if ("null".equalsIgnoreCase(text) || text.isEmpty()) {
        return new NullValue();
      }

      return new NumberValue(Double.parseDouble(text));
    } catch (final NumberFormatException e) {
      throw new ColumnTypeMismatchException("\"" + text + "\" is not a numeric value");
    }
  }

  @Override
  public void read(final Element element) throws WrongXmlException {
  }

  @Override
  public String toString() {
    return super.toString() + ",\ttype: number";
  }
}
