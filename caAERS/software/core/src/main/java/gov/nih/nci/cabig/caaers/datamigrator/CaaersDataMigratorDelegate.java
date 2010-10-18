package gov.nih.nci.cabig.caaers.datamigrator;

import java.util.List;

/**
 * 
 * @author Monish Dombla
 * @author Biju Joseph (Refactored to support a generic migration strategy)
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