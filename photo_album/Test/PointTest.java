import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Point;


/**
 * The type Point test.
 */
public class PointTest {

  private Point a;
  private Point b;
  private Point c;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    a = new Point(0, 0);
    b = new Point(1000000, -9999999);
    c = new Point(-999, 10);
  }

  /**
   * Test to string.
   */
  @Test
  public void testToString() {
    assertEquals("(0.0, 0.0)", a.toString());
    assertEquals("(1000000.0, -9999999.0)", b.toString());
    assertEquals("(-999.0, 10.0)", c.toString());

  }
}