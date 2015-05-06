package table;

import java.util.Date;
import java.util.GregorianCalendar;

public class DateConversion {
	
	/**
	 * Converts Excel Serial Date to a Java Date object.
	 * 
	 * @param excelSerial Excel Style Date in SerialDate format.
	 * @return Date object in java.util.Date style
	 */
	public static Date fromExcelSerialToDate2(int excelSerial) {
		GregorianCalendar c = new GregorianCalendar();
		c.set(1900, GregorianCalendar.JANUARY, 1);
		
		c.add(GregorianCalendar.DAY_OF_YEAR, excelSerial);
		
		return c.getTime();	
	}

}
