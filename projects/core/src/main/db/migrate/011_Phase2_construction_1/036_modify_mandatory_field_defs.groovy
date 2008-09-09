class ModifyMandatoryFieldDefs extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		dropColumn("mandatory_field_defs", "mandatory");
		addColumn("mandatory_field_defs", "mandatory", "integer", nullable: false, defaultValue: 0);
	}
	
	void down() {

	}
}