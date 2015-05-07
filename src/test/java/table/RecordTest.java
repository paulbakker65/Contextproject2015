package table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import input.Column;
import input.StringColumn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class RecordTest {

	ArrayList<Column> cols;
	
	@Before
	public void setUp(){
		cols = new ArrayList<Column>();
		cols.add(new StringColumn("fruit"));
		cols.add(new StringColumn("drink"));
	}
	
	@Test
	public void testContructor() {
		Map<String,String> expected = new HashMap<String,String>();
		expected.put("fruit", "banana");
		expected.put("drink", "milk");
		
		assertEquals(expected,new Record(cols,new String[]{"banana","milk"}));
		
	}
	
	@Test
	public void testToString(){
		Record r = new Record(cols,new String[]{"banana","milk"});
		String result = r.toString();
		if (result.indexOf("banana")==-1||result.indexOf("milk")==-1){
			fail(result);
		}
	}
	

}
