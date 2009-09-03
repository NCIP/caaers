package gov.nih.nci.cabig.caaers.dao.query;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;

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
	    
	    public void testFindStudies_IgnoreByStudyId(){
	    	query.joinIdentifier();
	    	
	    	Organization org = Fixtures.createOrganization("test", -1003);
	    	OrganizationAssignedIdentifier id = Fixtures.createOrganizationAssignedIdentifier("1138-42", org);
	    	id.setType(id.COORDINATING_CENTER_IDENTIFIER_TYPE);
	    	id.setPrimaryIndicator(true);
	    	id.setValue("1138-42");
	    	
	    	query.filterByIdentifier(id);
	    	query.ignoreStudyById(new Integer(-2));
	    	List<Study> studies = studyDao.find(query);
	    	assertTrue(CollectionUtils.isEmpty(studies));
	    }
	    
	    public void testFindStudies_FilterByOrganizationAssinedIdentifier(){
	    	query.joinIdentifier();
	    	
	    	Organization org = Fixtures.createOrganization("test", -1003);
	    	OrganizationAssignedIdentifier id = Fixtures.createOrganizationAssignedIdentifier("1138-42", org);
	    	id.setType(id.COORDINATING_CENTER_IDENTIFIER_TYPE);
	    	id.setPrimaryIndicator(true);
	    	id.setValue("1138-42");
	    	
	    	query.filterByIdentifier(id);
	    	List<Study> studies = studyDao.find(query);
	    	assertEquals(1, studies.size());
	    	assertEquals(new Integer(-2), studies.get(0).getId());
	    }
	    
	    public void testFindStudies_FilterByOrganizationAssinedIdentifier_NoMatch(){
	    	query.joinIdentifier();
	    	
	    	Organization org = Fixtures.createOrganization("test", -1001);
	    	OrganizationAssignedIdentifier id = Fixtures.createOrganizationAssignedIdentifier("1138-42", org);
	    	id.setType(id.COORDINATING_CENTER_IDENTIFIER_TYPE);
	    	id.setPrimaryIndicator(true);
	    	id.setValue("1138-42");
	    	
	    	query.filterByIdentifier(id);
	    	List<Study> studies = studyDao.find(query);
	    	assertTrue(CollectionUtils.isEmpty(studies));
	    }
	    
	    public void testFindStudies_FilterBySystemAssinedIdentifier(){
	    	query.joinIdentifier();
	    	
	    	SystemAssignedIdentifier id = Fixtures.createSystemAssignedIdentifier("caaers");
	    	id.setPrimaryIndicator(true);
	    	id.setType("MRN");
	    	id.setValue("A1234");
	    	id.setSystemName("caaers");
	    	
	    	query.filterByIdentifier(id);
	    	List<Study> studies = studyDao.find(query);
	    	assertEquals(1, studies.size());
	    	assertEquals(new Integer(-3), studies.get(0).getId());
	    }
}
