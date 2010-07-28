class ModifyWFTasks extends edu.northwestern.bioinformatics.bering.Migration {

	void up() {
		external("wf_modify_task_message.sql");
	}

	void down() {
      //not needed
    }

}