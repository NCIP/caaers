class ChangeAeReport extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn('ae_reports','reporter_id' , 'string' , nullable:false);
    	addColumn('ae_reports','physician_id' , 'string' , nullable:false);     	
    }

    void down() {
        removeColumn('ae_reports','reporter_id');
    	removeColumn('ae_reports','physician_id');    	
    }
}