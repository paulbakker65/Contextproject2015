package main;


import java.io.IOException;
import java.net.URISyntaxException;

import table.Table;
import input.CSVReader;
import input.TXTReader;

public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException {
		Table test = CSVReader.read("/csvexample.csv");
		Table txtTest = TXTReader.read("/ADMIRE_13.txt");
		System.out.println(test);
		System.out.println(txtTest);
		
	}
	
}
