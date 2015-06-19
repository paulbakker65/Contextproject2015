package net.tudelft.hi.e.input;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Reads the first sheet of an XLSX 2007 File.
 *
 * @author unset
 *
 */
public class ExcelReader extends Reader {

  private Iterator<Row> rowIterator;

  private XSSFWorkbook workbook;

  private final int sheetindex;

  /**
   * Creates a new XLS reader.
   *
   * @param path path to the file
   * @param sheetindex sheet number (0-based)
   */
  public ExcelReader(final String path, final int sheetindex) {
    super(path);
    this.sheetindex = sheetindex;
    System.setProperty("org.apache.poi.util.POILogger", "org.apache.commons.logging.impl.NoOpLog");
  }

  private static String cellToString(final Cell cell) {
    if (cell == null) {
      return "";
    } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(cell)) {
      final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
      final Date date = cell.getDateCellValue();
      return dateFormat.format(date);
    } else {
      return cell.toString();
    }
  }

  private static String[] rowToStrings(final Row row) {
    final List<String> values = new ArrayList<String>();
    int pos = 0;
    for (final Cell c : row) {
      while (c.getColumnIndex() > pos) {
        pos++;
        values.add("");
      }
      values.add(cellToString(c));
      pos++;
    }
    return values.toArray(new String[0]);
  }

  @Override
  public void close() throws IOException {
    if (workbook != null) {
      workbook.close();
    }
  }

  /**
   * Loads the file, it it hasn't been loaded yet.
   */
  private void loadFile() {
    if (workbook == null) {
      try {
        workbook = new XSSFWorkbook(filepath);
        final XSSFSheet sheet = workbook.getSheetAt(sheetindex);
        rowIterator = sheet.rowIterator();
      } catch (final Exception ex) {
        Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE,
            "Loading of xlsx failed: " + ex.getMessage(), ex);
      }
    }
  }

  @Override
  public String[] readRow() throws IOException {
    loadFile();
    if (rowIterator.hasNext()) {
      final Row row = rowIterator.next();
      return rowToStrings(row);
    } else {
      return new String[0];
    }
  }
}
