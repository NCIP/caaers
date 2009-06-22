class CreateConfigGroup extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		//serves as a lookup table
        createTable("config_properties") { t ->
            t.addVersionColumn()
            t.addColumn("code", "string", nullable: false)
            t.addColumn("name", "string", nullable: false)
            t.addColumn("description", "string")
            t.addColumn("grid_id", "string")
            t.addColumn("config_type", "integer")
        }
    }

    void down() {
        dropTable("config_properties")
    }
}