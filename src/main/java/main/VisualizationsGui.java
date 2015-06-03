package main;

import input.Input;
import table.value.Column;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

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
public class VisualizationsGui extends JPanel implements ActionListener{
  private static final long serialVersionUID = 1L;
  
  private JTextField datafile;
  private JTextField settings;
  private JButton openfile;
  
  private JTabbedPane tabbedPane;
  
  private JComboBox<String> comboFrequency;
  private JButton buttonFrequency;
  private JButton buttonBar;
  private JButton buttonPie;
  private JComboBox<String> comboStateT;
  private JButton buttonStateT;

  /**
   * Creates the Visualizations GUI.
   */
  public static void init() {
    JFrame frame = new JFrame("Visualizations");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JComponent contentPane = new VisualizationsGui();
    contentPane.setOpaque(true);
    contentPane.setPreferredSize(new Dimension(1024, 600));
    frame.setContentPane(contentPane);

    frame.pack();
    GUI.setIconImage(frame);
    GUI.centreWindow(frame);
    frame.setVisible(true);
  }
  
  /**
   * Creates the GUI components, use init() instead.
   */
  public VisualizationsGui() {
    super(new BorderLayout());
    
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
    
    
    
    tabbedPane = new JTabbedPane();
    tabbedPane.setEnabled(false);
    ImageIcon icon = createImageIcon("icon.png");
    
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
    
    add(filepanel, BorderLayout.PAGE_START);
    add(tabbedPane, BorderLayout.CENTER);
    setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
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
    buttonBar = new JButton();
    return new JPanel();
  }
  
  private JPanel createStateTransitionPanel() {
    JPanel panel = new JPanel();
    panel.add(new JLabel("Select a column: "));
    
    comboStateT = new JComboBox<String>(getColumns());
    panel.add(comboStateT);
    
    buttonStateT = new JButton("Start");
    buttonStateT.addActionListener(this);
    panel.add(buttonStateT);
    
    return panel;
  }
  
  private void updateTabbedPane() {
    tabbedPane.setComponentAt(0, createFrequencyPanel());
    tabbedPane.setComponentAt(1, createBarChartPanel());
    tabbedPane.setComponentAt(2, createPieChartPanel());
    tabbedPane.setComponentAt(3, createStateTransitionPanel());
    
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
    } else if (src == buttonFrequency) {
      onFrequency();
    } else if (src == buttonBar) {
      onBarChart();
    } else if (src == buttonPie) {
      onPieChart();
    } else if (src == buttonStateT) {
      onStateT();
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
      //addDataFile will throw an exception if an error occurs when 
      //creating the Reader and parsing the settings.
      JOptionPane.showMessageDialog(null, e.getMessage());
      return;
    }

    if (Input.getFiles().size() > 1) {
      Input.getFiles().remove(0);
    }
    datafile.setText(dataFile.getAbsolutePath());
    settings.setText(settingsFile.getAbsolutePath());
    
    updateTabbedPane();
  }

  private void onFrequency() {
    
  }
  
  private void onBarChart() {
    
  }
  
  private void onPieChart() {
    /*
    PieChart demo = new PieChart("Comparison", "Which operating system are you using?");
    demo.pack();
    GUI.setIconImage(demo);
    GUI.centreWindow(demo);
    demo.setVisible(true);
    */
  }
  
  private void onStateT() {
    
  }
  


  
  private static ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = ClassLoader.getSystemResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }
  
  /**
   * Gives a list of all columns the selected settings file specifies.
   */
  public String[] getColumns() {
    ArrayList<Column> columnlist = Input.getFiles().get(0).getSettings().getColumns();
    String[] columns = new String[columnlist.size()];
    for (int i = 0; i < columnlist.size(); i++) {
      columns[i] = columnlist.get(i).getName();
    }
    return columns;
  }
  
  public static void main(String[] argv) {
    GUI.setSystemLook();
    VisualizationsGui.init();
  }
}
