class ModifyMandatoryFieldDefs extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		// add new mandatory column(integer) and drop existing mandatory (boolean)

		if (databaseMatches('postgresql')) {
			// rename existing boolean column to mandatory_temp
			// not droping in postgres because we need the existing data to be migrated to new column
			
			renameColumn("mandatory_field_defs", "mandatory", "mandatory_temp");
		} else {
			// drop boolean column if it is not postgres, because no migration needed
			
			dropColumn("mandatory_field_defs", "mandatory");
		}
		
		//add new column with integer		
		
		addColumn("mandatory_field_defs", "mandatory", "integer", nullable: false, defaultValue: 0);
		
		if (databaseMatches('postgresql')) {
			// migrate old data and drop temp column
			
			execute("update mandatory_field_defs set mandatory = (case when (mandatory_temp = true) then 1 else 0 end)");
			dropColumn("mandatory_field_defs", "mandatory_temp");
		}
	}
	
	void down() {

	}
}