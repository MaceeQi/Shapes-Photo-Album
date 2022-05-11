import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.IShape;
import model.Oval;
import model.Rectangle;
import model.Snapshot;

/**
 * A JUnit test class for the Snapshot class.
 */
public class SnapshotTest {
  private Snapshot snap1;
  private Snapshot snap2;
  private Snapshot snap3;

  private IShape r1;
  private IShape r2;
  private IShape o1;

  private List<IShape> shapes1;
  private List<IShape> shapes2;
  private List<IShape> shapes3;

  /**
   * Instantiate valid objects for testing Snapshot class.
   */
  @Before
  public void setUp() {
    // Empty description; empty list of shapes
    shapes1 = new ArrayList<>();
    snap1 = new Snapshot("", shapes1);

    // Null description; list with one shape
    shapes2 = new LinkedList<>();
    r1 = new Rectangle("r1", -101, -101, 20, 10,
            255, 0, 0);
    shapes2.add(r1);
    snap2 = new Snapshot(null, shapes2);

    // Description; list with multiple shapes
    shapes3 = new ArrayList<>();
    o1 = new Oval("o1", 500, 496, 40, 89,
            0, 0, 255);
    r2 = new Rectangle("r2", 1, 5, 0, 0,
            255, 255, 120);
    shapes3.add(o1);
    shapes3.add(r2);
    snap3 = new Snapshot("Third selfie", shapes3);
  }

  /**
   * Positive testing for constructor.
   */
  @Test
  public void testValidSnapshot() {
    // Format to convert String back to LocalDateTime format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");

    // Check snapshot IDs - convert ID back to LocalDateTime and ensure ID is before or equal
    // to current time
    LocalDateTime time = LocalDateTime.now();

    String stringID1 = snap1.getID();
    LocalDateTime snapID1 = LocalDateTime.parse(stringID1, formatter);
    assertTrue(snapID1.isBefore(time) || snapID1.isEqual(time));

    String stringID2 = snap2.getID();
    LocalDateTime snapID2 = LocalDateTime.parse(stringID2, formatter);
    assertTrue(snapID2.isBefore(time) || snapID2.isEqual(time));

    String stringID3 = snap3.getID();
    LocalDateTime snapID3 = LocalDateTime.parse(stringID3, formatter);
    assertTrue(snapID3.isBefore(time) || snapID3.isEqual(time));

    // Check remaining attributes are correct when Snapshot was created
    DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    assertEquals("Snapshot ID: " + stringID1 + "\nTimestamp: "
            + LocalDateTime.now().format(timestampFormat) + "\nDescription: \nShape Information:\n",
            snap1.toString());
    assertEquals("Snapshot ID: " + stringID2 + "\nTimestamp: "
            + LocalDateTime.now().format(timestampFormat) + "\nDescription: \nShape Information:\n"
            + "Name: r1\nType: rectangle\nMin corner: (-101.0, -101.0), Width: 20.0, Height: 10.0, "
            + "Color: (255,0,0)\n", snap2.toString());
    assertEquals("Snapshot ID: " + stringID3 + "\nTimestamp: "
            + LocalDateTime.now().format(timestampFormat) + "\nDescription: Third selfie\n"
            + "Shape Information:\nName: o1\nType: oval\nCenter: (500.0, 496.0), X radius: 40.0, "
            + "Y radius: 89.0, Color: (0,0,255)\nName: r2\nType: rectangle\nMin corner: "
            + "(1.0, 5.0), Width: 0.0, Height: 0.0, Color: (255,255,120)\n", snap3.toString());
  }

  /**
   * Negative testing for constructor. List of shapes is null.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSnapshot() {
    snap1 = new Snapshot("null list", null);
  }

  /**
   * Positive testing for getID() method. Convert ID back to LocalDateTime and ensure ID is before
   * or equal to current time.
   */
  @Test
  public void testGetID() {
    // Format to convert String ID back to LocalDateTime format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");

    // Get current time
    LocalDateTime time = LocalDateTime.now();

    // Ensure IDs are all before or equal to current time
    String stringID1 = snap1.getID();
    LocalDateTime snapID1 = LocalDateTime.parse(stringID1, formatter);
    assertTrue(snapID1.isBefore(time) || snapID1.isEqual(time));

    String stringID2 = snap2.getID();
    LocalDateTime snapID2 = LocalDateTime.parse(stringID2, formatter);
    assertTrue(snapID2.isBefore(time) || snapID2.isEqual(time));

    String stringID3 = snap3.getID();
    LocalDateTime snapID3 = LocalDateTime.parse(stringID3, formatter);
    assertTrue(snapID3.isBefore(time) || snapID3.isEqual(time));
  }

  /**
   * Positive testing for getDescription() method.
   */
  @Test
  public void testGetDescription() {
    assertEquals("", snap1.getDescription());
    assertEquals("", snap2.getDescription());
    assertEquals("Third selfie", snap3.getDescription());
  }

  /**
   * Positive testing for getShapes() method.
   */
  @Test
  public void testGetShapes() {
    // No changes made to shapes or list
    assertEquals("[]", snap1.getShapes().toString());
    assertEquals("[Name: r1\nType: rectangle\nMin corner: (-101.0, -101.0), Width: 20.0, "
            + "Height: 10.0, Color: (255,0,0)]", snap2.getShapes().toString());
    assertEquals("[Name: o1\nType: oval\nCenter: (500.0, 496.0), X radius: 40.0, "
            + "Y radius: 89.0, Color: (0,0,255), Name: r2\nType: rectangle\nMin corner: "
            + "(1.0, 5.0), Width: 0.0, Height: 0.0, Color: (255,255,120)]",
            snap3.getShapes().toString());

    // Check changes made to original list of shapes not reflected in Snapshot
    shapes1.add(new Rectangle("WILL NOT BE ADDED", 2, 900, 89, 0,
            113, 98, 7));
    assertEquals("[]", snap1.getShapes().toString());

    shapes2.remove(0);
    assertEquals("[Name: r1\nType: rectangle\nMin corner: (-101.0, -101.0), Width: 20.0, "
            + "Height: 10.0, Color: (255,0,0)]", snap2.getShapes().toString());

    shapes3.remove(o1);
    shapes3.remove(r2);
    assertEquals("[Name: o1\nType: oval\nCenter: (500.0, 496.0), X radius: 40.0, "
            + "Y radius: 89.0, Color: (0,0,255), Name: r2\nType: rectangle\nMin corner: "
            + "(1.0, 5.0), Width: 0.0, Height: 0.0, Color: (255,255,120)]",
            snap3.getShapes().toString());

    // Check changes made to shapes in original list not reflected in Snapshot
    assertEquals("[]", snap1.getShapes().toString());

    r1.setPoint(0, 0);
    r1.setColor(110, 13, 98);
    assertEquals("[Name: r1\nType: rectangle\nMin corner: (-101.0, -101.0), Width: 20.0, "
            + "Height: 10.0, Color: (255,0,0)]", snap2.getShapes().toString());

    r2.setSize(9, 9991);
    o1.setColor(192, 88, 0);
    shapes3.remove(o1);
    assertEquals("[Name: o1\nType: oval\nCenter: (500.0, 496.0), X radius: 40.0, "
            + "Y radius: 89.0, Color: (0,0,255), Name: r2\nType: rectangle\nMin corner: "
            + "(1.0, 5.0), Width: 0.0, Height: 0.0, Color: (255,255,120)]",
            snap3.getShapes().toString());
  }

  /**
   * Positive testing for toString() method.
   */
  @Test
  public void testToString() {
    DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    assertEquals("Snapshot ID: " + snap1.getID() + "\nTimestamp: "
            + LocalDateTime.now().format(timestampFormat) + "\nDescription: \nShape Information:\n",
            snap1.toString());
    assertEquals("Snapshot ID: " + snap2.getID() + "\nTimestamp: "
            + LocalDateTime.now().format(timestampFormat) + "\nDescription: \nShape Information:\n"
            + "Name: r1\nType: rectangle\nMin corner: (-101.0, -101.0), Width: 20.0, Height: 10.0, "
            + "Color: (255,0,0)\n", snap2.toString());
    assertEquals("Snapshot ID: " + snap3.getID() + "\nTimestamp: "
            + LocalDateTime.now().format(timestampFormat) + "\nDescription: Third selfie\n"
            + "Shape Information:\nName: o1\nType: oval\nCenter: (500.0, 496.0), X radius: 40.0, "
            + "Y radius: 89.0, Color: (0,0,255)\nName: r2\nType: rectangle\nMin corner: "
            + "(1.0, 5.0), Width: 0.0, Height: 0.0, Color: (255,255,120)\n", snap3.toString());
  }
}