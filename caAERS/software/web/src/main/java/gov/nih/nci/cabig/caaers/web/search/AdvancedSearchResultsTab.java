package gov.nih.nci.cabig.caaers.web.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.WorkFlowTab;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;

/**
 * This is the advanced search results tab.
 * @author Sameer Sawant
 */

public class AdvancedSearchResultsTab<T extends AdvancedSearchCommand> extends WorkFlowTab<T> {
	
	
	public AdvancedSearchResultsTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }
	
	public void onBind(HttpServletRequest request, T command, Errors errors) {
        super.onBind(request, command, errors);
    }

	@Override
    public Map<String, Object> referenceData(HttpServletRequest request, AdvancedSearchCommand command) {
		Map refdata = new HashMap<String, Object>();
		String action = (String) findInRequest(request, "_action");
		if(action != null && action.equals("nestedView")){
			// Find all the dependent objects in the tab and put it in the list nestedDependentObjects.
			List<DependentObject> nestedDependentObjects = new ArrayList<DependentObject>();
			for(DependentObject dObject: command.getSearchTargetObject().getDependentObject()){
				if(dObject.isInView() && !dObject.getClassName().equals(command.getSearchTargetObject().getClassName()))
					nestedDependentObjects.add(dObject);
			}
			refdata.put("nestedDependentObjects", nestedDependentObjects);
		}
		return refdata;
	}
	
	/**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final ServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }
}