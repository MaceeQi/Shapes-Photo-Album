package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a snapshot of a shapes photo album. A Snapshot has a unique ID, timestamp,
 * and list of shapes. A snapshot is a "freeze frame" of shapes in a shapes photo album.
 */
public class Snapshot {
  private final String ID;
  private final String timestamp;
  private String description;
  private final List<IShape> shapes;

  /**
   * Constructs a Snapshot object instantiated to the given description and makes a deep copy of the
   * given list of shapes in their then current-location and state. If description is null, converts
   * it to an empty String. The ID and timestamp are based on when the Snapshot was created in time.
   * @param description (String) description associated to the Snapshot.
   * @param currentShapes (List</IShape>) shapes to be saved in Snapshot.
   * @throws IllegalArgumentException if list of shapes is null.
   */
  public Snapshot(String description, List<IShape> currentShapes) throws IllegalArgumentException {
    if (currentShapes == null) {
      throw new IllegalArgumentException("List of shapes cannot be null!");
    }

    // When null passed in as description, make it blank
    if (description == null) {
      description = "";
    }

    // Create ID and timestamp based on current moment in time
    LocalDateTime snapshotID = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    this.shapes = this.copyShapes(currentShapes); // deep copy of shapes
    this.description = description;
    this.ID = snapshotID.toString();
    this.timestamp = snapshotID.format(format);
  }

  /**
   * Helper method for the constructor. Returns a deep copy of the list of shapes given.
   * @param original (List</IShape>) list of shapes to copy.
   * @return (List</IShape>) deep copy of the given list of shapes.
   */
  private List<IShape> copyShapes(List<IShape> original) {
    // Make deep copy of given shapes - any future changes to shapes will not be reflected here
    List<IShape> copyShapes = new ArrayList<>();
    for (IShape each : original) {
      copyShapes.add(each.cloneDeep());
    }
    return copyShapes;
  }

  /**
   * Returns the snapshot ID.
   * @return (String) the ID of the snapshot.
   */
  public String getID() {
    return this.ID;
  }

  /**
   * Returns the description associated to the snapshot.
   * @return (String) description for the snapshot.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Returns the shapes saved to this snapshot.
   * @return (List</IShape>) list of shapes in their then-current locations and state at the time
   *                         of snapshot.
   */
  public List<IShape> getShapes() {
    return this.shapes;
  }

  /**
   * Returns a String representing the Snapshot. Contains snapshot ID, timestamp, description, and
   * shape information.
   * @return (String) represents Snapshot with ID, timestamp, description, and shape information.
   */
  @Override
  public String toString() {
    // Create one String containing all the shapes' information
    String shapeInformation = "";
    for (IShape each : this.shapes) {
      shapeInformation += each.toString() + "\n";
    }
    return "Snapshot ID: " + this.ID + "\nTimestamp: " + this.timestamp + "\nDescription: "
            + this.description + "\nShape Information:\n" + shapeInformation;
  }
}
