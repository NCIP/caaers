package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.AdverseEventService;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import gov.nih.nci.cabig.ctms.domain.GridIdentifiable;

import java.util.List;

public class DefaultAdverseEventService implements AdverseEventService {
    private StudyDao studyDao;

    private OrganizationDao organizationDao;

    private ParticipantDao participantDao;

    private StudyParticipantAssignmentDao studyParticipantAssignmentDao;

    private ExpeditedAdverseEventReportDao adverseEventReportDao;

    public DefaultAdverseEventService() {
    }

    public String createCandidateAdverseEvent(Study study, Participant participant,
                    Organization organization, AdverseEvent ae, List<Lab> labs) {
        ParameterLoader loader = new ParameterLoader(study, organization, participant);
        StudyParticipantAssignment studyParticipantAssignment = getStudyParticipantAssignmentDao()
                        .getAssignment(loader.participant, loader.study);

        ExpeditedAdverseEventReport adverseEventReport = new ExpeditedAdverseEventReport();
        adverseEventReport.setAssignment(studyParticipantAssignment);
        adverseEventReport.addAdverseEvent(ae);
        adverseEventReport.getLabs().addAll(labs);
        getAdverseEventReportDao().save(adverseEventReport);
        return adverseEventReport.getGridId();
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    private <T extends DomainObject & GridIdentifiable> T load(T param, GridIdentifiableDao<T> dao,
                    boolean required) {
        T loaded = null;
        boolean hasGridId = checkForGridId(param);
        boolean hasIdentifiers = this.checkForIdentifiers(param);
        if (!hasGridId && !hasIdentifiers) {
            throw new IllegalArgumentException(param.getClass().getSimpleName()
                            + " doesn't have grid identifiers or identifiers");
        }
        if (hasGridId) {
            loaded = dao.getByGridId(param.getGridId());
            if (loaded != null) {
                return loaded;
            }

        }
        if (hasIdentifiers) {
            // load based on identifiers
            loaded = loadByIdentifiers(param);
        }

        if (loaded == null) {
            throw new IllegalArgumentException(param.getClass().getSimpleName() + " doesn't exist.");
        }
        return loaded;
    }

    @SuppressWarnings("unchecked")
    private <T extends DomainObject> T loadByIdentifiers(T param) {
        DomainObject returnObject = null;
        if (param instanceof Participant) {
            returnObject = (DomainObject) getParticipantDao().getByIdentifier(
                            ((Participant) param).getIdentifiers().get(0));
        } else if (param instanceof Study) {
            returnObject = (DomainObject) getStudyDao().getByIdentifier(
                            ((Study) param).getIdentifiers().get(0));
        }
        return (T) returnObject;
        // getByExample(param, {"identifiers"}, )
    }

    private boolean checkForGridId(GridIdentifiable gridIdentifiable) {
        if (!gridIdentifiable.hasGridId()) {
            return false;
            // throw new IllegalArgumentException(
            // "No gridId on " + gridIdentifiable.getClass().getSimpleName().toLowerCase() + "
            // parameter");
        }
        return true;
    }

    private boolean checkForIdentifiers(DomainObject obj) {
        List<Identifier> identifiers = null;
        if (obj instanceof Study) {
            Study study = (Study) obj;
            identifiers = study.getIdentifiers();
        }
        if (obj instanceof Participant) {
            Participant subject = (Participant) obj;
            identifiers = subject.getIdentifiers();
        }

        if (identifiers == null) {
            return false;
        }

        return true;
    }

    public void setStudyParticipantAssignmentDao(
                    StudyParticipantAssignmentDao studyParticipantAssignmentDao) {
        this.studyParticipantAssignmentDao = studyParticipantAssignmentDao;
    }

    public StudyParticipantAssignmentDao getStudyParticipantAssignmentDao() {
        return studyParticipantAssignmentDao;
    }

    public ExpeditedAdverseEventReportDao getAdverseEventReportDao() {
        return adverseEventReportDao;
    }

    public void setAdverseEventReportDao(ExpeditedAdverseEventReportDao adverseEventReportDao) {
        this.adverseEventReportDao = adverseEventReportDao;
    }

    private class ParameterLoader {
        private Study study;

        private Organization organization;

        private Participant participant;

        public ParameterLoader(Study study, Organization organization) {
            loadStudy(study);
            loadOrganization(organization);
        }

        public ParameterLoader(Study study, Organization organization, Participant participant) {
            this(study, organization);
            loadParticipant(participant);
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
            if (param == null) {
                this.organization = getOrganizationDao().getDefaultOrganization();
            } else {
                this.organization = load(param, getOrganizationDao(), true);
                if (this.organization == null) {
                    if (this.organization == null) {
                        this.organization = getOrganizationDao().getDefaultOrganization();
                    }
                }
            }
        }

        private void loadStudy(Study param) {
            this.study = load(param, getStudyDao(), true);
        }

        private void loadParticipant(Participant param) {
            this.participant = load(param, getParticipantDao(), true);
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

        public Participant getParticipant() {
            return participant;
        }

        public void setParticipant(Participant participant) {
            this.participant = participant;
        }
    }

}
