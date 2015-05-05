package table;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Table extends ArrayList<Tuple> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(new Scanner(new Table().getClass().getResourceAsStream("/csvexample.csv").toString()));
	}
	
	public Tuple getPatientByID(int id) {
		 for(int i = 0; i < this.size(); i++) {
			if(this.get(i).containsKey("userid")){
				if(Integer.valueOf(this.get(i).get("userid")) == id){
					return this.get(i);
				}
			}
		 }
		return null;
	}
	
	
	public String toString() {
		String res = "";
		
		for (Tuple tuple : this) {
			res += tuple.toString() + "\n";
		}
		
		return res;
	}
	
}
