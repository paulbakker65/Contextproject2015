package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import operations.FilterOperation;
import operations.FilterOperation.ConstraintComparatorEnum;
import export.Exporter;
import parsers.ColumnTypeMismatchException;
import parsers.NumberValue;
import parsers.StringValue;
import table.Record;
import table.RecordComparator;
import table.Table;
import input.WrongXMLException;

public class Main {
	private static ArrayList<DataFile> files = new ArrayList<DataFile>();
	private static File scriptFile;
	private static File outputDir;
	
	public static void main(String[] args) throws IOException, URISyntaxException, WrongXMLException {
		
		parseCommandline(args);
		
		openGUI();

		//System.exit(0);	    
	    
		
		Table input = new Table();
		for(DataFile f : files){
			Table t = null;
			try {
				t = f.getParser().parse(f.getReader());
			} catch (ColumnTypeMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			input.addAll(t);
		}
		Collections.sort(input, new RecordComparator("Date"));
		Set<String> keys = new HashSet<String>();
		for(Record r : input) {
		  keys.addAll(r.keySet());
		}
		
		FilterOperation fo = new FilterOperation(input);
		fo.setOperationParameters("Login", ConstraintComparatorEnum.EQ, new StringValue("admire13"));
		fo.execute();
		input = fo.getResult();
		FilterOperation fo2 = new FilterOperation(input);
		fo2.setOperationParameters("PatientID", ConstraintComparatorEnum.EQ, new NumberValue(13));
		fo2.execute();
		input = fo2.getResult();
		Exporter.export(input, new FileWriter(outputDir + "/output.csv"), keys);


		//Read script & execute.

		
		/*
		//DEMO START
		Table patientWebsite = tables.get(0).getPatientByID("admire13", "Login");
		Table hospitalVisitPatient = tables.get(2).getPatientByID("13", "PatientID");
		Table statsensor = tables.get(1);
		
		System.out.println(patientWebsite);
		System.out.println();
		System.out.println(statsensor);
		System.out.println();
		System.out.println(hospitalVisitPatient);
		
		Exporter.export(patientWebsite, new FileWriter("output.csv"), files.get(0).getSettings());
		Exporter.export(statsensor, new FileWriter("output2.csv"), files.get(1).getSettings());
		Exporter.export(hospitalVisitPatient, new FileWriter("output3.csv"), files.get(2).getSettings());
		System.out.println("Demo finished!");
		//DEMO END
		*/
		
		System.exit(0);
	}
	
	/**
	 * Parses command line arguments
	 * @param argv String[] containing all arguments.
	 */
	public static void parseCommandline(String[] argv){
		int argc = argv.length;
		for(int i = 0; i < argc; i++){
			if(argv[i].equals("-f") && argc-i > 2){
				String filepath = argv[i+1];
				String settingsfilepath = argv[i+2];
				
				File file = new File(filepath);
				File settings = new File(settingsfilepath);
				
				if(!file.exists()){
					System.out.println(filepath + " does not exist!");
					System.exit(1);
				}
				if(!settings.exists()){
					System.out.println(settings + " does not exist!");
					System.exit(1);
				}
				
				DataFile f = new DataFile(file, settings);
				files.add(f);
				i = i + 2;
			}
			else if(argv[i].equals("-s") && argc-i > 1){
				scriptFile = new File(argv[i+1]);
			}
			else{
				String usage =
						  "Error in program arguments.\n"
						+ "Available commands are:\n"
						+ "    -f <data file> <settings file>\n"
						+ "    -s <script file>\n";
				System.out.println(usage);
				System.exit(1);
			}
		}
	}
	
	/**
	 * openGUI will run the graphical user interface.
	 */
	public static void openGUI(){
		String windowTitle = "Contextproject 2015 Groep 5/E";
	    mainUI dialog = new mainUI();
	    dialog.pack();
	    dialog.setTitle(windowTitle);
		dialog.centreWindow();
	    dialog.setVisible(true);

		files = dialog.getFiles();
		scriptFile = dialog.getScriptFile();
		outputDir = dialog.getOutputDir();

		if(files == null || scriptFile == null || outputDir == null){
			System.out.println("Error retrieving data from gui.");
			System.exit(1);
		}

		System.out.println("GUI done\nscriptFile = " + scriptFile.getPath() + "\noutputDir = " + outputDir.getPath() + "\nfiles = ");
		for(DataFile file : files){
			System.out.println(file.toString());
		}
	}
}
