class CreateParticipants extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('agents') { t ->
            t.addColumn('name', 'string', nullable:true)
            t.addColumn('description','string', nullable:true)
            t.addVersionColumn()
             t.addColumn('grid_id' , 'string' , nullable:true);
        }
        
        insert('agents', [id: 1, name: "agent1", description: "Agent I"], primaryKey: false)
    }

    void down() {
        dropTable('agents')
    }
}