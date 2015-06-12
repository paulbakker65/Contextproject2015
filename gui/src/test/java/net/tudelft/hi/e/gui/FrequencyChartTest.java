package net.tudelft.hi.e.gui;

import java.io.File;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.gui.FrequencyChart;
import net.tudelft.hi.e.input.DataFile;
import org.jfree.data.category.DefaultCategoryDataset;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class FrequencyChartTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testDataSet() throws Exception {
    DataFile df =
        new DataFile(new File("src/test/resources/test3.csv"), new File(
            "src/test/resources/test3.xml"));
    Table table = df.getParser().parse(df.getReader());

    DefaultCategoryDataset ds =
        (DefaultCategoryDataset) FrequencyChart.createDataset(table, "Soort");

    assertEquals(3,ds.getValue("Appel", "1"));
    assertEquals(2,ds.getValue("Appel", "2"));
    assertEquals(2,ds.getValue("Peer", "1"));
    assertEquals(1,ds.getValue("Banaan", "1"));
    assertEquals(2,ds.getValue("Banaan", "2"));

  }

}
