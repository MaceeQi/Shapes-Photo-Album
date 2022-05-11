import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import model.IPhotoAlbum;
import model.IShape;
import model.ShapesPhotoAlbumModel;
import model.Snapshot;

/**
 * A JUnit test class for the ShapesPhotoAlbumModel class, which implements IPhotoAlbum.
 */
public class IPhotoAlbumTest {
  private IPhotoAlbum album1;
  private IPhotoAlbum album2;
  private IPhotoAlbum album3;

  private IShape r1;
  private IShape r2;
  private IShape o1;
  private IShape o2;
  private IShape o3;

  /**
   * Instantiate valid objects for testing.
   */
  @Before
  public void setUp() {
    // Empty model
    album1 = new ShapesPhotoAlbumModel();

    // Contains shapes w/ no snapshots taken
    album2 = new ShapesPhotoAlbumModel();
    r1 = album2.createShape("RECTANGLE", "r1", -100, -401, 4,
            4, 0, 0, 0);
    o1 = album2.createShape("Oval", "o1", 100, 13, 500, 400,
            255, 255, 255);
    album2.addShape(r1);
    album2.addShape(o1);

    // Contains shapes + snapshots taken
    album3 = new ShapesPhotoAlbumModel();
    r2 = album2.createShape("rectangle", "r2", 0, 0, 0, 0, 128,
            64, 77);
    o2 = album1.createShape("OVAl", "o2", 1000, 1000, 1000, 1000,
            0, 255, 252);
    o3 = album3.createShape("oval", "o3", -2, -1000, 365, 5,
            82, 40, 0);
    album3.addShape(r2);
    album3.addShape(o2);
    album3.takeSnapshot("Set up");
    album3.addShape(o3);
  }

  /**
   * Positive testing for constructor.
   */
  @Test
  public void testPhotoAlbum() {
    assertEquals("[]", album1.getSnapshots().toString());
    assertEquals("[]", album1.getShapes().toString());

    assertEquals("[]", album2.getSnapshots().toString());
    assertEquals("[Name: r1\nType: rectangle\nMin corner: (-100.0, -401.0), Width: 4.0, "
            + "Height: 4.0, Color: (0,0,0), Name: o1\nType: oval\nCenter: (100.0, 13.0), "
            + "X radius: 500.0, Y radius: 400.0, Color: (255,255,255)]",
            album2.getShapes().toString());

    LocalDateTime timeForTimestamp = LocalDateTime.now();
    DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    List<Snapshot> snap3 = album3.getSnapshots();
    assertEquals("[Snapshot ID: " + snap3.get(0).getID() + "\nTimestamp: "
            + timeForTimestamp.format(timestampFormat) + "\nDescription: Set up\nShape Information:"
            +"\nName: r2\nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, Height: 0.0, Color: "
            + "(128,64,77)\nName: o2\nType: oval\nCenter: (1000.0, 1000.0), X radius: 1000.0, "
            + "Y radius: 1000.0, Color: (0,255,252)\n]", album3.getSnapshots().toString());
    assertEquals("[Name: r2\nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, Height: "
            + "0.0, Color: (128,64,77), Name: o2\nType: oval\nCenter: (1000.0, 1000.0), X "
            + "radius: 1000.0, Y radius: 1000.0, Color: (0,255,252), Name: o3\nType: oval\n"
            + "Center: (-2.0, -1000.0), X radius: 365.0, Y radius: 5.0, Color: (82,40,0)]",
            album3.getShapes().toString());
  }

  /**
   * Positive testing for createShape() method.
   */
  @Test
  public void testValidCreateShape() {
    // Create rectangle
    IShape r1 = album1.createShape("RECTANGLE", "r1", 0, 0, 12,
            347, 255, 0, 0);
    assertEquals("Name: r1\nType: rectangle\nMin corner: (0.0, 0.0), Width: 12.0, "
            + "Height: 347.0, Color: (255,0,0)", r1.toString());

    IShape r2 = album2.createShape("rectangle", "r2", -200, -200, 0,
            0, 255, 128, 54);
    assertEquals("Name: r2\nType: rectangle\nMin corner: (-200.0, -200.0), Width: 0.0, "
            + "Height: 0.0, Color: (255,128,54)", r2.toString());

    IShape r3 = album3.createShape("REctaNgLE", "r3", -10, 8,
            1930498124, 109123, 64, 64, 64);
    assertEquals("Name: r3\nType: rectangle\nMin corner: (-10.0, 8.0), Width: 1930498124.0, "
            + "Height: 109123.0, Color: (64,64,64)", r3.toString());

    // Create oval
    IShape o1 = album1.createShape("OVAL", "o1", 0, 0, 0,
            0, 0, 0, 1);
    assertEquals("Name: o1\nType: oval\nCenter: (0.0, 0.0), X radius: 0.0, "
            + "Y radius: 0.0, Color: (0,0,1)", o1.toString());

    IShape o2 = album2.createShape("oval", "o2", -123, -4467, 100,
            51, 128, 77, 26);
    assertEquals("Name: o2\nType: oval\nCenter: (-123.0, -4467.0), X radius: 100.0, "
            + "Y radius: 51.0, Color: (128,77,26)", o2.toString());

    IShape o3 = album3.createShape("OvAl", "o3", 10, 0,
            8459095, 290348, 255, 28, 0);
    assertEquals("Name: o3\nType: oval\nCenter: (10.0, 0.0), X radius: 8459095.0, "
            + "Y radius: 290348.0, Color: (255,28,0)", o3.toString());

    // Create non-existent shape type
    assertEquals(null, album1.createShape("rect", "shape", -100,
            -40, 92301, 89, 255, 120, 110));
    assertEquals(null, album2.createShape("circle", "c1", 100,
            10, 20, 0, 0, 0, 0));
    assertEquals(null, album3.createShape("TRIANGLE", "t1", 190,
            -37, 100, 400, 190, 138, 44));
  }


  /**
   * Negative testing for createShape() method. Null type.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCreateShape1() {
    album1.createShape(null, "invalid", 10, 101, 200, 2,
            255, 0, 110);
  }

  /**
   * Negative testing for createShape() method. Empty type.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCreateShape2() {
    album1.createShape("", "empty", -100, -200, 39, 90,
            0, 5, 23);
  }

  /**
   * Negative testing for createShape() method. Null name.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCreateShape3() {
    album1.createShape("rectangle", null, -40, 8888, 54, 90,
            1, 2, 3);
  }

  /**
   * Negative testing for createShape() method. Empty name.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCreateShape4() {
    album1.createShape("oval", "", 590, 0, 31, 23,
            38, 29, 14);
  }

  /**
   * Negative testing for createShape() method. Negative horizontal size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCreateShape5() {
    album1.createShape("rectangle", "r1", 15, -100, -1, 0,
            255, 255,255);
  }

  /**
   * Negative testing for createShape() method. Negative horizontal size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCreateShape6() {
    album1.createShape("oval", "o1", -1215, 100, -12193, 0,
            255, 148,0);
  }

  /**
   * Negative testing for createShape() method. Negative vertical size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCreateShape7() {
    album1.createShape("oval", "o", 15, -100, 0, -1,
            255, 255,255);
  }

  /**
   * Negative testing for createShape() method. Negative vertical size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCreateShape8() {
    album1.createShape("rectangle", "r", 77, -90, 32819, -120983,
            128, 190, 0);
  }

  /**
   * Negative testing for createShape() method. Color intensity less than 0.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCreateShape9() {
    album1.createShape("oval", "OVAL", 903, 106,
            10291023, 980, -1, 0, 202);
  }

  /**
   * Negative testing for createShape() method. Color intensity less than 0.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCreateShape10() {
    album1.createShape("rectangle", "R", -89, -200,
            0, 0, 13, -1, 220);
  }

  /**
   * Negative testing for createShape() method. Color intensity less than 0.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCreateShape11() {
    album1.createShape("oval", "shape", 16, 145,
            1, 0, 190, 177, -255);
  }

  /**
   * Negative testing for createShape() method. Color intensity greater than 255.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCreateShape12() {
    album1.createShape("oval", "circle", 16910, 1450,
            21, 77, 256, 13, 12);
  }

  /**
   * Negative testing for createShape() method. Color intensity greater than 255.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCreateShape13() {
   album1.createShape("rectangle", "shape name", 300, 100,
            0, 0, 129, 1000, 9);
  }

  /**
   * Negative testing for createShape() method. Color intensity greater than 255.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCreateShape14() {
    album1.createShape("oval", "my oval", 800, 226,
            50, 100, 67, 123, 1290237);
  }

  /**
   * Positive testing for addShape() method.
   */
  @Test
  public void testAddShape() {
    // Null shape not added
    IShape nullShape1 = null;
    album1.addShape(nullShape1);
    assertEquals("[]", album1.getShapes().toString());

    IShape nullShape2 = album2.createShape("circle", "circle type doesn't exist",
            101, 909, 23, 4, 255, 0, 125);
    album2.addShape(nullShape2);
    assertEquals("[Name: r1\nType: rectangle\nMin corner: (-100.0, -401.0), Width: 4.0, "
            + "Height: 4.0, Color: (0,0,0), Name: o1\nType: oval\nCenter: (100.0, 13.0), "
            + "X radius: 500.0, Y radius: 400.0, Color: (255,255,255)]",
            album2.getShapes().toString());

    // Shape with name that already exists in model is not added
    IShape sameName1 = album3.createShape("rectangle", "r2", 51, 133,
            8, 99, 0, 13, 120);
    album3.addShape(sameName1);
    assertEquals("[Name: r2\nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, Height: "
            + "0.0, Color: (128,64,77), Name: o2\nType: oval\nCenter: (1000.0, 1000.0), X "
            + "radius: 1000.0, Y radius: 1000.0, Color: (0,255,252), Name: o3\nType: oval\n"
            + "Center: (-2.0, -1000.0), X radius: 365.0, Y radius: 5.0, Color: (82,40,0)]",
            album3.getShapes().toString());

    IShape sameName2 = album2.createShape("OVAL", "r1", 294, 103,
            4895, 0, 210, 130, 120);
    album2.addShape(sameName2);
    assertEquals("[Name: r1\nType: rectangle\nMin corner: (-100.0, -401.0), Width: 4.0, "
            + "Height: 4.0, Color: (0,0,0), Name: o1\nType: oval\nCenter: (100.0, 13.0), "
            + "X radius: 500.0, Y radius: 400.0, Color: (255,255,255)]",
            album2.getShapes().toString());

    // Add valid shape
    IShape valid1 = album1.createShape("OVAL", " ", -1000, -1000,
            2381, 1, 102, 84, 153);
    album1.addShape(valid1);
    assertEquals("[Name:  \nType: oval\nCenter: (-1000.0, -1000.0), X radius: 2381.0, "
            + "Y radius: 1.0, Color: (102,84,153)]", album1.getShapes().toString());

    // Multiple adds; add/remove/add/reset/add again)
    IShape valid2 = album2.createShape("Rectangle", "valid1", 342, 1000,
            23984, 30, 13, 110, 12);
    IShape valid3 = album2.createShape("rectangle", "valid2", 0, 0,
            23, 100, 145, 231, 9);
    album2.addShape(valid2);
    album2.addShape(valid3);
    assertEquals("[Name: r1\nType: rectangle\nMin corner: (-100.0, -401.0), Width: 4.0, "
            + "Height: 4.0, Color: (0,0,0), Name: o1\nType: oval\nCenter: (100.0, 13.0), "
            + "X radius: 500.0, Y radius: 400.0, Color: (255,255,255), Name: valid1\nType: "
            + "rectangle\nMin corner: (342.0, 1000.0), Width: 23984.0, Height: 30.0, Color: "
            + "(13,110,12), Name: valid2\nType: rectangle\nMin corner: (0.0, 0.0), Width: "
            + "23.0, Height: 100.0, Color: (145,231,9)]", album2.getShapes().toString());
    album2.removeShape("valid1");
    assertEquals("[Name: r1\nType: rectangle\nMin corner: (-100.0, -401.0), Width: 4.0, "
            + "Height: 4.0, Color: (0,0,0), Name: o1\nType: oval\nCenter: (100.0, 13.0), "
            + "X radius: 500.0, Y radius: 400.0, Color: (255,255,255), Name: valid2\nType: "
            + "rectangle\nMin corner: (0.0, 0.0), Width: 23.0, Height: 100.0, Color: "
            + "(145,231,9)]", album2.getShapes().toString());
    album2.addShape(album2.createShape("oval", "add after remove", -16, 1,
            10, 5, 255, 25, 113));
    assertEquals("[Name: r1\nType: rectangle\nMin corner: (-100.0, -401.0), Width: 4.0, "
            + "Height: 4.0, Color: (0,0,0), Name: o1\nType: oval\nCenter: (100.0, 13.0), "
            + "X radius: 500.0, Y radius: 400.0, Color: (255,255,255), Name: valid2\nType: "
            + "rectangle\nMin corner: (0.0, 0.0), Width: 23.0, Height: 100.0, Color: "
            + "(145,231,9), Name: add after remove\nType: oval\nCenter: (-16.0, 1.0), X radius: "
            + "10.0, Y radius: 5.0, Color: (255,25,113)]", album2.getShapes().toString());
    album2.reset();
    album2.addShape(valid3);
    assertEquals("[Name: valid2\nType: rectangle\nMin corner: (0.0, 0.0), Width: 23.0, "
            + "Height: 100.0, Color: (145,231,9)]", album2.getShapes().toString());
  }

  /**
   * Positive testing for removeShape() method.
   */
  @Test
  public void testRemoveShape() {
    // Shape doesn't exist in model
    album1.removeShape(null);
    assertEquals("[]", album1.getShapes().toString());

    album2.removeShape("r2");
    assertEquals("[Name: r1\nType: rectangle\nMin corner: (-100.0, -401.0), Width: 4.0, "
            + "Height: 4.0, Color: (0,0,0), Name: o1\nType: oval\nCenter: (100.0, 13.0), "
            + "X radius: 500.0, Y radius: 400.0, Color: (255,255,255)]",
            album2.getShapes().toString());

    album3.removeShape("");
    assertEquals("[Name: r2\nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, Height: "
            + "0.0, Color: (128,64,77), Name: o2\nType: oval\nCenter: (1000.0, 1000.0), X "
            + "radius: 1000.0, Y radius: 1000.0, Color: (0,255,252), Name: o3\nType: oval\n"
            + "Center: (-2.0, -1000.0), X radius: 365.0, Y radius: 5.0, Color: (82,40,0)]",
            album3.getShapes().toString());

    // Shape exists in model
    album3.removeShape("o2");
    assertEquals("[Name: r2\nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, Height: "
            + "0.0, Color: (128,64,77), Name: o3\nType: oval\nCenter: (-2.0, -1000.0), "
            + "X radius: 365.0, Y radius: 5.0, Color: (82,40,0)]",
            album3.getShapes().toString());

    // Remove multiple, add, then remove again
    album2.removeShape("remove any one");
    album2.removeShape("r1");
    assertEquals("[Name: o1\nType: oval\nCenter: (100.0, 13.0), X radius: 500.0, Y radius: "
            + "400.0, Color: (255,255,255)]", album2.getShapes().toString());
    album2.removeShape("o1");
    assertEquals("[]", album2.getShapes().toString());
    album2.addShape(album2.createShape("OVAL", " ", 100, 200,
            60, 30, 0, 0, 255));
    assertEquals("[Name:  \nType: oval\nCenter: (100.0, 200.0), X radius: 60.0,"
            + " Y radius: 30.0, Color: (0,0,255)]", album2.getShapes().toString());
    album2.removeShape("  ");
    assertEquals("[Name:  \nType: oval\nCenter: (100.0, 200.0), X radius: 60.0,"
            + " Y radius: 30.0, Color: (0,0,255)]", album2.getShapes().toString());
    album2.removeShape(" ");
    assertEquals("[]", album2.getShapes().toString());
  }

  /**
   * Positive testing for changeShapeColor() method.
   */
  @Test
  public void testChangeShapeColor() {
    // Shape doesn't exist in model
    album1.changeShapeColor(null, 0, 0, 0);
    assertEquals("[]", album1.getShapes().toString());

    album2.changeShapeColor("r", 255, 14, 129);
    assertEquals("[Name: r1\nType: rectangle\nMin corner: (-100.0, -401.0), Width: 4.0, "
            + "Height: 4.0, Color: (0,0,0), Name: o1\nType: oval\nCenter: (100.0, 13.0), "
            + "X radius: 500.0, Y radius: 400.0, Color: (255,255,255)]",
            album2.getShapes().toString());

    album3.changeShapeColor("", 138, 13, 111);
    assertEquals("[Name: r2\nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, Height: "
            + "0.0, Color: (128,64,77), Name: o2\nType: oval\nCenter: (1000.0, 1000.0), X "
            + "radius: 1000.0, Y radius: 1000.0, Color: (0,255,252), Name: o3\nType: oval\n"
            + "Center: (-2.0, -1000.0), X radius: 365.0, Y radius: 5.0, Color: (82,40,0)]",
            album3.getShapes().toString());

    // Shape exists in model
    album2.changeShapeColor("r1", 130, 255, 220);
    assertEquals("[Name: r1\nType: rectangle\nMin corner: (-100.0, -401.0), Width: 4.0, "
            + "Height: 4.0, Color: (130,255,220), Name: o1\nType: oval\nCenter: (100.0, 13.0), "
            + "X radius: 500.0, Y radius: 400.0, Color: (255,255,255)]",
            album2.getShapes().toString());

    album3.changeShapeColor("o3", 0, 0, 0);
    assertEquals("[Name: r2\nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, Height: "
            + "0.0, Color: (128,64,77), Name: o2\nType: oval\nCenter: (1000.0, 1000.0), X "
            + "radius: 1000.0, Y radius: 1000.0, Color: (0,255,252), Name: o3\nType: oval\n"
            + "Center: (-2.0, -1000.0), X radius: 365.0, Y radius: 5.0, Color: (0,0,0)]",
            album3.getShapes().toString());

    album3.changeShapeColor("r2", 10, 98, 147);
    album3.changeShapeColor("o2", 255, 100, 78);
    assertEquals("[Name: r2\nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, Height: "
            + "0.0, Color: (10,98,147), Name: o2\nType: oval\nCenter: (1000.0, 1000.0), X "
            + "radius: 1000.0, Y radius: 1000.0, Color: (255,100,78), Name: o3\nType: oval\n"
            + "Center: (-2.0, -1000.0), X radius: 365.0, Y radius: 5.0, Color: (0,0,0)]",
            album3.getShapes().toString());
  }

  /**
   * Negative testing for changeShapeColor() method. Color intensity value less than 0.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidChangeShapeColor1() {
    album2.changeShapeColor("r1", -1, 0, 13);
  }

  /**
   * Negative testing for changeShapeColor() method. Color intensity value less than 0.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidChangeShapeColor2() {
    album2.changeShapeColor("r1", 6, -10, 32);
  }

  /**
   * Negative testing for changeShapeColor() method. Color intensity value less than 0.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidChangeShapeColor3() {
    album2.changeShapeColor("r1", 144, 100, -129301);
  }

  /**
   * Negative testing for changeShapeColor() method. Color intensity value greater than 255.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidChangeShapeColor4() {
    album3.changeShapeColor("o2", 256, 1, 122);
  }

  /**
   * Negative testing for changeShapeColor() method. Color intensity value greater than 255.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidChangeShapeColor5() {
    album3.changeShapeColor("o2", 255, 3, 256);
  }

  /**
   * Negative testing for changeShapeColor() method. Color intensity value greater than 255.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidChangeShapeColor6() {
    album3.changeShapeColor("o2", 3, 256, 100);
  }

  /**
   * Positive testing for moveShape() method.
   */
  @Test
  public void testMoveShape() {
    // Shape doesn't exist in model
    album1.moveShape("", 100, 0);
    assertEquals("[]", album1.getShapes().toString());

    album2.moveShape(null, 390, 30);
    assertEquals("[Name: r1\nType: rectangle\nMin corner: (-100.0, -401.0), Width: 4.0, "
            + "Height: 4.0, Color: (0,0,0), Name: o1\nType: oval\nCenter: (100.0, 13.0), "
            + "X radius: 500.0, Y radius: 400.0, Color: (255,255,255)]",
            album2.getShapes().toString());

    album3.moveShape("r", 23, 30);
    assertEquals("[Name: r2\nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, Height: "
            + "0.0, Color: (128,64,77), Name: o2\nType: oval\nCenter: (1000.0, 1000.0), X "
            + "radius: 1000.0, Y radius: 1000.0, Color: (0,255,252), Name: o3\nType: oval\n"
            + "Center: (-2.0, -1000.0), X radius: 365.0, Y radius: 5.0, Color: (82,40,0)]",
            album3.getShapes().toString());

    // Shape exists in model
    album2.moveShape("o1", 1000, 1234568);
    assertEquals("[Name: r1\nType: rectangle\nMin corner: (-100.0, -401.0), Width: 4.0, "
            + "Height: 4.0, Color: (0,0,0), Name: o1\nType: oval\nCenter: (1000.0, 1234568.0), "
            + "X radius: 500.0, Y radius: 400.0, Color: (255,255,255)]",
            album2.getShapes().toString());

    album3.moveShape(" ", -901, 323);
    album3.moveShape("o3", 0, 0);
    album3.moveShape("r2", 136, -898);
    assertEquals("[Name: r2\nType: rectangle\nMin corner: (136.0, -898.0), Width: 0.0, "
            + "Height: 0.0, Color: (128,64,77), Name: o2\nType: oval\nCenter: (1000.0, 1000.0), "
            + "X radius: 1000.0, Y radius: 1000.0, Color: (0,255,252), Name: o3\nType: oval\n"
            + "Center: (0.0, 0.0), X radius: 365.0, Y radius: 5.0, Color: (82,40,0)]",
            album3.getShapes().toString());
  }

  /**
   * Positive testing for setShapeSize() method.
   */
  @Test
  public void testSetShapeSize() {
    // Shape doesn't exist in model
    album1.setShapeSize("", 0, 1290);
    assertEquals("[]", album1.getShapes().toString());

    album2.setShapeSize(null, 390, 130);
    assertEquals("[Name: r1\nType: rectangle\nMin corner: (-100.0, -401.0), Width: 4.0, "
            + "Height: 4.0, Color: (0,0,0), Name: o1\nType: oval\nCenter: (100.0, 13.0), "
            + "X radius: 500.0, Y radius: 400.0, Color: (255,255,255)]",
            album2.getShapes().toString());

    album3.setShapeSize("o", 1023, 10);
    assertEquals("[Name: r2\nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, Height: "
            + "0.0, Color: (128,64,77), Name: o2\nType: oval\nCenter: (1000.0, 1000.0), X "
            + "radius: 1000.0, Y radius: 1000.0, Color: (0,255,252), Name: o3\nType: oval\n"
            + "Center: (-2.0, -1000.0), X radius: 365.0, Y radius: 5.0, Color: (82,40,0)]",
            album3.getShapes().toString());

    // Shape exists in model
    album2.setShapeSize("o1", 0, 0);
    assertEquals("[Name: r1\nType: rectangle\nMin corner: (-100.0, -401.0), Width: 4.0, "
            + "Height: 4.0, Color: (0,0,0), Name: o1\nType: oval\nCenter: (100.0, 13.0), "
            + "X radius: 0.0, Y radius: 0.0, Color: (255,255,255)]",
            album2.getShapes().toString());

    album3.setShapeSize("r2", 1239012, 129);
    album3.setShapeSize("r1", 9, 8);
    album3.setShapeSize("o2", 100, 350);
    assertEquals("[Name: r2\nType: rectangle\nMin corner: (0.0, 0.0), Width: 1239012.0, "
            + "Height: 129.0, Color: (128,64,77), Name: o2\nType: oval\nCenter: (1000.0, 1000.0),"
            + " X radius: 100.0, Y radius: 350.0, Color: (0,255,252), Name: o3\nType: oval\n"
            + "Center: (-2.0, -1000.0), X radius: 365.0, Y radius: 5.0, Color: (82,40,0)]",
            album3.getShapes().toString());
  }

  /**
   * Negative testing for setShapeSize() method. Negative size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetShapeSize1() {
    album2.setShapeSize("o1", -1, 105);
  }

  /**
   * Negative testing for setShapeSize() method. Negative size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetShapeSize2() {
    album2.setShapeSize("o1", 12938, -1);
  }

  /**
   * Negative testing for setShapeSize() method. Negative size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetShapeSize3() {
    album2.setShapeSize("o1", -800, 120938);
  }

  /**
   * Negative testing for setShapeSize() method. Negative size.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetShapeSize4() {
    album2.setShapeSize("o1", 300, -2910);
  }

  /**
   * Positive testing for reset() method.
   */
  @Test
  public void testReset() {
    // Reset already empty model
    album1.reset();
    assertEquals("[]", album1.getShapes().toString());
    assertEquals("[]", album1.getSnapshots().toString());

    // Reset model that consists of shapes
    album2.reset();
    assertEquals("[]", album2.getShapes().toString());
    assertEquals("[]", album2.getSnapshots().toString());

    // Reset model that consists of shapes and snapshots
    album3.reset();
    assertEquals("[]", album3.getShapes().toString());
    assertEquals("[]", album3.getSnapshots().toString());
  }

  /**
   * Positive testing for takeSnapshot() method.
   */
  @Test
  public void testTakeSnapshot() {
    // Create formatter and get current date/time for snapshot timestamp (helper for testing)
    LocalDateTime timeForTimestamp = LocalDateTime.now();
    DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    // Take snapshot of empty model state
    album1.takeSnapshot("Empty snap");
    List<Snapshot> snaps1 = album1.getSnapshots();
    assertEquals("[Snapshot ID: " + snaps1.get(0).getID() + "\nTimestamp: "
            + timeForTimestamp.format(timestampFormat) + "\nDescription: Empty snap\nShape "
            + "Information:\n]", album1.getSnapshots().toString());

    // Take snapshots of same model state
    album2.takeSnapshot("First selfie");
    album2.takeSnapshot("");
    List<Snapshot> snaps2 = album2.getSnapshots();
    assertEquals("[Snapshot ID: " + snaps2.get(0).getID() + "\nTimestamp: "
            + timeForTimestamp.format(timestampFormat) + "\nDescription: First selfie\nShape "
            + "Information:\nName: r1\nType: rectangle\nMin corner: (-100.0, -401.0), Width: 4.0, "
            + "Height: 4.0, Color: (0,0,0)\nName: o1\nType: oval\nCenter: (100.0, 13.0), X "
            + "radius: 500.0, Y radius: 400.0, Color: (255,255,255)\n, Snapshot ID: "
            + snaps2.get(1).getID() + "\nTimestamp: " + timeForTimestamp.format(timestampFormat)
            + "\nDescription: \nShape Information:\nName: r1\nType: rectangle\nMin corner: (-100.0,"
            + " -401.0), Width: 4.0, Height: 4.0, Color: (0,0,0)\nName: o1\nType: oval\n"
            + "Center: (100.0, 13.0), X radius: 500.0, Y radius: 400.0, Color: (255,255,255)\n]",
            album2.getSnapshots().toString());

    // Take snapshots of different model states
    album3.takeSnapshot("Second selfie");
    album3.setShapeSize("o2", 556, 556);
    album3.removeShape("r2");
    album3.takeSnapshot("Changed o2 shape size, removed shape r2");
    List<Snapshot> snaps3 = album3.getSnapshots();
    assertEquals("[Snapshot ID: " + snaps3.get(0).getID() + "\nTimestamp: "
            + timeForTimestamp.format(timestampFormat) + "\nDescription: Set up\nShape Information:"
            + "\nName: r2\nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, Height: 0.0, Color: "
            + "(128,64,77)\nName: o2\nType: oval\nCenter: (1000.0, 1000.0), X radius: 1000.0, "
            + "Y radius: 1000.0, Color: (0,255,252)\n, Snapshot ID: " + snaps3.get(1).getID()
            + "\nTimestamp: " + timeForTimestamp.format(timestampFormat) + "\nDescription: Second"
            + " selfie\nShape Information:\nName: r2\nType: rectangle\nMin corner: (0.0, 0.0), "
            + "Width: 0.0, Height: 0.0, Color: (128,64,77)\nName: o2\nType: oval\nCenter: "
            + "(1000.0, 1000.0), X radius: 1000.0, Y radius: 1000.0, Color: (0,255,252)\nName: "
            + "o3\nType: oval\nCenter: (-2.0, -1000.0), X radius: 365.0, Y radius: 5.0, Color: "
            + "(82,40,0)\n, Snapshot ID: " + snaps3.get(2).getID() + "\nTimestamp: "
            + timeForTimestamp.format(timestampFormat) + "\nDescription: Changed o2 shape size, "
            + "removed shape r2\nShape Information:\nName: o2\nType: oval\nCenter: (1000.0, 1000.0),"
            + " X radius: 556.0, Y radius: 556.0, Color: (0,255,252)\nName: o3\nType: oval\n"
            + "Center: (-2.0, -1000.0), X radius: 365.0, Y radius: 5.0, Color: (82,40,0)\n]",
            album3.getSnapshots().toString());
  }

  /**
   * Positive testing for getSnapshots() method.
   */
  @Test
  public void testGetSnapshots() {
    assertEquals("[]", album1.getSnapshots().toString());
    assertEquals("[]", album2.getSnapshots().toString());

    LocalDateTime timeForTimestamp = LocalDateTime.now();
    DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    List<Snapshot> snap3 = album3.getSnapshots();
    assertEquals("[Snapshot ID: " + snap3.get(0).getID() + "\nTimestamp: "
            + timeForTimestamp.format(timestampFormat) + "\nDescription: Set up\nShape Information:"
            +"\nName: r2\nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, Height: 0.0, Color: "
            + "(128,64,77)\nName: o2\nType: oval\nCenter: (1000.0, 1000.0), X radius: 1000.0, "
            + "Y radius: 1000.0, Color: (0,255,252)\n]", album3.getSnapshots().toString());
  }

  /**
   * Positive testing for getShapes() method.
   */
  @Test
  public void testGetShapes() {
    assertEquals("[]", album1.getShapes().toString());
    assertEquals("[Name: r1\nType: rectangle\nMin corner: (-100.0, -401.0), Width: 4.0, "
            + "Height: 4.0, Color: (0,0,0), Name: o1\nType: oval\nCenter: (100.0, 13.0), "
            + "X radius: 500.0, Y radius: 400.0, Color: (255,255,255)]",
            album2.getShapes().toString());
    assertEquals("[Name: r2\nType: rectangle\nMin corner: (0.0, 0.0), Width: 0.0, Height: "
            + "0.0, Color: (128,64,77), Name: o2\nType: oval\nCenter: (1000.0, 1000.0), X "
            + "radius: 1000.0, Y radius: 1000.0, Color: (0,255,252), Name: o3\nType: oval\n"
            + "Center: (-2.0, -1000.0), X radius: 365.0, Y radius: 5.0, Color: (82,40,0)]",
            album3.getShapes().toString());
  }

  /**
   * Positive testing for getModelState() method.
   */
  @Test
  public void testGetModelState() {
    // Create and add two shapes to model - take snapshot
    IShape R = album1.createShape("RECTANGLE", "R", 200, 200, 50,
            100, 0, 255, 101);
    IShape O = album1.createShape("oval", "O", -500, -100, 60,
            30, 99, 78, 6);
    album1.addShape(R);
    album1.addShape(O);
    album1.takeSnapshot("After first selfie");

    // Move rectangle and change rectangle size - take snapshot
    album1.moveShape("R", -100, -300);
    album1.setShapeSize("R", 25, 100);
    album1.takeSnapshot("2nd selfie");

    // Remove rectangle - take snapshot
    album1.removeShape("R");
    album1.takeSnapshot("Selfie after removing the rectangle from the picture");

    // Create formatter and get current date/time for snapshot timestamp (helper for testing)
    LocalDateTime timeForTimestamp = LocalDateTime.now();
    DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    // Get list of snapshot IDs (helper for testing)
    List<Snapshot> snapshots = album1.getSnapshots();
    List<String> snapshotIDs = snapshots.stream().map(snap ->
            snap.getID()).collect(Collectors.toList());

    assertEquals("Name: R\nType: rectangle\nMin corner: (200.0, 200.0), Width: 50.0, "
            + "Height: 100.0, Color: (0,255,101)\nName: O\nType: oval\nCenter: (-500.0, -100.0),"
            + " X radius: 60.0, Y radius: 30.0, Color: (99,78,6)\n\nName: R\nType: "
            + "rectangle\nMin corner: (-100.0, -300.0), Width: 25.0, Height: 100.0, Color: "
            + "(0,255,101)\nName: O\nType: oval\nCenter: (-500.0, -100.0), X radius: 60.0, "
            + "Y radius: 30.0, Color: (99,78,6)\n\nName: O\nType: oval\nCenter: "
            + "(-500.0, -100.0), X radius: 60.0, Y radius: "
            + "30.0, Color: (99,78,6)\n\nList of snapshots taken before reset: "
            + snapshotIDs.toString() + "\n\nPrinting Snapshots\nSnapshot ID: "
            + snapshots.get(0).getID() + "\nTimestamp: " + timeForTimestamp.format(timestampFormat)
            + "\nDescription: After first selfie\nShape Information:\nName: R\nType: rectangle\nMin"
            + " corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: (0,255,101)\nName: O"
            + "\nType: oval\nCenter: (-500.0, -100.0), X radius: 60.0, Y radius: 30.0, Color: (99,"
            + "78,6)\n\nSnapshot ID: " + snapshots.get(1).getID() + "\nTimestamp: "
            + timeForTimestamp.format(timestampFormat) + "\nDescription: 2nd selfie\nShape "
            + "Information:\nName: R\nType: rectangle\nMin corner: (-100.0, -300.0), Width: 25.0, "
            + "Height: 100.0, Color: (0,255,101)\nName: O\nType: oval\nCenter: (-500.0, -100.0), "
            + "X radius: 60.0, Y radius: 30.0, Color: (99,78,6)\n\nSnapshot ID: "
            + snapshots.get(2).getID() + "\nTimestamp: " + timeForTimestamp.format(timestampFormat)
            + "\nDescription: Selfie after removing the rectangle from the picture\nShape "
            + "Information:\nName: O\nType: oval\nCenter: (-500.0, -100.0), X radius: 60.0, Y radius:"
            + " 30.0, Color: (99,78,6)\n\n", album1.getModelState());
  }
}