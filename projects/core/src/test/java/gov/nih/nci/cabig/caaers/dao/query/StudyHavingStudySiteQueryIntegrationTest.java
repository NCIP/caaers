package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.ctms.audit.DataAuditInfo;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Biju Joseph
 */
public class StudyHavingStudySiteQueryIntegrationTest extends
        CaaersDbNoSecurityTestCase {

    private static Logger log = Logger.getLogger(StudyHavingStudySiteQueryIntegrationTest.class);

    private StudyDao studyDao;

    private Study study, anotherStudy;

    private OrganizationDao organizationDao;

    private Organization organization;
    private JdbcTemplate jdbcTemplate;

    @Required
    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @Required
    public void setStudyDao(final StudyDao studyDao) {
        this.studyDao = studyDao;
    }
    
    public void testMethod(){
    	
    }
    
    //REVISIT

//    public void testFilterStudiesbyShortTitle() throws Exception {
//        StudyHavingStudySiteQuery query = new StudyHavingStudySiteQuery();
//        query.filterByStudyShortTile("a");
//        List<Study> studies = studyDao.find(query);
//
//        int count = jdbcTemplate
//                .queryForInt("select count(study.id) from study_organizations studysite inner join studies study on studysite.study_id=study.id where studysite.type='SST' "
//                        + "and (lower(study.short_title) like '%a')");
//        assertEquals(count, studies.size());
//        assertTrue(studies.size() >= 1);
//        for (Study study : studies) {
//            assertTrue(study.getShortTitle().indexOf("a") >= 0);
//
//        }
//
//    }
//
//    public void testFilterStudiesbyStudySiteName() throws Exception {
//        StudyHavingStudySiteQuery query = new StudyHavingStudySiteQuery();
//        query.filterByStudySiteName("or");
//        List<Study> studies = studyDao.find(query);
//
//        int count = jdbcTemplate
//                .queryForInt("select count(study1_.id) from study_organizations studysite0_ inner join studies study1_ on studysite0_.study_id=study1_.id,"
//                        + " organizations organizati2_ where studysite0_.type='SST' and studysite0_.site_id=organizati2_.id and (lower(organizati2_.name) like '%or%')");
//        assertEquals(count, studies.size());
//        assertTrue(studies.size() >= 2);
//        for (Study study : studies) {
//            assertTrue(study.getStudySites().get(0).getOrganization().getName().indexOf("org") >= 0);
//
//        }
//
//    }
//
//    public void testFilterStudiesbyIdentifierValue() throws Exception {
//
//        StudyHavingStudySiteQuery query = new StudyHavingStudySiteQuery();
//        query.filterByIdentifierValue("val");
//        List<Study> studies = studyDao.find(query);
//
//        for (Study study : studies) {
//            List<Identifier> identifiers = study.getIdentifiers();
//            for (Identifier identifier : identifiers) {
//                assertTrue(identifier.getValue().indexOf("val") >= 0);
//            }
//
//        }
//
//    }

//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();    //To change body of overridden methods use File | Settings | File Templates.
//        DataAuditInfo.setLocal(new gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo("admin",
//                "localhost", new Date(), "/pages/task"));
//        studyDao = (StudyDao)getApplicationContext().getBean("studyDao");
//        jdbcTemplate = (JdbcTemplate) getApplicationContext().getBean("jdbcTemplate");
//        organizationDao = (OrganizationDao) getApplicationContext().getBean("organizationDao");
//
//        study = createStudy("a");
//
//        studyDao.save(study);
//        anotherStudy = createStudy("b");
//        studyDao.save(anotherStudy);
//
//    }
//
//    @Override
//    protected void tearDown() throws Exception {
//        super.tearDown();    //To change body of overridden methods use File | Settings | File Templates.
//        DataAuditInfo.setLocal(null);
//
//    }
//
//
//    private Study createStudy(final String name) {
//        study = new Study();
//        study.setLongTitle("long title" + name);
//        study.setShortTitle("short title" + name);
//
//        AeTerminology aeTerminology = new AeTerminology();
//        aeTerminology.setTerm(Term.CTC);
//        aeTerminology.setStudy(study);
//        study.setAeTerminology(aeTerminology);
//        study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
//        // newStudy.setAmended(false);
//        // study.setProtocolAuthorityId("protocolAuthority for:" + name);
//
//        // now retrieve the site
//        StudySite studySite1 = new StudySite();
//        studySite1.setStudy(study);
//        organization = Fixtures.createOrganization("org");
//        organizationDao.save(organization);
//        studySite1.setOrganization(organization);
//        study.addStudySite(studySite1);
//        study.setAdeersReporting(Boolean.TRUE);
//
//        SystemAssignedIdentifier systemAssignedIdentifier = Fixtures
//                .createSystemAssignedIdentifier("val1");
//        OrganizationAssignedIdentifier organizationAssignedIdentifier = Fixtures
//                .createOrganizationAssignedIdentifier("val2", organization);
//        study.addIdentifier(organizationAssignedIdentifier);
//        study.addIdentifier(systemAssignedIdentifier);
//        return study;
//
//    }
//
//    private Study createStudy(final String name, final List<Organization> organizations) {
//        Study study = new Study();
//
//        study.setLongTitle("long title" + name);
//        study.setShortTitle("short title" + name);
//
//        AeTerminology aeTerminology = new AeTerminology();
//        aeTerminology.setTerm(Term.CTC);
//        aeTerminology.setStudy(study);
//        study.setAeTerminology(aeTerminology);
//        study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
//        // newStudy.setAmended(false);
//        // study.setProtocolAuthorityId("protocolAuthority for:" + name);
//
//        // now retrieve the site
//        for (Organization organization : organizations) {
//            StudySite studySite1 = new StudySite();
//            studySite1.setStudy(study);
//            studySite1.setOrganization(organization);
//            study.addStudySite(studySite1);
//        }
//        return study;
//
//    }


}
