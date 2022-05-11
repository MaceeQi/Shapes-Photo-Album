package model;

import java.awt.*;

/**
 * This interface contains all operations that all types of 2D shapes should support.
 */
public interface IShape {

  /**
   * Sets the coordinates of the shape's point in 2D space to the given x and y values.
   * @param x (int) x-coordinate for the shape's point.
   * @param y (int) y-coordinate for the shape's point.
   */
  void setPoint(int x, int y);

  /**
   * Sets the color of the shape based on the given r, g, b intensity values. Range from
   * 0-255, inclusive.
   * @param r (int) intensity of the color red (between 0 - 255, inclusive).
   * @param g (int) intensity of the color green (between 0 - 255, inclusive).
   * @param b (int) intensity of the color blue (between 0 - 255, inclusive).
   */
  void setColor(int r, int g, int b);

  /**
   * Sets the size of the shape to the given sizeX and sizeY. SizeX represents the size of the shape
   * horizontally, sizeY represents the size of the shape vertically.
   * @param sizeX (int) horizontal size of the shape.
   * @param sizeY (int) vertical size of the shape.
   */
  void setSize(int sizeX, int sizeY);

  /**
   * Returns the name of the shape.
   * @return (String) name of the shape.
   */
  String getName();

  /**
   * Returns the shape's point in 2D space.
   * @return (Point2D) the shape's point in 2D space.
   */
  Point2D getPoint();

  /**
   * Returns the color of the shape.
   * @return (Color) color of the shape.
   */
  Color getColor();


  /**
   * Makes a deep copy of the current shape.
   * @return (IShape) deep copy of the current shape.
   */
  IShape cloneDeep();

  /**
   * Returns a String representation of the shape that follows SVG markup.
   * @return (String) shape in SVG markup.
   */
  String toSVG();

  /**
   * Allows this shape to be drawable on components.
   * @param g (Graphics) the Graphics object for drawing.
   */
  void draw(Graphics g);
}
