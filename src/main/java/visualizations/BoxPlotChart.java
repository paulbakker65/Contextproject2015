package visualizations;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.general.Dataset;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

import table.Chunk;
import table.Record;
import table.Table;
import table.value.NumberValue;
import table.value.Value;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class BoxPlotChart extends JFrame {

  private static final long serialVersionUID = 1L;



  /**
   * Makes a new Box and Whisker plot, (boxplot).
   * @param windowTitle title of the frame
   * @param table data to use
   * @param column number column to calculate mean, min, max etc
   */
  public BoxPlotChart(String windowTitle, Table table, String column) {
    super(windowTitle);

    Dataset dataset = createDataset(table, column);

    JFreeChart chart = createChart(dataset, column);

    ChartPanel chartPanel = new ChartPanel(chart);

    chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

    setContentPane(chartPanel);
  }

  /**
   * Creates a Box plot dataset.
   * @param table the source
   * @param column column to take min, max, average etc.
   * @return the dataset
   */
  public static Dataset createDataset(Table table, String column) {
    DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
    for (Chunk chunk : FrequencyChart.extractChunks(table)) {
      List<Double> items = new ArrayList<Double>();
      for (Record record : chunk) {
        Value value = record.get(column);
        if (value.isNumeric()) {
          items.add(((NumberValue) value).getValue());
        }
      }
      dataset.add(items, column, chunk.getLabel());
    }

    return dataset;
  }

  /**
   * Creates a boxplot chart.
   * @param dataset source
   * @param column column to do calculations on
   * @return boxplot
   */
  public static JFreeChart createChart(Dataset dataset, String column) {
    JFreeChart chart = ChartFactory.createBoxAndWhiskerChart("Code frequency", // chart title
        "", // domain axis label
        column, // range axis label
        (BoxAndWhiskerCategoryDataset) dataset, // data
        true);
    CategoryPlot catplot = (CategoryPlot) chart.getPlot();
    BoxAndWhiskerRenderer renderer = (BoxAndWhiskerRenderer) catplot.getRenderer();
    renderer.setMaximumBarWidth(0.20);

    return chart;
  }
}
