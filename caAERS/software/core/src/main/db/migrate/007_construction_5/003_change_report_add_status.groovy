class ChangeReport extends edu.northwestern.bioinformatics.bering.Migration{
	void up() {
       	addColumn('report_schedules','status_code', 'integer' , nullable:true);            
    }

    void down() {
       	dropColumn('report_schedules', 'status_code');
    }
}