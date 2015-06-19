package net.tudelft.hi.e.data;

import net.tudelft.hi.e.common.exceptions.TableNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for reading and writing table files.
 */
public class TableFile {
  private static final Logger LOG = Logger.getLogger(TableFile.class.getName());

  /**
   * Writes a table to a file.
   *
   * @param table the table to write.
   * @param path the path to write to.
   * @throws IOException when the Table cannot be written to a file.
   */
  public static void writeTable(Table table, String path) throws TableNotFoundException {
    if (!path.endsWith(".ser")) {
      path += ".ser";
    }

    FileOutputStream fos;
    try {
      fos = new FileOutputStream(path);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(table);
      oos.close();
    } catch (IOException e) {
      LOG.log(Level.SEVERE, e.getMessage(), e);
    }

  }

  /**
   * Reads a Table given a file path.
   *
   * @param path the path to read the table.
   * @return the read Table.
   * @throws IOException when no Table can be read.
   */
  public static Table readTable(String path) throws TableNotFoundException {
    if (!path.endsWith(".ser")) {
      path += ".ser";
    }

    try {
      return readTable(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      LOG.log(Level.SEVERE, "Table file not found", e);
      return null;
    }
  }

  /**
   * Reads a Table given a file object.
   *
   * @param file the file object to read the table.
   * @return the read Table.
   * @throws TableNotFoundException table not found.
   * @throws IOException when no Table can be read.
   */
  public static Table readTable(File file) throws TableNotFoundException {
    try {
      return readTable(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      LOG.log(Level.SEVERE, "Table file not found", e);
      return null;
    }
  }

  private static Table readTable(FileInputStream fis) throws TableNotFoundException {
    try {
      ObjectInputStream ois = new ObjectInputStream(fis);
      Table res = new Table();
      res = (Table) ois.readObject();
      ois.close();
      return res;
    } catch (ClassNotFoundException e) {
      LOG.log(Level.SEVERE, e.getMessage(), e);
      return null;
    } catch (IOException e) {
      LOG.log(Level.SEVERE, "Table file not found", e);
      return null;
    }
  }
}
