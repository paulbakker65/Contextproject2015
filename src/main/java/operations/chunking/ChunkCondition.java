package operations.chunking;

import parsers.Value;

public abstract class ChunkCondition {

	public abstract boolean matches(Value recordValue, Value check);
	
}
