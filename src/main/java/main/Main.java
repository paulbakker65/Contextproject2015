package main;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

import export.Exporter;
import parsers.ColumnTypeMismatchException;
import parsers.NumberValue;
import parsers.Parser;
import parsers.StringValue;
import process.Chunker;
import table.Table;
import input.CSVReader;
import input.Reader;
import input.Settings;
import input.WrongXMLException;
import input.XMLReader;

public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException, WrongXMLException, ColumnTypeMismatchException {
		Settings settings_website = XMLReader.readXMLFile("/settings_website.xml");
		Settings settings_statsensor = XMLReader.readXMLFile("/settings.xml");
		Settings settings_hospital = XMLReader.readXMLFile("/settings_hospital.xml");
		
		Reader reader;
		Parser parser;
		
		reader = new CSVReader("medical/Q_ADMIRE_metingen_pagevisits_141214.csv", settings_statsensor.getDelimiter());
		parser = new Parser(settings_website);
		Table website = parser.parse(reader);
		
		reader = new CSVReader("medical/measured creatinine/ADMIRE_13.txt", settings_statsensor.getDelimiter());
		parser = new Parser(settings_statsensor);
		Table statsensor = parser.parse(reader);
		
		reader = new CSVReader("medical/Afspraken_geanonimiseerd.csv", settings_statsensor.getDelimiter());
		parser = new Parser(settings_hospital);
		Table hospital = parser.parse(reader);
		
//		System.out.println(website);
//		System.out.println(statsensor);
		//System.out.println(hospital);
		
		Table patientWebsite = website.getPatientByID(new StringValue("admire13"), "Login");
		Table hospitalVisitPatient = hospital.getPatientByID(new NumberValue(13), "PatientID");
		
		System.out.println(patientWebsite);
		System.out.println();
		System.out.println(statsensor);
		System.out.println();
		System.out.println(hospitalVisitPatient);
		System.out.println();
		
		Chunker chunker = new Chunker(patientWebsite);
		Table temp = chunker.chunkOnMonth("Date");
		System.out.println(temp);

		
		Exporter.export(patientWebsite, new FileWriter("output.csv"), settings_website);
		Exporter.export(statsensor, new FileWriter("output2.csv"), settings_statsensor);
		Exporter.export(hospitalVisitPatient, new FileWriter("output3.csv"), settings_hospital);
		System.out.println("Demo finished!");
	}
}
