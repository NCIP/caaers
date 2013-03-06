/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.nih.nci.cabig.caaers.utils.ObjectPrivilegeParser;
import org.acegisecurity.Authentication;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * This class redirects the Task to the first url in the list provided based on whether the logged in person is authorized to access the page. 
 * @author Sameer Sawant
 */
public class TaskResolverController extends AbstractController{
	protected CaaersSecurityFacade caaersSecurityFacade;
	protected Map<String, String> objectPrivilegeMap;
	protected List<String> urls;

    public CaaersSecurityFacade getCaaersSecurityFacade() {
		return caaersSecurityFacade;
	}

	public void setCaaersSecurityFacade(CaaersSecurityFacade caaersSecurityFacade) {
		this.caaersSecurityFacade = caaersSecurityFacade;
	}

	public void setObjectPrivilegeMap(Map<String, String> objectPrivilegeMap) {
        this.objectPrivilegeMap = objectPrivilegeMap;
    }
    
	protected ModelAndView handleRequestInternal(HttpServletRequest request, 
			HttpServletResponse response){
		Authentication authentication = SecurityUtils.getAuthentication();
		for(String url: urls){
			String objectPrivilege = objectPrivilegeMap.get(url);
	        if (objectPrivilege == null) return null;
	        ObjectPrivilegeParser p = new ObjectPrivilegeParser(objectPrivilege);
	        if(caaersSecurityFacade.checkAuthorization(authentication, p.getDomainObjectPrivileges()[0][0], p.getDomainObjectPrivileges()[0][1]))
	        	return new ModelAndView("redirect:" + url);
		}
		return null;
	}
	
	public List<String> getUrls() {
		return urls;
	}
	
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
}
