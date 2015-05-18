package operations.chunking;

import parsers.NumberValue;
import parsers.Value;

public class PatientCondition extends ChunkCondition {

  @Override
  public boolean matches(Value recordValue, Value check) {
    NumberValue current = (NumberValue) check;
    NumberValue record = (NumberValue) recordValue;
    if (current.equals(record)) {
      return true;
    }
    return false;
  }

}
