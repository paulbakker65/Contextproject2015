package export;

import input.Settings;

import table.Table;
import table.value.Column;

import java.util.ArrayList;

public class SettingsBuilder {
  
  /**
   * Generates a settings object for the given table.
   * @param table The table to generate the settings for.
   * @param delimiter The delimiter to use when exporting to csv file.
   * @return Returns a settings object for the table, if null then something went wrong.
   */
  public static Settings generateSettings(Table table, String delimiter, int startline) {
    if (table.isEmpty() || delimiter == null) {
      return null;
    }
    Settings settings = new Settings();
    settings.setName(table.getName());
    settings.setDelimiter(delimiter);
    settings.setStartLine(startline);
        
    settings.setColumns((ArrayList<Column>) table.getColumns());

    return settings;
  }
  
  private SettingsBuilder() { }

}
