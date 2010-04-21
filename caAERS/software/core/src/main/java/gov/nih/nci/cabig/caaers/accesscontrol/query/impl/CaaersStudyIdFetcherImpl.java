package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.accesscontrol.BaseSecurityFilterer;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.utils.FetcherUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;

/**
 * Will return the ID of the studies that a particular user has access to.
 *
 * @author Srini Akkala
 * @author Biju Joseph
 *
 */
public class CaaersStudyIdFetcherImpl extends AbstractIdFetcher implements IdFetcher {
    
    public List fetch(String s) {
        return null;  
    }

    //
//	private UserDao userDao;
//	private StudyDao studyDao;
//	private FetcherUtils fetcherUtils;
//
//    /**
//     * Will list out all the study that the login has access to.
//     * <br>
//     *  Study Coordinator or Site Coordinator at Organization X
//     *   - can access the studies that is associated to Organization X.
//     *
//     *  All other roles
//     *   - can access the study only if user is associated to Study
//     *
//     * @param loginId
//     * @return
//     */
//	public List<String> fetch(String loginId) {
//		// Implementation goes here ...
//		List<String> allowedStudyIds = new ArrayList<String>();
//		User user = userDao.getByLoginId(loginId);
//
//        if(user instanceof ResearchStaff){
//            //is a research staff.
//        }else{
//            //is an investigator - StudyAssignmentFiltering
//            //should return all the studies that this investigator is associated to.
//
//        }
//
//
//        List<Integer> associatedOrganzationIds = new ArrayList<Integer>();
//
//        //find the organizations where the user is still active.
//        if(user instanceof ResearchStaff){
//           List<SiteResearchStaff> activeSiteResearchStaff = ((ResearchStaff) user).getActiveSiteResearchStaff();
//           if(activeSiteResearchStaff != null){
//               for(SiteResearchStaff siteStaff : activeSiteResearchStaff){
//                   associatedOrganzationIds.add(siteStaff.getOrganization().getId());
//               }
//           }
//        }else if(user instanceof Investigator){
//             ((Investigator) user).getSiteInvestigators()
//        }
//
//        //Study Coordinator or Site Coordinator
//        //  - select sr.id from SiteResearchStaff sr join sr.siteResearchStaffRoles srr join sr.organization org
//        // where (srr.roleCode =  ? or srr.roleCode = ?) and srr.startDate <= ? and srr.endDate>=?
//
//        //other roles
//        //  - select sp.study.id from StudyPersonnel sp where sp.startDate<= ? and sp.endDate >= ? and sp.retired <> true and sp.siteResearchStaff.researchStaff.loginId=?
//
//
//		List<String> userOrganizationCodes = getUserOrganizations(caaersUser);
//		List<String> rolesToExclude = Arrays.asList(getRolesWhichDoesntRequireStudyLevelFiltering());
//		boolean studyFilteringRequired = fetcherUtils.studyFilteringRequired(caaersUser.getUserGroupTypes(), rolesToExclude);
//		boolean isAuthorizedOnThisStudy = true;
//		List<Study> allStudies = studyDao.getAllStudies();
//
//		for (Study study:allStudies) {
//			isAuthorizedOnThisStudy = true;
//			if (studyFilteringRequired) {
//				//study level filtering for SLRR
//    			if (studyFilteringRequired) {
//    				// checks if user authorized on this study .. if yes , the isAuthorizedOnThisStudy flags stays TRUE
//    				if (!isUserAssignedToStudy(caaersUser.getId(),study)) {
//    					isAuthorizedOnThisStudy=false;
//    				}
//    			}
//    			//if not SLRR , or SLRR is authorized , then apply site level filtering.
//            	if (isAuthorizedOnThisStudy || isAuthorized(userOrganizationCodes, null, study)) {
//            		allowedStudyIds.add(study.getId()+"");
//            	}
//			} else {
//				allowedStudyIds.add(study.getId()+"");
//			}
//		}
//		return allowedStudyIds;
//	}
//
//	/**
//	 * study filtering is required only for ROLE_caaers_participant_cd and ROLE_caaers_ae_cd ,
//	 * study filtering is not requred if users role is one of the following
//	 * @return
//	 */
//	private String[] getRolesWhichDoesntRequireStudyLevelFiltering() {
//        String[] roles = {
//				  UserGroupType.caaers_study_cd.getSecurityRoleName(),
//				  UserGroupType.caaers_site_cd.getSecurityRoleName(),
//				  UserGroupType.caaers_physician.getSecurityRoleName()
//				  };
//        return roles;
//	}
//
//	/**
//	 *
//	 * @param userId
//	 * @param study
//	 * @return
//	 */
//	private boolean isUserAssignedToStudy(Integer userId, Study study) {
//		// TODO
//
//		List<StudyOrganization> soList = study.getActiveStudyOrganizations();
//		for (StudyOrganization so:soList) {
//
//			List<StudyPersonnel> spList = so.getActiveStudyPersonnel();
//			for (StudyPersonnel sp:spList) {
//				if(sp.isInActive()) continue;
//				if (sp.getSiteResearchStaff().getResearchStaff().getId().equals(userId)) {
//					return true;
//				}
//			}
//			/* FUTURE REQUIREMENT .. check for investigators also , physician role dont need study level filtering now , but this code covers the future requirement if any
//			List<StudyInvestigator> studyInvList = so.getStudyInvestigators();
//			for (StudyInvestigator studyInv:studyInvList) {
//				if (studyInv.getSiteInvestigator().getInvestigator().getId().equals(userId)) {
//					return true;
//				}
//			}*/
//
//		}
//		return false;
//	}
//
//	/**
//	 *
//	 * @param userOrganizations
//	 * @param study
//	 * @param studyDomainObj
//	 * @return
//	 */
//	private boolean isAuthorized(List<String> userOrganizations, StudySearchableAjaxableDomainObject study, Study studyDomainObj) {
//		// check if user is part of co-ordinating center
//		if (studyDomainObj == null ) {
//			//if (researchStaffOrganization.equals(study.getCoordinatingCenterCode())) return true;
//			if (userOrganizations.contains(study.getCoordinatingCenterCode()) || userOrganizations.contains(study.getPrimarySponsorCode()) ) return true;
//			studyDomainObj = studyDao.getById(study.getId()) ;
//		} else  {
//			//if (researchStaffOrganization.equals(studyDomainObj.getStudyCoordinatingCenter().getOrganization().getNciInstituteCode())) return true;
//			if (userOrganizations.contains(studyDomainObj.getStudyCoordinatingCenter().getOrganization().getNciInstituteCode()) ||
//					userOrganizations.contains(studyDomainObj.getPrimarySponsorCode()) ) return true;
//		}
//		return isUserOrganizationPartOfStudySites(userOrganizations,studyDomainObj);
//
//	}
//
//	/**
//	 *
//	 * @param userOrganizations
//	 * @param study
//	 * @return
//	 */
//	private boolean isUserOrganizationPartOfStudySites( List<String> userOrganizations , Study study) {
//		List<StudyOrganization> soList = study.getActiveStudyOrganizations();
//		for (StudyOrganization so:soList) {
//
//			if (so instanceof StudySite) {
//				if (userOrganizations.contains(so.getOrganization().getNciInstituteCode())) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//
//	public void setUserDao(UserDao userDao) {
//		this.userDao = userDao;
//	}
//
//	public void setStudyDao(StudyDao studyDao) {
//		this.studyDao = studyDao;
//	}
//
//	public void setFetcherUtils(FetcherUtils fetcherUtils) {
//		this.fetcherUtils = fetcherUtils;
//	}
//
//	public List fetch(String id, Object query) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
