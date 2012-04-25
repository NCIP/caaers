class AddPriorTherapyType extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
	    addColumn("prior_therapies","therapy_type","string");
    }

    void down() {
    	dropColumn("prior_therapies","therapy_type");
    }
}