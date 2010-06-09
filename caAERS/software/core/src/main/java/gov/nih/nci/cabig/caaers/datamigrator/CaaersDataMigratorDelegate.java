package gov.nih.nci.cabig.caaers.datamigrator;

import java.util.List;

/**
 * 
 * @author Monish Dombla
 *
 */
public class CaaersDataMigratorDelegate {

	private List<CaaersDataMigrator> migrators;
	
	/**
	 * 
	 * @throws Exception
	 */
	public void doMigrate() throws Exception{
		
		for(CaaersDataMigrator migrator : migrators){
			migrator.migrateData();
		}
	}
	
	public List<CaaersDataMigrator> getMigrators() {
		return migrators;
	}

	public void setMigrators(List<CaaersDataMigrator> migrators) {
		this.migrators = migrators;
	}
}
