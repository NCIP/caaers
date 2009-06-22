package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.LabTermDao;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.LabTerm;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class LabsTabTest extends AeTabTestCase {
    @Override
    protected LabsTab createTab() {
        ConfigProperty configProperty = new ConfigProperty();
        Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();
        map.put("labUnitsRefData", new ArrayList<Lov>());
        configProperty.setMap(map);
        LabsTab tab = new LabsTab();
        tab.setConfigurationProperty(configProperty);
        tab.setLabTermDao(new LabTermDao() {

            @Override
            public List<LabTerm> getAll() {
                return new ArrayList<LabTerm>();
            }

        });
        return tab;
    }

    @Override
    protected void fillInUsedProperties(ExpeditedAdverseEventInputCommand cmd) {
        cmd.getAeReport().addLab(new Lab());
    }

    public void testGroupDisplayNames() throws Exception {
        assertDisplayNameForFieldGroup("Lab A", "lab0");
        assertDisplayNameForFieldGroup("Lab G", "lab6");
    }

    public void testFieldProperties() throws Exception {
        assertFieldProperties("lab3", "aeReport.labs[3].labTerm", "aeReport.labs[3].other",
                        "aeReport.labs[3].units", "aeReport.labs[3].baseline.value",
                        "aeReport.labs[3].baseline.date", "aeReport.labs[3].nadir.value",
                        "aeReport.labs[3].nadir.date", "aeReport.labs[3].recovery.value",
                        "aeReport.labs[3].recovery.date", "aeReport.labs[3].site",
                        "aeReport.labs[3].labDate", "aeReport.labs[3].infectiousAgent");
    }
    // TODO: This test is no longer valid.
    // public void testEitherTestNameOrOtherRequired() throws Exception {
    // command.getAeReport().getLabs().get(0).setName(null);
    // command.getAeReport().getLabs().get(0).setOther(null);
    // doValidate();
    // assertEquals(2, getErrors().getErrorCount()); //one for missing name(or other) and one for
    // missing units
    // ObjectError fieldError = getErrors().getFieldError("aeReport.labs[0]");
    // assertNotNull(fieldError);
    // assertEquals("Wrong code", "REQUIRED", fieldError.getCode());
    // assertEquals("Wrong message", "Either a known test name or other is required",
    // fieldError.getDefaultMessage());
    // }
}
