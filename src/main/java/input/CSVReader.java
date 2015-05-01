package input;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CSVReader {
	
	public static List<String[]> read (File file) throws IOException {
		List<String[]> data = new ArrayList<String[]>();
		System.out.println("test");
		Scanner sc = new Scanner(new CSVReader().getClass().getResourceAsStream("/csvexample.csv"));
		while(sc.hasNext()){
			data.add(sc.nextLine().split(";"));
		}
		sc.close();
		return data;
	}
	
}
