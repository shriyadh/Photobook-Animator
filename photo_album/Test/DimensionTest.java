import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Dimension;

/**
 * The type Dimension test.
 */
public class DimensionTest {

  /**
   * The One.
   */
  private Dimension one;
  /**
   * The Two.
   */
  private Dimension two;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    one = new Dimension(3.13);
    two = new Dimension(710, 1.0);
  }

  /**
   * Test bad setup.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadSetup() {
    one = new Dimension(-76);
    two = new Dimension(100000, 0);
  }

  /**
   * Gets dimension one.
   */
  @Test
  public void getDimensionOne() {
    assertEquals(3.13, one.getDimensionOne(), 0.01);
    assertEquals(710.0, two.getDimensionOne(), 0.01);
  }

  /**
   * Gets dimension two.
   */
  @Test
  public void getDimensionTwo() {
    Dimension three = new Dimension(9999999, 100000000);
    assertEquals(1.0, two.getDimensionTwo(), 0.01);
    assertEquals(100000000, three.getDimensionTwo(), 0.01);
  }
}