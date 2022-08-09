package model;

/**
 * The Dimension class describes the dimensions of an object. It has overloaded constructors that
 * take one or two dimension measurements depending on the shape.
 * Radius -> circle.
 * RadiusX and RadiusY -> oval.
 * Width and Height -> Rectangle / Square.
 */
public class Dimension {

  private double dimensionOne;
  private double dimensionTwo;

  /**
   * Instantiates a new Dimension.
   *
   * @param dimensionOne the dimension one
   * @throws IllegalArgumentException the illegal argument exception if entries are negative.
   */
  public Dimension(double dimensionOne) throws IllegalArgumentException {
    if (dimensionOne <= 0) {
      throw new IllegalArgumentException("Dimension cannot be negative or zero!");
    }
    this.dimensionOne = dimensionOne;
  }

  /**
   * Instantiates a new Dimension.
   *
   * @param dimensionOne the dimension one
   * @param dimensionTwo the dimension two
   * @throws IllegalArgumentException the illegal argument exception
   */
  public Dimension(double dimensionOne, double dimensionTwo) throws IllegalArgumentException {
    if (dimensionOne <= 0 || dimensionTwo <= 0) {
      throw new IllegalArgumentException("Dimension cannot be negative or zero!");
    }
    this.dimensionOne = dimensionOne;
    this.dimensionTwo = dimensionTwo;
  }

  /**
   * Gets dimension one.
   *
   * @return the dimension one
   */
  public double getDimensionOne() {
    return this.dimensionOne;
  }

  /**
   * Gets dimension two.
   *
   * @return the dimension two
   */
  public double getDimensionTwo() {
    return this.dimensionTwo;
  }

}
