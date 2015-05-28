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

  Iterator<Row> rowIterator;
  XSSFWorkbook workbook;

  /**
   * Creates a new XLS reader.
   * 
   * @param path
   *          path to the file
   * @param sheetindex
   *          sheet number (0-based)
   * @throws IOException
   */
  public ExcelReader(String path, int sheetindex) throws IOException {
    super(path);
    System.setProperty("org.apache.poi.util.POILogger", "org.apache.commons.logging.impl.NoOpLog");
    workbook = new XSSFWorkbook(filepath);
    XSSFSheet sheet = workbook.getSheetAt(sheetindex);
    rowIterator = sheet.rowIterator();
  }

  @Override
  public String[] readRow() throws IOException {
    if (rowIterator.hasNext()) {
      System.out.println();
      Row r = rowIterator.next();
      return rowToStrings(r);
    } else {
      return null;
    }
  }

  private static String[] rowToStrings(Row r) {
    List<String> values = new ArrayList<String>();
    int pos = 0;
    for (Cell c : r) {
      while (c.getColumnIndex() > pos) {
        pos++;
        values.add("");
      }
      values.add(cellToString(c));
      pos++;
    }
    return values.toArray(new String[0]);
  }

  private static String cellToString(Cell c) {
    if (c == null) {
      return "";
    } else if (c.getCellType() == Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(c)) {
      DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
      Date d = c.getDateCellValue();
      return df.format(d);
    } else {
      return c.toString();
    }
  }

  @Override
  public void close() throws IOException {
    workbook.close();
  }

}
