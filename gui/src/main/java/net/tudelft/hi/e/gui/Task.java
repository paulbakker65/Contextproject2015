package net.tudelft.hi.e.gui;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.swing.SwingWorker;

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

class Task extends SwingWorker<Void, Void> {

  private static final Logger LOG = Logger.getLogger(Task.class.getName());

  List<Table> tables = null;

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
   * @return returns true if succeeded, false if an error occurred during parsing.
   */
  private boolean parseFiles() {
    log("Parsing input files.");
    tables = new ArrayList<Table>();

    int currentprogress = 0;
    int onefileprogress = 30 / Input.getFiles().size();

    for (DataFile f : Input.getFiles()) {
      Table table = null;
      try {
        log("Parsing " + f.toString());
        table = f.getParser().parse(f.getReader());
        tables.add(table);
        currentprogress = currentprogress + onefileprogress;
        setProgress(currentprogress);
      } catch (ParseFailedException ex) {
        LOG.log(Level.SEVERE, "Error parsing input files: " + ex.getMessage() + ".");
        return false;
      }
    }
    log("Done parsing input files.\n");
    setProgress(30);

    return true;
  }

  /**
   * Executes the script file.
   * @return returns true if succeeded, false if an error occurred during parsing or execution.
   */
  private boolean execScript() {
    log("Executing script.");

    ScriptExecutionManager exec = new ScriptExecutionManager(tables);
    ExceptionHandler.getExceptionHandlerInstance().getLogRecords();
    try {
      exec.addScriptFile(Input.getScriptFile().getAbsolutePath());
    } catch (ParseFailedException ex) {
      error("Error parsing the script file!");
      error(ex.getMessage());
      return false;
    }
    if (!ExceptionHandler.getExceptionHandlerInstance().getLogRecords().isEmpty()) {
      error("Error parsing the script file!");
      for(LogRecord r : ExceptionHandler.getExceptionHandlerInstance().getLogRecords()) {
        error(r.getMessage());
      }
      return false;
    }
    exec.executeAllScripts();
    tables = exec.getResultDataTables();

    log("Done executing script.\n");
    setProgress(80);
    return true;
  }

  /**
   * Exports all tables and settings for the tables.
   */
  private void exportFiles() {
    File outputDir = Input.getOutputDir();

    log("Writing output files.##############");
    for (Table table : tables) {
      String filepath = outputDir.getAbsolutePath() + "/output_" + table.getName();
      exportFile(table, filepath);
      exportSettings(table, filepath + ".xml");
    }
    log("Done writing output files.##############\n");

    setProgress(100);
  }

  /**
   * Writes the table to a given file path.
   * @param table The table to export.
   * @param filepath The file path to save the table in.
   */
  private void exportFile(Table table, String filepath) {
    log("Writing data file: " + filepath);
    try {
      Exporter.export(table, filepath, ".csv");
    } catch (Exception e) {
      error("Error writing file: " + filepath);
      e.printStackTrace();
    }
  }

  /**
   * Generates the settings and saves it to a given file path.
   * @param table The table to export settings for.
   * @param filepath The file path to save the settings in.
   */
  private void exportSettings(Table table, String filepath) {
    Settings settings = SettingsBuilder.generateSettings(table, ",", 2);
    if (settings == null) {
      error("Error generating settings for: " + table.getName());
    } else {
      log("Writing Settings file: " + filepath);
      try {
        SettingsWriter.writeSettings(settings, new File(filepath));
      } catch (Exception e) {
        error("Error creating xml file: " + e.getMessage());
        e.printStackTrace();
      }
    }
  }

  private void log(String message) {
    System.out.println("log: " + message);
    this.firePropertyChange("log", null, message + System.lineSeparator());
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
