package net.tudelft.hi.e.export;

import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.input.Settings;

public final class SettingsBuilder {

  /**
   * Default hidden constructor.
   */
  private SettingsBuilder() {
  }

  /**
   * Generates a settings object for the given table.
   *
   * @param table
   *         The table to generate the settings for.
   * @param delimiter
   *         The delimiter to use when exporting to csv file.
   *
   * @return Returns a settings object for the table, if null then something went wrong.
   */
  public static Settings generateSettings(final Table table, final String delimiter,
          final int startline) {
    Settings settings = null;
    if (!table.isEmpty() && delimiter != null) {
      settings = new Settings();
      settings.setName(table.getName());
      settings.setDelimiter(delimiter);
      settings.setStartLine(startline);

      settings.setColumns(table.getColumns());
    }
    return settings;
  }
}
