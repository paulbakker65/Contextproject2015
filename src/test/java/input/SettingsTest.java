package input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import table.value.Column;
import table.value.StringColumn;

/**
 * Class for testing the Settings class.
 */
public class SettingsTest {

  @Test
  public void testEquals() {
    String otherDelimiter = ";";
    ArrayList<Column> columns = new ArrayList<Column>(Arrays.asList(new StringColumn("name")));
    
    final Settings settings = new Settings();
    final Settings settingsSame = new Settings();
    Settings settingsNotSameColumns = new Settings();
    settingsNotSameColumns.setColumns(columns);
    Settings settingsNotSameDelimiter = new Settings();
    settingsNotSameDelimiter.setDelimiter(otherDelimiter);
    Settings settingsNotSameStartLine = new Settings();
    settingsNotSameStartLine.setStartLine(2);
    final String otherClass = "";
    
    assertEquals(settings, settings);
    assertEquals(settings, settingsSame);
    assertNotEquals(settings, null);
    assertNotEquals(settings, settingsNotSameColumns);
    assertNotEquals(settings, settingsNotSameDelimiter);
    assertNotEquals(settings, settingsNotSameStartLine);
    assertNotEquals(settings, otherClass);
  }
  
  @Test
  public void testHashCode() {
    String delimiter = ";";
    ArrayList<Column> columns = new ArrayList<Column>(Arrays.asList(new StringColumn("name")));
    
    Settings settings = new Settings();
    settings.setColumns(columns);
    settings.setDelimiter(delimiter);
    settings.setStartLine(2);
    
    int expectedHashCode = 31 + columns.hashCode();
    expectedHashCode = expectedHashCode * 31 + delimiter.hashCode();
    expectedHashCode = expectedHashCode * 31 + 2;
    assertEquals(expectedHashCode, settings.hashCode());
        
  }

}
