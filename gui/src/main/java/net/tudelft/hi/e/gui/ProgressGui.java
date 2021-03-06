package net.tudelft.hi.e.gui;

import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.export.Exporter;
import net.tudelft.hi.e.input.Input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;


/**
 * A GUI that shows a progress bar and a text area to show a log.
 * Also creates a Task, it's code is run in a different thread and can be used to update the gui.
 */
public class ProgressGui extends JPanel implements PropertyChangeListener {
  private static final Logger LOG = Logger.getLogger(ProgressGui.class.getName());
  private static final long serialVersionUID = 1L;

  private JProgressBar progressBar;
  private JTextPane log;
  private JButton visualizationsButton;
  private JButton previewButton;
  private JButton exportButton;
  private JLabel comboLabel;
  private JComboBox<String> comboPreviews;
  private final Task task;
  private final JFrame frame;

  /**
   * ProgressGui.init() should be used instead.
   * Creates all all gui components.
   */
  private ProgressGui(JFrame frame) {
    super(new BorderLayout());

    this.frame = frame;

    createPanels();

    task = new Task();
    task.addPropertyChangeListener(this);
    task.execute();
  }

  /**
   * Creates the GUI.
   */
  public static void init() {
    JFrame frame = new JFrame("Working...");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JComponent contentPane = new ProgressGui(frame);
    contentPane.setOpaque(true);
    contentPane.setPreferredSize(new Dimension(1024, 720));
    frame.setContentPane(contentPane);

    Gui.init(frame);
  }


  private void createPanels() {
    progressBar = new JProgressBar(0, 100);
    progressBar.setValue(0);
    progressBar.setStringPainted(true);

    log = new JTextPane();
    log.setMargin(new Insets(5, 5, 5, 5));
    log.setEditable(false);

    JPanel panel = new JPanel();
    panel.add(progressBar);

    add(panel, BorderLayout.PAGE_START);
    add(new JScrollPane(log), BorderLayout.CENTER);
    add(createButtonPanel(), BorderLayout.PAGE_END);
    setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  }

  private JPanel createButtonPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(createLeftButtonPanel(), BorderLayout.WEST);
    panel.add(createRightButtonPanel(), BorderLayout.EAST);

    return panel;
  }

  private JPanel createLeftButtonPanel() {
    JPanel panel = new JPanel();
    panel.setVisible(true);
    
    visualizationsButton = new JButton("Visualizations");
    visualizationsButton.setMnemonic(KeyEvent.VK_V);
    visualizationsButton.setIcon(Gui.createImageIcon("icon.png"));
    visualizationsButton.setEnabled(false);
    visualizationsButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        onVisualizations();
      }
    });
    panel.add(visualizationsButton);

    previewButton = new JButton("Preview Table");
    previewButton.setMnemonic(KeyEvent.VK_P);
    previewButton.setIcon(Gui.createImageIcon("table.png"));
    previewButton.setEnabled(false);
    previewButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        onPreview();
      }
    });
    panel.add(previewButton);
    
    exportButton = new JButton("Export with custom delimiter");
    exportButton.setMnemonic(KeyEvent.VK_E);
    exportButton.setIcon(Gui.createImageIcon("save.png"));
    exportButton.setEnabled(false);
    exportButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        onExport();
      }
    });
    panel.add(exportButton);

    JPanel comboPanel = new JPanel(new BorderLayout());
    comboLabel = new JLabel("Table selected:");
    comboLabel.setEnabled(false);
    comboPanel.add(comboLabel, BorderLayout.NORTH);
    comboPreviews = new JComboBox<>();
    comboPreviews.setEnabled(false);
    comboPanel.add(comboPreviews, BorderLayout.SOUTH);
    panel.add(comboPanel);

    return panel;
  }

  private JPanel createRightButtonPanel() {
    final JPanel panel = new JPanel();
    JButton viewoutputdirButton = new JButton("View output directory");
    viewoutputdirButton.setMnemonic(KeyEvent.VK_O);
    viewoutputdirButton.setIcon(Gui.createImageIcon("folder.png"));
    viewoutputdirButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        onViewOutputDir();
      }
    });
    panel.add(viewoutputdirButton);

    JButton exitButton = new JButton("Close");
    exitButton.setMnemonic(KeyEvent.VK_X);
    exitButton.setIcon(Gui.createImageIcon("exit.png"));
    exitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        onExit();
      }
    });
    panel.add(exitButton);

    return panel;
  }


  /**
   * Invoked when task's progress property changes.
   */
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    String prop = evt.getPropertyName();
    if (Objects.equals("progress", prop)) {
      int progress = (Integer) evt.getNewValue();
      progressBar.setValue(progress);
    } else if (Objects.equals("starting", prop)) {
      setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    } else if (Objects.equals("done", evt.getPropertyName())) {
      done();
    } else if (Objects.equals("log", prop)) {
      appendToLog((String) evt.getNewValue(), (boolean)evt.getOldValue());
    } else if (Objects.equals("error", prop)) {
      appendToLog((String) evt.getNewValue(), Color.RED, false);
    }
  }

  private void done() {
    setCursor(null);
    Toolkit.getDefaultToolkit().beep();
    appendToLog("Done!\n");
    visualizationsButton.setEnabled(true);
    previewButton.setEnabled(true);
    exportButton.setEnabled(true);
    comboLabel.setEnabled(true);
    setComboItems();
    comboPreviews.setEnabled(true);
  }

  private void setComboItems() {
    for (Table table : task.getTables()) {
      comboPreviews.addItem(table.getName());
    }
  }



  private void onVisualizations() {
    Table table = task.getTable(comboPreviews.getSelectedIndex());
    VisualizationsGui.init(table);
  }

  private void onPreview() {
    DisplayTableGui.init(task.getTable(comboPreviews.getSelectedIndex()));
  }
  
  private void onExport() {
    Character[] delimiters = new Character[]{',',  ';', '.', '\t'};
    Object result = JOptionPane.showInputDialog(frame, 
        "Please select delimiter:", "Change delimiter", JOptionPane.PLAIN_MESSAGE, 
        Gui.createImageIcon("save.png"), delimiters, ',');
    if (result == null) {
      return;
    }

    Exporter.setDelimiter((char)result);

    Table table = task.getTable(comboPreviews.getSelectedIndex());
    String filepath = Input.getOutputDir() + "/output_" + table.getName();
    task.exportFile(table, filepath);
    task.exportSettings(table, filepath + ".xml");
  }

  private void onViewOutputDir() {
    Gui.openSystemEditor(Input.getOutputDir());
  }

  private void onExit() {
    frame.dispose();
  }





  private void appendToLog(String message, boolean... options) {
    boolean bold = options.length > 0 && options[0];

    appendToLog(message, Color.BLACK, bold);
  }

  private void appendToLog(String message, Color color, boolean bold) {
    LOG.log(color == Color.RED ? Level.SEVERE : Level.INFO, message);
    log.setEditable(true);
    StyleContext sc = StyleContext.getDefaultStyleContext();
    AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

    aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
    aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
    aset = sc.addAttribute(aset,  StyleConstants.Bold, bold);

    int len = log.getDocument().getLength();
    log.setCaretPosition(len);
    log.setCharacterAttributes(aset, false);
    log.replaceSelection(message);
    log.setEditable(false);
  }
}
