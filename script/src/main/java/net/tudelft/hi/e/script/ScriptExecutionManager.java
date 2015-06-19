/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package net.tudelft.hi.e.script;

import net.tudelft.hi.e.common.exceptions.ParseFailedException;
import net.tudelft.hi.e.computation.Operation;
import net.tudelft.hi.e.data.Table;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mawdegroot
 */
public class ScriptExecutionManager {

  /**
   * Map containing the tables, with the table name as key.
   */
  private Map<String, Table> tableMap;

  /**
   * Operation List.
   */
  private List<Operation> opList;

  /**
   * Default constructor creating a script execution manager.
   * @param tables list of tables to use with the operations.
   */
  public ScriptExecutionManager(List<Table> tables) {
    tableMap = new LinkedHashMap<String, Table>();
    for (Table t : tables) {
      tableMap.put(t.getName(), t);
    }
    opList = new ArrayList<Operation>();
  }

  /**
   * Add a script file to the manager.
   * @param filePath path to the script file.
   * @throws ParseFailedException if the script file cannot be parsed.
   */
  public void addScriptFile(String filePath) throws ParseFailedException {
    opList.addAll(OperationFactory.createOperationsFromFile(getTableMapAsList(), filePath));
  }

  /**
   * Add a script string to the manager.
   * @param scriptString string containing the script.
   * @throws ParseFailedException if the script file cannot be parsed.
   */
  public void addScriptString(String scriptString) throws ParseFailedException {
    opList.addAll(OperationFactory.createOperationsFromString(getTableMapAsList(), scriptString));
  }

  /**
   * Execute all scripts in the manager.
   * @return the list of tables containing the output.
   */
  public List<Table> executeAllScripts() {
    for (Operation o : opList) {
      createTableIfNotExists(o.getResultTableName());
      o.resetData(tableMap.get(o.getInputTableName()));
      o.execute();
      tableMap.remove(o.getResultTableName());
      tableMap.put(o.getResultTableName(), o.getResult());
    }
    return getTableMapAsList();
  }

  /**
   * Create a table for the given table name if it doesn't exist in the table map.
   * @param resultTableName the name for the table to create.
   */
  private void createTableIfNotExists(String resultTableName) {
    if (!tableMap.containsKey(resultTableName)) {
      Table newTable = new Table();
      newTable.setName(resultTableName);
      tableMap.put(resultTableName, newTable);
    }
  }

  /**
   * Get the result tables of the script execution.
   * @return list of result tables.
   */
  public List<Table> getResultDataTables() {
    return getTableMapAsList();
  }

  /**
   * Get the table map as a list of tables.
   * @return the table map as list.
   */
  private List<Table> getTableMapAsList() {
    return new ArrayList<Table>(tableMap.values());
  }

}
