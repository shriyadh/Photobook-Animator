package model;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * The Color class is used to define the color attribute. It has three values (R, G, B).
 * Red , Green, Blue values are used to represent the spectrum of colors on the scale.
 * Takes in a String value denoting the name of the color.
 */
public class Color {

  private double red;
  private double green;
  private double blue;
  private String colorName;

  /**
   * Instantiates a new Color object on R,G,B values.
   *
   * @param red   the red.
   * @param green the green.
   * @param blue  the blue.
   * @throws IllegalArgumentException the illegal argument exception
   */
  public Color(double red, double green, double blue)
          throws IllegalArgumentException {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Color values are invalid.");
    }

    this.red = red;
    this.green = green;
    this.blue = blue;
    this.colorName = "";
  }

  /**
   * Instantiates a new Color.
   *
   * @param colorName the color name
   * @param red       the red
   * @param green     the green
   * @param blue      the blue
   * @throws IllegalArgumentException the illegal argument exception
   */
  public Color(String colorName, double red, double green, double blue)
          throws IllegalArgumentException {

    if (colorName == null || colorName.isEmpty()) {
      throw new IllegalArgumentException("Color name cannot be empty!");
    }
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Color values are invalid.");
    }
    this.colorName = colorName;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Gets color name of the object.
   *
   * @return the color name
   */
  public String getColorName() {
    return this.colorName;
  }

  /**
   * Gets red.
   *
   * @return the red
   */
  public double getRed() {
    return this.red;
  }

  /**
   * Gets green.
   *
   * @return the green
   */
  public double getGreen() {
    return this.green;
  }

  /**
   * Gets blue.
   *
   * @return the blue
   */
  public double getBlue() {
    return this.blue;
  }


  /**
   * Changes color and sets it to the desired new color.
   *
   * @param newRed    the new red
   * @param newGreen  the new green
   * @param newBlue   the new blue
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void changeColor(double newRed, double newGreen, double newBlue)
          throws IllegalArgumentException {

    if (newRed < 0 || newRed > 255 || newBlue < 0 || newBlue > 255 || newGreen < 0
            || newGreen > 255) {
      throw new IllegalArgumentException("RGB Values are invalid!");
    }

    this.red = newRed;
    this.green = newGreen;
    this.blue = newBlue;
  }

  @Override
  public String toString() {
    DecimalFormat formatColor = new DecimalFormat("0.0");
    return "(" + formatColor.format(this.red) + ", " + formatColor.format(this.green) + ", "
            + formatColor.format(this.blue) + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Color color = (Color) o;
    return Double.compare(color.red, red) == 0 && Double.compare(color.green, green) == 0
            && Double.compare(color.blue, blue) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue);
  }
}
