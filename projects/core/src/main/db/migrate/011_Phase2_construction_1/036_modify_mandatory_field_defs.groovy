class ModifyMandatoryFieldDefs extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		renameColumn("mandatory_field_defs", "mandatory", "mandatory_temp");
		addColumn("mandatory_field_defs", "mandatory", "integer", nullable: false, defaultValue: 0);
		execute("update mandatory_field_defs set mandatory = (case when (mandatory_temp = true) then 1 else 0 end)");
		dropColumn("mandatory_field_defs", "mandatory_temp");
	}
	
	void down() {

	}
}