/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tudelft.hi.e.script;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.tudelft.hi.e.common.enums.ChunkType;
import net.tudelft.hi.e.common.enums.CompareOperator;
import net.tudelft.hi.e.common.enums.ComputeOperator;
import net.tudelft.hi.e.common.exceptions.ParseFailedException;
import net.tudelft.hi.e.computation.ChunkingOperation;
import net.tudelft.hi.e.computation.ComputeOperation;
import net.tudelft.hi.e.computation.ConstraintOperation;
import net.tudelft.hi.e.computation.ForEachChunkOperation;
import net.tudelft.hi.e.computation.Operation;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.input.DataFile;
import net.tudelft.hi.e.input.Input;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mawdegroot
 */
public class ScriptExecutionManagerTest {

  private static final Logger LOG
      = Logger.getLogger(ScriptExecutionManagerTest.class.getName());

  private ScriptExecutionManager manager;

  private List<Table> tables;

  private List<Table> tablesExpected;

  public ScriptExecutionManagerTest() {
  }

  @Before
  public void setUp() {
    tables = new ArrayList<Table>();
    try {
      Input.addDataFile(new File("src/test/resources/test4.csv"),
          new File("src/test/resources/test4.xml"));
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

    Input.clean();
    tablesExpected = new ArrayList<Table>();
    try {
      Input.addDataFile(new File("src/test/resources/test4.csv"),
          new File("src/test/resources/test4.xml"));
    } catch (IOException ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
    }
    for (DataFile f : Input.getFiles()) {
      Table table = null;
      try {
        table = f.getParser().parse(f.getReader());
        tablesExpected.add(table);
      } catch (ParseFailedException ex) {
        LOG.log(Level.SEVERE, "Error parsing input files.");
      }
    }
  }

  @After
  public void tearDown() {
    manager = null;
    tables = null;
    tablesExpected = null;
  }

  /**
   * Test of addScriptFile method, of class ScriptExecutionManager.
   */
  @Test
  public void testAddScriptFile() throws  ParseFailedException {
    manager.addScriptFile("src/test/resources/script.txt");
    manager.executeAllScripts();
    List<Table> actual = manager.getResultDataTables();

    ConstraintOperation operation = new ConstraintOperation(tablesExpected.
        get(0),
        "login", CompareOperator.EQ, new StringValue("admire13"));
    operation.execute();

    assertEquals(operation.getResult().getName(), actual.get(0).getName());
    assertEquals(operation.getResult().getChunks(), actual.get(0).getChunks());
    assertEquals(operation.getResult().getCodes(), actual.get(0).getCodes());
    assertEquals(operation.getResult(), actual.get(0));
  }

  /**
   * Test of addScriptString method, of class ScriptExecutionManager.
   */
  @Test
  public void testAddScriptString() throws ParseFailedException{
    manager.addScriptString("CONSTRAINT [website].[login] == \"admire13\"");
    manager.executeAllScripts();
    List<Table> actual = manager.getResultDataTables();

    ConstraintOperation operation = new ConstraintOperation(tablesExpected.
        get(0),
        "login", CompareOperator.EQ, new StringValue("admire13"));
    operation.execute();

    assertEquals(operation.getResult(), actual.get(0));
  }

  /**
   * Test of executeAllScripts method, of class ScriptExecutionManager.
   */
  @Test
  public void testExecuteAllScripts() throws ParseFailedException {
    manager.addScriptString("CONSTRAINT [website].[login] == \"admire13\"");
    manager.addScriptString("CHUNK [website].[date] USING MONTH 1");
    manager.addScriptString(
        "FOR EACH CHUNK [website] 1 COMPUTE [website] COUNT() [website].[login]");

    manager.executeAllScripts();
    List<Table> actual = manager.getResultDataTables();

    List<Operation> opList = new ArrayList<Operation>();
    Map<String, Table> tableMap = new LinkedHashMap<String, Table>();
    for (Table t : tablesExpected) {
      tableMap.put(t.getName(), t);
    }
    opList.add(new ConstraintOperation(tablesExpected.get(0),
        "login", CompareOperator.EQ, new StringValue("admire13")));
    opList.add(new ChunkingOperation(tablesExpected.get(0),
        "date", ChunkType.MONTH, 1));
    ComputeOperation operation = new ComputeOperation(tablesExpected.get(0),
        ComputeOperator.COUNT, "login");
    opList.add(new ForEachChunkOperation(tablesExpected.get(0), 1, operation));

    for (int i = 0; i < opList.size(); i++) {
      opList.get(i).execute();
      Table operationResultTable = tableMap.get(opList.get(i).
          getResultTableName());

      operationResultTable.clear();
      operationResultTable.addAll(opList.get(i).getResult());
    }

    assertEquals(tableMap.get("website"), actual.get(0));
  }

  /**
   * Test of getResultDataTables method, of class ScriptExecutionManager.
   */
  @Test
  public void testGetResultDataTables() {
    assertEquals(manager.getResultDataTables(), manager.getResultDataTables());
    assertNotEquals(manager.getResultDataTables(), null);
  }

}
