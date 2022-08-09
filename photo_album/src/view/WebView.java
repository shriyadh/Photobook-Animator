package view;

import java.io.IOException;
import java.util.List;

import model.IShape;
import model.Rectangle;
import model.Snapshot;

/**
 * The type Web view creates a web view for the user based on instructions in file.
 * Uses SVG and HTML to implement the web page.
 */
public class WebView implements IView {

  private int dimensionOne;
  private int dimensionTwo;

  /**
   * Instantiates a new Web view.
   *
   * @param dimensionOne the dimension one
   * @param dimensionTwo the dimension two
   */
  public WebView(int dimensionOne, int dimensionTwo) {
    this.dimensionOne = dimensionOne;
    this.dimensionTwo = dimensionTwo;
  }

  @Override
  public void renderWebView(List<Snapshot> snapshots, Appendable appendable)
          throws IOException {

    try {
      appendable.append("<!DOCTYPE HTML>\n");

      appendable.append("<html>\n\n"
              + "\t\t<head>\n"
              + "\t\t\t<style>\n"
              + "\t\t\t.snapShot {border: 3px solid red; margin-bottom: 50px;} \n"
              + "\t\t\t.snap-head {background-color: lightblue; width=\"" + this.dimensionOne
              + "px\" height=\"100px\"}\n"
              + "\t\t\t.snap-ID {margin-top: 0;}\n"
              + "\t\t\t.shapes {background-color: white; width=\"" + this.dimensionOne
              + "\"px;height=\"" + (this.dimensionTwo + 100) + "px\"}\n"
              + "\t\t\t.snap-description {margin: 0;}\n"
              + "\t\t\t</style>\n"
              + "\t\t</head> \n\n"
              + "\t\t<body> \n"
              + "\t\t\t\t<div id = \"CS5004 Project\">\n");
    } catch (Exception e) {
      throw new IOException("Error --- Not appendable");
    }

    int counter = 1;
    for (Snapshot snapshot : snapshots) {

      String description;
      if (snapshot.getDescription().equals("")) {
        description = "No Description";
      } else {
        description = snapshot.getDescription();
      }

      try {
        appendable.append("\t\t\t\t\t\t<div id=\"Snap" + counter + "\" class=\"snapshot\">\n"
                + "\t\t\t\t\t\t\t\t<div class=\"snap-head\">\n"
                + "\t\t\t\t\t\t\t\t\t\t<h1 class=\"snap-ID\">" + snapshot.getID() + "</h1>\n"
                + "\t\t\t\t\t\t\t\t\t\t<h2 class=\"snap-description\">" + description
                + "</h2>\n"
                + "\t\t\t\t\t\t\t\t</div>\n\n");

      } catch (Exception e) {
        throw new IOException("Error --- Not appendable");
      }
      // for each snapshot
      List<IShape> photos = snapshot.getShapesInSnapshot();
      appendable.append("\t\t\t\t\t\t\t\t<div class=\"shapes\" "
              + "style=\"position:relative;width:"
              + this.dimensionOne + "px;height:"
              + (this.dimensionTwo + 100) + "px;\">\n ");


      int innerCounter = 1;
      for (IShape shape : photos) {
        if (shape instanceof Rectangle) {

          try {
            // for each shape
            appendable.append("\t\t\t\t\t\t\t\t\t\t<div id=\"shape" + counter + "\">\n");

            appendable.append("\t\t\t\t\t\t\t\t\t\t\t\t<svg width=\"" + shape.getWidth()
                    + "\" height=\"" + shape.getHeight()
                    + " \" style=\"position:absolute;left:" + shape.getPosition().getX()
                    + ";top:" + shape.getPosition().getY() + ";\"" + ">\n");
            appendable.append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<rect width= \""
                    + (int) shape.getWidth()
                    + "\" height =\"" + (int) shape.getHeight()
                    + "\" style=\"fill:rgb(" + (int) shape.getColor().getRed() + ","
                    + (int) shape.getColor().getGreen() + ","
                    + (int) shape.getColor().getBlue() + "); stroke-width:3;stroke:rgb("
                    + (int) shape.getColor().getRed() + ","
                    + (int) shape.getColor().getGreen() + ","
                    + (int) shape.getColor().getBlue() + ");\" />\n");
            appendable.append("\t\t\t\t\t\t\t\t\t\t\t\t</svg>\n");
            appendable.append("\t\t\t\t\t\t\t\t\t\t</div>\n");

          } catch (IOException ignore) {
            throw new IOException("Error --- Not appendable");
          }
        } else {
          try {

            // for each shape
            appendable.append("\t\t\t\t\t\t\t\t\t\t<div id=\"shape" + counter + "\">\n");

            appendable.append("\t\t\t\t\t\t\t\t\t\t\t\t<svg width=\"" + this.dimensionOne
                    + "\" height=\"" + (this.dimensionTwo + 100)
                    + " \" style=\"position:absolute;left:" + 0.0 + ";top:" + 0.0
                    + ";\"" + ">\n");
            appendable.append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<ellipse cx=\""
                    + shape.getPosition().getX() + "\" cy=\""
                    + shape.getPosition().getY() + "\" rx= \"" + shape.getWidth()
                    + "\" ry =\"" + shape.getHeight()
                    + "\" style=\"fill:rgb(" + (int) shape.getColor().getRed() + ","
                    + (int) shape.getColor().getGreen() + ","
                    + (int) shape.getColor().getBlue() + "); "
                    + "stroke-width:3stroke:rgb("
                    + (int) shape.getColor().getRed() + ","
                    + (int) shape.getColor().getGreen() + ","
                    + (int) shape.getColor().getBlue() + ");\" />\n");
            appendable.append("\t\t\t\t\t\t\t\t\t\t\t\t</svg>\n");
            appendable.append("\t\t\t\t\t\t\t\t\t\t</div>\n");

          } catch (IOException e) {
            throw new IOException("Error --- Not appendable");
          }
        }
        counter++;
      }
      try {
        appendable.append("</div>\n");
        appendable.append("</div>\n");
      } catch (IOException e) {
        throw new IOException("Error --- Not appendable");
      }
      counter++;
    }
    try {
      appendable.append("\t\t</div>\n");
      appendable.append("\t\t\t\t</body>\n");
      appendable.append("</html>\n");
    } catch (IOException e) {
      throw new IOException("Error --- Not appendable");
    }
  }
}


