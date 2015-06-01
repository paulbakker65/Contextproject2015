package input;

import java.io.File;
import java.util.ArrayList;

/**
 * Stores all the required input and validates all input files.
 */
public class Input {
  static ArrayList<DataFile> files = new ArrayList<DataFile>();
  static File scriptFile = null;
  static File outputDir = null;

  /**
   * Validates the input files before creating the DataFile.
   * 
   * @param file The data file.
   * @param settings The settings file.
   * @throws Exception Throws an exception if input can not be found or if reading the data file and
   *         settings fails.
   */
  public static void addDataFile(final File file, final File settings) throws Exception {
    if (!exists(file) || !file.isFile()) {
      final String message = "Data file not found or is not a valid file.";
      System.out.println(message);
      throw new Exception(message);
    }
    if (!exists(settings) || !file.isFile()) {
      final String message = "Settings file not found or is not a valid file.";
      System.out.println(message);
      throw new Exception(message);
    }

    DataFile df = null;
    try {
      df = new DataFile(file, settings);
    } catch (final Exception e) {
      System.out.println(e.getMessage());
      throw e;
    }

    files.add(df);
  }

  /**
   * Resets the object clearing all the set fields.
   */
  public static void clean() {
    Input.files = new ArrayList<DataFile>();
    Input.outputDir = null;
    Input.scriptFile = null;
  }

  /**
   * Checks whether file exist, if not it prints this to the system.out.
   *
   * @param file The file to check.
   * @return Returns true if the file exists, false if it does not.
   */
  public static boolean exists(final File file) {
    if (!file.exists()) {
      System.out.println(file.getAbsolutePath() + " does not exist!");
      return false;
    }
    return true;
  }

  public static ArrayList<DataFile> getFiles() {
    return files;
  }

  public static File getOutputDir() {
    return outputDir;
  }

  public static File getScriptFile() {
    return scriptFile;
  }

  public static boolean hasFiles() {
    return !files.isEmpty();
  }

  public static boolean hasOutput() {
    return outputDir != null;
  }

  public static boolean hasScript() {
    return scriptFile != null;
  }

  /**
   * Set the output directory.
   * 
   * @param outputDir The output directory.
   * @return true iff the output directory was succesfully set. <br>
   *         false iff the output directory was not set.
   */
  public static boolean setOutputDir(final File outputDir) {
    if (outputDir == null) {
      return false;
    }
    if (!outputDir.isDirectory() && outputDir.exists()) {
      System.out.println("'" + outputDir.getAbsolutePath() + "' is not a valid directory!");
      return false;
    }
    if (!exists(outputDir)) {
      System.out.println("Trying to create directory.");
      final boolean ret = outputDir.mkdir();
      if (!ret) {
        System.out.println("Error creating direcotry '" + outputDir.getAbsolutePath() + "'.");
        return false;
      }
    }
    Input.outputDir = outputDir;
    return true;
  }

  /**
   * Set the script file.
   * 
   * @param scriptFile The script file.
   * @return true iff the script file was succesfully set. <br>
   *         false iff the script file was not set.
   */
  public static boolean setScriptFile(final File scriptFile) {
    if (scriptFile == null || !exists(scriptFile)) {
      return false;
    }
    Input.scriptFile = scriptFile;
    return true;
  }
}
