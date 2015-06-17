package net.tudelft.hi.e.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.OutputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutFocusTraversalPolicy;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

import net.tudelft.hi.e.export.SettingsWriter;
import net.tudelft.hi.e.input.DataFile;
import net.tudelft.hi.e.input.Reader;
import net.tudelft.hi.e.input.Settings;

/**
 * A window that lets the user create XML settings based on an example file.
 */
public class SettingsGenerator extends JFrame {

  private static final long serialVersionUID = 1L;

  private File example;

  private JPanel mainPanel;

  private JLabel filePathLabel;

  private JPanel filePanel;

  private JPanel delimPanel;

  private JPanel startPanel;

  private JCheckBox headersCheckBox;

  private SpinnerNumberModel startSpinner;

  private JPanel bottomPanel;

  private JButton openFileButton;

  private JButton previewButton;

  private JTextField delimiterTextField;

  private ColumnSettingsPane csp;

  private Settings settings;

  private JTextField nameTextField;

  private JButton saveButton;
  
  private static FileNameExtensionFilter xmlfilter =
      new FileNameExtensionFilter("XML Setting Files", "xml");

  private static FileNameExtensionFilter allfilter =
      new FileNameExtensionFilter("All supported data files", "csv", "txt", "xlsx");
  
  private static final int GAP = 8;

  /**
   * Constructs a new settingswindow.
   */
  public SettingsGenerator() {
    super();

    setTitle("Settings Generator");

    setLayout(new BorderLayout(GAP, GAP));

    // Main panel
    mainPanel = new JPanel(new BorderLayout(GAP, GAP));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
    mainPanel.setLayout(new BorderLayout(GAP, GAP));
    add(mainPanel, BorderLayout.CENTER);

    // Filepanel

    filePanel = new JPanel(new BorderLayout(GAP, GAP));
    mainPanel.add(filePanel, BorderLayout.PAGE_START);

    filePathLabel = new JLabel("No file selected!");
    filePanel.add(filePathLabel, BorderLayout.LINE_START);

    openFileButton = new JButton("Choose Example...");
    filePanel.add(openFileButton, BorderLayout.LINE_END);

    openFileButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onOpenFile();
      }
    });

    // Delimiter panel

    delimPanel = new JPanel(new BorderLayout(GAP, GAP));
    filePanel.add(delimPanel, BorderLayout.PAGE_END);

    delimPanel.add(new JLabel("Delimiter: "), BorderLayout.LINE_START);

    delimiterTextField = new JTextField();
    delimPanel.add(delimiterTextField, BorderLayout.CENTER);

    previewButton = new JButton("Preview...");
    delimPanel.add(previewButton, BorderLayout.LINE_END);

    previewButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onPreview();
      }
    });

    // Line Start Panel

    startPanel = new JPanel(new BorderLayout(GAP, GAP));
    delimPanel.add(startPanel, BorderLayout.PAGE_START);

    startPanel.add(new JLabel("Starting line of data"), BorderLayout.CENTER);

    startSpinner = new SpinnerNumberModel(1, 1, 1000000, 1);
    startPanel.add(new JSpinner(startSpinner), BorderLayout.LINE_END);

    headersCheckBox = new JCheckBox("Has Headers");
    headersCheckBox.setSelected(true);
    startPanel.add(headersCheckBox, BorderLayout.LINE_START);

    // Center

    csp = ColumnSettingsPane.createPanel();
    csp.setPreferredSize(new Dimension(580, 200));
    mainPanel.add(csp, BorderLayout.CENTER);

    // Lower

    bottomPanel = new JPanel(new BorderLayout(GAP, GAP));
    mainPanel.add(bottomPanel, BorderLayout.PAGE_END);

    bottomPanel.add(new JLabel("Internal name:"), BorderLayout.LINE_START);

    nameTextField = new JTextField("table_name");
    bottomPanel.add(nameTextField, BorderLayout.CENTER);

    saveButton = new JButton("Save settings...");
    bottomPanel.add(saveButton, BorderLayout.LINE_END);

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onSave();
      }
    });

    setPreferredSize(new Dimension(700, 500));
    pack();
  }

  private void onOpenFile() {
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogType(JFileChooser.OPEN_DIALOG);
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    chooser.setFileFilter(allfilter);
    chooser.setDialogTitle("Select an example file to create settings for");

    int state = chooser.showOpenDialog(null);

    if (state == JFileChooser.APPROVE_OPTION) {
      example = chooser.getSelectedFile();
      filePathLabel.setText(example.getAbsolutePath());
      nameTextField.setText(example.getName());
    }
  }

  private void onPreview() {
    settings = new Settings();
    settings.setDelimiter(delimiterTextField.getText());
    Reader reader;
    try {
      reader = DataFile.createReader(example, settings);

      int rowsToSkip = startSpinner.getNumber().intValue() - 1;
      while (rowsToSkip > 0) {
        reader.readRow();
        rowsToSkip--;
      }

      int dataStart = startSpinner.getNumber().intValue();
      String[] firstdatarow;
      if (headersCheckBox.isSelected()) {
        String[] headers = reader.readRow();
        settings.setColumns(SettingsTableModel.generateColsByHeaders(headers));
        firstdatarow = reader.readRow();
        dataStart++;
      } else {
        firstdatarow = reader.readRow();
        settings.setColumns(SettingsTableModel.generateEmptyCols(firstdatarow.length));
      }
      settings.setStartLine(dataStart);
      TableModel tm = new SettingsTableModel(settings, firstdatarow);

      csp.setModel(tm);

    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void onSave() {
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogType(JFileChooser.SAVE_DIALOG);
    chooser.setFileFilter(xmlfilter);
    chooser.setDialogTitle("Save XML settings...");
    int state = chooser.showOpenDialog(null);

    if (state == JFileChooser.APPROVE_OPTION) {
      OutputStream out;
      try {
        settings.setName(nameTextField.getText());
        SettingsWriter.writeSettings(settings, chooser.getSelectedFile());
      } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Save XML settings failed",
            JOptionPane.ERROR_MESSAGE);
      }

    }
  }

  /**
   * Main method for stand-alone run.
   * 
   * @param args
   *          ignored
   */
  public static void main(String[] args) {
    SettingsGenerator test = new SettingsGenerator();
    test.setVisible(true);

  }

}
