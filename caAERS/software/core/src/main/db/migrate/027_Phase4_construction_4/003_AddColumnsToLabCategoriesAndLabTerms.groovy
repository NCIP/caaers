class AddColumnsToLabCategoriesAndLabTerms extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
	    addColumn("lab_categories","grid_id","string");
	    addColumn("lab_categories", "version", "integer", defaultValue: 0, nullable: false);
	    addColumn("lab_categories","retired_indicator", "boolean", defaultValue: 0);
	    
	    addColumn("lab_terms","grid_id","string");
	    addColumn("lab_terms", "version", "integer", defaultValue: 0, nullable: false);
	    addColumn("lab_terms","retired_indicator", "boolean", defaultValue: 0);
    }

    void down() {
    	dropColumn("lab_categories","grid_id");
    	dropColumn("lab_categories","version");
    	dropColumn("lab_categories","retired_indicator");
    	
    	dropColumn("lab_terms","grid_id");
    	dropColumn("lab_terms","version");
    	dropColumn("lab_terms","retired_iRndicator");
    }
}