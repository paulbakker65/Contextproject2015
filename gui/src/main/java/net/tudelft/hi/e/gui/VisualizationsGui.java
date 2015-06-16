package net.tudelft.hi.e.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import javax.swing.UIManager;

import net.tudelft.hi.e.common.exceptions.ParseFailedException;
import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.DateColumn;
import net.tudelft.hi.e.data.NumberColumn;
import net.tudelft.hi.e.data.StateTransitionMatrix;
import net.tudelft.hi.e.data.StemLeafPlot;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.TableFile;
import net.tudelft.hi.e.input.DataFile;
import net.tudelft.hi.e.input.Input;

/**
 * A GUI for selecting visualizations.
 */
public class VisualizationsGui extends JPanel implements ActionListener {
  private static final long serialVersionUID = 1L;

  private static final Logger LOG
      = Logger.getLogger(VisualizationsGui.class.getName());

  private JTextField datafile;
  private JTextField settings;
  private JButton openfile;
  private JButton opentable;

  private JTabbedPane tabbedPane;

  private JComboBox<String> comboFrequency;
  private JButton buttonFrequency;
  private JDesktopPane desktopFrequency;

  private JComboBox<String> comboBar;
  private JButton buttonBar;
  private JDesktopPane desktopBar;

  private JComboBox<String> comboStateT;
  private JButton buttonStateT;
  private JDesktopPane desktopStateT;

  private JComboBox<String> comboStemLeaf;
  private JButton buttonStemLeaf;
  private JTextField textStemLeaf;
  private JDesktopPane desktopStemLeaf;

  private JComboBox<String> comboHistogram;
  private JButton buttonHistogram;
  private JTextField textHistogram;
  private JDesktopPane desktopHistogram;

  private Table table;

  /**
   * Creates the Visualizations GUI.
   */
  public static void init(Table table) {
    String name = (table == null ? "" : " - " + table.getName());
    JFrame frame = new JFrame("Visualizations" + name);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JComponent contentPane = new VisualizationsGui(table);
    contentPane.setOpaque(true);
    contentPane.setPreferredSize(new Dimension(1024, 600));
    frame.setContentPane(contentPane);

    GUI.init(frame);
  }

  /**
   * Creates the GUI components, use init() instead.
   */
  public VisualizationsGui(Table table) {
    super(new BorderLayout());

    setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    createTabbedPane();
    add(tabbedPane, BorderLayout.CENTER);

    this.table = table;
    if (table == null) {
      add(createFilePanel(), BorderLayout.PAGE_START);
    } else {
      updateTabbedPane();
    }
  }



  private JPanel createFilePanel() {
    GridBagConstraints gbc;
    JPanel filepanel = new JPanel();
    filepanel.setLayout(new GridBagLayout());
    gbc = new GridBagConstraints();
    gbc.weightx = 3.0;
    gbc.weighty = 1.0;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(0, 0, 0, 5);

    gbc.gridy = 1;

    gbc.gridx = 1;
    filepanel.add(new JLabel("Data file:"), gbc);
    gbc.gridx = 2;
    filepanel.add(new JLabel("Settings file:"), gbc);

    gbc.gridy = 2;

    datafile = new JTextField();
    datafile.setEditable(false);
    gbc.gridx = 1;
    filepanel.add(datafile, gbc);

    settings = new JTextField();
    settings.setEditable(false);
    gbc.gridx = 2;
    filepanel.add(settings, gbc);

    openfile = new JButton("Open file");
    openfile.addActionListener(this);
    gbc.gridx = 3;
    gbc.weightx = 0.1;
    gbc.insets = new Insets(0, 0, 0, 0);
    filepanel.add(openfile, gbc);

    opentable = new JButton("Open table");
    opentable.addActionListener(this);
    gbc.gridx = 4;
    gbc.weightx = 0.1;
    gbc.insets = new Insets(0, 0, 0, 0);
    filepanel.add(opentable, gbc);

    return filepanel;
  }

  private JTabbedPane createTabbedPane() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    panel.add(Box.createHorizontalGlue());
    JLabel label = new JLabel("Please select a file first.");
    label.setFont(new Font(label.getFont().getName(), Font.BOLD, 15));
    panel.add(label);
    panel.add(Box.createHorizontalGlue());


    tabbedPane = new JTabbedPane();
    tabbedPane.setEnabled(false);
    ImageIcon icon = GUI.createImageIcon("icon.png");

    tabbedPane.addTab("Table Preview", icon, panel, "View a preview of the table");
    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

    tabbedPane.addTab("Frequency Bar", icon, null, "Create a Frequency Bar diagram");
    tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

    tabbedPane.addTab("Box Plot", icon, null, "Create a Box Plor diagram");
    tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

    tabbedPane.addTab("Transition Matrix", icon, null, "Create a Transition Matrix");
    tabbedPane.setMnemonicAt(3, KeyEvent.VK_5);

    tabbedPane.addTab("Stem and Leaf", icon, null, "Create a Stem and Leaf plot");
    tabbedPane.setMnemonicAt(4, KeyEvent.VK_6);

    tabbedPane.addTab("Histogram", icon, null, "Create a Histogram");
    tabbedPane.setMnemonicAt(5, KeyEvent.VK_7);

    return tabbedPane;
  }

  private JPanel createFrequencyPanel() {
    JPanel panel = new JPanel(new BorderLayout());

    JPanel top = new JPanel();
    panel.add(top,  BorderLayout.PAGE_START);
    top.add(new JLabel("Select a column: "));

    comboFrequency = new JComboBox<String>(getColumns());
    top.add(comboFrequency);

    buttonFrequency = new JButton("Start");
    buttonFrequency.addActionListener(this);
    top.add(buttonFrequency);

    desktopFrequency = createDesktopPane();
    panel.add(desktopFrequency, BorderLayout.CENTER);

    return panel;
  }

  private JPanel createBarChartPanel() {
    JPanel panel = new JPanel(new BorderLayout());

    JPanel top = new JPanel();
    panel.add(top, BorderLayout.PAGE_START);

    top.add(new JLabel("Select a column: "));

    List<Class<? extends Column>> allowed = new ArrayList<Class<? extends Column>>();

    allowed.add(NumberColumn.class);
    comboBar = new JComboBox<String>(getColumns(allowed, false));
    top.add(comboBar);

    buttonBar = new JButton("Start");
    buttonBar.addActionListener(this);
    top.add(buttonBar);

    desktopBar = createDesktopPane();
    panel.add(desktopBar, BorderLayout.CENTER);

    return panel;
  }

  private JPanel createStateTransitionPanel() {
    JPanel panel = new JPanel(new BorderLayout());

    JPanel top = new JPanel();
    panel.add(top, BorderLayout.PAGE_START);

    top.add(new JLabel("Select a column: "));

    List<Class<? extends Column>> allowed = new ArrayList<Class<? extends Column>>();
    allowed.add(DateColumn.class);
    comboStateT = new JComboBox<String>(getColumns(allowed, false));
    top.add(comboStateT);

    buttonStateT = new JButton("Start");
    buttonStateT.addActionListener(this);
    top.add(buttonStateT);

    desktopStateT = createDesktopPane();
    panel.add(desktopStateT, BorderLayout.CENTER);

    return panel;
  }

  private JPanel createStemandLeafPanel() {
    JPanel panel = new JPanel(new BorderLayout());

    JPanel top = new JPanel();
    panel.add(top, BorderLayout.PAGE_START);

    top.add(new JLabel("Select a column: "));

    List<Class<? extends Column>> allowed = new ArrayList<Class<? extends Column>>();
    allowed.add(NumberColumn.class);
    comboStemLeaf = new JComboBox<String>(getColumns(allowed, false));
    top.add(comboStemLeaf);

    top.add(new JLabel("Power: "));
    textStemLeaf = new JTextField("2");
    top.add(textStemLeaf);

    buttonStemLeaf = new JButton("Start");
    buttonStemLeaf.addActionListener(this);
    top.add(buttonStemLeaf);

    desktopStemLeaf = createDesktopPane();
    panel.add(desktopStemLeaf, BorderLayout.CENTER);

    return panel;
  }

  private JPanel createHistogramPanel() {
    JPanel panel = new JPanel(new BorderLayout());

    JPanel top = new JPanel();
    panel.add(top, BorderLayout.PAGE_START);

    top.add(new JLabel("Select a column: "));

    List<Class<? extends Column>> allowed = new ArrayList<Class<? extends Column>>();
    allowed.add(NumberColumn.class);
    comboHistogram = new JComboBox<String>(getColumns(allowed, false));
    top.add(comboHistogram);

    top.add(new JLabel("Power: "));
    textHistogram = new JTextField("2");
    top.add(textHistogram);

    buttonHistogram = new JButton("Start");
    buttonHistogram.addActionListener(this);
    top.add(buttonHistogram);

    desktopHistogram = createDesktopPane();
    panel.add(desktopHistogram, BorderLayout.CENTER);

    return panel;
  }

  private JDesktopPane createDesktopPane() {
    return new JDesktopPane() {
      private static final long serialVersionUID = 1L;

      @Override
      protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setColor(UIManager.getColor("Panel.background"));
        graphics.fillRect(0, 0, getWidth(), getHeight());
      }
    };
  }

  private void updateTabbedPane() {
    tabbedPane.setComponentAt(0, new DisplayTableGui(table));
    tabbedPane.setComponentAt(1, createFrequencyPanel());
    tabbedPane.setComponentAt(2, createBarChartPanel());
    tabbedPane.setComponentAt(3, createStateTransitionPanel());
    tabbedPane.setComponentAt(4, createStemandLeafPanel());
    tabbedPane.setComponentAt(5, createHistogramPanel());

    tabbedPane.setEnabled(true);
  }





  /**
   * Dispatch for button presses.
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    Object src = event.getSource();
    if (src == openfile) {
      onOpenFile();
    } else if (src == opentable) {
      onOpenTable();
    } else if (src == buttonFrequency) {
      onFrequency();
    } else if (src == buttonBar) {
      onBoxChart();
    } else if (src == buttonStateT) {
      onStateT();
    } else if (src == buttonStemLeaf) {
      onStemLeaf();
    } else if (src == buttonHistogram) {
      onHistogram();
    }
  }

  private void onOpenFile() {
    File dataFile;
    File settingsFile;

    dataFile = MainUI.openDataFile();
    if (dataFile == null) {
      return;
    }

    settingsFile = MainUI.openSettingsFile();
    if (settingsFile == null) {
      return;
    }

    try {
      Input.addDataFile(dataFile, settingsFile);
    } catch (Exception e) {
      // addDataFile will throw an exception if an error occurs when
      // creating the Reader and parsing the settings.
      JOptionPane.showMessageDialog(null, e.getMessage());
      return;
    }

    if (Input.getFiles().size() > 1) {
      Input.getFiles().remove(0);
    }

    DataFile df = Input.getFiles().get(0);
    try {
      table = df.getParser().parse(df.getReader());
    } catch (ParseFailedException ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
      return;
    }

    datafile.setText(dataFile.getAbsolutePath());
    settings.setText(settingsFile.getAbsolutePath());

    updateTabbedPane();
  }

  private void onOpenTable() {
    File tableFile = MainUI.openTableFile();

    if (tableFile == null) {
      return;
    }

    try {
      table = TableFile.readTable(tableFile);
      updateTabbedPane();
    } catch (IOException | ClassNotFoundException e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
    }
  }

  private void onFrequency() {
    String column = (String) comboFrequency.getSelectedItem();
      String title = "Frequency Chart {column:'" + column + "'}";
    JInternalFrame frame = GUI.createInternalFrame(title, FrequencyChart.createFrequencyChartPanel(table, column));
    desktopFrequency.add(frame);
  }

  private void onBoxChart() {
    String column = (String) comboBar.getSelectedItem();

      String title = "Bar Chart {column:'" + column + "'}";
    JInternalFrame frame = GUI.createInternalFrame(title, BoxPlotChart.createBoxPlotPanel(table, column));
    desktopBar.add(frame);
  }

  private void onStateT() {
    String column = (String) comboStateT.getSelectedItem();
    StateTransitionMatrix stm = new StateTransitionMatrix(table, column);
      String title = "State Transition Matrix {column:'" + column + "'}";
    desktopStateT.add(GUI.createInternalFrame(title, new DisplayTableGui(stm)));
  }

  private void onStemLeaf() {
    String column = (String) comboStemLeaf.getSelectedItem();
    int power = 2;
    try {
      power = Integer.parseInt(textStemLeaf.getText());
    } catch (NumberFormatException exception) {
      exception.printStackTrace();
      return;
    }
    StemLeafPlot slp = new StemLeafPlot(table, column, power);
      String title = "Stem and Leaf {column:'" + column + "', power:" + power + "}";
    desktopStemLeaf.add(GUI.createInternalFrame(title, new DisplayTableGui(slp)));
  }

  private void onHistogram() {
    String column = (String) comboHistogram.getSelectedItem();
    int power = 2;
    try {
      power = Integer.parseInt(textHistogram.getText());
    } catch (NumberFormatException exception) {
      exception.printStackTrace();
      return;
    }

     String title = "Histogram {column:'" + column + "', power: " + power + "}";
     JInternalFrame frame = GUI.createInternalFrame(title, HistogramChart.createPanel(table, column, power));
     desktopHistogram.add(frame);
  }





  /**
   * Gives a list of all columns the table contains.
   *
   * @param columntypes
   *          a list containing column classes. If set null, all class types are allowed.
   * @param isblacklist
   *          if true, columntypes is used as a blacklist, else it is used as a whitelist.
   * @return all the column names.
   */
  private String[] getColumns(List<Class<? extends Column>> columntypes, boolean isblacklist) {
    List<Column> columnlist = table.getColumns();
    ArrayList<String> columns = new ArrayList<String>();

    for (Column column : columnlist) {
      if (columntypes == null || columntypes.contains(column.getClass()) != isblacklist) {
        columns.add(column.getName());
      }
    }
    return columns.toArray(new String[0]);
  }

  private String[] getColumns() {
    return getColumns(null, false);
  }

  public static void main(String[] argv) {
    GUI.setSystemLook();
    VisualizationsGui.init(null);
  }
}
