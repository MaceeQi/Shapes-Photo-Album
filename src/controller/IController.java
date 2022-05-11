package controller;

/**
 * This interface contains all methods that all controllers should support.
 */
public interface IController {

  /**
   * Gives control of the program to the given controller. Controller relinquishes only when the
   * program ends.
   */
  void go();
}
