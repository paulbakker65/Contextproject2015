package operations;

import static org.junit.Assert.assertEquals;
import input.ChunkColumn;
import input.Column;
import input.DateColumn;
import input.NumberColumn;
import input.Settings;
import input.StringColumn;

import java.util.ArrayList;

import operations.ChunkingOperation.ChunkComparatorEnum;

import org.junit.Before;
import org.junit.Test;

import parsers.ChunkValue;
import parsers.DateValue;
import parsers.NumberValue;
import parsers.StringValue;
import parsers.Value;
import table.DateConversion;
import table.Record;
import table.Table;

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
		co = new ChunkingOperation(dataTable);
	}
	
	@Test
	public void testGetResult() {
		assertEquals(new Table(), co.getResult());
	}
	
	@Test
	public void testExecute_empty() {
		co.execute();
		assertEquals(new Table(), co.getResult());
	}
	
	@Test
	public void testMothChunkResult() {
		
		// Create table with 30 user id's.
		for (int i = 0; i < 30; i++) {
			Record r = new Record(cols, new Value[]{new NumberValue(i), new NumberValue(i * 10), new StringValue("String:"+i), new DateValue(DateConversion.fromExcelSerialToDate(40000+i))});
			dataTable.add(r);
		}
		
		co.setOperationParameters("dateField", ChunkComparatorEnum.MONTH, settings);
		co.execute();
		Table temp = new Table();
		Table chunk1 = new Table();
		Table chunk2 = new Table();
		int i = 0;
		while(i < 26){
			chunk1.add(dataTable.get(i));
			i++;
		}
		while(i < dataTable.size()) {
			chunk2.add(dataTable.get(i));
			i++;
		}
		ArrayList<Column> col = new ArrayList<Column>();
		col.add(new ChunkColumn("Chunk"));
		Value[] values1 = {new ChunkValue(0, "Chunk 0", chunk1)};
		Value[] values2 = {new ChunkValue(1, "Chunk 1", chunk2)};
		temp.add(new Record(col, values1));
		temp.add(new Record(col, values2));		
		assertEquals(temp, co.getResult());	
		assertEquals(temp.toString(), co.toString());
	}
	
	@Test
	public void testDayChunkResult() {
		
		// Create table with 20 user id's.
		for (int i = 0; i < 20; i++) {
			Record r = new Record(cols, new Value[]{new NumberValue(i), new NumberValue(i * 10), new StringValue("String:"+i), new DateValue(DateConversion.fromExcelSerialToDate(40000+(i/5)))});
			dataTable.add(r);
		}
		
		co.setOperationParameters("dateField", ChunkComparatorEnum.DAY, settings);
		co.execute();
		Table temp = new Table();
		Table chunk1 = new Table();
		Table chunk2 = new Table();
		Table chunk3 = new Table();
		Table chunk4 = new Table();
		int i = 0;
		while(i < 5){
			chunk1.add(dataTable.get(i));
			i++;
		}
		while(i < 10) {
			chunk2.add(dataTable.get(i));
			i++;
		}
		while(i < 15) {
			chunk3.add(dataTable.get(i));
			i++;
		}
		while(i < 20) {
			chunk4.add(dataTable.get(i));
			i++;
		}
		ArrayList<Column> col = new ArrayList<Column>();
		col.add(new ChunkColumn("Chunk"));
		Value[] values1 = {new ChunkValue(0, "Chunk 0", chunk1)};
		Value[] values2 = {new ChunkValue(1, "Chunk 1", chunk2)};
		Value[] values3 = {new ChunkValue(1, "Chunk 2", chunk3)};
		Value[] values4 = {new ChunkValue(1, "Chunk 3", chunk4)};
		temp.add(new Record(col, values1));
		temp.add(new Record(col, values2));	
		temp.add(new Record(col, values3));	
		temp.add(new Record(col, values4));	
		assertEquals(temp, co.getResult());	
		assertEquals(temp.toString(), co.toString());
	}
	
	@Test
	public void testYearChunkResult() {
		
		// Create table with 10 user id's.
		for (int i = 0; i < 10; i++) {
			Record r = new Record(cols, new Value[]{new NumberValue(i), new NumberValue(i * 10), new StringValue("String:"+i), new DateValue(DateConversion.fromExcelSerialToDate(40000+i*90))});
			dataTable.add(r);
		}
				
		co.setOperationParameters("dateField", ChunkComparatorEnum.YEAR, settings);
		co.execute();
		Table temp = new Table();
		Table chunk1 = new Table();
		Table chunk2 = new Table();
		Table chunk3 = new Table();
		int i = 0;
		while(i < 2) {
			chunk1.add(dataTable.get(i));
			i++;
		}
		while(i < 7) {
			chunk2.add(dataTable.get(i));
			i++;
		}
		while( i < 10) {
			chunk3.add(dataTable.get(i));
			i++;
		}
		ArrayList<Column> col = new ArrayList<Column>();
		col.add(new ChunkColumn("Chunk"));
		Value[] values1 = {new ChunkValue(0, "Chunk 0", chunk1)};
		Value[] values2 = {new ChunkValue(1, "Chunk 1", chunk2)};
		Value[] values3 = {new ChunkValue(1, "Chunk 2", chunk3)};
		temp.add(new Record(col, values1));
		temp.add(new Record(col, values2));
		temp.add(new Record(col, values3));	
		assertEquals(temp, co.getResult());
		assertEquals(temp.toString(), co.toString());
	}
	
	@Test
	public void testYearChunkOutput() {
		
		// Create table with 10 user id's.
		for (int i = 0; i < 10; i++) {
			Record r = new Record(cols, new Value[]{new NumberValue(i), new NumberValue(i * 10), new StringValue("String:"+i), new DateValue(DateConversion.fromExcelSerialToDate(40000+i*90))});
			dataTable.add(r);
		}
		
		co.setOperationParameters("dateField", ChunkComparatorEnum.YEAR, settings);
		co.execute();
		Table temp = new Table();
		temp.addAll(dataTable);
		int i = 0;
		while(i < 2) {
			temp.get(i).put("Chunk", new StringValue("Chunk 0"));
			i++;
		}
		while(i < 7) {
			temp.get(i).put("Chunk", new StringValue("Chunk 1"));
			i++;
		}
		while( i < 10) {
			temp.get(i).put("Chunk", new StringValue("Chunk 2"));
			i++;
		}
		assertEquals(temp, co.getOutput());
	}
	
	@Test
	public void testPatientChunkResult() {
		
		// Create table with 20 user id's.
		for (int i = 0; i < 15; i++) {
			Record r = new Record(cols, new Value[]{new NumberValue(i/4), new NumberValue(i * 10), new StringValue("String:"+i), new DateValue(DateConversion.fromExcelSerialToDate(40000+(i)))});
			dataTable.add(r);
		}
		
		System.out.println(dataTable);
		
		co.setOperationParameters("userid", ChunkComparatorEnum.PATIENT, settings);
		co.execute();
		Table temp = new Table();
		Table chunk1 = new Table();
		Table chunk2 = new Table();
		Table chunk3 = new Table();
		Table chunk4 = new Table();
		int i = 0;
		while(i < 4){
			chunk1.add(dataTable.get(i));
			i++;
		}
		while(i < 8) {
			chunk2.add(dataTable.get(i));
			i++;
		}
		while(i < 12) {
			chunk3.add(dataTable.get(i));
			i++;
		}
		while(i < 15) {
			chunk4.add(dataTable.get(i));
			i++;
		}
		ArrayList<Column> col = new ArrayList<Column>();
		col.add(new ChunkColumn("Chunk"));
		Value[] values1 = {new ChunkValue(0, "Chunk 0", chunk1)};
		Value[] values2 = {new ChunkValue(1, "Chunk 1", chunk2)};
		Value[] values3 = {new ChunkValue(1, "Chunk 2", chunk3)};
		Value[] values4 = {new ChunkValue(1, "Chunk 3", chunk4)};
		temp.add(new Record(col, values1));
		temp.add(new Record(col, values2));	
		temp.add(new Record(col, values3));	
		temp.add(new Record(col, values4));	
		assertEquals(temp, co.getResult());	
		assertEquals(temp.toString(), co.toString());
	}
	
	@Test
	public void testInvalidCCE(){
		assertEquals(null, co.getCondition(ChunkComparatorEnum.TEST));
	}
	
}
