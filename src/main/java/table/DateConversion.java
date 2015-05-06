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
	public static Date fromExcelSerialToDate(double excelSerial) {
		int nDays = (int)Math.floor(excelSerial);
		double partialDays = excelSerial - nDays;
		int nMillis = (int)(partialDays * 24 * 60 * 60 * 1000);
		
		GregorianCalendar c = new GregorianCalendar();
		c.set(1900, GregorianCalendar.JANUARY, 1, 0, 0, 0);
		
		c.add(GregorianCalendar.DAY_OF_YEAR, nDays);
		c.add(GregorianCalendar.MILLISECOND, nMillis);
		
		return c.getTime();	
	}

}
