package net.tudelft.hi.e.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.Table;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.Dataset;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;


public class BoxPlotChart extends JFrame {

  private static final long serialVersionUID = 1L;

  private Table table;
  private String column;

  public BoxPlotChart(String windowTitle, Table table, String column) {
    super(windowTitle);
    this.table = table;
    this.column = column;

    Dataset dataset = createDataset();

    JFreeChart chart = createChart(dataset);

    ChartPanel chartPanel = new ChartPanel(chart);

    chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

    setContentPane(chartPanel);
  }


  private Dataset createDataset() {
    List<Double> items = new ArrayList<Double>();
    for (Record record : table) {
      items.add(((NumberValue) record.get(column)).getValue());
    }

    DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
    dataset.add(items, column, "Default Chunk");

    return dataset;
  }


  /**
   * Creates a chart.
   */
  private JFreeChart createChart(Dataset dataset) {
    JFreeChart chart = ChartFactory.createBoxAndWhiskerChart("Code frequency", // chart title
        "", // domain axis label
        column, // range axis label
        (BoxAndWhiskerCategoryDataset) dataset, // data
        true);
    return chart;
  }
}
