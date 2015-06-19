package net.tudelft.hi.e.gui;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import net.tudelft.hi.e.data.Chunk;
import net.tudelft.hi.e.data.ChunksFinder;
import net.tudelft.hi.e.data.Code;
import net.tudelft.hi.e.data.DateColumn;
import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import javax.swing.JPanel;

class FrequencyChart {
  private static final long serialVersionUID = 1L;
  private Table table;
  private int chunkDepth;

  /**
   * Makes a new Frequency Frame
   *
   * @param table data to use
   * @param column column with values to check frequencies on.
   * @param chunkDepth depth of chunks
   */
  public static JPanel createPanel(Table table, int chunkDepth, String column) {
    FrequencyChart chart = new FrequencyChart(table, chunkDepth);
		
    Dataset dataset = createDataset(table, column, chunkDepth);
    return chart.createChartPanel(chart.createChart(dataset, column));
  }

  /**
   * Makes a Frequency Frame with codes
   *
   * @param table data to use
   * @param chunkDepth depth of chunks
   */
  public static JPanel createPanel(Table table, int chunkDepth) {
	FrequencyChart chart = new FrequencyChart(table, chunkDepth);
		
    Dataset dataset = chart.createCodesDataset();
    return chart.createChartPanel(chart.createChart(dataset, "Codes"));
  }

  private FrequencyChart(Table table, int chunkDepth) {
    this.table = table;
    this.chunkDepth = chunkDepth;
  }

  private ChartPanel createChartPanel(JFreeChart chart) {
    ChartPanel chartPanel = new ChartPanel(chart);
    CategoryPlot plot = (CategoryPlot) chart.getPlot();
    BarRenderer renderer = (BarRenderer) plot.getRenderer();
    renderer.setShadowVisible(false);
    renderer.setBarPainter(new StandardBarPainter());
    chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
    return chartPanel;
  }

  private Dataset createCodesDataset() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    for (Chunk chunk : ChunksFinder.extractChunks(table, chunkDepth)) {
      for (Code code : chunk.getCodes().values()) {
        dataset.addValue(code.getFrequency(), code.getName(), chunk.getLabel());
      }
    }

    return dataset;
  }

  /**
   * Creates a data set for frequency.
   * 
   * @param table source
   * @param column column to check frequency on
   * @return frequency data set
   */
  static Dataset createDataset(Table table, String column, int chunkDepth) {
    if (isTimeColumn(table, column)) {
      return createDatesDataset(table, column, chunkDepth);
    }

    DefaultCategoryDataset ds = new DefaultCategoryDataset();

    for (Chunk chunk : ChunksFinder.extractChunks(table, chunkDepth)) {
      HashMap<String, Integer> amount = new LinkedHashMap<String, Integer>();
      for (Record record : chunk) {
        if (record.get(column).isNull()) {
          continue;
        }
        String eventtype = record.get(column).toString();
        Integer current = amount.get(eventtype);
        if (current == null) {
          amount.put(eventtype, 1);
        } else {
          amount.put(eventtype, current + 1);
        }
      }
      for (Entry<String, Integer> e : amount.entrySet()) {
        ds.setValue(e.getValue(), e.getKey(), chunk.getLabel());
      }
    }

    return ds;
  }

  private static Dataset createDatesDataset(Table table, String column, int chunkDepth) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    for (Chunk chunk : ChunksFinder.extractChunks(table, chunkDepth)) {
      int[] amount = new int[24];
      for (Record record : chunk) {
        if (record.get(column).isNull()) {
          continue;
        }
        amount[((DateValue) record.get(column)).getValue().get(Calendar.HOUR_OF_DAY)]++;
      }
      for (int i = 0; i < amount.length; i++) {
        dataset.addValue(amount[i], new Integer(i), chunk.getLabel());
      }
    }
    return dataset;
  }

  private static boolean isTimeColumn(Table table, String column) {
    if (table.isEmpty()) {
      return false;
    }

    Iterator<Record> iterator = table.iterator();
    Record curRecord = iterator.next();

    while (curRecord.get(column).isNull() && iterator.hasNext()) {
      curRecord = iterator.next();
    }
    Value value = curRecord.get(column);
    return value.isTime()
        || (value.isDate() && DateColumn.ISO_FORMAT_STR.equals(((DateValue) value).getFormat()));
  }

  /**
   * Creates a frequency chart.
   *
   * @param dataset data set to use
   * @param title Title of the chart. Just used as label for the axis
   * @return frequency chart
   */
  public JFreeChart createChart(Dataset dataset, String title) {
    return ChartFactory.createBarChart(title, // chart title
        "Chunk", // domain axis label
        "Frequency", // range axis label
        (CategoryDataset) dataset, // data
        PlotOrientation.VERTICAL, // orientation
        true, // include legend
        true, // tooltips?
        false // URLs?
        );
  }
}
