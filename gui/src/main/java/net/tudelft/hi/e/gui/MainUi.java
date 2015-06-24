package net.tudelft.hi.e.gui;

import net.tudelft.hi.e.input.DataFile;
import net.tudelft.hi.e.input.Input;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * MainUI class implementing the main Graphical User Interface.
 */
public class MainUi extends JFrame {

  private static final long serialVersionUID = 1L;
  private static final Logger LOG = Logger.getLogger(MainUi.class.getName());

  // All GUI components:
  private JPanel contentPane;
  private JButton buttonRunScript;
  private JButton buttonCancel;
  private JTextField textFieldscriptfilepath;
  private JButton openFileButton;
  private JButton editScriptButton;
  private JTable filesTable;
  private JButton addFileSButton;
  private JButton removeSelectedButton;
  private JTextField textFieldOutputDir;
  private JButton browseButton;
  private JButton viewDirectoryButton;
  private JButton buttonVisualizations;
  private JButton settingsbuilderButton;
  private JPanel filesPanel;
  private JScrollPane filesTableScrollPane;

  // Filters for JFileChooser dialog
  public static final FileNameExtensionFilter xmlfilter =
          new FileNameExtensionFilter("XML files", "xml");
  @SuppressWarnings("WeakerAccess")
  public static final FileNameExtensionFilter txtfilter =
          new FileNameExtensionFilter("TXT files", "txt");
  @SuppressWarnings("WeakerAccess")
  public static final FileNameExtensionFilter csvfilter =
          new FileNameExtensionFilter("CSV files", "csv");
  @SuppressWarnings("WeakerAccess")
  public static final FileNameExtensionFilter xlsfilter =
          new FileNameExtensionFilter("Excel files", "xls", "xlsx");
  @SuppressWarnings("WeakerAccess")
  public static final FileNameExtensionFilter serfilter =
          new FileNameExtensionFilter("SER files", "ser");
  public static final FileNameExtensionFilter allsupportedfilter =
          new FileNameExtensionFilter("All supported files", "csv", "txt", "xls", "xlsx");

  private static File previousDirectory;


  /**
   * Creates a gui for select input files.
   */
  public MainUi() {
    setButtonActionListeners();

    init();

    setCloseOperation();
  }

  /**
   * Initializes the GUI. Sets the dialog look to match the system look,
   * loads the icon and sets the file path fields.
   */
  private void init() {
    Gui.setSystemLook();
    setContentPane(contentPane);

    getRootPane().setDefaultButton(buttonRunScript);

    filesTable.setTableHeader(null);
    filesTable.setModel(new FilesTableModel());
    filesTable.setColumnSelectionAllowed(false);
    MainUiContextMenu.init(filesTable, this);

    if (Input.hasScript()) {
      updateScriptInfo();
    }
    if (Input.hasOutput()) {
      textFieldOutputDir.setText(Input.getOutputDir().getAbsolutePath());
    }
    
    readPreviousDirectory();
  }

  private void setCloseOperation() {
    contentPane.registerKeyboardAction(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
          onCancel();
        }
      },
      KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
      JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent event) {
        onCancel();
      }
    });
  }

  private void setButtonActionListeners() {
    openFileButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onOpenScript();
      }
    });

    editScriptButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onNewScript();
      }
    });

    browseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onSelectOutputDir();
      }
    });

    viewDirectoryButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onViewOutputDir();
      }
    });

    addFileSButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onAddNewFile();
      }
    });

    removeSelectedButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onRemoveFile();
      }
    });

    buttonRunScript.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onRunScript();
      }
    });

    buttonCancel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onCancel();
      }
    });

    buttonVisualizations.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onVisualizations();
      }
    });

    settingsbuilderButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onSettingsBuilder();
      }
    });
  }

  private void updateScriptInfo() {
    for (ActionListener al : editScriptButton.getActionListeners()) {
      editScriptButton.removeActionListener(al);
    }

    editScriptButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        onEditScript();
      }
    });

    textFieldscriptfilepath.setText(Input.getScriptFile().getAbsolutePath());
    editScriptButton.setText("Edit Script");
    editScriptButton.setMnemonic(KeyEvent.VK_E);
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
      if (!Input.setScriptFile(chooser.getSelectedFile())) {
        JOptionPane.showMessageDialog(this,
                "Error opening script file, please select a valid script file.",
                "File not found.", JOptionPane.ERROR_MESSAGE);
        return;
      }
      updateScriptInfo();
    }
  }

  /**
   * Shows a file dialog for the user to create a new file. The created file wil then be opened.
   */
  private void onNewScript() {
    List<FileNameExtensionFilter> filters = new ArrayList<>();
    filters.add(txtfilter);
    File script = saveFile(filters, "a new script file");
    if (script == null) {
      return;
    }

    if (script.exists()) {
      if (!overrideFile(this, script) || script.delete()) {
        return;
      }
    } else {
      script = setExtension(script, ".txt");
    }

    try {
      if (!script.createNewFile()) {
        throw new IOException("The file already exists!");
      }
    } catch (IOException e) {
      LOG.log(Level.SEVERE, "Error creating file '" + script.getAbsolutePath() + "': " 
          + e.getMessage(), e);
      return;
    }

    Input.setScriptFile(script);
    onEditScript();
    updateScriptInfo();
  }

  /**
   * Checks if the file has the given extension, if not it adds the extension.
   * @param file The file to add the extension for.
   * @param extension A string containing the extension (e.g. '.txt')
   * @return returns the file containing the extension.
   */
  public static File setExtension(File file, String extension) {
    if (!file.getPath().toLowerCase().endsWith(extension)) {
      return new File(file.getPath() + extension);
    }
    return file;
  }

  /**
   * Prompts the user if they want to override this file.
   * @param window The parent window.
   * @param file The file to override.
   * @return Returns true if the user want's to override the file.
   */
  @SuppressWarnings("BooleanMethodIsAlwaysInverted")
  public static boolean overrideFile(Window window, File file) {
    return JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(window,
          "The file '" + file.getName() + "' already exists, would you like to overide this file?.",
          "File already exists.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
  }
  /**
   * Opens the default system editor for the script file.
   */
  private void onEditScript() {
    if (!Input.hasScript()) {
      return;
    }

    Gui.openSystemEditor(Input.getScriptFile());
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

      if (!Input.setOutputDir(chooser.getSelectedFile())) {
        JOptionPane.showMessageDialog(this,
                "Error with output directory. "
                        + "Selected path is a file or the direcotry could not be made.",
                "Directory not found.", JOptionPane.ERROR_MESSAGE);
        return;
      }


      textFieldOutputDir.setText(Input.getOutputDir().getAbsolutePath());
    }
  }

  /**
   * Opens the default system program to view the directory. If no output directory has been
   * specified, it will prompt the user to do so.
   */
  private void onViewOutputDir() {
    if (!Input.hasOutput()) {
      int option = JOptionPane.showConfirmDialog(null,
              "No output directory selected.\nWould you like to select one now?",
              "No output directory.", JOptionPane.YES_NO_OPTION);
      if (option == JOptionPane.YES_OPTION) {
        onSelectOutputDir();
      } else {
        return;
      }
    }

    if (!Input.hasOutput()) {
      return;
    }

    Gui.openSystemEditor(Input.getOutputDir());
  }

  /**
   * Opens a open file dialog for the user to select a data file and a settings file. When the user
   * is done, the file wil be displayed in the files table.
   */
  private void onAddNewFile() {
    File dataFile;
    File settingsFile;

    dataFile = openDataFile();
    if (dataFile == null) {
      return;
    }

    settingsFile = openSettingsFile();
    if (settingsFile == null) {
      return;
    }

    try {
      Input.addDataFile(dataFile, settingsFile);
    } catch (Exception e) {
      //addDataFile will throw an exception if an error occurs when
      //creating the Reader and parsing the settings.
      JOptionPane.showMessageDialog(null, e.getMessage());
      LOG.log(Level.SEVERE, e.getMessage(), e);
      return;
    }
    filesTable.revalidate();
  }

  /**
   * Removes the selected row from the files table.
   */
  private void onRemoveFile() {
    int[] selectedRows = filesTable.getSelectedRows();
    int rowcount = filesTable.getRowCount();
    if (selectedRows.length > rowcount) {
      return;
    }

    //make sure the selected row index is not out of bound.
    for (int i : selectedRows) {
      if (i >= rowcount) {
        return;
      }
    }

    // Sort the index list, rows with a higher index should be removed before rows with a lower
    // index to prevent indices from changing.
    Arrays.sort(selectedRows);

    for (int i = selectedRows.length - 1; i >= 0; i--) {
      Input.getFiles().remove(selectedRows[i]);
    }
    filesTable.revalidate();
  }
  
  /**
   * Opens the settings builder window.
   */
  private void onSettingsBuilder() {
    Gui.init(new SettingsGenerator());
  }

  /**
   * Open a blank visualizations window.
   */
  private void onVisualizations() {
    VisualizationsGui.init(null);
  }

  /**
   * Validates that the user has specified the required files and exits the gui.
   */
  private void onRunScript() {

    if (!Input.hasScript() || !Input.hasOutput() || !Input.hasFiles()) {
      JOptionPane.showMessageDialog(null,
              "Please make sure you have selected a script file, "
                      + "output directory and at least 1 data file.",
              "Wrong input.", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    LOG.log(Level.INFO, "Run script with input:\n"
            + "scriptFile = " + Input.getScriptFile().getAbsolutePath() + "\n"
            + "outputDir = " + Input.getOutputDir().getAbsolutePath() + "\n"
            + "files = ");
    for (DataFile file : Input.getFiles()) {
      LOG.log(Level.INFO, file.toString());
    }
    ProgressGui.init();
  }

  private void readPreviousDirectory() {
    File prevDirectoryFile = new File("prev_directory.txt");

    if (!(prevDirectoryFile.exists() && prevDirectoryFile.isFile())) {
      return;
    }
    try {
      BufferedReader reader = new BufferedReader(new FileReader(prevDirectoryFile));
      String line = reader.readLine();
      File prevDirectory = new File(line);

      if (prevDirectory.exists() && prevDirectory.isDirectory()) {
        previousDirectory = prevDirectory;
      }
      reader.close();
    } catch (IOException e) {
      LOG.log(Level.WARNING, e.getMessage(), e);
    }
  }

  private void writePreviousDirectory() {
    if (previousDirectory == null) {
      return;
    }

    try {
      FileWriter writer = new FileWriter("prev_directory.txt");
      writer.write(previousDirectory.getAbsolutePath());
      writer.close();
    } catch (IOException e) {
      LOG.log(Level.WARNING, e.getMessage(), e);
    }
  }

  /**
   * Exits the program.
   */
  private void onCancel() {
    writePreviousDirectory();
    dispose();
  }

  /**
   * Shows a file dialog for the user to select the data file.
   */
  public static File openDataFile() {
    List<FileNameExtensionFilter> filters = new ArrayList<>();
    filters.add(xlsfilter);
    filters.add(txtfilter);
    filters.add(csvfilter);
    filters.add(allsupportedfilter);
    return openFile(filters, "data file");
  }

  /**
   * Shows a file dialog for the user to select a ser file.
   */
  public static File openTableFile() {
    List<FileNameExtensionFilter> filters = new ArrayList<>();
    filters.add(serfilter);
    return openFile(filters, "table file");
  }

  /**
   * Shows a file dialog for the user to select the settings file.
   */
  public static File openSettingsFile() {
    List<FileNameExtensionFilter> filters = new ArrayList<>();
    filters.add(xmlfilter);
    return openFile(filters, "settings file");
  }

  /**
   * Shows a file dialog for the user to select a file.
   */
  public static File openFile(List<FileNameExtensionFilter> filters, String title) {
    File file;
    JFileChooser chooser = new JFileChooser(previousDirectory);
    chooser.setDialogTitle("Open " + title + ".");

    for (FileNameExtensionFilter filter : filters) {
      chooser.setFileFilter(filter);
    }

    int state = chooser.showOpenDialog(null);

    if (state == JFileChooser.APPROVE_OPTION) {

      previousDirectory = chooser.getCurrentDirectory();
      file = chooser.getSelectedFile();
      if (!Input.exists(file)) {
        JOptionPane.showMessageDialog(null,
                "Error opening " + title + ", the " + title + " can not be found.",
                "File not found.", JOptionPane.ERROR_MESSAGE);
        return null;
      }

      return file;
    }

    return null;
  }

  /**
   * Shows a file dialog for the user to save a file.
   */
  public static File saveFile(List<FileNameExtensionFilter> filters, String title) {
    File file;
    JFileChooser chooser = new JFileChooser(previousDirectory);
    chooser.setDialogTitle("Save " + title + ".");

    for (FileNameExtensionFilter filter : filters) {
      chooser.setFileFilter(filter);
    }

    int state = chooser.showSaveDialog(null);

    if (state == JFileChooser.APPROVE_OPTION) {
      previousDirectory = chooser.getCurrentDirectory();
      file = chooser.getSelectedFile();
      return file;
    }

    return null;
  }

  
//CHECKSTYLE:OFF
  {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
    $$$setupUI$$$();
  }

  /**
   * Method generated by IntelliJ IDEA GUI Designer
   * >>> IMPORTANT!! <<<
   * DO NOT edit this method OR call it in your code!
   *
   * @noinspection ALL
   */
  private void $$$setupUI$$$() {
    contentPane = new JPanel();
    contentPane.setLayout(new GridBagLayout());
    contentPane.setOpaque(true);
    contentPane.setPreferredSize(new Dimension(1024, 720));
    JPanel filesPanel = new JPanel();
    filesPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc;
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(2, 2, 2, 2);
    contentPane.add(filesPanel, gbc);
    addFileSButton = new JButton();
    addFileSButton.setFocusPainted(false);
    addFileSButton.setHorizontalAlignment(2);
    addFileSButton.setIcon(new ImageIcon(getClass().getResource("/icons/add.png")));
    addFileSButton.setMaximumSize(new Dimension(170, 48));
    addFileSButton.setMinimumSize(new Dimension(170, 48));
    addFileSButton.setPreferredSize(new Dimension(170, 48));
    addFileSButton.setText("Add File(s)");
    addFileSButton.setMnemonic('A');
    addFileSButton.setDisplayedMnemonicIndex(0);
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 2;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(2, 2, 2, 2);
    filesPanel.add(addFileSButton, gbc);
    removeSelectedButton = new JButton();
    removeSelectedButton.setFocusPainted(false);
    removeSelectedButton.setHorizontalAlignment(2);
    removeSelectedButton.setIcon(new ImageIcon(getClass().getResource("/icons/remove.png")));
    removeSelectedButton.setMaximumSize(new Dimension(170, 48));
    removeSelectedButton.setMinimumSize(new Dimension(170, 48));
    removeSelectedButton.setPreferredSize(new Dimension(170, 48));
    removeSelectedButton.setText("Remove Selected");
    removeSelectedButton.setMnemonic('R');
    removeSelectedButton.setDisplayedMnemonicIndex(0);
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 3;
    gbc.weighty = 100.0;
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(2, 2, 2, 2);
    filesPanel.add(removeSelectedButton, gbc);
    buttonCancel = new JButton();
    buttonCancel.setFocusPainted(false);
    buttonCancel.setHideActionText(false);
    buttonCancel.setHorizontalAlignment(2);
    buttonCancel.setHorizontalTextPosition(11);
    buttonCancel.setIcon(new ImageIcon(getClass().getResource("/icons/exit.png")));
    buttonCancel.setIconTextGap(4);
    buttonCancel.setMaximumSize(new Dimension(170, 48));
    buttonCancel.setMinimumSize(new Dimension(170, 48));
    buttonCancel.setPreferredSize(new Dimension(170, 48));
    buttonCancel.setText("Close");
    buttonCancel.setMnemonic('C');
    buttonCancel.setDisplayedMnemonicIndex(0);
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 7;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(2, 2, 2, 2);
    filesPanel.add(buttonCancel, gbc);
    buttonRunScript = new JButton();
    buttonRunScript.setFocusPainted(false);
    buttonRunScript.setHorizontalAlignment(2);
    buttonRunScript.setIcon(new ImageIcon(getClass().getResource("/icons/run.png")));
    buttonRunScript.setMaximumSize(new Dimension(170, 48));
    buttonRunScript.setMinimumSize(new Dimension(170, 48));
    buttonRunScript.setPreferredSize(new Dimension(170, 48));
    buttonRunScript.setText("Run Script");
    buttonRunScript.setMnemonic('S');
    buttonRunScript.setDisplayedMnemonicIndex(4);
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 6;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(2, 2, 2, 2);
    filesPanel.add(buttonRunScript, gbc);
    final JLabel label1 = new JLabel();
    label1.setText("Output Directory");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weighty = 1.0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(2, 2, 2, 2);
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
    gbc.insets = new Insets(2, 2, 2, 2);
    filesPanel.add(textFieldOutputDir, gbc);
    viewDirectoryButton = new JButton();
    viewDirectoryButton.setEnabled(true);
    viewDirectoryButton.setFocusPainted(false);
    viewDirectoryButton.setHorizontalAlignment(2);
    viewDirectoryButton.setIcon(new ImageIcon(getClass().getResource("/icons/folder.png")));
    viewDirectoryButton.setMaximumSize(new Dimension(170, 48));
    viewDirectoryButton.setMinimumSize(new Dimension(170, 48));
    viewDirectoryButton.setOpaque(true);
    viewDirectoryButton.setPreferredSize(new Dimension(170, 48));
    viewDirectoryButton.setText("View Directory");
    viewDirectoryButton.setMnemonic('D');
    viewDirectoryButton.setDisplayedMnemonicIndex(5);
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(2, 2, 2, 2);
    filesPanel.add(viewDirectoryButton, gbc);
    browseButton = new JButton();
    browseButton.setFocusPainted(false);
    browseButton.setIcon(new ImageIcon(getClass().getResource("/icons/search.png")));
    browseButton.setMaximumSize(new Dimension(170, 48));
    browseButton.setMinimumSize(new Dimension(170, 48));
    browseButton.setPreferredSize(new Dimension(170, 48));
    browseButton.setText("Browse");
    browseButton.setMnemonic('B');
    browseButton.setDisplayedMnemonicIndex(0);
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(2, 2, 2, 2);
    filesPanel.add(browseButton, gbc);
    final JLabel label2 = new JLabel();
    label2.setText("Script filepath");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weighty = 1.0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(2, 2, 2, 2);
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
    gbc.insets = new Insets(2, 2, 2, 2);
    filesPanel.add(textFieldscriptfilepath, gbc);
    editScriptButton = new JButton();
    editScriptButton.setFocusPainted(false);
    editScriptButton.setHorizontalAlignment(2);
    editScriptButton.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png")));
    editScriptButton.setMaximumSize(new Dimension(170, 48));
    editScriptButton.setMinimumSize(new Dimension(170, 48));
    editScriptButton.setPreferredSize(new Dimension(170, 48));
    editScriptButton.setText("New Script");
    editScriptButton.setMnemonic('N');
    editScriptButton.setDisplayedMnemonicIndex(0);
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(2, 2, 2, 2);
    filesPanel.add(editScriptButton, gbc);
    buttonVisualizations = new JButton();
    buttonVisualizations.setFocusPainted(false);
    buttonVisualizations.setHorizontalAlignment(10);
    buttonVisualizations.setIcon(new ImageIcon(getClass().getResource("/icons/icon.png")));
    buttonVisualizations.setMaximumSize(new Dimension(170, 48));
    buttonVisualizations.setMinimumSize(new Dimension(170, 48));
    buttonVisualizations.setPreferredSize(new Dimension(170, 48));
    buttonVisualizations.setText("Visualizations");
    buttonVisualizations.setMnemonic('V');
    buttonVisualizations.setDisplayedMnemonicIndex(0);
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 5;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(2, 2, 2, 2);
    filesPanel.add(buttonVisualizations, gbc);
    settingsbuilderButton = new JButton();
    settingsbuilderButton.setFocusPainted(false);
    settingsbuilderButton.setHorizontalAlignment(2);
    settingsbuilderButton.setIcon(new ImageIcon(getClass().getResource("/icons/settings.png")));
    settingsbuilderButton.setMargin(new Insets(2, 14, 2, 14));
    settingsbuilderButton.setMaximumSize(new Dimension(170, 48));
    settingsbuilderButton.setMinimumSize(new Dimension(170, 48));
    settingsbuilderButton.setPreferredSize(new Dimension(170, 48));
    settingsbuilderButton.setText("Settings Builder");
    settingsbuilderButton.setMnemonic('T');
    settingsbuilderButton.setDisplayedMnemonicIndex(2);
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 4;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(2, 2, 2, 2);
    filesPanel.add(settingsbuilderButton, gbc);
    openFileButton = new JButton();
    openFileButton.setFocusPainted(false);
    openFileButton.setIcon(new ImageIcon(getClass().getResource("/icons/search.png")));
    openFileButton.setMaximumSize(new Dimension(170, 48));
    openFileButton.setMinimumSize(new Dimension(170, 48));
    openFileButton.setPreferredSize(new Dimension(170, 48));
    openFileButton.setText("Open File");
    openFileButton.setMnemonic('O');
    openFileButton.setDisplayedMnemonicIndex(0);
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(2, 2, 2, 2);
    filesPanel.add(openFileButton, gbc);
    JScrollPane filesTableScrollPane = new JScrollPane();
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 3;
    gbc.gridheight = 6;
    gbc.weightx = 100.0;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(2, 2, 2, 2);
    filesPanel.add(filesTableScrollPane, gbc);
    filesTable = new JTable();
    filesTable.setCellSelectionEnabled(true);
    filesTable.setColumnSelectionAllowed(true);
    filesTable.setShowVerticalLines(true);
    filesTableScrollPane.setViewportView(filesTable);
  }

  /**
   * @noinspection ALL
   */
  public JComponent $$$getRootComponent$$$() {
    return contentPane;
  }
//CHECKSTYLE:ON
  
}
