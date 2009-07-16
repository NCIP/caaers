class ModifyAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
	
    void up() {
        addColumn("adverse_events","retired_indicator", "boolean", defaultValue: 0)
        addColumn("adverse_events","post_submission_updated_date", "date")
    }

    void down() {
        dropColumn("adverse_events", "retired_indicator")
         dropColumn("adverse_events", "post_submission_updated_date")
    }
}
