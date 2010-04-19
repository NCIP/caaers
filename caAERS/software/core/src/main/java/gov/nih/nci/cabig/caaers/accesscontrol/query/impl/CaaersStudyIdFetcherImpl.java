package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.accesscontrol.BaseSecurityFilterer;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.utils.FetcherUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;
/**
 * 
 * @author akkalas
 *
 */
public class CaaersStudyIdFetcherImpl extends BaseSecurityFilterer implements IdFetcher {
	
	private UserDao userDao;
	private StudyDao studyDao;
	private FetcherUtils fetcherUtils;
	
	/**
	 * fetch studyIDS for a given loginId...
	 */
	public List<String> fetch(String loginId) {
		// Implementation goes here ...
		List<String> allowedStudyIds = new ArrayList<String>();
		gov.nih.nci.cabig.caaers.domain.User caaersUser = userDao.getByLoginId(loginId);
		
		
		
		List<String> userOrganizationCodes = getUserOrganizations(caaersUser);
		List<String> rolesToExclude = Arrays.asList(getRolesWhichDoesntRequireStudyLevelFiltering());
		boolean studyFilteringRequired = fetcherUtils.studyFilteringRequired(caaersUser.getUserGroupTypes(), rolesToExclude);
		boolean isAuthorizedOnThisStudy = true;
		List<Study> allStudies = studyDao.getAllStudies();
		
		for (Study study:allStudies) {
			isAuthorizedOnThisStudy = true;
			if (studyFilteringRequired) {
				//study level filtering for SLRR
    			if (studyFilteringRequired) {
    				// checks if user authorized on this study .. if yes , the isAuthorizedOnThisStudy flags stays TRUE
    				if (!isUserAssignedToStudy(caaersUser.getId(),study)) {
    					isAuthorizedOnThisStudy=false;
    				}
    			}
    			//if not SLRR , or SLRR is authorized , then apply site level filtering.
            	if (isAuthorizedOnThisStudy || isAuthorized(userOrganizationCodes, null, study)) {
            		allowedStudyIds.add(study.getId()+"");
            	}
			} else {
				allowedStudyIds.add(study.getId()+"");
			}
		}
		return allowedStudyIds;
	}
	
	/**
	 * study filtering is required only for ROLE_caaers_participant_cd and ROLE_caaers_ae_cd , 
	 * study filtering is not requred if users role is one of the following         
	 * @return
	 */
	private String[] getRolesWhichDoesntRequireStudyLevelFiltering() {
        String[] roles = {
				  UserGroupType.caaers_study_cd.getSecurityRoleName(),
				  UserGroupType.caaers_site_cd.getSecurityRoleName(),
				  UserGroupType.caaers_physician.getSecurityRoleName()
				  };
        return roles;
	}

	/**
	 * 
	 * @param userId
	 * @param study
	 * @return
	 */
	private boolean isUserAssignedToStudy(Integer userId, Study study) {
		// TODO
		
		List<StudyOrganization> soList = study.getActiveStudyOrganizations();
		for (StudyOrganization so:soList) {
			
			List<StudyPersonnel> spList = so.getActiveStudyPersonnel();
			for (StudyPersonnel sp:spList) {
				if(sp.isInActive()) continue;
				if (sp.getSiteResearchStaff().getResearchStaff().getId().equals(userId)) {
					return true;
				}
			}
			/* FUTURE REQUIREMENT .. check for investigators also , physician role dont need study level filtering now , but this code covers the future requirement if any 
			List<StudyInvestigator> studyInvList = so.getStudyInvestigators();
			for (StudyInvestigator studyInv:studyInvList) {
				if (studyInv.getSiteInvestigator().getInvestigator().getId().equals(userId)) {
					return true;
				}
			}*/
			
		}
		return false;
	}
	
	/**
	 * 
	 * @param userOrganizations
	 * @param study
	 * @param studyDomainObj
	 * @return
	 */
	private boolean isAuthorized(List<String> userOrganizations, StudySearchableAjaxableDomainObject study, Study studyDomainObj) {
		// check if user is part of co-ordinating center 
		if (studyDomainObj == null ) {
			//if (researchStaffOrganization.equals(study.getCoordinatingCenterCode())) return true;
			if (userOrganizations.contains(study.getCoordinatingCenterCode()) || userOrganizations.contains(study.getPrimarySponsorCode()) ) return true;
			studyDomainObj = studyDao.getById(study.getId()) ;
		} else  {
			//if (researchStaffOrganization.equals(studyDomainObj.getStudyCoordinatingCenter().getOrganization().getNciInstituteCode())) return true;
			if (userOrganizations.contains(studyDomainObj.getStudyCoordinatingCenter().getOrganization().getNciInstituteCode()) || 
					userOrganizations.contains(studyDomainObj.getPrimarySponsorCode()) ) return true;
		}
		return isUserOrganizationPartOfStudySites(userOrganizations,studyDomainObj);
		
	}
	
	/**
	 * 
	 * @param userOrganizations
	 * @param study
	 * @return
	 */
	private boolean isUserOrganizationPartOfStudySites( List<String> userOrganizations , Study study) {
		List<StudyOrganization> soList = study.getActiveStudyOrganizations();
		for (StudyOrganization so:soList) {
			
			if (so instanceof StudySite) {
				if (userOrganizations.contains(so.getOrganization().getNciInstituteCode())) {
					return true;
				}
			}			
		}
		return false;
	}
	

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	public void setFetcherUtils(FetcherUtils fetcherUtils) {
		this.fetcherUtils = fetcherUtils;
	}

	public List fetch(String id, Object query) {
		// TODO Auto-generated method stub
		return null;
	}

}
