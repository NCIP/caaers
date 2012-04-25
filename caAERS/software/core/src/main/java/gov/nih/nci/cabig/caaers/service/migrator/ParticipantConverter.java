package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;
import gov.nih.nci.cabig.caaers.integration.schema.common.ParticipantIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.common.StudyIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.AssignmentType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.EthnicityType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.GenderType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantRef;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantType.Assignments;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantType.Identifiers;
import gov.nih.nci.cabig.caaers.integration.schema.participant.RaceType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ReducedIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.StudySiteType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.StudyType;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * This class has one public method which Converts a JAXB generated Participant Type object
 * to a Domain Object Participant Type as required by ParticipantMigrator.
 * @author Monish Dombla
 * @author Biju Joseph 
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
	
	public void convertParticipantDtoToParticipantDomain(ParticipantType participantDto, Participant participant) throws CaaersSystemException{
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
					DateValue dateOfBirth = new DateValue(null,participantDto.getBirthMonth().intValue(),participantDto.getBirthYear().intValue());
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
			convertIdentifierTypesToDomainIdentifiers(participantDto.getIdentifiers(),participant.getIdentifiers());
			
			//Populate Assignments
			convertAssignmentTypesToDomainAssignments(participantDto,participant);
			
		}catch(Exception e){
			throw new CaaersSystemException("Exception while ParticipantDto Conversion",e);
		}
	}
	
	public void convertParticipantRefToParticipantDomain(ParticipantRef participantDto, Participant participant) throws CaaersSystemException{
		if(participant == null){
			participant = new Participant();
		}
		
		try{
			//Populate Identifiers
			List<Identifier> identifierList = participant.getIdentifiers();
			List<OrganizationAssignedIdentifierType> orgAssignedIdList = participantDto.getIdentifiers().getOrganizationAssignedIdentifier();
			if(orgAssignedIdList != null && !orgAssignedIdList.isEmpty()){
				for(OrganizationAssignedIdentifierType organizationAssignedIdentifierType : orgAssignedIdList){
					identifierList.add(convertOrganizationIdentifierTypeToDomainIdentifier(organizationAssignedIdentifierType));
				}
			}
			
		}catch(Exception e){
			throw new CaaersSystemException("Exception while ParticipantDto Conversion",e);
		}
	}
	
	public void convertDomainParticipantToParticipantDto(Participant participant,ParticipantType participantType ){
		if(participantType == null) participantType = new ParticipantType();
		try{
			participantType.setFirstName(participant.getFirstName());
			participantType.setLastName(participant.getLastName());
			participantType.setMiddleName(participant.getMiddleName());
			participantType.setMaidenName(participant.getMaidenName());
			
			if(participant.getBirthDate() != null){
				DatatypeFactory dtf = DatatypeFactory.newInstance();
				XMLGregorianCalendar xgc = dtf.newXMLGregorianCalendar();
				if(participant.getDateOfBirth().getYear() != null){
					xgc.setYear(participant.getDateOfBirth().getYear());
				}
				if(participant.getDateOfBirth().getDay() != null){
					xgc.setDay(participant.getDateOfBirth().getDay()); 
				}
				if(participant.getDateOfBirth().getMonth() != null){
					xgc.setMonth(participant.getDateOfBirth().getMonth());
				}
				participantType.setBirthDate(xgc);
			}
			if(participant.getGender() != null){
				participantType.setGender(GenderType.fromValue(participant.getGender()));
			}
			if(participant.getRace() != null){
				participantType.setRace(RaceType.fromValue(participant.getRace()));
			}
			if(participant.getEthnicity() != null){
				participantType.setEthnicity(EthnicityType.fromValue(participant.getEthnicity()));
			}
			
			//Populate participant type Identifiers
			participantType.setIdentifiers(new Identifiers());
			convertDomainIdentifiersToParticipantIdentifierTypes(participant.getOrganizationIdentifiers(),participantType.getIdentifiers().getOrganizationAssignedIdentifier());
			
			//Populate Assignments
			convertDomainAssignementsToAssignmentTypes(participant, participantType);
			
		}catch(Exception e){
			throw new CaaersSystemException("Exception while ParticipantDto Conversion",e);
		}
	}
	
	private void convertDomainIdentifiersToParticipantIdentifierTypes(List<OrganizationAssignedIdentifier> domainIdentifiers, 
			List<gov.nih.nci.cabig.caaers.integration.schema.participant.OrganizationAssignedIdentifierType> identifiers) throws Exception{
		for(Identifier domainIdentifier : domainIdentifiers){
				identifiers.add(convertDomainIdentifierToParticipantIdentifierType((OrganizationAssignedIdentifier)domainIdentifier));
		}
	}

	private OrganizationAssignedIdentifierType convertDomainIdentifierToParticipantIdentifierType(OrganizationAssignedIdentifier organizationAssignedIdentifier) 
			throws Exception{
		gov.nih.nci.cabig.caaers.integration.schema.participant.OrganizationAssignedIdentifierType orgIdentifierType = 
				new gov.nih.nci.cabig.caaers.integration.schema.participant.OrganizationAssignedIdentifierType();
		OrganizationType orgType = new OrganizationType();
		orgType.setName(organizationAssignedIdentifier.getOrganization().getName());
		orgType.setNciInstituteCode(organizationAssignedIdentifier.getOrganization().getNciInstituteCode());
		orgIdentifierType.setOrganization(orgType);
		orgIdentifierType.setType(ParticipantIdentifierType.fromValue(organizationAssignedIdentifier.getType()));
		orgIdentifierType.setValue(organizationAssignedIdentifier.getValue());
		orgIdentifierType.setPrimaryIndicator(organizationAssignedIdentifier.getPrimaryIndicator());
	
		return orgIdentifierType;
	}
	
		
	/*private void convertDomainIdentifiersToIdentifierTypes( List<Identifier> identifiers, Identifiers identifierTypes) throws Exception{
		
		//BJ: fixed  	 CAAERS-2900
		if(identifierTypes != null){
			List<OrganizationAssignedIdentifierType> orgAssignedIdList = identifierTypes.getOrganizationAssignedIdentifier();
			if(orgAssignedIdList != null && !orgAssignedIdList.isEmpty()){
				for(OrganizationAssignedIdentifierType organizationAssignedIdentifierType : orgAssignedIdList){
					identifiers.add(convertOrganizationIdentifierTypeToDomainIdentifier(organizationAssignedIdentifierType));
				}
			}
		}
	}*/
	
	
	private void convertIdentifierTypesToDomainIdentifiers(Identifiers identifierTypes, List<Identifier> identifiers) throws Exception{
		
		//BJ: fixed  	 CAAERS-2900
		if(identifierTypes != null){
			List<OrganizationAssignedIdentifierType> orgAssignedIdList = identifierTypes.getOrganizationAssignedIdentifier();
			if(orgAssignedIdList != null && !orgAssignedIdList.isEmpty()){
				for(OrganizationAssignedIdentifierType organizationAssignedIdentifierType : orgAssignedIdList){
					identifiers.add(convertOrganizationIdentifierTypeToDomainIdentifier(organizationAssignedIdentifierType));
				}
			}
		}
	}
	
	private OrganizationAssignedIdentifier convertOrganizationIdentifierTypeToDomainIdentifier(OrganizationAssignedIdentifierType organizationAssignedIdentifierType) throws Exception{
		Organization organization = new LocalOrganization();
		OrganizationAssignedIdentifier orgIdentifier = new OrganizationAssignedIdentifier();
		orgIdentifier.setType(organizationAssignedIdentifierType.getType().value());
		orgIdentifier.setValue(organizationAssignedIdentifierType.getValue());
		orgIdentifier.setPrimaryIndicator(organizationAssignedIdentifierType.isPrimaryIndicator());
		organization.setName(organizationAssignedIdentifierType.getOrganization().getName());
		organization.setNciInstituteCode(organizationAssignedIdentifierType.getOrganization().getNciInstituteCode());
		orgIdentifier.setOrganization(organization);
		return orgIdentifier;
		}
			
	
	private void convertAssignmentTypesToDomainAssignments(ParticipantType participantDto, Participant participant) throws Exception{
		
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
				study = new LocalStudy();
				identifier = new Identifier();
				organization = new LocalOrganization();
				
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
	
	private void convertDomainAssignementsToAssignmentTypes(Participant participant, ParticipantType participantDto) throws Exception{
		
		participantDto.setAssignments(new Assignments());
		for(StudyParticipantAssignment domainAssignment : participant.getAssignments()){
			AssignmentType assigmentType = new AssignmentType();
			assigmentType.setStudySubjectIdentifier(domainAssignment.getStudySubjectIdentifier());
			StudyType studyType = new StudyType();
			studyType.setIdentifiers(new StudyType.Identifiers());
			for(OrganizationAssignedIdentifier orgAssignedIdentifier : domainAssignment.getStudySite().getStudy().getOrganizationAssignedIdentifiers()){
				studyType.getIdentifiers().setIdentifier(convertStudyDomainIdentifierToParticipantStudyIdentifierType(orgAssignedIdentifier));
			}
			
			StudySiteType studySiteType = new StudySiteType();
			OrganizationType orgType = new OrganizationType();
			
			orgType.setName(domainAssignment.getStudySite().getOrganization().getName());
			orgType.setNciInstituteCode(domainAssignment.getStudySite().getOrganization().getNciInstituteCode());
			studySiteType.setOrganization(orgType);
			studySiteType.setStudy(studyType);
			assigmentType.setStudySite(studySiteType);
			participantDto.getAssignments().getAssignment().add(assigmentType);
			
		}
	}
	
	private ReducedIdentifierType convertStudyDomainIdentifierToParticipantStudyIdentifierType(OrganizationAssignedIdentifier organizationAssignedIdentifier) 
			throws Exception{
		ReducedIdentifierType reducedIdentifier = new ReducedIdentifierType();
		reducedIdentifier.setType(StudyIdentifierType.fromValue(organizationAssignedIdentifier.getType()));
		reducedIdentifier.setValue(organizationAssignedIdentifier.getValue());
	
		return reducedIdentifier;
	}
}
