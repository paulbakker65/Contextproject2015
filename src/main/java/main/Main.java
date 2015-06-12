package main;

import input.DataFile;
import input.Input;

import java.io.File;


/**
 * Contains the first method that will be run.
 * Main will parse command line arguments and start the GUI.
 */
public class Main{
  
  /**
   * Main
   * @param args A array of arguments.
   */
  public static void main(String[] args) {  
    if (!parseCommandline(args)) {
      return;
    }

    if (!openGui()) {
      return;
    }

    //ProgressGui.init();

    return;
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
        if (!Input.setScriptFile(new File(argv[i + 1]))) {
          return false;
        }
        i++;
      } else if (argv[i].equals("-o") && argc - i > 1) {
        if (!Input.setOutputDir(new File(argv[i + 1]))) {
          return false;
        }
        i++;
      } else {
        String usage = "Error in program arguments.\n"
            + "Available commands are:\n" 
            + "    -f <data file> <settings file>\n" 
            + "    -s <script file>\n" 
            + "    -o <output directory>\n";
        System.out.println(usage);
        return false;
      }
    }
    return true;
  }

  /**
   * openGUI will run the graphical user interface.
   */
  public static boolean openGui() {
    String windowTitle = "Contextproject 2015 Groep 5/E";
    MainUI frame = new MainUI();
    frame.setTitle(windowTitle);
    
    GUI.init(frame);
    
    return true;
  }
}
