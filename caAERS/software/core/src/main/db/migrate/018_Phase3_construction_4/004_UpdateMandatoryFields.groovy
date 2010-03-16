class RemoveAdditionalInformation extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		execute("delete from mandatory_field_defs where field_path like 'additionalInformation%'")
	}
	void down() {
		//nothing here
	}
}