package main;

import exceptions.TableNotFoundException;

import export.Exporter;

import input.DataFile;
import input.Input;

import operations.Operation;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import scriptlang.AnalysisLangLexer;
import scriptlang.AnalysisLangParser;
import scriptlang.extra.OperationSpec;
import scriptlang.extra.ScriptListener;

import table.Table;
import table.value.ColumnTypeMismatchException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingWorker;

class Task extends SwingWorker<Void, Void> {
  ArrayList<Table> tables = null;
  
  @Override
  public Void doInBackground() throws TableNotFoundException {
    this.firePropertyChange("starting", null, null);
    
    if (!parseFiles()) {
      return null;
    }
    
    if (!execScript()) {
      return null;
    }
    
    if (!exportFiles()) {
      return null;
    }
    
    return null;
  }
 
  @Override
  public void done() {
    this.firePropertyChange("done", null, null);
  }
  
  private boolean parseFiles() {
    log("Parsing input files.\n");
    tables = new ArrayList<Table>();
    
    int currentprogress = 0;
    int onefileprogress = 30 / Input.getFiles().size();
    
    for (DataFile f : Input.getFiles()) {
      Table table = null;
      try {
        log("Parsing " + f.toString() + "\n");
        table = f.getParser().parse(f.getReader());
        tables.add(table);
        currentprogress = currentprogress + onefileprogress;
        setProgress(currentprogress);
      } catch (ColumnTypeMismatchException | IOException e) {
        error("Error parsing input files.");
        e.printStackTrace();
        return false;
      }
    }
    log("Done parsing input files.\n\n");
    setProgress(30);
    return true;
  }
  
  private boolean execScript() {
    log("Executing script.\n");

    ANTLRInputStream input = null;
    try {
      input = new ANTLRFileStream(Input.getScriptFile().getAbsolutePath());
    } catch (IOException e) {
      log("Error reading script file.");
      e.printStackTrace();
      return false;
    }
    AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    AnalysisLangParser parser = new AnalysisLangParser(tokens);
    ScriptListener listener = new ScriptListener(tables);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());

    ArrayList<OperationSpec> operationList = listener.getOpList();

    for (OperationSpec o : operationList) {
      Operation op;
      try {
        op = o.getOperationForThisSpec();
        op.execute();
        
        o.getTableForTableName((String) o.operandList.get(0)).clear();
        o.getTableForTableName((String) o.operandList.get(0)).addAll(op.getResult());
      } catch (TableNotFoundException e) {
        error(e.getMessage());
        e.printStackTrace();
        return false;
      }
    }
    
    log("Done executing script.\n\n");    
    setProgress(80);
    return true;
  }
  
  private boolean exportFiles() {
    File od = Input.getOutputDir();
    
    log("Writing output files.\n");
    for (Table t : tables) {
      try {
        Exporter.export(t, new FileWriter(od.getAbsolutePath() 
            + "/output_" + t.getName() + ".csv"));
      } catch (IOException e) {
        log("Error writing file.");
        e.printStackTrace();
        return false;
      }
    }
    log("Done writing output files.\n\n");
    setProgress(100);
    return true;
  }
  
  private void log(String message) {
    System.out.println("log: " + message);
    this.firePropertyChange("log", null, message);
  }
  
  private void error(String error) {
    this.firePropertyChange("error", null, error);
  }
  
  public Table getTable() {
    return tables.get(0);
  }
}