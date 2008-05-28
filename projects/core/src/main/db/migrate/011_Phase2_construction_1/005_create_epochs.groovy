class CreateEpochs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("epochs") { t ->
            t.addVersionColumn()
            t.addColumn("name", "string", nullable:false)
            t.addColumn("description", "string", nullable:true)
            t.addColumn("study_id", "integer", nullable:true)
            t.addColumn("order_no", "integer", nullable:false)
            t.addColumn("grid_id" , "string" , nullable:true)
          }
        execute("ALTER TABLE epochs ADD CONSTRAINT fk_epoch_study_id FOREIGN KEY (study_id) REFERENCES studies");
        
    }
    
    void down() {
        dropTable("epochs")
    }
}