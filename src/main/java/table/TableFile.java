package table;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class for reading and writing table files.
 */
public class TableFile {
  private TableFile() {
  }

  /**
   * Writes a table to a file.
   * 
   * @param table
   *          the table to write.
   * @param path
   *          the path to write to.
   * @throws IOException
   *           when the Table cannot be written to a file.
   */
  public static void writeTable(Table table, String path) throws IOException {
    if (!path.endsWith(".ser")) {
      path += ".ser";
    }

    FileOutputStream fos = new FileOutputStream(path);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(table);
    oos.close();
  }

  /**
   * Reads a Table given a file path.
   * 
   * @param path
   *          the path to read the table.
   * @return the read Table.
   * @throws IOException
   *           when no Table can be read.
   */
  public static Table readTable(String path) throws IOException, ClassNotFoundException {
    if (!path.endsWith(".ser")) {
      path += ".ser";
    }

    return readTable(new FileInputStream(path));
  }

  /**
   * Reads a Table given a file object.
   * 
   * @param file
   *          the file object to read the table.
   * @return the read Table.
   * @throws IOException
   *           when no Table can be read.
   */
  public static Table readTable(File file) throws IOException, ClassNotFoundException {
    return readTable(new FileInputStream(file));
  }

  private static Table readTable(FileInputStream fis) throws IOException, ClassNotFoundException {
    ObjectInputStream ois = new ObjectInputStream(fis);
    Table res = (Table) ois.readObject();
    ois.close();
    return res;
  }
}
