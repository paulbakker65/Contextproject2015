package main;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class FilesTableTest {
	private FilesTable ft;
	private DataFile df1, df2, df3;
	
	@Before
	public void setUp(){
		ft = new FilesTable(new FilesTableModel());
	}
	
	@Test
	public void testAddRow() {
		ft.addRow(df1);
		ArrayList<DataFile> files = ft.getFiles();
		assertEquals(1, files.size());
		assertEquals(df1, files.get(0));
		
		ft.addRow(df2);
		files = ft.getFiles();
		assertEquals(2, files.size());
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
		assertEquals(2, files.size());
		assertEquals(df2, files.get(0));
		assertEquals(df3, files.get(1));
		
		ft.removeRow(1);
		ft.removeRow(0);
		
		files = ft.getFiles();
		assertEquals(0, files.size());
		
		ft.removeRow(5);
	}

	@Test
	public void testGetFiles() {
		ArrayList<DataFile> files = ft.getFiles();
		assertEquals(0, files.size());
		ft.addRow(df1);
		files = ft.getFiles();
		assertEquals(1, files.size());
		ft.removeRow(0);
		files = ft.getFiles();
		assertEquals(0, files.size());	
	}

}
