class CreateStudyParticipantAssignments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('study_agents') { t ->
            t.addColumn('study_id', 'integer', nullable:false)
            t.addColumn('agent_id', 'integer', nullable:false)
            t.addColumn('ind_identifier', 'string', nullable:true)
            t.addColumn('ind_indicator', 'boolean', nullable:true)
            t.addColumn('start_date', 'date', nullable:true)
            t.addColumn('end_date', 'date', nullable:true)
            t.addColumn('grid_id' , 'string' , nullable:true);
            t.addVersionColumn()
        }
    }

    void down() {
        dropTable('study_agents')
    }

}