package charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

import table.Chunk;
import table.Record;
import table.Table;
import table.value.NumberValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class ChartMaker {

  /**
   * Generates a frequency chart.
   * 
   * @param data
   *          the source data
   * @param col
   *          the column to count
   * @return frequency chart
   */
  public JFreeChart createFrequencyChart(Table data, String col) {
    DefaultCategoryDataset ds = new DefaultCategoryDataset();
    
    for (Chunk ch : extractChunks(data)) {
      HashMap<String, Integer> amount = new HashMap<String, Integer>();
      for (Record r : ch) {
        String eventtype = r.get(col).toString();
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

    JFreeChart chart = ChartFactory.createBarChart("Code frequency", // chart title
        "Chunk", // domain axis label
        "Value", // range axis label
        ds, // data
        PlotOrientation.VERTICAL, // orientation
        true, // include legend
        true, // tooltips?
        false // URLs?
        );
    return chart;
  }

  /**
   * Generates a BoxPlot (box and whisker).
   * 
   * @param data
   *          the source data
   * @param col
   *          the column to show
   * @return boxplot chart
   */
  public JFreeChart createBoxPlotChart(Table data, String col) {
    // TODO: Split up in methods
    List<Double> items = new ArrayList<Double>();
    for (Record r : data) {
      items.add(((NumberValue) r.get(col)).getValue());
    }

    DefaultBoxAndWhiskerCategoryDataset ds = new DefaultBoxAndWhiskerCategoryDataset();
    ds.add(items, col, "Default Chunk");

    JFreeChart chart = ChartFactory.createBoxAndWhiskerChart("Code frequency", // chart title
        "", // domain axis label
        col, // range axis label
        ds, // data
        true);
    return chart;
  }

  private List<Chunk> extractChunks(Table table) {
    if (table.getChunks().size() == 0) {
      System.out.println("Regen chunks");
      // regen chunks
      HashMap<String, List<Record>> chunkhm = new HashMap<String, List<Record>>();
      for (Record r : table) {
        String chunkname = r.get("Chunk").toString();
        List<Record> chunk = chunkhm.get(chunkname);
        if (chunk == null) {
          chunk = new ArrayList<Record>();
        }
        chunk.add(r);
        chunkhm.put(chunkname, chunk);
      }
      int ii = 0;
      for (Entry<String, List<Record>> e : chunkhm.entrySet()) {
        System.out.println("Chunk added");
        Chunk chunk = new Chunk(ii, e.getKey());
        ii++;
        for (Record r : e.getValue()) {
          chunk.add(r);
        }
        table.addChunk(chunk);

      }
    }
    return table.getChunks();
  }

}
