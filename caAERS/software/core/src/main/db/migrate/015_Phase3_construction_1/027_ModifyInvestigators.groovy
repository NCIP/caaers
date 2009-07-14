class ModifyInvestigators extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
      	if (databaseMatches('oracle')) {
        	addColumn("investigators","allowed_to_login", "boolean",defaultValue: 1)
        }else{
         	addColumn("investigators","allowed_to_login", "boolean",defaultValue: true)
        }
    }
    void down() {
    	 dropColumn("site_investigators", "allowed_to_login");
    }
}