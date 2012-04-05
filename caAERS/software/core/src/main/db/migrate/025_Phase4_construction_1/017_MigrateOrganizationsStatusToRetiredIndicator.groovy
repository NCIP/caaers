class MigrateOrganizationsStatusToRetiredIndicator extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	 execute("update organizations set retired_indicator = false where status = 'AC'");
    	  execute("update organizations set retired_indicator = true where status = 'IN'");
        dropColumn("organizations","status")
		
    }

    void down() {
       addColumn("organizations", "status")
    }
}