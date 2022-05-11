import java.util.List;

import controller.Features;
import model.IShape;
import model.Snapshot;
import view.GUIView;
import view.IView;

/**
 * Mock view for testing. A MockView receives a unique code from the controller, output by the
 * model to ensure results from the model are correctly transmitted to this view by the controller.
 */
public class MockView implements IView, GUIView {
  private String uniqueCode;

  /**
   * Instantiate an empty MockView object.
   */
  public MockView() {
  }

  /**
   * Sets the unique code transmitted from controller to this view to ensure results from model
   * are correctly transmitted to this view by the controller.
   * @param snapshots (List</Snapshot>) contains one snapshot with the description containing the
   *                                    unique code.
   */
  @Override
  public void produceView(List<Snapshot> snapshots) {
    this.uniqueCode = snapshots.get(0).getDescription();
  }

  /**
   * Returns the unique code transmitted as output by the model to the controller, passed to this
   * view to ensure results from the model are correctly transmitted.
   * @return (String) the unique code.
   */
  @Override
  public String toString() {
    return this.uniqueCode;
  }

  @Override
  public void addFeatures(Features features) {
    return;
  }

  @Override
  public int displayMenu(Object[] options, int currentIndex) {
    return 0;
  }

  @Override
  public void displayErrorMessage() {
    return;
  }

  @Override
  public void paintSnapshot(List<IShape> currentShapes) {
    return;
  }

  @Override
  public void setShapeInformation(String id, String description) {
    return;
  }
}
