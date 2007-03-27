class CreateStudyParticipantAssignments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('notifications') { t ->
            t.addColumn('content', 'string', nullable:true)
            t.addColumn('email', 'string', nullable:true)
            t.addColumn('subject', 'string', nullable:true)
            t.addColumn('name', 'string', nullable:true)
            t.addVersionColumn()
            t.addColumn('grid_id' , 'string' , nullable:true);
        }
        
    }

    void down() {
        dropTable('notifications')
    }

}