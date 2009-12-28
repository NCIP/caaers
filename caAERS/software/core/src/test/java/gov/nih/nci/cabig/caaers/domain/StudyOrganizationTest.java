package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;

import java.util.Date;
import java.util.HashMap;

import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class StudyOrganizationTest extends TestCase {


    private StudyOrganization studyOrganization;
    private StudyPersonnel studyPersonnel1, studyPersonnel2;
    
    @Override
    protected void setUp() throws Exception {

        studyOrganization = new StudySite();

        studyPersonnel1 = new StudyPersonnel();
        studyPersonnel1.setStartDate(new Date());
       
        studyPersonnel2 = new StudyPersonnel();
        studyPersonnel2.setStartDate(new Date());

        studyOrganization.addStudyPersonnel(studyPersonnel1);
        studyOrganization.addStudyPersonnel(studyPersonnel2);

    }

    public void testRemoveStudyPersonal() {
        studyOrganization.getStudyPersonnels().remove(0);
        assertEquals(1, studyOrganization.getStudyPersonnels().size());
    }
    
    public void testActiveStudyPersonnel(){
    	
    	StudyPersonnel sp1 = new StudyPersonnel();
    	sp1.setStartDate(new Date());
    	StudyPersonnel sp2 = new StudyPersonnel();
    	sp2.retire();
    	
    	studyOrganization.addStudyPersonnel(sp1);
    	studyOrganization.addStudyPersonnel(sp2);
    	
    	assertEquals(3, studyOrganization.getActiveStudyPersonnel().size());
    	System.out.println(studyOrganization.getActiveStudyPersonnel());
    	assertSame(sp1, studyOrganization.getActiveStudyPersonnel().get(2));
    	
    	sp1.retire();
    	studyPersonnel1.retire();
    	studyPersonnel2.retire();
    	
    	assertTrue(studyOrganization.getActiveStudyPersonnel().isEmpty());
    	
    }
    
    public void testActiveStudyInvestigators(){

    	StudyInvestigator si1 = new StudyInvestigator();
    	si1.setStartDate(new Date());
    	StudyInvestigator si2 = new StudyInvestigator();
    	si2.retire();
    	
    	studyOrganization.addStudyInvestigators(si1);
    	studyOrganization.addStudyInvestigators(si2);
    	
    	assertEquals(1, studyOrganization.getActiveStudyInvestigators().size());
    	assertSame(si1, studyOrganization.getActiveStudyInvestigators().get(0));
    	
    	si1.retire();
    	
    	assertTrue(studyOrganization.getActiveStudyInvestigators().isEmpty());
    	
    }
    
    public void testIsActive(){
    	assertFalse(studyOrganization.isActive());
    	studyOrganization.activate();
    	assertTrue(studyOrganization.isActive());
    	studyOrganization.deactivate();
    	assertFalse(studyOrganization.isActive());
    }

    public void testIsInactive(){
    	studyOrganization.activate();
    	assertFalse(studyOrganization.isInActive());
    	studyOrganization.activate();
    	assertFalse(studyOrganization.isInActive());
    	studyOrganization.deactivate();
    	assertTrue(studyOrganization.isInActive());
    }

    public void testUniqueStudyOrganizations() {
        Study study = new LocalStudy();
        Organization org1 = new LocalOrganization(); org1.setNciInstituteCode("CODE_01");
        Organization org2 = new LocalOrganization(); org2.setNciInstituteCode("CODE_02");
        Organization org3 = new LocalOrganization(); org3.setNciInstituteCode("CODE_01");

        StudySite ss1 = new StudySite(); ss1.setStudy(study); ss1.setOrganization(org1); study.addStudyOrganization(ss1);
        StudySite ss2 = new StudySite(); ss2.setStudy(study); ss2.setOrganization(org2); study.addStudyOrganization(ss2);
        StudySite ss3 = new StudySite(); ss3.setStudy(study); ss3.setOrganization(org3); study.addStudyOrganization(ss3);

        assertEquals(3, study.getStudyOrganizations().size());
        assertEquals(2, study.getUniqueStudyOrganizations().size());
    }
}
