package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.IPhotoAlbum;
import model.IShape;

/**
 * This class represents a photo file reader. A photo file reader has a file to read from and a
 * shapes photo album model to update based on contents of the file.
 */
public class PhotoFileReader {
  private File file;
  private IPhotoAlbum model;

  /**
   * Constructs a PhotoFileReader instantiated to the given file and shapes photo album model.
   * @param file (File) the file to read from.
   * @param model (IPhotoAlbum) the shapes photo album model to be updated based on contents of file.
   */
  public PhotoFileReader(File file, IPhotoAlbum model) {
    this.file = file;
    this.model = model;
  }

  /**
   * Parses the file and updates the model based on contents of the file.
   */
  public void parsePhotoFile() {
    try {
      // Create Scanner to read data from given file
      Scanner scanner = new Scanner(this.file);

      // Read line by line and split by any whitespace
      while (scanner.hasNextLine()) {
        String inputLine = scanner.nextLine();

        // Remove any leading spaces
        inputLine = inputLine.trim();

        // Split each line by any whitespaces
        String[] tokens = inputLine.split("\\s+");

        // Update the model based on the file contents
        this.updateModel(tokens);
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("Could not find input file!");
      System.exit(1);
    }
  }

  /**
   * A helper method that updates the model based on the parsed file.
   * @param tokens (String[]) the contents of the parsed file.
   */
  private void updateModel(String[] tokens) {
    switch (tokens[0].toLowerCase()) {
      // Creates new shape and adds to model
      case "shape":
        IShape shape = this.model.createShape(tokens[2], tokens[1], Integer.parseInt(tokens[3]),
                Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]),
                Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]),
                Integer.parseInt(tokens[8]), Integer.parseInt(tokens[9]));
        this.model.addShape(shape);
        break;

      // Moves given shape to new x,y location
      case "move":
        this.model.moveShape(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
        break;

      // Changes color of given shape
      case "color":
        this.model.changeShapeColor(tokens[1], Integer.parseInt(tokens[2]),
                Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
        break;

      // Changes size of given shape
      case "resize":
        this.model.setShapeSize(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
        break;

      // Removes given shape
      case "remove":
        this.model.removeShape(tokens[1]);
        break;

      // Takes a snapshot of the model
      case "snapshot":
        String description = "";
        for (int i = 1; i < tokens.length; i++) {
          description += tokens[i] + " ";
        }
        this.model.takeSnapshot(description);
        break;

      default:
        break;
    }
  }
}
