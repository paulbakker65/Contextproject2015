package input;

import java.io.IOException;

public abstract class Reader {
	protected String filepath = "";
	
	abstract public String[] readRow() throws IOException;

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public String toString() {
		return "Reader [filepath=" + filepath + "]";
	}
}
