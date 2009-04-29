class AddDTypeForResearcStaff extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("research_staffs","type", "string", defaultValue:'LOCAL')
		execute("UPDATE research_staffs SET TYPE='LOCAL'")
		if (databaseMatches('postgresql')){
         	execute("ALTER TABLE research_staffs ALTER COLUMN phone_number DROP NOT NULL")
     	}else if(databaseMatches('oracle')){
     		execute("ALTER TABLE research_staffs modify phone_number NULL")
     	}
		
		addColumn("research_staffs","external_id", "string")
	}
	
	void down() {
		dropColumn("research_staffs", "type")
		if (databaseMatches('postgresql')){
         	execute("ALTER TABLE research_staffs ALTER COLUMN phone_number SET NOT NULL")
     	}else if(databaseMatches('oracle')){
     		//will not work in oracle
     	}
		
		dropColumn("research_staffs", "external_id")
	}
}

