package main;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

import operations.ChunkingOperation;
import operations.ChunkingOperation.ChunkComparatorEnum;
import export.Exporter;
import parsers.ColumnTypeMismatchException;
import parsers.NumberValue;
import parsers.Parser;
import parsers.StringValue;
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
		
		
		Table patientWebsite = website.getPatientByID(new StringValue("admire13"), "Login");
		Table hospitalVisitPatient = hospital.getPatientByID(new NumberValue(13), "PatientID");
		
		System.out.println(patientWebsite);
		System.out.println();
		System.out.println(statsensor);
		System.out.println();
		System.out.println(hospitalVisitPatient);
		System.out.println();
		
		ChunkingOperation chunker = new ChunkingOperation(patientWebsite);
		chunker.setOperationParameters("Date", ChunkComparatorEnum.MONTH, settings_website);
		chunker.execute();
		
		ChunkingOperation chunker2 = new ChunkingOperation(website);
		chunker2.setOperationParameters("UserId", ChunkComparatorEnum.PATIENT, settings_website);
		chunker2.execute();

		
		//Exporter.export(patientWebsite, new FileWriter("output.csv"), settings_website);
		Exporter.export(chunker.getOutput(), new FileWriter("test.csv"), settings_website);
		Exporter.export(chunker2.getOutput(), new FileWriter("test2.csv"), settings_website);
		
		Exporter.export(statsensor, new FileWriter("output2.csv"), settings_statsensor);
		Exporter.export(hospitalVisitPatient, new FileWriter("output3.csv"), settings_hospital);
		System.out.println("Demo finished!");
	}
}
