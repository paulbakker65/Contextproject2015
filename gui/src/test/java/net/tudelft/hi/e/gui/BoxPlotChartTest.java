package net.tudelft.hi.e.gui;

import static org.junit.Assert.assertEquals;

import net.tudelft.hi.e.data.ChunksFinder;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.input.DataFile;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.junit.Test;

import java.io.File;

public class BoxPlotChartTest {

  @Test
  public void testDataSet() throws Exception {
    DataFile df =
        new DataFile(new File("src/test/resources/test3.csv"), new File(
            "src/test/resources/test3.xml"));
    Table table = df.getParser().parse(df.getReader());



    DefaultBoxAndWhiskerCategoryDataset ds =
        (DefaultBoxAndWhiskerCategoryDataset) BoxPlotChart.createDataset(table, "Beoordeling");

    assertEquals(9.0, ds.getMaxOutlier("Beoordeling", ChunksFinder.DEFAULT_CHUNK_NAME));
    assertEquals(1.0, ds.getMinOutlier("Beoordeling", ChunksFinder.DEFAULT_CHUNK_NAME));

  }

}
