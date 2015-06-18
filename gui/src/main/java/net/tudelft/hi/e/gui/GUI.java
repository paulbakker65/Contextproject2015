package net.tudelft.hi.e.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Contains methods for GUI's.
 */
public class GUI {
  
  private static final Logger LOG = Logger.getLogger(GUI.class.getName());
  /**
   * Sets the gui to use visuals similar to the operating system, instead of the java gui visuals.
   */
  public static void setSystemLook() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException e) {
      LOG.log(Level.SEVERE, e.getMessage(), e);
    } catch (InstantiationException e) {
      LOG.log(Level.WARNING, e.getMessage(), e);
    } catch (IllegalAccessException e) {
      LOG.log(Level.SEVERE, e.getMessage(), e);
    } catch (UnsupportedLookAndFeelException e) {
      LOG.log(Level.INFO, e.getMessage(), e);
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
      Image icon = ImageIO.read(ClassLoader.getSystemResource("icon.png"));
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
    java.net.URL imgUrl = ClassLoader.getSystemResource(path);
    if (imgUrl != null) {
      return new ImageIcon(imgUrl);
    } else {
      LOG.log(Level.SEVERE, "Error opening icon from resource.", new IOException());
      return null;
    }
  }
}
