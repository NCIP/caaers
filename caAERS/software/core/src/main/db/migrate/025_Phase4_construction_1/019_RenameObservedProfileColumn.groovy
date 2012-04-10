class RenameObservedProfileColumn extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
		dropColumn("observed_ae_profiles", "total_no_ofae")
		addColumn("observed_ae_profiles", "total_no_of_registrations", "integer", nullable:true)
    }
    
    void down() {
        dropColumn("observed_ae_profiles", "total_no_of_registrations")
		addColumn("observed_ae_profiles", "total_no_ofae", "integer", nullable:true)
    }
}