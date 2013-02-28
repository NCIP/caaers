/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReportingPeriodType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ContactMechanismType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.EthnicityType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.GenderType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OrganizationRefType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ParticipantType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.PhysicianType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.RaceType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReducedIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReporterType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudyParticipantAssignmentType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudySiteType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudyType;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;
import gov.nih.nci.cabig.caaers.integration.schema.common.ParticipantIdentifierType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class ExpeditedAdverseEventReportConverterTest extends CaaersDbTestCase {
	
	private ExpeditedAdverseEventReportConverter converter = (ExpeditedAdverseEventReportConverter) getApplicationContext().getBean("eaeConverter");
	private AdverseEventReport aeReport =null;
	
	protected static final Calendar BIRTH_DATE = new GregorianCalendar(1921, 05, 27);
	protected static final String STUDY_IDENTIFIER = "STUDY-001";
	protected static final String STUDY_SUBJECT_IDENTIFIER = "STU-SUB-001";
	protected static final String SUBJECT_IDENTIFIER = "SUB-001";
	protected static final String ORG_NCI_CODE= "NC010";
	protected static final String ORG_NAME= "Duke University";
	
	protected XMLGregorianCalendar getXMLDate(Date date) throws DatatypeConfigurationException {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		return date2;
	}
	
	 protected AdverseEventReport createExpeditedReport() {
		AdverseEventReport report = new AdverseEventReport();
	 	report = new AdverseEventReport();
	 	Date date = new Date();
		try {
			report.setCreatedAt(getXMLDate(date));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		report.setAdverseEventReportingPeriod(createReportingPeriod());
	 	report.setReporter(createReporter(new ReporterType()));
	 	report.setPhysician(createPhysician(new PhysicianType()));
        return report;
    }
	 
	 protected AdverseEventReportingPeriodType createReportingPeriod() {
		AdverseEventReportingPeriodType reportingPeriod = new AdverseEventReportingPeriodType();
		reportingPeriod.setCycleNumber(1);
		Date date = new Date();
		try {
			reportingPeriod.setStartDate(getXMLDate(date));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		reportingPeriod.setStudyParticipantAssignment(createStudyParticipantAssignmentType());
        return reportingPeriod;
	    }
	 
	 protected StudyParticipantAssignmentType createStudyParticipantAssignmentType(){
		 StudyParticipantAssignmentType studyParticipantAssignmentType = new StudyParticipantAssignmentType();
		 studyParticipantAssignmentType.setStudySite(createStudySiteType());
		 studyParticipantAssignmentType.setParticipant(createParticipantType());
		 try {
			studyParticipantAssignmentType.setDateOfEnrollment(getXMLDate(new Date()));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		 
		 studyParticipantAssignmentType.setStudySubjectIdentifier(STUDY_SUBJECT_IDENTIFIER);
		 
		 return studyParticipantAssignmentType;
	 }
	 
	 protected StudySiteType createStudySiteType(){
		 StudySiteType studySiteType = new StudySiteType();
		 studySiteType.setStudy(createStudyType());
		 studySiteType.setOrganization(createOrganizationType(ORG_NAME, ORG_NCI_CODE));
		 
		 return studySiteType;
	 }
	 
	 protected OrganizationType createOrganizationType(String name, String nciCode ){
		 OrganizationType organizationType = new OrganizationType();
		 organizationType.setName(name);
		 organizationType.setNciInstituteCode(nciCode);
		 
		 return organizationType;
	 }
	 
	 protected StudyType createStudyType(){
		 StudyType studyType = new StudyType();
		 
		 ReducedIdentifierType identifier = new ReducedIdentifierType();
		 identifier.setType(gov.nih.nci.cabig.caaers.integration.schema.common.StudyIdentifierType.PROTOCOL_AUTHORITY_IDENTIFIER);
		 identifier.setValue(STUDY_IDENTIFIER);
		 
		 StudyType.Identifiers identifiers = new StudyType.Identifiers();
		 identifiers.setIdentifier(identifier);
		 
		 studyType.setIdentifiers(identifiers);
		 
		 
		 return studyType;
	 }
	 
	 protected ParticipantType createParticipantType(){
		 ParticipantType participantType = new ParticipantType();
		 try {
			participantType.setBirthDate(getXMLDate(BIRTH_DATE.getTime()));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		 
		 participantType.setEthnicity(EthnicityType.NOT_HISPANIC_OR_LATINO);
		 participantType.setFirstName("John");
		 participantType.setLastName("Doe");
		 participantType.setRace(RaceType.NOT_REPORTED);
		 participantType.setGender(GenderType.UNKNOWN);
		 
		 OrganizationAssignedIdentifierType orgId = new OrganizationAssignedIdentifierType();
		 OrganizationRefType orgRefType = new OrganizationRefType();
		 orgRefType.setName(ORG_NAME);
		 orgRefType.setNciInstituteCode(ORG_NCI_CODE);
		 orgId.setOrganizationRef(orgRefType);
		 orgId.setPrimaryIndicator(true);
		 orgId.setType(ParticipantIdentifierType.MRN);
		 orgId.setValue(SUBJECT_IDENTIFIER);
		 
		 List<OrganizationAssignedIdentifierType> orgIds = new ArrayList<OrganizationAssignedIdentifierType>();
		 orgIds.add(orgId);
		 
		 ParticipantType.Identifiers identifiers = new ParticipantType.Identifiers();
		 identifiers.setOrganizationAssignedIdentifier(orgIds);
		 
		 participantType.setIdentifiers(identifiers);
		 
		 return participantType;
	 }
	 
	 
	 protected ReporterType createReporter(final ReporterType person) {
        person.setFirstName("Alfred");
        person.setLastName("Nobel");
        ContactMechanismType cm = new ContactMechanismType();
        cm.setType("e-mail");
        cm.setValue("jmarshall@gmail.ire");
        person.getContactMechanism().add(cm);
        return person;
	    }
	 
	 protected PhysicianType createPhysician(final PhysicianType person) {
        person.setFirstName("Frank");
        person.setLastName("Just Frank");
        ContactMechanismType cm = new ContactMechanismType();
        cm.setType("phone");
        cm.setValue("712-123-9081");
        person.getContactMechanism().add(cm);
        return person;
	    }
	
	protected void setUp() throws Exception {
		super.setUp();
		
		aeReport = createExpeditedReport();
	
	}
	
	public void testConvertReport() throws Exception{
		ExpeditedAdverseEventReport domainReport = converter.convert(aeReport);
		
		assertNotNull(domainReport.getReportingPeriod());
		assertNotNull(domainReport.getReporter());
		assertNotNull(domainReport.getPhysician());
		assertNotNull(domainReport.getReportingPeriod().getAssignment());
		assertNotNull(domainReport.getReportingPeriod().getAssignment().getParticipant());
		assertNotNull(domainReport.getReportingPeriod().getAssignment().getStudySite());
		assertNotNull(domainReport.getReportingPeriod().getAssignment().getStudySite().getStudy());
		
		assertEquals("wrong physician first name","Frank", domainReport.getPhysician().getFirstName());
		assertEquals("wrong physician phone","712-123-9081", domainReport.getPhysician().getPhoneNumber());
		assertEquals("wrong reporter last name","Nobel", domainReport.getReporter().getLastName());
		assertEquals("wrong reporter email","jmarshall@gmail.ire", domainReport.getReporter().getEmailAddress());
		
		assertEquals("wrong study identifier",STUDY_IDENTIFIER, domainReport.getReportingPeriod().getAssignment().
				getStudySite().getStudy().getIdentifiers().get(0).getValue());
		assertEquals("wrong study short title","dummy", domainReport.getReportingPeriod().getAssignment().
				getStudySite().getStudy().getShortTitle());
		assertEquals("wrong subject identifier",SUBJECT_IDENTIFIER, domainReport.getReportingPeriod().getAssignment()
				.getParticipant().getIdentifiers().get(0).getValue());
		assertEquals("wrong study site",ORG_NCI_CODE, domainReport.getReportingPeriod().getAssignment().getStudySite().getOrganization().getNciInstituteCode());
		assertEquals("wrong participant birth date", BIRTH_DATE.getTime(),  domainReport.getReportingPeriod().getAssignment()
				.getParticipant().getDateOfBirth().toDate());
		
		
	}
}

