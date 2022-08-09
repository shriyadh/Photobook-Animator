
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Color;
import model.Dimension;
import model.IShape;
import model.Oval;
import model.Point;
import model.Rectangle;
import model.Type;

/**
 * The type Shape test.
 * Kept all tests in package model to help differentiation when controller and view is implemented.
 */
public class IShapeTest {

  private IShape rectOne;
  private IShape ovalOne;
  private Point cornerOne;
  private Point centreOne;
  private Color colorRectOne;
  private Color colorOvalOne;
  private Dimension dimensionRectOne;
  private Dimension dimensionOvalOne;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {

    cornerOne = new Point(20, 7.7);
    colorRectOne = new Color("Red", 255, 0, 0);
    dimensionRectOne = new Dimension(20, 16.0);
    rectOne = new Rectangle("RectOne", Type.RECTANGLE, cornerOne,
            colorRectOne, dimensionRectOne);

    centreOne = new Point(0, 0);
    colorOvalOne = new Color("Blue", 0, 0, 255);
    dimensionOvalOne = new Dimension(3, 6);
    ovalOne = new Oval("OvalOne", Type.OVAL, centreOne, colorOvalOne, dimensionOvalOne);
  }

  /**
   * Test bad setup.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadSetup() {
    rectOne = new Rectangle("", Type.RECTANGLE, cornerOne,
            colorRectOne, dimensionRectOne);
    Rectangle rectB = new Rectangle("rectB", Type.RECTANGLE, cornerOne,
            null, dimensionRectOne);
    Rectangle rectC = new Rectangle("rectB", Type.RECTANGLE, cornerOne,
            colorRectOne, null);

    ovalOne = new Oval("OvalOne", null,
            centreOne, colorOvalOne, dimensionOvalOne);
    Oval ovalB = new Oval("OvalOne", Type.OVAL, null,
            colorOvalOne, dimensionOvalOne);
  }

  /**
   * Gets name.
   */
  @Test
  public void getName() {
    assertEquals("RectOne", rectOne.getName());
    assertEquals("OvalOne", ovalOne.getName());
  }

  /**
   * Gets type.
   */
  @Test
  public void getType() {
    assertEquals(Type.RECTANGLE, rectOne.getType());
    assertEquals(Type.OVAL, ovalOne.getType());
  }

  /**
   * Gets position.
   */
  @Test
  public void getPosition() {
    assertEquals("(20.0, 7.7)", rectOne.getPosition().toString());
    assertEquals("(0.0, 0.0)", ovalOne.getPosition().toString());
  }

  /**
   * Gets color.
   */
  @Test
  public void getColor() {
    assertEquals("(255.0, 0.0, 0.0)", rectOne.getColor().toString());
    assertEquals("(0.0, 0.0, 255.0)", ovalOne.getColor().toString());
  }

  /**
   * Move to.
   */
  @Test
  public void moveTo() {
    rectOne.moveTo(new Point(30, 100));
    ovalOne.moveTo(new Point(-98, -120));
    assertEquals("(30.0, 100.0)", rectOne.getPosition().toString());
    assertEquals("(-98.0, -120.0)", ovalOne.getPosition().toString());
  }

  /**
   * Test bad move to.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadMoveTo() {
    rectOne.moveTo(null);
    ovalOne.moveTo(null);
  }

  /**
   * Test change color.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColor() {
    rectOne.changeColor(null);
    ovalOne.changeColor(null);
  }

  /**
   * Change color.
   */
  @Test
  public void changeColor() {
    rectOne.changeColor(new Color("Blue", 0, 0, 255));
    ovalOne.changeColor(new Color("Red", 255, 0, 0));
    assertEquals("(0.0, 0.0, 255.0)", rectOne.getColor().toString());
    assertEquals("(255.0, 0.0, 0.0)", ovalOne.getColor().toString());
  }

  /**
   * Test bad resize.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadResize() {
    rectOne.resizeShape(null);
    ovalOne.resizeShape(null);
  }

  /**
   * Resize shape.
   */
  @Test
  public void resizeShape() {
    rectOne.resizeShape(new Dimension(19, 99));
    assertEquals("Width: 19.0, Height: 99.0", rectOne.getDimensions());

    ovalOne.resizeShape(new Dimension(999, 1023));
    assertEquals("X radius: 999.0, Y radius: 1023.0", ovalOne.getDimensions());
  }

  /**
   * Gets area.
   */
  @Test
  public void getArea() {
    assertEquals(320.0, rectOne.getArea(), 0.01);
    assertEquals(56.54, ovalOne.getArea(), 0.01);
  }

  /**
   * Test to string.
   */
  @Test
  public void testToString() {
    assertEquals("Name: RectOne\n"
                    + "Type: Rectangle\n"
                    + "Min corner: (20.0, 7.7), Width: 20.0, Height: 16.0,"
                    + " Color: (255.0, 0.0, 0.0)" + "\n",
            rectOne.toString());
    assertEquals("Name: OvalOne\n"
                    + "Type: Oval\n"
                    + "Center: (0.0, 0.0), X radius: 3.0, Y radius: 6.0, "
                    + "Color: (0.0, 0.0, 255.0)" + "\n",
            ovalOne.toString());
  }

  /**
   * Test equals.
   */
  @Test
  public void testEquals() {
    Rectangle rect = new Rectangle("RectOne", Type.RECTANGLE, cornerOne,
            colorRectOne, dimensionRectOne);
    assertEquals(rect, rectOne);

    Oval oval = new Oval("OvalOne", Type.OVAL, centreOne,
            colorOvalOne, dimensionOvalOne);
    assertEquals(oval, ovalOne);
  }
}