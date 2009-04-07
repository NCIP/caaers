class AddDTypeForOrganization extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("organizations","type", "string", defaultValue:'LOCAL')
		execute("UPDATE organizations SET TYPE='LOCAL'")
		execute("ALTER TABLE organizations ALTER COLUMN name DROP NOT NULL")
		addColumn("organizations","external_id", "string")
	}
	
	void down() {
		dropColumn("organizations", "type")
		execute("ALTER TABLE organizations ALTER COLUMN name SET NOT NULL")
		dropColumn("organizations", "external_id")
	}
}

