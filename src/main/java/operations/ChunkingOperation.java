package operations;

import input.Column;
import input.Settings;
import input.StringColumn;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import parsers.ChunkValue;
import parsers.DateValue;
import parsers.NumberValue;
import parsers.StringValue;
import parsers.Value;
import table.Record;
import table.Table;

public class ChunkingOperation extends Operation {

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
	/**
	 * The settings of the inputData. We will add an extra column for the
	 * Exporter.class
	 */
	Settings settings;
	/**
	 * The comparator needed for the chunking.
	 */
	RecordComparator rc;

	/**
	 * The enum on which we want to chunk.
	 * 
	 * @author paulbakker
	 *
	 */
	public enum ChunkComparatorEnum {
		DAY, MONTH, YEAR, PATIENT
	}

	/**
	 * Constructor that calls the Operation class to set the inputData and
	 * create an the colums for the new table.
	 * 
	 * @param input
	 */
	public ChunkingOperation(Table input) {
		super(input);
		cols.add(new StringColumn("Chunk"));
	}

	/**
	 * Setting the parameters for the operation.
	 * 
	 * @param columnName
	 * @param cce
	 * @param settings
	 * @return
	 */
	public boolean setOperationParameters(String columnName,
			ChunkComparatorEnum cce, Settings settings) {
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

	/**
	 * This method return the data in such a way that it can be used in the
	 * Exporter.class
	 * 
	 * @return
	 */
	public Table getOutput() {
		Table output = new Table();
		for (Record r : resultData) {
			ChunkValue temp = (ChunkValue) r.get("Chunk");
			for (Record r2 : temp.getTable()) {
				r2.put("Chunk", new StringValue(temp.getLabel()));
			}
			output.addAll(temp.getTable());
		}
		settings.addColumn(new StringColumn("Chunk"));
		return output;
	}

	/**
	 * We create the chunk with new index and label if chunkingOperation returns
	 * false. We add the a record to the chunk if chunkingOperation returns
	 * true.
	 */
	@Override
	public boolean execute() {
		inputData.sort(rc);
		int index = 0;
		String label = "Chunk " + Integer.toString(index);
		ChunkValue chunk = new ChunkValue(index, label, new Table());
		Value check = inputData.get(0).get(columnName);
		for (Record r : inputData) {
			if (chunkingOperation(this.cce, r.get(columnName), check)) {
				chunk.addRecord(r);
			} else {
				Value[] values = { chunk };
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

	/**
	 * Determines if a new chunk is needed.
	 * 
	 * @param cce
	 *            , the enum on we which to chunk.
	 * @param recordValue
	 *            , the current record we need to check.
	 * @param check
	 *            , the record from the current chuck by which we will compare
	 *            the recordValue
	 * @return
	 */
	public boolean chunkingOperation(ChunkComparatorEnum cce,
			Value recordValue, Value check) {

		/**
		 * Switch case for determining if a new chunk is needed. Because of the
		 * cases we can cast without throwing cast exceptions.
		 */
		switch (cce) {
		case DAY: {
			DateValue current = (DateValue) check;
			GregorianCalendar currentDate = current.getValue();
			DateValue record = (DateValue) recordValue;
			GregorianCalendar recordDate = record.getValue();
			if (recordDate.get(Calendar.DAY_OF_YEAR) == currentDate
					.get(Calendar.DAY_OF_YEAR)
					&& recordDate.get(Calendar.YEAR) == currentDate
							.get(Calendar.YEAR)) {

				return true;
			}
			return false;
		}
		case MONTH: {
			DateValue current = (DateValue) check;
			GregorianCalendar currentDate = current.getValue();
			DateValue record = (DateValue) recordValue;
			GregorianCalendar recordDate = record.getValue();
			if (recordDate.get(Calendar.MONTH) == currentDate
					.get(Calendar.MONTH)
					&& recordDate.get(Calendar.YEAR) == currentDate
							.get(Calendar.YEAR)) {

				return true;
			}
			return false;
		}
		case YEAR: {
			DateValue current = (DateValue) check;
			GregorianCalendar currentDate = current.getValue();
			DateValue record = (DateValue) recordValue;
			GregorianCalendar recordDate = record.getValue();
			if (recordDate.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR)) {
				return true;
			}
			return false;
		}
		case PATIENT: {
			NumberValue current = (NumberValue) check;
			NumberValue record = (NumberValue) recordValue;
			if (current.equals(record)) {
				return true;
			}
			return false;
		}
		default:
			return false;
		}
	}
}
