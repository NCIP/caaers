package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.Address;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.AdverseEventResponseDescription;
import gov.nih.nci.cabig.caaers.domain.AnatomicSite;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.DiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.EventStatus;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.MetastaticDiseaseSite;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.TimeValue;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReportingPeriodType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventResponseDescriptionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ContactMechanismType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DiseaseHistoryType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.MetastaticDiseaseSiteType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ParticipantType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ParticipantType.Identifiers;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.PhysicianType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReporterType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudyParticipantAssignmentType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudySiteType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudyType;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Ramakrishna Gundala
 */

public class ExpeditedAdverseEventReportConverter {
	AEReportAdverseEventConverter aeReportAdverseEventConverter = new AEReportAdverseEventConverter();
	
	  /** The Constant EMAIL. {@link #getContactMechanisms} key for the e-mail address */
    protected static final String EMAIL = "e-mail";

    /** The Constant FAX. {@link #getContactMechanisms} key for the fax number */
    protected static final String FAX = "fax";

    /** The Constant PHONE. {@link #getContactMechanisms} key for the phone number */
    protected static final String PHONE = "phone";
	Study dbStudy = null;

	public Study getDbStudy() {
		return dbStudy;
	}

	public void setDbStudy(Study dbStudy) {
		this.dbStudy = dbStudy;
	}

	public ExpeditedAdverseEventReport convert(
			AdverseEventReport xmlAdverseEventReport) {
		ExpeditedAdverseEventReport domainAdverseEventReport = new ExpeditedAdverseEventReport();
		domainAdverseEventReport.setCreatedAt(new Timestamp(xmlAdverseEventReport.getCreatedAt().toGregorianCalendar().getTimeInMillis()));
		if(xmlAdverseEventReport.getAdverseEventResponseDescription() != null){
			domainAdverseEventReport.setResponseDescription(convertToDomainAdverseEventResponseDescription(xmlAdverseEventReport.getAdverseEventResponseDescription()));
		}
		
		domainAdverseEventReport.setReportingPeriod(convertToDomainReportingPeriod(xmlAdverseEventReport.getAdverseEventReportingPeriod()));
		
		domainAdverseEventReport.setReporter(convertToDomainReporter(xmlAdverseEventReport.getReporter()));
		domainAdverseEventReport.setPhysician(convertToDomainPhysician(xmlAdverseEventReport.getPhysician()));
		
		if(xmlAdverseEventReport.getStudyParticipantAssignment() != null){
			domainAdverseEventReport.getReportingPeriod().setAssignment(convertToDomainStudyParticipantAssignment(xmlAdverseEventReport.getStudyParticipantAssignment()));
		}
		
		for(AdverseEventType xmlAdverseEventType : xmlAdverseEventReport.getAdverseEvent()){
			domainAdverseEventReport.addAdverseEvent(aeReportAdverseEventConverter.convertAdverseEventDtoToAdverseEventDomain(
					xmlAdverseEventType, dbStudy.getAeTerminology(), domainAdverseEventReport.getReportingPeriod().getAssignment().getStartDateOfFirstCourse()));
		}

		return domainAdverseEventReport;
	}
	
	protected DiseaseHistory convertToDomainDiseaseHistory(DiseaseHistoryType xmlDiseaseHistoryType){
		DiseaseHistory diseaseHistory = new DiseaseHistory();
		if (xmlDiseaseHistoryType.getMetastaticDiseaseSite() != null){
			for(MetastaticDiseaseSiteType metastaticDiseaseSite : xmlDiseaseHistoryType.getMetastaticDiseaseSite()){
				diseaseHistory.addMetastaticDiseaseSite(convertToDomainMestastaticDiseaseSite(metastaticDiseaseSite));
			}
		}
		
		if(xmlDiseaseHistoryType.getDiagnosisDate() != null){
			DateValue diagnosisDate = new DateValue();
			diagnosisDate.setDay(xmlDiseaseHistoryType.getDiagnosisDate().getDay());
			diagnosisDate.setMonth(xmlDiseaseHistoryType.getDiagnosisDate().getMonth());
			diagnosisDate.setYear(xmlDiseaseHistoryType.getDiagnosisDate().getYear());
			diagnosisDate.setZone(xmlDiseaseHistoryType.getDiagnosisDate().getZone());
			diseaseHistory.setDiagnosisDate(diagnosisDate);
		}
		
		return diseaseHistory;
	}
	
	protected MetastaticDiseaseSite convertToDomainMestastaticDiseaseSite(MetastaticDiseaseSiteType xmlMetastaticDiseaseSiteType){
		MetastaticDiseaseSite site = new MetastaticDiseaseSite();
		if (xmlMetastaticDiseaseSiteType.getAnatomicSite() != null){
			AnatomicSite anatomicSite = new AnatomicSite();
			anatomicSite.setName(xmlMetastaticDiseaseSiteType.getAnatomicSite().getName());
			anatomicSite.setCategory(xmlMetastaticDiseaseSiteType.getAnatomicSite().getCategory());
			site.setCodedSite(anatomicSite);
		}
		
		if(xmlMetastaticDiseaseSiteType.getOtherMetastaticDiseaseSite() != null){
			site.setOtherSite(xmlMetastaticDiseaseSiteType.getOtherMetastaticDiseaseSite());
		}
		
		return site;
	}
	
	
	
	protected AdverseEventResponseDescription convertToDomainAdverseEventResponseDescription(AdverseEventResponseDescriptionType xmlAdverseEventResponseDescriptionType){
		AdverseEventResponseDescription adverseEventResponseDescription = new AdverseEventResponseDescription();
		adverseEventResponseDescription.setEventDescription(xmlAdverseEventResponseDescriptionType.getEventDescription());
		if(xmlAdverseEventResponseDescriptionType.getRecoveryDate() != null){
			adverseEventResponseDescription.setRecoveryDate(xmlAdverseEventResponseDescriptionType.getRecoveryDate().toGregorianCalendar().getTime());
		}
		
		if(xmlAdverseEventResponseDescriptionType.getDateRemovedFromProtocol() != null){
			adverseEventResponseDescription.setDateRemovedFromProtocol(xmlAdverseEventResponseDescriptionType.getDateRemovedFromProtocol().toGregorianCalendar().getTime());
		}
		
		adverseEventResponseDescription.setRetreated(xmlAdverseEventResponseDescriptionType.isRetreated());
		
		if(xmlAdverseEventResponseDescriptionType.getPresentStatus() != null){
			adverseEventResponseDescription.setPresentStatus(PostAdverseEventStatus.valueOf(xmlAdverseEventResponseDescriptionType.getPresentStatus()));
		}
		if(xmlAdverseEventResponseDescriptionType.getEventAbate() != null){
			adverseEventResponseDescription.setEventAbate(EventStatus.valueOf(xmlAdverseEventResponseDescriptionType.getEventAbate()));
		}
		
		if(xmlAdverseEventResponseDescriptionType.getEventReappear() != null){
			adverseEventResponseDescription.setEventReappear(EventStatus.valueOf(xmlAdverseEventResponseDescriptionType.getEventReappear()));
		}
		
		if(xmlAdverseEventResponseDescriptionType.getPrimaryTreatmentApproximateTime() != null){
			TimeValue primaryTreatmentApproximateTime = new TimeValue();
			primaryTreatmentApproximateTime.setHour(xmlAdverseEventResponseDescriptionType.getPrimaryTreatmentApproximateTime().getHour());
			primaryTreatmentApproximateTime.setMinute(xmlAdverseEventResponseDescriptionType.getPrimaryTreatmentApproximateTime().getMinute());
			primaryTreatmentApproximateTime.setType(xmlAdverseEventResponseDescriptionType.getPrimaryTreatmentApproximateTime().getType());
		}
		
		return adverseEventResponseDescription;
	}
	
	protected Reporter convertToDomainReporter(ReporterType  xmlReporterType){
		Reporter reporter = new Reporter();
		reporter.setFirstName(xmlReporterType.getFirstName());
		reporter.setLastName(xmlReporterType.getLastName());
		if(xmlReporterType.getNciIdentifier() != null){
			ResearchStaff staff = new LocalResearchStaff();
			staff.setNciIdentifier(xmlReporterType.getNciIdentifier());
		}
		
		Address address = new Address();
		address.setStreet(xmlReporterType.getStreet());
		address.setCity(xmlReporterType.getCity());
		address.setCountry(xmlReporterType.getCountry());
		address.setState(xmlReporterType.getState());
		address.setZip(xmlReporterType.getZip());
		
		reporter.setAddress(address);
		if(xmlReporterType.getContactMechanism() != null){
			reporter.setEmailAddress(getEmail(xmlReporterType.getContactMechanism()));
			reporter.setPhoneNumber(getPhone(xmlReporterType.getContactMechanism()));
			reporter.setFax(getFax(xmlReporterType.getContactMechanism()));
		}
		
		return reporter;
		
	}
	
	
	protected Physician convertToDomainPhysician(PhysicianType  xmlPhysicianType){
		Physician physician = new Physician();
		physician.setFirstName(xmlPhysicianType.getFirstName());
		physician.setLastName(xmlPhysicianType.getLastName());
		if(xmlPhysicianType.getNciIdentifier() != null){
			Investigator investigator = new LocalInvestigator();
			investigator.setNciIdentifier(xmlPhysicianType.getNciIdentifier());
		}
		
		Address address = new Address();
		address.setStreet(xmlPhysicianType.getStreet());
		address.setCity(xmlPhysicianType.getCity());
		address.setCountry(xmlPhysicianType.getCountry());
		address.setState(xmlPhysicianType.getState());
		address.setZip(xmlPhysicianType.getZip());
		
		physician.setAddress(address);
		if(xmlPhysicianType.getContactMechanism() != null){
			physician.setEmailAddress(getEmail(xmlPhysicianType.getContactMechanism()));
			physician.setPhoneNumber(getPhone(xmlPhysicianType.getContactMechanism()));
			physician.setFax(getFax(xmlPhysicianType.getContactMechanism()));
		}
		
		
		return physician;
		
	}
	
	protected AdverseEventReportingPeriod convertToDomainReportingPeriod(AdverseEventReportingPeriodType xmlReportingPeriodType){
		AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
		if(xmlReportingPeriodType.getStudyParticipantAssignment() != null){
			reportingPeriod.setAssignment(convertToDomainStudyParticipantAssignment(xmlReportingPeriodType.getStudyParticipantAssignment()));
		} 
		
		if(xmlReportingPeriodType.getTreatmentAssignment() != null){
			TreatmentAssignment treatmentAssignment = new TreatmentAssignment();
			treatmentAssignment.setCode(xmlReportingPeriodType.getTreatmentAssignment().getCode());
			if(xmlReportingPeriodType.getTreatmentAssignment().getComments() != null){
				treatmentAssignment.setComments(xmlReportingPeriodType.getTreatmentAssignment().getComments());
			}
			treatmentAssignment.setDescription(xmlReportingPeriodType.getTreatmentAssignment().getDescription());
			treatmentAssignment.setDoseLevelOrder(xmlReportingPeriodType.getTreatmentAssignment().getDoseLevelOrder());
			
			reportingPeriod.setTreatmentAssignment(treatmentAssignment);
		}
		
		if(xmlReportingPeriodType.getStartDate() != null){
			reportingPeriod.setStartDate(xmlReportingPeriodType.getStartDate().toGregorianCalendar().getTime());
		}
		
		if(xmlReportingPeriodType.getCycleNumber() != null){
		//	reportingPeriod.setCycleNumber(xmlReportingPeriodType.getCycleNumber());
		}
		
		
		return reportingPeriod;
		
	}
	
	protected StudyParticipantAssignment convertToDomainStudyParticipantAssignment(StudyParticipantAssignmentType xmlAssignmentType) {
		StudyParticipantAssignment assignment=  new StudyParticipantAssignment();
		assignment.setStudySubjectIdentifier(xmlAssignmentType.getStudySubjectIdentifier());
		
		assignment.setStudySite(convertToDomainStudySite(xmlAssignmentType.getStudySite()));
		assignment.setDateOfEnrollment(xmlAssignmentType.getDateOfEnrollment().toGregorianCalendar().getTime());
		if(xmlAssignmentType.getStartDateOfFirstCourse() != null){
			assignment.setStartDateOfFirstCourse(xmlAssignmentType.getStartDateOfFirstCourse().toGregorianCalendar().getTime());
		}

		return assignment;
	}
	
	protected Participant convertToDomainParticipant(ParticipantType xmlParticipantType){
		Participant participant = new Participant();
		try{
			participant.setFirstName(xmlParticipantType.getFirstName());
			participant.setLastName(xmlParticipantType.getLastName());
			participant.setMiddleName(xmlParticipantType.getMiddleName());
			participant.setMaidenName(xmlParticipantType.getMaidenName());
			if(xmlParticipantType.getBirthDate() != null){
				DateValue dateOfBirth = new DateValue(xmlParticipantType.getBirthDate().getDay(),xmlParticipantType.getBirthDate().getMonth(),xmlParticipantType.getBirthDate().getYear());
				participant.setDateOfBirth(dateOfBirth);
			}else{
				if(xmlParticipantType.getBirthYear() != null){
					DateValue dateOfBirth = new DateValue(null,xmlParticipantType.getBirthMonth().intValue(),xmlParticipantType.getBirthYear().intValue());
					participant.setDateOfBirth(dateOfBirth);
				}
			}
			if(xmlParticipantType.getGender() != null){
				participant.setGender(xmlParticipantType.getGender().value());
			}
			if(xmlParticipantType.getRace() != null){
				participant.setRace(xmlParticipantType.getRace().value());
			}
			if(xmlParticipantType.getEthnicity() != null){
				participant.setEthnicity(xmlParticipantType.getEthnicity().value());
			}
			
			//Populate Identifiers
			convertIdentifierTypesToDomainIdentifiers(xmlParticipantType.getIdentifiers(),participant.getIdentifiers());
			
		}catch(Exception e){
			throw new CaaersSystemException("Exception while converting XML participant to domain participant",e);
		}
		
		return participant;
	}
	
	protected void convertIdentifierTypesToDomainIdentifiers(Identifiers identifierTypes, List<Identifier> identifiers) throws Exception{
		
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
	
	protected OrganizationAssignedIdentifier convertOrganizationIdentifierTypeToDomainIdentifier(OrganizationAssignedIdentifierType organizationAssignedIdentifierType) throws Exception{
		Organization organization = new LocalOrganization();
		OrganizationAssignedIdentifier orgIdentifier = new OrganizationAssignedIdentifier();
		orgIdentifier.setType(organizationAssignedIdentifierType.getType().value());
		orgIdentifier.setValue(organizationAssignedIdentifierType.getValue());
		orgIdentifier.setPrimaryIndicator(organizationAssignedIdentifierType.isPrimaryIndicator());
	//	organization.setName(organizationAssignedIdentifierType.getOrganizationRef().getName());
		organization.setNciInstituteCode(organizationAssignedIdentifierType.getOrganizationRef().getNciInstituteCode());
		orgIdentifier.setOrganization(organization);
		return orgIdentifier;
		}
	
	protected StudySite convertToDomainStudySite(StudySiteType xmlStudySiteType) {
		StudySite studySite =  new StudySite();
		
		studySite.setOrganization(convertToDomainOrganization(xmlStudySiteType.getOrganization()));
		studySite.setStudy(convertToDomainStudy(xmlStudySiteType.getStudy()));

		return studySite;
	}
	
	protected Organization convertToDomainOrganization(OrganizationType xmlOrganizationType){
		
		LocalOrganization organization = new LocalOrganization();
		organization.setName(xmlOrganizationType.getName());
		organization.setNciInstituteCode(xmlOrganizationType.getNciInstituteCode());
		
		return organization;
	}
	
	protected Study convertToDomainStudy(StudyType xmlStudyType) {
		Study study =  new LocalStudy();
		Identifier identifier = new Identifier();
		
		identifier.setType(xmlStudyType.getIdentifiers().getIdentifier().getType().value());
		identifier.setValue(xmlStudyType.getIdentifiers().getIdentifier().getValue());
		study.addIdentifier(identifier);

		return study;
	}
	
	protected String getEmail(List<ContactMechanismType> contactMechanisms){
		for(ContactMechanismType cm : contactMechanisms){
			if(cm.getType().equals(EMAIL)){
				return cm.getValue();
			}
		}
		
		return null;
	}
	
	protected String getPhone(List<ContactMechanismType> contactMechanisms){
		for(ContactMechanismType cm : contactMechanisms){
			if(cm.getType().equals(PHONE)){
				return cm.getValue();
			}
		}
		
		return null;
	}
	
	
	protected String getFax(List<ContactMechanismType> contactMechanisms){
		for(ContactMechanismType cm : contactMechanisms){
			if(cm.getType().equals(FAX)){
				return cm.getValue();
			}
		}
		
		return null;
	}


}
