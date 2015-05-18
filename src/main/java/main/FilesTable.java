package main;

import javax.swing.*;
import javax.swing.table.TableModel;

import java.util.ArrayList;


public class FilesTable extends JTable {
	static FilesTableModel ftm = new FilesTableModel();

	public FilesTable() {
		super(ftm);
	}
	
	public FilesTable(FilesTableModel model){
		super(model);
		ftm = model;
	}
	
	public void addRow(DataFile file){
		ftm.addRow(file);
		repaint();
	}

	public void removeRow(int index){
		ftm.removeRow(index);
		repaint();
	}

    public ArrayList<DataFile> getFiles(){
        return ftm.getFiles();
    }
	
}
