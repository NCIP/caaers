package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.dao.LabTermDao;
import gov.nih.nci.cabig.caaers.dao.LabVersionDao;
import gov.nih.nci.cabig.caaers.domain.LabCategory;
import gov.nih.nci.cabig.caaers.domain.LabTerm;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.List;

/**
 * @author Ramakrishna Gundala
 */

public class LabMigrator implements Migrator<LabCategory>{
	
	private LabVersionDao labVersionDao;
	private LabTermDao labTermDao;

	public void setLabTermDao(LabTermDao labTermDao) {
		this.labTermDao = labTermDao;
	}

	public void setLabVersionDao(LabVersionDao labVersionDao) {
		this.labVersionDao = labVersionDao;
	}

	public void migrate(LabCategory src, LabCategory dest, DomainObjectImportOutcome<LabCategory> outcome) {
		dest.setName(src.getName());
		dest.setRetiredIndicator(src.getRetiredIndicator());
		// if lab category exists in the db, loop through lab terms and check if each lab term exists. If it exists, update it else create new one
		if(dest.getId() != null) {
			// case for existing lab category
			for(LabTerm labTerm:src.getTerms()){
				LabTerm dbTerm  = null;
				List<LabTerm> labTerms = labTermDao.getBySubname(new String[]{labTerm.getTerm()}, dest.getId());
				if(labTerms != null && labTerms.size() > 0){
					dbTerm = labTerms.get(0);
				}
				if (dbTerm == null){
					dbTerm = new LabTerm();
				}
				// migrate lab term, set retired indicator etc
				migrate(labTerm,dbTerm,null);
				// if term doesn't exist add it
				if(dbTerm.getId() == null){
					dest.addTerm(dbTerm);
				}
			}
		} else {
			// case for new lab category, set lab version to Version 1
			dest.setLabVersion(labVersionDao.getByName("Version 1"));
			// create a new lab term for each migrated one.
			for(LabTerm labTerm:src.getTerms()){
				LabTerm newLabTerm = new LabTerm();
				migrate(labTerm,newLabTerm,null);
				dest.addTerm(newLabTerm);
			}
		}
	}
	
	public void migrate(LabTerm src, LabTerm dest, DomainObjectImportOutcome<LabCategory> outcome) {
		dest.setTerm(src.getTerm());
		dest.setRetiredIndicator(src.getRetiredIndicator());
	}
}
