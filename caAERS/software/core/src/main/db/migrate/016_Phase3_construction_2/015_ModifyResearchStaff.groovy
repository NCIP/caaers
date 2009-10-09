class ModifyResearchStaff extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("research_staffs","last_login", "date")
		
    }

    void down() {
        dropColumn("research_staffs", "last_login")
    }
}