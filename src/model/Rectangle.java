package model;

import java.awt.*;

/**
 * This class represents a 2D rectangle. A Rectangle has a name, x- and y-coordinates in 2D space,
 * width, height, and color (r,g,b).
 */
public class Rectangle extends AbstractShape {
  private int width;
  private int height;

  /**
   * Constructs a Rectangle object instantiated to the given name, x- and y-coordinates in 2D space,
   * width, height, and color based on r,g,b intensity values. Name cannot be null or empty,
   * width and height must be non-negative, and color values must be between 0 and 255, inclusive.
   * @param name (String) name of the rectangle (non-null and non-empty).
   * @param x (int) x-coordinate of the lower-left corner of the rectangle.
   * @param y (int) y-coordinate of the lower-left corner of the rectangle.
   * @param width (int) width of the rectangle (non-negative).
   * @param height (int) height of the rectangle (non-negative).
   * @param r (int) intensity of the color red (between 0 and 255, inclusive).
   * @param g (int) intensity of the color green (between 0 and 255, inclusive).
   * @param b (int) intensity of the color blue (between 0 and 255, inclusive).
   * @throws IllegalArgumentException if name is null or empty, width or height is negative,
   *                                  or if color intensity values not between 0 and 255.
   */
  public Rectangle(String name, int x, int y, int width, int height, int r, int g,
                   int b) throws IllegalArgumentException {
    super(name, x, y, r, g, b);
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width and height of rectangle cannot be less than 0!");
    }
    this.width = width;
    this.height = height;
  }

  /**
   * Sets the width and height of the rectangle to what is given. Width and height must be
   * a non-negative number. Width is sizeX, height is sizeY.
   * @param sizeX (int) width of the rectangle (non-negative).
   * @param sizeY (int) height of the rectangle (non-negative).
   * @throws IllegalArgumentException if either sizeX or sizeY are negative.
   */
  @Override
  public void setSize(int sizeX, int sizeY) throws IllegalArgumentException {
    if (sizeX < 0 || sizeY < 0) {
      throw new IllegalArgumentException("Width and height of rectangle cannot be less than 0!");
    }
    this.width = sizeX;
    this.height = sizeY;
  }

  /**
   * Returns a String representing the Rectangle object. Contains its name, type (rectangle), x-
   * and y-coordinates of its lower left corner, width, height, and color represented as (r, g, b)
   * intensity values within range of 0-255, inclusive.
   * @return (String) represents Rectangle object with name, coordinates, size, and color.
   */
  @Override
  public String toString() {
    return String.format("Name: %s\nType: rectangle\nMin corner: %s, Width: %d.0, Height: %d.0, "
            + "Color: (%d,%d,%d)", this.getName(), this.getPoint(), this.width, this.height,
            this.getColor().getRed(), this.getColor().getGreen(), this.getColor().getBlue());
  }


  @Override
  public IShape cloneDeep() {
    IShape clonedRectangle = new Rectangle(this.getName(), this.getPoint().getX(),
            this.getPoint().getY(), this.width, this.height, this.getColor().getRed(),
            this.getColor().getGreen(), this.getColor().getBlue());
    return clonedRectangle;
  }

  @Override
  public String toSVG() {
    return String.format("\t\t<rect id=\"%s\" x=\"%d.0\" y=\"%d.0\" width=\"%d.0\" height=\"%d.0\""
            + " fill=\"rgb(%d,%d,%d)\">\n\t\t</rect>\n", this.getName(), this.getPoint().getX(),
            this.getPoint().getY(), this.width, this.height, this.getColor().getRed(),
            this.getColor().getGreen(), this.getColor().getBlue());
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(this.getColor());
    g.fillRect(this.getPoint().getX(), this.getPoint().getY(), this.width, this.height);
  }
}
