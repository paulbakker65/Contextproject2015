package charts;

import input.DataFile;

import org.jfree.chart.ChartPanel;
import org.jfree.ui.RefineryUtilities;
import org.junit.Before;

import table.Table;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JFrame;

/**
 * Tests chart, by showing it :).
 * 
 * @author unset
 *
 */
public class ChartMakerTest extends JFrame {

  private static final long serialVersionUID = 1L;

  @Before
  public void setUp() throws Exception {
  }

  /**
   * Test for charts.
   * 
   * @param args
   *          arguments
   * @throws Exception
   *           exception
   */
  public static void main(String[] args) throws Exception {
    ChartMakerTest test = new ChartMakerTest();
    test.doStuff();
    
  }

  /**
   * Does stuff such as showing a chart.
   * 
   * @throws Exception
   *           an exception
   */
  public void doStuff() throws Exception {

    ChartMaker cm = new ChartMaker();
    DataFile df =
        new DataFile(new File("src/test/resources/test3.csv"), new File(
            "src/test/resources/test3.xml"));
    Table table = df.getParser().parse(df.getReader());

    System.out.println(table.toString());

    ChartPanel chartPanel = new ChartPanel(cm.createFrequencyChart(table, "Soort"));
    chartPanel.setPreferredSize(new Dimension(500, 270));
    setContentPane(chartPanel);

    pack();
    RefineryUtilities.centerFrameOnScreen(this);
    setVisible(true);

  }

}
