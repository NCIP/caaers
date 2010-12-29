package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.UserAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import gov.nih.nci.cabig.caaers.utils.ranking.RankBasedSorterUtils;
import gov.nih.nci.cabig.caaers.utils.ranking.Serializer;
import gov.nih.nci.cabig.caaers.web.AbstractAjaxFacade;
import gov.nih.nci.security.authorization.domainobjects.User;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Monish
 *
 */
public class UserAjaxFacade extends AbstractAjaxFacade {

	private static Class<?>[] CONTROLLERS = {};
	private UserRepository userRepository; 
	private OrganizationRepository organizationRepository;
	private StudyDao studyDao;
	
	@Override
	public Class<?>[] controllers() {
		return CONTROLLERS;
	}
	
	public UserAjaxFacade(){
		
	}
	
	/**
	 * This method is invoked from user.jsp to populate the Organization AutoCompleter.
	 * @param text
	 * @return
	 */
    public List<Organization> restrictOrganization(String text) {
        List<Organization> orgs = organizationRepository.restrictBySubnames(new String[] { text });
        orgs = RankBasedSorterUtils.sort(orgs , text, new Serializer<Organization>(){
            public String serialize(Organization object) {
                return object.getFullName();
            }
        });
        return ObjectTools.reduceAll(orgs, "id", "name", "nciInstituteCode","externalId");
    }
	
	
    /**
     * This method is invoked from the user_search.jsp to fetch users for the given search criteria  
     * @param firstName
     * @param lastName
     * @param userName
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<UserAjaxableDomainObject> getUserTable(String firstName, String lastName, String userName, HttpServletRequest request) {
    	
    	List<User> csmUserList = userRepository.searchCsmUser(firstName, lastName, userName);
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
    
    
    /**
     * This method is invoked from user.jsp to populate the Study AutoCompleter.
     * @param text
     * @param organizationCodes
     * @return
     */
    public List<StudyAjaxableDomainObject> matchStudies(String text,String[] organizationCodes) {
    	 	
    	 StudyQuery studyQuery = new StudyQuery();
    	 studyQuery.filterStudiesWithMatchingText(text);
    	 if (organizationCodes != null && organizationCodes.length > 0) {
    		 studyQuery.filterStudiesByOrganizations(organizationCodes);
    	 }
    	 List<Study> dbStudies = studyDao.find(studyQuery);
    	 List<StudyAjaxableDomainObject> studies = new ArrayList<StudyAjaxableDomainObject>();
    	 StudyAjaxableDomainObject studyAjaxableDomainObject = null;
    	 for(Study study : dbStudies){
    		 studyAjaxableDomainObject = new StudyAjaxableDomainObject();
    		 studyAjaxableDomainObject.setShortTitle(study.getShortTitle());
    		 studyAjaxableDomainObject.setPrimaryIdentifierValue(study.getIdentifierContaining(text));
    		 studyAjaxableDomainObject.setCcIdentifierValue(study.getCoordinatingCenterIdentifierValue());
    		 studies.add(studyAjaxableDomainObject);
    	 }
         studies = RankBasedSorterUtils.sort(studies , text, new Serializer<StudyAjaxableDomainObject>(){
             public String serialize(StudyAjaxableDomainObject object) {
                 return object.getDisplayName();
             }
         });
         return studies;
     }
    
	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public OrganizationRepository getOrganizationRepository() {
		return organizationRepository;
	}

	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}

	public StudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}
}