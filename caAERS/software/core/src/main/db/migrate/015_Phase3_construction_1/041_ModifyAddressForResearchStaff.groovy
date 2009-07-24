class ModifyAddressForResearchStaff extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	 	addColumn("research_staffs","country", "string")
	 	addColumn("site_research_staffs","country", "string")
	 	addColumn("ae_report_people","country", "string")
	}
	void down(){
        dropColumn("ae_report_people","country")
		dropColumn("site_research_staffs","country")
		dropColumn("research_staffs","country")
	}
}