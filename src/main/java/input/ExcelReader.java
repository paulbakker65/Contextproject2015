package input;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Reads the first sheet of an XLSX 2007 File.
 * 
 * @author unset
 *
 */
public class ExcelReader extends Reader {

  private Iterator<Row> rowIterator;
  private XSSFWorkbook workbook;
  private int sheetindex;

  /**
   * Creates a new XLS reader.
   *
   * @param path
   *          path to the file
   * @param sheetindex
   *          sheet number (0-based)
   */
  public ExcelReader(String path, int sheetindex) {
    super(path);
    this.sheetindex = sheetindex;
    System.setProperty("org.apache.poi.util.POILogger", "org.apache.commons.logging.impl.NoOpLog");
  }

  /**
   * Loads the file, it it hasn't been loaded yet.
   */
  private void loadFile() {
    if (workbook == null) {
      try {
        workbook = new XSSFWorkbook(filepath);
        XSSFSheet sheet = workbook.getSheetAt(sheetindex);
        rowIterator = sheet.rowIterator();
      } catch (Exception e) {
        throw new Error("Loading of xlsx failed: " + e.getMessage());
      }
    }
  }

  @Override
  public void close() throws IOException {
    if (workbook != null) {
      workbook.close();
    }
  }

  @Override
  public String[] readRow() throws IOException {
    loadFile();
    if (rowIterator.hasNext()) {
      Row row = rowIterator.next();
      return rowToStrings(row);
    } else {
      return null;
    }
  }

  private static String[] rowToStrings(Row row) {
    List<String> values = new ArrayList<String>();
    int pos = 0;
    for (Cell c : row) {
      while (c.getColumnIndex() > pos) {
        pos++;
        values.add("");
      }
      values.add(cellToString(c));
      pos++;
    }
    return values.toArray(new String[0]);
  }

  private static String cellToString(Cell cell) {
    if (cell == null) {
      return "";
    } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(cell)) {
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
      Date date = cell.getDateCellValue();
      return dateFormat.format(date);
    } else {
      return cell.toString();
    }
  }

}
