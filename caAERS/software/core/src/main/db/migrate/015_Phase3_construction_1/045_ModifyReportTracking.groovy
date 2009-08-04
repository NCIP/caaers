class ModifyReportTracking extends edu.northwestern.bioinformatics.bering.Migration {
        void up() {
      	if (databaseMatches('oracle')) {
        	execute('alter table report_tracking_status modify column recorded_time  DEFAULT SYSDATE')
        }else{
        	execute('alter table report_tracking_status ALTER column recorded_time SET DEFAULT CURRENT_DATE')
        }
    }
    void down() {
    	 
    }
}