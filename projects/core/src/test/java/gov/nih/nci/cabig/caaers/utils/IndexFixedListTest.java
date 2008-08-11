package gov.nih.nci.cabig.caaers.utils;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class IndexFixedListTest extends TestCase {
	
	List<String> l = new ArrayList<String>();
	IndexFixedList<String> fl;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		l.add("0");
		l.add("1");
		l.add("2");
		l.add("3");
		l.add("4");
		l.add("5");
		l.add("6");
		l.add("7");
		
		fl = new IndexFixedList<String>(l);
	}
	public void testFixedListDeletion(){
		fl.remove(4);
		String s = fl.get(5);
		assertEquals(s, "5");
		s = fl.get(2);
		assertSame(s,"2");
		assertSame(fl.get(0), "0");
		assertSame(fl.get(7), "7");
		
		fl.add("8");
		assertSame(fl.get(8), "8");
		
		fl.remove(0);
		assertSame(fl.get(1),"1");
		assertSame(fl.get(8),"8");
		
		assertSame(9, fl.size());
	}
}
