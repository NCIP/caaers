package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.AbstractIdentifiableDomainObject;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.util.List;


public abstract class AbstractImportServiceImpl {
	
	private OrganizationDao organizationDao;
	private StudyDao studyDao;
	
	
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
						if (identifier.getType().equals("Sponsor Identifier")){
							identifier.setPrimaryIndicator(false);
						}
						if (source instanceof Study){
						StudyCoordinatingCenter studyCoordinatingCenter = new StudyCoordinatingCenter();
						studyCoordinatingCenter.setOrganization(organization);
						((Study)destination).addStudyOrganization(studyCoordinatingCenter);
						}
				}

				if (identifier instanceof SystemAssignedIdentifier) {
					// I don't need to do anything i think
				}
				destination.getIdentifiers().add(identifier);
			}
		}
		ifNullOrEmptyList(source.getIdentifiers(),studyImportOutcome,Severity.ERROR);
	}
	
	
	//	Helpers
	protected Organization getOrganization(String organizationName){
		Organization org = organizationDao.getByName(organizationName);
		return org;
	}
	
	protected void ifNullObject(DomainObject domainObject, DomainObjectImportOutcome studyImportOutcome, Severity severity){
		if(domainObject == null){
			studyImportOutcome.addErrorMessage(domainObject.getClass().getSimpleName() + "is required or has errors", severity);
		}
	}
	
	protected void ifNullOrEmptyList(List list, DomainObjectImportOutcome studyImportOutcome, Severity severity){
		if(list.isEmpty()){
			studyImportOutcome.addErrorMessage("is required or has errors",severity);
		}
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
