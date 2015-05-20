package table;

import java.util.ArrayList;
import java.util.List;

/**
 * Code which contains a list of tables. Each table represents an event.
 * @author paulbakker
 */
public class Code {

  private List<Table> events;
  private String name;
  
  public Code(String name) {
    this.setName(name);
    this.events = new ArrayList<Table>();
  }
  
  public void addEvent(Table t) {
    events.add(t);
  }
  
  public List<Table> getEvents() {
    return events;
  }
  
  public int getFrequency() {
    return events.size();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
}
