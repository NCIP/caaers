class MigrateOrganizationsStatusToRetiredIndicator extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	 execute("update organizations set retired_indicator = 0 where status = 'AC'");
    	  execute("update organizations set retired_indicator = 1 where status = 'IN'");
        dropColumn("organizations","status")
		
    }

    void down() {
       addColumn("organizations", "status")
    }
}