package parsers;

import input.Column;
import input.Reader;
import input.Settings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import table.Record;
import table.Table;

/**
 * Class for parsing a file into a Table object.
 * @author Robin
 *
 */
public class Parser {
	protected Settings settings;
	protected ArrayList<Column> columns;
	protected int numColumns;
	
	/**
	 * Constructs a new parser given the settings to describe the file.
	 * @param settings the settings that describe the file.
	 */
	public Parser(Settings settings) {
		super();
		this.settings = settings;
		this.columns = settings.getColumns();
		this.numColumns = columns.size();
	}

	/**
	 * Parses the file given the Reader that reads the file.
	 * @param reader the reader that reads the file.
	 * @return a Table object which represents the read file as a table.
	 * @throws IOException when something fail during reading.
	 * @throws ColumnTypeMismatchException when the read file contains other values
	 * than specified by the settings.
	 */
	public Table parse(Reader reader) throws IOException, ColumnTypeMismatchException {
		// Skip lines until the start line is reached.
		for (int i = 0; i < settings.getStartLine() - 1; i++) {
			reader.readRow();
		}
		
		Table t = new Table();
		
		String[] row = reader.readRow();

		// Read a row, convert the values and store them in the Table.
		while (row != null && row.length == numColumns) {			
			Value[] values = new Value[numColumns];
			Map<String, String> timeDateLinks = new HashMap<String, String>();
			
			for (int i = 0; i < columns.size(); i++) {
				values[i] = columns.get(i).convertToValue(row[i]);
				
				if (values[i].isTime())
					timeDateLinks.put(columns.get(i).getName(), ((TimeValue) values[i]).getTargetDate());
			}
						
			Record tuple = new Record(columns, values);
			connectLinks(timeDateLinks, tuple);
			t.add(tuple);
			
			row = reader.readRow();
		}
		
		return t;	
	}
	
	private void connectLinks(Map<String, String> links, Record record) {
		for (String time : links.keySet()) {
			TimeValue timeValue = (TimeValue) record.get(time);
			DateValue dateValue = (DateValue) record.get(links.get(time));
			
			dateValue.addTime(timeValue.getValue());
			timeValue.setValue(dateValue.getValue());
		}
	}
	
	/**
	 * Returns the parser's Settings.
	 * @return the parser's Settings.
	 */
	public Settings getSettings() {
		return settings;
	}
	
	/**
	 * Returns the parser's columns.
	 * @return the parser's columns.
	 */
	public ArrayList<Column> getColumns() {
		return columns;
	}
	
	/**
	 * Returns the parser's number of columns.
	 * @return the parser's number of columns.
	 */
	public int getNumberOfColumns() {
		return numColumns;
	}
}
