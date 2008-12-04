package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;

import java.util.Iterator;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.User;

public class ParticipantSiteSecurityFilterer implements DomainObjectSecurityFilterer {
	
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
        
        System.out.println(organization.getNciInstituteCode()) ;
        
		StudySiteAjaxableDomainObject studySite = new StudySiteAjaxableDomainObject();
		studySite.setNciInstituteCode(organization.getNciInstituteCode());
		
		if (returnObject instanceof ParticipantAjaxableDomainObject) {
			if (isAuthorized(studySite,(ParticipantAjaxableDomainObject)returnObject)) {
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
        	List <StudySiteAjaxableDomainObject> studySites = participant.getStudySites();
        	if (!studySites.contains(studySite)) {
        		filterer.remove(participant);
        	}
        }
		
		return filterer.getFilteredObject();
	}
	private boolean isAuthorized(StudySiteAjaxableDomainObject studySite, ParticipantAjaxableDomainObject participant) {
		if (participant.getStudySites().contains(studySite)) return true;
		return false;
	}
	public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}
	


}
