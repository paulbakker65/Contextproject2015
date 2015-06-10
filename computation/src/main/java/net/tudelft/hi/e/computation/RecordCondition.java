package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.data.Record;

/**
 * Interface for representing a condition a record should satisfy.
 */
public interface RecordCondition {
  boolean matches(Record record);
}
