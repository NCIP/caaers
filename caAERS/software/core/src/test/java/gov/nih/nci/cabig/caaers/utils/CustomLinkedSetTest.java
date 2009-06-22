package gov.nih.nci.cabig.caaers.utils;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class CustomLinkedSetTest extends TestCase {



	public void testAddAll() {
		
		CustomLinkedSet<String> cs = new CustomLinkedSet<String>();
		
		List<String> c1 = new ArrayList<String>();
		c1.add("red");
		c1.add("blue");
		c1.add("green");
		
		List<String> c2 = new ArrayList<String>();
		c2.add("red");
		c2.add("blue");
		c2.add("green");
		c2.add("red");
		
		
		
		List<String> c3 = new ArrayList<String>();
		c3.add("green");
		c3.add("green");
		c3.add("green");
		c3.add("blue");
		c3.add("red");
		
		cs.addAll(c1);
		
		assertEquals(3, cs.size());
		assertTrue(cs.contains("red"));
		assertTrue(cs.contains("blue"));
		assertTrue(cs.contains("green"));
		
		cs.clear();
		
		cs.addAll(c3);

		
		assertEquals(5, cs.size());
		assertTrue(cs.contains("red"));
		assertTrue(cs.contains("blue"));
		assertTrue(cs.contains("green"));
		
		cs.clear();
		cs.addAll(c1);
		cs.addAll(c2);
		cs.addAll(c3);
		
		assertEquals("[red, blue, green, red, green, green]", cs.toString());
	}

}
