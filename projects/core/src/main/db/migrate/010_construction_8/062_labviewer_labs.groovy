class CreateLabViewerLabs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("labviewer_labs") { t ->
            t.addVersionColumn()
            t.addColumn("name", "string", nullable:false)
            t.addColumn("lab_date", "date")
            t.addColumn("result", "string")
            t.addColumn("units", "string")
            t.addColumn("assignment_id", "integer", nullable:false)
            t.addColumn("dismissed", "boolean", nullable:false)
            t.addColumn("grid_id" , "string" , nullable:true)
            
        }  
        execute('create sequence seq_labviewer_labs_id increment by 1');
        execute('ALTER TABLE labviewer_labs ADD CONSTRAINT fk_sp_assignment_id FOREIGN KEY (assignment_id) REFERENCES participant_assignments');
      
    }
    
    void down() {
        dropTable("labviewer_labs")
    }
}

