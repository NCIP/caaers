package gov.nih.nci.cabig.caaers.web.remote;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;

import java.util.List;

import com.semanticbits.coppa.infrastructure.RemoteSession;

public class RemoteObjectBinder {
	private RemoteSession remoteSession;
	private OrganizationDao organizationDao;
	/**
	 * 
	 * @param study
	 * @return
	 */
	public Study bindStudy(Study study) {
    	List<StudyOrganization> studyOrganizations = study.getStudyOrganizations();
    	for (StudyOrganization studyOrganization:studyOrganizations) {
    		Organization organization = studyOrganization.getOrganization();
    		if (organization instanceof RemoteOrganization) {
    			Organization remoteOrganization = bindOrganization(organization);
    			studyOrganization.setOrganization(remoteOrganization);
    		}
    	} 
    	return study;
	}
	/**
	 * 
	 * @param organization
	 * @return
	 */
	public Organization bindOrganization(Organization organization) {		
		if (organization instanceof RemoteOrganization) {
			Organization remoteOrganization = 
				(RemoteOrganization)remoteSession.load(RemoteOrganization.class, organization.getNciInstituteCode());
			organizationDao.reassociate(remoteOrganization);
			return remoteOrganization;
		}
		return organization;
	}
	public void setRemoteSession(RemoteSession remoteSession) {
		this.remoteSession = remoteSession;
	}
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
	
	
}
