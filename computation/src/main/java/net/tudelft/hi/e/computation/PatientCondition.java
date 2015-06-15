package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Value;

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
