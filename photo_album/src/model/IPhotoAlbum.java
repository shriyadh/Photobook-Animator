package model;

import java.util.List;

/**
 * The interface Photo album contains the contract of methods and attributes that any photo album
 * implementing this class should fulfill.
 * The methods interact with all the other classes in the model implementation and provide the
 * first serve interface for users / controller/ view.
 */
public interface IPhotoAlbum {

  /**
   * Create shape.
   *
   * @param name      the name
   * @param type      the type
   * @param point     the point
   * @param color     the color
   * @param dimension the dimension
   */
  void createShape(String name, String type, Point point, Dimension dimension, Color color);

  /**
   * Gets shape.
   *
   * @param shapeName the shape name
   * @return the shape
   * @throws IllegalArgumentException the illegal argument exception
   */
  IShape getShape(String shapeName) throws IllegalArgumentException;

  public List<IShape> getPhotos();

  /**
   * Move shape.
   *
   * @param shapeName   the shape name
   * @param newPosition the new position
   * @throws IllegalArgumentException the illegal argument exception
   */
  void moveShape(String shapeName, Point newPosition) throws IllegalArgumentException;

  /**
   * Change shape color.
   *
   * @param shapeName the shape name
   * @param newColor  the new color
   * @throws IllegalArgumentException the illegal argument exception
   */
  void changeShapeColor(String shapeName, Color newColor) throws IllegalArgumentException;

  /**
   * Resize.
   *
   * @param shapeName the shape name
   * @param dimension the dimension
   * @throws IllegalArgumentException the illegal argument exception
   */
  void resize(String shapeName, Dimension dimension) throws IllegalArgumentException;

  /**
   * Remove shape.
   *
   * @param shapeName the shape name
   * @throws IllegalArgumentException the illegal argument exception
   */
  void removeShape(String shapeName) throws IllegalArgumentException;

  /**
   * Takes snapshot of the present state of the photoAlbum.
   *
   * @param description the description
   */
  void takeSnapshot(String description);

  /**
   * Gets snapshots in the form of the ID/timestamp list.
   *
   * @return the snapshots
   */
  String getSnapshots();

  List<Snapshot> getSnapshotObjects();

  /**
   * Print snapshots string depicting all the changes made in the photoalbum in the form
   * of selfies.
   *
   * @return the string
   */
  String printSnapshots();

  /**
   * Show album shows all the information on the shapes at any given time in the album.
   *
   * @return the string
   */
  String showAlbum();

  /**
   * Reset album clears all photos and sets a "fresh" canvas.
   */
  void resetAlbum();

  /**
   * Gets a string of all commands performed in the photo album. Tacks history!
   */
  String getCommandSet();

}
