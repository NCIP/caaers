package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.List;

/**
 * @author Biju Joseph
 */
public class StudyRepositoryIntegrationTest extends CaaersDbNoSecurityTestCase {

    private OrganizationDao organizationDao = (OrganizationDao) getApplicationContext().getBean(
            "organizationDao");

    private ParticipantDao participantDao = (ParticipantDao) getApplicationContext().getBean(
            "participantDao");

    StudyRepository studyRepository = (StudyRepository) getApplicationContext().getBean("studyRepository");

    public String getTestDataFileName() {
        String fileName = "testdata/StudyRepositoryTest.xml";
        return fileName;
    }
    
    public void testGet(){
    }

    public void testSearchStudyByExample() throws Exception {
        Study study = new LocalStudy();
        study.setShortTitle("Gemcitabine");
        OrganizationAssignedIdentifier organizationAssignedIdentifier = new OrganizationAssignedIdentifier();
        organizationAssignedIdentifier.setType("NCI assigned identifier");
        organizationAssignedIdentifier.setValue("NCI-2009-00008");
        study.addIdentifier(organizationAssignedIdentifier);
        List<Study> studies = studyRepository.search(study);
        assertNotNull("Studes is null", studies);
    }
    
    public void testFind(){
    	StudySearchableAjaxableDomainObjectQuery query = new StudySearchableAjaxableDomainObjectQuery();
    	query.filterStudiesWithMatchingIdentifierOnly("NCI-2009-00008");
    	List<Object[]> objects = studyRepository.search(query,"idtf","NCI-2009-00008");
    	assertNotNull(objects);
    	
    }
}