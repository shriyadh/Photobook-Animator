
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Color;

/**
 * The type Color test.
 * Kept all tests in package model to help differentiation when controller and view is implemented.
 */
public class ColorTest {

  private Color red;
  private Color blue;
  private Color green;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    red = new Color("RED", 255, 0, 0);
    blue = new Color("Blue", 0, 0, 225);
    green = new Color("green", 0, 225, 0);
  }

  /**
   * Test bad setup.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadSetup() {
    red = new Color("", 255, 0, 0);
    blue = new Color("Blue", -9, 0, 225);
    green = new Color("green", 0, -225, 0);

  }

  /**
   * Gets color name.
   */
  @Test
  public void getColorName() {
    assertEquals("RED", red.getColorName());
    assertEquals("Blue", blue.getColorName());
    assertEquals("green", green.getColorName());
  }

  /**
   * Test get red.
   */
  @Test
  public void testGetRed() {
    assertEquals(255.0, red.getRed(), 0.01);
    assertEquals(0, green.getRed(), 0.01);
    assertEquals(0, blue.getRed(), 0.01);
  }

  /**
   * Test get blue.
   */
  @Test
  public void testGetBlue() {
    assertEquals(0.0, red.getBlue(), 0.01);
    assertEquals(0.0, green.getBlue(), 0.01);
    assertEquals(225.0, blue.getBlue(), 0.01);
  }

  /**
   * Test get green.
   */
  @Test
  public void testGetGreen() {
    assertEquals(0.0, red.getGreen(), 0.01);
    assertEquals(225.0, green.getGreen(), 0.01);
    assertEquals(0.0, blue.getGreen(), 0.01);
  }

  /**
   * Change color.
   */
  @Test
  public void changeColor() {
    red.changeColor(128, 0, 0);
    assertEquals(128.00, red.getRed(), 0.01);
    assertEquals(0.00, red.getBlue(), 0.01);
    assertEquals(0.00, red.getGreen(), 0.01);


    green.changeColor(165, 42, 42);
    assertEquals(165.00, green.getRed(), 0.01);
    assertEquals(42.00, green.getBlue(), 0.01);
    assertEquals(42.00, green.getGreen(), 0.01);

    blue.changeColor(255, 192, 203);
    assertEquals(255.00, blue.getRed(), 0.01);
    assertEquals(192.00, blue.getGreen(), 0.01);
    assertEquals(203.00, blue.getBlue(), 0.01);
  }

  /**
   * Test bad change color.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadChangeColor() {
    red.changeColor(-128, 0, 0);
    green.changeColor(165, -42, 42);
    blue.changeColor(255, 192, -203);
  }

  /**
   * Test to string.
   */
  @Test
  public void testToString() {
    assertEquals("(255.0, 0.0, 0.0)", red.toString());
    assertEquals("(0.0, 0.0, 225.0)", blue.toString());
    assertEquals("(0.0, 225.0, 0.0)", green.toString());
  }
}