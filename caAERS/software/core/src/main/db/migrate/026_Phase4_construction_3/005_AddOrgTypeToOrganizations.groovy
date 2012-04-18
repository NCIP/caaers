class AddOrgTypeToOrganizations extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
	    addColumn("organizations","org_type","string");
    }

    void down() {
    	dropColumn("organizations","org_type");
    }
}