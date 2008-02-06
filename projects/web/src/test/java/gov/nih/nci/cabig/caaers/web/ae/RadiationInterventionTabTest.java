package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
/**
 * @author Krikor Krumlian
 */
@CaaersUseCases({ CREATE_EXPEDITED_REPORT })
public class RadiationInterventionTabTest extends AeTabTestCase {
    @Override
    protected RadiationInterventionTab createTab() {
    	ConfigProperty configProperty = new ConfigProperty();
    	Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();
    	map.put("radiationDoseUMORefData", new ArrayList<Lov>());
    	map.put("radiationAdjustmentRefData", new ArrayList<Lov>());
    	configProperty.setMap(map);
    	RadiationInterventionTab tab = new RadiationInterventionTab();
    	tab.setConfigurationProperty(configProperty);
    	return tab;
    }
    
    public void testFieldProperties() throws Exception {
        assertFieldProperties(
            "radiationIntervention6",
            "aeReport.radiationInterventions[6].administration",
            "aeReport.radiationInterventions[6].dosage",
            "aeReport.radiationInterventions[6].dosageUnit",
            "aeReport.radiationInterventions[6].lastTreatmentDate",
            "aeReport.radiationInterventions[6].fractionNumber",
            "aeReport.radiationInterventions[6].daysElapsed",
            "aeReport.radiationInterventions[6].adjustment"
        );
    }
}