package input;

import java.io.IOException;

public abstract class Reader {
	protected String filepath = "";
	
	public abstract String[] readRow() throws IOException;

	public String getFilepath() {
		return filepath;
	}

	@Override
	public String toString() {
		return "Reader [filepath=" + filepath + "]";
	}
}
