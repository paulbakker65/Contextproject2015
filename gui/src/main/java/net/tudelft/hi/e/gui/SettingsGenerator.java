package net.tudelft.hi.e.gui;

import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.DateColumn;
import net.tudelft.hi.e.export.SettingsWriter;
import net.tudelft.hi.e.input.DataFile;
import net.tudelft.hi.e.input.Reader;
import net.tudelft.hi.e.input.Settings;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

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

  private static FileNameExtensionFilter allfilter = new FileNameExtensionFilter(
      "All supported data files", "csv", "txt", "xlsx");

  private static final int GAP = 8;

  private static final Logger LOG = Logger.getLogger(SettingsGenerator.class.getName());

  /**
   * Constructs a new settings window.
   */
  public SettingsGenerator() {
    super();

    setTitle("Settings Generator");

    setMainPanel();
    setFilePanel();
    setDelimiterField();
    setPreviewButton();
    setStartPanel();
    setCenterPanel();
    setBottomButtons();

    setPreferredSize(new Dimension(700, 500));
    pack();

  }

  private void setMainPanel() {
    // Main panel
    mainPanel = new JPanel(new BorderLayout(GAP, GAP));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
    mainPanel.setLayout(new BorderLayout(GAP, GAP));
    add(mainPanel, BorderLayout.CENTER);
  }

  private void setFilePanel() {
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
  }

  private void setDelimiterField() {
    delimPanel = new JPanel(new BorderLayout(GAP, GAP));
    filePanel.add(delimPanel, BorderLayout.PAGE_END);

    delimPanel.add(new JLabel("Delimiter: "), BorderLayout.LINE_START);

    delimiterTextField = new JTextField();
    delimPanel.add(delimiterTextField, BorderLayout.CENTER);
  }

  private void setPreviewButton() {
    previewButton = new JButton("Preview...");
    delimPanel.add(previewButton, BorderLayout.LINE_END);

    previewButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onPreview();
      }
    });

  }

  private void setStartPanel() {
    startPanel = new JPanel(new BorderLayout(GAP, GAP));
    delimPanel.add(startPanel, BorderLayout.PAGE_START);

    startPanel.add(new JLabel("Starting line of data"), BorderLayout.CENTER);

    startSpinner = new SpinnerNumberModel(1, 1, 1000000, 1);
    startPanel.add(new JSpinner(startSpinner), BorderLayout.LINE_END);

    headersCheckBox = new JCheckBox("Has Headers");
    headersCheckBox.setSelected(true);
    startPanel.add(headersCheckBox, BorderLayout.LINE_START);

  }

  private void setCenterPanel() {
    csp = ColumnSettingsPane.createPanel();
    csp.setPreferredSize(new Dimension(580, 200));
    mainPanel.add(csp, BorderLayout.CENTER);
  }

  private void setBottomButtons() {
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
      csp.showPreviewHint();
    }
  }

  private void onPreview() {
    settings = new Settings();
    if (example == null) {
      JOptionPane.showMessageDialog(this, "Need to select a file before you can preview it",
          "File not specified", JOptionPane.WARNING_MESSAGE, Gui.createImageIcon("icon.png"));
      return;
    }
    if (delimiterTextField.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "Need to specify the delimiter of the selected file",
          "Delimiter not specified", JOptionPane.WARNING_MESSAGE, Gui.createImageIcon("icon.png"));
      return;
    }
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
    if (noFormat()) {
      return;
    }
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    chooser.setDialogType(JFileChooser.OPEN_DIALOG);
    chooser.setDialogTitle("Save XML settings...");
    int state = chooser.showOpenDialog(null);

    if (state == JFileChooser.APPROVE_OPTION) {
      try {
        settings.setName(nameTextField.getText());
        SettingsWriter.writeSettings(settings, new File(chooser.getSelectedFile() + "/"
            + nameTextField.getText() + ".xml"));
        Object[] options = {"Yes", "No", "Close settings builder"};
        int chosenOption =
            JOptionPane.showOptionDialog(this, nameTextField.getText()
                + ".xml was created in the directory " + chooser.getSelectedFile()
                + "\n Would you like to see the output directory?", "A Silly Question",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                Gui.createImageIcon("icon.png"), options, options[2]);
        if (chosenOption == JOptionPane.CANCEL_OPTION) {
          this.dispose();
        }
        if (chosenOption == JOptionPane.YES_OPTION) {
          try {
            Desktop.getDesktop().open(chooser.getSelectedFile());
          } catch (IOException e1) {
            LOG.log(Level.SEVERE, "Output directory could not be opened");
          }
        }

      } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Save XML settings failed",
            JOptionPane.ERROR_MESSAGE);
      }

    }
  }

  /**
   * Checks if a format is typed when date is selected.
   * 
   * @return boolean if no format is specified.
   */
  private boolean noFormat() {
    for (Column column : settings.getColumns()) {
      if (column.getType().equals("date")) {
        DateColumn dateColumn = (DateColumn) column;
        if (dateColumn.getFormatStr().isEmpty()) {
          JOptionPane.showMessageDialog(this, "Need to specify the format of the datacolumn: "
              + column.getName(), "Format not specified", JOptionPane.WARNING_MESSAGE,
              Gui.createImageIcon("icon.png"));
          return true;
        }
      }
    }
    return false;
  }
}
