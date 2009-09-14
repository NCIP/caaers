package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;

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

public class StudySitesTabTestCase extends AbstractStudyWebTestCase {



    protected StudyTab createTab() {
        SitesTab tab = new SitesTab();
        tab.setConfigurationProperty(new ConfigProperty());

        Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();
        tab.getConfigurationProperty().setMap(map);

        return tab;
    }

    public void testValidation() {
        StudySite ss = new StudySite();
        ss.setId(71);
        ss.setOrganization(new LocalOrganization());
        ss.getOrganization().setId(2);
        ss.getOrganization().setName("A");
        study.addStudySite(ss);

        ss = new StudySite();
        ss.setId(72);
        ss.setOrganization(new LocalOrganization());
        ss.getOrganization().setId(1);
        ss.getOrganization().setName("B");
        study.addStudySite(ss);

        replayMocks();
        tab.validate(command, errors);
        verifyMocks();

        assertEquals(0, errors.getErrorCount());
    }

    public void testPostProcess() {
        StudySite ss = new StudySite();
        ss.setId(71);
        ss.setOrganization(new LocalOrganization());
        ss.getOrganization().setId(2);
        ss.getOrganization().setName("A");
        study.addStudySite(ss);

        ss = new StudySite();
        ss.setId(72);
        ss.setOrganization(new LocalOrganization());
        ss.getOrganization().setId(1);
        ss.getOrganization().setName("B");
        study.addStudySite(ss);

        assertEquals(2, study.getStudySites().size());

        request.setParameter("_action", "removeSite");
        request.setParameter("_selected", "0");

        replayMocks();
        tab.postProcess(request, command, errors);
        verifyMocks();

        assertEquals(1, study.getStudySites().size());
        assertEquals("B", study.getStudySites().get(0).getOrganization().getName());
        assertEquals(0, errors.getErrorCount());
    }

    public void testCreateFieldGroups() {

        StudySite ss = new StudySite();
        ss.setId(71);
        study.addStudySite(ss); 

        ss = new StudySite();
        ss.setId(72);
        study.addStudySite(ss);

        replayMocks();
        Map<String, InputFieldGroup> map = tab.createFieldGroups(command);
        verifyMocks();
        assertEquals(0, errors.getErrorCount());
        assertEquals(2, map.size());

        RepeatingFieldGroupFactory.RepeatingFieldGroup main0 = (RepeatingFieldGroupFactory.RepeatingFieldGroup)map.get("main0");
        RepeatingFieldGroupFactory.RepeatingFieldGroup main1 = (RepeatingFieldGroupFactory.RepeatingFieldGroup)map.get("main1");

        assertEquals(1, main0.getFields().size());
    }

   
}