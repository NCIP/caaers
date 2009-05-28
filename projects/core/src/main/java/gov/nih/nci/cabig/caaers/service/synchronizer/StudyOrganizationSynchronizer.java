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
		if(CollectionUtils.isNotEmpty(xmlStudy.getStudySites())){
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
		
		//de-activate, all the other sites and their investigators and personnel
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
		
		if(xmlStudyOrganization.getStudyInvestigators() != null){
			if(xmlStudyOrganization.getStudyInvestigators().size() == 0){
				if(dbStudyOrganization.getStudyInvestigators() != null){
					while(!dbStudyOrganization.getStudyInvestigators().isEmpty()){
						dbStudyOrganization.getStudyInvestigators().remove(0);
					}
				}
				return;
			}
		}
		
		List<StudyInvestigator> newStudyInvestigatorList = new ArrayList<StudyInvestigator>();
		List<StudyInvestigator> deleteStudyInvestigatorList = new ArrayList<StudyInvestigator>();
		StudyInvestigator remStudyInvestigator = null;
		
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
		
		//Identify StudyInvestigators to be Removed
		for(StudyInvestigator dbStudyInvestigator : dbStudyOrganization.getStudyInvestigators()){
			for(StudyInvestigator xmlStudyInvestigator : xmlStudyOrganization.getStudyInvestigators()){
				remStudyInvestigator = new StudyInvestigator();
				remStudyInvestigator = dbStudyInvestigator;
				
				String xmlNciIdentifier = xmlStudyInvestigator.getSiteInvestigator().getInvestigator().getNciIdentifier();
				String dbNciIdentifier = remStudyInvestigator.getSiteInvestigator().getInvestigator().getNciIdentifier();
				String xmlFName = xmlStudyInvestigator.getSiteInvestigator().getInvestigator().getFirstName();
				String dbFName = remStudyInvestigator.getSiteInvestigator().getInvestigator().getFirstName();
				String xmlLName = xmlStudyInvestigator.getSiteInvestigator().getInvestigator().getLastName();
				String dbLName = remStudyInvestigator.getSiteInvestigator().getInvestigator().getLastName();
				
				if(dbNciIdentifier != null && xmlNciIdentifier != null){
					if(dbNciIdentifier.equals(xmlNciIdentifier)){
						remStudyInvestigator = null;
						break;
					}
				}else{
					if(dbFName.equals(xmlFName) && dbLName.equals(xmlLName)){
						remStudyInvestigator = null;
						break;
					}
				}
			}
			if(remStudyInvestigator != null){
				deleteStudyInvestigatorList.add(remStudyInvestigator);
			}
		}
		
		//Add New StudyInvestigators
		for(StudyInvestigator newStudyInvestigator : newStudyInvestigatorList){
			dbStudyOrganization.getStudyInvestigators().add(newStudyInvestigator);
		}
		
		//Remove StudyInvestigators
		for(StudyInvestigator delStudyInvestigator : deleteStudyInvestigatorList){
			dbStudyOrganization.getStudyInvestigators().remove(delStudyInvestigator);
		}
		
		for(StudyInvestigator studyInvestigator : dbStudyOrganization.getStudyInvestigators()){
			studyInvestigator.setStudyOrganization(dbStudyOrganization);
		}
		
	}//end method
	
	private void syncStudyPersonnels(StudyOrganization dbStudyOrganization,
										StudyOrganization xmlStudyOrganization,
										Organization organization, 
										DomainObjectImportOutcome<Study> studyImportOutcome) {
		
		if(xmlStudyOrganization.getStudyPersonnels() != null){
			if(xmlStudyOrganization.getStudyPersonnels().size() == 0){
				if(dbStudyOrganization.getStudyPersonnels() != null){
					while(!dbStudyOrganization.getStudyPersonnels().isEmpty()){
						dbStudyOrganization.getStudyPersonnels().remove(0);
					}
				}
				return;
			}
		}
		
		List<StudyPersonnel> newStudyPersonnelList = new ArrayList<StudyPersonnel>();
		List<StudyPersonnel> deleteStudyPersonnelList = new ArrayList<StudyPersonnel>();
		StudyPersonnel remStudyPersonnel = null;
		
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
		
		//Identify StudyPersonnel to be Removed
		for(StudyPersonnel dbStudyPersonnel : dbStudyOrganization.getStudyPersonnels()){
			for(StudyPersonnel xmlStudyPersonnel : xmlStudyOrganization.getStudyPersonnels()){
				remStudyPersonnel = new StudyPersonnel();
				remStudyPersonnel = dbStudyPersonnel;
				
				String xmlNciIdentifier = xmlStudyPersonnel.getResearchStaff().getNciIdentifier();
				String dbNciIdentifier = remStudyPersonnel.getResearchStaff().getNciIdentifier();
				String xmlFName = xmlStudyPersonnel.getResearchStaff().getFirstName();
				String dbFName = remStudyPersonnel.getResearchStaff().getFirstName();
				String xmlLName = xmlStudyPersonnel.getResearchStaff().getLastName();
				String dbLName = remStudyPersonnel.getResearchStaff().getLastName();
				
				if(dbNciIdentifier != null && xmlNciIdentifier != null){
					if(dbNciIdentifier.equals(xmlNciIdentifier)){
						remStudyPersonnel = null;
						break;
					}
				}else{
					if(dbFName.equals(xmlFName) && dbLName.equals(xmlLName)){
						remStudyPersonnel = null;
						break;
					}
				}
			}
			if(remStudyPersonnel != null){
				deleteStudyPersonnelList.add(remStudyPersonnel);
			}
		}
		
		//Add New StudyPersonnel
		for(StudyPersonnel newStudyPersonnel : newStudyPersonnelList){
			dbStudyOrganization.getStudyPersonnels().add(newStudyPersonnel);
		}
		
		//Remove StudyPersonnel
		for(StudyPersonnel delStudyPersonnel : deleteStudyPersonnelList){
			dbStudyOrganization.getStudyPersonnels().remove(delStudyPersonnel);
		}
		
		for(StudyPersonnel studyPersonnel : dbStudyOrganization.getStudyPersonnels()){
			studyPersonnel.setStudyOrganization(dbStudyOrganization);
		}
		
	}//end method
	
	private String generateIndexKey(StudyOrganization so){
		Organization o = so.getOrganization();
		String nciCode = o.getNciInstituteCode();
		String name = o.getName();
		assert nciCode != null || name != null : " Organization Name and NCICode, atleast one should be present";
		return ((nciCode == null) ? "" : nciCode + "%" ) + ( (name == null) ? "" : name); 
	}

}
