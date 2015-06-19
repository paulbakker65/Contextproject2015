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
  final private Map<String, Table> tableMap;

  /**
   * Operation List.
   */
  final private List<Operation> opList;

  /**
   * Default constructor creating a script execution manager.
   *
   * @param tables
   *         list of tables to use with the operations.
   */
  public ScriptExecutionManager(final List<Table> tables) {
    this.tableMap = new LinkedHashMap<String, Table>();
    for (Table t : tables) {
      this.tableMap.put(t.getName(), t);
    }
    this.opList = new ArrayList<Operation>();
  }

  /**
   * Add a script file to the manager.
   *
   * @param filePath
   *         path to the script file.
   *
   * @throws ParseFailedException
   *         if the script file cannot be parsed.
   */
  public void addScriptFile(final String filePath) throws ParseFailedException {
    this.opList.addAll(OperationFactory.createOperationsFromFile(getTableMapAsList(), filePath));
  }

  /**
   * Add a script string to the manager.
   *
   * @param scriptString
   *         string containing the script.
   *
   * @throws ParseFailedException
   *         if the script file cannot be parsed.
   */
  public void addScriptString(final String scriptString) throws ParseFailedException {
    this.opList.addAll(OperationFactory.createOperationsFromString(getTableMapAsList(), scriptString));
  }

  /**
   * Execute all scripts in the manager.
   *
   * @return the list of tables containing the output.
   */
  public List<Table> executeAllScripts() {
    for (Operation o : this.opList) {
      createTableIfNotExists(o.getResultTableName());
      o.resetData(this.tableMap.get(o.getInputTableName()));
      o.execute();
      this.tableMap.remove(o.getResultTableName());
      this.tableMap.put(o.getResultTableName(), o.getResult());
    }
    return getTableMapAsList();
  }

  /**
   * Create a table for the given table name if it doesn't exist in the table map.
   *
   * @param resultTableName
   *         the name for the table to create.
   */
  private void createTableIfNotExists(final String resultTableName) {
    if (!this.tableMap.containsKey(resultTableName)) {
      final Table newTable = new Table();
      newTable.setName(resultTableName);
      this.tableMap.put(resultTableName, newTable);
    }
  }

  /**
   * Get the result tables of the script execution.
   *
   * @return list of result tables.
   */
  public List<Table> getResultDataTables() {
    return getTableMapAsList();
  }

  /**
   * Get the table map as a list of tables.
   *
   * @return the table map as list.
   */
  private List<Table> getTableMapAsList() {
    return new ArrayList<Table>(this.tableMap.values());
  }

}
