package net.tudelft.hi.e.gui;

import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.DateColumn;
import net.tudelft.hi.e.data.NumberColumn;
import net.tudelft.hi.e.data.StringColumn;
import net.tudelft.hi.e.input.Settings;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * A TableModel that wraps around a Settings instance, and updates it as the table using this model
 * is edited.
 *
 */
public class SettingsTableModel extends AbstractTableModel {

  /**
   * Default id.
   */
  private static final long serialVersionUID = 1L;
  private Settings settings;
  private String[] examples;

  private static final String[] colnames = { "name", "type", "format", "target", "example" };

  public SettingsTableModel(Settings settings, String[] examples) {
    this.settings = settings;
    this.examples = examples;
  }

  @Override
  public int getRowCount() {
    return settings.getColumns().size();
  }

  @Override
  public int getColumnCount() {
    return colnames.length;
  }

  @Override
  public String getColumnName(int columnIndex) {
    return colnames[columnIndex];
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return String.class;
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    if (columnIndex == 4) {
      return false;
    }
    return "date".equals(settings.getColumns().get(rowIndex).getType()) || columnIndex < 2;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    if (columnIndex == 4) {
      return examples[rowIndex];
    }

    Column col = settings.getColumns().get(rowIndex);

    if (columnIndex == 0) {
      return col.getName();
    }
    if (columnIndex == 1) {
      return col.getType();
    }
    if (!"date".equals(col.getType())) {
      return "";
    }

    DateColumn dc = (DateColumn) col;

    if (columnIndex == 2) {
      return dc.getFormatStr();
    }

    return dc.getTargetDate();
  }

  @Override
  public void setValueAt(Object avalue, int rowIndex, int columnIndex) {
    Column col = settings.getColumns().get(rowIndex);
    if (columnIndex == 0) {
      settings.getColumns().get(rowIndex).setName((String) avalue);
    }
    if (columnIndex == 1) {
      changeType((String) avalue, rowIndex, col);
    }
    if (columnIndex == 2) {
      ((DateColumn) col).setFormat((String) avalue);
    }
    if (columnIndex == 3) {
      ((DateColumn) col).setTargetDate((String) avalue);
    }
    fireTableDataChanged();
  }

  private void changeType(String avalue, int rowIndex, Column col) {
    String prevname = col.getName();
    String prevtype = col.getType();
    Column newcol;
    if (prevtype.equals(avalue)) {
      return;
    }
    if ("number".equals(avalue)) {
      newcol = new NumberColumn(prevname);
    } else if ("date".equals(avalue)) {
      newcol = new DateColumn(prevname);
    } else {
      newcol = new StringColumn(prevname);
    }
    settings.getColumns().set(rowIndex, newcol);
  }

  /**
   * Makes a list of columns based on the headers in a file.
   * 
   * @param headers
   *          list of headers
   * @return Columns to use in settings
   */
  public static List<Column> generateColsByHeaders(String[] headers) {
    ArrayList<Column> cols = new ArrayList<Column>();
    for (String header : headers) {
      cols.add(new StringColumn(header));
    }
    return cols;
  }

  /**
   * Makes a list of columns based on the amount of cols.
   * 
   * @param amount
   *          amount of columns to make
   * @return Columns to use in settings
   */
  public static List<Column> generateEmptyCols(int amount) {
    ArrayList<Column> cols = new ArrayList<Column>();
    for (int i = 0; i < amount; i++) {
      cols.add(new StringColumn("Col " + (i + 1)));
    }
    return cols;
  }

}
