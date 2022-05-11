import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import controller.IController;
import controller.InteractiveController;
import controller.StaticController;
import model.IPhotoAlbum;
import view.GUIView;
import view.IView;

/**
 * A JUnit test class for the classes that implement IController. Ensures input provided to
 * controllers are correctly transmitted to the model and results from the model are correctly
 * transmitted to the view object by the controller.
 */
public class IControllerTest {
  private StringBuilder log1;
  private StringBuilder log2;
  private StringBuilder log3;
  private StringBuilder log4;
  private IPhotoAlbum model1;
  private IPhotoAlbum model2;
  private IPhotoAlbum model3;
  private IPhotoAlbum model4;
  private IView staticView1;
  private IView staticView2;
  private GUIView guiView1;
  private GUIView guiView2;
  private IController staticController1;
  private IController staticController2;
  private InteractiveController interactiveController1;
  private InteractiveController interactiveController2;

  /**
   * Instantiate static controller, interactive controller, and mock models and views for testing.
   */
  @Before
  public void setUp() {
    // Logs for mock models
    log1 = new StringBuilder();
    log2 = new StringBuilder();
    log3 = new StringBuilder();
    log4 = new StringBuilder();

    // Create mock models and views
    model1 = new MockModel(log1, "abcdefghijklmnopqrstuvwxyz");
    model2 = new MockModel(log2, "1234321");
    model3 = new MockModel(log3, "abcdefghijklmnopqrstuvwxyz");
    model4 = new MockModel(log4, "1234321");
    staticView1 = new MockView();
    staticView2 = new MockView();
    guiView1 = new MockView();
    guiView2 = new MockView();

    // Instantiate both types of controllers to mock models and views
    staticController1 = new StaticController("test/testfile1.txt", model1, staticView1);
    staticController2 = new StaticController("test/testfile2.txt", model2, staticView2);

    interactiveController1 = new InteractiveController("test/testfile1.txt", model3, guiView1);
    interactiveController2 = new InteractiveController("test/testfile2.txt", model4, guiView2);
  }

  /**
   * Testing for go() method of both controller types. Ensure inputs reached the model correctly
   * and output of model transmitted correctly. Ensure calling other methods from interactive
   * controller does not change the initial output of model.
   */
  @Test
  public void testControllers() {
    // Static controller
    staticController1.go();
    assertEquals("", log1.toString()); // Inputs reached model correctly
    assertEquals("abcdefghijklmnopqrstuvwxyz",
            staticView1.toString()); // Output of model transmitted correctly

    staticController2.go();
    assertEquals("Create shape: rectangle, myrect, 200, 200, 50, 100, 255, "
            + "0, 0\nAdd shape\nCreate shape: oval, myoval, 500, 100, 60, 30, 0, 255, 1\nAdd shape\n"
            + "Take snapshot: First Selfie \nMove shape: myrect, (300,200)\nChange shape size: "
            + "myrect, size x: 25, size y: 100\nMove shape: myrect, (100,300)\nTake snapshot: "
            + "description here \nChange shape color: myrect, (0,0,255)\nMove shape: myoval, "
            + "(500,400)\nTake snapshot: \nRemove shape: myrect\nTake snapshot: last selfie \n",
            log2.toString()); // Inputs reached model correctly
    assertEquals("1234321", staticView2.toString()); // Output of model transmitted correctly

    // Interactive controller
    interactiveController1.go();
    assertEquals("", log3.toString()); // Inputs reached model correctly
    assertEquals("abcdefghijklmnopqrstuvwxyz",
            guiView1.toString()); // Output of model transmitted correctly
    // Ensure output of model is still transmitted correctly even when calling other methods
    interactiveController1.previousSnapshot();
    assertEquals("abcdefghijklmnopqrstuvwxyz", guiView1.toString());
    interactiveController1.menu();
    assertEquals("abcdefghijklmnopqrstuvwxyz", guiView1.toString());
    interactiveController1.nextSnapshot();
    assertEquals("abcdefghijklmnopqrstuvwxyz", guiView1.toString());

    interactiveController2.go();
    assertEquals("Create shape: rectangle, myrect, 200, 200, 50, 100, 255,"
            + " 0, 0\nAdd shape\nCreate shape: oval, myoval, 500, 100, 60, 30, 0, 255, 1\nAdd shape"
            + "\nTake snapshot: First Selfie \nMove shape: myrect, (300,200)\nChange shape size: "
            + "myrect, size x: 25, size y: 100\nMove shape: myrect, (100,300)\nTake snapshot: "
            + "description here \nChange shape color: myrect, (0,0,255)\nMove shape: myoval, "
            + "(500,400)\nTake snapshot: \nRemove shape: myrect\nTake snapshot: last selfie \n",
            log4.toString()); // Inputs reached model correctly
    assertEquals("1234321", guiView2.toString()); // Output of model transmitted correctly
    // Ensure output of model is still transmitted correctly even when calling other methods
    interactiveController2.previousSnapshot();
    assertEquals("1234321", guiView2.toString());
    interactiveController2.menu();
    assertEquals("1234321", guiView2.toString());
    interactiveController2.nextSnapshot();
    assertEquals("1234321", guiView2.toString());
  }
}