package main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * MainUI class implementing the main Graphical User Interface.
 * 
 */
public class MainUI extends JDialog {
  private static final long serialVersionUID = 1L;

  // All GUI components:
  private JPanel contentPane;
  private JButton buttonRunScript;
  private JButton buttonCancel;
  private JTextField textFieldscriptfilepath;
  private JButton openFileButton;
  private JButton editScriptButton;
  private FilesTable filesTable;
  private JButton addFileSButton;
  private JButton removeSelectedButton;
  private JPanel filesPanel;
  private JTextField textFieldOutputDir;
  private JButton browseButton;
  private JButton viewDirectoryButton;

  // Filters for JFileChooser dialog
  private FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("XML files", "xml");
  private FileNameExtensionFilter csvfilter = new FileNameExtensionFilter("CSV and TXT files",
      "csv", "txt");
  //private FileNameExtensionFilter xlsfilter = new FileNameExtensionFilter("Excel files", "xls");

  private File scriptFile;
  private File outputDir;
  private ArrayList<DataFile> files;

  private File previousDirectory;
  private boolean exit = false;

  public MainUI() {
    setSystemLook();
    setContentPane(contentPane);
    setModal(true);
    setModalityType(ModalityType.APPLICATION_MODAL);

    getRootPane().setDefaultButton(buttonRunScript);

    // call onCancel() on ESCAPE
    contentPane
        .registerKeyboardAction(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            onCancel();
          }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    // call onCancel() when cross is clicked
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        onCancel();
      }
    });

    openFileButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        onOpenScript();
      }
    });

    editScriptButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        onEditScript();
      }
    });

    browseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        onSelectOutputDir();
      }
    });

    viewDirectoryButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        onViewOutputDir();
      }
    });

    addFileSButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        onAddNewFile();
      }
    });

    removeSelectedButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        onRemoveFile();
      }
    });

    buttonRunScript.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onRunScript();
      }
    });

    buttonCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onCancel();
      }
    });
  }

  /**
   * Opens a open file dialog for the user to select the script file.
   */
  private void onOpenScript() {
    JFileChooser chooser = new JFileChooser(previousDirectory);
    chooser.setDialogTitle("Open a script file.");

    int of = chooser.showOpenDialog(null);

    if (of == JFileChooser.APPROVE_OPTION) {
      previousDirectory = chooser.getCurrentDirectory();
      scriptFile = chooser.getSelectedFile();
      textFieldscriptfilepath.setText(scriptFile.getPath());
      System.out.println(scriptFile);
    }
  }

  /**
   * Opens the default system editor for the script file. If no script file has been specified, it
   * will prompt the user to do so.
   */
  private void onEditScript() {
    if (scriptFile == null) {
      int option = JOptionPane.showConfirmDialog(null,
          "No scipt file selected.\nWould you like to select one now?", "No script file.",
          JOptionPane.YES_NO_OPTION);
      if (option == JOptionPane.YES_OPTION) {
        onOpenScript();
      } else {
        return;
      }
    }

    if (scriptFile == null) {
      return;
    }

    try {
      Desktop.getDesktop().open(scriptFile);
    } catch (IOException e1) {
      System.out.println("Error trying to open default editor.");
      e1.printStackTrace();
    }
  }

  /**
   * Opens a open file dialog for the user to select an output directory.
   */
  private void onSelectOutputDir() {

    JFileChooser chooser = new JFileChooser(previousDirectory); // Passing null is allowed :)
    chooser.setDialogType(JFileChooser.OPEN_DIALOG);
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    chooser.setDialogTitle("Save output to.");

    int state = chooser.showSaveDialog(null);

    if (state == JFileChooser.APPROVE_OPTION) {

      previousDirectory = chooser.getCurrentDirectory();
      outputDir = chooser.getSelectedFile();

      if (!outputDir.isDirectory()) {
        outputDir = null;
        textFieldOutputDir.setText(null);
        return;
      }
      textFieldOutputDir.setText(outputDir.getPath());
    }
  }

  /**
   * Opens the default system program to view the directory. If no output directory has been
   * specified, it will prompt the user to do so.
   */
  private void onViewOutputDir() {
    if (outputDir == null) {
      int option = JOptionPane.showConfirmDialog(null,
          "No output directory selected.\nWould you like to select one now?",
          "No output directory.", JOptionPane.YES_NO_OPTION);
      if (option == JOptionPane.YES_OPTION) {
        onSelectOutputDir();
      } else {
        return;
      }
    }

    if (outputDir == null || !outputDir.isDirectory()) {
      return;
    }

    try {
      Desktop.getDesktop().open(outputDir);
    } catch (IOException e1) {
      System.out.println("Error trying to view the directory.");
      e1.printStackTrace();
    }
  }

  /**
   * Opens a open file dialog for the user to select a data file and a settings file. When the user
   * is done, the file wil be displayed in the files table.
   */
  private void onAddNewFile() {
    File dataFile;
    File settingsFile;

    JFileChooser chooser = new JFileChooser(previousDirectory);
    chooser.setDialogTitle("Open data file.");
    chooser.setFileFilter(csvfilter);
    // chooser.setFileFilter(xlsfilter);

    int state = chooser.showOpenDialog(null);

    if (state == JFileChooser.APPROVE_OPTION) {

      previousDirectory = chooser.getCurrentDirectory();
      dataFile = chooser.getSelectedFile();
      System.out.println("Selected data file: " + dataFile);
    } else {
      return;
    }

    JFileChooser chooser2 = new JFileChooser(previousDirectory);
    chooser2.setDialogTitle("Open settings file.");
    chooser2.setFileFilter(xmlfilter);

    state = chooser2.showOpenDialog(null);

    if (state == JFileChooser.APPROVE_OPTION) {

      previousDirectory = chooser2.getCurrentDirectory();
      settingsFile = chooser2.getSelectedFile();
      System.out.println("Selected settings file: " + settingsFile);
    } else {
      return;
    }

    // create DataFile and add it to the table.
    DataFile file;
    try {
      file = new DataFile(dataFile, settingsFile);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
      return;
    }
    
    filesTable.addRow(file);
  }

  /**
   * Removes the selected row from the files table.
   */
  private void onRemoveFile() {
    int selectedRows[] = filesTable.getSelectedRows();
    if (selectedRows.length > filesTable.getRowCount()) {
      return;
    }

    // Sort the index list, rows with a higher index should be removed before rows with a lower
    // index to prevent indices from changing.
    Arrays.sort(selectedRows);

    for (int i = selectedRows.length - 1; i >= 0; i--) {
      filesTable.removeRow(selectedRows[i]);
    }
  }

  /**
   * Validates that the user has specified the required files and exits the gui.
   */
  private void onRunScript() {
    files = filesTable.getFiles();

    if (scriptFile == null || scriptFile.exists() == false || outputDir == null
        || outputDir.exists() == false || files.isEmpty()) {
      JOptionPane
          .showMessageDialog(
              null,
              "Please make sure you have selected a script file, output directory and at least 1 data file.",
              "Wrong input.", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    dispose();
  }

  /**
   * Exits the program.
   */
  private void onCancel() {
    dispose();
    exit = true;
  }

  /**
   * This method will place the dialog in the center of the screen.
   */
  public void centreWindow() {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) dimension.getWidth() / 2 - getWidth() / 2;
    int y = (int) dimension.getHeight() / 2 - getHeight() / 2;
    setLocation(x, y);
  }

  /**
   * Sets the gui to use visuals similar to the operating system, instead of the java gui visuals.
   */
  private void setSystemLook() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }

  public File getScriptFile() {
    return scriptFile;
  }

  public File getOutputDir() {
    return outputDir;
  }

  public ArrayList<DataFile> getFiles() {
    return files;
  }

  
  public boolean isExit() {
    return exit;
  }


  {
    // GUI initializer generated by IntelliJ IDEA GUI Designer
    // >>> IMPORTANT!! <<<
    // DO NOT EDIT OR ADD ANY CODE HERE!
    $$$setupUI$$$();
  }

  /**
   * Method generated by IntelliJ IDEA GUI Designer >>> IMPORTANT!! <<< DO NOT edit this method OR
   * call it in your code!
   *
   * @noinspection ALL
   */
  private void $$$setupUI$$$() {
    contentPane = new JPanel();
    contentPane.setLayout(new GridBagLayout());
    contentPane.setPreferredSize(new Dimension(800, 600));
    filesPanel = new JPanel();
    filesPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc;
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(5, 5, 5, 5);
    contentPane.add(filesPanel, gbc);
    addFileSButton = new JButton();
    addFileSButton.setText("Add File(s)");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 2;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    filesPanel.add(addFileSButton, gbc);
    removeSelectedButton = new JButton();
    removeSelectedButton.setText("Remove Selected");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 3;
    gbc.weightx = 1.0;
    gbc.weighty = 100.0;
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    filesPanel.add(removeSelectedButton, gbc);
    filesTable = new FilesTable();
    filesTable.setCellSelectionEnabled(true);
    filesTable.setColumnSelectionAllowed(true);
    filesTable.setShowVerticalLines(true);
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 3;
    gbc.gridheight = 6;
    gbc.weightx = 100.0;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(0, 0, 0, 5);
    filesPanel.add(filesTable, gbc);
    buttonCancel = new JButton();
    buttonCancel.setText("Cancel");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 7;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    filesPanel.add(buttonCancel, gbc);
    buttonRunScript = new JButton();
    buttonRunScript.setText("Run Script");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 6;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    filesPanel.add(buttonRunScript, gbc);
    final JLabel label1 = new JLabel();
    label1.setText("Output Directory");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weighty = 1.0;
    gbc.anchor = GridBagConstraints.WEST;
    filesPanel.add(label1, gbc);
    textFieldOutputDir = new JTextField();
    textFieldOutputDir.setEditable(false);
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(0, 5, 0, 5);
    filesPanel.add(textFieldOutputDir, gbc);
    viewDirectoryButton = new JButton();
    viewDirectoryButton.setEnabled(true);
    viewDirectoryButton.setText("View Directory");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 1;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    filesPanel.add(viewDirectoryButton, gbc);
    browseButton = new JButton();
    browseButton.setMaximumSize(new Dimension(106, 32));
    browseButton.setMinimumSize(new Dimension(106, 32));
    browseButton.setPreferredSize(new Dimension(120, 26));
    browseButton.setText("Browse");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.weighty = 1.0;
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(3, 0, 0, 5);
    filesPanel.add(browseButton, gbc);
    final JLabel label2 = new JLabel();
    label2.setText("Script filepath");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weighty = 1.0;
    gbc.anchor = GridBagConstraints.WEST;
    filesPanel.add(label2, gbc);
    textFieldscriptfilepath = new JTextField();
    textFieldscriptfilepath.setEditable(false);
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(0, 5, 0, 5);
    filesPanel.add(textFieldscriptfilepath, gbc);
    openFileButton = new JButton();
    openFileButton.setPreferredSize(new Dimension(120, 26));
    openFileButton.setText("Open File");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(0, 0, 0, 5);
    filesPanel.add(openFileButton, gbc);
    editScriptButton = new JButton();
    editScriptButton.setText("Edit Script");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 0;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    filesPanel.add(editScriptButton, gbc);
  }

  /**
   * @noinspection ALL
   */
  public JComponent $$$getRootComponent$$$() {
    return contentPane;
  }
}
