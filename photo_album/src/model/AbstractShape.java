package model;

/**
 * The type AbstractShape implements the IShape interface.
 * It implements functionality that have been defined in the IShape interface common to all shapes.
 * It takes in certain parameters in its constructor (acts as super constructor for all its
 * concrete classes. Takes in shapeName(Unique), Type of shape, corner/centre of the shape,
 * color of the shape.
 * It implements basic getter/setter methods here which are common to all shapes and hence can
 * be implemented in a more generic class like this.
 */
public abstract class AbstractShape implements IShape {

  private String shapeName;
  private Type type;
  private Point startPoint;
  private Color color;

  /**
   * Instantiates a new Abstract shape.
   *
   * @param shapeName  the shape name
   * @param type       the type
   * @param startPoint the start point
   * @param color      the color
   */
  public AbstractShape(String shapeName, Type type, Point startPoint, Color color)
          throws IllegalArgumentException{
    if(shapeName == null || shapeName.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty.");
    }
    if(type == null) {
      throw new IllegalArgumentException("Type cannot be empty.");
    }
    if(startPoint == null) {
      throw new IllegalArgumentException("Point cannot be empty.");
    }
    if(color == null) {
      throw new IllegalArgumentException("Color cannot be empty.");
    }

    this.shapeName = shapeName;
    this.type = type;
    this.startPoint = startPoint;
    this.color = color;
  }

  @Override
  public String getName() {
    return this.shapeName;
  }

  @Override
  public Type getType() {
    return this.type;
  }

  @Override
  public String getTypeString() {
    return this.type.toString();
  }

  @Override
  public Point getPosition() {
    return this.startPoint;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public String getColorName() {
    return this.color.getColorName();
  }


  @Override
  public void moveTo(Point newPosition) throws IllegalArgumentException {
    if(newPosition == null) {
      throw new IllegalArgumentException("New Position cannot be empty.");
    }
    this.startPoint = newPosition;
  }

  @Override
  public void changeColor(Color newColor) throws IllegalArgumentException {
    if(newColor == null) {
      throw new IllegalArgumentException("New color cannot be empty.");
    }
    this.color = newColor;
  }

}

