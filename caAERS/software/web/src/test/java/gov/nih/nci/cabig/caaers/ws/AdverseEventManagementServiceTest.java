/**
 * 
 */
package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Study;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author MedaV
 *
 */
@ContextConfiguration(locations = "classpath:/pages-servlet.xml")
public class AdverseEventManagementServiceTest extends AbstractTestCase {
	
	Study study;
	StudyDao studyDao;
	
	public StudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	protected void setUp() throws Exception {
		super.setUp();
		study = new LocalStudy();
		study.setId(1);
		
		
	}

	@Test
	public void test() {
		assertNotNull(studyDao);
	}

}
