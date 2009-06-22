package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerminology;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.ctms.domain.MutableDomainObject;

import java.util.ArrayList;
import java.util.List;


/*
* A wrapper around a MutableDomain object
* This class shows the status of the imported domain object.
*/
public class DomainObjectImportOutcome<T extends MutableDomainObject> {
    private List<Message> messages = new ArrayList<Message>();

    private T importedDomainObject;

    private boolean isSavable = true;

    public void ifNullObject(Object domainObject,
                                Severity severity, String message) {
        if (domainObject == null) {
            addErrorMessage(message, severity);
        }
    }


    public enum Severity {
        ERROR, WARNING
    }

    public void addErrorMessage(String msg, Severity severity) {
        if (severity == Severity.ERROR) {
            isSavable = false;
        }
        messages.add(new Message(msg, severity));
    }

    public boolean hasErrors() {
        return messages.size() > 0;
    }

    @Override
    public String toString() {
        return messages.toString();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public T getImportedDomainObject() {
        return importedDomainObject;
    }

    public void setImportedDomainObject(T importedDomainObject) {
        this.importedDomainObject = importedDomainObject;
    }

    public boolean isSavable() {
        return isSavable;
    }

    public void setSavable(boolean isSavable) {
        this.isSavable = isSavable;
    }

    public static class Message {
        private String message;

        private Severity severity;

        private String property;

        public Message(String message, Severity severity) {
            this.message = message;
            this.severity = severity;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Severity getSeverity() {
            return severity;
        }

        public void setSeverity(Severity severity) {
            this.severity = severity;
        }

        @Override
        public String toString() {
            return message + ", " + property;
        }
    }



    public void addErrorMessageForAE(final AdverseEvent ae, final Integer ordinal) {
        ifNullObject(ae.getGrade(), Severity.ERROR, "Grade is either Empty or Not Valid in AE " + ordinal + " .");
        ifNullObject(ae.getHospitalization(), Severity.ERROR, "Hospitalization is either Empty or Not Valid in AE " + ordinal + " .");
        ifNullObject(ae.getExpected(), Severity.ERROR, "Expectedness is either Empty or Not Valid in AE " + ordinal + " .");
        ifNullObject(ae.getAttributionSummary(), Severity.ERROR, "Attribution is either Empty or Not Valid in AE " + ordinal + " .");


    }

    public void addErrorMessageForCtcTerm(final CtcTerm ctcTerm, final Integer ordinal, final Ctc ctcVersion) {
        ifNullObject(ctcTerm, Severity.ERROR, "The Term you provided in AE " + ordinal + " is not valid. Make sure you provide a " + ctcVersion.getName() + " term.");

    }


    public void addErrorMessageForLowLevelTerm(final LowLevelTerm lowLevelTerm, final Integer ordinal, final MeddraVersion meddraVersion) {
        ifNullObject(lowLevelTerm, Severity.ERROR, "The Term you provided in AE " + ordinal + " is not valid. Make sure you provide a " + meddraVersion.getName() + " term.");

    }


    public void addErrorMessageForTreatmentAssignment(final TreatmentAssignment treatmentAssignment, final String code) {
        ifNullObject(treatmentAssignment, Severity.ERROR, "The treatment Assignment code  " + code + "is not valid ");

    }


    public void addErrorMessageForStudyParticipantAssignment(final StudyParticipantAssignment assignment) {
        ifNullObject(assignment, Severity.ERROR, " Study/Participant could not be found ");
    }


    public void addErrorMessageForIdentifier(final Organization organization) {
        ifNullObject(organization, Severity.ERROR, "The organization specified in identifier is invalid");

    }


    public void addErrorMessageForIdentifiers(final List<Identifier> identifiers) {
        ifNullOrEmptyList(identifiers, Severity.ERROR,
                "Identifiers are either Empty or Not Valid");
    }


    public void ifNullOrEmptyList(List list, Severity severity, String message) {
        if (list.isEmpty()) {
            this.addErrorMessage(message, severity);
        }
    }





    protected void ifNullOrEmptyList(List list,
                                     Severity severity) {
        if (list.isEmpty()) {
            addErrorMessage("is required or has errors", severity);
        }
    }


    public void errorInBusinessLogic(Severity severity,
                                        String message) {
        this.addErrorMessage(message, severity);
    }


    public void addErrorMessageForCtcTerm(final Ctc ctc) {
        ifNullObject(ctc, Severity.ERROR, "CTC is either Empty or Not Valid");

    }


    public void addErrorMessageForMedra(final MeddraVersion meddraVersion) {
        ifNullObject(meddraVersion, Severity.ERROR, "MedDRA Version is either Empty or Not Valid");

    }


    public void addErrorMessageForAeTerminoloty(final AeTerminology aeTerminology) {
        ifNullObject(aeTerminology, Severity.ERROR, "AeTerminology is either Empty or Not Valid");

    }


    public void addErrorMessageFordiseaseTerminology(final DiseaseTerminology diseaseTerminology) {
        ifNullObject(diseaseTerminology, Severity.ERROR, "Disease AeTerminology is either Empty or Not Valid");

    }


    public void addErrorMessageFordiseaseCodeTerm(final DiseaseCodeTerm diseaseCodeTerm) {
        ifNullObject(diseaseCodeTerm, Severity.ERROR, "Disease Code Term is either Empty or Not Valid");

    }

    public void addErrorMessageForFundingSponsor(final Organization organization) {
        ifNullObject(organization, Severity.ERROR, "The organization specified in fundingSponsor is invalid");

    }

    public void addErrorMessageForInvestigator(final Investigator investigator) {
        ifNullObject(null, DomainObjectImportOutcome.Severity.ERROR,
                "The selected investigator " + investigator.getFirstName() + " " + investigator.getLastName() + " is not Valid ");

    }
}
