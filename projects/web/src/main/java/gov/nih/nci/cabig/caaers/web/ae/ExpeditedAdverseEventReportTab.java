package gov.nih.nci.cabig.caaers.web.ae;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

/**
 * @author Sameer Sawant
 */
public class ExpeditedAdverseEventReportTab extends TabWithFields<ExpeditedAdverseEventReportCommand>{
	
	public ExpeditedAdverseEventReportTab(String longTitle, String shortTitle, String viewName){
		super(longTitle, shortTitle, viewName);
	}
	
	/**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final HttpServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }
    
    @Override
	public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventReportCommand cmd) {
		Map<String, InputFieldGroup> map = new HashMap<String, InputFieldGroup>();
		return map;
	}
	
}