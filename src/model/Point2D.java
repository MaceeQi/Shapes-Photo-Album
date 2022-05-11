package model;

/**
 * This class represents a point in 2D space. A Point2D has an x and a y which represents a location
 * in coordinate space.
 */
public class Point2D {
  private int x;
  private int y;

  /**
   * Constructs a Point2D object and initializes it to the given x, y values.
   * @param x (int) the x-coordinate of this point.
   * @param y (int) the y-coordinate of this point.
   */
  public Point2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the x-coordinate of this Point2D.
   * @return (int) x-coordinate of this Point2D.
   */
  public int getX() {
    return this.x;
  }

  /**
   * Returns the y-coordinate of this Point2D.
   * @return (int) y-coordinate of this Point2D.
   */
  public int getY() {
    return this.y;
  }

  /**
   * Returns a String representation of this Point2D. Formatted as: (x, y).
   * @return (String) represents Point2D coordinates surrounded by parentheses.
   */
  @Override
  public String toString() {
    return "(" + this.x + ".0, " + this.y + ".0)";
  }
}
