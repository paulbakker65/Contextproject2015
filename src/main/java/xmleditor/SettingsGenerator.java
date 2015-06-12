package xmleditor;

import input.DataFile;
import input.Reader;
import input.Settings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A window that lets the user create XML settings based on an example file.
 */
public class SettingsGenerator extends JFrame {

  private static final long serialVersionUID = 1L;

  private File example;

  private JPanel mainPanel;

  private JPanel filePanel;

  private JPanel delimPanel;

  private JPanel bottomPanel;

  private JLabel filePathLabel;

  private JLabel delimiterLabel;

  private JButton openFileButton;

  private JButton previewButton;

  private JTextField delimiterTextField;

  private ColumnSettingsPane csp;

  private ColumnSettingsTableModel cstm;

  private JLabel nameLabel;

  private JTextField nameTextField;

  private JButton saveButton;

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

    delimiterLabel = new JLabel("Delimiter: ");
    delimPanel.add(delimiterLabel, BorderLayout.LINE_START);

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

    // Center

    csp = ColumnSettingsPane.createPanel();
    csp.setPreferredSize(new Dimension(580, 200));
    mainPanel.add(csp, BorderLayout.CENTER);

    // Lower

    bottomPanel = new JPanel(new BorderLayout(GAP, GAP));
    mainPanel.add(bottomPanel, BorderLayout.PAGE_END);

    nameLabel = new JLabel("Internal name:");
    bottomPanel.add(nameLabel, BorderLayout.LINE_START);

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
    chooser.setDialogTitle("Select an example file to create settings for");

    int state = chooser.showOpenDialog(null);

    if (state == JFileChooser.APPROVE_OPTION) {
      example = chooser.getSelectedFile();
      filePathLabel.setText(example.getAbsolutePath());
      nameTextField.setText(example.getName());
    }
  }

  private void onPreview() {
    Settings tempsettings = new Settings();
    tempsettings.setDelimiter(delimiterTextField.getText());
    Reader reader;
    try {
      reader = DataFile.createReader(example, tempsettings);
      String[] cols = reader.readRow();
      String[] firstrow = reader.readRow();
      cstm = new ColumnSettingsTableModel(cols, firstrow, delimiterTextField.getText(), "2");

      csp.setModel(cstm);

    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void onSave() {
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogType(JFileChooser.SAVE_DIALOG);
    chooser.setDialogTitle("Save XML settings...");
    int state = chooser.showOpenDialog(null);

    if (state == JFileChooser.APPROVE_OPTION) {
      OutputStream out;
      try {
        out = new FileOutputStream(chooser.getSelectedFile());
        cstm.export(out, nameTextField.getText());
        out.close();
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
