package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.ajax.UserAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.web.AbstractAjaxFacade;
import gov.nih.nci.security.authorization.domainobjects.User;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class SearchUserAjaxFacade extends AbstractAjaxFacade {

	private static Class<?>[] CONTROLLERS = {};
	private CSMUserRepository csmUserRepository; 
	
	@Override
	public Class<?>[] controllers() {
		return CONTROLLERS;
	}
	
	public SearchUserAjaxFacade(){
		
	}
	
    @SuppressWarnings("unchecked")
	public List<UserAjaxableDomainObject> getUserTable(String firstName, String lastName, String userName, HttpServletRequest request) {
    	
    	List<User> csmUserList = csmUserRepository.searchCsmUser(firstName, lastName, userName);
		List<UserAjaxableDomainObject> ajaxableUserList = new ArrayList<UserAjaxableDomainObject>();
		UserAjaxableDomainObject ajaxableUser = null;
		for(User csmUser : csmUserList){
			ajaxableUser = new UserAjaxableDomainObject();
			ajaxableUser.setFirstName(csmUser.getFirstName());
			ajaxableUser.setLastName(csmUser.getLastName());
			ajaxableUser.setUserName(csmUser.getLoginName());
			ajaxableUser.setEmailAddress(csmUser.getEmailId());
			ajaxableUserList.add(ajaxableUser);
		}
		return ajaxableUserList;
	}

	public CSMUserRepository getCsmUserRepository() {
		return csmUserRepository;
	}

	public void setCsmUserRepository(CSMUserRepository csmUserRepository) {
		this.csmUserRepository = csmUserRepository;
	}
}