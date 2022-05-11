import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.IShape;
import model.Oval;
import model.Rectangle;

/**
 * A JUnit test class for the Rectangle and Oval classes (IShape).
 */
public class IShapeTest {
  private IShape r1;
  private IShape r2;
  private IShape r3;
  private IShape r4;

  private IShape o1;
  private IShape o2;
  private IShape o3;
  private IShape o4;

  /**
   * Instantiate valid IShape objects for testing.
   */
  @Before
  public void setUp() {
    // Rectangle
    r1 = new Rectangle(" ", 0, 0, 0, 0, 0, 0, 0);
    r2 = new Rectangle("rectangle", 0, -123456790, 0,
            190457, 255, 255, 255);
    r3 = new Rectangle("abc123...", 100978, 1, 25, 101,
            0, 0, 255);
    r4 = new Rectangle("$!0r1", -101, 500, 9855634, 5,
            128, 51, 26);

    // Oval
    o1 = new Oval(" ", 0, 0, 0, 0, 0, 0, 0);
    o2 = new Oval("oval", 2, 94562, 15, 900,
            128, 128, 128);
    o3 = new Oval("000", -4598, -5, 23456, 23,
            128, 51, 255);
    o4 = new Oval("*****", 200, -100, 30, 10, 77,
            255, 255);
  }

  /**
   * Positive testing for constructor.
   */
  @Test
  public void testValidShape() {
    // Rectangle shape
    assertEquals("Name:  \nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, "
            + "Height: 0.0, Color: (0,0,0)", r1.toString());
    assertEquals("Name: rectangle\nType: rectangle\nMin corner: (0.0, -123456790.0), "
            + "Width: 0.0, Height: 190457.0, Color: (255,255,255)", r2.toString());
    assertEquals("Name: abc123...\nType: rectangle\nMin corner: (100978.0, 1.0), "
            + "Width: 25.0, Height: 101.0, Color: (0,0,255)", r3.toString());
    assertEquals("Name: $!0r1\nType: rectangle\nMin corner: (-101.0, 500.0), "
            + "Width: 9855634.0, Height: 5.0, Color: (128,51,26)", r4.toString());

    // Oval shape
    assertEquals("Name:  \nType: oval\nCenter: (0.0, 0.0), X radius: 0.0, Y radius: 0.0, "
            + "Color: (0,0,0)", o1.toString());
    assertEquals("Name: oval\nType: oval\nCenter: (2.0, 94562.0), X radius: 15.0, "
            + "Y radius: 900.0, Color: (128,128,128)", o2.toString());
    assertEquals("Name: 000\nType: oval\nCenter: (-4598.0, -5.0), X radius: 23456.0, "
            + "Y radius: 23.0, Color: (128,51,255)", o3.toString());
    assertEquals("Name: *****\nType: oval\nCenter: (200.0, -100.0), X radius: 30.0, "
            + "Y radius: 10.0, Color: (77,255,255)", o4.toString());
  }

  /**
   * Negative testing for rectangle constructor. Null name.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShapeR1() {
    r1 = new Rectangle(null, 5, 358, 11, 10, 255, 0,
            0);
  }

  /**
   * Negative testing for rectangle constructor. Empty name.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShapeR2() {
    r1 = new Rectangle("", 10, 200, 1, 36, 240, 3,
            1);
  }


  /**
   * Negative testing for rectangle constructor. Negative width.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShapeR3() {
    r1 = new Rectangle("neg width!", -11, 22, -1, 23,
            1, 11, 1);
  }

  /**
   * Negative testing for rectangle constructor. Negative width.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShapeR4() {
    r1 = new Rectangle("larger neg width!", 901, 30000, -789, 100,
            5, 10, 84);
  }

  /**
   * Negative testing for rectangle constructor. Negative height.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShapeR5() {
    r1 = new Rectangle("__negative_height__", 0, 1, 23, -1,
            33, 21, 254);
  }

  /**
   * Negative testing for rectangle constructor. Negative height.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShapeR6() {
    r1 = new Rectangle("__negative_height__", 3, 105, 228, -599,
            255, 10, 90);
  }

  /**
   * Negative testing for rectangle constructor. Color intensity less than 0.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShapeR7() {
    r1 = new Rectangle("illegal", -10, 100, 20, 200,
            -1, 255, -255);
  }

  /**
   * Negative testing for rectangle constructor. Color intensity greater than 255.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShapeR8() {
    r1 = new Rectangle("too intense", 50, -25, 11, 1891,
            256, 255, 255);
  }

  /**
   * Negative testing for oval constructor. Null name.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShapeO1() {
    o1 = new Oval(null, 11, -100, 123457, 89,
            255, 125, 200);
  }

  /**
   * Negative testing for oval constructor. Empty name.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShapeO2() {
    o1 = new Oval("", 23, 1, 0, 11, 0, 0, 0);
  }

  /**
   * Negative testing for oval constructor. Negative x radius.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShapeO3() {
    o1 = new Oval("illegal x radius", 400, 100, -1, 460,
            1, 1, 1);
  }

  /**
   * Negative testing for oval constructor. Negative x radius.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShapeO4() {
    o1 = new Oval("illegal x radius", 10000, -100000, -988, 23,
            220, 200, 180);
  }

  /**
   * Negative testing for oval constructor. Negative y radius.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShapeO5() {
    o1 = new Oval("illegal y radius", -545, 1, 70, -1,
            180, 10, 20);
  }

  /**
   * Negative testing for oval constructor. Negative y radius.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShapeO6() {
    o1 = new Oval("illegal y radius", 222, 10001, 0, -1001,
            250, 128, 100);
  }

  /**
   * Negative testing for oval constructor. Color intensity less than 0.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShapeO7() {
    o1 = new Oval("invalid color intensity", 1083094, 436, 15, 1,
            21, 199, -1);
  }

  /**
   * Negative testing for oval constructor. Color intensity greater than 255.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShapeO8() {
    o1 = new Oval("intensity too high", 800, 300, 251, 138,
            256, 100, 150);
  }

  /**
   * Positive testing for setPoint() method.
   */
  @Test
  public void testValidSetPoint() {
    // Same point
    r1.setPoint(0, 0);
    assertEquals(0, r1.getPoint().getX());
    assertEquals(0, r1.getPoint().getX());
    r2.setPoint(0, -123456790);
    assertEquals(0, r2.getPoint().getX());
    assertEquals(-123456790, r2.getPoint().getY());

    // New point
    r1.setPoint(10, -981231);
    assertEquals(10, r1.getPoint().getX());
    assertEquals(-981231, r1.getPoint().getY());
    r2.setPoint(13484359, 1);
    assertEquals(13484359, r2.getPoint().getX());
    assertEquals(1, r2.getPoint().getY());
    o1.setPoint(-5, 1);
    assertEquals(-5, o1.getPoint().getX());
    assertEquals(1, o1.getPoint().getY());
    o2.setPoint(23, 88);
    assertEquals(23, o2.getPoint().getX());
    assertEquals(88, o2.getPoint().getY());

    // Set point multiple times
    r3.setPoint(-201, 390);
    assertEquals(-201, r3.getPoint().getX());
    assertEquals(390, r3.getPoint().getY());
    r3.setPoint(-2309, -1);
    assertEquals(-2309, r3.getPoint().getX());
    assertEquals(-1, r3.getPoint().getY());

    r4.setPoint(1, 123);
    assertEquals(1, r4.getPoint().getX());
    assertEquals(123, r4.getPoint().getY());
    r4.setPoint(5, 10);
    r4.setPoint(3290, 1);
    assertEquals(3290, r4.getPoint().getX());
    assertEquals(1, r4.getPoint().getY());

    o3.setPoint(-790, 300);
    assertEquals(-790, o3.getPoint().getX());
    assertEquals(300, o3.getPoint().getY());
    o3.setPoint(8460, 2);
    assertEquals(8460, o3.getPoint().getX());
    assertEquals(2, o3.getPoint().getY());

    o4.setPoint(1203986, 34052);
    assertEquals(1203986, o4.getPoint().getX());
    assertEquals(34052, o4.getPoint().getY());
    o4.setPoint(-10, -9);
    o4.setPoint(89, 200);
    assertEquals(89 ,o4.getPoint().getX());
    assertEquals(200, o4.getPoint().getY());
  }

  /**
   * Positive testing for setColor() method.
   */
  @Test
  public void testValidSetColor() {
    // Same color
    r1.setColor(0, 0, 0);
    assertEquals(0, r1.getColor().getRed());
    assertEquals(0, r1.getColor().getGreen());
    assertEquals(0, r1.getColor().getBlue());
    r2.setColor(255, 255, 255);
    assertEquals(255, r2.getColor().getRed());
    assertEquals(255, r2.getColor().getGreen());
    assertEquals(255, r2.getColor().getBlue());
    o1.setColor(0, 0, 0);
    assertEquals(0, o1.getColor().getRed());
    assertEquals(0, o1.getColor().getGreen());
    assertEquals(0, o1.getColor().getBlue());
    o2.setColor(128, 128, 128);
    assertEquals(128, o2.getColor().getRed());
    assertEquals(128, o2.getColor().getGreen());
    assertEquals(128, o2.getColor().getBlue());

    // New color at/near boundary
    r3.setColor(0, 0, 0);
    assertEquals(0, r3.getColor().getRed());
    assertEquals(0, r3.getColor().getGreen());
    assertEquals(0, r3.getColor().getBlue());
    r4.setColor(255, 255, 255);
    assertEquals(255, r4.getColor().getRed());
    assertEquals(255, r4.getColor().getGreen());
    assertEquals(255, r4.getColor().getBlue());
    o3.setColor(0, 0, 255);
    assertEquals(0, o3.getColor().getRed());
    assertEquals(0, o3.getColor().getGreen());
    assertEquals(255, o3.getColor().getBlue());
    o4.setColor(255, 0, 0);
    assertEquals(255, o4.getColor().getRed());
    assertEquals(0, o4.getColor().getGreen());
    assertEquals(0, o4.getColor().getBlue());

    // New color
    r1.setColor(10, 20, 33);
    assertEquals(10, r1.getColor().getRed());
    assertEquals(20, r1.getColor().getGreen());
    assertEquals(33, r1.getColor().getBlue());
    r2.setColor(254, 1, 120);
    assertEquals(254, r2.getColor().getRed());
    assertEquals(1, r2.getColor().getGreen());
    assertEquals(120, r2.getColor().getBlue());
    o1.setColor(79, 199, 201);
    assertEquals(79, o1.getColor().getRed());
    assertEquals(199, o1.getColor().getGreen());
    assertEquals(201, o1.getColor().getBlue());
    o2.setColor(57, 38, 209);
    assertEquals(57, o2.getColor().getRed());
    assertEquals(38, o2.getColor().getGreen());
    assertEquals(209, o2.getColor().getBlue());

    // Set color multiple times
    r3.setColor(1, 1, 1);
    assertEquals(1, r3.getColor().getRed());
    assertEquals(1, r3.getColor().getGreen());
    assertEquals(1, r3.getColor().getBlue());
    r3.setColor(200, 20, 255);
    assertEquals(200, r3.getColor().getRed());
    assertEquals(20, r3.getColor().getGreen());
    assertEquals(255, r3.getColor().getBlue());

    r4.setColor(0, 0, 0);
    r4.setColor(128, 127, 126);
    r4.setColor(255, 0, 130);
    assertEquals(255, r4.getColor().getRed());
    assertEquals(0, r4.getColor().getGreen());
    assertEquals(130, r4.getColor().getBlue());

    o3.setColor(77, 98, 120);
    assertEquals(77, o3.getColor().getRed());
    assertEquals(98, o3.getColor().getGreen());
    assertEquals(120, o3.getColor().getBlue());
    o3.setColor(128, 38, 0);
    assertEquals(128, o3.getColor().getRed());
    assertEquals(38, o3.getColor().getGreen());
    assertEquals(0, o3.getColor().getBlue());

    o4.setColor(0, 40, 150);
    o4.setColor(29, 21, 15);
    o4.setColor(255, 254, 253);
    assertEquals(255, o4.getColor().getRed());
    assertEquals(254, o4.getColor().getGreen());
    assertEquals(253, o4.getColor().getBlue());
  }

  /**
   * Negative testing for setColor() method. Color intensity less than 0.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetColor1() {
    r1.setColor(-1, 1, 155);
  }

  /**
   * Negative testing for setColor() method. Color intensity less than 0.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetColor2() {
    r1.setColor(20, -1, 0);
  }

  /**
   * Negative testing for setColor() method. Color intensity less than 0.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetColor3() {
    r1.setColor(155, 0, -1);
  }

  /**
   * Negative testing for setColor() method. Color intensity less than 0.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetColor4() {
    o1.setColor(-1, 10, 255);
  }

  /**
   * Negative testing for setColor() method. Color intensity less than 0.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetColor5() {
    o1.setColor(10, -1, 254);
  }

  /**
   * Negative testing for setColor() method. Color intensity less than 0.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetColor6() {
    o1.setColor(0, 0, -1);
  }

  /**
   * Negative testing for setColor() method. Color intensity greater than 255.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetColor7() {
    r1.setColor(135, 1, 256);
  }

  /**
   * Negative testing for setColor() method. Color intensity greater than 255.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetColor8() {
    r1.setColor(0, 256, 234);
  }

  /**
   * Negative testing for setColor() method. Color intensity greater than 255.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetColor9() {
    r1.setColor(256, 255, 254);
  }

  /**
   * Negative testing for setColor() method. Color intensity greater than 255.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetColor10() {
    o1.setColor(256, 0, 255);
  }

  /**
   * Negative testing for setColor() method. Color intensity greater than 255.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetColor11() {
    o1.setColor(125, 256, 0);
  }

  /**
   * Negative testing for setColor() method. Color intensity greater than 255.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetColor12() {
    o1.setColor(128, 1, 256);
  }

  /**
   * Positive testing for setSize() method.
   */
  @Test
  public void testValidSetSize() {
    // Same size
    r1.setSize(0, 0);
    assertEquals("Name:  \nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, "
            + "Height: 0.0, Color: (0,0,0)", r1.toString());
    r2.setSize(0, 190457);
    assertEquals("Name: rectangle\nType: rectangle\nMin corner: (0.0, -123456790.0), "
            + "Width: 0.0, Height: 190457.0, Color: (255,255,255)", r2.toString());
    o1.setSize(0, 0);
    assertEquals("Name:  \nType: oval\nCenter: (0.0, 0.0), X radius: 0.0, Y radius: 0.0, "
            + "Color: (0,0,0)", o1.toString());
    o2.setSize(15, 900);
    assertEquals("Name: oval\nType: oval\nCenter: (2.0, 94562.0), X radius: 15.0, "
            + "Y radius: 900.0, Color: (128,128,128)", o2.toString());

    // At/near boundary
    r3.setSize(0, 0);
    assertEquals("Name: abc123...\nType: rectangle\nMin corner: (100978.0, 1.0), "
            + "Width: 0.0, Height: 0.0, Color: (0,0,255)", r3.toString());
    o3.setSize(0, 0);
    assertEquals("Name: 000\nType: oval\nCenter: (-4598.0, -5.0), X radius: 0.0, "
            + "Y radius: 0.0, Color: (128,51,255)", o3.toString());

    // New size
    r1.setSize(1, 16);
    assertEquals("Name:  \nType: rectangle\nMin corner: (0.0, 0.0), Width: 1.0, "
            + "Height: 16.0, Color: (0,0,0)", r1.toString());
    r2.setSize(10240, 1982457);
    assertEquals("Name: rectangle\nType: rectangle\nMin corner: (0.0, -123456790.0), "
            + "Width: 10240.0, Height: 1982457.0, Color: (255,255,255)", r2.toString());
    o1.setSize(91, 8);
    assertEquals("Name:  \nType: oval\nCenter: (0.0, 0.0), X radius: 91.0, Y radius: 8.0, "
            + "Color: (0,0,0)", o1.toString());
    o2.setSize(2319, 10);
    assertEquals("Name: oval\nType: oval\nCenter: (2.0, 94562.0), X radius: 2319.0, "
            + "Y radius: 10.0, Color: (128,128,128)", o2.toString());

    // Set size multiple times
    r3.setSize(778, 31);
    r3.setSize(1, 0);
    assertEquals("Name: abc123...\nType: rectangle\nMin corner: (100978.0, 1.0), "
            + "Width: 1.0, Height: 0.0, Color: (0,0,255)", r3.toString());

    r4.setSize(90, 57);
    r4.setSize(5, 21093);
    assertEquals("Name: $!0r1\nType: rectangle\nMin corner: (-101.0, 500.0), "
            + "Width: 5.0, Height: 21093.0, Color: (128,51,26)", r4.toString());

    o3.setSize(84, 0);
    o3.setSize(347, 10);
    assertEquals("Name: 000\nType: oval\nCenter: (-4598.0, -5.0), X radius: 347.0, "
            + "Y radius: 10.0, Color: (128,51,255)", o3.toString());

    o4.setSize(34894, 8984723);
    o4.setSize(15, 19);
    assertEquals("Name: *****\nType: oval\nCenter: (200.0, -100.0), X radius: 15.0, "
            + "Y radius: 19.0, Color: (77,255,255)", o4.toString());
  }

  /**
   * Negative testing for setSize() method. Negative size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetSize1() {
    r1.setSize(-1, 12399);
  }

  /**
   * Negative testing for setSize() method. Negative size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetSize2() {
    r1.setSize(-16, 93);
  }

  /**
   * Negative testing for setSize() method. Negative size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetSize3() {
    r1.setSize(-130985, 0);
  }

  /**
   * Negative testing for setSize() method. Negative size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetSize4() {
    o1.setSize(-1, 129038);
  }

  /**
   * Negative testing for setSize() method. Negative size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetSize5() {
    o1.setSize(-8, 8);
  }

  /**
   * Negative testing for setSize() method. Negative size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetSize6() {
    o1.setSize(-12389, 11);
  }

  /**
   * Negative testing for setSize() method. Negative size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetSize7() {
    r1.setSize(1293, -1);
  }

  /**
   * Negative testing for setSize() method. Negative size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetSize8() {
    r1.setSize(10, -18);
  }

  /**
   * Negative testing for setSize() method. Negative size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetSize9() {
    r1.setSize(3, -12398);
  }

  /**
   * Negative testing for setSize() method. Negative size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetSize10() {
    o1.setSize(120, -1);
  }

  /**
   * Negative testing for setSize() method. Negative size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetSize11() {
    o1.setSize(1, -9);
  }

  /**
   * Negative testing for setSize() method. Negative size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetSize12() {
    o1.setSize(15, -19000);
  }

  /**
   * Positive testing for getName() method.
   */
  @Test
  public void testGetName() {
    assertEquals(" ", r1.getName());
    assertEquals("rectangle", r2.getName());
    assertEquals("abc123...", r3.getName());
    assertEquals("$!0r1", r4.getName());

    assertEquals(" ", o1.getName());
    assertEquals("oval", o2.getName());
    assertEquals("000", o3.getName());
    assertEquals("*****", o4.getName());
  }

  /**
   * Positive testing for getPoint() method.
   */
  @Test
  public void testGetPoint() {
    assertEquals(0, r1.getPoint().getX());
    assertEquals(0, r1.getPoint().getY());

    assertEquals(0, r2.getPoint().getX());
    assertEquals(-123456790, r2.getPoint().getY());

    assertEquals(100978, r3.getPoint().getX());
    assertEquals(1, r3.getPoint().getY());

    assertEquals(-101, r4.getPoint().getX());
    assertEquals(500, r4.getPoint().getY());

    assertEquals(0, o1.getPoint().getX());
    assertEquals(0, o1.getPoint().getY());

    assertEquals(2, o2.getPoint().getX());
    assertEquals(94562, o2.getPoint().getY());

    assertEquals(-4598, o3.getPoint().getX());
    assertEquals(-5, o3.getPoint().getY());

    assertEquals(200, o4.getPoint().getX());
    assertEquals(-100, o4.getPoint().getY());
  }

  /**
   * Positive testing for getColor() method.
   */
  @Test
  public void testGetColor() {
    assertEquals(0, r1.getColor().getRed());
    assertEquals(0, r1.getColor().getGreen());
    assertEquals(0, r1.getColor().getBlue());

    assertEquals(255, r2.getColor().getRed());
    assertEquals(255, r2.getColor().getGreen());
    assertEquals(255, r2.getColor().getBlue());

    assertEquals(0, r3.getColor().getRed());
    assertEquals(0, r3.getColor().getGreen());
    assertEquals(255, r3.getColor().getBlue());

    assertEquals(128, r4.getColor().getRed());
    assertEquals(51, r4.getColor().getGreen());
    assertEquals(26, r4.getColor().getBlue());

    assertEquals(0, o1.getColor().getRed());
    assertEquals(0, o1.getColor().getGreen());
    assertEquals(0, o1.getColor().getBlue());

    assertEquals(128, o2.getColor().getRed());
    assertEquals(128, o2.getColor().getGreen());
    assertEquals(128, o2.getColor().getBlue());

    assertEquals(128, o3.getColor().getRed());
    assertEquals(51, o3.getColor().getGreen());
    assertEquals(255, o3.getColor().getBlue());

    assertEquals(77, o4.getColor().getRed());
    assertEquals(255, o4.getColor().getGreen());
    assertEquals(255, o4.getColor().getBlue());
  }

  /**
   * Positive testing for cloneDeep() method.
   */
  @Test
  public void testCloneDeep() {
    // Check attributes are copied
    IShape r1Copy = r1.cloneDeep();
    assertEquals("Name:  \nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, "
            + "Height: 0.0, Color: (0,0,0)", r1Copy.toString());

    IShape r2Copy = r2.cloneDeep();
    assertEquals("Name: rectangle\nType: rectangle\nMin corner: (0.0, -123456790.0), "
            + "Width: 0.0, Height: 190457.0, Color: (255,255,255)", r2Copy.toString());

    IShape o1Copy = o1.cloneDeep();
    assertEquals("Name:  \nType: oval\nCenter: (0.0, 0.0), X radius: 0.0, Y radius: 0.0, "
            + "Color: (0,0,0)", o1Copy.toString());

    IShape o2Copy = o2.cloneDeep();
    assertEquals("Name: oval\nType: oval\nCenter: (2.0, 94562.0), X radius: 15.0, "
            + "Y radius: 900.0, Color: (128,128,128)", o2Copy.toString());

    // Check changes to original aren't reflected in copy
    IShape r3Copy = r3.cloneDeep();
    r3.setPoint(5, 1);
    r3.setColor(255, 102, 77);
    r3.setSize(10, 26);
    assertEquals("Name: abc123...\nType: rectangle\nMin corner: (100978.0, 1.0), "
            + "Width: 25.0, Height: 101.0, Color: (0,0,255)", r3Copy.toString());
    assertEquals("Name: abc123...\nType: rectangle\nMin corner: (5.0, 1.0), "
            + "Width: 10.0, Height: 26.0, Color: (255,102,77)", r3.toString());

    IShape r4Copy = r4.cloneDeep();
    r4.setPoint(100, -100);
    r4.setColor(255, 0, 0);
    r4.setSize(20, 180);
    assertEquals("Name: $!0r1\nType: rectangle\nMin corner: (-101.0, 500.0), "
            + "Width: 9855634.0, Height: 5.0, Color: (128,51,26)", r4Copy.toString());
    assertEquals("Name: $!0r1\nType: rectangle\nMin corner: (100.0, -100.0), "
            + "Width: 20.0, Height: 180.0, Color: (255,0,0)", r4.toString());

    IShape r5 = new Rectangle("null", 0, 0, 0, 0, 0, 0, 0);
    IShape r5Copy = r5.cloneDeep();
    r5.setPoint(89173, 0);
    r5.setColor(128, 77, 26);
    r5.setSize(200, 19);
    assertEquals("Name: null\nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, "
            + "Height: 0.0, Color: (0,0,0)", r5Copy.toString());
    assertEquals("Name: null\nType: rectangle\nMin corner: (89173.0, 0.0), Width: 200.0, "
            + "Height: 19.0, Color: (128,77,26)", r5.toString());

    IShape o3Copy = o3.cloneDeep();
    o3.setPoint(-200, -200);
    o3.setColor(255, 0, 0);
    o3.setSize(19028, 9119);
    assertEquals("Name: 000\nType: oval\nCenter: (-4598.0, -5.0), X radius: 23456.0, "
            + "Y radius: 23.0, Color: (128,51,255)", o3Copy.toString());
    assertEquals("Name: 000\nType: oval\nCenter: (-200.0, -200.0), X radius: 19028.0, "
            + "Y radius: 9119.0, Color: (255,0,0)", o3.toString());

    IShape o4Copy = o4.cloneDeep();
    o4.setPoint(1000, 16);
    o4.setColor(26, 128, 204);
    o4.setSize(1, 8);
    assertEquals("Name: *****\nType: oval\nCenter: (200.0, -100.0), X radius: 30.0, "
            + "Y radius: 10.0, Color: (77,255,255)", o4Copy.toString());
    assertEquals("Name: *****\nType: oval\nCenter: (1000.0, 16.0), X radius: 1.0, "
            + "Y radius: 8.0, Color: (26,128,204)", o4.toString());

    IShape o5 = new Oval("blank", 0, 0, 0, 0, 0, 255, 0);
    IShape o5Copy = o5.cloneDeep();
    o5.setPoint(-8, -10);
    o5.setColor(0, 102, 255);
    o5.setSize(138, 137);
    assertEquals("Name: blank\nType: oval\nCenter: (0.0, 0.0), X radius: 0.0, "
            + "Y radius: 0.0, Color: (0,255,0)", o5Copy.toString());
    assertEquals("Name: blank\nType: oval\nCenter: (-8.0, -10.0), X radius: 138.0, "
            + "Y radius: 137.0, Color: (0,102,255)", o5.toString());

    // Check changes to copy aren't reflected in original
    IShape r6 = new Rectangle("   rect", 0, -90000, 0, 0,
            255, 255, 255);
    IShape r6Copy = r6.cloneDeep();
    r6Copy.setPoint(10, 100);
    r6Copy.setColor(255, 0, 102);
    r6Copy.setSize(100, 100);
    assertEquals("Name:    rect\nType: rectangle\nMin corner: (0.0, -90000.0), Width: 0.0, "
            + "Height: 0.0, Color: (255,255,255)", r6.toString());
    assertEquals("Name:    rect\nType: rectangle\nMin corner: (10.0, 100.0), Width: 100.0, "
            + "Height: 100.0, Color: (255,0,102)", r6Copy.toString());

    IShape r7 = new Rectangle("[203.,#$", 349123, 6, 0, 0,
            153, 255, 26);
    IShape r7Copy = r7.cloneDeep();
    r7Copy.setPoint(901, 900);
    r7Copy.setColor(0, 0, 0);
    r7Copy.setSize(101, 1);
    r7.setSize(10, 5);
    assertEquals("Name: [203.,#$\nType: rectangle\nMin corner: (349123.0, 6.0), Width: 10.0,"
            + " Height: 5.0, Color: (153,255,26)", r7.toString());
    assertEquals("Name: [203.,#$\nType: rectangle\nMin corner: (901.0, 900.0), Width: 101.0,"
            + " Height: 1.0, Color: (0,0,0)", r7Copy.toString());

    IShape r8 = new Rectangle("r", 200, 500, 0, 0, 0, 255, 128);
    IShape r8Copy = r8.cloneDeep();
    r8Copy.setPoint(-23, -88);
    r8Copy.setColor(77, 77, 77);
    r8Copy.setSize(10, 89);
    assertEquals("Name: r\nType: rectangle\nMin corner: (200.0, 500.0), Width: 0.0, "
            + "Height: 0.0, Color: (0,255,128)", r8.toString());
    assertEquals("Name: r\nType: rectangle\nMin corner: (-23.0, -88.0), Width: 10.0, "
            + "Height: 89.0, Color: (77,77,77)", r8Copy.toString());

    IShape o6 = new Oval(" o ", 4, 10000, 0, 0, 128, 77, 77);
    IShape o6Copy = o6.cloneDeep();
    o6Copy.setPoint(0, 0);
    o6Copy.setColor(255, 0, 204);
    o6Copy.setSize(23901, 0);
    assertEquals("Name:  o \nType: oval\nCenter: (4.0, 10000.0), X radius: 0.0, "
            + "Y radius: 0.0, Color: (128,77,77)", o6.toString());
    assertEquals("Name:  o \nType: oval\nCenter: (0.0, 0.0), X radius: 23901.0, "
            + "Y radius: 0.0, Color: (255,0,204)", o6Copy.toString());

    IShape o7 = new Oval("^ sally", 3889, 20, 0, 0,
            255, 255, 255);
    IShape o7Copy = o7.cloneDeep();
    o7Copy.setPoint(398, 0);
    o7Copy.setColor(77, 255, 0);
    o7Copy.setSize(25, 10);
    o7.setPoint(-3, -4);
    assertEquals("Name: ^ sally\nType: oval\nCenter: (-3.0, -4.0), X radius: 0.0, "
            + "Y radius: 0.0, Color: (255,255,255)", o7.toString());
    assertEquals("Name: ^ sally\nType: oval\nCenter: (398.0, 0.0), X radius: 25.0, "
            + "Y radius: 10.0, Color: (77,255,0)", o7Copy.toString());

    IShape o8 = new Oval("#@ID5004ood", 545, 124, 0, 0,
            26, 230, 51);
    IShape o8Copy = o8.cloneDeep();
    o8Copy.setPoint(10, 1);
    o8Copy.setColor(0, 0, 0);
    o8Copy.setSize(100, 4);
    assertEquals("Name: #@ID5004ood\nType: oval\nCenter: (545.0, 124.0), X radius: 0.0, "
            + "Y radius: 0.0, Color: (26,230,51)", o8.toString());
    assertEquals("Name: #@ID5004ood\nType: oval\nCenter: (10.0, 1.0), X radius: 100.0, "
            + "Y radius: 4.0, Color: (0,0,0)", o8Copy.toString());
  }

  /**
   * Positive testing for toString() method.
   */
  @Test
  public void testToString() {
    // Rectangle shape
    assertEquals("Name:  \nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, "
            + "Height: 0.0, Color: (0,0,0)", r1.toString());
    assertEquals("Name: rectangle\nType: rectangle\nMin corner: (0.0, -123456790.0), "
            + "Width: 0.0, Height: 190457.0, Color: (255,255,255)", r2.toString());
    assertEquals("Name: abc123...\nType: rectangle\nMin corner: (100978.0, 1.0), "
            + "Width: 25.0, Height: 101.0, Color: (0,0,255)", r3.toString());
    assertEquals("Name: $!0r1\nType: rectangle\nMin corner: (-101.0, 500.0), "
            + "Width: 9855634.0, Height: 5.0, Color: (128,51,26)", r4.toString());

    // Oval shape
    assertEquals("Name:  \nType: oval\nCenter: (0.0, 0.0), X radius: 0.0, Y radius: 0.0, "
            + "Color: (0,0,0)", o1.toString());
    assertEquals("Name: oval\nType: oval\nCenter: (2.0, 94562.0), X radius: 15.0, "
            + "Y radius: 900.0, Color: (128,128,128)", o2.toString());
    assertEquals("Name: 000\nType: oval\nCenter: (-4598.0, -5.0), X radius: 23456.0, "
            + "Y radius: 23.0, Color: (128,51,255)", o3.toString());
    assertEquals("Name: *****\nType: oval\nCenter: (200.0, -100.0), X radius: 30.0, "
            + "Y radius: 10.0, Color: (77,255,255)", o4.toString());
  }

  /**
   * Positive testing for toSVG() method.
   */
  @Test
  public void testToSVG() {
    assertEquals("\t\t<rect id=\" \" x=\"0.0\" y=\"0.0\" width=\"0.0\" height=\"0.0\" "
            + "fill=\"rgb(0,0,0)\">\n\t\t</rect>\n", r1.toSVG());
    assertEquals("\t\t<rect id=\"rectangle\" x=\"0.0\" y=\"-123456790.0\" width=\"0.0\" "
            + "height=\"190457.0\" fill=\"rgb(255,255,255)\">\n\t\t</rect>\n", r2.toSVG());
    assertEquals("\t\t<rect id=\"abc123...\" x=\"100978.0\" y=\"1.0\" width=\"25.0\" "
            + "height=\"101.0\" fill=\"rgb(0,0,255)\">\n\t\t</rect>\n", r3.toSVG());
    assertEquals("\t\t<rect id=\"$!0r1\" x=\"-101.0\" y=\"500.0\" width=\"9855634.0\" "
            + "height=\"5.0\" fill=\"rgb(128,51,26)\">\n\t\t</rect>\n", r4.toSVG());

    assertEquals("\t\t<ellipse id=\" \" cx=\"0.0\" cy=\"0.0\" rx=\"0.0\" ry=\"0.0\" "
            + "fill=\"rgb(0,0,0)\">\n\t\t</ellipse>\n", o1.toSVG());
    assertEquals("\t\t<ellipse id=\"oval\" cx=\"2.0\" cy=\"94562.0\" rx=\"15.0\" "
            + "ry=\"900.0\" fill=\"rgb(128,128,128)\">\n\t\t</ellipse>\n", o2.toSVG());
    assertEquals("\t\t<ellipse id=\"000\" cx=\"-4598.0\" cy=\"-5.0\" rx=\"23456.0\" "
            + "ry=\"23.0\" fill=\"rgb(128,51,255)\">\n\t\t</ellipse>\n", o3.toSVG());
    assertEquals("\t\t<ellipse id=\"*****\" cx=\"200.0\" cy=\"-100.0\" rx=\"30.0\" "
            + "ry=\"10.0\" fill=\"rgb(77,255,255)\">\n\t\t</ellipse>\n", o4.toSVG());
  }
}