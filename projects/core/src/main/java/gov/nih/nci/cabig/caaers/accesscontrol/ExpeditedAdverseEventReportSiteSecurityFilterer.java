
package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.utils.Filterer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;

public class ExpeditedAdverseEventReportSiteSecurityFilterer extends BaseSecurityFilterer implements DomainObjectSecurityFilterer {
	
	private UserDao userDao;


	public Object filter(Authentication authentication, String permission, Object returnObject) {
		
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
        
        /*
		boolean studyFilteringRequired = false ; 
		// study level restricted roles(SLRR) - AE Coordinator or Subject Coordinator or study co..
        //check if user is  SLRR
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

        
		boolean isAuthorizedOnThisStudy = true;

		Filterer filterer = (Filterer)returnObject;
		Iterator collectionIter = filterer.iterator();
		
		while (collectionIter.hasNext()) {
        	Object domainObject = collectionIter.next();
        	ExpeditedAdverseEventReport expeditedAdverseEventReport = (ExpeditedAdverseEventReport)domainObject;

        	
        	isAuthorizedOnThisStudy = true;
        	// study level filtering for SLRR
     //   	Study study = expeditedAdverseEventReport.getStudy();     	
        	
			if (studyFilteringRequired) {
				if (!isUserOrganizationPartOfStudySites(caaersUser.getId(),expeditedAdverseEventReport.getAssignment())) {
					isAuthorizedOnThisStudy=false;
				}
			}
			//if not SLRR , or SLRR is authorized , then apply site level filtering.
        	if (!isAuthorized(userOrganizationCodes,expeditedAdverseEventReport.getAssignment()) || !isAuthorizedOnThisStudy) {
        		filterer.remove(expeditedAdverseEventReport);
        	}
        }
		
		return filterer.getFilteredObject();
	}
	private boolean isAuthorized(List<String> userOrganizations , StudyParticipantAssignment assignment) {
		// check if user is part of co-ordinating center 
		if (userOrganizations.contains(assignment.getStudySite().getStudy().getStudyCoordinatingCenter().getOrganization().getNciInstituteCode())) return true;
		
		Organization studySite = assignment.getStudySite().getOrganization();
		//for (StudyOrganization so:soList) {
			//if (so instanceof StudySite) {
				if (userOrganizations.contains(studySite.getNciInstituteCode())) {
					return true;
				}
			//}			
		//}
		return false;
	}
	private boolean isUserOrganizationPartOfStudySites(Integer userId, StudyParticipantAssignment assignment) {

		//StudyOrganization so = assignment.getStudySite();//.getOrganization();
		List<StudyOrganization> soList = assignment.getStudySite().getStudy().getStudyOrganizations();

		for (StudyOrganization so:soList) {
			List<StudyPersonnel> spList = so.getStudyPersonnels();
			for (StudyPersonnel sp:spList) {
				if (sp.getResearchStaff().getId().equals(userId)) {
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
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


}
