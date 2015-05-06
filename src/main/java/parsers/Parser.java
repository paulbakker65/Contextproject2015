package parsers;

import input.Column;
import input.Reader;
import input.Settings;

import java.io.IOException;
import java.util.ArrayList;

import table.Record;
import table.Table;

public class Parser {
	protected Settings settings;
	protected ArrayList<Column> columns;
	protected int numcolumns;
	
	public Parser(Settings settings) {
		super();
		this.settings = settings;
		this.columns = settings.getColumns();
		numcolumns = columns.size();
	}

	public Table Parse(Reader r) throws IOException {
		for(int i = 0; i < settings.getStartLine() - 1; i++) {
			r.readRow();
		}
		
		Table t = new Table();
		
		String row[] = r.readRow();

		while(row != null && row.length == numcolumns){
			
			//VERIFY DATA HERE && Convert string to other data types
			
			Record tuple = new Record(columns, row);
			t.add(tuple);
			
			row = r.readRow();
		}
		return t;	
	}
}
