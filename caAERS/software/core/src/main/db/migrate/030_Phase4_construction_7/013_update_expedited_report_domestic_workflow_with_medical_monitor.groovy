class UpdateExpeditedReportDomesticWorkflowWithMedicalMonitor extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	 if (databaseMatches('postgresql')){
         external('update_wf_def_expedited_report_domestic_with_medical_monitor_postgres.sql')
     }else if(databaseMatches('oracle')){
     	 external('update_wf_def_expedited_report_domestic_with_medical_monitor_oracle.sql')
     }
	 	
	}
	void down(){
		//not required
	}
}