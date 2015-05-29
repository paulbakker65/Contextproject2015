package operations.patterns.condition;

import table.Record;

/**
 * Interface for representing a condition a record should satisfy.
 */
public interface RecordCondition {
  boolean matches(Record record);
}
