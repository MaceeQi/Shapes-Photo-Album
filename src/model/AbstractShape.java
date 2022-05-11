package model;

import java.awt.*;

/**
 * This abstract class contains implementation for methods from the IShape interface that are
 * common to all 2D shapes.
 */
public abstract class AbstractShape implements IShape {
  private final String name;
  private Point2D point;
  private Color color;

  /**
   * Constructs a 2D shape instantiated to the given name, x- and y-coordinates, and color intensity
   * values. Name must not be null or empty, and color values must be between 0 and 255, inclusive.
   * @param name (String) name of the shape (non-null and non-empty).
   * @param x (int) x-coordinate of shape in 2D space.
   * @param y (int) y-coordinate of shape in 2D space.
   * @param r (int) intensity of the color red (between 0 and 255, inclusive).
   * @param g (int) intensity of the color green (between 0 and 255, inclusive).
   * @param b (int) intensity of the color blue (between 0 and 255, inclusive).
   * @throws IllegalArgumentException if name is null or empty, or if color intensity values
   *                                  not between 0 and 255.
   */
  public AbstractShape(String name, int x, int y, int r, int g, int b)
          throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name must not be null or empty!");
    }
    this.name = name;
    this.point = new Point2D(x, y);
    this.color = new Color(r, g, b);
  }

  /**
   * Sets the coordinates of the shape's point in 2D space to the given x and y values.
   * @param x (int) x-coordinate for the shape's point.
   * @param y (int) y-coordinate for the shape's point.
   */
  @Override
  public void setPoint(int x, int y) {
    this.point = new Point2D(x, y);
  }

  /**
   * Sets the color of the shape based on the given r, g, b intensity values. Range between
   * 0-255, inclusive.
   * @param r (int) intensity of the color red (between 0 - 255, inclusive).
   * @param g (int) intensity of the color green (between 0 - 255, inclusive).
   * @param b (int) intensity of the color blue (between 0 - 255, inclusive).
   * @throws IllegalArgumentException if any of the values are less than 0 or greater than 255.
   */
  @Override
  public void setColor(int r, int g, int b) throws IllegalArgumentException {
    this.color = new Color(r, g, b);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Point2D getPoint() {
    return this.point;
  }

  @Override
  public Color getColor() {
    return this.color;
  }
}
