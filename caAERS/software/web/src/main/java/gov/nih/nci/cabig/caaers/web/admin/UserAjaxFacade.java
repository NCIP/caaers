package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.dao.query.SiteResearchStaffQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.UserAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.InvestigatorRepository;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import gov.nih.nci.cabig.caaers.utils.ranking.RankBasedSorterUtils;
import gov.nih.nci.cabig.caaers.utils.ranking.Serializer;
import gov.nih.nci.cabig.caaers.web.AbstractAjaxFacade;
import gov.nih.nci.security.authorization.domainobjects.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	private InvestigatorRepository investigatorRepository;
	private ResearchStaffRepository researchStaffRepository;
	private static final Log log = LogFactory.getLog(UserAjaxFacade.class);
	
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
    
    @SuppressWarnings("unchecked")
	public List<UserAjaxableDomainObject> getResults(HashMap searchCriteriaMap) {

    	List<UserAjaxableDomainObject> searchResults = new ArrayList<UserAjaxableDomainObject>();
    	
    	String firstName = (String)searchCriteriaMap.get("firstName");
    	String lastName = (String)searchCriteriaMap.get("lastName");
    	String userName = (String)searchCriteriaMap.get("userName");
    	String personType = (String)searchCriteriaMap.get("personType");
    	String personIdentifier = (String)searchCriteriaMap.get("personIdentifier");
    	String organization = (String)searchCriteriaMap.get("organization");
    	
    	//Only Organization provided
    	if(StringUtils.isEmpty(firstName)
    			&& StringUtils.isEmpty(lastName) 
    			&& StringUtils.isEmpty(personIdentifier) 
    			&& "Please Select".equals(personType)
    			&& StringUtils.isEmpty(userName)
    			&& !StringUtils.isEmpty(organization)){
    		
    		searchResults = getResearchStaffTable(searchCriteriaMap);
    		searchResults.addAll(getInvestigatorTable(searchCriteriaMap));
    		return searchResults;
    	}
    	
    	//Only Person Identifier provided
    	if(StringUtils.isEmpty(firstName)
    			&& StringUtils.isEmpty(lastName) 
    			&& "Please Select".equals(personType)
    			&& StringUtils.isEmpty(userName)
    			&& StringUtils.isEmpty(organization)
    			&& !StringUtils.isEmpty(personIdentifier)){
    		
    		searchResults = getResearchStaffTable(searchCriteriaMap);
    		searchResults.addAll(getInvestigatorTable(searchCriteriaMap));
    		return searchResults;
    	}
    	
    	if("ResearchStaff".equals(personType)){
    		 return getResearchStaffTable(searchCriteriaMap);
    	}else if("Investigator".equals(personType)){
    		return getInvestigatorTable(searchCriteriaMap);
    	}else{
    		HashMap resultsMap = new HashMap<String,UserAjaxableDomainObject>();

    		searchResults = getResearchStaffTable(searchCriteriaMap);
    		searchResults.addAll(getInvestigatorTable(searchCriteriaMap));
    		
    		for(UserAjaxableDomainObject uado : searchResults){
    			if(StringUtils.isNotEmpty(uado.getUserName()) && !resultsMap.containsKey(uado.getUserName())){
    				resultsMap.put(uado.getUserName(), uado);
    			}
    		}
    		List<UserAjaxableDomainObject> csmResults = getUserTable(searchCriteriaMap);
    		for(UserAjaxableDomainObject uado : csmResults){
    			if(resultsMap.containsKey(uado.getUserName())){
    				//Do not add it to searchResults
    			}else{
    				searchResults.add(uado);
    			}
    		}
    		return searchResults;
    	}
    }
	
    /**
     * This method is invoked from the user_search.jsp to fetch csm users for the given search criteria  
     * @param firstName
     * @param lastName
     * @param userName
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<UserAjaxableDomainObject> getUserTable(HashMap searchCriteriaMap) {
    	List<User> csmUserList = userRepository.searchCsmUser((String)searchCriteriaMap.get("firstName"), 
    															(String)searchCriteriaMap.get("lastName"), 
    															(String)searchCriteriaMap.get("userName"));
    	
		List<UserAjaxableDomainObject> ajaxableUserList = new ArrayList<UserAjaxableDomainObject>();
		UserAjaxableDomainObject ajaxableUser = null;
		for(User csmUser : csmUserList){
			ajaxableUser = new UserAjaxableDomainObject();
			ajaxableUser.setId(csmUser.getUserId().intValue());
			ajaxableUser.setFirstName(csmUser.getFirstName());
			ajaxableUser.setLastName(csmUser.getLastName());
			ajaxableUser.setNumber("");
			ajaxableUser.setUserName(csmUser.getLoginName());
			ajaxableUser.setEmailAddress(csmUser.getEmailId());
			ajaxableUser.setRecordType("CSM_RECORD");
			ajaxableUserList.add(ajaxableUser);
		}
		return ajaxableUserList;
	}
    
    
    @SuppressWarnings("unchecked")
	public List<UserAjaxableDomainObject> getResearchStaffTable(HashMap searchCriteriaMap) {
        
        List<SiteResearchStaff> siteResearchStaffs = new ArrayList<SiteResearchStaff>();
        siteResearchStaffs = constructExecuteSiteResearchStaffQuery(searchCriteriaMap);

        Set<UserAjaxableDomainObject> set = new HashSet<UserAjaxableDomainObject>();
        for (SiteResearchStaff srs : siteResearchStaffs) {
            UserAjaxableDomainObject rsado = new UserAjaxableDomainObject();
            rsado.setRecordType("RESEARCHSTAFF_RECORD");
            rsado.setFirstName(srs.getResearchStaff().getFirstName());
            rsado.setLastName(srs.getResearchStaff().getLastName());
            rsado.setMiddleName(srs.getResearchStaff().getMiddleName());
            rsado.setEmailAddress(srs.getResearchStaff().getEmailAddress());
            rsado.setUserName(srs.getResearchStaff().getCaaersUser() != null ? srs.getResearchStaff().getCaaersUser().getLoginName() : "");
            
            StringBuffer sb = new StringBuffer("");
            for (SiteResearchStaff site : srs.getResearchStaff().getSiteResearchStaffs()) {
                sb.append(site.getOrganization().getName() + "<br>");
            }
            rsado.setOrganization(sb.toString());

            rsado.setId(srs.getResearchStaff().getId());
            rsado.setNumber(srs.getResearchStaff().getNciIdentifier() != null ? srs.getResearchStaff().getNciIdentifier() : "");
            rsado.setExternalId(srs.getResearchStaff().getExternalId() != null ? srs.getResearchStaff().getExternalId().trim() : "");
            rsado.setActive(srs.isActive() ? "Active" : "Inactive");
            set.add(rsado);
        }
        return new ArrayList<UserAjaxableDomainObject>(set);
    }
    
    @SuppressWarnings({ "unchecked", "finally" })
    private List<SiteResearchStaff> constructExecuteSiteResearchStaffQuery(HashMap searchCriteriaMap) {

        List<SiteResearchStaff> siteResearchStaffs = new ArrayList<SiteResearchStaff>();
        SiteResearchStaffQuery query = new SiteResearchStaffQuery();
        
        if(StringUtils.isNotEmpty((String)searchCriteriaMap.get("firstName"))){
        	query.filterByFirstName((String)searchCriteriaMap.get("firstName"));
        }
        if(StringUtils.isNotEmpty((String)searchCriteriaMap.get("lastName"))){
        	query.filterByLastName((String)searchCriteriaMap.get("lastName"));
        }
        if(StringUtils.isNotEmpty((String)searchCriteriaMap.get("organization"))){
        	query.filterByOrganization((String)searchCriteriaMap.get("organization"));
        }
        if(StringUtils.isNotEmpty((String)searchCriteriaMap.get("personIdentifier"))){
        	query.filterByNciIdentifier((String)searchCriteriaMap.get("personIdentifier"));
        }
        if(StringUtils.isNotEmpty((String)searchCriteriaMap.get("userName"))){
        	query.filterByUserName((String)searchCriteriaMap.get("userName"));
        }        
        
        try {
            siteResearchStaffs = researchStaffRepository.getSiteResearchStaff(query,searchCriteriaMap);
        }
        catch (Exception e) {
            throw new RuntimeException("Formatting Error", e);
        }
        finally {
            return siteResearchStaffs;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List<UserAjaxableDomainObject> getInvestigatorTable(HashMap searchCriteriaMap) {

        List<Investigator> investigators = new ArrayList<Investigator>();
        investigators = constructExecuteInvestigatorQuery(searchCriteriaMap);

        List<UserAjaxableDomainObject> inv = new ArrayList<UserAjaxableDomainObject>();
        for (Investigator i : investigators) {
            UserAjaxableDomainObject invAdo = new UserAjaxableDomainObject();
            invAdo.setRecordType("INVESTIGATOR_RECORD");
            invAdo.setFirstName(i.getFirstName());
            invAdo.setLastName(i.getLastName());
            invAdo.setMiddleName(i.getMiddleName());
            invAdo.setEmailAddress(i.getEmailAddress());
            invAdo.setUserName(i.getCaaersUser() != null ? i.getCaaersUser().getLoginName() : "");
            
            StringBuffer sb = new StringBuffer();
            for (SiteInvestigator si : i.getSiteInvestigators()) {
                sb.append(si.getOrganization().getName() + "<br>");
            }
            invAdo.setOrganization(sb.toString());

            invAdo.setId(i.getId());
            invAdo.setActive(i.isActive() ? "Active" : "Inactive");
            invAdo.setNumber(i.getNciIdentifier() != null ? i.getNciIdentifier() : "");
            invAdo.setExternalId(i.getExternalId() != null ? i.getExternalId().trim() : "");
            inv.add(invAdo);
        }
        return inv;
    }
    
    @SuppressWarnings({ "finally", "unchecked" })
    private List<Investigator> constructExecuteInvestigatorQuery(HashMap searchCriteriaMap) {

        List<Investigator> investigators = new ArrayList<Investigator>();
        InvestigatorQuery investigatorQuery = new InvestigatorQuery();
        
        if(StringUtils.isNotEmpty((String)searchCriteriaMap.get("firstName"))){
        	investigatorQuery.filterByFirstName((String)searchCriteriaMap.get("firstName"));
        }
        if(StringUtils.isNotEmpty((String)searchCriteriaMap.get("lastName"))){
        	investigatorQuery.filterByLastName((String)searchCriteriaMap.get("lastName"));
        }
        if(StringUtils.isNotEmpty((String)searchCriteriaMap.get("personIdentifier"))){
        	investigatorQuery.filterByNciIdentifier((String)searchCriteriaMap.get("personIdentifier"));
        }
        if(StringUtils.isNotEmpty((String)searchCriteriaMap.get("organization"))){
        	investigatorQuery.filterByOrganization((String)searchCriteriaMap.get("organization"));
        }
        if(StringUtils.isNotEmpty((String)searchCriteriaMap.get("userName"))){
        	investigatorQuery.filterByUserName((String)searchCriteriaMap.get("userName"));
        }

        try {
            investigators = investigatorRepository.searchInvestigator(investigatorQuery);
        }
        catch (Exception e) {
            throw new RuntimeException("Formatting Error", e);
        }
        finally {
            return investigators;
        }
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

	public void setInvestigatorRepository(
			InvestigatorRepository investigatorRepository) {
		this.investigatorRepository = investigatorRepository;
	}

	public void setResearchStaffRepository(
			ResearchStaffRepository researchStaffRepository) {
		this.researchStaffRepository = researchStaffRepository;
	}
}