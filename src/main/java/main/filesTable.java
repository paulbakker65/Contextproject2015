package main;

import javax.swing.*;
import java.util.ArrayList;


public class filesTable extends JTable {
	static filesTableModel ftm = new filesTableModel();

	public filesTable() {
		super(ftm);
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
