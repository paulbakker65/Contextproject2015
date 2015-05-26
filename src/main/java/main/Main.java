package main;

import input.DataFile;
import input.Input;
import input.WrongXMLException;

import java.io.File;
//import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.Collections;

//import operations.FilterOperation;
//import operations.FilterOperation.ConstraintComparatorEnum;
//import export.Exporter;
//import parsers.ColumnTypeMismatchException;
//import parsers.NumberValue;
//import parsers.StringValue;
//import table.RecordComparator;
//import table.Table;

/**
 * Contains the first method that will be run. Main will parse command line arguments and start the GUI.
 */
public class Main{

  public static void main(String[] args) throws IOException, URISyntaxException, WrongXMLException {

    if (!parseCommandline(args)){
      return;
    }

    if (!openGUI()){
      return;
    }

    System.exit(0);

//    ArrayList<Table> tables = new ArrayList<Table>();
//
//    for (DataFile f : Input.getFiles()) {
//      Table t = null;
//      try {
//        t = f.getParser().parse(f.getReader());
//      } catch (ColumnTypeMismatchException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      }
//    }
    // Read script & execute.

    /*
     * //DEMO START Table patientWebsite = tables.get(0).getPatientByID("admire13", "Login"); Table
     * hospitalVisitPatient = tables.get(2).getPatientByID("13", "PatientID"); Table statsensor =
     * tables.get(1);
     * 
     * System.out.println(patientWebsite); System.out.println(); System.out.println(statsensor);
     * System.out.println(); System.out.println(hospitalVisitPatient);
     * 
     * Exporter.export(patientWebsite, new FileWriter("output.csv"), files.get(0).getSettings());
     * Exporter.export(statsensor, new FileWriter("output2.csv"), files.get(1).getSettings());
     * Exporter.export(hospitalVisitPatient, new FileWriter("output3.csv"),
     * files.get(2).getSettings()); System.out.println("Demo finished!"); //DEMO END
     */

  }

  /**
   * Parses command line arguments.
   * 
   * @param argv String[] containing all arguments.
   */
  public static boolean parseCommandline(String[] argv) {
    int argc = argv.length;
    for (int i = 0; i < argc; i++) {
      if (argv[i].equals("-f") && argc - i > 2) {
        String filepath = argv[i + 1];
        String settingsfilepath = argv[i + 2];

        File file = new File(filepath);
        File settings = new File(settingsfilepath);

        try {
          Input.addDataFile(file, settings);
        } catch (Exception e) {
          System.out.println(e.getMessage());
          return false;
        }
        i = i + 2;
      } else if (argv[i].equals("-s") && argc - i > 1) {
        if (!Input.setScriptFile(new File(argv[i + 1]))){
          return false;
        }
        i++;
      } else if (argv[i].equals("-o") && argc - i > 1) {
        if (!Input.setOutputDir(new File(argv[i + 1]))){
          return false;
        }
        i++;
      } else {
        String usage = "Error in program arguments.\n" + 
            "Available commands are:\n" + 
            "    -f <data file> <settings file>\n" + 
            "    -s <script file>\n" + 
            "    -o <output directory>\n";
        System.out.println(usage);
        return false;
      }
    }
    return true;
  }

  /**
   * openGUI will run the graphical user interface.
   */
  public static boolean openGUI() {
    String windowTitle = "Contextproject 2015 Groep 5/E";
    MainUI dialog = new MainUI();
    dialog.pack();
    dialog.setTitle(windowTitle);
    dialog.centreWindow();
    dialog.setVisible(true);

    if (dialog.isExit()){//User pressed Cancel or closed the window.
      return false;
    }

    System.out.println("GUI done\n" + 
        "scriptFile = " + Input.getScriptFile().getAbsolutePath() + "\n" + 
        "outputDir = " + Input.getOutputDir().getAbsolutePath() + "\n" + 
        "files = ");
    for (DataFile file : Input.getFiles()) {
      System.out.println(file.toString());
    }
    
    return true;
  }
}
