package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class StudyOrganizationTest extends TestCase {


    private StudyOrganization studyOrganization;
    private StudyPersonnel studyPersonnel1, studyPersonnel2;

    private ResearchStaff researchStaff;

    @Override
    protected void setUp() throws Exception {

        studyOrganization = new StudySite();
        researchStaff = new LocalResearchStaff();

        studyPersonnel1 = new StudyPersonnel();
        studyPersonnel1.setRoleCode("role code");
        studyPersonnel1.setResearchStaff(researchStaff);
        studyPersonnel1.setStatusCode("status code");
        studyPersonnel2 = new StudyPersonnel();
        studyPersonnel2.setRoleCode("another role code");
        studyPersonnel1.setResearchStaff(researchStaff);
        studyPersonnel2.setStatusCode("another status code");

        studyOrganization.addStudyPersonnel(studyPersonnel1);
        studyOrganization.addStudyPersonnel(studyPersonnel2);
        researchStaff.addStudyPersonnel(studyPersonnel1);
        researchStaff.addStudyPersonnel(studyPersonnel2);

    }

    public void testRemoveStudyPersonal() {
        assertEquals(studyOrganization.getStudyPersonnels().get(0), researchStaff.getStudyPersonnels().get(0));
        studyOrganization.getStudyPersonnels().remove(0);
        assertEquals(1, studyOrganization.getStudyPersonnels().size());
       // assertEquals(1, researchStaff.getStudyPersonnels().size());

    }
    
    public void testActiveStudyPersonnel(){
    	
    	StudyPersonnel sp1 = new StudyPersonnel();
    	StudyPersonnel sp2 = new StudyPersonnel();
    	sp2.retire();
    	
    	studyOrganization.addStudyPersonnel(sp1);
    	studyOrganization.addStudyPersonnel(sp2);
    	
    	assertEquals(3, studyOrganization.getActiveStudyPersonnel().size());
    	assertSame(sp1, studyOrganization.getActiveStudyPersonnel().get(2));
    	
    	sp1.retire();
    	studyPersonnel1.retire();
    	studyPersonnel2.retire();
    	
    	assertTrue(studyOrganization.getActiveStudyPersonnel().isEmpty());
    	
    }
    
    public void testActiveStudyInvestigators(){

    	StudyInvestigator si1 = new StudyInvestigator();
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
    	studyOrganization.setStatus("Active");
    	assertTrue(studyOrganization.isActive());
    	studyOrganization.setStatus("Inactive");
    	assertFalse(studyOrganization.isActive());
    }
    public void testIsInactive(){
    	assertFalse(studyOrganization.isInactive());
    	studyOrganization.setStatus("Active");
    	assertFalse(studyOrganization.isInactive());
    	studyOrganization.setStatus("Inactive");
    	assertTrue(studyOrganization.isInactive());
    }
}
