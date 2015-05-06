package main;


import java.io.IOException;
import java.net.URISyntaxException;

import table.Table;
import input.CSVReader;
import input.TXTReader;

public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException {
		Table test = CSVReader.read("/Q_ADMIRE_metingen_pagevisits_141214.csv");
		Table txtTest = TXTReader.read("/ADMIRE_13.txt");
		Table patientWebsite = test.getPatientByID("admire13");
		patientWebsite.addAll(txtTest);
		System.out.println(patientWebsite);
		//System.out.println(txtTest);
		
	}
	
}
