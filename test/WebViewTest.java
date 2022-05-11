import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.IShape;
import model.Oval;
import model.Rectangle;
import model.Snapshot;
import view.IView;
import view.WebView;

/**
 * A JUnit test class for the WebView class.
 */
public class WebViewTest {
  private IView web1;
  private IView web2;
  private IView web3;

  private Snapshot snap1;
  private Snapshot snap2;

  private IShape shape1;
  private IShape shape2;
  private IShape shape3;

  private List<IShape> list1;
  private List<IShape> list2;

  /**
   * Instantiate valid objects for testing.
   */
  @Before
  public void setUp() {
    web1 = new WebView("Title of Web View", "output.html", "0", "0");
    web2 = new WebView("", "null.html", "2983123", "1");
    web3 = new WebView(" ", "blank.html", "9000", "9000");

    shape1 = new Rectangle("r1", 0, 0, 0, 0, 0, 0, 0);
    shape2 = new Oval("o1", 25, 100, 350, 400, 255,255,255);
    shape3 = new Oval("OVAL", -100, 300, 1000, 100, 128, 100,
            78);

    list1 = new ArrayList<>();
    list2 = new ArrayList<>();
    list2.add(shape1);
    list2.add(shape2);
    list2.add(shape3);

    // No shapes - empty snapshot
    snap1 = new Snapshot("blank snap", list1);

    // Multiple different shapes in snapshot
    snap2 = new Snapshot("snap with shapes", list2);
  }

  /**
   * Positive testing for constructor. Ensure output is blank upon instantiation.
   */
  @Test
  public void testValidView() {
    assertEquals("", web1.toString());
    assertEquals("", web2.toString());
    assertEquals("", web3.toString());
  }

  /**
   * Negative testing for constructor. Null title.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidView1() {
    web1 = new WebView(null, "file.html", "200", "200");
  }

  /**
   * Negative testing for constructor. Null output file name.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidView2() {
    web1 = new WebView("Null Output File", null, "200", "200");
  }

  /**
   * Negative testing for constructor. Blank output file name.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidView3() {
    web1 = new WebView("Blank Output File", "", "0", "0");
  }

  /**
   * Negative testing for constructor. Output file is not HTML.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidView4() {
    web1 = new WebView("Not HTML output 1", "output.htm", "1000", "1000");
  }

  /**
   * Negative testing for constructor. Output file is not HTML.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidView5() {
    web1 = new WebView("Not HTML output 2", "output.web", "890", "400");
  }

  /**
   * Negative testing for constructor. Output file is not HTML.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidView6() {
    web1 = new WebView("Not HTML output 3", "outputhtml", "320", "1290");
  }

  /**
   * Negative testing for constructor. Output file is not HTML.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidView7() {
    web1 = new WebView("Not HTML output 4", "html", "9022", "800");
  }

  /**
   * Negative testing for constructor. Max X is not an integer number.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidView8() {
    web1 = new WebView("max x is not #", "output.html", "140.55", "800");
  }

  /**
   * Negative testing for constructor. Max X is not an integer number.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidView9() {
    web1 = new WebView("max x invalid", "random.html", "I40", "200");
  }

  /**
   * Negative testing for constructor. Max Y is not an integer number.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidView10() {
    web1 = new WebView("max y invalid", "invalid.html", "140", "1000.0");
  }

  /**
   * Negative testing for constructor. Max Y is not an integer number.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidView11() {
    web1 = new WebView("max y not a #", "output1.html", "290", "III");
  }

  /**
   * Negative testing for constructor. Output file name is html but blank.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidView12() {
    web1 = new WebView("html but blank", ".html", "1000", "1000");
  }

  /**
   * Positive testing for produceView() using toString() method. Ensure correct output to HTML file.
   */
  @Test
  public void testProduceView() {
    // Empty list
    List<Snapshot> snapList1 = new ArrayList<>();
    web1.produceView(snapList1);
    assertEquals("<!DOCTYPE html>\n<html>\n<body>\n<h1>Title of Web View</h1>\n"
                    + "<style>\n.myDiv {\n\tborder: 5px outset red;\n\tbackground-color: "
                    + "lightblue;\n}\n</style>\n</body>\n</html>\n", web1.toString());

    // Empty snapshot in list
    List<Snapshot> snapList2 = new ArrayList<>();
    snapList2.add(snap1);
    web2.produceView(snapList2);
    assertEquals("<!DOCTYPE html>\n<html>\n<body>\n<h1></h1>\n"
            + "<style>\n.myDiv {\n\tborder: 5px outset red;\n\tbackground-color: "
            + "lightblue;\n}\n</style>\n<div class=\"myDiv\">\n\t<h3>" + snap1.getID()
            + "</h3>\n\t<p>Description: blank snap</p>\n\t<svg width=\"2983123\" height=\"1\">\n"
            + "\t</svg>\n</div>\n<p>\n</p>\n</body>\n</html>\n", web2.toString());

    // List of snapshots with shapes
    List<Snapshot> snapList3 = new ArrayList<>();
    snapList3.add(snap1);
    snapList3.add(snap2);
    web3.produceView(snapList3);
    assertEquals("<!DOCTYPE html>\n<html>\n<body>\n<h1> </h1>\n"
            + "<style>\n.myDiv {\n\tborder: 5px outset red;\n\tbackground-color: "
            + "lightblue;\n}\n</style>\n<div class=\"myDiv\">\n\t<h3>" + snap1.getID()
            + "</h3>\n\t<p>Description: blank snap</p>\n\t<svg width=\"9000\" height=\"9000\">\n"
            + "\t</svg>\n</div>\n<p>\n</p>\n<div class=\"myDiv\">\n\t<h3>" + snap2.getID()
            + "</h3>\n\t<p>Description: snap with shapes</p>\n\t<svg width=\"9000\" height=\"9000\""
            + ">\n\t\t<rect id=\"r1\" x=\"0.0\" y=\"0.0\" width=\"0.0\" height=\"0.0\""
            + " fill=\"rgb(0,0,0)\">\n\t\t</rect>\n\t\t<ellipse id=\"o1\" cx=\"25.0\" cy=\"100.0\""
            + " rx=\"350.0\" ry=\"400.0\" fill=\"rgb(255,255,255)\">\n\t\t</ellipse>\n"
            + "\t\t<ellipse id=\"OVAL\" cx=\"-100.0\" cy=\"300.0\" rx=\"1000.0\" ry=\"100.0\""
            + " fill=\"rgb(128,100,78)\">\n\t\t</ellipse>\n\t</svg>\n</div>\n<p>\n</p>\n"
            + "</body>\n</html>\n", web3.toString());
  }
}