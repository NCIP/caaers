package gov.nih.nci.cabig.caaers.web.admin;

import java.io.InputStream;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ImportControllerTest extends TestCase {
	ImportController controller;
	protected void setUp() throws Exception {
		super.setUp();
		controller = new ImportController();
	}

	public void testGetXSDLocation() {
		assertEquals("/schema/integration/StudySchema.xsd", controller.getXSDLocation("study"));
		assertEquals("/schema/integration/ParticipantSchema.xsd", controller.getXSDLocation("participant"));
		assertEquals("/schema/integration/routineAeXSD.xsd", controller.getXSDLocation("routineAeReport"));
		assertEquals("/schema/integration/Investigator.xsd", controller.getXSDLocation("investigator"));
		assertEquals("/schema/integration/ResearchStaff.xsd", controller.getXSDLocation("researchStaff"));
	}

	public void testGetResource() throws Exception{
		InputStream inputStream  = controller.getResource(controller.getXSDLocation("study"));
		assertNotNull(inputStream);
	}

}
