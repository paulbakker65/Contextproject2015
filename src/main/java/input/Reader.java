package input;

import java.io.Closeable;
import java.io.IOException;

/**
 * Reader abstract class providing the framework for all filetype-specific Readers.
 *
 */
public abstract class Reader implements Closeable {
  protected String filepath = "";

  public Reader(final String filepath) {
    this.filepath = filepath;
  }

  public String getFilepath() {
    return filepath;
  }

  public abstract String[] readRow() throws IOException;

  @Override
  public String toString() {
    return "Reader [filepath=" + filepath + "]";
  }
}
