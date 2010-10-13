class CreateDevices extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
      createTable("devices") { t ->
          t.addVersionColumn()
          t.addColumn("brand_name", "string", nullable:true)
          t.addColumn("common_name", "string", nullable:true)
          t.addColumn("device_type", "string", nullable:true)
          t.addColumn('grid_id' , 'string' , nullable:true);
      }
    }

    void down() {
        dropTable("devices")
    }
}
