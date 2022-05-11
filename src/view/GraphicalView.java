package view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import controller.Features;
import model.IShape;
import model.Snapshot;

import static javax.swing.JOptionPane.CLOSED_OPTION;

/**
 * This class represents an interactive graphical view. A graphical view has a title, and maximum
 * x and y values that specify the bounds of the view window.
 */
public class GraphicalView extends JFrame implements GUIView {
  private JPanel topPanel;
  private DrawPanel middlePanel;
  private JPanel bottomPanel;
  private JLabel id = new JLabel();
  private JLabel description = new JLabel();
  private JButton prevButton;
  private JButton selectButton;
  private JButton nextButton;
  private JButton quitButton;
  private int xMax;
  private int yMax;

  /**
   * Constructs a GraphicalView instantiated to the given title, and maximum x and y values that
   * specify the bounds of the view window.
   * @param title (String) title for the window.
   * @param xMax (String) maximum x value that specifies the horizontal bound of the view window.
   * @param yMax (String) maximum y value that specifies the vertical bound of the view window.
   */
  public GraphicalView(String title, String xMax, String yMax) {
    // Set up window (title, close, size)
    super(title);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.xMax = Integer.parseInt(xMax);
    this.yMax = Integer.parseInt(yMax);
  }

  @Override
  public void produceView(List<Snapshot> snapshots) {
    // Create description panel (top)
    this.topPanel = new JPanel();
    this.topPanel.setBackground(Color.PINK);
    this.topPanel.setLayout(new GridLayout(2, 1));
    this.topPanel.add(this.id);
    this.topPanel.add(this.description);
    this.add(this.topPanel, BorderLayout.NORTH);

    // Create button panel (bottom)
    this.bottomPanel = new JPanel();
    this.bottomPanel.setBackground(Color.ORANGE);
    this.add(this.bottomPanel, BorderLayout.SOUTH);

    // Set up buttons and add to bottom button panel
    this.prevButton = new JButton("<< Prev <<");
    this.prevButton.setActionCommand("Previous Button");
    this.bottomPanel.add(this.prevButton);

    this.selectButton = new JButton("^^ Select ^^");
    this.selectButton.setActionCommand("Select Button");
    this.bottomPanel.add(this.selectButton);

    this.nextButton = new JButton(">> Next >>");
    this.nextButton.setActionCommand("Next Button");
    this.bottomPanel.add(this.nextButton);

    this.quitButton = new JButton("xx Quit xx");
    this.quitButton.setActionCommand("Quit Button");
    this.bottomPanel.add(this.quitButton);

    // Create photo panel (middle) for displaying snapshot and make it scrollable if necessary
    this.middlePanel = new DrawPanel();
    JScrollPane scrollPane = new JScrollPane(this.middlePanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.add(scrollPane);

    // Size the frame and make all components visible
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void addFeatures(Features features) {
    // Connect previousSnapshot callback to the clicking of the Previous Button
    this.prevButton.addActionListener(evt -> features.previousSnapshot());

    // Connect menu callback to the clicking of the Select Button
    this.selectButton.addActionListener(evt -> features.menu());

    // Connect nextSnapshot callback to the clicking of the Next Button
    this.nextButton.addActionListener(evt -> features.nextSnapshot());

    // Connect quitProgram callback to the clicking of the Quit Button
    this.quitButton.addActionListener(evt -> features.quitProgram());
  }

  @Override
  public int displayMenu(Object[] options, int currentIndex) {
    // Display the menu for the user
    Object selectedValue = JOptionPane.showInputDialog(this, "Choose",
            "Menu", JOptionPane.PLAIN_MESSAGE, null, options, options[currentIndex]);

    // Check if option was chosen
    if (selectedValue == null) {
      return CLOSED_OPTION;
    }

    // Check which option was chosen
    for (int i = 0; i < options.length; i++) {
      if (options[i].equals(selectedValue)) {
        return i;
      }
    }
    return CLOSED_OPTION;
  }

  @Override
  public void displayErrorMessage() {
    JOptionPane.showMessageDialog(this, "End of the photo album. No "
            + "snapshots to show beyond this one.");
  }

  @Override
  public void paintSnapshot(List<IShape> shapes) {
    this.middlePanel.setCurrentSnapshot(shapes);
    this.repaint();
  }

  @Override
  public void setShapeInformation(String id, String description) {
    this.id.setText(id);
    this.description.setText(description);
  }


  /**
   * This class represents the panel in the window that draws the desired snapshot.
   */
  private class DrawPanel extends JPanel {
    private List<IShape> currentSnapshot;

    /**
     * Constructs a DrawPanel object.
     */
    public DrawPanel() {
    }

    /**
     * Updates the current snapshot to be displayed on the panel.
     * @param currentShapes (List</IShape>) list of shapes in the snapshot to be displayed.
     */
    public void setCurrentSnapshot(List<IShape> currentShapes) {
      this.currentSnapshot = currentShapes;
    }

    @Override
    public Dimension getPreferredSize() {
      // Set the size of the panel
      return new Dimension(xMax, yMax);
    }

    @Override
    public void paintComponent(Graphics g) {
      // Let UI delegate paint first
      super.paintComponent(g);

      // Now paint my components (background and shapes from snapshot)
      setBackground(new Color(51, 153, 255));
      for (IShape each : this.currentSnapshot) {
        each.draw(g);
      }
    }
  }
}