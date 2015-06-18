package net.tudelft.hi.e.script;

import net.tudelft.hi.e.common.exceptions.ParseFailedException;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.input.DataFile;
import net.tudelft.hi.e.input.Input;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class ScriptErrorListenerTest {

  ScriptExecutionManager manager;
  private List<Table> tables;
  private static final Logger LOG
      = Logger.getLogger(ScriptErrorListenerTest.class.getName());

  @Before
  public void setUp() throws Exception {
    tables = new ArrayList<Table>();
    try {
      Input.addDataFile(new File("src/test/resources/test4.csv"), new File(
          "src/test/resources/test4.xml"));
    } catch (IOException ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
    }
    for (DataFile f : Input.getFiles()) {
      Table table = null;
      try {
        table = f.getParser().parse(f.getReader());
        tables.add(table);
      } catch (ParseFailedException ex) {
        LOG.log(Level.SEVERE, "Error parsing input files.");
      }
    }
    manager = new ScriptExecutionManager(tables);
  }

  @Test(expected = ParseFailedException.class)
  public void testSyntaxError() throws ParseFailedException {
    manager.addScriptString("TRALALAA WRONG SCRIPT STRING");
  }

  @Test
  public void testSyntaxNoError() throws ParseFailedException {
    manager.addScriptString("CONSTRAINT [website].[Login] == \"admire13\"");
  }
}
