package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import util.ShapeBuilder;

/**
 * The type Photo album is the Singleton class that depicts the canvas/ photo gallery of our
 * photos / shapes. It maintains the shape and has the major control over all the functions to be
 * implemented on shapes or on itself. This class demands total control of the model and hence is
 * designed in a way that the controller of the program would only be allowed to interact with
 * this class and none of the other classes.
 * The photo album allows the user to create shapes.
 * The photo album allows the user to move shapes.
 * The photo album allows the user to resize shapes.
 * The photo album allows the user to change color of shapes.
 * The photo album allows the user to snapshot the state of album and print it.
 * The photo album allows the user to reset the gallery.
 * It also tracks the history of commands in the program.
 */
public class PhotoAlbum implements IPhotoAlbum {

  private static final PhotoAlbum INSTANCE = new PhotoAlbum();
  private HashMap<String, IShape> photos = new LinkedHashMap<>();
  private List<Snapshot> snapshots = new ArrayList<>();
  private List<String> commandHistory = new ArrayList<>();

  private PhotoAlbum() {
  }

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static PhotoAlbum getInstance() {
    return INSTANCE;
  }

  @Override
  public void createShape(String name, String type, Point point,
                          Dimension dimension, Color color) throws IllegalArgumentException {
    if (photos.containsKey(name.toUpperCase())) {
      throw new IllegalArgumentException("Name already exists! Try a different name.");
    }
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty!");
    }
    if (type == null || type.isEmpty()) {
      throw new IllegalArgumentException("Type cannot be empty!");
    }
    if (point == null) {
      throw new IllegalArgumentException("Point cannot be empty!");
    }
    if (color == null) {
      throw new IllegalArgumentException("Color cannot be empty!");
    }
    if (dimension == null) {
      throw new IllegalArgumentException("Dimension cannot be empty!");
    }

    if (type.equalsIgnoreCase("Rectangle")) {
      Rectangle rectangle = new Rectangle(name, Type.RECTANGLE, point, color, dimension);
      photos.put(name.toUpperCase(), rectangle);
      String cmd = "Create " + rectangle.getColorName() + " " + rectangle.getType() + " "
              + rectangle.getName() + " " + "with corner at " + rectangle.getPosition()
              + ", " + rectangle.getDimensions();
      this.recordCommand(cmd);
    } else if (type.equalsIgnoreCase("Oval")) {
      Oval oval = new Oval(name, Type.OVAL, point, color, dimension);
      photos.put(name.toUpperCase(), oval);
      String cmd = "Create " + oval.getColorName() + " " + oval.getType() + " " + oval.getName()
              + " " + "with centre at " + oval.getPosition() + ", " + oval.getDimensions();
      this.recordCommand(cmd);
    }
  }

  @Override
  public List<IShape> getPhotos() {
    return new ArrayList<>(this.photos.values());
  }


  @Override
  public IShape getShape(String shapeName) {
    if (!this.photos.containsKey(shapeName.toUpperCase())) {
      throw new IllegalArgumentException("Shape not found! Please enter valid shape name.");
    }
    return photos.get(shapeName.toUpperCase());
  }

  @Override
  public void moveShape(String shapeName, Point newPosition) throws IllegalArgumentException {
    if (!this.photos.containsKey(shapeName.toUpperCase())) {
      throw new IllegalArgumentException("Shape not found! Please enter valid shape name.");
    }
    if (newPosition == null) {
      throw new IllegalArgumentException("New Position cannot be null");
    }
    this.photos.get(shapeName.toUpperCase()).moveTo(newPosition);

    String cmd = this.photos.get(shapeName.toUpperCase()).getName() + " moves to "
            + this.photos.get(shapeName.toUpperCase()).getPosition();
    this.recordCommand(cmd);
  }

  @Override
  public void changeShapeColor(String shapeName, Color newColor) throws IllegalArgumentException {
    if (!this.photos.containsKey(shapeName.toUpperCase())) {
      throw new IllegalArgumentException("Shape not found! Please enter valid shape name.");
    }
    if (newColor == null) {
      throw new IllegalArgumentException("New color cannot be null");
    }
    String cmd = this.photos.get(shapeName.toUpperCase()).getName() + " changes from "
            + this.photos.get(shapeName.toUpperCase()).getColorName();

    this.photos.get(shapeName.toUpperCase()).changeColor(newColor);
    cmd += " to " + this.photos.get(shapeName.toUpperCase()).getColorName();
    this.recordCommand(cmd);
  }

  @Override
  public void resize(String shapeName, Dimension newDimension)
          throws IllegalArgumentException {
    if (!this.photos.containsKey(shapeName.toUpperCase())) {
      throw new IllegalArgumentException("Shape not found! Please enter valid shape name.");
    }
    if (newDimension == null) {
      throw new IllegalArgumentException("New Dimension cannot be null");
    }
    this.photos.get(shapeName.toUpperCase()).resizeShape(newDimension);
    String cmd = shapeName + " was resized to "
            + this.photos.get(shapeName.toUpperCase()).getDimensions();
    this.recordCommand(cmd);
  }

  @Override
  public void removeShape(String shapeName) throws IllegalArgumentException {
    if (!this.photos.containsKey(shapeName.toUpperCase())) {
      throw new IllegalArgumentException("Shape not found! Please enter valid shape name.");
    }
    this.photos.remove(shapeName.toUpperCase());
    String cmd = shapeName + " got removed from photo album.";
    this.recordCommand(cmd);
  }

  @Override
  public void takeSnapshot(String description) {
    List<IShape> listPhotos = new ArrayList<>(this.photos.values());
    List<IShape> copy = new ArrayList<>();
    for (IShape s : listPhotos) {
      copy.add((s.copy()));
    }
    Snapshot newSnapshot = new Snapshot(description, copy);
    snapshots.add(newSnapshot);
    this.recordCommand("Take a Snapshot.");

  }

  @Override
  public String getSnapshots() {
    List<String> IDs = snapshots.stream().map(Snapshot::getID).collect(Collectors.toList());
    return "List of snapshots taken before reset:\n" + IDs;
  }

  @Override
  public List<Snapshot> getSnapshotObjects() {
    return this.snapshots;
  }

  @Override
  public String printSnapshots() {
    StringBuilder result = new StringBuilder("Printing Snapshots\n");

    for (Snapshot snap : snapshots) {
      result.append(snap.toString()).append("\n\n");
    }
    return String.valueOf(result);
  }

  @Override
  public String showAlbum() {
    StringBuilder result = new StringBuilder();
    List<IShape> shapes = new ArrayList<>(this.photos.values());

    for (IShape s : shapes) {
      result.append(s.toString()).append("\n");
    }
    return String.valueOf(result);
  }


  @Override
  public void resetAlbum() {
    this.photos.clear();
    this.commandHistory.clear();
    this.recordCommand("Whole album was reset.");
  }

  /**
   * Gets size.
   *
   * @return the size
   */
  public int getSize() {
    return this.photos.size();
  }

  /*
   * Maintains command history.
   */
  private void recordCommand(String information) {
    this.commandHistory.add(information);
  }

  @Override
  public String getCommandSet() {
    StringBuilder history = new StringBuilder();

    for (String s : this.commandHistory) {
      history.append(s).append("\n");
    }
    return history.toString();
  }


  /**
   * Gets builder-> copy of the original model which acts as appendable.
   *
   * @return the builder
   */
  public static ShapeBuilder getBuilder() {
    return new ShapeBuilderModel();
  }

  /**
   * The type Shape builder model is an adapter class that allows the controller to parse inputs
   * from the file according to user specification and build a model copy by adding and
   * improvising arguments based off the inputs. It adapts the inputs to be accepted in the form
   * that model is designed to handle thus providing re-usability of the same model. This acts
   * as an appendable for the main class
   */
  public static class ShapeBuilderModel implements ShapeBuilder {
    /**
     * The Photo album.
     */
    PhotoAlbum photoAlbum;

    /**
     * Instantiates a new Shape builder model.
     */
    public ShapeBuilderModel() {
      photoAlbum = new PhotoAlbum();
    }

    @Override
    public ShapeBuilder addShape(String name, String type, int x_pos, int y_pos, int width,
                                 int height, int red, int green, int blue) {
      photoAlbum.createShape(name, type, new Point(x_pos, y_pos), new Dimension(width, height),
              new Color(red, green, blue));
      return this;
    }

    @Override
    public ShapeBuilder removeShape(String shapeName) {
      photoAlbum.removeShape(shapeName);
      return this;

    }

    @Override
    public ShapeBuilder moveShape(String shapeName, int moveToX, int moveToY) {
      photoAlbum.moveShape(shapeName, new Point(moveToX, moveToY));
      return this;
    }

    @Override
    public ShapeBuilder resizeShape(String shapeName, int newWidth, int newHeight) {
      photoAlbum.resize(shapeName, new Dimension(newWidth, newHeight));
      return this;
    }

    @Override
    public ShapeBuilder changeColorShape(String shapeName, int newRed, int newGreen, int newBlue) {
      photoAlbum.changeShapeColor(shapeName, new Color(newRed, newGreen, newBlue));
      return this;
    }

    @Override
    public ShapeBuilder takeSnapShot(String description) {
      photoAlbum.takeSnapshot(description);
      return this;
    }

    @Override
    public PhotoAlbum build() {
      return photoAlbum;
    }
  }
}
