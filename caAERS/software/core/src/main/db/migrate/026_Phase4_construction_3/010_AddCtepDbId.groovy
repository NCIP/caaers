class AddCtepDBID extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
	    addColumn("devices","ctep_db_identifier","string");
	    addColumn("treatment_assignment","ctep_db_identifier","string");
    }

    void down() {
    	dropColumn("devices","ctep_db_identifier");
    	dropColumn("treatment_assignment","ctep_db_identifier");
    }
}