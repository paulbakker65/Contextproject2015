package visualizations;

import static org.junit.Assert.assertEquals;

import input.DataFile;

import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.Before;
import org.junit.Test;

import table.Table;

import java.io.File;

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
    
    assertEquals(5,ds.getValue("Appel", FrequencyChart.DEFAULT_CHUNK_NAME));
    assertEquals(2,ds.getValue("Peer", FrequencyChart.DEFAULT_CHUNK_NAME));
    assertEquals(3,ds.getValue("Banaan", FrequencyChart.DEFAULT_CHUNK_NAME));

  }

}
