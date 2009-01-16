
package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;

import java.util.Iterator;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.User;

public class ExpeditedAdverseEventReportSiteSecurityFilterer extends BaseSecurityFilterer implements DomainObjectSecurityFilterer {
	
	private ResearchStaffDao researchStaffDao;


	public Object filter(Authentication authentication, String permission, Object returnObject) {
		
		System.out.println("filtering from new classes ");
		
		//get user
		User user = (User)authentication.getPrincipal();
		
		//no filtering if super user
        GrantedAuthority[] grantedAuthorities = user.getAuthorities();

		if (isSuperUser(user)) {
    		if (returnObject instanceof Filterer) {
    			return ((Filterer)returnObject).getFilteredObject();
    		} else {
    			return returnObject;
    		}			
		}
             
        
        // get research staff and associated organization.
		ResearchStaffQuery rsQuery = new ResearchStaffQuery();
    	rsQuery.filterByLoginId(user.getUsername());
        List<ResearchStaff> rsList = researchStaffDao.searchResearchStaff(rsQuery);
        
        ResearchStaff researchStaff = rsList.get(0);
        Organization organization = researchStaff.getOrganization();
        
		boolean studyFilteringRequired = false ; 
		// study level restricted roles(SLRR) - AE Coordinator or Subject Coordinator or study co..
        //check if user is  SLRR
        for (int i=0; i<grantedAuthorities.length; i++) {
        	GrantedAuthority grantedAuthority = (GrantedAuthority)grantedAuthorities[i];
        	if ( grantedAuthority.getAuthority().equals("ROLE_caaers_participant_cd") || grantedAuthority.getAuthority().equals("ROLE_caaers_ae_cd")
        			//|| grantedAuthority.getAuthority().equals("ROLE_caaers_physician") 
        			|| grantedAuthority.getAuthority().equals("ROLE_caaers_study_cd")) {
        		studyFilteringRequired = true;
        		break;
        	}
        }
        
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
				if (!isResearchStaffOrganizationPartOfStudySites(researchStaff.getId(),expeditedAdverseEventReport.getAssignment())) {
					isAuthorizedOnThisStudy=false;
				}
			}
			//if not SLRR , or SLRR is authorized , then apply site level filtering.
        	if (!isAuthorized(organization,expeditedAdverseEventReport.getAssignment(),organization.getNciInstituteCode()) || !isAuthorizedOnThisStudy) {
        		filterer.remove(expeditedAdverseEventReport);
        	}
        }
		
		return filterer.getFilteredObject();
	}
	private boolean isAuthorized(Organization studySite, StudyParticipantAssignment assignment, String researchStaffOrganozation) {
		// check if user is part of co-ordinating center 
		if (researchStaffOrganozation.equals(assignment.getStudySite().getStudy().getStudyCoordinatingCenter().getOrganization().getNciInstituteCode())) return true;
		
		Organization organization = assignment.getStudySite().getOrganization();
		//for (StudyOrganization so:soList) {
			//if (so instanceof StudySite) {
				if (studySite.getNciInstituteCode().equals(organization.getNciInstituteCode())) {
					return true;
				}
			//}			
		//}
		return false;
	}
	private boolean isResearchStaffOrganizationPartOfStudySites(Integer researchStaffId, StudyParticipantAssignment assignment) {

		//StudyOrganization so = assignment.getStudySite();//.getOrganization();
		List<StudyOrganization> soList = assignment.getStudySite().getStudy().getStudyOrganizations();

		for (StudyOrganization so:soList) {
			List<StudyPersonnel> spList = so.getStudyPersonnels();
			for (StudyPersonnel sp:spList) {
				if (sp.getResearchStaff().getId().equals(researchStaffId)) {
					return true;
				}
			}
		}
		return false;
	}
	public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}


}
