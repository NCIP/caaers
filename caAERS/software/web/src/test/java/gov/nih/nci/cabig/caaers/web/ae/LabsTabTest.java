package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.LabTermDao;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.LabTerm;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.classextension.EasyMock;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
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
        
        EasyMock.expect(evaluationService.validateReportingBusinessRules(command.getAeReport(), tab.section())).andReturn(new ValidationErrors()).anyTimes();
        
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
    
    public void testValidate_NoErrors(){
    	doValidate();
    	assertFalse(getErrors().hasErrors());
    }
    
    public void testValidate_WrongBaseLineAndTerm(){
    	Lab l = command.getAeReport().getLabs().get(0);
    	l.getBaseline().setDate(new Date());
    	l.getBaseline().setValue("-99.99");
    	doValidate();
    	assertFieldError("aeReport.labs[0].baseline.value", "REQUIRED", "<b>Invalid sign:</b> &quot;Baseline value&quot;");
    	assertFieldError("aeReport.labs[0].labTerm", "SAE_029", "<b>Missing:</b> &quot;Lab Name&quot;");
    }
    
    public void testValidate_LongBaseLine(){
    	Lab l = command.getAeReport().getLabs().get(0);
    	l.getBaseline().setDate(new Date());
    	l.getBaseline().setValue("94444444444444.99");
    	doValidate();
    	assertFieldError("aeReport.labs[0].baseline.value", "REQUIRED", "<b>Invalid:</b> &quot;Baseline value&quot;");
    }
    
    public void testValidate_ValidBaseLineButRecoveryAndWorstGotHit(){
    	Lab l = command.getAeReport().getLabs().get(0);
    	l.getBaseline().setDate(new Date());
    	l.getBaseline().setValue("9.99");
    	l.getRecovery().setDate(new Date());
    	l.getRecovery().setValue("9999999999999999999");
    	l.getNadir().setDate(new Date());
    	l.getNadir().setValue("99.0009999999999999999");
    	doValidate();
    	assertFieldError("aeReport.labs[0].recovery.value", "REQUIRED", "<b>Invalid:</b> &quot;Recovery value&quot;");
    	assertFieldError("aeReport.labs[0].nadir.value", "REQUIRED", "<b>Invalid:</b> &quot;Worst value&quot;");
    }
}
