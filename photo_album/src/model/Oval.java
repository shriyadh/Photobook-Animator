package model;

import java.util.Objects;

/**
 * The Oval class provides attributes and methods for the shape oval.
 * This class extends the AbstractShape and indirectly implements IShape interface.
 * The oval object has a unique name, type.Oval from enum class, a centre point, a specific color
 * and two radii (X and Y).
 */
public class Oval extends AbstractShape {

  private double radiusX;
  private double radiusY;

  /**
   * Instantiates a new Oval.
   *
   * @param shapeName the shape name
   * @param type      the type
   * @param center    the center
   * @param color     the color
   * @param dimension the dimension
   * @throws IllegalArgumentException the illegal argument exception
   */
  public Oval(String shapeName, Type type, Point center, Color color, Dimension dimension)
          throws IllegalArgumentException {
    super(shapeName, type, center, color);
    if (dimension == null) {
      throw new IllegalArgumentException("Dimension cannot be empty.");
    }
    if (type != Type.OVAL) {
      throw new IllegalArgumentException("Invalid given type of shape");
    }
    this.radiusX = dimension.getDimensionOne();
    this.radiusY = dimension.getDimensionTwo();
  }

  @Override
  public String getDimensions() {
    return "X radius: " + this.radiusX + ", Y radius: " + this.radiusY;
  }

  @Override
  public double getWidth() {
    return this.radiusX;
  }

  @Override
  public double getHeight() {
    return this.radiusY;
  }

  @Override
  public void resizeShape(Dimension newDimension) throws IllegalArgumentException {
    if (newDimension == null) {
      throw new IllegalArgumentException("Dimension cannot be empty.");
    }
    this.radiusX = newDimension.getDimensionOne();
    this.radiusY = newDimension.getDimensionTwo();
  }

  @Override
  public double getArea() {
    return Math.PI * radiusX * radiusY;
  }

  @Override
  public IShape copy() {
    return new Oval(this.getName(), this.getType(), this.getPosition(), this.getColor(),
            new Dimension(this.getWidth(), this.getHeight()));
  }

  @Override
  public String toString() {
    return "Name: " + this.getName() + "\nType: " + this.getType().toString() + "\nCenter: "
            + this.getPosition() + ", X radius: " + this.radiusX + ", Y radius: " + this.radiusY
            + ", Color: " + this.getColor() + "\n";
  }

  /**
   * The equals method checks if one oval object is equal to the other oval object based on
   * name, type, color, centre and dimensions
   *
   * @param o other oval object.
   * @return boolean based on whether objects are equal or not.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Oval oval = (Oval) o;
    return Double.compare(oval.radiusX, radiusX) == 0 && Double.compare(oval.radiusY, radiusY) == 0
            && Objects.equals(oval.getColor(), this.getColor())
            && oval.getPosition().equals(this.getPosition())
            && oval.getName().equalsIgnoreCase(this.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(radiusX, radiusY, this.getName());
  }
}
