package model;

import java.util.Objects;

/**
 * The Rectangle class provides attributes and methods for the shape rectangle.
 * This class extends the AbstractShape and indirectly implements IShape interface.
 * The rectangle object has a unique name, type.Rectangle from enum class, a centre point,
 * a specific color and two dimensions width and height.
 */
public class Rectangle extends AbstractShape {

  private double width;
  private double height;

  /**
   * Instantiates a new Rectangle.
   *
   * @param shapeName the shape name
   * @param type      the type
   * @param corner    the corner
   * @param color     the color
   * @param dimension the dimension
   */
  public Rectangle(String shapeName, Type type, Point corner, Color color, Dimension dimension)
          throws IllegalArgumentException {
    super(shapeName, type, corner, color);
    if(dimension == null) {
      throw new IllegalArgumentException("Dimension cannot be empty.");
    }
    if (type != Type.RECTANGLE) {
      throw new IllegalArgumentException("Invalid given type of shape");
    }
    this.width = dimension.getDimensionOne();
    this.height = dimension.getDimensionTwo();
  }

  @Override
  public String getDimensions() {
    return "Width: " + this.width + ", Height: " + this.height;
  }

  public double getWidth() {
    return this.width;
  }

  public double getHeight() {
    return this.height;
  }

  @Override
  public void resizeShape(Dimension newDimension) throws IllegalArgumentException {
    if(newDimension == null) {
      throw new IllegalArgumentException("New Dimension cannot be empty.");
    }
    this.width = newDimension.getDimensionOne();
    this.height = newDimension.getDimensionTwo();
  }

  @Override
  public double getArea() {
    return width * height;
  }

  @Override
  public IShape copy() {
    return new Rectangle(this.getName(), this.getType(), this.getPosition(), this.getColor(),
            new Dimension(this.getWidth(), this.getHeight()));
  }

  @Override
  public String toString() {
    return "Name: " + this.getName() + "\nType: " + this.getType().toString() + "\nMin corner: "
            + this.getPosition() + ", Width: " + this.width + ", Height: " + this.height
            + ", Color: " + this.getColor() + "\n";
  }

  /**
   * The equals method checks if one rectangle object is equal to the other oval object based on
   * name, type, color, corner and dimensions
   *
   * @param o other rectangle object.
   * @return boolean based on whether objects are equal or not.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Rectangle rectangle = (Rectangle) o;
    return Double.compare(rectangle.width, width) == 0
            && Double.compare(rectangle.height, height) == 0
            && Objects.equals(rectangle.getColor(), this.getColor())
            && rectangle.getPosition().equals(this.getPosition())
            && rectangle.getName().equalsIgnoreCase(this.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height, this.getName());
  }
}
