class CreateDevices extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
      createTable("other_interventions") { t ->
          t.addVersionColumn()
          t.addColumn('study_id', 'integer')
          t.addColumn('study_therapy_type', 'integer', nullable:false)
          t.addColumn("name", "string", nullable:true)
          t.addColumn("description", "string", nullable:true)
          t.addColumn('retired_indicator', 'boolean', defaultValue: 0)
          t.addColumn('grid_id', "string")
      }

      addColumn("study_agents","study_therapy_type", "integer", nullable:false, defaultValue: 1)
      addColumn('study_devices', 'study_therapy_type', 'integer', nullable:false, defaultValue: 4)
    }

    void down() {
        dropTable("other_interventions")
        dropColumn("study_agents","study_therapy_type"); 
        dropColumn("study_devices","study_therapy_type"); 
    }
}
