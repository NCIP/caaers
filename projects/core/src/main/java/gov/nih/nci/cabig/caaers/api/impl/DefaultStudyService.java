/**
 * 
 */
package gov.nih.nci.cabig.caaers.api.impl;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.annotation.Transactional;

import gov.nih.nci.cabig.caaers.api.StudyService;
import gov.nih.nci.cabig.caaers.dao.CaaersDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.SiteDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.DomainObject;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 *
 */
@Transactional
public class DefaultStudyService implements StudyService {
    
    private ParticipantDao participantDao;
    private StudyParticipantAssignmentDao studyParticipantAssignmentDao;
    private SiteDao siteDao;
    private StudyDao studyDao;
    private SessionFactory sessionFactory;
    


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.api.StudyService#assignParticipant(gov.nih.nci.cabig.caaers.domain.Study, gov.nih.nci.cabig.caaers.domain.Participant, gov.nih.nci.cabig.caaers.domain.Site)
     */
    public StudyParticipantAssignment assignParticipant(Study study, Participant participant,
                    Site site) {

        
        
        StudyParticipantAssignment newAssignment = new StudyParticipantAssignment();
        ParameterLoader loader = new ParameterLoader(study, site);

        Participant loadedParticipant = load(participant, getParticipantDao(), false);
        if (loadedParticipant == null) {
            getParticipantDao().save(participant);
            loadedParticipant = participant;
            
        } else {
            StudyParticipantAssignment existingAssignment = 
                getStudyParticipantAssignmentDao().getAssignment(loadedParticipant, loader.getStudy());
            if (existingAssignment != null) {
                throw new IllegalArgumentException(
                    "Participant already assigned to this study.");
            }
        }
        
        StudySite studySite = loader.validateSiteInStudy();
        newAssignment.setParticipant(loadedParticipant);
        newAssignment.setStudySite(studySite);
        newAssignment.setDateOfEnrollment(new Date());
        loadedParticipant.addAssignment(newAssignment);
        getParticipantDao().save(loadedParticipant);
        
        return newAssignment;
    }
    
//    public StudyParticipantAssignment getStudyParticipantAssignment(Participant participant, Study study){
//        return getStudyParticipantAssignmentDao().getAssignment(participant, study);
//    }
    

   
    
    private <T extends DomainObject> T load(T param, CaaersDao<T> dao, boolean required) {
        T loaded = null;
        if(param.getId() != null){
            loaded = dao.getById(param.getId());
        }
        if(required && loaded == null){
            throw new IllegalArgumentException(param.getClass().getSimpleName() + " doesn't exist.");
        }
        return loaded;
    }
    
    private class ParameterLoader{
        private Study study;
        private Site site;
        
        public ParameterLoader(Study study, Site site){
            loadStudy(study);
            loadSite(site);
        }

        public StudySite validateSiteInStudy() {
            StudySite studySite = null;
            for (StudySite aStudySite : getStudy().getStudySites()) {
                if (aStudySite.getSite().equals(getSite())) {
                    studySite = aStudySite;
                }
            }
            if(studySite == null){
                throw new IllegalArgumentException("Site " + getSite().getId() + " not associated with study " + getStudy().getId());
            }
            return studySite;
        }

        private void loadSite(Site param) {
            this.site = load(param, getSiteDao(), true);
        }

        private void loadStudy(Study param) {
            this.study = load(param, getStudyDao(), true);
        }

        public Study getStudy() {
            return study;
        }

        public void setStudy(Study study) {
            this.study = study;
        }

        public Site getSite() {
            return site;
        }

        public void setSite(Site site) {
            this.site = site;
        }
    }

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }



    public StudyParticipantAssignmentDao getStudyParticipantAssignmentDao() {
        return studyParticipantAssignmentDao;
    }



    public void setStudyParticipantAssignmentDao(StudyParticipantAssignmentDao assignmentDao) {
        this.studyParticipantAssignmentDao = assignmentDao;
    }



    public SiteDao getSiteDao() {
        return siteDao;
    }



    public void setSiteDao(SiteDao siteDao) {
        this.siteDao = siteDao;
    }



    public StudyDao getStudyDao() {
        return studyDao;
    }



    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

}
