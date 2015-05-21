package operations;

import java.util.ListIterator;

import operations.coding.Pattern;
import table.Code;
import table.Record;
import table.Table;

/**
 * The coding operation. Needs a pattern and name in order to function.
 * Pattern is made in <INSERTCLASS> 
 * @author paulbakker
 *
 */
public class CodingOperation extends Operation {

  /**
   * The pattern to find in the input data.
   */
  private Pattern pattern;
  /**
   * The name of the pattern.
   */
  private String name;
  
  /**
   * Constructor only calls the super class.
   * @param inputDataset
   */
  public CodingOperation(Table inputDataset) {
    super(inputDataset);
    this.resultData = (Table) inputDataset.clone();
  }

  /**
   * Getter of the result data.
   */
  @Override
  public Table getResult() {
    return resultData;
  }

  /**
   * Setting the parameters of the operation.
   * @param p
   * @param name
   * @return true if set
   */
  public boolean setOperationParameters(Pattern p, String name) {
    if (p == null) {
      this.operationParametersSet = false;
    }
    else {
      this.pattern = p;
      this.name = name;
      this.operationParametersSet = true;
    }    
    
    return this.operationParametersSet;
  }

  /**
   * Create a code based on the pattern.
   */
  @Override
  public boolean execute() {
      ListIterator<Record> iterator = inputData.listIterator();
      Code code = new Code(name);
      
      while (iterator.hasNext()) {
        Table records = new Table();
        if (pattern.findPattern(iterator, records)) {
          code.addEvent(records);
        }
      }
      
      resultData.addCode(code);
      
      return true;
  }
  
  
  

}
