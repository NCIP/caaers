package gov.nih.nci.cabig.caaers.web.search;

import junit.framework.TestCase;

public class AdvanceSearchColumnTest extends TestCase{
	
	AdvancedSearchColumn advSearchColumn;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		advSearchColumn = new AdvancedSearchColumn();
	}
	
	public void testGetColumnHeader()	{
		advSearchColumn.setColumnHeader("COLUMN_HEADER");
		assertEquals("COLUMN_HEADER",advSearchColumn.getColumnHeader());
		
	}
	
	public void testGetLengthyValue()	{
		advSearchColumn.setLengthyValue("LENGTHY_VALUE");
		assertEquals("LENGTHY_VALUE",advSearchColumn.getLengthyValue());
	}
	
	public void testGetValue()	{
		advSearchColumn.setValue("VALUE");
		assertEquals("VALUE",advSearchColumn.getValue());
	}

}
