class AddDTypeForInvestigator extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("investigators","type", "string", defaultValue:'LOCAL')
		execute("UPDATE investigators SET TYPE='LOCAL'")
		if (databaseMatches('postgresql')){
         	execute("ALTER TABLE investigators ALTER COLUMN phone_number DROP NOT NULL")
     	}else if(databaseMatches('oracle')){
     		execute("ALTER TABLE investigators modify phone_number NULL")
     	}
		
		addColumn("investigators","external_id", "string")
	}
	
	void down() {
		dropColumn("investigators", "type")
		if (databaseMatches('postgresql')){
         	execute("ALTER TABLE investigators ALTER COLUMN phone_number SET NOT NULL")
     	}else if(databaseMatches('oracle')){
     		//not work in oracle
     	}
		
		dropColumn("investigators", "external_id")
	}
}

