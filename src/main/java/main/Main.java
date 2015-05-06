package main;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

import export.Exporter;
import table.Table;
import input.CSVReader;
import input.Settings;
import input.WrongXMLException;
import input.XMLReader;

public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException, WrongXMLException {
		Settings settings = XMLReader.readXMLFile("/settings.xml");
		Settings settings_website = XMLReader.readXMLFile("/settings_website.xml");
		Settings settings_hospital = XMLReader.readXMLFile("/settings_hospital.xml");
		Table hospitalTest = CSVReader.read("/Afspraken_geanonimiseerd.csv", settings_hospital);
		Table websiteTest = CSVReader.read("/Q_ADMIRE_metingen_pagevisits_141214.csv", settings_website);
		Table txtTest = CSVReader.read("/ADMIRE_13.txt", settings);
		Table patientWebsite = websiteTest.getPatientByID("admire13", "Login");
		Table hospitalVisitPatient = hospitalTest.getPatientByID("13", "PatientID");
		
		System.out.println(txtTest);
		System.out.println(patientWebsite);
		System.out.println(hospitalVisitPatient);

		Exporter.export(patientWebsite, new FileWriter("output.csv"));
		Exporter.export(txtTest, new FileWriter("output2.csv"));
		Exporter.export(hospitalVisitPatient, new FileWriter("output3.csv"));
		
		
	}
	
}
