package main;

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
   * Checks whenether file exist, if not it prints this to the system.out.
   * @param file The file to check.
   * @return Returns true if the file exists, false if it does not.
   */
  public static boolean exists(File file){
    if (!file.exists()){
      System.out.println(file.getAbsolutePath() + " does not exist!");
      return false;
    }
    return true;
  }
  
  public static ArrayList<DataFile> getFiles() {
    return files;
  }

  /**
   * Validates the input files before creating the DataFile.
   * @param file The data file.
   * @param settings The settings file.
   * @throws Exception Throws an exception if input can not be found or if reading the data file and settings fails.
   */
  public static void addDataFile(File file, File settings) throws Exception{
    if (!exists(file) || !file.isFile()){
      String message = "Data file not found or is not a valid file.";
      System.out.println(message);
      throw new Exception(message);
    }
    if (!exists(settings) || !file.isFile()){
      String message = "Settings file not found or is not a valid file.";
      System.out.println(message);
      throw new Exception(message);
    }
    
    DataFile df = null;
    try {
      df = new DataFile(file, settings);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw e;
    }
    
    files.add(df);
  }  

  public static File getScriptFile() {
    return scriptFile;
  }

  public static boolean setScriptFile(File scriptFile) {
    if (scriptFile == null || !exists(scriptFile)){
      return false;
    }
    Input.scriptFile = scriptFile;
    return true;
  }

  public static File getOutputDir() {
    return outputDir;
  }

  public static boolean setOutputDir(File outputDir) {
    if (outputDir == null){
      return false;
    }
    if (!outputDir.isDirectory() && outputDir.exists()){
      System.out.println("'" + outputDir.getAbsolutePath() + "' is not a valid directory!");
      return false;
    }
    if (!exists(outputDir)){
      System.out.println("Trying to create directory.");
      boolean ret = outputDir.mkdir();
      if (!ret){
        System.out.println("Error creating direcotry '" + outputDir.getAbsolutePath() + "'.");
        return false;
      }
    }
    Input.outputDir = outputDir;
    return true;
  }
  
  public static boolean hasScript(){
    return scriptFile != null;
  }
  
  public static boolean hasOutput(){
    return outputDir != null;
  }
  
  public static boolean hasFiles(){
    return !files.isEmpty();
  }  
  
  public static void clean(){
    Input.files = new ArrayList<DataFile>();
    Input.outputDir = null;
    Input.scriptFile = null;
  }
}
