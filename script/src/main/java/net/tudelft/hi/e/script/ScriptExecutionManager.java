/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tudelft.hi.e.script;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.tudelft.hi.e.computation.Operation;
import net.tudelft.hi.e.data.Table;

/**
 * @author mawdegroot
 */
public class ScriptExecutionManager {

  private List<Table> tableList;

  private List<Operation> opList;

  public ScriptExecutionManager(List<Table> tables) {
    tableList = tables;
    opList = new ArrayList<Operation>();
  }

  public void addScriptFile(String filePath) {
    opList.
        addAll(OperationFactory.createOperationsFromFile(tableList, filePath));
  }

  public void addScriptString(String scriptString) {
    opList.addAll(OperationFactory.createOperationsFromString(tableList,
        scriptString));
  }

  public List<Table> executeAllScripts() {
    for (int i = 0; i < opList.size(); i++) {
      String inputTableName = opList.get(i).getInputTableName();
      String resultTableName = opList.get(i).getResultTableName();
      opList.get(i).resetData(getTableForTableName(inputTableName));
      opList.get(i).execute();
      setTableForTableName(resultTableName, opList.get(i).getResult());
    }
    return getResultDataTables();
  }

  private void createTableIfNotExists(String resultTableName) {
    if (!tableList.contains(resultTableName)) {
      Table newTable = new Table();
      newTable.setName(resultTableName);
    }
  }

  public List<Table> getResultDataTables() {
    return tableList;
  }

  private Table getTableForTableName(String tableName) {
    for (Table t : tableList) {
      if (t.getName().equals(tableName)) {
        return t;
      }
    }
    return null;
  }

  private void setTableForTableName(String tableName, Table newTable) {
    tableList.set(getTableIndexForTableName(tableName), newTable);
  }

  private int getTableIndexForTableName(String tableName) {
    for (int i = 0; i < tableList.size(); i++) {
      if (tableList.get(i).getName().equals(tableName)) {
        return i;
      }
    }
    return -1;
  }

}
