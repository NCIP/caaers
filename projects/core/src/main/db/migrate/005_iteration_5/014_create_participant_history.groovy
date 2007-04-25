class CreateParticipantHistory extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('participant_history') { t ->
            t.addColumn('height', 'numeric', nullable:true)
            t.addColumn('weight', 'numeric', nullable:true)
            t.addColumn('baseline_performance_status', 'string', nullable:true)            
            t.addColumn('height_unit_of_measure', 'string', nullable:true)
            t.addColumn('weight_unit_of_measure', 'string', nullable:true)            
            t.addColumn('grid_id', 'string', nullable:true)
            t.addVersionColumn()
        }        
    }

    void down() {
        dropTable('participant_history')
    }

}