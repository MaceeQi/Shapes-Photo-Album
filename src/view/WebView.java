package view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.IShape;
import model.Snapshot;

/**
 * This class represents a static web page view with HTML markup and SVG. A web view has a title,
 * output HTML file, and maximum x and y values that specify the bounds of the view window.
 */
public class WebView implements IView {
  private File f;
  private final String title;
  private final String maxX;
  private final String maxY;
  private StringBuilder output;

  /**
   * Constructs a WebView instantiated to the given title, output file name, and maximum x and y
   * values that specify the bounds of the view window. Output file must be an HTML file (.html).
   * @param title (String) title for the web view.
   * @param fileName (String) name of the output HTML file.
   * @param maxX (String) maximum x value that specifies the horizontal bound of the view window.
   * @param maxY (String) maximum y value that specifies the vertical bound of the view window.
   * @throws IllegalArgumentException if title or output file name is null, output file name is blank,
   *                                  given output file name is not an HTML file, or if max x and
   *                                  y values are not integer numbers.
   */
  public WebView(String title, String fileName, String maxX, String maxY)
          throws IllegalArgumentException {
    // Title can't be null and output file name cannot be null or blank
    if (title == null || fileName == null || fileName.equals("")
            || fileName.equalsIgnoreCase(".html")) {
      throw new IllegalArgumentException("Title and output file must not be null. Output file also "
              + "cannot be blank.");
    }

    // Max x and y values must be an integer number
    try {
      Integer.parseInt(maxX);
      Integer.parseInt(maxY);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Max x and y must be integers!");
    }

    // Must be an HTML file for the web view
    if (!fileName.contains(".html")) {
      throw new IllegalArgumentException("HTML web views only!");
    }
    this.title = title;
    this.f = new File(fileName);
    this.maxX = maxX;
    this.maxY = maxY;
    this.output = new StringBuilder();
  }

  /**
   * A helper method which creates a String of the HTML markup and SVG based on the given list of
   * snapshots. The String that is produced will be what is written to the HTML file.
   * @param snapshots (List</Snapshot>) the snapshots to be displayed in the web view.
   */
  private void createHTMLMarkup(List<Snapshot> snapshots) {
    // Define header
    this.output.append("<!DOCTYPE html>\n<html>\n<body>\n<h1>" + this.title + "</h1>\n");

    // Define style of each snapshot border/area
    this.output.append("<style>\n.myDiv {\n\tborder: 5px outset red;\n\tbackground-color: "
            + "lightblue;\n}\n</style>\n");

    // Add each snapshot to HTML
    for (Snapshot each : snapshots) {
      this.output.append("<div class=\"myDiv\">\n\t<h3>" + each.getID() + "</h3>\n\t<p>Description: "
              + each.getDescription() + "</p>\n");
      this.output.append("\t<svg width=\"" + this.maxX + "\" height=\"" + this.maxY + "\">\n");

      // Draw shapes from snapshot
      for (IShape shape : each.getShapes()) {
        this.output.append(shape.toSVG());
      }

      this.output.append("\t</svg>\n</div>\n<p>\n</p>\n");
    }
    this.output.append("</body>\n</html>\n");
  }

  @Override
  public void produceView(List<Snapshot> snapshots) {
    // Check if output file already exists
    if (this.f.exists()) {
      System.out.println("File already exists!");
      System.exit(0);
    }

    // Create the HTML and SVG markup for the file (contains snapshot information)
    this.createHTMLMarkup(snapshots);

    // Write snapshot information to the HTML file
    try ( BufferedWriter bw = new BufferedWriter(new FileWriter(f)); ) {
      bw.write(this.output.toString());
    }
    catch (IOException e) {
      System.out.println("Could not create or write to the desired output file!");
    }
  }

  /**
   * For testing purposes. Returns a String containing the contents that are written to the output
   * HTML file.
   * @return (String) the contents written to the output HTML file.
   */
  @Override
  public String toString() {
    return this.output.toString();
  }
}
