class AddGridIdToPriorTherapyAndPreExistingConditions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
	    addColumn("prior_therapies","grid_id","string");
	    addColumn("pre_existing_conditions","grid_id","string");
    }

    void down() {
    	dropColumn("prior_therapies","grid_id");
    	dropColumn("pre_existing_conditions","grid_id");
    }
}