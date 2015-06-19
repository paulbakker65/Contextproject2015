package net.tudelft.hi.e.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class GuiTest {
  private static final String[] icons = {
    "add", "edit", "exit", "folder", "icon", "remove", "run", "save", "search", "settings", "table"
  };

  @Test
  public void testCreateImageIcon() {
    for (String file : icons) {
      assertNotNull(Gui.createImageIcon(file + ".png"));
    }
  }
  
  @Test
  public void testCreateInternalFrame() {
    JPanel panel = new JPanel();
    JInternalFrame frame = Gui.createInternalFrame("sometitle", panel);
    assertNotNull(frame);
    assertTrue(frame.isVisible() && frame.isResizable()
        && frame.isClosable() && frame.isMaximizable() && frame.isIconifiable());
    assertTrue(frame.getFrameIcon().toString().contains("icon.png"));
    assertEquals(panel, frame.getContentPane());
  }

}
