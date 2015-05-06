package main;


import java.io.IOException;
import java.net.URISyntaxException;

import table.Table;
import input.CSVReader;
import input.Settings;
import input.WrongXMLException;
import input.XMLReader;

public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException, WrongXMLException {
		Settings settings = XMLReader.readXMLFile("/settings.xml");
		Settings settings_website = XMLReader.readXMLFile("/settings_website.xml");
		Table websiteTest = CSVReader.read("/Q_ADMIRE_metingen_pagevisits_141214.csv", settings_website);
		Table txtTest = CSVReader.read("/ADMIRE_13.txt", settings);
		Table patientWebsite = websiteTest.getPatientByID("admire13");
		patientWebsite.addAll(txtTest);
		System.out.println(patientWebsite);
		
		
	}
	
}
