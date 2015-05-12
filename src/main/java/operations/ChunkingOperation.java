package operations;

import input.Column;
import input.Settings;
import input.StringColumn;

import java.util.ArrayList;
import java.util.Date;

import parsers.ChunkValue;
import parsers.DateValue;
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
	
	
	public enum ChunkComparatorEnum{
		DAY, MONTH, YEAR	
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
		DateValue current = (DateValue) check;
		Date currentDate = current.getValue();
		DateValue record = (DateValue) recordValue;
		Date recordDate = record.getValue();
		
		switch(cce){
		case DAY :
			if((recordDate.getTime()-currentDate.getTime()) < 1000*60*60*24) {
				return true;
			}
			return false;
		case MONTH :
			if(recordDate.getMonth() == currentDate.getMonth() && recordDate.getYear() == recordDate.getYear()){
				return true;
			}
			return false;
		
		case YEAR : 
			if(recordDate.getYear() == currentDate.getYear()) {
				return true;
			}
			return false;
		default: return false;	
		}
		
		

	}
	
	
	
}
