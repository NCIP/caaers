class CreateArms extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("arms") { t ->
            t.addVersionColumn()
            t.addColumn("name", "string", nullable:false)
            t.addColumn("description", "string", nullable:true)
            t.addColumn("epoch_id", "integer", nullable:false)
            t.addColumn("grid_id" , "string" , nullable:true)
            
        }
        execute("ALTER TABLE arms ADD CONSTRAINT fk_arm_epoch_id FOREIGN KEY (epoch_id) REFERENCES epochs");
        
    }
    
    void down() {
        dropTable("arms")
    }
}