package net.tudelft.hi.e.computation;

import java.util.Objects;

import net.tudelft.hi.e.data.Table;

/**
 * Pattern for checking if a Pattern is not found.
 */
public class NotPattern extends Pattern {
	private Pattern invertPattern;
	
	/**
	 * Constructs a NotPattern without a next Pattern.
	 * 
	 * @param invertPattern
	 * 			the Pattern that should not be found.
	 */
	public NotPattern(Pattern invertPattern) {
		super();
		setInvertPattern(invertPattern);
	}
	
	/**
	 * Constructs a NotPattern with a next Pattern.
	 * 
	 * @param invertPattern
	 * 			the Pattern that should not be found.
	 * @param nextPattern 
	 * 			the next Pattern.
	 */
	public NotPattern(Pattern invertPattern, Pattern nextPattern) {
		super(nextPattern);
		setInvertPattern(invertPattern);
	}
	
	/**
	 * Returns the Pattern that should not be found.
	 * 
	 * @return the Pattern that should not be found.
	 */
	public Pattern getInvertPattern() {
		return invertPattern;
	}
	
	private void setInvertPattern(Pattern invertPattern) {
		if (invertPattern == null) {
			this.invertPattern = new NullPattern();
		} else {
			this.invertPattern = invertPattern;
		}
	}

	@Override
	public boolean findPattern(Table table, int fromIndex, Table records) {
		if (fromIndex >= table.size()) {
			return false;
		}
		
		if (invertPattern.findPattern(table, fromIndex, records)) {
			records.clear();
			return false;
		}

		return nextPattern.findPattern(table, fromIndex, records);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + invertPattern.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		NotPattern other = (NotPattern) obj;
		return Objects.equals(this.invertPattern, other.invertPattern);
	}

}
