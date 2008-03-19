package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ParticipantServiceImpl extends AbstractImportServiceImpl implements ParticipantService {

    ParticipantDao participantDao;

    StudySiteDao studySiteDao;

    /**
     * Search using a sample. Populate a Participant object
     * 
     * @param participant
     *                object
     * @return List of Participant objects based on the sample participant object
     * @throws Runtime
     *                 exception
     */
    public List<Participant> search(Participant participant) throws Exception {
        return participantDao.searchByExample(participant);
    }

    /**
     * Will calculate the body surface area using Mosteller formula
     */
    public double bodySuraceArea(double height, String heightUOM, double weight, String weightUOM) {

        ParticipantHistory participantHistory = new ParticipantHistory();
        ParticipantHistory.Measure ht = new ParticipantHistory.Measure();
        ht.setQuantity(new BigDecimal(height));
        ht.setUnit(heightUOM);

        participantHistory.setHeight(ht);

        ParticipantHistory.Measure wt = new ParticipantHistory.Measure();
        wt.setQuantity(new BigDecimal(weight));
        wt.setUnit(weightUOM);

        participantHistory.setWeight(wt);

        return participantHistory.getBodySurfaceArea();
    }

    public List<Participant> searchParticipant(final ParticipantQuery query) {
        return participantDao.searchParticipant(query);
    }

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public DomainObjectImportOutcome<Participant> createParticipantObjects(
                    Participant xstreamParticipant) {

        Participant participant = new Participant();
        DomainObjectImportOutcome<Participant> participantImportOutcome = new DomainObjectImportOutcome<Participant>();

        participant.setFirstName(xstreamParticipant.getFirstName());
        participant.setLastName(xstreamParticipant.getLastName());
        participant.setMiddleName(xstreamParticipant.getMiddleName());
        participant.setMaidenName(xstreamParticipant.getMaidenName());
        participant.setDateOfBirth(xstreamParticipant.getDateOfBirth());
        participant.setGender(xstreamParticipant.getGender());
        participant.setRace(xstreamParticipant.getRace());
        participant.setEthnicity(xstreamParticipant.getEthnicity());

        // migrateIdentifiers(participant,xstreamParticipant);
        migrateIdentifiers(participant, xstreamParticipant, participantImportOutcome);
        migrateAssignments(participant, xstreamParticipant, participantImportOutcome);

        participantImportOutcome.setImportedDomainObject(participant);
        participantUniquenessCheck(participant, participantImportOutcome, Severity.ERROR);

        return participantImportOutcome;
    }

    private void migrateAssignments(Participant destination, Participant source,
                    DomainObjectImportOutcome participantImportOutcome) {

        if (source.getAssignments() != null) {
            ArrayList<String> studySites = new ArrayList<String>();
            for (int i = 0; i < source.getAssignments().size(); i++) {
                StudyParticipantAssignment studyParticipantAssignment = source.getAssignments()
                                .get(i);
                String theIdentifier = "";

                for (Identifier identifier : studyParticipantAssignment.getStudySite().getStudy()
                                .getIdentifiers()) {

                    log.debug("Size of identifiers : "
                                    + studyParticipantAssignment.getStudySite().getStudy()
                                                    .getIdentifiers());
                    theIdentifier = identifier.getValue();
                    StudySite studySite = studySiteDao.matchByStudyAndOrg(
                                    studyParticipantAssignment.getStudySite().getOrganization()
                                                    .getName(), identifier.getValue(), identifier
                                                    .getType());

                    if (studySite != null && !studySites.contains(studySite.getId().toString())) {
                        studySites.add(studySite.getId().toString());
                        log.info("StudySite was found id :  " + studySite.getId());
                        destination.getAssignments().add(
                                        new StudyParticipantAssignment(destination, studySite));
                        break;
                    }

                    participantImportOutcome.ifNullObject(
                                    studySite,
                            Severity.ERROR,
                                    "The Study with Identifier \" "
                                                    + theIdentifier
                                                    + " \" is either nonexistant or does not match the provided Site");

                }
            }
        }
        participantImportOutcome.ifNullOrEmptyList(source.getAssignments(), Severity.ERROR,
                        "Assignments are either Empty or Not Valid");
    }

    private void participantUniquenessCheck(Participant participant,
                    DomainObjectImportOutcome participantImportOutcome, Severity severity) {

        participant.firstPrimaryIndicatorInIdentifiers();

        for (Identifier identifier : participant.getIdentifiers()) {
            Participant tempParticipant = getParticipantDao().getByIdentifier(identifier);
            if (tempParticipant != null) {
                participantImportOutcome.addErrorMessage(participant.getClass().getSimpleName()
                                + " identifier already exists. ", severity);
                break;
            }
        }
    }

    public StudySiteDao getStudySiteDao() {
        return studySiteDao;
    }

    public void setStudySiteDao(StudySiteDao studySiteDao) {
        this.studySiteDao = studySiteDao;
    }

}
