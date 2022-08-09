package controller;

import java.io.IOException;

/**
 * The interface Controller is the contract for the controller to implement methods for proper
 * handling of instructions between model and view.
 * The type Controller class returns Controller object that controls the communication between
 * model and view. It calls the parser class to read the instructions from the file and with the
 * help of an adapter class 'ShapeBuilder' constructs a new model copy for the view. It then
 * gets a list of Snapshot objects and passes it into the view depending on the type of view to be
 * rendered. It controls all operations between view and model and does not allow direct access
 * to either.
 */
public interface IController {


  /**
   * Loads the view based on the user input and transfers required information to the view from
   * the model.
   *
   * @throws IOException the io exception
   */
  void runController() throws IOException;

}
