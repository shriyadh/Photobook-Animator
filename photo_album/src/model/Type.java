package model;

/**
 * The enum Type to represent any 2D shape.
 */
public enum Type {

  /**
   * Rectangle type.
   */
  RECTANGLE("Rectangle"),
  /**
   * Oval type.
   */
  OVAL("Oval");

  private String type;

  Type(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return this.type;
  }
}
