/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.tags;

import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
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
