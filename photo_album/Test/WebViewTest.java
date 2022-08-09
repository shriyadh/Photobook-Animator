import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import model.Color;
import model.Dimension;
import model.IShape;
import model.PhotoAlbum;
import model.Point;
import model.Snapshot;
import view.WebView;

import static org.junit.Assert.assertEquals;

/**
 * The type Web view test.
 */
public class WebViewTest {

  private WebView webView;
  private PhotoAlbum album;
  private IShape rectOne;
  private IShape ovalOne;
  private Point cornerOne;
  private Point centreOne;
  private Color colorRectOne;
  private Color colorOvalOne;
  private Dimension dimensionRectOne;
  private Dimension dimensionOvalOne;
  private InputStream readFile;


  /**
   * Sets up.
   *
   * @throws IOException the io exception
   */
  @Before
  public void setUp() throws IOException {
    album = PhotoAlbum.getInstance();

    album.resetAlbum();
    cornerOne = new Point(20, 7.7);
    colorRectOne = new Color("Red", 255, 0, 0);
    dimensionRectOne = new Dimension(200, 160.0);

    centreOne = new Point(100, 100);
    colorOvalOne = new Color("Blue", 0, 0, 255);
    dimensionOvalOne = new Dimension(50, 60);

    // first snap
    album.createShape("RectOne", "RECTANGLE", cornerOne,
            dimensionRectOne, colorRectOne);
    album.createShape("OvalOne", "oval", new Point(400, 400),
            dimensionOvalOne, colorOvalOne);
    album.takeSnapshot("First State of the Photo album");

    // Second snap after adding shapes
    album.createShape("RectTwo", "RECTANGLE", new Point(100, 500),
            dimensionRectOne, colorRectOne);

    album.createShape("OvalTwo", "oval", new Point(700, 180),
            dimensionOvalOne, new Color(150, 150, 200));
    //take snap
    album.takeSnapshot("After Adding more photos!");


    String name = "myTest.html";
    PrintStream outputFile = new PrintStream(new File(name));
    Appendable appendable = outputFile;
    webView = new WebView(800, 800);
    List<Snapshot> s = album.getSnapshotObjects();
    System.out.println(s.size());
    webView.renderWebView(s, appendable);

    String curDir = System.getProperty("user.dir");
    String file = curDir + "//" + name;
    readFile = new FileInputStream(file);

  }

  /**
   * Render web view.
   *
   * @throws IOException the io exception
   */
  @Test
  public void renderWebView() throws IOException {

    Scanner scan = new Scanner(readFile);

    StringBuilder compare = new StringBuilder();
    while (scan.hasNextLine()) {
      String sc = scan.nextLine();

      sc = sc.strip();
      if (!sc.contains("<h1 class=\"snap-ID\">")) {
        compare.append(sc).append("\n");
      }
    }
    assertEquals("<!DOCTYPE HTML>\n" +
            "<html>\n" +
            "\n" +
            "<head>\n" +
            "<style>\n" +
            ".snapShot {border: 3px solid red; margin-bottom: 50px;}\n" +
            ".snap-head {background-color: lightblue; width=\"800px\" height=\"100px\"}\n" +
            ".snap-ID {margin-top: 0;}\n" +
            ".shapes {background-color: white; width=\"800\"px;height=\"900px\"}\n" +
            ".snap-description {margin: 0;}\n" +
            "</style>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "<div id = \"CS5004 Project\">\n" +
            "<div id=\"Snap1\" class=\"snapshot\">\n" +
            "<div class=\"snap-head\">\n" +
            "<h2 class=\"snap-description\">First State of the Photo album</h2>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"shapes\" style=\"position:relative;width:800px;height:900px;\">\n" +
            "<div id=\"shape1\">\n" +
            "<svg width=\"200.0\" height=\"160.0 \" style=\"position:" +
            "absolute;left:20.0;top:7.7;\">\n" +
            "<rect width= \"200\" height =\"160\" style=\"fill:rgb(255,0,0);" +
            " stroke-width:3;stroke:rgb(255,0,0);\" />\n" +
            "</svg>\n" +
            "</div>\n" +
            "<div id=\"shape2\">\n" +
            "<svg width=\"800\" height=\"900 \" style=\"position:absolute;left:0.0;top:0.0;\">\n" +
            "<ellipse cx=\"400.0\" cy=\"400.0\" rx= \"50.0\" ry =\"60.0\" style=\"fill:" +
            "rgb(0,0,255); stroke-width:3stroke:rgb(0,0,255);\" />\n" +
            "</svg>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "<div id=\"Snap4\" class=\"snapshot\">\n" +
            "<div class=\"snap-head\">\n" +
            "<h2 class=\"snap-description\">After Adding more photos!</h2>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"shapes\" style=\"position:relative;width:800px;height:900px;\">\n" +
            "<div id=\"shape4\">\n" +
            "<svg width=\"200.0\" height=\"160.0 \" style=\"position:absolute;left:20.0;top:7." +
            "7;\">\n" +
            "<rect width= \"200\" height =\"160\" style=\"fill:rgb(255,0,0); stroke-width:3;strok" +
            "e:rgb(255,0,0);\" />\n" +
            "</svg>\n" +
            "</div>\n" +
            "<div id=\"shape5\">\n" +
            "<svg width=\"800\" height=\"900 \" style=\"position:absolute;left:0.0;top:0.0;\">\n" +
            "<ellipse cx=\"400.0\" cy=\"400.0\" rx= \"50.0\" ry =\"60.0\" style=\"fill:rgb(0,0," +
            "255); stroke-width:3stroke:rgb(0,0,255);\" />\n" +
            "</svg>\n" +
            "</div>\n" +
            "<div id=\"shape6\">\n" +
            "<svg width=\"200.0\" height=\"160.0 \" style=\"position:absolute;left:100.0;top:" +
            "500.0;\">\n" +
            "<rect width= \"200\" height =\"160\" style=\"fill:rgb(255,0,0); stroke-width:3;s" +
            "troke:rgb(255,0,0);\" />\n" +
            "</svg>\n" +
            "</div>\n" +
            "<div id=\"shape7\">\n" +
            "<svg width=\"800\" height=\"900 \" style=\"position:absolute;left:0.0;top:0.0;\">\n" +
            "<ellipse cx=\"700.0\" cy=\"180.0\" rx= \"50.0\" ry =\"60.0\" style=\"fill" +
            ":rgb(150,150,200); stroke-width:3stroke:rgb(150,150,200);\" />\n" +
            "</svg>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>\n", compare.toString());
  }


}