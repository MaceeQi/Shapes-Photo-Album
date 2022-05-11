package controller;

/**
 * This interface contains all features that interactive controllers should support.
 */
public interface Features {

  /**
   * Shows the previous snapshot in the photo album if it exists. If there is no "previous", a
   * message to the user is shown.
   */
  void previousSnapshot();

  /**
   * Jumps to a snapshot the user explicitly selects from a list of options.
   */
  void menu();

  /**
   * Shows the next snapshot in the photo album if it exists. If no further snapshots exist, a
   * message to the user is shown.
   */
  void nextSnapshot();

  /**
   * Exits the entire program.
   */
  void quitProgram();
}
