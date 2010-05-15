package gov.nih.nci.cabig.caaers.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import junit.framework.TestCase;

public class ProjectedListTest extends TestCase {
	List l = new ArrayList();
	ProjectedList<Integer> pl;
	Integer i;
	int[] j;

	protected void setUp() throws Exception {
		super.setUp();
		l.add("one");
		l.add("two");
		l.add("three");
		j[1] = 1;
		j[2] = 2;
		j[3] = 3;
		l.add(new Integer(j[0]));
		l.add(new Integer(j[1]));
		l.add(new Integer(j[2]));
		pl = new ProjectedList<Integer>(l, Integer.class);
		i = new Integer(22);

	}

	protected void tearDown() throws Exception {
		super.tearDown();
		pl = null;
		l = null;
	}

	public void testAddElement() {
		int size = pl.size();
		pl.add(i);
		assertEquals("Added integer " + i + " to Projected List but did not"
				+ "get the same value back", i, pl.get(size));
	}

	public void testAddElementAtIndex() {
		pl.add(1, i);
		assertEquals("Added integer " + i
				+ " to Projected List at position 1 but did not"
				+ "get the same value back", i, pl.get(1));
	}

	public void testRemoveElementAtIndex() {
		pl.add(1, i);
		pl.remove(1);
		assertSame("Wrong object was removed from list", i, pl.remove(1));
		assertFalse("Deleted Object is still available in list", pl.contains(i));
		assertEquals("List is of the wrong size", 3, pl.size());
	}

	public void testListIterator() {
		l.add(3, new String("test string"));
		pl.add(3, i);
		ListIterator<Integer> li = pl.listIterator();
		int size = 0;
		
		while (li.hasNext()) {
			assertEquals("Wrong elements returned",j[li.nextIndex()-1] ,li.next().intValue());
			size++;
		}
		assertEquals("Returned ListIterator is not of correct size", 4, size);
	}

	public void testRemoveElementAtIndex1() {

	}

	public void testSet() {

	}

	public void testSize() {

	}

	public void testSubList() {

	}

}
