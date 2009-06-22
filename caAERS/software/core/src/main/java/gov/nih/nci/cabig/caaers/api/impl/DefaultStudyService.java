/**
 * 
 */
package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.StudyService;
import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import gov.nih.nci.cabig.ctms.domain.GridIdentifiable;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * @author Biju Joseph
 * 
 */

// TODO: need refactoring.
@Transactional
public class DefaultStudyService implements StudyService {

    private ParticipantDao participantDao;

    private StudyParticipantAssignmentDao studyParticipantAssignmentDao;

    private OrganizationDao organizationDao;

    private StudyDao studyDao;

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cabig.caaers.api.StudyService#assignParticipant(gov.nih.nci.cabig.caaers.domain.Study,
     *      gov.nih.nci.cabig.caaers.domain.Participant, gov.nih.nci.cabig.caaers.domain.Site)
     */
    public StudyParticipantAssignment assignParticipant(Study study, Participant participant,
                    Organization organization, String registrationGridId) {

        StudyParticipantAssignment newAssignment = new StudyParticipantAssignment();
        if (registrationGridId != null) {
            newAssignment.setGridId(registrationGridId);
        }
        ParameterLoader loader = new ParameterLoader(study, organization);

        Participant loadedParticipant = load(participant, getParticipantDao(), false);
        if (loadedParticipant == null) {
            List<Identifier> identifiers = participant.getIdentifiers();
            for (Identifier identifier : identifiers) {
                if (identifier instanceof OrganizationAssignedIdentifier) {
                    // load the organization.
                    OrganizationAssignedIdentifier orgIdentifer = (OrganizationAssignedIdentifier) identifier;
                    Organization org = organizationDao.getByGridId(orgIdentifer.getOrganization()
                                    .getGridId());
                    // update it
                    orgIdentifer.setOrganization(org);
                }
            }

            getParticipantDao().save(participant);
            loadedParticipant = participant;

        } else {
            StudyParticipantAssignment existingAssignment = getStudyParticipantAssignmentDao()
                            .getAssignment(loadedParticipant, loader.getStudy());
            // TODO: newly added organization identifiers should be updated.
            if (existingAssignment != null) {
                throw new IllegalArgumentException("Participant already assigned to this study.");
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

    // public StudyParticipantAssignment getStudyParticipantAssignment(Participant participant,
    // Study study){
    // return getStudyParticipantAssignmentDao().getAssignment(participant, study);
    // }

    private <T extends DomainObject & GridIdentifiable> T load(T param, GridIdentifiableDao<T> dao,
                    boolean required) {
        checkForGridId(param);
        T loaded = null;
        if (param.getGridId() != null) {
            loaded = dao.getByGridId(param.getGridId());
        }
        if (required && loaded == null) {
            throw new IllegalArgumentException(param.getClass().getSimpleName() + " doesn't exist.");
        }
        return loaded;
    }

    private void checkForGridId(GridIdentifiable gridIdentifiable) {
        if (!gridIdentifiable.hasGridId()) {
            throw new IllegalArgumentException("No gridId on "
                            + gridIdentifiable.getClass().getSimpleName().toLowerCase()
                            + " parameter");
        }
    }

    private class ParameterLoader {
        private Study study;

        private Organization organization;

        public ParameterLoader(Study study, Organization organization) {
            loadStudy(study);
            loadOrganization(organization);
        }

        public StudySite validateSiteInStudy() {
            StudySite studySite = null;
            for (StudySite aStudySite : getStudy().getStudySites()) {
                if (aStudySite.getOrganization().equals(getOrganization())) {
                    studySite = aStudySite;
                }
            }
            if (studySite == null) {
                throw new IllegalArgumentException("Site " + getOrganization().getId()
                                + " not associated with study " + getStudy().getId());
            }
            return studySite;
        }

        private void loadOrganization(Organization param) {
            this.organization = load(param, getOrganizationDao(), true);
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

        public Organization getOrganization() {
            return organization;
        }

        public void setOrganization(Organization organization) {
            this.organization = organization;
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

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

}
