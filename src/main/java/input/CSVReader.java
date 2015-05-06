package input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import table.Table;
import table.Record;


public class CSVReader {
	
	public static Table read (String file, Settings settings) throws IOException {
		Table data = new Table();
		Scanner sc = new Scanner(new CSVReader().getClass().getResourceAsStream(file));
		ArrayList<Column> columns = settings.getColumns();
		for(int i = 0; i < settings.getStartLine() - 1; i++) {
			sc.nextLine();
		}
		while(sc.hasNext()){
			String[] values = sc.nextLine().split(";");
			if(values.length == columns.size()){
				data.add(new Record(columns, values));
			}
		}
		sc.close();
		return data;
	}
	
}
