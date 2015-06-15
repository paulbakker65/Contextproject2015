package operations;

import table.Record;
import table.Table;

import java.util.List;

public class CombineOperation extends Operation {

  private String column;
  private String value;
  private String value2;
  private String equalsCol;
  
  public CombineOperation(Table inputDataset, String column, String value, String value2, String equalsCol) {
    super(inputDataset);
    setOperationParameters(column, value, value2, equalsCol);
    operationParametersSet = true;
  }
  
  private void setOperationParameters(String column, String value, String value2, String equalsCol) {
    this.column = column;
    this.value = value;
    this.value2 = value2;
    this.equalsCol = equalsCol;
  }
  
  @Override
  public boolean execute() {    
    boolean valueFound = false;
    boolean value2Found = false;
    
    Record newRecord = new Record();
    for(Record record : inputData) {
       
        if(record.get(column).toString().equals(value)) {
          if(value2Found && newRecord.get(equalsCol).equals(record.get(equalsCol))) {
            for(String key: record.getKeysInOrder()) {
              newRecord.put(key + "_1", record.get(key));
            }
            resultData.add((Record) newRecord.clone());
            newRecord = new Record(); 
            valueFound = false;
            value2Found = false;
          } 
          else {
            newRecord.putAll(record);
            valueFound = true;
          }
        }
        
        
        if(record.get(column).toString().equals(value2)) {
          if(valueFound && newRecord.get(equalsCol).equals(record.get(equalsCol))) {
            for(String key: record.getKeysInOrder()) {
              newRecord.put(key + "_1", record.get(key));
            }
            resultData.add((Record) newRecord.clone());
            newRecord = new Record(); 
            valueFound = false;
            value2Found = false;
          }
          else {
            newRecord.putAll(record);
            value2Found = true;
          }
        }
    }
    
    return true;
    
  }
  
  public Table getResult() {
    return resultData;
  }

  @Override
  public void resetData(Table inputData) {
    this.inputData = inputData;
    this.resultData = new Table();
  }

}
