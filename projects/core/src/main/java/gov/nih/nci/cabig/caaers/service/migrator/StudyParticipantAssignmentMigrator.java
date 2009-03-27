package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.security.util.StringUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

public class StudyParticipantAssignmentMigrator implements Migrator<Participant> {
	
	private StudySiteDao studySiteDao;
	private final Log log = LogFactory.getLog(getClass());
	  
	public void migrate(Participant src, Participant dest,DomainObjectImportOutcome<Participant> outcome) {

        for (StudyParticipantAssignment studyParticipantAssignment : src.getAssignments()) {
            for (Identifier identifier : studyParticipantAssignment.getStudySite().getStudy().getIdentifiers()) {
                log.debug("Size of identifiers : " + studyParticipantAssignment.getStudySite().getStudy().getIdentifiers());
                String identifierValue = identifier.getValue();
                String organizationName = studyParticipantAssignment.getStudySite().getOrganization().getName();
                String organizationNciInstituteCode = studyParticipantAssignment.getStudySite().getOrganization().getNciInstituteCode();
                String identifierType = identifier.getType();
                StudySite studySite = null;
                if (StringUtilities.isBlank(organizationNciInstituteCode)) {
                	studySite = studySiteDao.matchByStudyAndOrg(organizationName, identifierValue, identifierType);
                } else {
                	studySite = studySiteDao.matchByStudyAndOrgNciId(organizationNciInstituteCode, identifierValue, identifierType);
                }

                StudyParticipantAssignment studParticipantAssignment = new StudyParticipantAssignment(dest, studySite);
                studParticipantAssignment.setStudySubjectIdentifier(studyParticipantAssignment.getStudySubjectIdentifier());
                
                dest.addAssignment(studParticipantAssignment);

                outcome.ifNullObject(studySite, DomainObjectImportOutcome.Severity.ERROR,
                        "The Study with Identifier \" " + identifierValue
                                + " \" is either nonexistant or does not match the provided Site");

            }

        }
        outcome.ifNullOrEmptyList(src.getAssignments(), DomainObjectImportOutcome.Severity.ERROR,
                "Assignments are either Empty or Not Valid");
	}
	
	///BEAN METHODS
	 @Required
	 public void setStudySiteDao(final StudySiteDao studySiteDao) {
		 this.studySiteDao = studySiteDao;
	 }
}
