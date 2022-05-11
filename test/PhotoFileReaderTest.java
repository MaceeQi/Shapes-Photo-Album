import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import model.IPhotoAlbum;
import model.ShapesPhotoAlbumModel;
import model.Snapshot;
import util.PhotoFileReader;

/**
 * A JUnit test class for the PhotoFileReader class.
 */
public class PhotoFileReaderTest {
  private File file1;
  private File file2;
  private PhotoFileReader reader1;
  private PhotoFileReader reader2;
  private IPhotoAlbum model1;
  private IPhotoAlbum model2;

  /**
   * Instantiate valid objects for testing.
   */
  @Before
  public void setUp() {
    // Empty file
    file1 = new File("test/testfile1.txt");

    // File with shape commands
    file2 = new File("test/testfile2.txt");

    model1 = new ShapesPhotoAlbumModel();
    model2 = new ShapesPhotoAlbumModel();

    reader1 = new PhotoFileReader(file1, model1);
    reader2 = new PhotoFileReader(file2, model2);
  }

  /**
   * Positive testing for parsePhotoFile() method. Ensure model reflects commands in text file.
   */
  @Test
  public void testParsePhotoFile() {
    // Empty text file
    reader1.parsePhotoFile();
    assertEquals("List of snapshots taken before reset: []\n\nPrinting Snapshots\n",
            model1.getModelState());


    // Text file with shape commands
    reader2.parsePhotoFile();

    // Get list of snapshot IDs (helper for testing)
    List<Snapshot> snapshots = model2.getSnapshots();
    List<String> snapshotIDs = snapshots.stream().map(snap ->
            snap.getID()).collect(Collectors.toList());
    // Create formatter and get current date/time for snapshot timestamp (helper for testing)
    LocalDateTime timeForTimestamp = LocalDateTime.now();
    DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    assertEquals("Name: myrect\nType: rectangle\nMin corner: (200.0, 200.0), Width: 50.0, "
                    + "Height: 100.0, Color: (255,0,0)\nName: myoval\nType: oval\nCenter: (500.0, "
                    + "100.0), X radius: 60.0, Y radius: 30.0, Color: (0,255,1)\n\nName: myrect\n"
                    + "Type: rectangle\nMin corner: (100.0, 300.0), Width: 25.0, Height: 100.0, "
                    + "Color: (255,0,0)\nName: myoval\nType: oval\nCenter: (500.0, 100.0), X "
                    + "radius: 60.0, Y radius: 30.0, Color: (0,255,1)\n\nName: myrect\nType: "
                    + "rectangle\nMin corner: (100.0, 300.0), Width: 25.0, Height: 100.0, Color: "
                    + "(0,0,255)\nName: myoval\nType: oval\nCenter: (500.0, 400.0), X radius: 60.0, "
                    + "Y radius: 30.0, Color: (0,255,1)\n\nName: myoval\nType: oval\nCenter: (500.0,"
                    + " 400.0), X radius: 60.0, Y radius: 30.0, Color: (0,255,1)\n\nList of "
                    + "snapshots taken before reset: " + snapshotIDs.toString() + "\n\nPrinting"
                    + " Snapshots\nSnapshot ID: " + snapshots.get(0).getID() + "\nTimestamp: "
                    + timeForTimestamp.format(timestampFormat) + "\nDescription: First Selfie \n"
                    + "Shape Information:\nName: myrect\nType: rectangle\nMin corner: (200.0, "
                    + "200.0), Width: 50.0, Height: 100.0, Color: (255,0,0)\nName: myoval\nType: "
                    + "oval\nCenter: (500.0, 100.0), X radius: 60.0, Y radius: 30.0, Color: (0,255,"
                    + "1)\n\nSnapshot ID: " + snapshots.get(1).getID() + "\nTimestamp: "
                    + timeForTimestamp.format(timestampFormat) + "\nDescription: description here \n"
                    + "Shape Information:\nName: myrect\nType: rectangle\nMin corner: (100.0, "
                    + "300.0), Width: 25.0, Height: 100.0, Color: (255,0,0)\nName: myoval\nType: "
                    + "oval\nCenter: (500.0, 100.0), X radius: 60.0, Y radius: 30.0, Color: (0,"
                    + "255,1)\n\nSnapshot ID: " + snapshots.get(2).getID() + "\nTimestamp: "
                    + timeForTimestamp.format(timestampFormat) + "\nDescription: \nShape "
                    + "Information:\nName: myrect\nType: rectangle\nMin corner: (100.0, 300.0), "
                    + "Width: 25.0, Height: 100.0, Color: (0,0,255)\nName: myoval\nType: oval\n"
                    + "Center: (500.0, 400.0), X radius: 60.0, Y radius: 30.0, Color: (0,255,1)\n\n"
                    + "Snapshot ID: " + snapshots.get(3).getID() + "\nTimestamp: "
                    + timeForTimestamp.format(timestampFormat) + "\nDescription: last selfie \nShape"
                    + " Information:\nName: myoval\nType: oval\nCenter: (500.0, 400.0), X radius: "
                    + "60.0, Y radius: 30.0, Color: (0,255,1)\n\n", model2.getModelState());
  }
}