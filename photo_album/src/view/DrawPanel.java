package view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import model.IShape;
import model.Type;

/**
 * The type Draw panel is a JPanel that forms the middle area or the drawing panel for all the
 * shapes. It uses the paintComponent method to draw the shapes and repaint to actually implement.
 *
 */
public class DrawPanel extends JPanel {

  private List<IShape> shapes = null;

  /**
   * Instantiates a new Draw panel.
   */
  public DrawPanel() {
    super();
  }

  @Override
  public void paintComponent(Graphics graphics) throws NullPointerException {
    if(graphics == null) {
      throw new NullPointerException("Graphics cannot be null!");
    }

    super.paintComponent(graphics);

    if(this.shapes != null) {

      Graphics2D graphics2D = (Graphics2D) graphics;
      for(IShape shape : this.shapes) {
        graphics2D.setColor(new Color((int) shape.getColor().getRed(),
                (int) shape.getColor().getGreen(),
                (int) shape.getColor().getBlue()));

        if (shape.getType() == Type.OVAL) {
          graphics2D.fillOval((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                  (int) shape.getWidth(), (int) shape.getHeight());
        } else if (shape.getType() == Type.RECTANGLE) {
          graphics2D.fillRect((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                  (int) shape.getWidth(), (int) shape.getHeight());
        }

      }

    }
  }

  /**
   * Draws the shapes based on snapshot shapes.
   *
   * @param shapes the shapes
   * @throws NullPointerException the null pointer exception
   */
  public void draw(List<IShape> shapes) throws NullPointerException {
    if (shapes == null) {
      throw new NullPointerException("Shapes cannot be null.");
    }
    this.shapes = shapes;
    this.repaint();
  }
}
