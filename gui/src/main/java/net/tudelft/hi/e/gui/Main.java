package net.tudelft.hi.e.gui;

import net.tudelft.hi.e.input.Input;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Contains the first method that will be run.
 * Main will parse command line arguments and start the GUI.
 */
public class Main{
  private static final Logger LOG = Logger.getLogger(Task.class.getName());
  private static boolean nogui = false;

  private Main() {
  }

  /**
   * Main
   * @param args A array of arguments.
   */
  public static void main(String[] args) {
    if (!parseCommandline(args)) {
      return;
    }

    if (nogui) {
      noGui();
    } else {
      openGui();
    }
  }

  /**
   * Parses command line arguments.
   * @param argv String[] containing all arguments.
   */
  public static boolean parseCommandline(String[] argv) {
    int argc = argv.length;
    int i = 0;
    while (i < argc) {
      String arg = argv[i];
      int left = argc - i;
      if ("-f".equals(arg) && left > 2) {
        if (!addDataFile(argv[i + 1], argv[i + 2])) {
          return false;
        }
        i += 2;
      } else if ("-s".equals(arg) && left > 1) {
        if (!Input.setScriptFile(new File(argv[i + 1]))) {
          return false;
        }
        i++;
      } else if ("-o".equals(arg) && left > 1) {
        if (!Input.setOutputDir(new File(argv[i + 1]))) {
          return false;
        }
        i++;
      } else if ("-nogui".equals(arg)) {
        nogui = true;
      } else {
        String usage = "Error in program arguments.\n"
            + "Available commands are:\n"
            + "    -f <data file> <settings file>\n"
            + "    -s <script file>\n"
            + "    -o <output directory>\n"
            + "    -nogui\n";
        LOG.log(Level.INFO, usage);
        return false;
      }
    }
    return true;
  }

  private static boolean addDataFile(String filepath, String settingsfilepath) {
    File file = new File(filepath);
    File settings = new File(settingsfilepath);

    try {
      Input.addDataFile(file, settings);
    } catch (Exception e) {
      LOG.log(Level.SEVERE, e.getMessage(), e);
      return false;
    }

    return true;
  }

  /**
   * openGUI will run the graphical user interface.
   */
  private static void openGui() {
    String windowTitle = "Contextproject 2015 Groep 5/E";
    MainUi frame = new MainUi();
    frame.setTitle(windowTitle);

    Gui.init(frame);
  }

  private static void noGui() {
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
