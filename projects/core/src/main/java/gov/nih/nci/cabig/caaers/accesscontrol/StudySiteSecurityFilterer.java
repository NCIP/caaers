package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;

import java.util.Iterator;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.User;

public class StudySiteSecurityFilterer implements DomainObjectSecurityFilterer {
	
	private ResearchStaffDao researchStaffDao;

	public Object filter(Authentication authentication, String permission, Object returnObject) {
		
		System.out.println("filtering from new classes ");
		
		//get user
		User user = (User)authentication.getPrincipal();
		
		//no filtering if super user
        GrantedAuthority[] grantedAuthorities = user.getAuthorities();
        for (int i=0; i<grantedAuthorities.length; i++) {
        	GrantedAuthority grantedAuthority = (GrantedAuthority)grantedAuthorities[i];
        	if ( grantedAuthority.getAuthority().equals("ROLE_caaers_super_user")) {
        		if (returnObject instanceof Filterer) {
        			return ((Filterer)returnObject).getFilteredObject();
        		} else {
        			return returnObject;
        		}
        		
        	}
        }
        
        boolean studyFilteringRequired = false ; 
        //check if user is AE Coordinator or Subject Coordinator  ...
        for (int i=0; i<grantedAuthorities.length; i++) {
        	GrantedAuthority grantedAuthority = (GrantedAuthority)grantedAuthorities[i];
        	if ( grantedAuthority.getAuthority().equals("ROLE_caaers_study_cd") || grantedAuthority.getAuthority().equals("ROLE_caaers_ae_cd")) {
        		studyFilteringRequired = true;
        		break;
        	}
        }
        
        // get research staff and associated organization.
		ResearchStaffQuery rsQuery = new ResearchStaffQuery();
    	rsQuery.filterByLoginId(user.getUsername());
        List<ResearchStaff> rsList = researchStaffDao.searchResearchStaff(rsQuery);
        
        ResearchStaff researchStaff = rsList.get(0);
        Organization organization = researchStaff.getOrganization();
        
		StudySiteAjaxableDomainObject studySite = new StudySiteAjaxableDomainObject();
		studySite.setNciInstituteCode(organization.getNciInstituteCode());
		
		if (returnObject instanceof StudySearchableAjaxableDomainObject) {
			if (isAuthorized(studySite,(StudySearchableAjaxableDomainObject)returnObject)) {
				return returnObject;
			} else {
				// study level filtering for AE Coordinator or Subject Coordinator  ...
				if (studyFilteringRequired) {
					if (isAuthorized(researchStaff.getId(),(StudySearchableAjaxableDomainObject)returnObject)) {
						return returnObject;
					}
				}
				return null;
			}
		}
		Filterer filterer = (Filterer)returnObject;
		Iterator collectionIter = filterer.iterator();
		
		while (collectionIter.hasNext()) {
        	Object domainObject = collectionIter.next();
        	StudySearchableAjaxableDomainObject study = (StudySearchableAjaxableDomainObject)domainObject;
        	if (!isAuthorized(studySite,study)) {
        		filterer.remove(study);
        	}
        }
		
		return filterer.getFilteredObject();
	}
	private boolean isAuthorized(StudySiteAjaxableDomainObject studySite, StudySearchableAjaxableDomainObject study) {
		if (study.getStudySites().contains(studySite)) return true;
		return false;
	}
	private boolean isAuthorized(Integer researchStaffId, StudySearchableAjaxableDomainObject study) {
		if (study.getStudyPersonnelIds().contains(researchStaffId)) return true;
		return false;
	}
	public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}

}
