package net.tudelft.hi.e.gui;

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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Contains methods for GUI's.
 */
class Gui {
  private static final Logger LOG = Logger.getLogger(Gui.class.getName());

  private Gui() {
  }

  /**
   * Sets the gui to use visuals similar to the operating system, instead of the java gui visuals.
   */
  public static void setSystemLook() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | IllegalAccessException e) {
      LOG.log(Level.SEVERE, e.getMessage(), e);
    } catch (InstantiationException e) {
      LOG.log(Level.WARNING, e.getMessage(), e);
    } catch (UnsupportedLookAndFeelException e) {
      LOG.log(Level.INFO, e.getMessage(), e);
    }
  }
  
  /**
   * This method will place the dialog in the center of the screen.
   */
  private static void centreWindow(Window window) {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int xloc = (int) dimension.getWidth() / 2 - window.getWidth() / 2;
    int yloc = (int) dimension.getHeight() / 2 - window.getHeight() / 2;
    window.setLocation(xloc, yloc);
  }
  
  /**
   * Sets the icon image to our program icon.
   * @param window The window (dialog/frame) to set the icon for.
   */
  private static void setIconImage(Window window) {
    try {
      Image icon = ImageIO.read(ClassLoader.getSystemResource("icons/icon.png"));
      window.setIconImage(icon);
    } catch (IOException e1) {
      LOG.log(Level.SEVERE, "Error opening icon from resource.", e1);
    }
  }
  
  /**
   * Packs the window, sets the icon, centres the window and sets it visible.
   * @param window The window to initialize.
   */
  public static void init(Window window) {
    window.pack();
    Gui.setIconImage(window);
    Gui.centreWindow(window);
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
      LOG.log(Level.SEVERE, "Error opening icon from resource.", new IOException());
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
    frame.setFrameIcon(Gui.createImageIcon("icon.png"));
    frame.setSize(container.getPreferredSize());
    frame.setVisible(true);
    return frame;
  }

  /**
   * Opens the file using the system default program for that file type.
   * @param file The file to open.
   */
  public static void openSystemEditor(File file) {
    try {
      Desktop.getDesktop().open(file);
    } catch (IOException e1) {
      LOG.log(Level.SEVERE, 
          "Error trying to open '" + file.getAbsolutePath() + "' in system editor.", e1);
    }
  }
}
