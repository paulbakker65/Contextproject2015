package net.tudelft.hi.e.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.swing.JFrame;
import net.tudelft.hi.e.data.Chunk;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;

public class FrequencyChart extends JFrame {

  public static final String DEFAULT_CHUNK_NAME = "Default Chunk";

  private static final long serialVersionUID = 1L;

  /**
   * Makes a new Frequency Frame
   * 
   * @param windowTitle
   *          title of the frame
   * @param table
   *          data to use
   * @param column
   *          column with values to check frequencies on.
   */
  public FrequencyChart(String windowTitle, Table table, String column) {
    super(windowTitle);
    
    Dataset dataset = createDataset(table, column);

    JFreeChart chart = createChart(dataset, column);

    ChartPanel chartPanel = new ChartPanel(chart);

    chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

    setContentPane(chartPanel);
  }

  /**
   * Creates a data set for frequency.
   * 
   * @param table
   *          source
   * @param column
   *          collumn to check frequency on
   * @return frequency data set
   */
  public static Dataset createDataset(Table table, String column) {
    DefaultCategoryDataset ds = new DefaultCategoryDataset();

    for (Chunk ch : extractChunks(table)) {
      HashMap<String, Integer> amount = new HashMap<String, Integer>();
      for (Record r : ch) {
        if (r.get(column).isNull()) {
          continue;
        }
        String eventtype = r.get(column).toString();
        Integer current = amount.get(eventtype);
        if (current == null) {
          amount.put(eventtype, 1);
        } else {
          amount.put(eventtype, current + 1);
        }
      }
      for (Entry<String, Integer> e : amount.entrySet()) {
        ds.addValue(e.getValue(), e.getKey(), ch.getLabel());
      }
    }

    return ds;
  }

  /**
   * Creates a frequency chart.
   *
   * @param dataset
   *          data set to use
   * @param column
   *          Name of column. Just used as label for the axis
   * @return frequency chart
   */
  public static JFreeChart createChart(Dataset dataset, String column) {
    return ChartFactory.createBarChart(column, // chart title
        "Chunk", // domain axis label
        "Frequency", // range axis label
        (CategoryDataset) dataset, // data
        PlotOrientation.VERTICAL, // orientation
        true, // include legend
        true, // tooltips?
        false // URLs?
        );
  }

  /**
   * Extract the chunks in a table, or the table itself if there are no chunks.
   * 
   * @param table
   *          table to extract from
   * @return list of chunks, or one chunk containing all the records
   */
  public static List<Chunk> extractChunks(Table table) {
    if (table.getChunks().isEmpty()) {
      Chunk defaultChunk = new Chunk(0, DEFAULT_CHUNK_NAME);
      defaultChunk.addAll(table);
      List<Chunk> chunkList = new ArrayList<Chunk>();
      chunkList.add(defaultChunk);
      return chunkList;
    }

    return table.getChunks();
  }
}
