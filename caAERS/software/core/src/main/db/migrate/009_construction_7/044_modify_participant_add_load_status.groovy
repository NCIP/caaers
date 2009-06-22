class ModidyParticipants extends edu.northwestern.bioinformatics.bering.Migration {
    
    void up() {
            addColumn("participants","load_status", 'integer');
            execute("update participants set load_status = 1");

    }

    void down() {
    	dropColumn("participants","load_status")
    }
    
}