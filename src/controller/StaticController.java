package controller;

import java.io.File;

import model.IPhotoAlbum;
import util.PhotoFileReader;
import view.IView;

/**
 * This class represents a static controller that communicates with a static-type view. A static
 * controller has the name of an input file, a photo album model, and a static view.
 */
public class StaticController implements IController {
  private IPhotoAlbum model;
  private String fileName;
  private IView view;

  /**
   * Constructs a StaticController instantiated to the given input file name, shapes photo album
   * model, and static view.
   * @param input (String) name of the input file to read from.
   * @param model (IPhotoAlbum) the shapes photo album model for the program.
   * @param view (IView) the static view to display the output.
   */
  public StaticController(String input, IPhotoAlbum model, IView view) {
    this.fileName = input;
    this.model = model;
    this.view = view;
  }

  /**
   * A helper method that creates the view with the model information.
   */
  private void setView() {
    // Provide view with model information
    this.view.produceView(this.model.getSnapshots());
  }

  @Override
  public void go() {
    // Create new File object based on input text file
    File input = new File(this.fileName);

    // Read input text file and update model accordingly
    PhotoFileReader fileReader = new PhotoFileReader(input, this.model);
    fileReader.parsePhotoFile();

    // Set up the view
    this.setView();
  }
}

