package gov.nih.nci.cabig.caaers.dao.query;

import java.util.List;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Study;

/**
 * 
 * @author Biju Joseph
 *
 */
public class StudyQueryIntegrationTest extends CaaersDbNoSecurityTestCase {
	
	  StudyQuery query ;
	  StudyDao studyDao;
	    
	    @Override
	    protected void setUp() throws Exception {
	    	super.setUp();
	    	query = new StudyQuery();
	    	studyDao = (StudyDao)getDeployedApplicationContext().getBean("studyDao");
	    }
	    
	    
	    public void testFindStudies(){
	    	List<Study> studies = studyDao.find(query);
	    	assertEquals(4, studies.size());
	    }
	    
	    public void testFindStudies_WithFilterOnDataEntry(){
	    	query.filterByDataEntryStatus(true);
	    	List<Study> studies = studyDao.find(query);
	    	
	    	assertEquals(3, studies.size());
	    }
	    
	    public void testFindStudies_WithFilterOnSponsor(){
	    	query.joinStudyOrganization();
	    	query.filterByFundingSponsorNameExactMatch("National Cancer Institute");
	    	List<Study> studies = studyDao.find(query);
	    	assertEquals(3, studies.size());
	    }
	    
	    public void testFindStudies_WithFilterOnStudyOrgName(){
	    	query.joinStudyOrganization();
	    	query.filterByStudyOrganizationNameExactMatch("CALGB");
	    	List<Study> studies = studyDao.find(query);
	    	assertEquals(4, studies.size());
	    }
	    
	    public void testFindStudies_WithFilterOnSponsorAndDataEntry(){
	    	query.joinStudyOrganization();
	    	query.filterByDataEntryStatus(true);
	    	query.filterByFundingSponsorNameExactMatch("National Cancer Institute");
	    	List<Study> studies = studyDao.find(query);
	    	assertEquals(2, studies.size());
	    }

}
