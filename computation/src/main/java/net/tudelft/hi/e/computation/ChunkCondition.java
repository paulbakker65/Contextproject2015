package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.data.Value;

/**
 * The abstract class which we use to determine if a new chunk has to be created. The matches method
 * returns true if no new chunk is needed.
 *
 * @author unset
 *
 */
public abstract class ChunkCondition {

  public abstract boolean matches(Value recordValue, Value check);

}