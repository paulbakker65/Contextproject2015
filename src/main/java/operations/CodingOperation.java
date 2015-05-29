package operations;

import operations.patterns.Pattern;
import table.Code;
import table.Table;

/**
 * The coding operation. Needs a pattern and name in order to function.
 * Pattern is made in PatternFactory.
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
   * @param inputDataset Table containing the input data.
   */
  public CodingOperation(Table inputDataset, Pattern pattern, String name) {
    super(inputDataset);
    this.resultData = (Table) inputDataset.clone();
    setOperationParameters(pattern, name);
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
   * @param pattern The pattern to use.
   * @param name The name for the coding.
   * @return true if set
   */
  public boolean setOperationParameters(Pattern pattern, String name) {
    if (pattern == null) {
      this.operationParametersSet = false;
    } else {
      this.pattern = pattern;
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
    if (!this.operationParametersSet) {
      return false;
    }
    
    int index = 0;
    Code code = new Code(name);
    
    while (index < inputData.size()) {
      Table records = new Table();
      if (pattern.findPattern(inputData, index, records)) {
        code.addEvent(records);
        index += records.size() - 1;
      }
      index++;
    }
    
    resultData.addCode(code);
    
    return true;
  }
}
