package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ion C. Olaru
 */

public class StudyPersonnelTabTestCase extends AbstractStudyWebTestCase {

    protected StudyCommand createCommand() {
        return createMockCommand();
    }

    protected StudyTab createTab() {
        PersonnelTab tab = new PersonnelTab();
        tab.setConfigurationProperty(new ConfigProperty());
        Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();
        map.put("studyPersonnelRoleRefData", new ArrayList<Lov>());
        map.put("studyPersonnelStatusRefData", new ArrayList<Lov>());
        tab.getConfigurationProperty().setMap(map);

        return tab;
    }

    public void testReferenceData() {
        replayMocks();
        Map<String, Object> output = tab.referenceData(request, command);
        verifyMocks();
        assertNotNull(output);
    }

    public void testValidation() {
        StudyOrganization so = new StudySite();
        so.setOrganization(new Organization());
        so.getOrganization().setName("A");

        StudyPersonnel sp;
        sp = new StudyPersonnel();
        sp.setResearchStaff(new ResearchStaff());
        sp.getResearchStaff().setNciIdentifier("Id1");
        sp.getResearchStaff().setLoginId("Id1");
        so.addStudyPersonnel(sp);

        sp = new StudyPersonnel();
        sp.setResearchStaff(new ResearchStaff());
        sp.getResearchStaff().setNciIdentifier("Id2");
        sp.getResearchStaff().setLoginId("Id2");
        so.addStudyPersonnel(sp);

        sp = new StudyPersonnel();
        sp.setResearchStaff(new ResearchStaff());
        sp.getResearchStaff().setNciIdentifier("Id3");
        sp.getResearchStaff().setLoginId("Id3");
        so.addStudyPersonnel(sp);

        study.addStudyOrganization(so);

        StudyOrganization _so = command.getStudy().getStudyOrganizations().get(0);
        assertEquals(3, _so.getStudyPersonnels().size());

        replayMocks();
        tab.validate(command, errors);
        verifyMocks();

        System.out.println(errors);
        assertEquals(0, errors.getErrorCount());
    }

    public void testPostProcess() {
        StudyOrganization so = new StudySite();
        so.setOrganization(new Organization());
        so.getOrganization().setName("A");

        StudyPersonnel sp;
        sp = new StudyPersonnel();
        sp.setResearchStaff(new ResearchStaff());
        sp.getResearchStaff().setNciIdentifier("Id1");
        sp.getResearchStaff().setLoginId("Id1");
        so.addStudyPersonnel(sp);

        sp = new StudyPersonnel();
        sp.setResearchStaff(new ResearchStaff());
        sp.getResearchStaff().setNciIdentifier("Id2");
        sp.getResearchStaff().setLoginId("Id2");
        so.addStudyPersonnel(sp);

        sp = new StudyPersonnel();
        sp.setResearchStaff(new ResearchStaff());
        sp.getResearchStaff().setNciIdentifier("Id3");
        sp.getResearchStaff().setLoginId("Id3");
        so.addStudyPersonnel(sp);
        
        study.addStudyOrganization(so);

        StudyOrganization _so = command.getStudy().getStudyOrganizations().get(0);
        assertEquals(3, _so.getStudyPersonnels().size());

        request.setParameter("_action", "removeStudyPersonnel");
        request.setParameter("_selectedPersonnel", "0");
        command.setStudySiteIndex(0);

        replayMocks();
        tab.postProcess(request, command, errors);
        verifyMocks();

        assertEquals(2, _so.getStudyPersonnels().size());
    }

    public void testCreateFieldGroups() {
        StudyOrganization so = new StudySite();
        so.setOrganization(new Organization());
        so.getOrganization().setName("A");

        StudyPersonnel sp;
        sp = new StudyPersonnel();
        sp.setResearchStaff(new ResearchStaff());
        sp.getResearchStaff().setNciIdentifier("Id1");
        sp.getResearchStaff().setLoginId("Id1");
        so.addStudyPersonnel(sp);

        sp = new StudyPersonnel();
        sp.setResearchStaff(new ResearchStaff());
        sp.getResearchStaff().setNciIdentifier("Id2");
        sp.getResearchStaff().setLoginId("Id2");
        so.addStudyPersonnel(sp);

        sp = new StudyPersonnel();
        sp.setResearchStaff(new ResearchStaff());
        sp.getResearchStaff().setNciIdentifier("Id3");
        sp.getResearchStaff().setLoginId("Id3");
        so.addStudyPersonnel(sp);

        study.addStudyOrganization(so);

        replayMocks();
        Map<String, InputFieldGroup> map = tab.createFieldGroups(command);
        verifyMocks();
        assertEquals(0, errors.getErrorCount());
        assertEquals(1, map.size());
    }

    protected StudyCommand createMockCommand() {
        StudyCommand command = new StudyCommand();
        Study study = new Study();
        command.setStudy(study);

        return command;
    }
}