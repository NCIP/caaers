package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ion C. Olaru
 */

public class StudyInvestigatorsTabTestCase extends AbstractStudyWebTestCase {

    protected StudyTab createTab() {
        InvestigatorsTab tab = new InvestigatorsTab();
        tab.setConfigurationProperty(new ConfigProperty());
        Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();
        map.put("invRoleCodeRefData", new ArrayList<Lov>());
        tab.getConfigurationProperty().setMap(map);

        return tab;
    }

    public void testReferenceData() {
        replayMocks();
        Map<String, Object> output = tab.referenceData(request, command);
        verifyMocks();

        assertNotNull(output);
        assertNotNull(output.get("invRoleCodeRefData"));
    }

    public void testValidation() {
        StudyOrganization so = new StudySite();
        so.setOrganization(new LocalOrganization());
        so.getOrganization().setName("A");
        so.addStudyInvestigators(new StudyInvestigator());
        study.addStudyOrganization(so);

        so = new StudySite();
        so.setOrganization(new LocalOrganization());
        so.getOrganization().setName("B");
        so.addStudyInvestigators(new StudyInvestigator());
        study.addStudyOrganization(so);

        replayMocks();
        tab.validate(command, errors);
        verifyMocks();

        assertEquals(0, errors.getErrorCount());
    }

    public void testPostProcess() {
        StudyOrganization so = new StudySite();
        so.setOrganization(new LocalOrganization());
        so.getOrganization().setName("A");
        so.addStudyInvestigators(new StudyInvestigator());
        so.addStudyInvestigators(new StudyInvestigator());
        so.addStudyInvestigators(new StudyInvestigator());
        study.addStudyOrganization(so);

        StudyOrganization _so = command.getStudy().getStudyOrganizations().get(0);
        assertEquals(3, _so.getStudyInvestigators().size());

        request.setParameter("_action", "removeInv");
        request.setParameter("_selectedInvestigator", "0");
        command.setStudySiteIndex(0);
        
        replayMocks();
        tab.postProcess(request, command, errors);
        verifyMocks();

        assertEquals(2, _so.getStudyInvestigators().size());
    }

    public void testCreateFieldGroups() {
        StudyOrganization so = new StudySite();
        so.setOrganization(new LocalOrganization());
        so.getOrganization().setName("A");
        so.addStudyInvestigators(new StudyInvestigator());
        study.addStudyOrganization(so);

        so = new StudySite();
        so.setOrganization(new LocalOrganization());
        so.getOrganization().setName("B");
        so.addStudyInvestigators(new StudyInvestigator());
        study.addStudyOrganization(so);

        replayMocks();
        Map<String, InputFieldGroup> map = tab.createFieldGroups(command);
        verifyMocks();
        assertEquals(0, errors.getErrorCount());
        assertEquals(1, map.size());
    }

   
}