package model;

import java.time.LocalDate;
import java.util.List;

/**
 * The Snapshot class creates "selfies" for the photoAlbum. The Snapshot depicts the state of the
 * album at the time the selfie is taken. It creates a Unique ID and time stamp and maintains a
 * record till reset() is called on.
 */
public class Snapshot {

  private String description;
  private final List<IShape> shapes;
  private String ID;
  private String timeStamp;

  /**
   * Instantiates a new Snapshot.
   *
   * @param description the description
   * @param shapes      the shapes
   */
  public Snapshot(String description, List<IShape> shapes) {
    LocalDate date = LocalDate.now();
    String day = String.valueOf(date.getDayOfMonth());
    String month = String.valueOf(date.getMonthValue());
    String year = String.valueOf(date.getYear());
    String time = String.valueOf((java.time.LocalTime.now()));

    this.description = description;
    this.shapes = shapes;
    this.ID = year + "-" + month + "-" + day + "T" + time;
    this.timeStamp = day + "-" + month + "-" + year + " " + time;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public String getID() {
    return this.ID;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets shapes in snapshot.
   *
   * @return the shapes in snapshot
   */
  public List<IShape> getShapesInSnapshot() {
    return this.shapes;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("Snapshot ID: " + this.ID + "\nTimestamp: "
            + this.timeStamp + "\nDescription: " + this.description + "\nShape Information: \n");
    for (IShape s : this.shapes) {
      result.append(s.toString()).append("\n");
    }
    return String.valueOf(result);
  }

}
