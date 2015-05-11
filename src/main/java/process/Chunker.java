package process;

import input.Column;
import input.NumberColumn;
import input.StringColumn;

import java.util.ArrayList;

import parsers.ChunkValue;
import parsers.DateValue;
import parsers.NumberValue;
import parsers.StringValue;
import parsers.Value;
import table.Record;
import table.Table;

public class Chunker {

	private Table table;
	
	public Chunker(Table t) {
		table = t;
	}
	
	@SuppressWarnings("deprecation")
	public Table chunkOnMonth(String col) {
		//table.sort(null);
		Table result = new Table();
		ArrayList<Column> cols = new ArrayList<Column>();
		cols.add(new NumberColumn("index"));
		cols.add(new StringColumn("label"));
		cols.add(new StringColumn("chunk"));
		
		DateValue max = (DateValue) table.get(0).get(col);
		int index = 0;
		String label = "chunk" + max.getValue().toString();
		ChunkValue chunk = new ChunkValue(index, label, new Table());
		for(int i = 0; i < table.size(); i++) {
			DateValue temp = (DateValue)  table.get(i).get(col);
			if(temp.getValue().getMonth() == max.getValue().getMonth() && temp.getValue().getYear() == max.getValue().getYear()) {
				chunk.addRecord(table.get(i));
			}
			else {
				
				Value[] values = {new NumberValue(index), new StringValue(label), chunk};
				result.add(new Record(cols, values));
				index++;
				label = "chunk" + temp.getValue().toString();
				chunk = new ChunkValue(index, label, new Table());
				chunk.addRecord(table.get(i));
				max = temp;
			}
			
		}
		return result;
	}
	@Override
	public String toString() {
		return table.toString();
	}
	
	
}
