package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.utils.Filterer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
/**
 * This filter, will filter the {@link Participant} or {@link ParticipantAjaxableDomainObject} (<i>or collection containing them</i>) based on the 
 * logged in user role. 
 * 
 * @author Srini Akkala 
 * @author Biju Joseph
 *
 */
public class ParticipantSiteSecurityFilterer extends BaseSecurityFilterer implements DomainObjectSecurityFilterer {
	
	private UserDao userDao;
	/**
	 * 1. A SUPER USER should be able to see everything, so no filtering is applied. 
	 * 2. Site Coordinator and Physician should be able to see everything, so no filtering is applied. 
	 * 3. For all the other roles, apply study level filtering.
	 *   i.e. Filter those {@link Participant}s from the search result, that are not assigned studies belonging to
	 *    logged-in user's organization.
	 * 
	 */
	public Object filter(Authentication authentication, String permission, Object returnObject) {
		//get user
		String userName = getUserName(authentication);
		GrantedAuthority[] grantedAuthorities = getGrantedAuthorities(authentication);
		
		//no filtering if super user
		if (isSuperUser(grantedAuthorities)) {
    		if (returnObject instanceof Filterer) {
    			return ((Filterer)returnObject).getFilteredObject();
    		} else {
    			return returnObject;
    		}			
		}
        // get research staff and associated organization.
		//ResearchStaffQuery rsQuery = new ResearchStaffQuery();
    	//rsQuery.filterByLoginId(user.getUsername());
    	//List<ResearchStaff> rsList = researchStaffDao.searchResearchStaff(rsQuery);        
		gov.nih.nci.cabig.caaers.domain.User caaersUser = getCaaersUser(userName,userDao);
		List<String> userOrganizationCodes = getUserOrganizations(caaersUser);
                
		//StudySiteAjaxableDomainObject researchStaffOrganization = new StudySiteAjaxableDomainObject();
		//researchStaffOrganization.setNciInstituteCode(organization.getNciInstituteCode());
        /*
        boolean studyFilteringRequired = false ; 
        //boolean isSiteCoodinator = false;
        //check if user is AE Coordinator or Subject Coordinator  or study coo...
        for (int i=0; i<grantedAuthorities.length; i++) {
        	GrantedAuthority grantedAuthority = (GrantedAuthority)grantedAuthorities[i];
        	if ( grantedAuthority.getAuthority().equals("ROLE_caaers_participant_cd") 
        			|| grantedAuthority.getAuthority().equals("ROLE_caaers_ae_cd")
        			|| grantedAuthority.getAuthority().equals("ROLE_caaers_study_cd")) {
        		studyFilteringRequired = true;
        		break;
        	}
        }	
        */
		
	    //study filtering is required only for ROLE_caaers_participant_cd , ROLE_caaers_study_cd and ROLE_caaers_ae_cd , study filtering is not requred if uses role is one of the following         
       String[] roles = {UserGroupType.caaers_site_cd.getSecurityRoleName(),UserGroupType.caaers_physician.getSecurityRoleName()};
       List<String> rolesToExclude = Arrays.asList(roles);
        boolean studyFilteringRequired = studyFilteringRequired(grantedAuthorities, rolesToExclude);

        /*
        for (int i=0; i<grantedAuthorities.length; i++) {
        	GrantedAuthority grantedAuthority = (GrantedAuthority)grantedAuthorities[i];
        	if ( grantedAuthority.getAuthority().equals("ROLE_caaers_site_cd")) {
        		isSiteCoodinator = true;
        		break;
        	}
        }*/

		if (returnObject instanceof ParticipantAjaxableDomainObject) {
			ParticipantAjaxableDomainObject participant = (ParticipantAjaxableDomainObject)returnObject;
			if (isUserOrganizationPartOfStudySites(userOrganizationCodes,getAuthorizedStudies(participant,caaersUser.getId(),studyFilteringRequired))) {
				return returnObject;
			} else {
				return null;
			}
		}
		Filterer filterer = (Filterer)returnObject;
		Iterator collectionIter = filterer.iterator();
		
		while (collectionIter.hasNext()) {
        	Object domainObject = collectionIter.next();
        	ParticipantAjaxableDomainObject participant = (ParticipantAjaxableDomainObject)domainObject;

        	if (!isUserOrganizationPartOfStudySites(userOrganizationCodes,getAuthorizedStudies(participant,caaersUser.getId(),studyFilteringRequired))) {
        		filterer.remove(participant);
        	}
        }
		
		return filterer.getFilteredObject();
	}
	private List <StudySearchableAjaxableDomainObject> getAuthorizedStudies(ParticipantAjaxableDomainObject participant, Integer researchStaffId, boolean studyFilteringRequired) {
		List <StudySearchableAjaxableDomainObject> authorizedStudies = new ArrayList<StudySearchableAjaxableDomainObject>();
		
//		 study level filtering for AE Coordinator or Subject Coordinator  ...
		if (studyFilteringRequired) {
			List <StudySearchableAjaxableDomainObject> studies = participant.getStudies();				
			for (StudySearchableAjaxableDomainObject study:studies) {
				if (study.getStudyPersonnelIds().contains(researchStaffId)) {
					authorizedStudies.add(study);
				}
			}
		} else {
			authorizedStudies = participant.getStudies();
		}	
		return authorizedStudies;
	}
	private boolean isUserOrganizationPartOfStudySites(List<String> userOrganizations, List <StudySearchableAjaxableDomainObject> authorizedStudies) {
		//get all studySites.
		List <StudySiteAjaxableDomainObject> allSites = new ArrayList<StudySiteAjaxableDomainObject>();		
		for (StudySearchableAjaxableDomainObject study:authorizedStudies) {
			//allSites.addAll(study.getStudySites());
			allSites.addAll(study.getAssignedStudySites());
			// add coordinating center if user is from coordinating center ... 
			//if (isSiteCoodinator) {
				for (StudySiteAjaxableDomainObject scc : study.getStudySites()) {
					if (scc.getType().equals("SCC") && userOrganizations.contains(scc.getNciInstituteCode())) {
						allSites.add(scc);
					}
				}
			//}
		}
		for (StudySiteAjaxableDomainObject studySiteAjaxableDomainObject:allSites){
			if (userOrganizations.contains(studySiteAjaxableDomainObject.getNciInstituteCode())) {
				return true;
			}
		}
		//if (allSites.contains(researchStaffOrganization)) return true;
		return false;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	


}
