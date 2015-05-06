package input;

import java.io.IOException;
import java.util.Scanner;

import table.Table;
import table.Tuple;

public class TXTReader {

	public static Table read(String file) throws IOException {
		Table data = new Table();
		Scanner sc = new Scanner(new CSVReader().getClass().getResourceAsStream(file));
		String[] columns = {"measurement", "value", "measurementunit","useless", "date", "time"};
		sc.nextLine();
		sc.nextLine();
		sc.nextLine();
		sc.nextLine();
		sc.nextLine();
		sc.nextLine();
		while(sc.hasNextLine()){
			String[] values = sc.nextLine().split(",");
			if(!values[0].equals("Crea")) {
				sc.close();
				return data;
			}
			else{
				data.add(new Tuple(columns, sc.nextLine().split(",")));
			}
		}
		sc.close();
		return data;
	}
	
	
}
