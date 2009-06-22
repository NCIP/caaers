class ChangeAeReport extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
		addColumn('ae_reports', 'disease_history_id', 'string', nullable:true);
        addColumn('ae_reports', 'participant_history_id', 'string', nullable:true);    				
    }

    void down() {
   		removeColumn('ae_reports','disease_history_id');
		removeColumn('ae_reports','participant_history_id');
    }
}