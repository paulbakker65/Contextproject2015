package net.tudelft.hi.e.gui;

import net.tudelft.hi.e.common.exceptions.ExceptionHandler;
import net.tudelft.hi.e.common.exceptions.ParseFailedException;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.export.Exporter;
import net.tudelft.hi.e.export.SettingsBuilder;
import net.tudelft.hi.e.export.SettingsWriter;
import net.tudelft.hi.e.input.DataFile;
import net.tudelft.hi.e.input.Input;
import net.tudelft.hi.e.input.Settings;
import net.tudelft.hi.e.script.ScriptExecutionManager;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.swing.SwingWorker;


class Task extends SwingWorker<Void, Void> {

  private static final Logger LOG = Logger.getLogger(Task.class.getName());

  private List<Table> tables = null;

  @Override
  public Void doInBackground() {
    this.firePropertyChange("starting", null, null);

    if (!parseFiles()) {
      return null;
    }

    if (!execScript()) {
      return null;
    }

    exportFiles();

    return null;
  }

  @Override
  public void done() {
    this.firePropertyChange("done", null, null);
  }

  /**
   * Parses all the files in Input class, the tables will be stored in the tables list.
   * 
   * @return returns true if succeeded, false if an error occurred during parsing.
   */
  private boolean parseFiles() {
    log("Parsing input files.", true);
    tables = new ArrayList<>();

    int currentprogress = 0;
    int onefileprogress = 30 / Input.getFiles().size();

    for (DataFile datafile : Input.getFiles()) {
      log("Parsing " + datafile.toString(), false);
      Table table = datafile.getTable();
      if (table == null) {
        error("Error Parsing " + datafile.toString());
        return false;
      }
      tables.add(table);
      currentprogress = currentprogress + onefileprogress;
      setProgress(currentprogress);
    }
    log("Done parsing input files.\n", true);
    setProgress(30);

    return true;
  }

  /**
   * Executes the script file.
   * 
   * @return returns true if succeeded, false if an error occurred during parsing or execution.
   */
  private boolean execScript() {
    log("Executing script.", true);

    ScriptExecutionManager exec = new ScriptExecutionManager(tables);
    try {
      exec.addScriptFile(Input.getScriptFile().getAbsolutePath());
    } catch (ParseFailedException ex) {
      String error = "Error parsing the script file!";
      LOG.log(Level.SEVERE, error, ex);
      error(error + ex.getMessage());
      return false;
    }
    if (!ExceptionHandler.getExceptionHandlerInstance().getLogRecords().isEmpty()) {
      error("Error parsing the script file!");
      for (LogRecord r : ExceptionHandler.getExceptionHandlerInstance().getLogRecords()) {
        error(r.getMessage());
      }
      return false;
    }
    exec.executeAllScripts();
    tables = exec.getResultDataTables();

    log("Done executing script.\n", true);
    setProgress(80);
    return true;
  }

  /**
   * Exports all tables and settings for the tables.
   */
  private void exportFiles() {
    File outputDir = Input.getOutputDir();

    int progress = getProgress();
    int percent = (100 - progress) / tables.size();
    log("Writing output files.", true);
    for (Table table : tables) {
      String filepath = 
          Paths.get(outputDir.getAbsolutePath() , "output_" + table.getName()).toString();
      exportFile(table, filepath);
      exportSettings(table, filepath + ".xml");
      progress += percent;
      setProgress(progress);
    }
    log("Done writing output files.\n", true);

    setProgress(100);
  }

  /**
   * Writes the table to a given file path.
   * 
   * @param table
   *          The table to export.
   * @param filepath
   *          The file path to save the table in.
   */
  public void exportFile(Table table, String filepath) {
    log("Writing data file: " + filepath);
    try {
      Exporter.export(table, filepath, ".csv");
    } catch (Exception e) {
      String error = "Error writing file: " + filepath;
      LOG.log(Level.SEVERE, error, e);
      error(error);
    }
  }

  /**
   * Generates the settings and saves it to a given file path.
   * 
   * @param table
   *          The table to export settings for.
   * @param filepath
   *          The file path to save the settings in.
   */
  public void exportSettings(Table table, String filepath) {
    Settings settings = SettingsBuilder.generateSettings(table, ",", 2);
    if (settings == null) {
      error("Error generating settings for: " + table.getName());
    } else {
      log("Writing Settings file: " + filepath);
      try {
        SettingsWriter.writeSettings(settings, new File(filepath));
      } catch (Exception e) {
        String error = "Error creating xml file: " + e.getMessage();
        LOG.log(Level.SEVERE, error, e);
        error(error);
      }
    }
  }

  private void log(String message, boolean... options) {
    boolean bold = options.length > 0 && options[0];
    LOG.log(Level.INFO, message);
    this.firePropertyChange("log", bold, message + System.lineSeparator());
  }

  private void error(String error) {
    this.firePropertyChange("error", null, error + System.lineSeparator());
  }

  public Table getTable(int index) {
    return tables.get(index);
  }

  public List<Table> getTables() {
    return tables;
  }
}
