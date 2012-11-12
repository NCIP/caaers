class DropIndexTables extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
		dropTable("report_index")
		dropTable("researchstaff_index")
		dropTable("investigator_index")
		dropTable("STUDY_INDEX")
		dropTable("PARTICIPANT_INDEX")
		dropTable("organization_index")
		dropTable("adverseevent_index")
		dropTable("expedited_ae_index")
		dropTable("reportingperiod_index")    
    }

    void down() {
    }
}