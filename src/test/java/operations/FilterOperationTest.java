package operations;

import static org.junit.Assert.*;
import input.Column;
import input.DateColumn;
import input.NumberColumn;
import input.StringColumn;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import parsers.DateValue;
import parsers.NumberValue;
import parsers.StringValue;
import parsers.Value;
import table.DateConversion;
import table.Record;
import table.Table;

public class FilterOperationTest {
	
	Table dataTable;
	FilterOperation fo;

	@Before
	public void setUp() throws Exception {
		// Table with test data
		dataTable = new Table();
		
		// Create table with 20 user id's.
		for (int i = 0; i < 20; i++) {
			ArrayList<Column> cols = new ArrayList<Column>();
			cols.add(new NumberColumn("userid"));
			cols.add(new NumberColumn("numberField"));
			cols.add(new StringColumn("stringField"));
			cols.add(new DateColumn("dateField"));
			Record r = new Record(cols, new Value[]{new NumberValue(i), new NumberValue(i * 10), new StringValue("String:"+i), new DateValue(DateConversion.fromExcelSerialToDate(40000+i))});
			dataTable.add(r);
		}
		
		fo = new FilterOperation(dataTable);
	}

	@Test
	public void testGetResult() {
		assertEquals(new Table(), fo.getResult());
	}

	@Test
	public void testExecute_empty() {
		fo.execute();
		assertEquals(new Table(), fo.getResult());
	}
	
	@Test
	public void testExecute_number_eq() {
		fo.setOperationParameters("userid", FilterOperation.ConstraintComparatorEnum.EQ, new NumberValue(9));
		assertEquals(true, fo.execute());
		
		Table resultTable = new Table();
		// Create table with 20 user id's.
		for (int i = 9; i < 10; i++) {
			ArrayList<Column> cols = new ArrayList<Column>();
			cols.add(new NumberColumn("userid"));
			cols.add(new NumberColumn("numberField"));
			cols.add(new StringColumn("stringField"));
			cols.add(new DateColumn("dateField"));
			Record r = new Record(cols, new Value[]{new NumberValue(i), new NumberValue(i * 10), new StringValue("String:"+i), new DateValue(DateConversion.fromExcelSerialToDate(40000+i))});
			resultTable.add(r);
		}
		assertEquals(resultTable, fo.getResult());
	}
	
	@Test
	public void testExecute_number_g() {
		fo.setOperationParameters("userid", FilterOperation.ConstraintComparatorEnum.G, new NumberValue(2));
		assertEquals(true, fo.execute());
		
		Table resultTable = new Table();
		// Create table with 20 user id's.
		for (int i = 3; i < 20; i++) {
			ArrayList<Column> cols = new ArrayList<Column>();
			cols.add(new NumberColumn("userid"));
			cols.add(new NumberColumn("numberField"));
			cols.add(new StringColumn("stringField"));
			cols.add(new DateColumn("dateField"));
			Record r = new Record(cols, new Value[]{new NumberValue(i), new NumberValue(i * 10), new StringValue("String:"+i), new DateValue(DateConversion.fromExcelSerialToDate(40000+i))});
			resultTable.add(r);
		}
		assertEquals(resultTable, fo.getResult());
	}
	
	@Test
	public void testExecute_number_geq() {
		fo.setOperationParameters("userid", FilterOperation.ConstraintComparatorEnum.GEQ, new NumberValue(17));
		assertEquals(true, fo.execute());
		
		Table resultTable = new Table();
		// Create table with 20 user id's.
		for (int i = 17; i < 20; i++) {
			ArrayList<Column> cols = new ArrayList<Column>();
			cols.add(new NumberColumn("userid"));
			cols.add(new NumberColumn("numberField"));
			cols.add(new StringColumn("stringField"));
			cols.add(new DateColumn("dateField"));
			Record r = new Record(cols, new Value[]{new NumberValue(i), new NumberValue(i * 10), new StringValue("String:"+i), new DateValue(DateConversion.fromExcelSerialToDate(40000+i))});
			resultTable.add(r);
		}
		assertEquals(resultTable, fo.getResult());
	}
	
	@Test
	public void testExecute_number_l() {
		fo.setOperationParameters("userid", FilterOperation.ConstraintComparatorEnum.L, new NumberValue(5));
		assertEquals(true, fo.execute());
		
		Table resultTable = new Table();
		// Create table with 20 user id's.
		for (int i = 0; i < 5; i++) {
			ArrayList<Column> cols = new ArrayList<Column>();
			cols.add(new NumberColumn("userid"));
			cols.add(new NumberColumn("numberField"));
			cols.add(new StringColumn("stringField"));
			cols.add(new DateColumn("dateField"));
			Record r = new Record(cols, new Value[]{new NumberValue(i), new NumberValue(i * 10), new StringValue("String:"+i), new DateValue(DateConversion.fromExcelSerialToDate(40000+i))});
			resultTable.add(r);
		}
		assertEquals(resultTable, fo.getResult());
	}
	
	@Test
	public void testExecute_number_leq() {
		fo.setOperationParameters("userid", FilterOperation.ConstraintComparatorEnum.LEQ, new NumberValue(14));
		assertEquals(true, fo.execute());
		
		Table resultTable = new Table();
		// Create table with 20 user id's.
		for (int i = 0; i < 15; i++) {
			ArrayList<Column> cols = new ArrayList<Column>();
			cols.add(new NumberColumn("userid"));
			cols.add(new NumberColumn("numberField"));
			cols.add(new StringColumn("stringField"));
			cols.add(new DateColumn("dateField"));
			Record r = new Record(cols, new Value[]{new NumberValue(i), new NumberValue(i * 10), new StringValue("String:"+i), new DateValue(DateConversion.fromExcelSerialToDate(40000+i))});
			resultTable.add(r);
		}
		assertEquals(resultTable, fo.getResult());
	}
	
	@Test
	public void testExecute_date_eq() {
		fo.setOperationParameters("dateField", FilterOperation.ConstraintComparatorEnum.EQ, new DateValue(DateConversion.fromExcelSerialToDate(40010)));
		assertEquals(true, fo.execute());
		
		Table resultTable = new Table();
		// Create expected result table
		for (int i = 10; i < 11; i++) {
			ArrayList<Column> cols = new ArrayList<Column>();
			cols.add(new NumberColumn("userid"));
			cols.add(new NumberColumn("numberField"));
			cols.add(new StringColumn("stringField"));
			cols.add(new DateColumn("dateField"));
			Record r = new Record(cols, new Value[]{new NumberValue(i), new NumberValue(i * 10), new StringValue("String:"+i), new DateValue(DateConversion.fromExcelSerialToDate(40000+i))});
			resultTable.add(r);
		}
		assertEquals(resultTable, fo.getResult());
	}
	
	@Test
	public void testExecute_date_g() {
		fo.setOperationParameters("dateField", FilterOperation.ConstraintComparatorEnum.G, new DateValue(DateConversion.fromExcelSerialToDate(40003)));
		assertEquals(true, fo.execute());
		
		Table resultTable = new Table();
		// Create expected result table
		for (int i = 4; i < 20; i++) {
			ArrayList<Column> cols = new ArrayList<Column>();
			cols.add(new NumberColumn("userid"));
			cols.add(new NumberColumn("numberField"));
			cols.add(new StringColumn("stringField"));
			cols.add(new DateColumn("dateField"));
			Record r = new Record(cols, new Value[]{new NumberValue(i), new NumberValue(i * 10), new StringValue("String:"+i), new DateValue(DateConversion.fromExcelSerialToDate(40000+i))});
			resultTable.add(r);
		}
		assertEquals(resultTable, fo.getResult());
	}
	
	@Test
	public void testExecute_date_geq() {
		fo.setOperationParameters("dateField", FilterOperation.ConstraintComparatorEnum.GEQ, new DateValue(DateConversion.fromExcelSerialToDate(40005)));
		assertEquals(true, fo.execute());
		
		Table resultTable = new Table();
		// Create expected result table
		for (int i = 5; i < 20; i++) {
			ArrayList<Column> cols = new ArrayList<Column>();
			cols.add(new NumberColumn("userid"));
			cols.add(new NumberColumn("numberField"));
			cols.add(new StringColumn("stringField"));
			cols.add(new DateColumn("dateField"));
			Record r = new Record(cols, new Value[]{new NumberValue(i), new NumberValue(i * 10), new StringValue("String:"+i), new DateValue(DateConversion.fromExcelSerialToDate(40000+i))});
			resultTable.add(r);
		}
		assertEquals(resultTable, fo.getResult());
	}
	
	@Test
	public void testExecute_date_l() {
		fo.setOperationParameters("dateField", FilterOperation.ConstraintComparatorEnum.L, new DateValue(DateConversion.fromExcelSerialToDate(40013)));
		assertEquals(true, fo.execute());
		
		Table resultTable = new Table();
		// Create expected result table
		for (int i = 0; i < 13; i++) {
			ArrayList<Column> cols = new ArrayList<Column>();
			cols.add(new NumberColumn("userid"));
			cols.add(new NumberColumn("numberField"));
			cols.add(new StringColumn("stringField"));
			cols.add(new DateColumn("dateField"));
			Record r = new Record(cols, new Value[]{new NumberValue(i), new NumberValue(i * 10), new StringValue("String:"+i), new DateValue(DateConversion.fromExcelSerialToDate(40000+i))});
			resultTable.add(r);
		}
		assertEquals(resultTable, fo.getResult());
	}
	
	@Test
	public void testExecute_date_leq() {
		fo.setOperationParameters("dateField", FilterOperation.ConstraintComparatorEnum.LEQ, new DateValue(DateConversion.fromExcelSerialToDate(40013)));
		assertEquals(true, fo.execute());
		
		Table resultTable = new Table();
		// Create expected result table
		for (int i = 0; i < 14; i++) {
			ArrayList<Column> cols = new ArrayList<Column>();
			cols.add(new NumberColumn("userid"));
			cols.add(new NumberColumn("numberField"));
			cols.add(new StringColumn("stringField"));
			cols.add(new DateColumn("dateField"));
			Record r = new Record(cols, new Value[]{new NumberValue(i), new NumberValue(i * 10), new StringValue("String:"+i), new DateValue(DateConversion.fromExcelSerialToDate(40000+i))});
			resultTable.add(r);
		}
		assertEquals(resultTable, fo.getResult());
	}

		
	@Test
	public void testExecute_string_found() {
		fo.setOperationParameters("stringField", FilterOperation.ConstraintComparatorEnum.EQ, new StringValue("String:"+7));
		assertEquals(true, fo.execute());
		
		Table resultTable = new Table();
		
		ArrayList<Column> cols = new ArrayList<Column>();
		cols.add(new NumberColumn("userid"));
		cols.add(new NumberColumn("numberField"));
		cols.add(new StringColumn("stringField"));
		cols.add(new DateColumn("dateField"));
		Record r = new Record(cols, new Value[]{new NumberValue(7), new NumberValue(7 * 10), new StringValue("String:7"), new DateValue(DateConversion.fromExcelSerialToDate(40000+7))});
		resultTable.add(r);
			
		assertEquals(resultTable, fo.getResult());
	}
	
	@Test
	public void testExecute_string_notfound() {
		fo.setOperationParameters("stringField", FilterOperation.ConstraintComparatorEnum.EQ, new StringValue("String:"+25));
		assertEquals(true, fo.execute());
		
		Table resultTable = new Table();
			
		assertEquals(resultTable, fo.getResult());
	}
}
