class CreateParticipantIndex extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("PARTICIPANT_INDEX") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("participant_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
        }
         execute('alter table PARTICIPANT_INDEX add constraint fk_part_index_pt_id foreign key (participant_id) references participants (id)')
    }

    void down() {
        dropTable("PARTICIPANT_INDEX")
    }
}