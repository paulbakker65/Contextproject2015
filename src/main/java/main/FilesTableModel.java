package main;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class FilesTableModel implements TableModel{
	private final String[] columnNames = {"Data filepath", "Settings filepath"};
	private ArrayList<DataFile> files = new ArrayList<DataFile>();
	
    /**
     * Adds a DataFile to the table.
     * @param file A DataFile to be added.
     */
	public void addRow(DataFile file){
		files.add(file);
	}
	
	/**
	 * Removes a DataFile from the table.
	 * @param index
	 */
	public void removeRow(int index){
		if(index < files.size()){
			files.remove(index);
		}
	}

	/**
	 * Returns a list of files that are in the table.
	 * @return Returns ArrayList<DataFile> containing all DataFile's in the table.
	 */
	public ArrayList<DataFile> getFiles() {
		return files;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public int getRowCount() {
		return files.size();
	}

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
    	DataFile file = files.get(rowIndex);
    	if(columnIndex == 0){
    		return file.getFilepath();
    	}
    	else{
    		return file.getSettingsfilepath();
    	}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}


	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}
}
