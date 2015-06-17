package net.tudelft.hi.e.computation;

import java.util.ArrayList;
import net.tudelft.hi.e.common.enums.ChunkType;
import net.tudelft.hi.e.data.Chunk;
import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.DateColumn;
import net.tudelft.hi.e.data.DateConversion;
import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.NumberColumn;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.StringColumn;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * ChunkingOperationTest class testing operations.ChunkingOperation.
 *
 */
public class ChunkingOperationTest {

  Table dataTable;
  ChunkingOperation co;
  ArrayList<Column> cols;

  /**
   * Create empty table.
   */
  @Before
  public void setUp() {
    cols = new ArrayList<Column>();
    cols.add(new NumberColumn("userid"));
    cols.add(new NumberColumn("numberField"));
    cols.add(new StringColumn("stringField"));
    cols.add(new DateColumn("dateField"));
    // Table with test data

    dataTable = new Table();
  }

  @Test
  public void testDayChunkresult() {

    // Create table with 20 user id's.
    for (int count = 0; count < 20; count++) {
      final Record record =
          new Record(cols, new Value[] {new NumberValue(count), new NumberValue(count * 10),
              new StringValue("String:" + count),
              new DateValue(DateConversion.fromExcelSerialToDate(40000 + (count / 5)))});
      dataTable.add(record);
    }

    co = new ChunkingOperation(dataTable, "dateField", ChunkType.DAY);
    co.execute();
    final Table temp = new Table(dataTable);
    final Chunk chunk1 = new Chunk(0, "Chunk 0");
    final Chunk chunk2 = new Chunk(1, "Chunk 1");
    final Chunk chunk3 = new Chunk(2, "Chunk 2");
    final Chunk chunk4 = new Chunk(3, "Chunk 3");
    int count = 0;
    while (count < 5) {
      chunk1.add(dataTable.get(count));
      count++;
    }
    while (count < 10) {
      chunk2.add(dataTable.get(count));
      count++;
    }
    while (count < 15) {
      chunk3.add(dataTable.get(count));
      count++;
    }
    while (count < 20) {
      chunk4.add(dataTable.get(count));
      count++;
    }

    temp.addChunk(chunk1);
    temp.addChunk(chunk2);
    temp.addChunk(chunk3);
    temp.addChunk(chunk4);
    assertEquals(temp, co.getResult());
    assertEquals(temp.toString(), co.toString());
  }

  @Test
  public void testExecute_empty() {
    co = new ChunkingOperation(dataTable, null, ChunkType.TEST);
    co.execute();
    assertEquals(new Table(), co.getResult());
  }

  @Test
  public void testGetresult() {
    co = new ChunkingOperation(dataTable, "dateField", ChunkType.MONTH);
    assertEquals(new Table(), co.getResult());
  }

  @Test
  public void testInvalidCce() {
    co = new ChunkingOperation(dataTable, null, ChunkType.TEST);
    assertEquals(null, co.getCondition(ChunkType.TEST));
  }

  @Test
  public void testMonthChunkresult() {

    // Create table with 30 user id's.
    for (int count = 0; count < 30; count++) {
      final Record record =
          new Record(cols, new Value[] {new NumberValue(count), new NumberValue(count * 10),
              new StringValue("String:" + count),
              new DateValue(DateConversion.fromExcelSerialToDate(40000 + count))});
      dataTable.add(record);
    }

    co = new ChunkingOperation(dataTable, "dateField", ChunkType.MONTH);
    co.execute();
    final Table temp = (Table) dataTable.clone();

    final Chunk chunk1 = new Chunk(0, "Chunk 0");
    final Chunk chunk2 = new Chunk(1, "Chunk 1");
    int count = 0;
    while (count < 26) {
      chunk1.add(dataTable.get(count));
      count++;
    }
    while (count < dataTable.size()) {
      chunk2.add(dataTable.get(count));
      count++;
    }
    temp.addChunk(chunk1);
    temp.addChunk(chunk2);

    assertEquals(temp, co.getResult());
    assertEquals(temp.toString(), co.toString());
  }

  @Test
  public void testPatientChunkresult() {

    // Create table with 20 user id's.
    for (int count = 0; count < 15; count++) {
      final Record record =
          new Record(cols, new Value[] {new NumberValue(count / 4), new NumberValue(count * 10),
              new StringValue("String:" + count),
              new DateValue(DateConversion.fromExcelSerialToDate(40000 + (count)))});
      dataTable.add(record);
    }

    co = new ChunkingOperation(dataTable, "userid", ChunkType.PATIENT);
    co.execute();
    final Table temp = (Table) dataTable.clone();
    final Chunk chunk1 = new Chunk(0, "Chunk 0");
    final Chunk chunk2 = new Chunk(1, "Chunk 1");
    final Chunk chunk3 = new Chunk(2, "Chunk 2");
    final Chunk chunk4 = new Chunk(3, "Chunk 3");
    int count = 0;
    while (count < 4) {
      chunk1.add(dataTable.get(count));
      count++;
    }
    while (count < 8) {
      chunk2.add(dataTable.get(count));
      count++;
    }
    while (count < 12) {
      chunk3.add(dataTable.get(count));
      count++;
    }
    while (count < 15) {
      chunk4.add(dataTable.get(count));
      count++;
    }

    temp.addChunk(chunk1);
    temp.addChunk(chunk2);
    temp.addChunk(chunk3);
    temp.addChunk(chunk4);

    assertEquals(temp, co.getResult());
    assertEquals(temp.toString(), co.toString());
  }

  @Test
  public void testYearChunkresult() {

    // Create table with 10 user id's.
    for (int count = 0; count < 10; count++) {
      final Record record =
          new Record(cols, new Value[] {new NumberValue(count), new NumberValue(count * 10),
              new StringValue("String:" + count),
              new DateValue(DateConversion.fromExcelSerialToDate(40000 + count * 90))});
      dataTable.add(record);
    }

    co = new ChunkingOperation(dataTable, "dateField", ChunkType.YEAR);
    co.execute();
    final Table temp = (Table) dataTable.clone();
    final Chunk chunk1 = new Chunk(0, "Chunk 0");
    final Chunk chunk2 = new Chunk(1, "Chunk 1");
    final Chunk chunk3 = new Chunk(2, "Chunk 2");
    int count = 0;
    while (count < 2) {
      chunk1.add(dataTable.get(count));
      count++;
    }
    while (count < 7) {
      chunk2.add(dataTable.get(count));
      count++;
    }
    while (count < 10) {
      chunk3.add(dataTable.get(count));
      count++;
    }

    temp.addChunk(chunk1);
    temp.addChunk(chunk2);
    temp.addChunk(chunk3);

    assertEquals(temp, co.getResult());
    assertEquals(temp.toString(), co.toString());
  }
  
  @Test
  public void testPhaseChunkresult() {
		for (int count = 0; count < 18; count++) {
		    final Record record =
		        new Record(cols, new Value[] {new NumberValue(count), new NumberValue(count * 10),
		            new StringValue("String:" + count),
		            new DateValue(DateConversion.fromExcelSerialToDate(40000 + count * 7))});
		    dataTable.add(record);
		}
		co = new ChunkingOperation(dataTable, "dateField", ChunkType.PHASE);
	    co.execute();
	    final Table temp = (Table) dataTable.clone();
	    final Chunk chunk1 = new Chunk(0, "Chunk 0");
	    final Chunk chunk2 = new Chunk(1, "Chunk 1");
	    final Chunk chunk3 = new Chunk(2, "Chunk 2");
	    final Chunk chunk4 = new Chunk(3, "Chunk 3");
	    int count = 0;
	    while (count < 5) {
	      chunk1.add(dataTable.get(count));
	      count++;
	    }
	    while (count < 10) {
	      chunk2.add(dataTable.get(count));
	      count++;
	    }
	    while (count < 16) {
	      chunk3.add(dataTable.get(count));
	      count++;
	    }
	    while (count < 18) {
	      chunk4.add(dataTable.get(count));
	      count++;
	    }
	    temp.addChunk(chunk1);
	    temp.addChunk(chunk2);
	    temp.addChunk(chunk3);
	    temp.addChunk(chunk4);
	    
	    assertEquals(temp, co.getResult());
  }

}
