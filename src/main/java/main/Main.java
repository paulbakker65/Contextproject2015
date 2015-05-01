package main;


import java.io.IOException;
import java.net.URISyntaxException;

import input.CSVReader;
import input.Table;

public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException {
		Table test = CSVReader.read(null);
		System.out.println(test);
		
	}
	
}
