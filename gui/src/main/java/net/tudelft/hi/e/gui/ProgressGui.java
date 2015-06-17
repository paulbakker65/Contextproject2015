package net.tudelft.hi.e.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.export.Exporter;
import net.tudelft.hi.e.input.Input;

/**
 * A GUI that shows a progress bar and a text area to show a log.
 * Also creates a Task, it's code is run in a different thread and can be used to update the gui.
 */
public class ProgressGui extends JPanel implements PropertyChangeListener {
  private static final long serialVersionUID = 1L;
  private JProgressBar progressBar;
  private JTextPane log;
  private JButton visualizationsButton;
  private JButton previewButton;
  private JButton exportButton;
  private JLabel comboLabel;
  private JComboBox<String> comboPreviews;
  private JButton viewoutputdirButton;
  private JButton exitButton;
  private Task task;
  private JFrame frame;


  /**
   * Creates the GUI.
   */
  public static void init() {
    JFrame frame = new JFrame("Working...");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JComponent contentPane = new ProgressGui();
    contentPane.setOpaque(true);
    contentPane.setPreferredSize(new Dimension(1024, 720));
    frame.setContentPane(contentPane);

    ((ProgressGui)contentPane).setFrame(frame);

    GUI.init(frame);
  }

  /**
   * ProgressGui.init() should be used instead.
   * Creates all all gui components.
   */
  public ProgressGui() {
    super(new BorderLayout());

    createPanels();

    task = new Task();
    task.addPropertyChangeListener(this);
    task.execute();
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
    visualizationsButton = new JButton("Visualizations");
    visualizationsButton.setIcon(GUI.createImageIcon("icon.png"));
    visualizationsButton.setEnabled(false);
    visualizationsButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        onVisualizations();
      }
    });
    panel.add(visualizationsButton);

    previewButton = new JButton("Preview Table");
    previewButton.setIcon(GUI.createImageIcon("table.png"));
    previewButton.setEnabled(false);
    previewButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        onPreview();
      }
    });
    panel.add(previewButton);
    
    exportButton = new JButton("Export with custom delimiter");
    exportButton.setIcon(GUI.createImageIcon("save.png"));
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
    comboPreviews = new JComboBox<String>();
    comboPreviews.setEnabled(false);
    comboPanel.add(comboPreviews, BorderLayout.SOUTH);
    panel.add(comboPanel);

    return panel;
  }

  private JPanel createRightButtonPanel() {
    JPanel panel = new JPanel();
    viewoutputdirButton = new JButton("View output directory");
    viewoutputdirButton.setIcon(GUI.createImageIcon("folder.png"));
    viewoutputdirButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        onViewOutputDir();
      }
    });
    panel.add(viewoutputdirButton);

    exitButton = new JButton("Exit");
    exitButton.setIcon(GUI.createImageIcon("exit.png"));
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
  public void propertyChange(PropertyChangeEvent evt) {
    String prop = evt.getPropertyName();
    if ("progress" == prop) {
      int progress = (Integer) evt.getNewValue();
      progressBar.setValue(progress);
    } else if ("starting" == prop) {
      setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    } else if ("done" == evt.getPropertyName()) {
      done();
    } else if ("log" == prop) {
      appendToLog((String) evt.getNewValue(), (boolean)evt.getOldValue());
    } else if ("error" == prop) {
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



  public void onVisualizations() {
    Table table = task.getTable(comboPreviews.getSelectedIndex());
    VisualizationsGui.init(table);
  }

  public void onPreview() {
    DisplayTableGui.init(task.getTable(comboPreviews.getSelectedIndex()));
  }
  
  public void onExport() {
	  Character[] delimiters = new Character[]{',',  ';', '.', '\t'};
	  Object result = JOptionPane.showInputDialog(frame, "Please select delimiter:", "Change delimiter", JOptionPane.PLAIN_MESSAGE, GUI.createImageIcon("save.png"), delimiters, ',');
	  if (result == null) {
		  return;
	  }
	  Exporter.delimiter = (char) result;

	  Table table = task.getTable(comboPreviews.getSelectedIndex());
	  String filepath = Input.getOutputDir() + "/output_" + table.getName();
      task.exportFile(table, filepath);
      task.exportSettings(table, filepath + ".xml");
  }

  public void onViewOutputDir() {
    try {
      Desktop.getDesktop().open(Input.getOutputDir());
    } catch (IOException e1) {
      System.out.println("Error trying to view the directory.");
      e1.printStackTrace();
    }
  }

  public void onExit() {
    frame.dispose();
  }




  public void setFrame(JFrame frame) {
    this.frame = frame;
  }

  private void appendToLog(String message, boolean... options) {
    boolean bold = (options.length > 0 ? options[0] : false);

    appendToLog(message, Color.BLACK, bold);
  }

  private void appendToLog(String message, Color color, boolean bold) {
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
