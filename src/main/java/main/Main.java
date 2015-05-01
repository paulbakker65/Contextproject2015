package main;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import input.CSVReader;

public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException {
		List<String[]> test = CSVReader.read(null);
		System.out.println(test);
		
	}
	
}
