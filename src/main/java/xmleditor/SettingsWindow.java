package xmleditor;

import input.DataFile;
import input.Reader;
import input.Settings;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.xml.transform.TransformerException;

/**
 * Creates XML settings based on an example.
 * 
 * @author unset
 *
 */
public class SettingsWindow extends JFrame {

  private static final long serialVersionUID = 1L;

  private File example;

  private JLabel filePathLabel;

  private JLabel delimiterLabel;

  private JButton openFileButton;

  private JButton previewButton;

  private JTextField delimiterTextField;

  private ColumnSettingsPane csp;

  private ColumnSettingsTableModel cstm;

  private JTextField nameTextField;

  private JButton saveButton;

  public SettingsWindow() {
    super();

    setTitle("Settings Generator");

    setLayout(new FlowLayout());

    filePathLabel = new JLabel("No file selected!");
    add(filePathLabel);

    openFileButton = new JButton("Choose Example...");
    add(openFileButton);

    openFileButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onOpenFile();
      }
    });

    delimiterLabel = new JLabel("Delimiter: ");
    add(delimiterLabel);

    delimiterTextField = new JTextField();
    delimiterTextField.setPreferredSize(new Dimension(100, 30));
    add(delimiterTextField);

    previewButton = new JButton("Preview...");
    add(previewButton);

    previewButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onPreview();
      }
    });

    csp = new ColumnSettingsPane(new JTable());
    csp.setPreferredSize(new Dimension(580, 200));
    add(csp);

    nameTextField = new JTextField("table_name");
    nameTextField.setPreferredSize(new Dimension(200, 50));
    add(nameTextField);

    saveButton = new JButton("Save settings...");
    add(saveButton);

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

  public static void main(String[] args) {
    SettingsWindow m = new SettingsWindow();
    m.setVisible(true);

  }

}
