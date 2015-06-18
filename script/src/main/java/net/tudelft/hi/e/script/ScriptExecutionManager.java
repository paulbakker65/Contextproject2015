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

/**
 * @author mawdegroot
 */
public class ScriptExecutionManager {

  private LinkedHashMap<String, Table> tableMap;

  private List<Operation> opList;

  public ScriptExecutionManager(List<Table> tables) {
    tableMap = new LinkedHashMap<String, Table>();
    for (Table t : tables) {
      tableMap.put(t.getName(), t);
    }
    opList = new ArrayList<Operation>();
  }

  public void addScriptFile(String filePath) throws ParseFailedException {
    opList.addAll(OperationFactory.createOperationsFromFile(getTableMapAsList(), filePath));
  }

  public void addScriptString(String scriptString) throws ParseFailedException {
    opList.addAll(OperationFactory.createOperationsFromString(getTableMapAsList(), scriptString));
  }

  public List<Table> executeAllScripts() {
    for (Operation o : opList) {
      createTableIfNotExists(o.getResultTableName());
      o.resetData(tableMap.get(o.getInputTableName()));
      o.execute();
      tableMap.replace(o.getResultTableName(), o.getResult());
    }
    return getTableMapAsList();
  }

  private void createTableIfNotExists(String resultTableName) {
    if (!tableMap.containsKey(resultTableName)) {
      Table newTable = new Table();
      newTable.setName(resultTableName);
      tableMap.put(resultTableName, newTable);
    }
  }

  public List<Table> getResultDataTables() {
    return getTableMapAsList();
  }

  private List<Table> getTableMapAsList() {
    return new ArrayList<Table>(tableMap.values());
  }

}
