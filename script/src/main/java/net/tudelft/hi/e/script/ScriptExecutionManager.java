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
 *
 * @author mawdegroot
 */
public class ScriptExecutionManager {

  private Map<String, Table> tableMap;

  private List<Table> tableList;

  private List<Operation> opList;

  public ScriptExecutionManager(List<Table> tables) {
    tableMap = new LinkedHashMap<String, Table>();
    for (Table t : tables) {
      tableMap.put(t.getName(), t);
    }
    tableList = tables;
    opList = new ArrayList<Operation>();
  }

  public void addScriptFile(String filePath) {
    updateTableList();
    opList.addAll(OperationFactory.createOperationsFromFile(tableList, filePath));
  }

  public void addScriptString(String scriptString) {
    updateTableList();
    opList.addAll(OperationFactory.createOperationsFromString(tableList, scriptString));
  }

  public List<Table> executeAllScripts() {
    for (int i = 0; i < opList.size(); i++) {
      opList.get(i).execute();
      createTableIfNotExists(opList.get(i).getResultTableName());
      Table operationResultTable = tableMap.get(opList.get(i).getResultTableName());

      operationResultTable.clear();
      operationResultTable.addAll(opList.get(i).getResult());
    }
    return tableList;
  }

  private void createTableIfNotExists(String resultTableName) {
    if (!tableMap.containsKey(resultTableName)) {
      Table newTable = new Table();
      newTable.setName(resultTableName);
      updateTableList();
    }
  }

  public List<Table> getResultDataTables() {
    updateTableList();
    return tableList;
  }

  private void updateTableList() {
    tableList.clear();
    tableList.addAll(tableMap.values());
  }

}
