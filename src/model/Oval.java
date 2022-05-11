package model;

import java.awt.*;

/**
 * This class represents a 2D oval. An oval has a name, x- and y-coordinates in 2D space, x radius,
 * y radius, and color (r,g,b).
 */
public class Oval extends AbstractShape {
  private int xRadius;
  private int yRadius;

  /**
   * Constructs an Oval instantiated to the given name, x- and y-coordinates in 2D space, x radius,
   * y radius, and color based on r,g,b intensity values. Name must be non-null and non-empty,
   * radius values must be non-negative, and color intensities between 0 - 255, inclusive.
   * @param name (String) name of the oval (non-null and non-empty).
   * @param x (int) x-coordinate at the center of the oval.
   * @param y (int) y-coordinate at the center of the oval.
   * @param xRadius (int) horizontal radius of the oval (non-negative).
   * @param yRadius (int) vertical radius of the oval (non-negative).
   * @param r (int) intensity of the color red (between 0 and 255, inclusive).
   * @param g (int) intensity of the color green (between 0 and 255, inclusive).
   * @param b (int) intensity of the color blue (between 0 and 255, inclusive).
   * @throws IllegalArgumentException if name is null or empty, either radius value is negative,
   *                                  or if color intensities not between 0 and 255.
   */
  public Oval(String name, int x, int y, int xRadius, int yRadius, int r, int g, int b)
          throws IllegalArgumentException {
    super(name, x, y, r, g, b);
    if (xRadius < 0 || yRadius < 0) {
      throw new IllegalArgumentException("X and Y radius values both must be non-negative!");
    }
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  /**
   * Sets the horizontal and vertical radius values for the oval. Both cannot be a negative value.
   * Horizontal radius is sizeX, vertical radius is sizeY.
   * @param sizeX (int) horizontal radius of the oval (non-negative).
   * @param sizeY (int) vertical radius of the oval (non-negative).
   * @throws IllegalArgumentException if either values are negative.
   */
  @Override
  public void setSize(int sizeX, int sizeY) throws IllegalArgumentException {
    if (sizeX < 0 || sizeY < 0) {
      throw new IllegalArgumentException("X and Y radius values must both be non-negative!");
    }
    this.xRadius = sizeX;
    this.yRadius = sizeY;
  }

  /**
   * Returns a String representing the Oval object. Contains its name, type (oval), center
   * coordinates, horizontal and vertical radius values, and color represented as (r, g, b)
   * intensity values.
   * @return (String) represents Oval with name, type, coordinates, size, and color.
   */
  @Override
  public String toString() {
    return "Name: " + this.getName() +"\nType: oval\nCenter: " + this.getPoint() + ", X radius: "
            + this.xRadius + ".0, Y radius: " + this.yRadius + ".0, " + "Color: ("
            + this.getColor().getRed() + "," + this.getColor().getGreen() + ","
            + this.getColor().getBlue() + ")";
  }

  @Override
  public IShape cloneDeep() {
    IShape clonedOval = new Oval(this.getName(), this.getPoint().getX(), this.getPoint().getY(),
            this.xRadius, this.yRadius, this.getColor().getRed(), this.getColor().getGreen(),
            this.getColor().getBlue());
    return clonedOval;
  }

  @Override
  public String toSVG() {
    return String.format("\t\t<ellipse id=\"%s\" cx=\"%d.0\" cy=\"%d.0\" rx=\"%d.0\" ry=\"%d.0\""
            + " fill=\"rgb(%d,%d,%d)\">\n\t\t</ellipse>\n", this.getName(), this.getPoint().getX(),
            this.getPoint().getY(), this.xRadius, this.yRadius, this.getColor().getRed(),
            this.getColor().getGreen(), this.getColor().getBlue());

  }

  @Override
  public void draw(Graphics g) {
    g.setColor(this.getColor());
    g.fillOval(this.getPoint().getX(), this.getPoint().getY(), this.xRadius, this.yRadius);
  }
}
