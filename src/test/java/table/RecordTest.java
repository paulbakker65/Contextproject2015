package table;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class RecordTest {

	@Test
	public void testContructor() {
		Map<String,String> expected = new HashMap<String,String>();
		expected.put("fruit", "banana");
		expected.put("drink", "milk");
		
		assertEquals(expected,new Record(new String[] {"fruit","drink"},new String[]{"banana","milk"}));
		
	}
	
	@Test
	public void testToString(){
		Record r = new Record(new String[] {"fruit","drink"},new String[]{"banana","milk"});
		String result = r.toString();
		if (result.indexOf("banana")==-1||result.indexOf("milk")==-1){
			fail(result);
		}
	}
	

}
