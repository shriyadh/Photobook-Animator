package util;

/**
 * The interface ShapeBuilder is an adapter class interface that provides pluggability of
 * user-defined inputs. The parser class uses the concrete implementation of this class to read
 * instructions and simultaneously pass them to the adapter to add / resize / reposition /
 * recolor the shapes.
 *
 * @param <T> the type parameter
 */
public interface ShapeBuilder<T> {

  /**
   * Adds pre-defined shapes to the shape-builder to be created by the model.
   *
   * @param name   the name
   * @param type   the type
   * @param x_pos  the x pos
   * @param y_pos  the y pos
   * @param width  the width
   * @param height the height
   * @param red    the red
   * @param green  the green
   * @param blue   the blue
   * @return the shape builder
   */
  ShapeBuilder<T> addShape(String name, String type, int x_pos, int y_pos, int width, int height,
                           int red, int green, int blue);

  /**
   * Removed shape from the builder model.
   *
   * @param shapeName the shape name
   * @return the shape builder
   */
  ShapeBuilder<T> removeShape(String shapeName);

  /**
   * Moves shape to a new position in the builder model.
   *
   * @param shapeName the shape name
   * @param moveToX   the move to x
   * @param moveToY   the move to y
   * @return the shape builder
   */
  ShapeBuilder<T> moveShape(String shapeName, int moveToX, int moveToY);

  /**
   * Resizes shape to a new dimension in the builder model.
   *
   * @param shapeName the shape name
   * @param newWidth  the new width
   * @param newHeight the new height
   * @return the shape builder
   */
  ShapeBuilder<T> resizeShape(String shapeName, int newWidth, int newHeight);

  /**
   * Changes color of the shape in the builder model.
   *
   * @param shapeName the shape name
   * @param newRed    the new red
   * @param newGreen  the new green
   * @param newBlue   the new blue
   * @return the shape builder
   */
  ShapeBuilder<T> changeColorShape(String shapeName, int newRed, int newGreen, int newBlue);

  /**
   * Takes snapshot of the state of the shape builder model.
   *
   * @param description the description
   * @return the shape builder
   */
  ShapeBuilder<T> takeSnapShot(String description);

  /**
   * Build the entire model to recreate the file instructions for the photo album.
   *
   * @return the t
   */
  T build();
}
