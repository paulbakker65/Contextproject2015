package net.tudelft.hi.e.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StemLeafPlot extends Table {

  /**
   * Default serial id.
   */
  private static final long serialVersionUID = 1L;
  private Table table;
  private transient List<Column> columns;
  private String column;
  private int order;

  /**
   * Creates a stem leaf plot based on the give table.
   *
   * @param table
   *          table on which to create the stem leaf plot.
   * @param column
   *          the column containing the numbers.
   */
  public StemLeafPlot(Table table, String column, int order) {
    super();
    this.table = table;
    this.column = column;
    this.order = order;
    columns = new ArrayList<Column>();
    columns.add(new StringColumn("Stem"));
    columns.add(new StringColumn("Leaf"));
    addStemLeafValues(makeStringofNumbers());

  }

  private List<String> makeStringofNumbers() {
    List<String> values = new ArrayList<String>();
    for (Record record : table) {
      if (record.get(column).isNumeric()) {
        NumberValue number = (NumberValue) record.get(column);
        values.add(Integer.toString((int) number.getValue()));
      }
    }

    return values;
  }

  /**
   * If the order is greater then the length of the number, add zeros.
   *
   * @param number
   *          number represented as string.
   * @return String array with the number supplemented with zero's
   */
  public char[] createSupplementArray(String number) {

    char[] characters = number.toCharArray();
    char[] supplementedCharacters;

    if (characters.length < order) {
      supplementedCharacters = new char[order];
      int count = 0;
      while (count < order - characters.length) {
        supplementedCharacters[count] = '0';
        count++;
      }
      int characterCount = 0;
      while (count < order) {
        supplementedCharacters[count] = characters[characterCount];
        count++;
        characterCount++;
      }
    } else {
      supplementedCharacters = characters;
    }
    return supplementedCharacters;
  }

  /**
   * Picks the stem and leaf for each record and adds it to the stem leaf plot.
   *
   * @param values
   *          All the values to be checked.
   */
  public void addStemLeafValues(List<String> values) {
    for (String number : values) {
      char[] array = createSupplementArray(number);
      boolean added = false;

      String stem = "" + array[array.length - order];
      String leaf = "" + array[array.length - order + 1];

      for (Record record : this) {
        if (record.get("Stem").toString().equals(stem)) {
          String current = record.get("Leaf").toString();
          record.put("Leaf", new StringValue(current.concat(leaf)));
          added = true;
        }
      }
      if (!added) {
        this.add(new Record(columns, new Value[] { new StringValue(stem), new StringValue(leaf) }));
      }

    }

  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 23 * hash + Objects.hashCode(this.table);
    hash = 23 * hash + Objects.hashCode(this.columns);
    hash = 23 * hash + Objects.hashCode(this.column);
    hash = 23 * hash + this.order;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final StemLeafPlot other = (StemLeafPlot) obj;
    return deepEquals(other);
  }

  private boolean deepEquals(StemLeafPlot other) {
    return equalTargetColumn(other) && equalTable(other) && equalOrder(other);
  }

  private boolean equalTargetColumn(StemLeafPlot other) {
    return Objects.equals(this.column, other.column);
  }

  private boolean equalTable(StemLeafPlot other) {
    if (!Objects.equals(this.table, other.table)) {
      return false;
    }
    return Objects.equals(this.columns, other.columns);
  }

  private boolean equalOrder(StemLeafPlot other) {
    return this.order == other.order;
  }

}
