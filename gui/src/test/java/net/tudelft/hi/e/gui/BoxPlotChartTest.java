package net.tudelft.hi.e.gui;

import java.io.File;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.input.DataFile;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class BoxPlotChartTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testDataSet() throws Exception {
    DataFile df =
        new DataFile(new File("src/test/resources/test3.csv"), new File(
            "src/test/resources/test3.xml"));
    Table table = df.getParser().parse(df.getReader());



    DefaultBoxAndWhiskerCategoryDataset ds =
        (DefaultBoxAndWhiskerCategoryDataset) BoxPlotChart.createDataset(table, "Beoordeling");


    System.out.println();
    assertEquals(9.0, ds.getMaxOutlier("Beoordeling", FrequencyChart.DEFAULT_CHUNK_NAME));
    assertEquals(1.0, ds.getMinOutlier("Beoordeling", FrequencyChart.DEFAULT_CHUNK_NAME));

  }

}
