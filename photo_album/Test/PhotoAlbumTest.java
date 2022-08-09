import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Color;
import model.Dimension;
import model.IShape;
import model.Oval;
import model.PhotoAlbum;
import model.Point;
import model.Rectangle;
import model.Type;


/**
 * The type Photo album test.
 * Kept all tests in package model to help differentiation when controller and view is implemented.
 */
public class PhotoAlbumTest {

  private IShape rectOne;
  private IShape ovalOne;
  private Point cornerOne;
  private Point centreOne;
  private Color colorRectOne;
  private Color colorOvalOne;
  private Dimension dimensionRectOne;
  private Dimension dimensionOvalOne;
  private PhotoAlbum album;

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

    album = PhotoAlbum.getInstance();
  }


  /**
   * Gets instance.
   */
  @Test
  public void getInstance() {
    PhotoAlbum instanceTwo = PhotoAlbum.getInstance();
    assertEquals(album, PhotoAlbum.getInstance());
    assertEquals(instanceTwo, album);
  }

  /**
   * Create shape.
   */
  @Test
  public void createShape() {
    album.resetAlbum();
    album.createShape("RectOnes", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    assertEquals(1, album.getSize());
    album.createShape("RectTwo", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("RectThree", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    assertEquals(3, album.getSize());

    album.createShape("OvalOnes", "oval", centreOne, dimensionOvalOne, colorOvalOne
    );
    album.createShape("OvalTwo", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);
    album.createShape("OvalThree", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);
    assertEquals(6, album.getSize());
  }

  /**
   * Test bad create shape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadCreateShape() {
    album.createShape("RectOne", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("Rectone", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);
    album.createShape("OvalOne", "", centreOne,
            dimensionOvalOne, colorOvalOne);
    album.createShape("Ooo", "oval", null,
            dimensionOvalOne, colorOvalOne);
    album.createShape("d", "RECTANGLE", cornerOne, dimensionRectOne,
            null);
    album.createShape("R", "RECTANGLE", cornerOne, null,
            colorRectOne);
  }


  /**
   * Gets shape.
   */
  @Test
  public void getShape() {
    album.resetAlbum();
    album.createShape("RectOne", "RECTANGLE", cornerOne, dimensionRectOne,
            colorRectOne);
    album.createShape("OvalOne", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);
    assertEquals(rectOne, album.getShape("rectone"));
    assertEquals(ovalOne, album.getShape("OVALone"));
  }

  /**
   * Test bad get shape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadGetShape() {
    album.createShape("RectOne", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("OvalOne", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);
    album.getShape("rectTwo");
    album.getShape("Ovalsss");
  }

  /**
   * Move shape.
   */
  @Test
  public void moveShape() {
    album.resetAlbum();
    album.createShape("RectOne", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("OvalOne", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);
    IShape tempRect = album.getShape("rectOne");
    assertEquals("(20.0, 7.7)", tempRect.getPosition().toString());
    album.moveShape("RECTone", new Point(0, 0));
    assertEquals("(0.0, 0.0)", tempRect.getPosition().toString());

    IShape tempOval = album.getShape("OvalOne");
    assertEquals("(0.0, 0.0)", tempOval.getPosition().toString());
    album.moveShape("OVALONE", new Point(2000, -1000));
    assertEquals("(2000.0, -1000.0)", tempOval.getPosition().toString());
  }


  /**
   * Test bad move shape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadMoveShape() {
    album.moveShape("OVALMitch", new Point(2000, -1000));
    album.moveShape("SHRIya", new Point(-19, -999));
    album.moveShape("OVALONE", null);
  }

  /**
   * Change shape color.
   */
  @Test
  public void changeShapeColor() {
    album.resetAlbum();
    album.createShape("RectOne", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("OvalOne", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);
    IShape tempRect = album.getShape("rectOne");
    assertEquals("(255.0, 0.0, 0.0)", tempRect.getColor().toString());
    assertEquals("Red", tempRect.getColorName());
    album.changeShapeColor("RECTone", new Color("Blue", 0, 0,
            255));
    assertEquals("(0.0, 0.0, 255.0)", tempRect.getColor().toString());
    assertEquals("Blue", tempRect.getColorName());

    IShape tempOval = album.getShape("OvalOne");
    assertEquals("(0.0, 0.0, 255.0)", tempOval.getColor().toString());
    assertEquals("Blue", tempOval.getColorName());
    album.changeShapeColor("OVALONE", new Color("Red", 255,
            0, 0));
    assertEquals("(255.0, 0.0, 0.0)", tempOval.getColor().toString());
    assertEquals("Red", tempOval.getColorName());
  }

  /**
   * Test bad change shape color.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadChangeShapeColor() {
    album.changeShapeColor("OVALONE", null);
    album.changeShapeColor("Shriya", new Color("Red", 255,
            0, 0));
    album.changeShapeColor("RectNEU", new Color("Red", 255,
            0, 0));
  }

  /**
   * Resize.
   */
  @Test
  public void resize() {
    album.resetAlbum();
    album.createShape("RectOne", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("OvalOne", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);
    // change width of rect
    IShape tempRect = album.getShape("rectOne");
    assertEquals("Width: 20.0, Height: 16.0", tempRect.getDimensions());
    album.resize("RECTone", new Dimension(200, 16));
    assertEquals("Width: 200.0, Height: 16.0", tempRect.getDimensions());

    // change width and height
    album.resize("RECTone", new Dimension(1500, 90));
    assertEquals("Width: 1500.0, Height: 90.0", tempRect.getDimensions());

    // change Radius X and Y
    IShape tempOval = album.getShape("OvalOne");
    assertEquals("X radius: 3.0, Y radius: 6.0", tempOval.getDimensions());
    album.resize("OVALONE", new Dimension(80, 99));
    assertEquals("X radius: 80.0, Y radius: 99.0", tempOval.getDimensions());
  }

  /**
   * Test bad resize.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadResize() {
    album.resize("OVALONE", null);
    album.resize("Shriya", new Dimension(80, 99));
    album.resize("RectNEU", new Dimension(80, 99));
  }

  /**
   * Remove shape.
   */
  @Test
  public void removeShape() {
    album.resetAlbum();
    album.createShape("RectOne", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("OvalOne", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);
    assertEquals(2, album.getSize());
    album.createShape("RectOnes", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    assertEquals(3, album.getSize());
    album.createShape("RectTwo", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("RectThree", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    assertEquals(5, album.getSize());

    album.createShape("OvalTwo", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);
    album.createShape("OvalThree", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);
    assertEquals(7, album.getSize());

    album.removeShape("rectone");
    assertEquals(6, album.getSize());
    album.removeShape("RectTwo");
    album.removeShape("RectThree");
    album.removeShape("OvalTwo");
    assertEquals(3, album.getSize());
    album.removeShape("OvalThree");
    album.removeShape("Ovalone");
    album.removeShape("Rectones");
    assertEquals(0, album.getSize());

  }

  /**
   * Test bad remove shape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadRemoveShape() {
    album.removeShape("Shriya");
    album.removeShape("Kennise");
    album.removeShape("");
  }


  /**
   * Test take and print snapshot.
   */
  @Test
  public void testTakeAndPrintSnapshot() {
    album.resetAlbum();
    album.createShape("RectOne", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("OvalOne", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);
    // first state
    assertEquals(2, album.getSize());
    album.takeSnapshot("First State of the Photo album");

    // adding shapes
    album.createShape("RectTwo", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("RectThree", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("Oval", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);
    album.createShape("OvalTwo", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);
    //take snap
    album.takeSnapshot("After Adding more photos!");

    // removing shapes
    album.removeShape("RectTwo");
    album.removeShape("RectThree");
    album.removeShape("OvalTwo");
    //take snap
    album.takeSnapshot("After deleting photos!");

    // change colors
    album.changeShapeColor("RECTone", new Color("Blue", 0, 0,
            255));
    album.changeShapeColor("OVALONE", new Color("Red", 255,
            0, 0));
    //take snap
    album.takeSnapshot("After changing color of photos!");

    // resizing shapes
    album.resize("RECTone", new Dimension(1500, 90));
    album.resize("OVALONE", new Dimension(80, 99));
    //take snap
    album.takeSnapshot("After resizing photos!");
    System.out.println(album.printSnapshots());
    // assert that photos in the albums have the same time stamp
    assertEquals(album.printSnapshots(), album.printSnapshots());
  }


  /**
   * Gets snapshots.
   */
  @Test
  public void getSnapshots() {

    album.resetAlbum();
    album.createShape("RectOne", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("OvalOne", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);    // first state
    assertEquals(2, album.getSize());
    //Take snapshot
    album.takeSnapshot("First State of the Photo album");

    // adding shapes
    album.createShape("RectTwo", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("RectThree", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("Oval", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);
    album.createShape("OvalTwo", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);
    //take snap
    album.takeSnapshot("After Adding more photos!");

    // removing shapes
    album.removeShape("RectTwo");
    album.removeShape("RectThree");
    album.removeShape("OvalTwo");
    //take snap
    album.takeSnapshot("After deleting photos!");

    // change colors
    album.changeShapeColor("RECTone", new Color("Blue", 0, 0,
            255));
    album.changeShapeColor("OVALONE", new Color("Red", 255,
            0, 0));
    //take snap
    album.takeSnapshot("After changing color of photos!");

    // resizing shapes
    album.resize("RECTone", new Dimension(1500, 90));
    album.resize("OVALONE", new Dimension(80, 99));
    //take snap
    album.takeSnapshot("After resizing photos!");
    System.out.println(album.getSnapshots());
    // assert that photos in the albums have the same time stamp
    assertEquals(album.getSnapshots(), album.getSnapshots());

  }


  /**
   * Show album.
   */
  @Test
  public void showAlbum() {
    album.resetAlbum();
    album.createShape("RectOne", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("OvalOne", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);

    assertEquals("Name: RectOne\n"
                    + "Type: Rectangle\n"
                    + "Min corner: (20.0, 7.7), Width: 20.0, Height: 16.0, Color: (255.0, 0.0, 0.0)\n"
                    + "\n"
                    + "Name: OvalOne\n"
                    + "Type: Oval\n"
                    + "Center: (0.0, 0.0), X radius: 3.0, Y radius: 6.0, Color: (0.0, 0.0, 255.0)\n\n",
            album.showAlbum());

    album.createShape("RectTwo", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("RectThree", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("Oval", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);

    assertEquals("Name: RectOne\n"
                    + "Type: Rectangle\n"
                    + "Min corner: (20.0, 7.7), Width: 20.0, Height: 16.0, Color: (255.0, 0.0, 0.0)\n"
                    + "\n"
                    + "Name: OvalOne\n"
                    + "Type: Oval\n"
                    + "Center: (0.0, 0.0), X radius: 3.0, Y radius: 6.0, Color: (0.0, 0.0, 255.0)\n"
                    + "\n"
                    + "Name: RectTwo\n"
                    + "Type: Rectangle\n"
                    + "Min corner: (20.0, 7.7), Width: 20.0, Height: 16.0, Color: (255.0, 0.0, 0.0)\n"
                    + "\n"
                    + "Name: RectThree\n"
                    + "Type: Rectangle\n"
                    + "Min corner: (20.0, 7.7), Width: 20.0, Height: 16.0, Color: (255.0, 0.0, 0.0)\n"
                    + "\n"
                    + "Name: Oval\n"
                    + "Type: Oval\n"
                    + "Center: (0.0, 0.0), X radius: 3.0, Y radius: 6.0, Color: (0.0, 0.0, 255.0)\n\n",
            album.showAlbum());

  }

  /**
   * Reset album.
   */
  @Test
  public void resetAlbum() {
    album.resetAlbum();
    album.createShape("RectOne", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("OvalOne", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);
    assertEquals(2, album.getSize());
    album.resetAlbum();
    assertEquals(0, album.getSize());
  }

  /**
   * Test get command set.
   */
  @Test
  public void testGetCommandSet() {
    album.resetAlbum();
    album.createShape("RectOne", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("OvalOne", "oval", centreOne,
            dimensionOvalOne, colorOvalOne);

    assertEquals("Whole album was reset.\n"
            + "Create Red Rectangle RectOne with corner at (20.0, 7.7), Width: 20.0,"
            + " Height: 16.0\n" + "Create Blue Oval OvalOne with centre at (0.0, 0.0), X radius:"
            + " 3.0, Y radius: 6.0\n", this.album.getCommandSet());

    album.changeShapeColor("OVALONE", new Color("Red", 255,
            0, 0));
    album.moveShape("RECTone", new Point(0, 0));
    album.removeShape("RecTONE");
    album.resize("OVALONE", new Dimension(80, 99));

    assertEquals("Whole album was reset.\n"
                    + "Create Red Rectangle RectOne with corner at (20.0, 7.7), Width: 20.0,"
                    + " Height: 16.0\n" + "Create Blue Oval OvalOne with centre at (0.0, 0.0), X radius:"
                    + " 3.0, Y radius: 6.0\n"
                    + "OvalOne changes from Blue to Red\nRectOne moves to (0.0, 0.0)\n"
                    + "RecTONE got removed from photo album.\n"
                    + "OVALONE was resized to X radius: 80.0, Y radius: 99.0\n",
            this.album.getCommandSet());
  }
}
