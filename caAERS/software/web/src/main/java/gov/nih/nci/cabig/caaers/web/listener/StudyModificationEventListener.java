package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.event.StudyModificationEvent;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.security.util.StringUtilities;

import java.util.List;

import org.springframework.context.ApplicationEvent;


/**
 * @author: Biju Joseph
 */
public class StudyModificationEventListener extends AbstractEventListener {
    private StudyRepository studyRepository;
    private CaaersSecurityFacade caaersSecurityFacade;
    

    @Override
    public boolean isSupported(ApplicationEvent event) {
        return event instanceof StudyModificationEvent;
    }

    @Override
    public void preProcess(ApplicationEvent event) {
        StudyModificationEvent studyEvent = (StudyModificationEvent) event;
        Study study = (Study) studyEvent.getSource();
        studyRepository.provisionStudyTeam(study);
        caaersSecurityFacade.clearUserCache(SecurityUtils.getUserLoginName(getAuthentication(event)));
        clearStudyUserCache(study);
    }
    
	/**
	 * RS or INV may have been added to the study , we need to clear cache of all users  associated to the study .
	 * @param study
	 */
    private void clearStudyUserCache(Study study) {
        List<StudyOrganization> sos = study.getStudyOrganizations();
        for (StudyOrganization so:sos) {
        	List<StudyPersonnel>  sps= so.getStudyPersonnels();
        	for (StudyPersonnel sp:sps) {
        		String loginName = sp.getSiteResearchStaff().getResearchStaff().getLoginId();
        		if (!StringUtilities.isBlank(loginName)) {
        			caaersSecurityFacade.clearUserCache(loginName);
        		}
        	}
        	
        	List<StudyInvestigator> sis = so.getStudyInvestigators();
        	for (StudyInvestigator si:sis) {
        		String loginName = si.getSiteInvestigator().getInvestigator().getLoginId();
        		if (!StringUtilities.isBlank(loginName)) {
        			caaersSecurityFacade.clearUserCache(loginName);
        		}
        	}   
        }
    }
    
    public StudyRepository getStudyRepository() {
        return studyRepository;
    }

    public void setStudyRepository(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

	public void setCaaersSecurityFacade(CaaersSecurityFacade caaersSecurityFacade) {
		this.caaersSecurityFacade = caaersSecurityFacade;
	}
}
