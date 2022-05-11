package main;

import java.util.HashMap;
import java.util.Map;

import controller.InteractiveController;
import controller.StaticController;
import model.IPhotoAlbum;
import model.ShapesPhotoAlbumModel;
import view.GUIView;
import view.GraphicalView;
import view.IView;
import view.WebView;

/**
 * Entry point for the Shapes Photo Album application.
 */
public class PhotoAlbumMain {
  private static String xMax = "1000";
  private static String yMax = "1000";

  public static void main(String[] args) {
    // Check command line parameter usage
    if (args.length < 4) {
      System.out.println("Usage: MyProgram -in \"name-of-command-file\" -view \"type-of-view\" "
              + "[-out \"where-output-should-go\"] [xmax] [ymax]");
      System.exit(1);
    }

    // Map to pair arguments (input, output, view, min/max)
    Map<String, String> instructions = new HashMap<>();

    // Parse command line arguments and save arguments as pairs
    for (int i = 0; i < args.length; i++) {
      // -view and -v are synonymous; convert to -view for consistency
      if (args[i].equals("-v")) {
        args[i] = "-view";
      }

      // Add pair arguments to map (input/output/view/min & max)
      if (args[i].equals("-in") || args[i].equals("-view") || args[i].equals("-out")) {
        try {
          instructions.put(args[i], args[i + 1]);
        }

        // Ensure each argument has a pair
        catch (IndexOutOfBoundsException e) {
          System.out.println("Input, output, and view all require pairs of arguments.");
          System.exit(1);
        }
      }

      // Set xmax and ymax if given
      if (args[i].matches("-?\\d+(\\.\\d+)?")) {
        try {
          xMax = args[i];
          i++;
          yMax = args[i];
        }
        // Ensure both max x and y are specified if given
        catch (IndexOutOfBoundsException e) {
          System.out.println("Both max x and max y are required if specifying.");
          System.exit(1);
        }

      }
    }

    // Input and view pairs are mandatory
    if (!instructions.containsKey("-in") || !instructions.containsKey("-view")) {
      System.out.println("[-in \"input file name\"] and [-view \"type of view\"] are mandatory!");
      System.exit(1);
    }

    // Output pair is required if desired view is web
    if (instructions.get("-view").equalsIgnoreCase("web")
            && !instructions.containsKey("-out")) {
      System.out.println("Web views must have a specified output pair!");
      System.exit(1);
    }

    // Create model
    IPhotoAlbum model = new ShapesPhotoAlbumModel();

    // Create view and controller and give control to controller
    switch(instructions.get("-view").toLowerCase()) {
      case "web":
        IView webView = new WebView("cs5004 Shapes Photo Album Web Viewer",
                instructions.get("-out"), xMax, yMax);
        StaticController webController = new StaticController(instructions.get("-in"), model, webView);
        webController.go();
        break;

      case "graphical":
        GUIView graphicalView = new GraphicalView("cs5004 Shapes Photo Album Graphical Viewer",
                xMax, yMax);
        InteractiveController graphicalController = new InteractiveController(instructions.get("-in"),
                model, graphicalView);
        graphicalController.go();
        break;

      default:
        System.out.println("That is not a valid view type for this program! Please use either "
                + "web or graphical.");
        System.exit(1);
    }
  }
}
