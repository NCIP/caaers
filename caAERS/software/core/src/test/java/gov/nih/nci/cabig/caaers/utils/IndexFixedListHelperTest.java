package gov.nih.nci.cabig.caaers.utils;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class IndexFixedListHelperTest extends TestCase {
	
	IndexFixedListHelper helper;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		helper = new IndexFixedListHelper();
	}
	
	public void testAdd() {
		helper.add(XYZ.class);
		assertNotNull("Internal list should not be null",helper.getIndexFixeList(XYZ.class));
		assertNotNull("IndexFixed list, internal list should not be null", helper.getInternalList(XYZ.class));
	}

	public void testSetInternalList() {
		ArrayList<XYZ> l = new ArrayList<XYZ>();
		l.add(new XYZ(1));
		l.add(new XYZ(2));
		l.add(new XYZ(3));
		l.add(new XYZ(4));
		
		helper.setInternalList(XYZ.class, l);
		
		List<XYZ> l2 = helper.getIndexFixeList(XYZ.class);
		assertNotNull(l2);
		
		assertSame(l.size(), l2.size());
	}

	public void testGetIndexFixeList() {
		ArrayList<XYZ> l = new ArrayList<XYZ>();
		l.add(new XYZ(1));
		l.add(new XYZ(2));
		l.add(new XYZ(3));
		l.add(new XYZ(4));
		
		helper.setInternalList(XYZ.class, l);
		
		List<XYZ> l2 = helper.getIndexFixeList(XYZ.class);
		assertNotNull(l2);
		l2.remove(2);
		
		assertSame(l.size(), l2.size() - 1);
	}

	public void testGetInternalList() {
		ArrayList<XYZ> l = new ArrayList<XYZ>();
		l.add(new XYZ(1));
		l.add(new XYZ(2));
		l.add(new XYZ(3));
		l.add(new XYZ(4));
		
		helper.setInternalList(XYZ.class, l);
		
		List<XYZ> l2 = helper.getIndexFixeList(XYZ.class);
		assertNotNull(l2);
		l2.add(new XYZ(5));
		assertSame(l.size(), l2.size());
	}
	
	class XYZ {
		int i = 0;
		public XYZ(int i ) {
			this.i = i;
		}
	}

}
