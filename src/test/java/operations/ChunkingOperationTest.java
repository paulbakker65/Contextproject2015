package operations;

import static org.junit.Assert.assertEquals;
import input.Column;
import input.DateColumn;
import input.NumberColumn;
import input.Settings;
import input.StringColumn;

import java.util.ArrayList;

import operations.ChunkingOperation.ChunkComparatorEnum;

import org.junit.Before;
import org.junit.Test;

import parsers.DateValue;
import parsers.NumberValue;
import parsers.StringValue;
import parsers.Value;
import table.Chunk;
import table.DateConversion;
import table.Record;
import table.Table;

/**
 * ChunkingOperationTest class testing operations.ChunkingOperation.
 * 
 */
public class ChunkingOperationTest {

  Table dataTable;
  ChunkingOperation co;
  Settings settings;
  ArrayList<Column> cols;

  @Before
  public void setUp() {
    cols = new ArrayList<Column>();
    cols.add(new NumberColumn("userid"));
    cols.add(new NumberColumn("numberField"));
    cols.add(new StringColumn("stringField"));
    cols.add(new DateColumn("dateField"));
    // Table with test data

    dataTable = new Table();

    settings = new Settings();
    settings.addColumn(new NumberColumn("userid"));
    settings.addColumn(new NumberColumn("numberField"));
    settings.addColumn(new StringColumn("stringField"));
    settings.addColumn(new DateColumn("dateField"));
  }

  @Test
  public void testGetResult() {
    co = new ChunkingOperation(dataTable);
    assertEquals(new Table(), co.getResult());
  }

  @Test
  public void testExecute_empty() {
    co = new ChunkingOperation(dataTable);
    co.execute();
    assertEquals(new Table(), co.getResult());
  }

  @Test
  public void testMonthChunkResult() {

    // Create table with 30 user id's.
    for (int i = 0; i < 30; i++) {
      Record r = new Record(cols, new Value[] { new NumberValue(i), new NumberValue(i * 10),
          new StringValue("String:" + i),
          new DateValue(DateConversion.fromExcelSerialToDate(40000 + i)) });
      dataTable.add(r);
    }
    
    co = new ChunkingOperation(dataTable);
    co.setOperationParameters("dateField", ChunkComparatorEnum.MONTH, settings);
    co.execute();
    Table temp = (Table) dataTable.clone();
    
    Chunk chunk1 = new Chunk(0, "Chunk 0");
    Chunk chunk2 = new Chunk(1, "Chunk 1");
    int i = 0;
    while (i < 26) {
      chunk1.add(dataTable.get(i));
      i++;
    }
    while (i < dataTable.size()) {
      chunk2.add(dataTable.get(i));
      i++;
    }
    temp.addChunk(chunk1);
    temp.addChunk(chunk2);

    assertEquals(temp, co.getResult());
    assertEquals(temp.toString(), co.toString());
  }

  @Test
  public void testDayChunkResult() {

    // Create table with 20 user id's.
    for (int i = 0; i < 20; i++) {
      Record r = new Record(cols, new Value[] { new NumberValue(i), new NumberValue(i * 10),
          new StringValue("String:" + i),
          new DateValue(DateConversion.fromExcelSerialToDate(40000 + (i / 5))) });
      dataTable.add(r);
    }

    co = new ChunkingOperation(dataTable);
    co.setOperationParameters("dateField", ChunkComparatorEnum.DAY, settings);
    co.execute();
    Table temp = (Table) dataTable.clone();
    Chunk chunk1 = new Chunk(0, "Chunk 0");
    Chunk chunk2 = new Chunk(1, "Chunk 1");
    Chunk chunk3 = new Chunk(2, "Chunk 2");
    Chunk chunk4 = new Chunk(3, "Chunk 3");
    int i = 0;
    while (i < 5) {
      chunk1.add(dataTable.get(i));
      i++;
    }
    while (i < 10) {
      chunk2.add(dataTable.get(i));
      i++;
    }
    while (i < 15) {
      chunk3.add(dataTable.get(i));
      i++;
    }
    while (i < 20) {
      chunk4.add(dataTable.get(i));
      i++;
    }
   
    temp.addChunk(chunk1);
    temp.addChunk(chunk2);
    temp.addChunk(chunk3);
    temp.addChunk(chunk4);
    assertEquals(temp, co.getResult());
    assertEquals(temp.toString(), co.toString());
  }

  @Test
  public void testYearChunkResult() {

    // Create table with 10 user id's.
    for (int i = 0; i < 10; i++) {
      Record r = new Record(cols, new Value[] { new NumberValue(i), new NumberValue(i * 10),
          new StringValue("String:" + i),
          new DateValue(DateConversion.fromExcelSerialToDate(40000 + i * 90)) });
      dataTable.add(r);
    }

    co = new ChunkingOperation(dataTable);
    co.setOperationParameters("dateField", ChunkComparatorEnum.YEAR, settings);
    co.execute();
    Table temp = (Table) dataTable.clone();
    Chunk chunk1 = new Chunk(0, "Chunk 0");
    Chunk chunk2 = new Chunk(1, "Chunk 1");
    Chunk chunk3 = new Chunk(2, "Chunk 2");
    int i = 0;
    while (i < 2) {
      chunk1.add(dataTable.get(i));
      i++;
    }
    while (i < 7) {
      chunk2.add(dataTable.get(i));
      i++;
    }
    while (i < 10) {
      chunk3.add(dataTable.get(i));
      i++;
    }
    
    temp.addChunk(chunk1);
    temp.addChunk(chunk2);
    temp.addChunk(chunk3);
    
    assertEquals(temp, co.getResult());
    assertEquals(temp.toString(), co.toString());
  }

  @Test
  public void testPatientChunkResult() {

    // Create table with 20 user id's.
    for (int i = 0; i < 15; i++) {
      Record r = new Record(cols, new Value[] { new NumberValue(i / 4), new NumberValue(i * 10),
          new StringValue("String:" + i),
          new DateValue(DateConversion.fromExcelSerialToDate(40000 + (i))) });
      dataTable.add(r);
    }
    
    co = new ChunkingOperation(dataTable);
    co.setOperationParameters("userid", ChunkComparatorEnum.PATIENT, settings);
    co.execute();
    Table temp = (Table) dataTable.clone();
    Chunk chunk1 = new Chunk(0, "Chunk 0");
    Chunk chunk2 = new Chunk(1, "Chunk 1");
    Chunk chunk3 = new Chunk(2, "Chunk 2");
    Chunk chunk4 = new Chunk(3, "Chunk 3");
    int i = 0;
    while (i < 4) {
      chunk1.add(dataTable.get(i));
      i++;
    }
    while (i < 8) {
      chunk2.add(dataTable.get(i));
      i++;
    }
    while (i < 12) {
      chunk3.add(dataTable.get(i));
      i++;
    }
    while (i < 15) {
      chunk4.add(dataTable.get(i));
      i++;
    }
  
    temp.addChunk(chunk1);
    temp.addChunk(chunk2);
    temp.addChunk(chunk3);
    temp.addChunk(chunk4);
    
    assertEquals(temp, co.getResult());
    assertEquals(temp.toString(), co.toString());
  }

  @Test
  public void testInvalidCCE() {
    co = new ChunkingOperation(dataTable);
    assertEquals(null, co.getCondition(ChunkComparatorEnum.TEST));
  }

}
