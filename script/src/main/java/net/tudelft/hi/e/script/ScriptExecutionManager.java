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
  private final Map<String, Table> tableMap;

  /**
   * Operation List.
   */
  private final List<Operation> opList;

  /**
   * Default constructor creating a script execution manager.
   *
   * @param tables
   *         list of tables to use with the operations.
   */
  public ScriptExecutionManager(final List<Table> tables) {
    this.tableMap = new LinkedHashMap<String, Table>();
    for (final Table table : tables) {
      this.tableMap.put(table.getName(), table);
    }
    this.opList = new ArrayList<>();
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
    this.opList
            .addAll(OperationFactory.createOperationsFromString(getTableMapAsList(), scriptString));
  }

  /**
   * Execute all scripts in the manager.
   *
   * @return the list of tables containing the output.
   */
  public List<Table> executeAllScripts() {
    for (final Operation operation : this.opList) {
      createTableIfNotExists(operation.getResultTableName());
      operation.resetData(this.tableMap.get(operation.getInputTableName()));
      operation.execute();
      this.tableMap.remove(operation.getResultTableName());
      this.tableMap.put(operation.getResultTableName(), operation.getResult());
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
