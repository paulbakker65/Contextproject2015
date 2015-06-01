package main;
 
import input.DataFile;
import input.Input;

import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import table.Table;
import table.value.ColumnTypeMismatchException;

import java.beans.*;
import java.io.IOException;
import java.util.ArrayList;
 
/**
 * A GUI that shows a progress bar and a text area to show a log.
 * Also creates a Task, it's code is run in a different thread and can be used to update the gui.
 */
public class ProgressGui extends JPanel implements PropertyChangeListener {
  private static final long serialVersionUID = 1L;
  private JProgressBar progressBar;
  private JTextArea log;
  private JButton previewButton;
  private JButton exitButton;
  private static Task task;
  private static JDialog dialog;
  
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
 
    log = new JTextArea(5, 20);
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
    if ("progress" == evt.getPropertyName()) {
      int progress = (Integer) evt.getNewValue();
      progressBar.setValue(progress);
      log.append(String.format("Completed %d%% of task.\n", task.getProgress()));
    } 
  }
  
  class Task extends SwingWorker<Void, Void> {
    ArrayList<Table> tables = null;;

    @Override
    public Void doInBackground() {
      setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
      
      
      log.append("Parsing input files.\n");
      tables = new ArrayList<Table>();

      for (DataFile f : Input.getFiles()) {
        Table t = null;
        try {
          log.append("Parsing " + f.toString() + "\n");
          t = f.getParser().parse(f.getReader());
          tables.add(t);
        } catch (ColumnTypeMismatchException | IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      log.append("Done parsing input files.\n\n");
      
      setProgress(100);

      return null;
    }
 
    @Override
    public void done() {
      setCursor(null); 
      Toolkit.getDefaultToolkit().beep();
      log.append("Done!\n");
      previewButton.setEnabled(true);
    }
    
    public Table getTable(){
      return tables.get(0);
    }
    
    public void setProg(int percent){
      setProgress(percent);
    }
  }
  
  
  public void onPreview(){
    DisplayTableGui.init(task.getTable());
  }
  
  public void onExit(){
    dialog.dispose();
  }
}
