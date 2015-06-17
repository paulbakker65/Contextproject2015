/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tudelft.hi.e.script;

import net.tudelft.hi.e.common.exceptions.ParseFailedException;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.input.DataFile;
import net.tudelft.hi.e.input.Input;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mawdegroot
 */
public class Test {

  private static final Logger LOG = Logger.getLogger(Test.class.getName());

  public static void main(String[] args) {
    List<Table> tables = new ArrayList<Table>();

    try {
      Input.addDataFile(new File("/Users/mawdegroot/aaa/ADMIRE_13.txt"),
          new File("/Users/mawdegroot/aaa/settings.xml"));
      Input.addDataFile(new File("/Users/mawdegroot/aaa/Afspraken_geanonimiseerd.csv"),
          new File("/Users/mawdegroot/aaa/settings_hospital.xml"));
      Input.addDataFile(new File("/Users/mawdegroot/aaa/Q_ADMIRE_metingen_pagevisits_141214.csv"),
          new File("/Users/mawdegroot/aaa/settings_website.xml"));
    } catch (Exception ex) {

    }
    for (DataFile f : Input.getFiles()) {
      Table table = null;
      try {
        table = f.getParser().parse(f.getReader());
        tables.add(table);
      } catch (ParseFailedException ex) {
        LOG.log(Level.SEVERE, "Error prasing input files.");
      }
    }

    ScriptExecutionManager exec = new ScriptExecutionManager(tables);
    exec.addScriptString("CONSTRAINT [website].[Login] == \"admire13\"");
    exec.addScriptString("CHUNK [website].[Date] USING MONTH 1");
    exec.addScriptString("FOR EACH CHUNK [website] 1 COMPUTE [website] COUNT() [website].[Login]");

    exec.executeAllScripts();

    List<Table> resultDataSet = exec.getResultDataTables();

    for (Table t1 : resultDataSet) {
      if ("website".equals(t1.getName())) {
        for (Table t2 : t1.getChunks()) {
          System.out.println(t2);
        }
      }
    }
  }

}
