package net.tudelft.hi.e.gui;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.tudelft.hi.e.input.Input;


/**
 * Contains the first method that will be run.
 * Main will parse command line arguments and start the GUI.
 */
public class Main{
  private static final Logger LOG = Logger.getLogger(Task.class.getName());
  private static boolean nogui = false;
  /**
   * Main
   * @param args A array of arguments.
   */
  public static void main(String[] args) {
    if (!parseCommandline(args)) {
      return;
    }

    if(nogui) {
      nooGui();
    } else {
      if (!openGui()) {
        return;
      }
    }

    return;
  }

  /**
   * Parses command line arguments.
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
      } else if (argv[i].equals("-nogui")) {
        nogui = true;
      } else {
        String usage = "Error in program arguments.\n"
            + "Available commands are:\n"
            + "    -f <data file> <settings file>\n"
            + "    -s <script file>\n"
            + "    -o <output directory>\n"
            + "    -nogui\n";
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

  public static void nooGui() {
    if (!Input.hasScript() || !Input.hasOutput() || !Input.hasFiles()) {
      LOG.log(Level.SEVERE, "Wrong input!\n"
              + "Please make sure you have selected a script file,\n"
              + "output directory and at least 1 data file.\n");
      return;
    }
    LOG.log(Level.INFO, "Starting task now.\n");
    Task task = new Task();
    task.doInBackground();
  }
}
