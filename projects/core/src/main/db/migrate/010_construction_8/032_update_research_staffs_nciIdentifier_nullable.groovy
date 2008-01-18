class UpdateResearchStaffs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {

	setNullable("research_staffs", "nci_identifier", true);
    }

    void down() {
        	//not supported
    
    }
}