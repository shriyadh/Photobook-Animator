import java.io.IOException;

import controller.Controller;
import model.PhotoAlbum;
import view.IView;
import view.ViewFactory;

/**
 * The type PhotoAlbum main is the entry point for this entire program.
 * It parses the command-line arguments and does the required actions requested by the user.
 * It makes the view and gets the instance of the model and passes it into the controller.
 * It then asks the controller to run which renders the view.
 */
public final class PhotoAlbumMain {

  /**
   * The entry point of application.
   * Parses the command line arguments to read the input files and produce appropriate outputs.
   *
   * @param args the input arguments
   * @throws IOException the io exception
   */
  public static void main(String[] args) throws IOException {

    String inputFile = "";
    String viewType = "";
    String outputFile = "";
    int dimensionOne = -1;
    int dimensionTwo = -1;

    String next;

    for (int i = 0; i < args.length; i++) {

      if (args[i].equalsIgnoreCase("-in")) {
        inputFile = args[i + 1];
      } else if (args[i].equalsIgnoreCase("-view")
              || args[i].equalsIgnoreCase("-v")) {
        viewType = args[i + 1];

      } else if (viewType.equalsIgnoreCase("graphical")) {
        try {
          if (dimensionOne == -1) {
            dimensionOne = Integer.parseInt(args[i]);

          } else if (dimensionTwo == -1) {
            dimensionTwo = Integer.parseInt(args[i]);
          }
        } catch (Exception e) {
          // do nothing
        }
      } else if (args[i].equalsIgnoreCase("-out")) {
        outputFile = args[i + 1];
      }

    }

    // input file and view type fields.
    if (inputFile.equals("") && viewType.equals("")) {
      throw new IOException("Input file and View type are mandatory fields!");
    }

    if (dimensionOne == -1) {
      dimensionOne = 1000;
    }

    if (dimensionTwo == -1) {
      dimensionTwo = 1000;
    }

    // get the model
    PhotoAlbum model = PhotoAlbum.getInstance();
    // get the type of view mentioned in command line.
    IView view = ViewFactory.chooseView(viewType.toUpperCase(), dimensionOne, dimensionTwo);
    // get the suitable controller depending on the type of view.
    //IController controller = ControllerFactory.makeController(model,viewType.toUpperCase(),view,inputFile);
    Controller controller = new Controller(model, viewType, view, inputFile, outputFile);
    // run the controller.
    controller.runController();

  }


}