class UpdateCoordinatingCenterWorkflowDef extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		external('wf_modify_def_reporting_period_basic.sql');
	}
	void down(){
		//not required
	}
}