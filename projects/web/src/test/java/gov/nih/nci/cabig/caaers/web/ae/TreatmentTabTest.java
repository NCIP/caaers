package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ion C. Olaru
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class TreatmentTabTest extends AeTabTestCase {
    @Override
    protected TreatmentTab createTab() {
        ConfigProperty configProperty = new ConfigProperty();
        Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();
        map.put("agentDoseUMORefData", new ArrayList<Lov>());
        configProperty.setMap(map);
        TreatmentTab tab = new TreatmentTab();
        tab.setConfigurationProperty(configProperty);
        return tab;
    }

    @Override
    protected void fillInUsedProperties(ExpeditedAdverseEventInputCommand cmd) {
        cmd.getAeReport().getTreatmentInformation().addCourseAgent(new CourseAgent());
    }

    public void testTreatmentInfoFields() throws Exception {
        assertFieldProperties("treatmentInfo", "aeReport.treatmentInformation.treatmentAssignment",
                        "aeReport.treatmentInformation.treatmentAssignmentDescription",
                        "aeReport.treatmentInformation.treatmentDescription",
                        "aeReport.treatmentInformation.firstCourseDate",
                        "aeReport.treatmentInformation.adverseEventCourse.date",
                        "aeReport.treatmentInformation.adverseEventCourse.number",
                        "aeReport.treatmentInformation.totalCourses",
                        "aeReport.treatmentInformation.investigationalAgentAdministered");
    }
}
