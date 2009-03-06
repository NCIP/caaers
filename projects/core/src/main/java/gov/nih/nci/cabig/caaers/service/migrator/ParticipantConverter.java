package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.webservice.participant.AssignmentType;
import gov.nih.nci.cabig.caaers.webservice.participant.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.caaers.webservice.participant.ParticipantType.Assignments;
import gov.nih.nci.cabig.caaers.webservice.participant.ParticipantType.Identifiers;

import java.util.ArrayList;
import java.util.List;


/**
 * This class has one public method which Converts a JAXB generated Participant Type object
 * to a Domain Object Participant Type as required by ParticipantMigrator.
 * @author Monish Dombla
 *
 */
public class ParticipantConverter {

	/**
	 * This method accepts a ParticipantDto which is a JAXB generated Participant Object
	 * and a Participant domain object. 
	 * It walks through the ParticipantDto object and prepares a Participant object 
	 * which is ParticipantMigrator Complaint.
	 * @param participantDto
	 * @param participant
	 */
	
	public void convertParticipantDtoToParticipantDomain(gov.nih.nci.cabig.caaers.webservice.participant.ParticipantType participantDto, Participant participant) throws CaaersSystemException{
		if(participant == null){
			participant = new Participant();
		}
		
		try{
			participant.setFirstName(participantDto.getFirstName());
			participant.setLastName(participantDto.getLastName());
			participant.setMiddleName(participantDto.getMiddleName());
			participant.setMaidenName(participantDto.getMaidenName());
			if(participantDto.getBirthDate() != null){
				DateValue dateOfBirth = new DateValue(participantDto.getBirthDate().getDay(),participantDto.getBirthDate().getMonth(),participantDto.getBirthDate().getYear());
				participant.setDateOfBirth(dateOfBirth);
			}else{
				if(participantDto.getBirthYear() != null){
					DateValue dateOfBirth = new DateValue(null,null,participantDto.getBirthYear().intValue());
					participant.setDateOfBirth(dateOfBirth);
				}
			}
			if(participantDto.getGender() != null){
				participant.setGender(participantDto.getGender().value());
			}
			if(participantDto.getRace() != null){
				participant.setRace(participantDto.getRace().value());
			}
			if(participantDto.getEthnicity() != null){
				participant.setEthnicity(participantDto.getEthnicity().value());
			}
			
			//Populate Identifiers
			populateIdentifiers(participantDto,participant);
			
			//Populate Assignments
			populateAssignments(participantDto,participant);
			
		}catch(Exception e){
			throw new CaaersSystemException("Exception while ParticipantDto Conversion",e);
		}
	}
	
	
	private void populateIdentifiers(gov.nih.nci.cabig.caaers.webservice.participant.ParticipantType participantDto, Participant participant) throws Exception{
		
		Identifiers identifiers = participantDto.getIdentifiers();
		
		if(identifiers != null){
			List<Identifier> identifierList = participant.getIdentifiers();
			List<OrganizationAssignedIdentifierType> orgAssignedIdList = identifiers.getOrganizationAssignedIdentifier();
			if(orgAssignedIdList != null && !orgAssignedIdList.isEmpty()){
				OrganizationAssignedIdentifier orgIdentifier;
				Organization organization = new Organization();
				for(OrganizationAssignedIdentifierType organizationAssignedIdentifierType : orgAssignedIdList){
					orgIdentifier = new OrganizationAssignedIdentifier();
					orgIdentifier.setType(organizationAssignedIdentifierType.getType().value());
					orgIdentifier.setValue(organizationAssignedIdentifierType.getValue());
					orgIdentifier.setPrimaryIndicator(organizationAssignedIdentifierType.isPrimaryIndicator());
					organization.setName(organizationAssignedIdentifierType.getOrganization().getName());
					organization.setNciInstituteCode(organizationAssignedIdentifierType.getOrganization().getNciInstituteCode());
					orgIdentifier.setOrganization(organization);
					identifierList.add(orgIdentifier);
				}
				participant.setIdentifiers(identifierList);
			}
		}
		
	}
	
	private void populateAssignments(gov.nih.nci.cabig.caaers.webservice.participant.ParticipantType participantDto, Participant participant) throws Exception{
		
		Assignments assignments = participantDto.getAssignments();
		StudyParticipantAssignment studyParticipantAssignment = null;
		StudySite studySite = null;
		Study study = null;
		Identifier identifier = null;
		Organization organization = null;
		
		if(assignments != null){
			List<StudyParticipantAssignment> assignmentList = new ArrayList<StudyParticipantAssignment>();
			for(AssignmentType assignmentType : assignments.getAssignment()){
				studyParticipantAssignment = new StudyParticipantAssignment();
				studyParticipantAssignment.setStudySubjectIdentifier(assignmentType.getStudySubjectIdentifier());
				
				studySite = new StudySite();
				study = new Study();
				identifier = new Identifier();
				organization = new Organization();
				
				identifier.setType(assignmentType.getStudySite().getStudy().getIdentifiers().getIdentifier().getType().value());
				identifier.setValue(assignmentType.getStudySite().getStudy().getIdentifiers().getIdentifier().getValue());
				study.addIdentifier(identifier);
				
				studySite.setStudy(study);
				
				organization.setName(assignmentType.getStudySite().getOrganization().getName());
				organization.setNciInstituteCode(assignmentType.getStudySite().getOrganization().getNciInstituteCode());
				studySite.setOrganization(organization);
				
				studyParticipantAssignment.setStudySite(studySite);
				assignmentList.add(studyParticipantAssignment);
			}
			participant.setAssignments(assignmentList);
		}
	}
}
