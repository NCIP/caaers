package gov.nih.nci.cabig.caaers.web.ae;

import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Kulasekaran
 */
public class MedicalInfoTab extends AeTab {	
	
    public MedicalInfoTab() {
        super("Medical info", "Medical", "ae/medical");
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map<String, InputFieldGroup> createFieldGroups(AdverseEventInputCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        return map;
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();        
        
        return refdata;
    }
        
    @Override
    public boolean isAllowDirtyForward() {
        return false;
    }

    @Override
    protected void validate(
        AdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
    	
    }        
    
}
