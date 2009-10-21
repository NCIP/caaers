package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.webservice.Studies;
import gov.nih.nci.cabig.caaers.webservice.Study;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class XMLUtilTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetStudyXML() {
		
		Studies studies = new Studies();
		Study study = new Study();
		study.setShortTitle("test");
		List<Study> studyList = new ArrayList<Study>();
		studyList.add(study);
		studies.setStudy(studyList);
		
		String xml = XMLUtil.getStudyXML(studies);
		assertNotNull(xml);
		assertTrue(xml.contains("<shortTitle>test</shortTitle>"));
	}
	
	public void testGetStudyXML_InvalidContent() {
		String xml = XMLUtil.getStudyXML("some junk");
		assertNull(xml);
	}

}
