package net.tudelft.hi.e.gui;

import java.io.File;

import net.tudelft.hi.e.data.ChunksFinder;
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
        (DefaultCategoryDataset) new FrequencyChart("Title", table, "Soort").createDataset();
    
    assertEquals(5,ds.getValue("Appel", ChunksFinder.DEFAULT_CHUNK_NAME));
    assertEquals(2,ds.getValue("Peer", ChunksFinder.DEFAULT_CHUNK_NAME));
    assertEquals(3,ds.getValue("Banaan", ChunksFinder.DEFAULT_CHUNK_NAME));
  }

}
