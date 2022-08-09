package view;

import java.io.IOException;
import java.util.List;

import model.Snapshot;

/**
 * The interface View.
 */
public interface IView {

  /**
   * Render web view.
   *
   * @param snapshots  the snapshots
   * @param appendable the appendable output file which will display the web view
   * @throws UnsupportedOperationException the unsupported operation exception
   * @throws IOException                   the io exception
   */
  default void renderWebView(List<Snapshot> snapshots, Appendable appendable)
          throws UnsupportedOperationException, IOException {
    throw new UnsupportedOperationException("Web view cannot provide graphical output.");
  }


  /**
   * Render graphical view based on the user instructions.
   *
   * @param snapshots the snapshots
   * @throws UnsupportedOperationException the unsupported operation exception
   */
  default void renderGraphicalView(List<Snapshot> snapshots)
          throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Graphical view cannot provide web view.");
  }


}
