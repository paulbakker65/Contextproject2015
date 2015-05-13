package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class filesTableTest {
	private filesTable ft = new filesTable();
	private DataFile df1, df2, df3;
	
	@Test
	public void testAddRow() {
		ft.addRow(df1);
		ArrayList<DataFile> files = ft.getFiles();
		assert(files.size() == 1);
		assertEquals(df1, files.get(0));
		
		ft.addRow(df2);
		files = ft.getFiles();
		assert(files.size() == 2);
		assertEquals(df1, files.get(0));
		assertEquals(df2, files.get(1));
	}

	@Test
	public void testRemoveRow() {
		ft.addRow(df1);
		ft.addRow(df2);
		ft.addRow(df3);
		
		ft.removeRow(0);
		ArrayList<DataFile> files = ft.getFiles();
		assert(files.size() == 2);
		assertEquals(df2, files.get(0));
		assertEquals(df3, files.get(1));
		
		ft.removeRow(1);
		ft.removeRow(0);
		
		files = ft.getFiles();
		assert(files.size() == 0);
		
		ft.removeRow(5);
	}

	@Test
	public void testGetFiles() {
		ArrayList<DataFile> files = ft.getFiles();
		assert(files.size() == 0);
		ft.addRow(df1);
		files = ft.getFiles();
		assert(files.size() == 1);
		ft.removeRow(0);
		files = ft.getFiles();
		assert(files.size() == 1);	
	}

}
