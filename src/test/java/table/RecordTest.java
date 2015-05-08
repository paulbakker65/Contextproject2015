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

import parsers.StringValue;
import parsers.Value;

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
		Map<String,Value> expected = new HashMap<String,Value>();
		expected.put("fruit", new StringValue("banana"));
		expected.put("drink", new StringValue("milk"));
		
		assertEquals(expected,new Record(cols,new Value[]{new StringValue("banana"), new StringValue("milk")}));
		
	}
	
	@Test
	public void testToString(){
		Record r = new Record(cols,new StringValue[]{new StringValue("banana"), new StringValue("milk")});
		String result = r.toString();
		if (result.indexOf("banana")==-1||result.indexOf("milk")==-1){
			fail(result);
		}
	}
	

}
