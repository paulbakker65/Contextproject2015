package table;

import java.util.ArrayList;
import java.util.List;

/**
 * Code which contains a list of tables. Each table represents an event.
 */
public class Code {

  /**
   * Each table represents an event. The event is for example a single device measurement – single
   * web entry event.
   */
  private final List<Table> events;
  /**
   * Name of the code.
   */
  private String name;

  /**
   * Constructor which sets the name and initializes the events list.
   */
  public Code(final String name) {
    this.setName(name);
    this.events = new ArrayList<Table>();
  }

  /**
   * Adding an event to the list of events.
   */
  public void addEvent(final Table table) {
    events.add(table);
  }

  /**
   * Getter of events.
   */
  public List<Table> getEvents() {
    return events;
  }

  /**
   * Getter of the number of events.
   */
  public int getFrequency() {
    return events.size();
  }

  /**
   * Getter of the name of the code.
   */
  public String getName() {
    return name;
  }

  /**
   * Setter of the name of the code.
   */
  public void setName(final String name) {
    this.name = name;
  }

}
