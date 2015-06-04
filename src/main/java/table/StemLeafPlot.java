package table;

import table.value.Column;
import table.value.ColumnTypeMismatchException;
import table.value.NumberValue;
import table.value.StringColumn;
import table.value.StringValue;
import table.value.Value;

import java.util.ArrayList;

public class StemLeafPlot extends Table {

  /**
   * Default serial id.
   */
  private static final long serialVersionUID = 1L;
  private Table table;

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
    try {
      create(column, order);
    } catch (ColumnTypeMismatchException e) {
    }
  }

  private void create(String column, int order) throws ColumnTypeMismatchException {
    ArrayList<Column> columns = new ArrayList<Column>();
    columns.add(new StringColumn("Stem"));
    columns.add(new StringColumn("Leaf"));

    ArrayList<String> values = new ArrayList<String>();
    for (Record record : table) {
      if (record.get(column).isNumeric()) {
        NumberValue number = (NumberValue) record.get(column);
        values.add(Integer.toString((int) number.getValue()));
      }
    }

    for (String number : values) {
      String[] characters = number.split("");
      String[] supplementedCharacters;
      boolean added = false;

      if (characters.length < order) {
        supplementedCharacters = new String[order];
        int count = 0;
        while (count < order - characters.length) {
          supplementedCharacters[count] = "0";
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

      String stem = supplementedCharacters[supplementedCharacters.length - order];
      String leaf = supplementedCharacters[supplementedCharacters.length - order + 1];

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

}
