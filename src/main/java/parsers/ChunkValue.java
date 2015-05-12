package parsers;

import table.Record;
import table.Table;


public class ChunkValue extends Value {
	
	private int index;
	private String label;
	private Table table;
	
	public ChunkValue(int i, String l, Table t) {
		index = i;
		label = l;
		table = t;
	}
	
	public void addRecord(Record r) {
		table.add(r);
	}
	
	public int getIndex() {
		return index;
	}
	
	public String getLabel() {
		return label;
	}
	
	public Table getTable() {
		return table;
	}
	
	@Override
	public String toString() {
		return table.toString();
	}

	@Override
	public boolean isNumeric() {
		return false;
	}

	@Override
	public boolean isDate() {
		return false;
	}

	@Override
	public boolean isString() {
		return false;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public boolean isTime() {
		return false;
	}
}

