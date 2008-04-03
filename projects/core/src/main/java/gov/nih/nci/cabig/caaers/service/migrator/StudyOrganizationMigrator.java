package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.domain.CoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.FundingSponsor;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

public class StudyOrganizationMigrator implements Migrator<Study>{
	
	  private OrganizationDao organizationDao;
	  private SiteInvestigatorDao siteInvestigatorDao;
	  private ResearchStaffDao researchStaffDao;
	  
	/**
	 * This method will copy the {@link StudyOrganization}s from source to destination
	 */
	public void migrate(Study source, Study destination,DomainObjectImportOutcome<Study> outcome) {
		
		//migrate funding sponsor
		migrateFundingSponsor(source, destination, outcome);
        
        //migrate coordinating center
		migrateCoordinatingCenter(source, destination, outcome);
		
        //migrate studyOrganization.
		for (StudyOrganization studyOrganization : source.getStudyOrganizations()) {
			String orgName = studyOrganization.getOrganization().getName();
	        Organization organization = organizationDao.getByName(orgName);
	        studyOrganization.setOrganization(organization);

	        // Migrate Study investigators and Study Personnels
	        migrateStudyInvestigators(studyOrganization, organization, outcome);
	        migrateStudyPersonnels(studyOrganization, organization, outcome);

	        if (studyOrganization instanceof StudySite) {
	        	destination.addStudySite((StudySite) studyOrganization);
	        } else if (studyOrganization instanceof StudyFundingSponsor) {
	            destination.addStudyFundingSponsor((StudyFundingSponsor) studyOrganization);
	        }
		}
		
	}
	
	private void migrateFundingSponsor(Study source, Study destination, DomainObjectImportOutcome<Study> outcome ){
		FundingSponsor sponsor = source.getFundingSponsor();
		if(sponsor == null) return;
		
		
		StudyFundingSponsor studySponsor = sponsor.getStudyFundingSponsor();
		String orgName = studySponsor.getOrganization().getName();
		Organization organization = organizationDao.getByName(orgName);
		outcome.ifNullObject(organization, DomainObjectImportOutcome.Severity.ERROR, 
				"The organization specified in fundingSponsor is invalid");
		studySponsor.setOrganization(organization);
		
		OrganizationAssignedIdentifier orgIdentifier = sponsor.getOrganizationAssignedIdentifier();
		orgIdentifier.setType(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE);
		orgIdentifier.setOrganization(organization);
        
        //	Migrate Study investigators and Study Personnels
        migrateStudyInvestigators(studySponsor, organization, outcome);
        migrateStudyPersonnels(studySponsor, organization, outcome);
        
        destination.getIdentifiers().add(orgIdentifier);
        destination.addStudyFundingSponsor(studySponsor);
		
	}
	
	private void migrateCoordinatingCenter(Study source, Study destination, DomainObjectImportOutcome<Study> outcome ){
		CoordinatingCenter coCenter = source.getCoordinatingCenter();
		if(coCenter == null) return;
		
		StudyCoordinatingCenter studyCoordinatingCenter = coCenter.getStudyCoordinatingCenter();
		String orgName = studyCoordinatingCenter.getOrganization().getName();
		Organization organization = organizationDao.getByName(orgName);
		outcome.ifNullObject(organization, DomainObjectImportOutcome.Severity.ERROR, "The organization specified in coordinatingCenter is invalid");
		studyCoordinatingCenter.setOrganization(organization);
		
		OrganizationAssignedIdentifier orgIdentifier = coCenter.getOrganizationAssignedIdentifier();
		orgIdentifier.setType(OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE);
		orgIdentifier.setOrganization(organization);
        
        //	Migrate Study investigators and Study Personnels
        migrateStudyInvestigators(studyCoordinatingCenter, organization, outcome);
        migrateStudyPersonnels(studyCoordinatingCenter, organization, outcome);
        
        destination.getIdentifiers().add(orgIdentifier);
        destination.addStudyOrganization(studyCoordinatingCenter);
		
	}	
 

    private void migrateStudyInvestigators(StudyOrganization studyOrganization, Organization organization, DomainObjectImportOutcome studyImportOutcome) {

        for (StudyInvestigator studyInvestigator : studyOrganization.getStudyInvestigators()) {

            Investigator investigator = studyInvestigator.getSiteInvestigator().getInvestigator();
            // TODO  : search should be done on something else too
            String[] investigatorFirstAndLast = {investigator.getFirstName(), investigator.getLastName()};
            List<SiteInvestigator> siteInvestigators = siteInvestigatorDao.getBySubnames(investigatorFirstAndLast, organization.getId());
            if (siteInvestigators.size() > 0) {
                studyInvestigator.setSiteInvestigator(siteInvestigators.get(0));
                studyInvestigator.setStudyOrganization(studyOrganization);
            } else {
                //studyOrganization.getStudyInvestigators().remove(studyInvestigator);
                studyImportOutcome.ifNullObject(null, DomainObjectImportOutcome.Severity.ERROR, "The selected investigator " +
                        investigator.getFirstName() + " " + investigator.getLastName() + " is not Valid ");
            }
        }
    }


    private void migrateStudyPersonnels(StudyOrganization studyOrganization,
                                        Organization organization, DomainObjectImportOutcome<Study> studyImportOutcome) {

        for (StudyPersonnel studyPersonnel : studyOrganization.getStudyPersonnels()) {
            ResearchStaff researchStaffer = studyPersonnel.getResearchStaff();
            // TODO : search should be done on something else too
            String[] investigatorFirstAndLast = {researchStaffer.getFirstName(), researchStaffer.getLastName()};
            List<ResearchStaff> researchStaffs = researchStaffDao.getBySubnames(investigatorFirstAndLast, organization.getId());

            if (researchStaffs.size() > 0) {
                ResearchStaff researchStaff = researchStaffs.get(0);
                studyPersonnel.setResearchStaff(researchStaff);
                studyPersonnel.setStudyOrganization(studyOrganization);
            } else {
                studyImportOutcome.ifNullObject(null, DomainObjectImportOutcome.Severity.ERROR, "The selected personnel " +
                        researchStaffer.getFirstName() + " " + researchStaffer.getLastName() + " is not Valid ");
            }
        }
    } 
    
    @Required
    public void setSiteInvestigatorDao(final SiteInvestigatorDao siteInvestigatorDao) {
        this.siteInvestigatorDao = siteInvestigatorDao;
    }
    
    @Required
    public void setResearchStaffDao(final ResearchStaffDao researchStaffDao) {
        this.researchStaffDao = researchStaffDao;
    }
    
    @Required
    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }
}
