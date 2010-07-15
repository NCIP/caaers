package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.event.StudyModificationEvent;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

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
