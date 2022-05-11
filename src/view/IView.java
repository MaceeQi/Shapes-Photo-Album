package view;

import java.util.List;

import model.Snapshot;

/**
 * This interface contains all methods that all types of views should support.
 */
public interface IView {

  /**
   * Creates the view based on the given list of snapshots.
   * @param snapshots (List</Snapshot>) snapshots to be displayed in the view.
   */
  void produceView(List<Snapshot> snapshots);
}
