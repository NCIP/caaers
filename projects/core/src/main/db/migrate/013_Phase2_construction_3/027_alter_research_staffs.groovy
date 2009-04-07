class AddDTypeForResearcStaff extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("research_staffs","type", "string", defaultValue:'LOCAL')
		execute("UPDATE research_staffs SET TYPE='LOCAL'")
		execute("ALTER TABLE research_staffs ALTER COLUMN phone_number DROP NOT NULL")
		addColumn("research_staffs","external_id", "string")
	}
	
	void down() {
		dropColumn("research_staffs", "type")
		execute("ALTER TABLE research_staffs ALTER COLUMN phone_number SET NOT NULL")
		dropColumn("research_staffs", "external_id")
	}
}

