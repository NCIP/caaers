class AddDTypeForInvestigator extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("investigators","type", "string", defaultValue:'LOCAL')
		execute("UPDATE investigators SET TYPE='LOCAL'")
		execute("ALTER TABLE investigators ALTER COLUMN phone_number DROP NOT NULL")
		addColumn("investigators","external_id", "string")
	}
	
	void down() {
		dropColumn("investigators", "type")
		execute("ALTER TABLE investigators ALTER COLUMN phone_number SET NOT NULL")
		dropColumn("investigators", "external_id")
	}
}

