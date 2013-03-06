/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.event.StudyModificationEvent;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.security.util.StringUtilities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        super.preProcess(event);
        log.debug("Entering gov.nih.nci.cabig.caaers.web.listener.StudyModificationEventListener.preProcess: " + event.toString());
        StudyModificationEvent studyEvent = (StudyModificationEvent) event;
        Study study = (Study) studyEvent.getSource();

        //re-provision in CSM the study protection elements. 
        studyRepository.provisionStudyTeam(study);

        Set<String> loginNames = new HashSet<String>(); //findLoginNamesFromStudy(study);
        loginNames.add(SecurityUtils.getUserLoginName(getAuthentication(event)) );
        
        //clear the users caches.
        for(String loginName : loginNames){
            caaersSecurityFacade.clearUserCache(loginName);
        }
        log.debug("Exiting gov.nih.nci.cabig.caaers.web.listener.StudyModificationEventListener.preProcess: " + event.toString());
    }

    /**
     * Will find all the usersnames associated with the study
     * @param study
     * @return
     */
    private Set<String> findLoginNamesFromStudy(Study study){
      Set<String> userNames = new HashSet<String>();
        List<StudyOrganization> sos = study.getStudyOrganizations();
        for (StudyOrganization so:sos) {
        	List<StudyPersonnel>  sps= so.getStudyPersonnels();
        	for (StudyPersonnel sp:sps) {
                if(sp == null) continue;
                SiteResearchStaff srs = sp.getSiteResearchStaff();
                if(srs == null) continue;
                ResearchStaff rs = srs.getResearchStaff();
                if(rs == null) continue;
                String loginName = rs.getLoginId();
                if(loginName != null) userNames.add(loginName);
        	}

        	List<StudyInvestigator> sis = so.getStudyInvestigators();
        	for (StudyInvestigator si:sis) {
                if(si == null) continue;
                SiteInvestigator siteInv = si.getSiteInvestigator();
                if(siteInv == null) continue;
                Investigator i = siteInv.getInvestigator();
                if(i == null) continue;
                String loginName = i.getLoginId();
                if(loginName != null) userNames.add(loginName);

        	}

        }
        return userNames;
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
