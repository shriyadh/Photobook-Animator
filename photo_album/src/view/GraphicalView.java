package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import model.IShape;
import model.Snapshot;

/**
 * The type Graphical view renders a graphical view for the user based on instructions in the
 * file/readable. The graphical view uses JAVAs SWING to implement the Graphical User Interface
 * for the user. The entire view extends JFrame and adds Action Listeners to provide
 * interactive buttons to the user which allows the viewer to load a particular snapshot, scroll
 * back to the previous snapshot, move on tp the next snapshot, view the description of snapshots
 * or exit the program.
 */
public class GraphicalView extends JFrame implements IView, ActionListener {

  private JPanel panelBottom;
  private JPanel panelUp;
  private JPanel panelMenu;

  private DrawPanel drawPanel;
  private JButton previous;
  private JButton next;
  private JButton select;
  private JButton exit;
  private JButton viewInfo;
  private List<Snapshot> snapshots;
  private Snapshot currentSnap;
  private JLabel description;
  private JLabel ID;
  private int currentIndex;


  /**
   * Instantiates a new Graphical view.
   *
   * @param dimensionOne the dimension one
   * @param dimensionTwo the dimension two
   */
  public GraphicalView(int dimensionOne, int dimensionTwo) {

    this.setSize(dimensionOne, dimensionTwo);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JScrollPane pane = new JScrollPane(this.drawPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    this.setLayout(new BorderLayout());
    this.setTitle("CS5004 PhotoAlbum");
    this.add(pane);
    this.setResizable(false);

    this.drawPanel = new DrawPanel();
    this.drawPanel.setBackground(Color.white);
    this.drawPanel.setPreferredSize(new Dimension(dimensionOne, dimensionTwo));
    this.add(drawPanel, BorderLayout.CENTER);


    // adding bottom panel
    panelBottom = new JPanel();
    this.panelBottom.setBackground(Color.BLACK);
    this.panelBottom.setPreferredSize(new Dimension(100, 50));
    previous = new JButton("Previous");
    next = new JButton("Next");
    select = new JButton("Select");
    exit = new JButton("Exit");
    previous.addActionListener(this);
    next.addActionListener(this);
    select.addActionListener(this);
    exit.addActionListener(this);
    previous.setFocusable(false);
    next.setFocusable(false);
    select.setFocusable(false);
    exit.setFocusable(false);
    this.panelBottom.add(previous);
    this.panelBottom.add(next);
    this.panelBottom.add(select);
    this.panelBottom.add(exit);
    this.add(panelBottom, BorderLayout.SOUTH);

    // adding upper panel
    this.panelUp = new JPanel();
    this.panelUp.setBackground(Color.pink);
    this.panelUp.setPreferredSize(new Dimension(100, 40));
    this.viewInfo = new JButton("View SnapShot Information here!");
    this.viewInfo.addActionListener(this);
    this.viewInfo.setFocusable(false);
    this.panelUp.add(viewInfo);
    this.add(panelUp, BorderLayout.NORTH);

    this.description = new JLabel();
  }

  @Override
  public void renderGraphicalView(List<Snapshot> snapshots) {
    this.snapshots = snapshots;
    this.currentSnap = snapshots.get(0);
    this.currentIndex = 0;
    List<IShape> shapes = currentSnap.getShapesInSnapshot();
    this.drawPanel.draw(shapes);
    this.setVisible(true);
  }

  private void drawLoadedSnap(Snapshot snap) {
    this.drawPanel.draw(snap.getShapesInSnapshot());
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // previous button
    if (e.getSource() == previous) {
      currentIndex--;
      if (currentIndex < 0) {
        this.currentIndex = 0;
        JOptionPane.showMessageDialog(this, "No More Snaps!",
                "Error", JOptionPane.ERROR_MESSAGE);
      } else {
        if (!this.viewInfo.isVisible()) {
          this.viewInfo.setVisible(true);
        }
        if (this.description.isVisible()) {
          this.description.setVisible(false);
        }
        this.currentSnap = snapshots.get(currentIndex);
        this.drawLoadedSnap(snapshots.get(currentIndex));
      }
    }

    // next button
    else if (e.getSource() == next) {
      currentIndex++;
      if (currentIndex >= snapshots.size()) {
        this.currentIndex--;
        JOptionPane.showMessageDialog(this, "No More Snaps!",
                "Error", JOptionPane.ERROR_MESSAGE);
      } else {
        if (!this.viewInfo.isVisible()) {
          this.viewInfo.setVisible(true);
        }
        if (this.description.isVisible()) {
          this.description.setVisible(false);
        }
        this.currentSnap = snapshots.get(currentIndex);
        this.drawLoadedSnap(snapshots.get(currentIndex));
      }
    }

    // select snapshot
    else if (e.getSource() == select) {
      String[] snapID = new String[snapshots.size()];

      for (int i = 0; i < snapID.length; i++) {
        snapID[i] = snapshots.get(i).getID();
      }

      Object selectedSnap = JOptionPane.showInputDialog(null,
              "Choose a snapshot to display!",
              "Select Snapshot", JOptionPane.INFORMATION_MESSAGE, null,
              snapID, snapID[0]);
     // System.out.println(selectedSnap);

      if (selectedSnap != null) {
        if (!this.viewInfo.isVisible()) {
          this.viewInfo.setVisible(true);
        }
        if (this.description.isVisible()) {
          this.description.setVisible(false);
        }

        for (int j = 0; j < snapshots.size(); j++) {
          Snapshot temp = snapshots.get(j);
          if (temp.getID().equalsIgnoreCase(selectedSnap.toString())) {
            currentIndex = j;
            currentSnap = temp;
            break;
          }
        }
        this.drawLoadedSnap(currentSnap);
      }
    }

    // exit button
    else if (e.getSource() == exit) {
      int result = JOptionPane.showConfirmDialog(this,
              "Are you sure you want to exit?",
              "Exit", JOptionPane.YES_NO_CANCEL_OPTION);
      if (result == 0) {
        System.exit(0);
      }
    }

    // view snapshot description
    else if (e.getSource() == viewInfo) {
      this.viewInfo.setVisible(false);
      description = new JLabel();
      ID = new JLabel();
      String info = this.currentSnap.getID() + "  " + this.currentSnap.getDescription();
      description.setText(info);
      description.setForeground(Color.BLACK);
      description.setFont(new Font("MV Boli", Font.BOLD, 15));
      description.setHorizontalTextPosition(JLabel.CENTER);
      description.setVerticalTextPosition(JLabel.TOP);
      this.panelUp.add(description);
    }
  }

  /**
   * Gets current snap.
   *
   * @return the current snap
   */
  public Snapshot getCurrentSnap() {
    return this.currentSnap;
  }

  /**
   * Gets snapshots list.
   *
   * @return the snapshots list
   */
  public List<Snapshot> getSnapshotsList() {
    return this.snapshots;
  }

  /**
   * Gets current index.
   *
   * @return the current index
   */
  public int getCurrentIndex() {
    return this.currentIndex;
  }

}


