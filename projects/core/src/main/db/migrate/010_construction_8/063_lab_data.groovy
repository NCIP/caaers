class CreateLabViewerLabs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute('drop sequence seq_labviewer_labs_id');
        execute('drop TABLE labviewer_labs');
        
        createTable("labs") { t ->
            t.addVersionColumn()
            t.addColumn("name", "string", nullable:false)
            t.addColumn("lab_date", "date")
            t.addColumn("result", "string")
            t.addColumn("units", "string")
            t.addColumn("assignment_id", "integer", nullable:false)
            t.addColumn("dismissed", "boolean", nullable:false)
            t.addColumn("grid_id" , "string" , nullable:true)
            
        }  
        execute('ALTER TABLE labs ADD CONSTRAINT fk_sp_assignment_id FOREIGN KEY (assignment_id) REFERENCES participant_assignments');
      
    }
    
    void down() {
        dropTable("labs")
    }
}