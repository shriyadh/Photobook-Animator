import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import controller.Controller;
import controller.IController;
import model.PhotoAlbum;
import view.GraphicalView;
import view.WebView;

/**
 * The type Controller test.
 * NOTE: The parser has been tested by passing in multiple text files which ultimately produces
 * appropriate output.
 * ALL TEST FILES INCLUDED!
 */
public class IControllerTest {

  private IController one;
  private IController two;
  private IController three;
  private IController four;


  /**
   * Sets up.
   *
   * @throws Exception the exception
   */
  @Before
  public void setUp() throws Exception {
    two = new Controller(PhotoAlbum.getInstance(), "web",
            new WebView(800, 800), "demo_input.txt",
            "myTest3.html");

    one = new Controller(PhotoAlbum.getInstance(), "web",
            new WebView(800, 800), "buildings.txt",
            "myTest2.html");

    three = new Controller(PhotoAlbum.getInstance(), "web",
            new WebView(800, 800), "playground.txt",
            "myTest4.html");

    four = new Controller(PhotoAlbum.getInstance(), "web",
            new WebView(800, 800), "face_sun.txt",
            "myTest5.html");

  }

  /**
   * Test bad set up.
   *
   * @throws FileNotFoundException the file not found exception
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadSetUp() throws FileNotFoundException {
    three = new Controller(null, "Graphical",
            new GraphicalView(800, 800), "buildings.txt",
            "myTest2.html");
    three = new Controller(PhotoAlbum.getInstance(), "",
            new GraphicalView(800, 800), "buildings.txt",
            "myTest2.html");
    three = new Controller(PhotoAlbum.getInstance(), "Graphical",
            null, "buildings.txt",
            "myTest2.html");
    three = new Controller(PhotoAlbum.getInstance(), "Graphical",
            new GraphicalView(800, 800), "",
            "myTest2.html");

  }

  /**
   * Run controller.
   *
   * @throws IOException the io exception
   */
  @Test
  public void runController() throws IOException {
    // runs and creates SVG without error
    one.runController();
    two.runController();
    three.runController();
    four.runController();
  }
}