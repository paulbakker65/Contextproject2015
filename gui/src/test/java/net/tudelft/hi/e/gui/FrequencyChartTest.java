package net.tudelft.hi.e.gui;

import static org.junit.Assert.assertEquals;

import net.tudelft.hi.e.data.ChunksFinder;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.input.DataFile;

import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.Test;

import java.io.File;


public class FrequencyChartTest {

  @Test
  public void testDataSet() throws Exception {
    DataFile df =
        new DataFile(new File("src/test/resources/test3.csv"), new File(
            "src/test/resources/test3.xml"));
    Table table = df.getParser().parse(df.getReader());

    
    DefaultCategoryDataset ds =
        (DefaultCategoryDataset) FrequencyChart.createDataset(table, "Soort", 1);
    
    assertEquals(5,ds.getValue("Appel", ChunksFinder.DEFAULT_CHUNK_NAME));
    assertEquals(2,ds.getValue("Peer", ChunksFinder.DEFAULT_CHUNK_NAME));
    assertEquals(3,ds.getValue("Banaan", ChunksFinder.DEFAULT_CHUNK_NAME));
  }
}
