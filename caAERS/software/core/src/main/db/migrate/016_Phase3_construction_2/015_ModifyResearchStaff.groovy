class ModifyResearchStaff extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("research_staffs","last_login", "timestamp")
		
    }

    void down() {
        dropColumn("research_staffs", "last_login")
    }
}