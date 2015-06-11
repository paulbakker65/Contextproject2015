package net.tudelft.hi.e.gui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import net.tudelft.hi.e.common.exceptions.ParseFailedException;
import net.tudelft.hi.e.common.exceptions.TableNotFoundException;
import net.tudelft.hi.e.computation.Operation;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.export.Exporter;
import net.tudelft.hi.e.input.DataFile;
import net.tudelft.hi.e.input.Input;
import net.tudelft.hi.e.script.AnalysisLangLexer;
import net.tudelft.hi.e.script.AnalysisLangParser;
import net.tudelft.hi.e.script.OperationSpec;
import net.tudelft.hi.e.script.ScriptListener;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

class Task extends SwingWorker<Void, Void> {

  private static final Logger LOG = Logger.getLogger(Task.class.getName());

  ArrayList<Table> tables = null;

  @Override
  public Void doInBackground() {
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
      } catch (ParseFailedException ex) {
        LOG.log(Level.SEVERE, "Error prasing input files.");
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

    List<OperationSpec> operationList = listener.getOpList();

    for (OperationSpec o : operationList) {
      Operation op;
      try {
        op = o.getOperationForThisSpec();
        op.execute();

        o.getTableForTableName((String) o.getOperandList().get(0)).clear();
        o.getTableForTableName((String) o.getOperandList().get(0)).addAll(op.getResult());
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
        Exporter
            .export(t, new FileWriter(od.getAbsolutePath() + "/output_" + t.getName() + ".csv"));
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

  public Table getTable(int index) {
    return tables.get(index);
  }

  public ArrayList<Table> getTables() {
    return tables;
  }
}
