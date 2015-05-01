package input;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class CSVReader {
	
	public static Table read (File file) throws IOException {
		Table data = new Table();
		System.out.println("test");
		Scanner sc = new Scanner(new CSVReader().getClass().getResourceAsStream("/csvexample.csv"));
		String[] columns = sc.nextLine().split(";");
		while(sc.hasNext()){
			data.add(new Tuple(columns, sc.nextLine().split(";")));
		}
		sc.close();
		return data;
	}
	
}
