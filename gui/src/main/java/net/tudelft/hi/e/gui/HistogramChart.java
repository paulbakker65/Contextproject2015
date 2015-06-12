package net.tudelft.hi.e.gui;

import java.util.ArrayList;
import javax.swing.JFrame;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.Table;
import org.apache.commons.lang3.ArrayUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.Dataset;
import org.jfree.data.statistics.HistogramDataset;

public class HistogramChart extends JFrame {

  /**
   * Default serial ID.
   */
  private static final long serialVersionUID = 1L;
  private static HistogramDataset dataset;

  /**
   * Creates histogram of a stem leaf plot.
   *
   * @param table
   *          stem leaf plot to make a histogram of.
   */
  public HistogramChart(Table table, String columnName, int power) {
    HistogramChart.dataset = createDataset(table, columnName, power);

    JFreeChart chart = createChart(dataset);

    ChartPanel chartPanel = new ChartPanel(chart);

    chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

    setContentPane(chartPanel);

  }

  /**
   * Create a dataset for the histogram.
   *
   * @param columnName
   *          the column in which all the values are saved.
   * @param power
   *          which tenth power has the stem.
   * @return the dataset.
   */
  protected static HistogramDataset createDataset(Table table, String columnName, int power) {
    double bins = Math.pow(10, power);

    HistogramDataset dataset = new HistogramDataset();
    ArrayList<Double> values = new ArrayList<Double>();
    for (Record record : table) {
      if (record.get(columnName).isNumeric()) {
        NumberValue number = (NumberValue) record.get(columnName);
        values.add(number.getValue());
      }
    }

    ArrayList<Double> inBoundValues = removeOutOfBoundValues(values, 0, (int) bins);

    dataset.addSeries("Histogram", ArrayUtils.toPrimitive(inBoundValues.toArray(new Double[0])),
        10, 0, bins);

    return dataset;
  }

  /**
   * Removes values whom are out of bounds.
   *
   * @param values
   *          to check
   * @param lowerBound
   *          lower bound
   * @param upperBound
   *          upper bound
   * @return list without the out of bounds values.
   */
  private static ArrayList<Double> removeOutOfBoundValues(ArrayList<Double> values, int lowerBound,
      int upperBound) {
    ArrayList<Double> returnValues = new ArrayList<Double>();
    for (Double value : values) {
      if (value >= lowerBound && value <= upperBound) {
        returnValues.add(value);
      }
    }
    return returnValues;
  }

  /**
   * Returns the chart using JFreeCharts.
   *
   * @param dataset
   *          dataset created above.
   * @return JFreeChart Histogram.
   */
  protected static JFreeChart createChart(Dataset dataset) {
    JFreeChart chart =
        ChartFactory.createHistogram("Histogram", "Stem", "Frequency", (HistogramDataset) dataset,
            PlotOrientation.VERTICAL, false, false, false);
    return chart;

  }
}
