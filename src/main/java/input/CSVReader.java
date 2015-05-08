package input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import table.Table;
import table.Record;


public class CSVReader extends Reader{
	private String delimiter = ";";
	
	private FileReader fr;
	private BufferedReader br;
	
	
	public CSVReader(String filepath) throws FileNotFoundException {
		super();
		this.filepath = filepath;
		
		fr = new FileReader(filepath);
	    br = new BufferedReader(fr);
	}
	
	

	public CSVReader(String filepath, String delimiter) throws FileNotFoundException {
		this(filepath);
		this.delimiter = delimiter;
	}



	public String[] readRow(){
		String line = null;
		try {
			line = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(line == null)
			return null;
		String[] record = line.split(delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		return record;
	}
	

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
}
