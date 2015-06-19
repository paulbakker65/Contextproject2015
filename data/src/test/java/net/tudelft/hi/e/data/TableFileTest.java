package net.tudelft.hi.e.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.tudelft.hi.e.common.exceptions.TableNotFoundException;

import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing reading and writing Tables.
 */
public class TableFileTest {
  private Table table;

  /**
   * Initialize a Table.
   */
  @Before
  public void setUp() {
    table = new Table();
    Record record = new Record();
    record.put("fruit", new StringValue("banaan"));
    record.put("groente", new StringValue("prei"));
    table.add(record);
  }

  @Test
  public void testWriteReadTablePath() throws TableNotFoundException {
    TableFile.writeTable(table, "src/test/resources/testTable");
    Table returnTable = TableFile.readTable("src/test/resources/testTable");

    assertEquals(table, returnTable);
  }

  @Test
  public void testWriteReadTableFile() throws TableNotFoundException {
    TableFile.writeTable(table, "src/test/resources/testTable");
    Table returnTable = TableFile.readTable(new File("src/test/resources/testTable.ser"));
    assertEquals(table, returnTable);
  }

  @Test
  public void testWriteReadTablePathSer() throws TableNotFoundException {
    TableFile.writeTable(table, "src/test/resources/testTable.ser");
    Table returnTable = TableFile.readTable("src/test/resources/testTable.ser");

    assertEquals(table, returnTable);
  }

  @Test
  public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException,
      InvocationTargetException, InstantiationException {
    Constructor<TableFile> constructor = TableFile.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
    constructor.newInstance();
  }

  @Test(expected = TableNotFoundException.class)
  public void testWriteFailure() throws TableNotFoundException {
    TableFile.writeTable(new Table(), "/thisisnotapath");
  }

  @Test(expected = TableNotFoundException.class)
  public void testReadFailureString() throws TableNotFoundException {
    TableFile.readTable("/thisisnotapath");
  }

  @Test(expected = TableNotFoundException.class)
  public void testReadFailureFile() throws TableNotFoundException {
    TableFile.readTable(new File("/thisisnotapath"));
  }
}
