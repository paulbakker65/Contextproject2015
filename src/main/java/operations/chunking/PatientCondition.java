package operations.chunking;

import table.value.NumberValue;
import table.value.Value;

/**
 * Chunks on patient ID.
 */
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
