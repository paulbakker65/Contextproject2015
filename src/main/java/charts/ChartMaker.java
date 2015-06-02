package charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import table.Record;
import table.Table;

import java.util.HashMap;
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
    // TODO: Split up in methods
    HashMap<String, Integer> amount = new HashMap<String, Integer>();
    for (Record r : data) {
      String eventtype = r.get(col).toString();
      Integer current = amount.get(eventtype);
      if (current == null) {
        amount.put(eventtype, 1);
      } else {
        amount.put(eventtype, current + 1);
      }
    }

    DefaultCategoryDataset ds = new DefaultCategoryDataset();
    for (Entry<String, Integer> e : amount.entrySet()) {
      ds.addValue(e.getValue(), "Count", e.getKey());
    }

    JFreeChart chart = ChartFactory.createBarChart("Code frequency", // chart title
        "Code", // domain axis label
        "Value", // range axis label
        ds, // data
        PlotOrientation.VERTICAL, // orientation
        true, // include legend
        true, // tooltips?
        false // URLs?
        );
    return chart;
  }

}
