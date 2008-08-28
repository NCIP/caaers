package gov.nih.nci.cabig.caaers.web.tags;

import junit.framework.TestCase;

public class RenderDecisionManagerTagTest extends TestCase {
	RenderDecisionManagerTag tag; 
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		tag = new RenderDecisionManagerTag();
	}
	
	
	public void testDoStartTagValidationError() throws Exception {
		tag.setUiType("junk");
		tag.setElementID("x");
		try{
			tag.doStartTag();
		}catch(Exception e){
			assertEquals ("Unknown value for uiType attribute allowed entries are - DIVISION, FIELD" , e.getMessage());
		}
	}

}
