package operations.chunking;

import table.value.NumberValue;
import table.value.Value;

/**
 * Chunks on patient ID.
 */
public class PatientCondition extends ChunkCondition {

  @Override
  public boolean matches(final Value recordValue, final Value check) {
    final NumberValue current = (NumberValue) check;
    final NumberValue record = (NumberValue) recordValue;
    if (current.equals(record)) {
      return true;
    }
    return false;
  }

}
