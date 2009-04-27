package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.List;

public class StudyOrganizationSynchronizer implements Migrator<gov.nih.nci.cabig.caaers.domain.Study>{

	public void migrate(Study dbStudy, Study xmlStudy,
			DomainObjectImportOutcome<Study> outcome) {
		
		
		//migrate funding sponsor
		syncFundingSponsor(dbStudy, xmlStudy, outcome);
        
        //migrate coordinating center
		syncCoordinatingCenter(dbStudy, xmlStudy, outcome);
		
        //migrate studyOrganization.
		syncStudySite(dbStudy, xmlStudy, outcome);
		
	}
	
	
	private void syncStudySite(Study dbStudy, Study xmlStudy,DomainObjectImportOutcome<Study> outcome) {
		
		List<StudySite> newStudySiteList = new ArrayList<StudySite>();
		
		//Identify New StudySites .
		for(StudySite xmlStudySite : xmlStudy.getStudySites()){
			for(StudySite dbStudySite : dbStudy.getStudySites()){
				
				String xmlNciInstituteCode = xmlStudySite.getOrganization().getNciInstituteCode();
				String dbNciInstituteCode = dbStudySite.getOrganization().getNciInstituteCode();
				String xmlOrgName = xmlStudySite.getOrganization().getName();
				String dbOrgName = dbStudySite.getOrganization().getName();
				
				xmlStudySite.setId(dbStudySite.getId());
				if(xmlNciInstituteCode != null && dbNciInstituteCode != null){
					if(xmlNciInstituteCode.equals(dbNciInstituteCode)){
						syncStudyInvestigators(dbStudySite,xmlStudySite,dbStudySite.getOrganization(),outcome);
						syncStudyPersonnels(dbStudySite,xmlStudySite,dbStudySite.getOrganization(),outcome);
						break;
					}else{
						xmlStudySite.setId(null);
					}
				}else{
					if(xmlOrgName.equals(dbOrgName)){
						syncStudyInvestigators(dbStudySite,xmlStudySite,dbStudySite.getOrganization(),outcome);
						syncStudyPersonnels(dbStudySite,xmlStudySite,dbStudySite.getOrganization(),outcome);
						break;
					}else{
						xmlStudySite.setId(null);
					}
				}
			}
			if(xmlStudySite.getId() == null){
				newStudySiteList.add(xmlStudySite);
			}
		}
		
		//Add New StudySites
		for(StudySite newStudySite : newStudySiteList){
			dbStudy.getStudySites().add(newStudySite);
		}
		
		for(StudySite studySite : dbStudy.getStudySites()){
			studySite.setStudy(dbStudy);
		}
	}
	
	private void syncFundingSponsor(Study dbStudy, Study xmlStudy, DomainObjectImportOutcome<Study> outcome ){
		
		if(dbStudy.getFundingSponsor() != null && xmlStudy.getFundingSponsor() != null){
			syncStudyInvestigators(dbStudy.getFundingSponsor().getStudyFundingSponsor(),
					   xmlStudy.getFundingSponsor().getStudyFundingSponsor(),
					   dbStudy.getFundingSponsor().getStudyFundingSponsor().getOrganization(),
					   outcome);

			syncStudyPersonnels(dbStudy.getFundingSponsor().getStudyFundingSponsor(),
				   xmlStudy.getFundingSponsor().getStudyFundingSponsor(),
				   dbStudy.getFundingSponsor().getStudyFundingSponsor().getOrganization(),
				   outcome);
		}
		
	}
	
	private void syncCoordinatingCenter(Study dbStudy, Study xmlStudy, DomainObjectImportOutcome<Study> outcome ){
		
		if(dbStudy.getCoordinatingCenter() != null && xmlStudy.getCoordinatingCenter() != null){
			syncStudyInvestigators(dbStudy.getCoordinatingCenter().getStudyCoordinatingCenter(),
					   xmlStudy.getCoordinatingCenter().getStudyCoordinatingCenter(),
					   dbStudy.getCoordinatingCenter().getStudyCoordinatingCenter().getOrganization(),
					   outcome);

		   syncStudyPersonnels(dbStudy.getCoordinatingCenter().getStudyCoordinatingCenter(),
		   xmlStudy.getCoordinatingCenter().getStudyCoordinatingCenter(),
		   dbStudy.getCoordinatingCenter().getStudyCoordinatingCenter().getOrganization(),
		   outcome);
		}
	}
	
	private void syncStudyInvestigators(StudyOrganization dbStudyOrganization,
										StudyOrganization xmlStudyOrganization,
										Organization organization, 
										DomainObjectImportOutcome<Study> studyImportOutcome) {
		
		List<StudyInvestigator> newStudyInvestigatorList = new ArrayList<StudyInvestigator>();
		
		//Identify newly added StudyInvestigators
		for(StudyInvestigator xmlStudyInvestigator : xmlStudyOrganization.getStudyInvestigators()){
			for(StudyInvestigator dbStudyInvestigator : dbStudyOrganization.getStudyInvestigators()){
				
				String xmlNciIdentifier = xmlStudyInvestigator.getSiteInvestigator().getInvestigator().getNciIdentifier();
				String dbNciIdentifier = dbStudyInvestigator.getSiteInvestigator().getInvestigator().getNciIdentifier();
				String xmlFName = xmlStudyInvestigator.getSiteInvestigator().getInvestigator().getFirstName();
				String dbFName = dbStudyInvestigator.getSiteInvestigator().getInvestigator().getFirstName();
				String xmlLName = xmlStudyInvestigator.getSiteInvestigator().getInvestigator().getLastName();
				String dbLName = dbStudyInvestigator.getSiteInvestigator().getInvestigator().getLastName();
				
				xmlStudyInvestigator.setId(dbStudyInvestigator.getId());
				if(xmlNciIdentifier != null && dbNciIdentifier != null) {
					if(xmlNciIdentifier.equals(dbNciIdentifier)){
						dbStudyInvestigator.setRoleCode(xmlStudyInvestigator.getRoleCode());
						dbStudyInvestigator.setStatusCode(xmlStudyInvestigator.getStatusCode());
						break;
					}else{
						xmlStudyInvestigator.setId(null);
					}
				}else{
					if(xmlFName.equals(dbFName) && xmlLName.equals(dbLName)){
						dbStudyInvestigator.setRoleCode(xmlStudyInvestigator.getRoleCode());
						dbStudyInvestigator.setStatusCode(xmlStudyInvestigator.getStatusCode());
						break;
					}else{
						xmlStudyInvestigator.setId(null);
					}
				}
			}
			if(xmlStudyInvestigator.getId() == null){
				newStudyInvestigatorList.add(xmlStudyInvestigator);
			}
		}
		
		//Add New StudyInvestigators
		for(StudyInvestigator newStudyInvestigator : newStudyInvestigatorList){
			dbStudyOrganization.getStudyInvestigators().add(newStudyInvestigator);
		}
		
		for(StudyInvestigator studyInvestigator : dbStudyOrganization.getStudyInvestigators()){
			studyInvestigator.setStudyOrganization(dbStudyOrganization);
		}
		
	}//end method
	
	private void syncStudyPersonnels(StudyOrganization dbStudyOrganization,
										StudyOrganization xmlStudyOrganization,
										Organization organization, 
										DomainObjectImportOutcome<Study> studyImportOutcome) {
		
		List<StudyPersonnel> newStudyPersonnelList = new ArrayList<StudyPersonnel>();
		
		//Identify newly added StudyPersonnel
		for(StudyPersonnel xmlStudyPersonnel : xmlStudyOrganization.getStudyPersonnels()){
			for(StudyPersonnel dbStudyPersonnel : dbStudyOrganization.getStudyPersonnels()){
				
				String xmlNciIdentifier = xmlStudyPersonnel.getResearchStaff().getNciIdentifier();
				String dbNciIdentifier = dbStudyPersonnel.getResearchStaff().getNciIdentifier();
				String xmlFName = xmlStudyPersonnel.getResearchStaff().getFirstName();
				String dbFName = dbStudyPersonnel.getResearchStaff().getFirstName();
				String xmlLName = xmlStudyPersonnel.getResearchStaff().getLastName();
				String dbLName = dbStudyPersonnel.getResearchStaff().getLastName();
				
				xmlStudyPersonnel.setId(dbStudyPersonnel.getId());
				if(xmlNciIdentifier != null && dbNciIdentifier != null) {
					if(xmlNciIdentifier.equals(dbNciIdentifier)){
						dbStudyPersonnel.setRoleCode(xmlStudyPersonnel.getRoleCode());
						dbStudyPersonnel.setStatusCode(xmlStudyPersonnel.getStatusCode());
						break;
					}else{
						xmlStudyPersonnel.setId(null);
					}
				}else{
					if(xmlFName.equals(dbFName) && xmlLName.equals(dbLName)){
						dbStudyPersonnel.setRoleCode(xmlStudyPersonnel.getRoleCode());
						dbStudyPersonnel.setStatusCode(xmlStudyPersonnel.getStatusCode());
						break;
					}else{
						xmlStudyPersonnel.setId(null);
					}
				}
			}
			if(xmlStudyPersonnel.getId() == null){
				newStudyPersonnelList.add(xmlStudyPersonnel);
			}
		}
		
		//Add New StudyPersonnel
		for(StudyPersonnel newStudyPersonnel : newStudyPersonnelList){
			dbStudyOrganization.getStudyPersonnels().add(newStudyPersonnel);
		}
		
		for(StudyPersonnel studyPersonnel : dbStudyOrganization.getStudyPersonnels()){
			studyPersonnel.setStudyOrganization(dbStudyOrganization);
		}
		
	}//end method

}
