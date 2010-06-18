package gov.nih.nci.cabig.caaers.datamigrator;

import java.util.List;

/**
 * 
 * @author Monish Dombla
 *
 */
public class CaaersDataMigratorDelegate {

	private List<CaaersDataMigrator> migrators;
	private String authenticationMode="local";
	
	/**
	 * 
	 * @throws Exception
	 */
	public void doMigrate() throws Exception{
	
		if(authenticationMode.equals("webSSO")) return;
		
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

	public void setAuthenticationMode(String authenticationMode) {
		this.authenticationMode = authenticationMode;
	}
}