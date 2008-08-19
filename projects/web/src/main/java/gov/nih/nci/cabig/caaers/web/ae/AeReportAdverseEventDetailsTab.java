package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Sameer Sawant
 */
public class AeReportAdverseEventDetailsTab extends ExpeditedAdverseEventReportTab{
	
	public AeReportAdverseEventDetailsTab() {
		super("AE Details", "AE Details", "ae/adverseEventsDetails");
		addHelpKeyExclusion("ctcVersion");
	}
	
	@Override
	public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventReportCommand cmd) {
		InputFieldGroupMap map = new InputFieldGroupMap();
		
		return map;
	}
	
	@Override
    public Map<String, Object> referenceData(HttpServletRequest request, ExpeditedAdverseEventReportCommand command) {
	
		return super.referenceData(request, command);
	}
	
	@Override
	public void onBind(HttpServletRequest request, ExpeditedAdverseEventReportCommand command, Errors errors) {
		
	}
	
	@Override
    protected void validate(ExpeditedAdverseEventReportCommand command, BeanWrapper commandBean,
                    Map<String, InputFieldGroup> fieldGroups, Errors errors) {
		
	}
	
		
}