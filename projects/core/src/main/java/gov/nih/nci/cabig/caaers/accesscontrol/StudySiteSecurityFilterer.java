package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;

public class StudySiteSecurityFilterer extends BaseSecurityFilterer implements DomainObjectSecurityFilterer {
	
	private UserDao userDao;
	private StudyDao studyDao;


	public Object filter(Authentication authentication, String permission, Object returnObject) {
		//get user

		String userName = getUserName(authentication);
		GrantedAuthority[] grantedAuthorities = getGrantedAuthorities(authentication);
		
		
		//User user = (User)authentication.getPrincipal();
		
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
		
        //Organization organization = researchStaff.getOrganization();
        
		//StudySiteAjaxableDomainObject studySite = new StudySiteAjaxableDomainObject();
		//studySite.setNciInstituteCode(organization.getNciInstituteCode());
		/*
		boolean studyFilteringRequired = false ; 
		// study level restricted roles(SLRR) - AE Coordinator or Subject Coordinator 
        //check if user is  SLRR
        for (int i=0; i<grantedAuthorities.length; i++) {
        	GrantedAuthority grantedAuthority = (GrantedAuthority)grantedAuthorities[i];
        	if ( grantedAuthority.getAuthority().equals("ROLE_caaers_participant_cd") 
        			|| grantedAuthority.getAuthority().equals("ROLE_caaers_ae_cd") 
        			) {
        		studyFilteringRequired = true;
        		break;
        	}
        }
        */
        
        //study filtering is required only for ROLE_caaers_participant_cd and ROLE_caaers_ae_cd , study filtering is not requred if uses role is one of the following         

        String[] roles = {UserGroupType.caaers_study_cd.getSecurityRoleName(),UserGroupType.caaers_site_cd.getSecurityRoleName(),UserGroupType.caaers_physician.getSecurityRoleName()};
        List<String> rolesToExclude = Arrays.asList(roles);
        boolean studyFilteringRequired = studyFilteringRequired(grantedAuthorities, rolesToExclude);
		boolean isAuthorizedOnThisStudy = true;
		/*
		if (returnObject instanceof StudySearchableAjaxableDomainObject) {			
			// study level filtering for SLRR
			if (studyFilteringRequired) {
				if (!isAuthorized(researchStaff.getId(),(StudySearchableAjaxableDomainObject)returnObject)) {
					isAuthorizedOnThisStudy=false;
				}
			}
			// if not SLRR , or SLRR is authorized , then apply site level filtering.
			if (isAuthorized(studySite,(StudySearchableAjaxableDomainObject)returnObject) && isAuthorizedOnThisStudy) {
				return returnObject;
			} else {
				return null;
			}
		}
		*/
		Filterer filterer = (Filterer)returnObject;
		Iterator collectionIter = filterer.iterator();
		
		while (collectionIter.hasNext()) {
        	Object domainObject = collectionIter.next();
        	if (domainObject instanceof Study) {
        		Study studyDomainObj = (Study)domainObject;
        		isAuthorizedOnThisStudy = true;
            	// study level filtering for SLRR
    			if (studyFilteringRequired) {
    				if (!isUserAssignedToStudy(caaersUser.getId(),studyDomainObj)) {
    					isAuthorizedOnThisStudy=false;
    				}
    			}
    			//if not SLRR , or SLRR is authorized , then apply site level filtering.
            	if (!isAuthorized(userOrganizationCodes, null, studyDomainObj) || !isAuthorizedOnThisStudy) {
            		filterer.remove(studyDomainObj);
            	}
        	} else {
	        	StudySearchableAjaxableDomainObject study = (StudySearchableAjaxableDomainObject)domainObject;
	        	isAuthorizedOnThisStudy = true;
	        	// study level filtering for SLRR
				if (studyFilteringRequired) {
					if (!checkResearchStaff(caaersUser.getId(),study)) {
						isAuthorizedOnThisStudy=false;
					}
				}
				//if not SLRR , or SLRR is authorized , then apply site level filtering.
	        	if (!isAuthorized(userOrganizationCodes, study, null) || !isAuthorizedOnThisStudy) {
	        		filterer.remove(study);
	        	}
        	}
        }
		
		return filterer.getFilteredObject();
	}
	private boolean isAuthorized(List<String> userOrganizations, StudySearchableAjaxableDomainObject study, Study studyDomainObj) {
		// check if user is part of co-ordinating center 
		if (studyDomainObj == null ) {
			//if (researchStaffOrganization.equals(study.getCoordinatingCenterCode())) return true;
			if (userOrganizations.contains((study.getCoordinatingCenterCode()))) return true;
			studyDomainObj = studyDao.getById(study.getId()) ;
		} else  {
			//if (researchStaffOrganization.equals(studyDomainObj.getStudyCoordinatingCenter().getOrganization().getNciInstituteCode())) return true;
			if (userOrganizations.contains(studyDomainObj.getStudyCoordinatingCenter().getOrganization().getNciInstituteCode())) return true;
		}
		return isUserOrganizationPartOfStudySites(userOrganizations,studyDomainObj);
		
	}

	private boolean isUserOrganizationPartOfStudySites( List<String> userOrganizations , Study study) {
		List<StudyOrganization> soList = study.getStudyOrganizations();
		for (StudyOrganization so:soList) {
			if (so instanceof StudySite) {
				if (userOrganizations.contains(so.getOrganization().getNciInstituteCode())) {
					return true;
				}
			}			
		}
		return false;
	}
	private boolean checkResearchStaff(Integer userId, StudySearchableAjaxableDomainObject study) {
		Study s = studyDao.getById(study.getId()) ;
		return isUserAssignedToStudy(userId,s);

	}
	private boolean isUserAssignedToStudy(Integer userId, Study study) {
		// TODO
		// Query is not doing outer join on research staff , need to fix the query. 
		// temp fix is getting study object for DAO.
		//if (study.getStudyPersonnelIds().contains(researchStaffId)) return true;		
		List<StudyOrganization> soList = study.getStudyOrganizations();
		for (StudyOrganization so:soList) {
			List<StudyPersonnel> spList = so.getStudyPersonnels();
			for (StudyPersonnel sp:spList) {
				if (sp.getResearchStaff().getId().equals(userId)) {
					return true;
				}
			}
		}
		return false;
	}
	

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
