class ChangeExpeditedAEReport extends edu.northwestern.bioinformatics.bering.Migration{
	void up() {
        dropColumn('ae_reports', 'status_code');
    }

    void down() {
       	addColumn('ae_reports','status_code', 'integer' , nullable:true);    
    }
}