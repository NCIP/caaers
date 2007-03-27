class CreateStudyParticipantAssignments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('notifications') { t ->
            t.addColumn('content', 'text', nullable:true)
            t.addColumn('email', 'text', nullable:true)
            t.addColumn('subject', 'text', nullable:true)
            t.addColumn('name', 'text', nullable:true)
            t.addVersionColumn()
            t.addColumn('grid_id' , 'string' , nullable:true);
        }
        
    }

    void down() {
        dropTable('notifications')
    }

}