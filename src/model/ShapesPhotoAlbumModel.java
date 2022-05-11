package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a shapes photo album model. A ShapesPhotoAlbumModel has shapes that can
 * be transformed, moved, and removed. The model also has snapshots which is a "freeze frame" of
 * the model state (shapes in their then-current locations and state) at a moment in time.
 */
public class ShapesPhotoAlbumModel implements IPhotoAlbum {
  private Map<String, IShape> currentShapes;
  private Map<String, Snapshot> snapshots;

  /**
   * Constructs an empty ShapesPhotoAlbumModel.
   */
  public ShapesPhotoAlbumModel() {
    this.currentShapes = new LinkedHashMap<>();
    this.snapshots = new LinkedHashMap<>();
  }

  /**
   * Creates a 2D shape (depending on the given type) that requires two parameters for a size.
   * Name, coordinates, size, and color intensity values will be instantiated to what is given.
   * Type and name cannot be null or empty, sizes cannot be negative, and color r,g,b intensity
   * values must be between 0 and 255, inclusive. Returns null if shape type does not exist.
   * @param type (String) type of 2D shape to be created (non-null and non-empty).
   * @param name (String) name of the shape (non-null and non-empty).
   * @param x (int) x-coordinate of the shape.
   * @param y (int) y-coordinate of the shape.
   * @param sizeX (int) horizontal size of the shape (width for rectangles, x radius for ovals).
   * @param sizeY (int) vertical size of the shape (height for rectangles, y radius for ovals).
   * @param r (int) intensity of the color red (between 0 and 255, inclusive).
   * @param g (int) intensity of the color green (between 0 and 255, inclusive).
   * @param b (int) intensity of the color blue (between 0 and 255, inclusive).
   * @return (IShape) desired shape type instantiated to the given attributes, or null if shape
   *                  type does not exist.
   * @throws IllegalArgumentException if type or name is null or empty, sizes are negative,
   *                                  or if color intensities not between 0 and 255.
   */
  @Override
  public IShape createShape(String type, String name, int x, int y, int sizeX,
                            int sizeY, int r, int g, int b) throws IllegalArgumentException {
    // Check valid type and name input
    if (type == null || type.equals("") || name == null || name.equals("")) {
      throw new IllegalArgumentException("Type and name cannot be null or empty.");
    }
    // Check valid size input
    if (sizeX < 0 || sizeY < 0) {
      throw new IllegalArgumentException("Sizes cannot be negative.");
    }

    // Create shapes depending on given type
    switch (type.toLowerCase()) {
      case "rectangle":
        return new Rectangle(name, x, y, sizeX, sizeY, r, g, b);
      case "oval":
        return new Oval(name, x, y, sizeX, sizeY, r, g, b);
      default:
        return null;
    }
  }

  @Override
  public void addShape(IShape shape) {
    // Null shape not added
    if (shape == null) {
      return;
    }
    // Shape with name that already exists in album is not added
    else if (this.currentShapes.containsKey(shape.getName())) {
      return;
    }
    // Add shape to album if name is unique and shape is not null
    else {
      this.currentShapes.put(shape.getName(), shape);
    }
  }

  @Override
  public void removeShape(String shapeName) {
    this.currentShapes.remove(shapeName);
  }

  @Override
  public void changeShapeColor(String shapeName, int r, int g, int b) {
    IShape shape = this.currentShapes.get(shapeName);

    // Ensure album contains desired shape - if not, no changes made
    if (shape == null) {
      return;
    } else {
      shape.setColor(r, g, b);
    }
  }

  @Override
  public void moveShape(String shapeName, int x, int y) {
    IShape shape = this.currentShapes.get(shapeName);

    // Ensure album contains desired shape - if not, no changes made
    if (shape == null) {
      return;
    } else {
      shape.setPoint(x, y);
    }
  }

  @Override
  public void setShapeSize(String shapeName, int sizeX, int sizeY) {
    IShape shape = this.currentShapes.get(shapeName);

    // Ensure album contains desired shape - if not, no changes made
    if (shape == null) {
      return;
    } else {
      shape.setSize(sizeX, sizeY);
    }
  }

  @Override
  public void reset() {
    this.currentShapes = new LinkedHashMap<>();
    this.snapshots = new LinkedHashMap<>();  }

  @Override
  public void takeSnapshot(String description) {
    // Convert map of shapes to just a list of shapes
    List<IShape> shapesList = new ArrayList<IShape>(this.currentShapes.values());

    // Create new snapshot and add to album
    Snapshot newSnap = new Snapshot(description, shapesList);
    this.snapshots.put(newSnap.getID(), newSnap);
  }

  @Override
  public List<Snapshot> getSnapshots() {
    // Convert map of snapshots to just a list of the snapshots taken
    List<Snapshot> snapshotsList = new ArrayList<Snapshot>(this.snapshots.values());
    return snapshotsList;
  }

  @Override
  public List<IShape> getShapes() {
    // Convert map of shapes to just a list of the shapes currently in model
    List<IShape> shapesList = new ArrayList<IShape>(this.currentShapes.values());

    // Make deep copy of shapes in model
    List<IShape> copyShapes = new ArrayList<>();
    for (IShape each : shapesList) {
      copyShapes.add(each.cloneDeep());
    }
    return copyShapes;
  }

  @Override
  public String getModelState() {
    // Create String of all shapes in snapshot history
    List<Snapshot> snapshotsList = this.getSnapshots();
    String snapshotShapes = "";
    for (Snapshot snap : snapshotsList) {
      for (IShape shape : snap.getShapes()) {
        snapshotShapes += shape.toString() + "\n";
      }
      snapshotShapes += "\n";
    }

    // Create String of all saved snapshots
    String snapshotHistory = "";
    for (Snapshot each : this.getSnapshots()) {
      snapshotHistory += each.toString() + "\n";
    }

    // Combine snapshot shapes and snapshot history into one String to represent model states
    String modelState = snapshotShapes + "List of snapshots taken before reset: "
            + this.snapshots.keySet() + "\n\nPrinting Snapshots\n" + snapshotHistory;
    return modelState;
  }
}
