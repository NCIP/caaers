class ModifyStudy extends edu.northwestern.bioinformatics.bering.Migration {
    
    void up() {
            addColumn("studies","load_status", 'integer');
            execute("update studies set load_status = 1");

    }

    void down() {
    	dropColumn("studies","load_status")
    }
    
}