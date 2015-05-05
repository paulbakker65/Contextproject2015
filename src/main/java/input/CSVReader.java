package input;

import java.io.IOException;
import java.util.Scanner;

import table.Table;
import table.Tuple;


public class CSVReader {
	
	public static Table read (String file) throws IOException {
		Table data = new Table();
		Scanner sc = new Scanner(new CSVReader().getClass().getResourceAsStream(file));
		String[] columns = sc.nextLine().split(";");
		while(sc.hasNext()){
			data.add(new Tuple(columns, sc.nextLine().split(";")));
		}
		sc.close();
		return data;
	}
	
}
