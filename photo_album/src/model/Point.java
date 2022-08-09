package model;

/**
 * The Point class contains the x and y coordinates of the center / corner of the defined shapes.
 * The point is class is used to represent any point on the cartesian system and shapes can be
 * initialized / moved to a new location by defining the point.
 */
public class Point {

  private double x;
  private double y;

  /**
   * Instantiates a new Point.
   *
   * @param x the x coordinate of the shape.
   * @param y the y coordinate of the shape.
   */
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets x.
   *
   * @return the x
   */
  public double getX() {
    return this.x;
  }

  /**
   * Gets y.
   *
   * @return the y
   */
  public double getY() {
    return this.y;
  }

  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
}
