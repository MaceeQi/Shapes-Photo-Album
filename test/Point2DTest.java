import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Point2D;

/**
 * A JUnit test class for the Point2D class.
 */
public class Point2DTest {
  private Point2D point1;
  private Point2D point2;
  private Point2D point3;
  private Point2D point4;

  /**
   * Instantiate valid Point2D objects for testing.
   */
  @Before
  public void setUp() {
    point1 = new Point2D(0, 0);
    point2 = new Point2D(-2, 45291);
    point3 = new Point2D(1, -7220129);
    point4 = new Point2D(200, -100);
  }

  /**
   * Positive testing for constructor.
   */
  @Test
  public void testValidPoint2D() {
    assertEquals("(0.0, 0.0)", point1.toString());
    assertEquals("(-2.0, 45291.0)", point2.toString());
    assertEquals("(1.0, -7220129.0)", point3.toString());
    assertEquals("(200.0, -100.0)", point4.toString());
  }

  /**
   * Positive testing for getX() method.
   */
  @Test
  public void testGetX() {
    assertEquals(0, point1.getX(), 0.0001);
    assertEquals(-2, point2.getX(), 0.0001);
    assertEquals(1, point3.getX(), 0.0001);
    assertEquals(200, point4.getX(), 0.0001);
  }

  /**
   * Positive testing for getY() method.
   */
  @Test
  public void testGetY() {
    assertEquals(0, point1.getY(), 0.0001);
    assertEquals(45291, point2.getY(), 0.0001);
    assertEquals(-7220129, point3.getY(), 0.0001);
    assertEquals(-100, point4.getY(), 0.0001);
  }

  /**
   * Positive testing for toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("(0.0, 0.0)", point1.toString());
    assertEquals("(-2.0, 45291.0)", point2.toString());
    assertEquals("(1.0, -7220129.0)", point3.toString());
    assertEquals("(200.0, -100.0)", point4.toString());
  }
}