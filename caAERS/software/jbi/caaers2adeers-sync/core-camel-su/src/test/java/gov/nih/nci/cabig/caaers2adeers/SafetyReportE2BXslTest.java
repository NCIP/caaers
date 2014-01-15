/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

import gov.nih.nci.cabig.XsltTransformer;

import java.io.IOException;

import junit.framework.TestCase;


public abstract class SafetyReportE2BXslTest extends TestCase{
	
	private XsltTransformer xsltTrans;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		xsltTrans = new XsltTransformer();
	}
	
	private String getPathNameString(String relativepath){
		
		try {
			return xsltTrans.getResources(relativepath)[0].getFile().getPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	protected String transform(String xmlFile, String xsltFile) throws Exception{
		String result = null;
		result = xsltTrans.toText(getPathNameString(xmlFile),getPathNameString(xsltFile));
		
		return result;
	}
	
}
