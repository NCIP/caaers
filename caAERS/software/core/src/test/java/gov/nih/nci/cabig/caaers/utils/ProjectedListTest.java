package gov.nih.nci.cabig.caaers.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import junit.framework.TestCase;

public class ProjectedListTest extends TestCase {
	List l = new ArrayList();
	List bl; //backing list used for validating list state
	ProjectedList<Integer> pl;
	Integer i;
	int[] j = new int[3];

	protected void setUp() throws Exception {
		super.setUp();
		l.add("one");
		l.add("two");
		l.add("three");
		j[0] = 1;
		j[1] = 2;
		j[2] = 3;
		l.add(new Integer(j[0]));
		l.add(new Integer(j[1]));
		l.add(new Integer(j[2]));
		bl=(List) ((ArrayList)l).clone();
		pl = new ProjectedList<Integer>(l, Integer.class);
		i = new Integer(22);

	}

	protected void tearDown() throws Exception {
		super.tearDown();
		pl = null;
		bl=null;
		l = null;
	}

	public void testAddElement() {
		int size = pl.size();
		pl.add(i);
		bl.add(i);
		assertEquals("Incorrect addition of element to list",pl.getInternalList(),bl );
	}

	public void testAddElementAtIndex() {
		pl.add(1, i);
		bl.add(4,i);
		assertEquals("Incorrect addition of element to list at specified index",bl, pl.getInternalList());
	}

	public void testRemoveElementAtIndex() {
		pl.remove(1);
		bl.remove(4);
		assertEquals("Incorrect removal of element to list at specified index", bl, pl.getInternalList());
//		assertSame("Wrong object was removed from list", i, pl.remove(1));
//		assertFalse("Deleted Object is still available in list", pl.contains(i));
//		assertEquals("List is of the wrong size", 3, pl.size());
	}

	public void testListIterator() {
		
		ListIterator<Integer> li = pl.listIterator();
		int size = 0;
		
		while (li.hasNext()) {
			assertEquals("Wrong elements returned",j[li.nextIndex()] ,li.next().intValue());
			size++;
		}
		assertEquals("Returned ListIterator is not of correct size", 3, size);
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
