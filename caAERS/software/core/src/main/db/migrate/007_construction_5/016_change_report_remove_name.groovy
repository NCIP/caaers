class ChangeReport extends edu.northwestern.bioinformatics.bering.Migration{
	void up() {
        dropColumn('report_schedules', 'name');
    }

    void down() {
       	addColumn('report_schedules','name', 'string' , nullable:false);    
    }
}