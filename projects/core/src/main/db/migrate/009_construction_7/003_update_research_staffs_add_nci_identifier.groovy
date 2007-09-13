class UpdateResearchStaffs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {

 	addColumn("research_staffs","nci_identifier", 'string');
 	execute("UPDATE research_staffs set nci_identifier='nci_id'")
	setNullable("research_staffs", "nci_identifier", false);
    }

    void down() {
        		dropColumn("research_staffs","nci_identifier");
    
    }
}