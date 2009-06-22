package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
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

public class StudyIdentifiersTabTestCase extends AbstractStudyWebTestCase {

   

    protected StudyTab createTab() {
        IdentifiersTab tab = new IdentifiersTab();
        tab.setConfigurationProperty(new ConfigProperty());
        Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();
        map.put("identifiersType", new ArrayList<Lov>());
        tab.getConfigurationProperty().setMap(map);

        return tab;
    }

    public void testReferenceData() {
        replayMocks();
        Map<String, Object> output = tab.referenceData(request, command);
        verifyMocks();
        assertNotNull(output);
        assertNotNull(output.get("identifiersTypeRefData"));
    }

    public void testValidation() {
        OrganizationAssignedIdentifier o = new OrganizationAssignedIdentifier();
        o.setType("T1");
        o.setValue("V1");
        o.setOrganization(new LocalOrganization());
        o.getOrganization().setName("O-1");
        study.addIdentifier(o);

        o = new OrganizationAssignedIdentifier();
        o.setType("T2");
        o.setValue("V2");
        o.setOrganization(new LocalOrganization());
        o.getOrganization().setName("O-2");
        study.addIdentifier(o);

        replayMocks();
        tab.validate(command, errors);
        verifyMocks();

        assertEquals(0, errors.getErrorCount());
    }

    public void testValidationDuplicates() {
        OrganizationAssignedIdentifier o = new OrganizationAssignedIdentifier();
        o.setType("T1");
        o.setValue("V1");
        o.setOrganization(new LocalOrganization());
        o.getOrganization().setName("O-1");
        study.addIdentifier(o);

        o = new OrganizationAssignedIdentifier();
        o.setType("T1");
        o.setValue("V1");
        o.setOrganization(new LocalOrganization());
        o.getOrganization().setName("O-2");
        study.addIdentifier(o);

        replayMocks();
        tab.validate(command, errors);
        verifyMocks();

        assertEquals(1, errors.getErrorCount());
    }

    public void testPostProcess() {
        OrganizationAssignedIdentifier o = new OrganizationAssignedIdentifier();
        o.setType("T1");
        o.setValue("V1");
        o.setOrganization(new LocalOrganization());
        o.getOrganization().setName("O-1");
        study.addIdentifier(o);

        o = new OrganizationAssignedIdentifier();
        o.setType("T2");
        o.setValue("V2");
        o.setOrganization(new LocalOrganization());
        o.getOrganization().setName("O-2");
        study.addIdentifier(o);

        request.setParameter("_action", "removeIdentifier");
        request.setParameter("_selected", "0");

        assertEquals(2, study.getIdentifiers().size());

        replayMocks();
        tab.postProcess(request, command, errors);
        verifyMocks();

        assertEquals(1, study.getIdentifiers().size());
    }

    public void testCreateFieldGroups() {
        OrganizationAssignedIdentifier o = new OrganizationAssignedIdentifier();
        o.setType("T1");
        o.setValue("V1");
        o.setOrganization(new LocalOrganization());
        o.getOrganization().setName("O-1");
        study.addIdentifier(o);

        o = new OrganizationAssignedIdentifier();
        o.setType("T2");
        o.setValue("V2");
        o.setOrganization(new LocalOrganization());
        o.getOrganization().setName("O-2");
        study.addIdentifier(o);

        replayMocks();
        Map<String, InputFieldGroup> map = tab.createFieldGroups(command);
        verifyMocks();

        assertEquals(0, errors.getErrorCount());
        assertNotNull(map);
        assertEquals(2, map.size());

        InputFieldGroup main0 = map.get("main0");
        assertNotNull(main0);

        InputFieldGroup main1 = map.get("main1");
        assertNotNull(main1);

        assertEquals(5, main0.getFields().size());
        assertEquals(5, main1.getFields().size());

    }

   
}