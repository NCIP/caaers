package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.User;

public class ParticipantSiteSecurityFilterer extends BaseSecurityFilterer implements DomainObjectSecurityFilterer {
	
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
                
		StudySiteAjaxableDomainObject researchStaffOrganization = new StudySiteAjaxableDomainObject();
		researchStaffOrganization.setNciInstituteCode(organization.getNciInstituteCode());
        
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
			if (isResearchStaffOrganizationPartOfStudySites(researchStaffOrganization,getAuthorizedStudies(participant,researchStaff.getId(),studyFilteringRequired))) {
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

        	if (!isResearchStaffOrganizationPartOfStudySites(researchStaffOrganization,getAuthorizedStudies(participant,researchStaff.getId(),studyFilteringRequired))) {
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
	private boolean isResearchStaffOrganizationPartOfStudySites(StudySiteAjaxableDomainObject researchStaffOrganization, List <StudySearchableAjaxableDomainObject> authorizedStudies) {
		//get all studySites.
		List <StudySiteAjaxableDomainObject> allSites = new ArrayList<StudySiteAjaxableDomainObject>();		
		for (StudySearchableAjaxableDomainObject study:authorizedStudies) {
			//allSites.addAll(study.getStudySites());
			allSites.addAll(study.getAssignedStudySites());
			// add coordinating center if user is from coordinating center ... 
			//if (isSiteCoodinator) {
				for (StudySiteAjaxableDomainObject scc : study.getStudySites()) {
					if (scc.getType().equals("SCC") && scc.getNciInstituteCode().equals(researchStaffOrganization.getNciInstituteCode())) {
						allSites.add(scc);
					}
				}
			//}
		}
		if (allSites.contains(researchStaffOrganization)) return true;
		return false;
	}

	public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}
	


}
