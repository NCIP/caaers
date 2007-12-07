package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.AbstractIdentifiableDomainObject;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public abstract class AbstractImportServiceImpl {
	
	private OrganizationDao organizationDao;
	private StudyDao studyDao;
	protected final Log log = LogFactory.getLog(getClass());
	
	
	/*
	 * This is common for participant and study
	 */
	protected void migrateIdentifiers(
			AbstractIdentifiableDomainObject destination,
			AbstractIdentifiableDomainObject source,
			DomainObjectImportOutcome studyImportOutcome) {

		// Identifiers
		if (source.getIdentifiers() != null) {
			for (int i = 0; i < source.getIdentifiers().size(); i++) {
				Identifier identifier = (Identifier) source.getIdentifiers()
						.get(i);
				if (identifier instanceof OrganizationAssignedIdentifier) {
						Organization organization = getOrganization(((OrganizationAssignedIdentifier) identifier).getOrganization().getName());
						((OrganizationAssignedIdentifier) identifier).setOrganization(organization);
						
						if (identifier.getType().equals(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE)){
							//identifier.setPrimaryIndicator(false);
						}
						if (source instanceof Study && identifier.getType().equals(OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE)){
						//StudyCoordinatingCenter studyCoordinatingCenter = new StudyCoordinatingCenter();
						//studyCoordinatingCenter.setOrganization(organization);
						//((Study)destination).addStudyOrganization(studyCoordinatingCenter);
						}
				}

				if (identifier instanceof SystemAssignedIdentifier) {
					// I don't need to do anything i think
				}
				destination.getIdentifiers().add(identifier);
			}
		}
		ifNullOrEmptyList(destination.getIdentifiers(),studyImportOutcome,Severity.ERROR, "Identifiers are either Empty or Not Valid");
	}
	
	
	//	Helpers
	protected Organization getOrganization(String organizationName){
		Organization org = organizationDao.getByName(organizationName);
		return org;
	}
	
	protected void ifNullObject(Object domainObject, DomainObjectImportOutcome importOutcome, Severity severity){
		if(domainObject == null){
			importOutcome.addErrorMessage(" is required or has errors", severity);
		}
	}
	
	protected void ifNullObject(Object domainObject, DomainObjectImportOutcome importOutcome, Severity severity, String message){
		if(domainObject == null){
			importOutcome.addErrorMessage(message, severity);
		}
	}
	
	protected void ifNullOrEmptyList(List list, DomainObjectImportOutcome studyImportOutcome, Severity severity){
		if(list.isEmpty()){
			studyImportOutcome.addErrorMessage("is required or has errors",severity);
		}
	}
	
	protected void ifNullOrEmptyList(List list, DomainObjectImportOutcome studyImportOutcome, Severity severity,String message){
		if(list.isEmpty()){
			studyImportOutcome.addErrorMessage(message,severity);
		}
	}
	
	protected void errorInBusinessLogic(DomainObjectImportOutcome importOutcome, Severity severity, String message){
		importOutcome.addErrorMessage(message , severity);
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
