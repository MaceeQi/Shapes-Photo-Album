package model;

import java.util.List;

/**
 * This interface contains all operations that all types of Shape Photo Albums should support.
 */
public interface IPhotoAlbum {

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
   * @return (IShape) desired shape type instantiated to the given attributes.
   */
  IShape createShape(String type, String name, int x, int y, int sizeX, int sizeY,
                     int r, int g, int b);

  /**
   * Adds given shape to the photo album. Duplicate shape names not allowed. If shape in photo
   * album already has the same name as the given shape, given shape is not added to the photo
   * album. If given shape is null, shape is not added.
   * @param shape (IShape) shape to be added to the photo album.
   */
  void addShape(IShape shape);

  /**
   * Removes the shape with given name from the photo album.
   * @param shapeName (String) name of the shape to be removed from the photo album.
   */
  void removeShape(String shapeName);

  /**
   * Changes the color of the shape that matches the given shape name to the given R,G,B color
   * intensity values. R,G,B intensity values must be between 0 and 255, inclusive.
   * @param shapeName (String) name of the shape.
   * @param r (int) intensity of the color red (between 0 and 255, inclusive).
   * @param g (int) intensity of the color green (between 0 and 255, inclusive).
   * @param b (int) intensity of the color blue (between 0 and 255, inclusive).
   */
  void changeShapeColor(String shapeName, int r, int g, int b);

  /**
   * Sets the given x- and y-coordinates to the shape that matches the given shape name.
   * @param shapeName (String) name of the shape.
   * @param x (int) x-coordinate.
   * @param y (int) y-coordinate.
   */
  void moveShape(String shapeName, int x, int y);

  /**
   * Sets the size of the shape that matches the given name to sizeX and sizeY. Desired shape must
   * only require two size dimensions. For rectangles, sizeX represents the width, sizeY
   * represents the height. For ovals, sizeX is X radius, sizeY is Y radius. Sizes must be non-negative.
   * @param shapeName (String) name of the shape.
   * @param sizeX (int) horizontal size (non-negative - width for rectangles, X radius for ovals).
   * @param sizeY (int) vertical size (non-negative - height for rectangles, Y radius for ovals).
   */
  void setShapeSize(String shapeName, int sizeX, int sizeY);

  /**
   * Resets the photo album to a blank slate. Clears any and all shapes and snapshots.
   */
  void reset();

  /**
   * Creates and saves a snapshot of the photo album in its current state. A snapshot is a
   * "freeze frame" of the model state, capturing the current locations and state of all shapes.
   * @param description (String) a comment/description associated with the snapshot.
   */
  void takeSnapshot(String description);

  /**
   * Returns a list of all snapshots taken.
   * @return (List</Snapshot>) list of all snapshots taken.
   */
  List<Snapshot> getSnapshots();

  /**
   * Returns a deep copied list of the shapes currently in the model.
   * @return (List</IShape>) deep copied list of shapes currently in the model.
   */
  List<IShape> getShapes();

  /**
   * Returns a String representation of the model. Includes description of the shapes that are part
   * of the photo album and their details, as well as descriptions of model state changes taken
   * with a snapshot as shapes are transformed/moved/removed.
   * @return (String) represents the model state changes.
   */
  String getModelState();
}
