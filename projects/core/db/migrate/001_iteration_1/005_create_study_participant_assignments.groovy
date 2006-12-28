class CreateStudyParticipantAssignments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('participant_assignments') { t ->
            t.addColumn('study_site_id', 'integer', nullable:false)
            t.addColumn('participant_id', 'integer', nullable:false)
            t.addColumn('date_of_enrollment', 'date', nullable:false)
            t.addVersionColumn()
        }
    }

    void down() {
        dropTable('participant_assignments')
    }

}