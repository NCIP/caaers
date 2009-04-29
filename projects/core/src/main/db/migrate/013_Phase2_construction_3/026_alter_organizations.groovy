class AddDTypeForOrganization extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("organizations","type", "string", defaultValue:'LOCAL')
		execute("UPDATE organizations SET TYPE='LOCAL'")
		 if (databaseMatches('postgresql')){
			execute("ALTER TABLE organizations ALTER COLUMN name DROP NOT NULL")
		}else if(databaseMatches('oracle')){
			execute("ALTER TABLE organizations modify name NULL")
		}
		addColumn("organizations","external_id", "string")
	}
	
	void down() {
		dropColumn("organizations", "type")
		if (databaseMatches('postgresql')){
			execute("ALTER TABLE organizations ALTER COLUMN name SET NOT NULL")
		}else if(databaseMatches('oracle')){
			//will not work in oracle.
		}
		
		dropColumn("organizations", "external_id")
	}
}

