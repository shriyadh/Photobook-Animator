package model;

/**
 * The interface for the shape class defines the methods and attributes each object instantiated
 * from the concrete class should have. IShape would provide the "Contract" for all the shapes
 * that can be placed on the canvas.
 * Each shape has a Unique name - which would act like a Unique ID to identify that shape.
 * It has a type from Enum class Type. Right now the enum is made of but would not be limited to
 * Oval and rectangle shapes.
 * Each shape has a corner/centre depending on the shape type.
 * Each shape has a color defined in terms of R,G,B.
 * Each shape has dimensions accounting for their width/height, radii lengths, etc.
 * Each shape can move itself to a new position on the cartesian plane.
 * Each shape can change its own color based on R,G,B values.
 * Each shape can resize itself by passing in new dimensions -> can change its width, height
 * or both.
 * Each shape can calculate its own area based on its formula and dimensions.
 */
public interface IShape {

  /**
   * Gets name.
   *
   * @return the name
   */
  String getName();

  /**
   * Gets type from enum Type.
   *
   * @return the type
   */
  Type getType();

  /**
   * Gets type string.
   *
   * @return the type string
   */
  String getTypeString();

  /**
   * Gets position.
   *
   * @return the position
   */
  Point getPosition();

  /**
   * Gets color of the shape and returns its toString.
   *
   * @return the color
   */
  Color getColor();

  /**
   * Gets color name.
   *
   * @return the color name
   */
  String getColorName();

  /**
   * Gets dimensions as toString.
   *
   * @return the dimensions
   */
  String getDimensions();

  /**
   * Gets width.
   *
   * @return the width
   */
  double getWidth();

  /**
   * Gets height.
   *
   * @return the height
   */
  double getHeight();


  /**
   * Move to a new position.
   *
   * @param newPosition the new position
   * @throws IllegalArgumentException the illegal argument exception
   */
  void moveTo(Point newPosition) throws IllegalArgumentException;

  /**
   * Change color.
   *
   * @param newColor the new color
   * @throws IllegalArgumentException the illegal argument exception
   */
  void changeColor(Color newColor) throws IllegalArgumentException;

  /**
   * Resize shape to new dimensions -> change height, width or both.
   *
   * @param dimension the dimension
   * @throws IllegalArgumentException the illegal argument exception
   */
  void resizeShape(Dimension dimension) throws IllegalArgumentException;

  /**
   * Gets area based on dimensions and formula.
   *
   * @return the area
   */
  double getArea();

  /**
   * Returns a new copy of the original shape.
   *
   * @return the shape
   */
  IShape copy();
}
