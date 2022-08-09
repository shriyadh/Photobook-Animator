package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

import model.PhotoAlbum;
import model.Snapshot;
import util.FileParser;
import util.ShapeBuilder;
import view.IView;

/**
 * The type Controller class returns Controller object that controls the communication between
 * model and view. It calls the parser class to read the instructions from the file and with the
 * help of an adapter class 'ShapeBuilder' constructs a new model copy for the view. It then
 * gets a list of Snapshot objects and passes it into the view depending on the type of view to be
 * rendered. It controls all operations between view and model and does not allow direct access
 * to either.
 */
public class Controller implements IController {

  private PhotoAlbum model;
  private String viewType;
  private IView view;
  private String inputFileName;
  private String outputFileName;
  private ShapeBuilder shapeBuilder;
  private PhotoAlbum newModel;
  private InputStream readFile;
  private List<Snapshot> listOfSnapshots;

  /**
   * Instantiates a new Controller.
   *
   * @param model          the model
   * @param type           the type of view
   * @param view           the view
   * @param inputFileName  the input file name
   * @param outputFileName the output file name
   * @throws FileNotFoundException    the file not found exception
   * @throws IllegalArgumentException the illegal argument exception
   */
  public Controller(PhotoAlbum model, String type, IView view, String inputFileName,
                    String outputFileName)
          throws FileNotFoundException, IllegalArgumentException {

    if (model == null) {
      throw new IllegalArgumentException("Error --- Model cannot be null.");
    }
    if (view == null) {
      throw new IllegalArgumentException("Error --- View cannot be null.");
    }
    if (inputFileName == null || inputFileName.equals("")) {
      throw new IllegalArgumentException("Error --- File cannot be null.");
    }
    String curDir = System.getProperty("user.dir");

    this.model = model;
    this.view = view;
    this.viewType = type.toUpperCase();
    this.inputFileName = curDir + "//" + inputFileName;
    this.outputFileName = outputFileName;
    this.parseInformation();
  }

  /**
   * Parses information by called the file parser class which returns a copy of the model based on
   * file description and renders a list of snapshot objects for the view.
   *
   * @throws FileNotFoundException the file not found exception
   */
  private void parseInformation() throws FileNotFoundException {
    // read the input file from the parser.
    try {
      //System.out.println(inputFileName);
      readFile = new FileInputStream(inputFileName);
    } catch (Exception e) {
      throw new FileNotFoundException("Error --- File not found!");
    }

    // get the builder(ADAPTER CLASS) from the model to track the instructions in the file.
    shapeBuilder = model.getBuilder();
    newModel = (PhotoAlbum) FileParser.parseFile(new InputStreamReader(readFile), shapeBuilder);

    // get a list of snapShots from the new Model returned from the ShapeBuilder Adapter class.
    listOfSnapshots = newModel.getSnapshotObjects();
  }

  @Override
  public void runController() throws IOException {

    switch (viewType) {
      case "GRAPHICAL":
        view.renderGraphicalView(listOfSnapshots);
        break;

      case "WEB":
        PrintStream outputFile = new PrintStream(new File(outputFileName));
        Appendable appendable = outputFile;
        view.renderWebView(listOfSnapshots, appendable);
        break;

      default:
        throw new IllegalArgumentException("Error --- Not a valid view");
    }

  }

}
