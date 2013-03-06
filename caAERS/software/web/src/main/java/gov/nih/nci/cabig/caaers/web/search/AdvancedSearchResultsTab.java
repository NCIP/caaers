/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
    public Map<String, Object> referenceData(HttpServletRequest request, T command) {
		Map refdata = new HashMap<String, Object>();
		String action = (String) findInRequest(request, "_action");
		
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
