class ChangeAeReport extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
		removeColumn('ae_reports','reporter_id');
		removeColumn('ae_reports','physician_id');
		addColumn('ae_reports', 'reporter_id', 'string', nullable:true);
        addColumn('ae_reports', 'physician_id', 'string', nullable:true);    				
    }

    void down() {
   		removeColumn('ae_reports','reporter_id');
		removeColumn('ae_reports','physician_id');
        addColumn('ae_reports', 'reporter_id', 'string');
        addColumn('ae_reports', 'physician_id', 'string');    
    }
}