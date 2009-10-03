class ModifyStudies extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("studies","type", "string", defaultValue:'LOCAL')
		addColumn("studies","external_id", "string")
		execute("UPDATE studies SET TYPE='LOCAL'")
	}
	
	void down() {
		dropColumn("studies", "type")
		dropColumn("studies", "external_id")
	}
}

