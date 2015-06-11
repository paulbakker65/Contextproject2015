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

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * A window that lets the user create XML settings based on an example file.
 */
public class SettingsWindow extends JFrame {

  private static final long serialVersionUID = 1L;

  private File example;

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

  /**
   * Constructs a new settingswindow.
   */
  public SettingsWindow() {
    super();

    setTitle("Settings Generator");

    setLayout(new BorderLayout());

    // Filepanel

    filePanel = new JPanel(new BorderLayout());
    add(filePanel, BorderLayout.PAGE_START);

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

    delimPanel = new JPanel(new BorderLayout());
    filePanel.add(delimPanel, BorderLayout.PAGE_END);

    delimiterLabel = new JLabel("Delimiter: ");
    delimPanel.add(delimiterLabel, BorderLayout.LINE_START);

    delimiterTextField = new JTextField();
    delimiterTextField.setPreferredSize(new Dimension(100, 30));
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

    csp = new ColumnSettingsPane(new JTable());
    csp.setPreferredSize(new Dimension(580, 200));
    add(csp, BorderLayout.CENTER);

    // Lower

    bottomPanel = new JPanel(new BorderLayout());
    add(bottomPanel, BorderLayout.PAGE_END);

    nameLabel = new JLabel("Internal name:");
    bottomPanel.add(nameLabel, BorderLayout.LINE_START);

    nameTextField = new JTextField("table_name");
    nameTextField.setPreferredSize(new Dimension(200, 50));
    bottomPanel.add(nameTextField, BorderLayout.CENTER);

    saveButton = new JButton("Save settings...");
    bottomPanel.add(saveButton, BorderLayout.LINE_END);

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onSave();
      }
    });

    setPreferredSize(new Dimension(600, 400));
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
    SettingsWindow test = new SettingsWindow();
    test.setVisible(true);

  }

}
