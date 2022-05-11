import java.util.ArrayList;
import java.util.List;

import model.IPhotoAlbum;
import model.IShape;
import model.Snapshot;

/**
 * Mock Photo Album model for testing. A MockModel logs the inputs provided to it and returns
 * a unique code provided to it at creation.
 */
public class MockModel implements IPhotoAlbum {
  private StringBuilder log;
  private final String uniqueCode;

  /**
   * Instantiates mock model to the given log and unique code.
   * @param log (StringBuilder) log to keep track of inputs provided to the model.
   * @param uniqueCode (String) unique code.
   */
  public MockModel(StringBuilder log, String uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public IShape createShape(String type, String name, int x, int y, int sizeX, int sizeY,
                            int r, int g, int b) {
    log.append("Create shape: " + type + ", " + name + ", " + x + ", " + y + ", " + sizeX + ", "
            + sizeY + ", " + r + ", " + g + ", " + b + "\n");
    return null;
  }

  @Override
  public void addShape(IShape shape) {
    log.append("Add shape\n");
  }

  @Override
  public void removeShape(String shapeName) {
    log.append("Remove shape: " + shapeName + "\n");
  }

  @Override
  public void changeShapeColor(String shapeName, int r, int g, int b) {
    log.append("Change shape color: " + shapeName + ", (" + r + "," + g + "," + b + ")\n");
  }

  @Override
  public void moveShape(String shapeName, int x, int y) {
    log.append("Move shape: " + shapeName + ", (" + x + "," + y + ")\n");
  }

  @Override
  public void setShapeSize(String shapeName, int sizeX, int sizeY) {
    log.append("Change shape size: " + shapeName + ", size x: " + sizeX + ", size y: " + sizeY
            + "\n");
  }

  @Override
  public void reset() {
    log.append("Reset\n");
  }

  @Override
  public void takeSnapshot(String description) {
    log.append("Take snapshot: " + description + "\n");
  }

  @Override
  public List<Snapshot> getSnapshots() {
    List<Snapshot> mockSnapshots = new ArrayList<>();
    mockSnapshots.add(new Snapshot(this.uniqueCode, new ArrayList<>()));
    return mockSnapshots;
  }

  @Override
  public List<IShape> getShapes() {
    return null;
  }

  @Override
  public String getModelState() {
    return null;
  }
}
