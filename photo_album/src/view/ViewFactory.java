package view;

/**
 * The type View factory generates the type of view object as specified by the user.
 * For now, it is limited to generating web or graphical views only.
 */
public class ViewFactory {

  /**
   * Generates the object of the desired view as specified by the user.
   *
   * @param view         the view
   * @param dimensionOne the dimension one
   * @param dimensionTwo the dimension two
   * @return the view
   * @throws IllegalArgumentException the illegal argument exception
   */
  public static IView chooseView(String view, int dimensionOne, int dimensionTwo)
          throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("Error --- View type cannot be empty!");
    }
    switch (view) {
      case "GRAPHICAL":
        return new GraphicalView(dimensionOne, dimensionTwo);

      case "WEB":
        return new WebView(dimensionOne, dimensionTwo);

      default:
        throw new IllegalArgumentException("Error --- Invalid view type!");
    }
  }
}
