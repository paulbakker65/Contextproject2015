package net.tudelft.hi.e.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Code which contains a list of tables. Each table represents an event.
 */
public class Code implements Serializable {

  /**
   * Serial version.
   */
  private static final long serialVersionUID = 1L;

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

  /**
   * Equals method.
   * 
   * @param o the other object
   * @return true if the two objects are equal, false if they are not.
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    Code code = (Code) object;
    return Objects.equals(events, code.events) && Objects.equals(name, code.name);
  }

  /**
   * HashCode.
   * 
   * @return hashcode of this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(events, name);
  }
}
