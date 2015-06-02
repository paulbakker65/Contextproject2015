package main;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import exceptions.TableNotFoundException;

import export.Exporter;

import input.DataFile;
import input.Input;
import input.WrongXmlException;

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
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Contains the first method that will be run. Main will parse command line arguments and start the
 * GUI.
 */
@SuppressFBWarnings(value = "UC_USELESS_OBJECT")
public class Main {

  /**
   * Main method, run as program entry.
   * 
   * @param args Command line arguments.
   * @throws IOException If a IOException occurs in any of the programs operations.
   * @throws URISyntaxException If a URISyntaxException occurs in any of the programs operations.
   * @throws WrongXmlException If a WrongXmlException occurs in any of the programs operations.
   * @throws TableNotFoundException If a TableNotFoundException occurs in any of the programs
   *         operations.
   */
  public static void main(final String[] args) throws IOException, URISyntaxException,
      WrongXmlException, TableNotFoundException {

    if (!parseCommandline(args)) {
      return;
    }

    if (!openGui()) {
      return;
    }

    final ArrayList<Table> tables = new ArrayList<Table>();

    for (final DataFile f : Input.getFiles()) {
      Table table = null;
      try {
        table = f.getParser().parse(f.getReader());
        tables.add(table);
      } catch (final ColumnTypeMismatchException e) {
        e.printStackTrace();
      }
    }

    final File od = Input.getOutputDir();

    final ANTLRInputStream input = new ANTLRFileStream(Input.getScriptFile().getAbsolutePath());
    final AnalysisLangLexer lexer = new AnalysisLangLexer(input);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final AnalysisLangParser parser = new AnalysisLangParser(tokens);
    final ScriptListener listener = new ScriptListener(tables);
    ParseTreeWalker.DEFAULT.walk(listener, parser.parse());

    final ArrayList<OperationSpec> operationList = listener.getOpList();

    for (final OperationSpec o : operationList) {
      final Operation op = o.getOperationForThisSpec();
      op.execute();

      o.getTableForTableName((String) o.operandList.get(0)).clear();
      o.getTableForTableName((String) o.operandList.get(0)).addAll(op.getResult());
    }

    for (final Table t : tables) {
      Exporter.export(t, new FileWriter(od.getAbsolutePath() + "/output_" + t.getName() + ".csv"));
    }

    System.exit(0);
  }

  /**
   * openGUI will run the graphical user interface.
   */
  public static boolean openGui() {
    final String windowTitle = "Contextproject 2015 Groep 5/E";
    final MainUI dialog = new MainUI();
    dialog.pack();
    dialog.setTitle(windowTitle);
    dialog.centreWindow();
    dialog.setVisible(true);

    if (dialog.isExit()) {
      return false;
    }

    System.out.println("GUI done\n" + "scriptFile = " + Input.getScriptFile().getAbsolutePath()
        + "\n" + "outputDir = " + Input.getOutputDir().getAbsolutePath() + "\n" + "files = ");
    for (final DataFile file : Input.getFiles()) {
      System.out.println(file.toString());
    }

    return true;
  }

  /**
   * Parses command line arguments.
   * 
   * @param argv String[] containing all arguments.
   */
  public static boolean parseCommandline(final String[] argv) {
    final int argc = argv.length;
    for (int i = 0; i < argc; i++) {
      if (argv[i].equals("-f") && argc - i > 2) {
        final String filepath = argv[i + 1];
        final String settingsfilepath = argv[i + 2];

        final File file = new File(filepath);
        final File settings = new File(settingsfilepath);

        try {
          Input.addDataFile(file, settings);
        } catch (final Exception e) {
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
        final String usage =
            "Error in program arguments.\n" + "Available commands are:\n"
                + "    -f <data file> <settings file>\n" + "    -s <script file>\n"
                + "    -o <output directory>\n";
        System.out.println(usage);
        return false;
      }
    }
    return true;
  }
}
