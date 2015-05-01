package input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Table extends ArrayList<Tuple> {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(new Scanner(new Table().getClass().getResourceAsStream("/csvexample.csv").toString()));
	}
	
	public String toString() {
		String res = "";
		
		for (Tuple tuple : this) {
			res += tuple.toString() + "\n";
		}
		
		return res;
	}
}
