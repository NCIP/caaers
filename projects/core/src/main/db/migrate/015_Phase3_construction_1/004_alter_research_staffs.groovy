class ModifyResearchStaffs extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	 	addColumn("research_staffs","status_code", "string", defaultValue:'ACT')
	}
	void down(){
		dropColumn("research_staffs","status_code")
	}
}