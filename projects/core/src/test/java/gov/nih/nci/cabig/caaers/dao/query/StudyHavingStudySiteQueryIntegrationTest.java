package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author Biju Joseph
 */
public class StudyHavingStudySiteQueryIntegrationTest extends
        CaaersDbNoSecurityTestCase {

    private static Logger log = Logger.getLogger(StudyHavingStudySiteQueryIntegrationTest.class);
    
    StudyHavingStudySiteQuery query;
    StudyDao studyDao;
    
    @Override
    protected void setUp() throws Exception {
    	super.setUp();
    	query = new StudyHavingStudySiteQuery();
    	studyDao = (StudyDao)getDeployedApplicationContext().getBean("studyDao");
    }
    
    
    public void testFindStudies(){
    	List<Study> studies = studyDao.find(query);
    	assertEquals(3, studies.size());
    }
    
    public void testFindStudies_WithFilterOnDataEntry(){
    	query.filterByDataEntryStatus(true);
    	List<Study> studies = studyDao.find(query);
    	
    	assertEquals(2, studies.size());
    }
    
}
