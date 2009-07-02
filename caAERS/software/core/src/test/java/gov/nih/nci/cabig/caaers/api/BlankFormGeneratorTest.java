package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;

import org.easymock.classextension.EasyMock;

public class BlankFormGeneratorTest extends CaaersTestCase {
	private BlankFormGenerator g;
    StudyDao studyDao;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
        studyDao = (StudyDao)getDeployedApplicationContext().getBean("studyDao");
		g = new BlankFormGenerator();
	}

	public void testGenerateCaaersXml() throws Exception {
        System.out.println(studyDao == null);
	}

}
