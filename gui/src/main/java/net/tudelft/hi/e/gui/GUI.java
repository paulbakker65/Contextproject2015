package net.tudelft.hi.e.gui;


import net.tudelft.hi.e.input.Input;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains methods for GUI's.
 */
public class GUI {
  private static final Logger LOG = Logger.getLogger(Task.class.getName());
  /**
   * Sets the gui to use visuals similar to the operating system, instead of the java gui visuals.
   */
  public static void setSystemLook() {
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
  
  /**
   * This method will place the dialog in the center of the screen.
   */
  public static void centreWindow(Window window) {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int xloc = (int) dimension.getWidth() / 2 - window.getWidth() / 2;
    int yloc = (int) dimension.getHeight() / 2 - window.getHeight() / 2;
    window.setLocation(xloc, yloc);
  }
  
  /**
   * Sets the icon image to our program icon.
   * @param window The window (dialog/frame) to set the icon for.
   */
  public static void setIconImage(Window window){
    try {
      Image icon = ImageIO.read(ClassLoader.getSystemResource("icons/icon.png"));
      window.setIconImage(icon);
    } catch (IOException e1) {
      System.err.println("Error opening icon from resource.");
    }
  }
  
  /**
   * Packs the window, sets the icon, centres the window and sets it visible.
   * @param window The window to initialize.
   */
  public static void init(Window window) {
    window.pack();
    GUI.setIconImage(window);
    GUI.centreWindow(window);
    window.setVisible(true);
  }
  
  /**
   * Creates an image icon from a file in resource.
   * @param path The path of the file in the resource directory.
   * @return Returns an image icon if succeeded, null if an error occurred.
   */
  public static ImageIcon createImageIcon(String path) {
    java.net.URL imgUrl = ClassLoader.getSystemResource("icons/" + path);
    if (imgUrl != null) {
      return new ImageIcon(imgUrl);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }

  /**
   * Creates a JInternalFrame with the container as it's content pane.
   * @param windowTitle The title for the frame.
   * @param container The container to set as the content pane.
   * @return returns a JInternalFrame.
   */
  public static JInternalFrame createInternalFrame(String windowTitle, Container container) {
    JInternalFrame frame = new JInternalFrame(windowTitle, true, true, true, true);
    frame.setContentPane(container);
    frame.setFrameIcon(GUI.createImageIcon("icon.png"));
    frame.setSize(container.getPreferredSize());
    frame.setVisible(true);
    return frame;
  }

  public static void openSystemEditor(File file) {
    try {
      Desktop.getDesktop().open(file);
    } catch (IOException e1) {
      LOG.log(Level.SEVERE, "Error trying to open '" + file.getAbsolutePath() + "' in system editor.");
    }
  }
}
