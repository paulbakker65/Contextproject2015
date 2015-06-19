package net.tudelft.hi.e.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stores all the required input and validates all input files.
 */
public class Input {
  static List<DataFile> files = new ArrayList<DataFile>();
  static File scriptFile = null;
  static File outputDir = null;
  private static final Logger LOG = Logger.getLogger(Input.class.getName());

  /**
   * Private constructor.
   */
  private Input() {
  }

  /**
   * Validates the input files before creating the DataFile.
   *
   * @param file The data file.
   * @param settings The settings file.
   * @throws IOException If the data or settings file is not found or not valid.
   */
  public static void addDataFile(final File file, final File settings) throws IOException {
    if (!exists(file)) {
      throw new FileNotFoundException("Data file is not found.");
    }
    if (!file.isFile()) {
      throw new UnsupportedEncodingException("Data file is not a valid file.");
    }
    if (!exists(settings)) {
      throw new FileNotFoundException("Settings file not found.");
    }
    if (!settings.isFile()) {
      throw new UnsupportedEncodingException("Data file is not a valid file.");
    }
    files.add(new DataFile(file, settings));
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
      LOG.log(Level.SEVERE, file.getAbsolutePath() + " does not exist!");
      return false;
    }
    return true;
  }

  public static List<DataFile> getFiles() {
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

  public static void setFiles(List<DataFile> files) {
    Input.files = files;
  }

  /**
   * Set the output directory.
   *
   * @param outputDir The output directory.
   * @return true if and only if the output directory was successfully set. <br>
   *         false if and only if the output directory was not set.
   */
  public static boolean setOutputDir(final File outputDir) {
    if (outputDir == null) {
      return false;
    }
    if (!outputDir.isDirectory() && outputDir.exists()) {
      LOG.log(Level.SEVERE, "\"{0}\" is not a valid directory!", outputDir.getAbsolutePath());
      return false;
    }
    if (!exists(outputDir)) {
      LOG.log(Level.INFO, "Trying to create directory.");
      final boolean ret = outputDir.mkdir();
      if (!ret) {
        LOG.log(Level.SEVERE, "Error creating direcotry ''{0}''.", outputDir.getAbsolutePath());
        return false;
      }
    }
    Input.outputDir = outputDir;
    return true;
  }

  /**
   * Set the script file.
   *
   * @param theScriptFile The script file.
   * @return true if and only if the script file was successfully set. <br>
   *         false if and only if the script file was not set.
   */
  public static boolean setScriptFile(final File theScriptFile) {
    if (theScriptFile == null || !exists(theScriptFile)) {
      return false;
    }
    Input.scriptFile = theScriptFile;
    return true;
  }
}
