class UpdateTaskConfiguraton extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		execute("update task_configuration set status_name = 'SUBMIT_TO_SPONSOR' where id = 16")
	}
	
	void down() {
		//not possible
		
	}
}
