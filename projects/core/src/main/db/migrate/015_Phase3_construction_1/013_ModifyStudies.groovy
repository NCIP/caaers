class ModifyStudies extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("studies","data_entry_status", "boolean", defaultValue: 1)
		
    }

    void down() {
        dropColumn("studies", "data_entry_status")
    }
}