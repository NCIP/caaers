class CreateSearch extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		//serves as a lookup table
        createTable("searches") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string", nullable: false)
            t.addColumn("name", "string", nullable: false)
            t.addColumn("description", "string")
		t.addColumn("created_date", "date", nullable:false)
            t.addColumn("criteria_xml", "string", nullable: false)
		t.addColumn("grid_id", "string", nullable: true)
        }
    }

    void down() {
        dropTable("searches")
    }
}