package gov.nih.nci.cabig.caaers.domain;

import edu.nwu.bioinformatics.commons.testing.CoreTestCase;

/**
 * @author Biju Joseph
 */
public class StudyOrganizationTest extends CoreTestCase {


    private StudyOrganization studyOrganization;
    private StudyPersonnel studyPersonnel1, studyPersonnel2;

    private ResearchStaff researchStaff;

    @Override
    protected void setUp() throws Exception {

        studyOrganization = new StudySite();
        researchStaff = new ResearchStaff();

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
}
