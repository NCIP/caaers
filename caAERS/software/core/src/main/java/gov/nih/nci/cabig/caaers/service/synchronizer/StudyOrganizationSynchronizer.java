package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class StudyOrganizationSynchronizer implements Migrator<gov.nih.nci.cabig.caaers.domain.Study>{

	public void migrate(Study dbStudy, Study xmlStudy,DomainObjectImportOutcome<Study> outcome) {
		
		
		//migrate funding sponsor
		syncFundingSponsor(dbStudy, xmlStudy, outcome);
        
        //migrate coordinating center
		syncCoordinatingCenter(dbStudy, xmlStudy, outcome);
		
        //migrate studyOrganization.
		syncStudySite(dbStudy, xmlStudy, outcome);
		
	}
	
	/**
	 * This method will synchronize the study site
	 * @param dbStudy
	 * @param xmlStudy
	 * @param outcome
	 */
	private void syncStudySite(Study dbStudy, Study xmlStudy,DomainObjectImportOutcome<Study> outcome) {
		
		//do nothing if study sites section is empty in xmlStudy
		if(CollectionUtils.isEmpty(xmlStudy.getStudySites())){
			return;
		}
		
		//create an index consisting of sites, in dbStudy
		HashMap<String, StudySite> siteIndexMap = new HashMap<String, StudySite>();
		for(StudySite ss : dbStudy.getStudySites()){
			siteIndexMap.put(generateIndexKey(ss), ss);
		}
		
		//loop through xmlStudy sites, and sync the personnel and investigators
		for(StudySite xmlStudySite : xmlStudy.getStudySites()){
			StudySite ss = siteIndexMap.remove(generateIndexKey(xmlStudySite));
			if(ss == null){
				//new so add it to dbStudy
				dbStudy.addStudySite(xmlStudySite);
				continue;
			}
			
			//sync the staff & investigators
			syncStudyInvestigators(ss, xmlStudySite, ss.getOrganization(), outcome);
			syncStudyPersonnels(ss, xmlStudySite, ss.getOrganization(), outcome);
			
		}
		
		//de-activate, all the other sites
		for(StudySite ss : siteIndexMap.values()){
			ss.deactivate();
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
		
		//do nothing if there is no investigator in the xmlStudy Organization
		if(CollectionUtils.isEmpty(xmlStudyOrganization.getStudyInvestigators())){
			return;
		}
		
		//generate and index of existing study investigators
		HashMap<String, StudyInvestigator> dbStudyInvIndexMap = new HashMap<String, StudyInvestigator>();
		for(StudyInvestigator si : dbStudyOrganization.getStudyInvestigators()){
			dbStudyInvIndexMap.put(generateIndexKey(si), si);
		}
		
		//loop through xmlStudy Organization StudyInvestigators, then add and modify details
		for(StudyInvestigator xmlSi : xmlStudyOrganization.getStudyInvestigators()){
			StudyInvestigator si = dbStudyInvIndexMap.remove(generateIndexKey(xmlSi));
			if(si == null){
				//new one so add it to Study
				dbStudyOrganization.addStudyInvestigators(xmlSi);
				continue;
			}
			//update existing investigator
			si.setEndDate(xmlSi.getEndDate());
			si.setStartDate(xmlSi.getStartDate());
			si.setRoleCode(xmlSi.getRoleCode());
		}
		
		//deactivate the study investigators which are not present in xmlStudy Organization
		for(StudyInvestigator si : dbStudyInvIndexMap.values()){
			si.deactivate();
		}
		
		
	}//end method
	
	private void syncStudyPersonnels(StudyOrganization dbStudyOrganization,
										StudyOrganization xmlStudyOrganization,
										Organization organization, 
										DomainObjectImportOutcome<Study> studyImportOutcome) {
		
		

		//do nothing if there is no personnel in the xmlStudy Organization
		if(CollectionUtils.isEmpty(xmlStudyOrganization.getStudyPersonnels())){
			return;
		}
		
		//generate and index of existing study StudyPersonnel
		HashMap<String, StudyPersonnel> dbStudyPersonnelIndexMap = new HashMap<String, StudyPersonnel>();
		for(StudyPersonnel sp : dbStudyOrganization.getStudyPersonnels()){
			dbStudyPersonnelIndexMap.put(generateIndexKey(sp), sp);
		}
		
		//loop through xmlStudy Organization StudyPersonnel, then add and modify details
		for(StudyPersonnel xmlSp : xmlStudyOrganization.getStudyPersonnels()){
			StudyPersonnel sp = dbStudyPersonnelIndexMap.remove(generateIndexKey(xmlSp));
			if(sp == null){
				//new one so add it to Study
				dbStudyOrganization.addStudyPersonnel(xmlSp);
				continue;
			}
			//update existing study personnel
			sp.setEndDate(xmlSp.getEndDate());
			sp.setStartDate(xmlSp.getStartDate());
			sp.setRoleCode(xmlSp.getRoleCode());
		}
		
		//deactivate the study staff which are not present in xmlStudy Organization
		for(StudyPersonnel sp : dbStudyPersonnelIndexMap.values()){
			sp.deactivate();
		}
		
	}//end method
	
	//generate a string key based on the values of site
	private String generateIndexKey(StudySite so){
		Organization o = so.getOrganization();
		String nciCode = o.getNciInstituteCode();
		String name = o.getName();
		assert nciCode != null || name != null : " Organization Name and NCICode, atleast one should be present";
		return ((nciCode == null) ? "" : nciCode + "%" ) + ( (name == null) ? "" : name); 
	}
	
	//generate a string key based on the values of study investigator
	private String generateIndexKey(StudyInvestigator si){
		Investigator inv = si.getSiteInvestigator().getInvestigator();
		String nciCode = inv.getNciIdentifier();
		String firstName = inv.getFirstName();
		String lastName = inv.getLastName();
		String roleCode = si.getRoleCode();
		
		assert (nciCode != null || firstName != null || lastName != null || roleCode != null) : "Investigator firstname, lastname , nciCode or roleCode should be present";
		StringBuffer sb = new StringBuffer();
		sb.append(nciCode != null ? nciCode : "").append("%")
		.append(firstName != null ? firstName : "").append("%")
		.append(lastName != null ? lastName : "").append("%")
		.append(roleCode != null ? roleCode : "");
		return sb.toString();
		
	}
	
	//generate a string key based on the values of study personnel
	private String generateIndexKey(StudyPersonnel sp){
		ResearchStaff staff = sp.getResearchStaff();
		String nciCode = staff.getNciIdentifier();
		String firstName = staff.getFirstName();
		String lastName = staff.getLastName();
		String roleCode = sp.getRoleCode();
		
		assert (nciCode != null || firstName != null || lastName != null || roleCode != null) : "ResearchStaff firstname, lastname , nciCode or roleCode should be present";
		StringBuffer sb = new StringBuffer();
		sb.append(nciCode != null ? nciCode : "").append("%")
		.append(firstName != null ? firstName : "").append("%")
		.append(lastName != null ? lastName : "").append("%")
		.append(roleCode != null ? roleCode : "");
		return sb.toString();
		
	}
}
