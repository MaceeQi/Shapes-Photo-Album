package controller;

import java.io.File;
import java.util.List;

import model.IPhotoAlbum;
import model.Snapshot;
import util.PhotoFileReader;
import view.GUIView;

/**
 * This class represents an interactive controller that communicates with a GUI-type view. An
 * interactive controller has the name of the input file, a photo album model, and a GUI view.
 */
public class InteractiveController implements IController, Features {
  private IPhotoAlbum model;
  private String fileName;
  private GUIView view;
  private int currentPhoto;

  /**
   * Constructs an InteractiveController instantiated to the given input file name, shapes photo
   * album model, and GUI view.
   * @param input (String) name of the input file to be read from.
   * @param model (IPhotoAlbum) the shapes photo album model for the program.
   * @param view (GUIView) the GUI view to display the output.
   */
  public InteractiveController(String input, IPhotoAlbum model, GUIView view) {
    this.fileName = input;
    this.model = model;
    this.view = view;
  }

  /**
   * A helper method that creates and sets up the view with the model information and all the
   * callbacks.
   */
  private void setView() {
    // Create and provide view with model information and set up the first snapshot
    this.view.produceView(this.model.getSnapshots());
    this.view.setShapeInformation(this.model.getSnapshots().get(0).getID(),
            this.model.getSnapshots().get(0).getDescription());
    this.view.paintSnapshot(this.model.getSnapshots().get(0).getShapes());

    // Provide view with all the callbacks
    this.view.addFeatures(this);

    // Set current photo to be the first snapshot
    this.currentPhoto = 0;
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

  @Override
  public void previousSnapshot() {
    // Show message to user if at end of photo album and there is no more "previous" snapshot
    if (this.currentPhoto == 0) {
      this.view.displayErrorMessage();
    }

    // Update the current view to the previous snapshot
    else {
      this.currentPhoto -= 1;
      this.updateView();
    }
  }

  @Override
  public void menu() {
    List<Snapshot> snapshot = this.model.getSnapshots();

    // Get all the snapshot IDs
    Object[] options = new String[snapshot.size()];
    for (int i = 0; i < snapshot.size(); i++) {
      options[i] = snapshot.get(i).getID();
    }

    // Display snapshot IDs as options in menu
    int selected = this.view.displayMenu(options, this.currentPhoto);

    if (selected >= 0) {
      // Update current view
      this.currentPhoto = selected;
      this.updateView();
    }
  }

  @Override
  public void nextSnapshot() {
    // Show message to user if at end of photo album and there is no more "next" snapshot
    if (this.currentPhoto == this.model.getSnapshots().size() - 1) {
      this.view.displayErrorMessage();
    }

    // Update the current view to the next snapshot
    else {
      this.currentPhoto += 1;
      this.updateView();
    }
  }

  @Override
  public void quitProgram() {
    System.exit(0);
  }

  /**
   * A helper method that updates the view with the desired snapshot information after a change
   * has been made.
   */
  private void updateView() {
    this.view.paintSnapshot(this.model.getSnapshots().get(this.currentPhoto).getShapes());
    this.view.setShapeInformation(this.model.getSnapshots().get(this.currentPhoto).getID(),
            this.model.getSnapshots().get(this.currentPhoto).getDescription());
  }
}

