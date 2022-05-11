package view;

import java.util.List;

import controller.Features;
import model.IShape;

/**
 * This interface contains all methods that GUI views should support.
 */
public interface GUIView extends IView {

  /**
   * Defines the listeners for the interactive features of this view and connects each listener
   * to the appropriate callback method.
   * @param features (Features) the listener for this view's interactive features.
   */
  void addFeatures(Features features);

  /**
   * Displays the menu for the user to select an option to view.
   * @param options (Object[]) a list of options that the user can select to view.
   * @param currentIndex (int) the option that is currently being displayed on the window.
   * @return (int) the option the user has chosen to view on the window.
   */
  int displayMenu(Object[] options, int currentIndex);

  /**
   * Displays an error message to the user.
   */
  void displayErrorMessage();

  /**
   * Updates the window to display the given list of shapes.
   * @param currentShapes (List</IShape>) list of shapes to be displayed on the window.
   */
  void paintSnapshot(List<IShape> currentShapes);

  /**
   * Updates the window to display the current snapshot's ID and description.
   * @param id (String) ID of the currently displayed snapshot.
   * @param description (String) description associated to the currently displayed snapshot.
   */
  void setShapeInformation(String id, String description);
  }
