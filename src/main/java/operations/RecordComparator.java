package operations;
import java.util.Comparator;

import parsers.DateValue;
import parsers.Value;
import table.Record;

/**
 * Compares records based on a time collumn.
 * @author unset
 *
 * @param <Record>
 */
public class RecordComparator implements Comparator<Record> {

	private String col;
	
	/**
	 * Makes a new timecomparator.
	 * @param col the time-collumn to compare on
	 */
	public RecordComparator(String col) {
		this.col = col;
	}

	
	/**
	 * Compares time. Could throw Cast exception!
	 */
	@Override
	public int compare(Record o1, Record o2) {
		Value v1 =  o1.get(col);
		Value v2 =  o2.get(col);
		return v1.compareTo(v2);
		
	}
	

}
