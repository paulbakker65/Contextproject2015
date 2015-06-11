package visualizations;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.Dataset;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

import table.Record;
import table.Table;
import table.value.NumberValue;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class BoxPlotChart extends JFrame {

  private static final long serialVersionUID = 1L;
  
  private Table table;
  private String column;

  /**
   * Create box plot chart.
   * @param windowTitle title
   * @param table on which to create the box plot chart.
   * @param column where the values are.
   */
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
      if (record.get(column).isNumeric()) {
        items.add(((NumberValue) record.get(column)).getValue());
      }
      
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
