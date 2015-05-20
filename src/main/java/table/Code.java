package table;

import java.util.ArrayList;
import java.util.List;

/**
 * Code which contains a list of tables. Each table represents an event.
 * @author paulbakker
 */
public class Code {

  /**
   * Each table represents an event.
   * The event is for example a single device measurement – single web entry event. 
   */
  private List<Table> events;
  /**
   * Name of the code.
   */
  private String name;
  
  /**
   * Constructor which sets the name and initializes the events list.
   * @param name
   */
  public Code(String name) {
    this.setName(name);
    this.events = new ArrayList<Table>();
  }
  
  /**
   * Adding an event to the list of events.
   * @param t
   */
  public void addEvent(Table t) {
    events.add(t);
  }
  
  /**
   * Getter of events.
   * @return
   */
  public List<Table> getEvents() {
    return events;
  }
  
  /**
   * Getter of the number of events.
   * @return
   */
  public int getFrequency() {
    return events.size();
  }

  /**
   * Getter of the name of the code.
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * Setter of the name of the code.
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }
  
}
