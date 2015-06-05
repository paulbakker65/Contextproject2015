package main;

import table.Table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
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
  private static final long serialVersionUID = 1L;
  private JProgressBar progressBar;
  private JTextPane log;
  private JButton previewButton;
  private JButton exitButton;
  private static Task task;
  private static JDialog dialog;
  private JComboBox<String> comboPreviews;
  
  /**
   * Creates the GUI.
   */
  public static void init() {
    dialog = new JDialog(null, ModalityType.TOOLKIT_MODAL);
    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    dialog.setTitle("Working...");
    
    JComponent contentPane = new ProgressGui();
    contentPane.setOpaque(true); //content panes must be opaque
    contentPane.setPreferredSize(new Dimension(800, 600));
    dialog.setContentPane(contentPane);

    dialog.pack();
    GUI.setIconImage(dialog);
    GUI.centreWindow(dialog);
    dialog.setVisible(true);
  }
  
  /**
   * ProgressGui.init() should be used instead.
   * Creates all all gui components.
   */
  public ProgressGui() {
    super(new BorderLayout());

    progressBar = new JProgressBar(0, 100);
    progressBar.setValue(0);
    progressBar.setStringPainted(true);
 
    log = new JTextPane();
    log.setMargin(new Insets(5, 5, 5, 5));
    log.setEditable(false);
    
    previewButton = new JButton("Preview Table");
    previewButton.setEnabled(false);
    previewButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent ae) {
        onPreview();
      }
    });
    
    comboPreviews = new JComboBox<String>();
    comboPreviews.setEnabled(false);
    
    exitButton = new JButton("Exit");
    exitButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent ae) {
        onExit();
      }
    });
    
    JPanel panel = new JPanel();
    panel.add(progressBar);
    
    JPanel panel2 = new JPanel();
    panel2.add(previewButton);
    panel2.add(comboPreviews);
    panel2.add(exitButton);
 
    add(panel, BorderLayout.PAGE_START);
    add(new JScrollPane(log), BorderLayout.CENTER);
    add(panel2, BorderLayout.PAGE_END);
    setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
 
    task = new Task();
    task.addPropertyChangeListener(this);
    task.execute();
  }
  
  /**
   * Invoked when task's progress property changes.
   */
  public void propertyChange(PropertyChangeEvent evt) {
    String prop = evt.getPropertyName();
    if ("progress" == prop) {
      int progress = (Integer) evt.getNewValue();
      progressBar.setValue(progress);
      appendToLog(String.format("Completed %d%% of task.\n", task.getProgress()));
    } else if ("starting" == prop) {
      setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    } else if ("done" == evt.getPropertyName()) {
      setCursor(null); 
      Toolkit.getDefaultToolkit().beep();
      appendToLog("Done!\n");
      previewButton.setEnabled(true);
      setComboItems();
      comboPreviews.setEnabled(true);      
    } else if ("log" == prop) {
      appendToLog((String) evt.getNewValue());
    } else if ("error" == prop) {
      appendToLog((String) evt.getNewValue(), Color.RED);
    }
  }
  
  private void setComboItems() {
    for (Table table : task.getTables()) {
      comboPreviews.addItem(table.getName());
    }
  }
  
  public void onPreview() {
    DisplayTableGui.init(task.getTable(comboPreviews.getSelectedIndex()));
  }
  
  public void onExit() {
    dialog.dispose();
  }
  
  
  private void appendToLog(String message) {
    appendToLog(message, Color.BLACK);
  }

  private void appendToLog(String message, Color color) {
    log.setEditable(true);
    StyleContext sc = StyleContext.getDefaultStyleContext();
    AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

    aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
    aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

    int len = log.getDocument().getLength();
    log.setCaretPosition(len);
    log.setCharacterAttributes(aset, false);
    log.replaceSelection(message);
    log.setEditable(false);
  }
}
