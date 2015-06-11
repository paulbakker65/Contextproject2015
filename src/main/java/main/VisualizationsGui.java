package main;

import input.DataFile;
import input.Input;

import table.StateTransitionMatrix;
import table.StemLeafPlot;
import table.Table;
import table.TableFile;
import table.value.Column;
import table.value.ColumnTypeMismatchException;
import table.value.DateColumn;
import table.value.NumberColumn;

import visualizations.BoxPlotChart;
import visualizations.FrequencyChart;
import visualizations.HistogramChart;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * A GUI for selecting visualizations.
 */
public class VisualizationsGui extends JPanel implements ActionListener {
  private static final long serialVersionUID = 1L;

  private JTextField datafile;
  private JTextField settings;
  private JButton openfile;
  private JButton opentable;

  private JTabbedPane tabbedPane;

  private JComboBox<String> comboFrequency;
  private JButton buttonFrequency;
  private JComboBox<String> comboBar;
  private JButton buttonBar;
  private JButton buttonPie;
  private JComboBox<String> comboStateT;
  private JButton buttonStateT;
  private JComboBox<String> comboStemLeaf;
  private JButton buttonStemLeaf;
  private JTextField textStemLeaf;
  private JComboBox<String> comboHistogram;
  private JButton buttonHistogram;
  private JTextField textHistogram;

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
    tabbedPane = new JTabbedPane();
    tabbedPane.setEnabled(false);
    ImageIcon icon = GUI.createImageIcon("icon.png");

    JPanel emptypanel = new JPanel();
    emptypanel.add(new JLabel("Please select a file first."));

    tabbedPane.addTab("Frequency Bar", icon, emptypanel, "Create a Frequency Bar diagram");
    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

    tabbedPane.addTab("Box Plot", icon, new JPanel(), "Create a Box Plor diagram");
    tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

    tabbedPane.addTab("Pie Chart", icon, new JPanel(), "Create a Pie Chart");
    tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

    tabbedPane.addTab("Transition Matrix", icon, new JPanel(), "Create a Transition Matrix");
    tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

    tabbedPane.addTab("Stem and Leaf", icon, new JPanel(), "Create a Stem and Leaf plot");
    tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);

    tabbedPane.addTab("Histogram", icon, new JPanel(), "Create a Histogram");
    tabbedPane.setMnemonicAt(5, KeyEvent.VK_6);
    
    return tabbedPane;
  }
  
  private JPanel createFrequencyPanel() {
    JPanel panel = new JPanel();
    panel.add(new JLabel("Select a column: "));

    comboFrequency = new JComboBox<String>(getColumns());
    panel.add(comboFrequency);

    buttonFrequency = new JButton("Start");
    buttonFrequency.addActionListener(this);
    panel.add(buttonFrequency);

    return panel;
  }

  private JPanel createPieChartPanel() {
    JPanel panel = new JPanel();

    buttonPie = new JButton("Start");
    buttonPie.addActionListener(this);
    panel.add(buttonPie);

    return panel;
  }

  private JPanel createBarChartPanel() {
    JPanel panel = new JPanel();
    panel.add(new JLabel("Select a column: "));

    List<Class<? extends Column>> allowed = new ArrayList<Class<? extends Column>>();

    allowed.add(NumberColumn.class);
    comboBar = new JComboBox<String>(getColumns(allowed, false));
    panel.add(comboBar);

    buttonBar = new JButton("Start");
    buttonBar.addActionListener(this);
    panel.add(buttonBar);

    return panel;
  }

  private JPanel createStateTransitionPanel() {
    JPanel panel = new JPanel();
    panel.add(new JLabel("Select a column: "));
    
    List<Class<? extends Column>> allowed = new ArrayList<Class<? extends Column>>();
    allowed.add(DateColumn.class);

    comboStateT = new JComboBox<String>(getColumns(allowed, false));
    panel.add(comboStateT);

    buttonStateT = new JButton("Start");
    buttonStateT.addActionListener(this);
    panel.add(buttonStateT);

    return panel;
  }

  private JPanel createStemandLeafPanel() {
    JPanel panel = new JPanel();
    panel.add(new JLabel("Select a column: "));

    List<Class<? extends Column>> allowed = new ArrayList<Class<? extends Column>>();
    allowed.add(NumberColumn.class);
    comboStemLeaf = new JComboBox<String>(getColumns(allowed, false));
    panel.add(comboStemLeaf);

    panel.add(new JLabel("Power: "));

    textStemLeaf = new JTextField("2");
    panel.add(textStemLeaf);

    buttonStemLeaf = new JButton("Start");
    buttonStemLeaf.addActionListener(this);
    panel.add(buttonStemLeaf);

    return panel;
  }

  private JPanel createHistogramPanel() {
    JPanel panel = new JPanel();
    panel.add(new JLabel("Select a column: "));

    List<Class<? extends Column>> allowed = new ArrayList<Class<? extends Column>>();
    allowed.add(NumberColumn.class);
    comboHistogram = new JComboBox<String>(getColumns(allowed, false));
    panel.add(comboHistogram);

    panel.add(new JLabel("Power: "));

    textHistogram = new JTextField("2");
    panel.add(textHistogram);

    buttonHistogram = new JButton("Start");
    buttonHistogram.addActionListener(this);
    panel.add(buttonHistogram);

    return panel;
  }

  private void updateTabbedPane() {
    tabbedPane.setComponentAt(0, createFrequencyPanel());
    tabbedPane.setComponentAt(1, createBarChartPanel());
    tabbedPane.setComponentAt(2, createPieChartPanel());
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
    } else if (src == buttonPie) {
      onPieChart();
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
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return;
    } catch (ColumnTypeMismatchException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
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
    FrequencyChart fc = new FrequencyChart("Frequency", table, column);
    GUI.init(fc);
  }

  private void onBoxChart() {
    String column = (String) comboBar.getSelectedItem();
    BoxPlotChart bc = new BoxPlotChart("Title", table, column);
    bc.pack();
    GUI.centreWindow(bc);
    GUI.setIconImage(bc);
    bc.setVisible(true);
  }

  private void onPieChart() {
    /*
     * PieChart demo = new PieChart("Comparison", "Which operating system are you using?");
     * demo.pack(); GUI.setIconImage(demo); GUI.centreWindow(demo); demo.setVisible(true);
     */
  }

  private void onStateT() {
    String column = (String) comboStateT.getSelectedItem();
    StateTransitionMatrix stm = new StateTransitionMatrix(table, column);
    DisplayTableGui.init(stm);
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
    DisplayTableGui.init(slp);
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

    HistogramChart hchart = new HistogramChart(table, column, power);
    GUI.init(hchart);
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
