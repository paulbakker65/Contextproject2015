package net.tudelft.hi.e.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Contains methods for GUI's.
 */
public class GUI {
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
      Image icon = ImageIO.read(ClassLoader.getSystemResource("icon.png"));
      window.setIconImage(icon);
    } catch (IOException e1) {
      System.err.println("Error opening icon from resource.");
    }
  }
}
