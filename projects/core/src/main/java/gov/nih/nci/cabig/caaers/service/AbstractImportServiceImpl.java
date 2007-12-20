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
				Identifier identifier = (Identifier) source.getIdentifiers().get(i);
				if (identifier instanceof OrganizationAssignedIdentifier) {
					Organization organization = getOrganization(((OrganizationAssignedIdentifier) identifier).getOrganization().getName());
					((OrganizationAssignedIdentifier) identifier).setOrganization(organization);	
				}
				if (identifier instanceof SystemAssignedIdentifier) {
					// I don't need to do anything i think
				}
				destination.getIdentifiers().add(identifier);
			}
		}
		ifNullOrEmptyList(destination.getIdentifiers(),studyImportOutcome,Severity.ERROR, "Identifiers are either Empty or Not Valid");
	}
	
	/*
	 * This is common for participant and study , make the first instance of primary indicators 
	 * hold and set the rest to false
	 * 
	 */
	protected void firstPrimaryIndicatorInIdentifiers(
			AbstractIdentifiableDomainObject destination,
			DomainObjectImportOutcome studyImportOutcome) {

		boolean isPrimaryIndicatorAvailable = Boolean.FALSE;
		for (Identifier identifier : destination.getIdentifiers()) {
			if (identifier.getPrimaryIndicator() && !isPrimaryIndicatorAvailable ) {
				isPrimaryIndicatorAvailable = Boolean.TRUE;
			}else{
				identifier.setPrimaryIndicator(Boolean.FALSE);
			}
			
		}
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
