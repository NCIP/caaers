class ChangeAdverseEventReport extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn('ae_reports','status_code', 'integer' , nullable:true);    
    }

    void down() {
        removeColumn('ae_reports', 'status_code');
    }
}