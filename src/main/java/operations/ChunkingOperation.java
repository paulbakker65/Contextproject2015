package operations;

import input.Column;
import input.Settings;
import input.StringColumn;

import java.util.ArrayList;
import java.util.Date;

import parsers.ChunkValue;
import parsers.DateValue;
import parsers.NumberValue;
import parsers.StringValue;
import parsers.Value;
import table.Record;
import table.Table;

public class ChunkingOperation extends Operation{
	
	/**
	 * the column name relevant for this operation
	 */
	String columnName;
	/**
	 * enum for the chunk type
	 */
	ChunkComparatorEnum cce;
	/**
	 * result dataset, after operation
	 */
	Table resultData;
	/**
	 * The columns created after chunking.
	 */
	ArrayList<Column> cols = new ArrayList<Column>();
	Settings settings;
	RecordComparator rc;
	
	
	public enum ChunkComparatorEnum{
		DAY, MONTH, YEAR, PATIENT	
	}
	
	public ChunkingOperation(Table input) {
		super(input);
		cols.add(new StringColumn("Chunk"));
	}
	
	public boolean setOperationParameters(String columnName, ChunkComparatorEnum cce, Settings settings){
		this.columnName = columnName;
		this.cce = cce;
		this.resultData = new Table();
		this.operationParametersSet = true;
		this.settings = settings;
		this.rc = new RecordComparator(columnName);
		
		return this.operationParametersSet;
	}
	
	@Override
	public String toString() {
		return resultData.toString();
	}

	@Override
	public Table getResult() {
		return resultData;
	}
	
	public Table getOutput() {
		Table output = new Table();
		for(Record r: resultData) {
			ChunkValue temp = (ChunkValue) r.get("Chunk");
			for(Record r2 : temp.getTable()) {
				r2.put("Chunk", new StringValue(temp.getLabel()));
			}
			output.addAll(temp.getTable());
		}
		settings.addColumn(new StringColumn("Chunk"));
		return output;
	}

	@Override
	public boolean execute() {
		inputData.sort(rc);
		int index = 0;
		String label = "Chunk " + Integer.toString(index);
		ChunkValue chunk = new ChunkValue(index, label, new Table());
		Value check = inputData.get(0).get(columnName);
		for(Record r: inputData){
			if(chunkingOperation(this.cce, r.get(columnName), check)){
				chunk.addRecord(r);
			}
			else {
				Value[] values = {chunk};
				resultData.add(new Record(cols, values));
				index++;
				label = "Chunk " + Integer.toString(index);
				chunk = new ChunkValue(index, label, new Table());
				chunk.addRecord(r);
				check = r.get(columnName);
				
			}
		}
		return false;
	}
	
	
	
	@SuppressWarnings("deprecation")
	public boolean chunkingOperation(ChunkComparatorEnum cce, Value recordValue, Value check) {
		
		
		switch(cce){
		case DAY : {
			DateValue current = (DateValue) check;
			Date currentDate = current.getValue();
			DateValue record = (DateValue) recordValue;
			Date recordDate = record.getValue();
			if((recordDate.getTime()-currentDate.getTime()) < 1000*60*60*24) {
				
				return true;
			}
			return false;
		}	
		case MONTH : {
			DateValue current = (DateValue) check;
			Date currentDate = current.getValue();
			DateValue record = (DateValue) recordValue;
			Date recordDate = record.getValue();
			if(recordDate.getMonth() == currentDate.getMonth() && recordDate.getYear() == recordDate.getYear()){
				return true;
			}
			return false;
		}
		case YEAR : {
			DateValue current = (DateValue) check;
			Date currentDate = current.getValue();
			DateValue record = (DateValue) recordValue;
			Date recordDate = record.getValue();
			if(recordDate.getYear() == currentDate.getYear()) {
				return true;
			}
			return false;
		}
		case PATIENT : {
			NumberValue current = (NumberValue) check;
			NumberValue record = (NumberValue) recordValue;
			if(current.equals(record)) {
				return true;
			}
			return false;
		}
		default: return false;	
		}
		
		

	}
	
	
	
}
