class UpdateTaskConfigurationMessage extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	 if (databaseMatches('postgresql')){
         execute("update task_configuration set message = replace(message,'Course :','Reporting Period :')")
     }
	 	
	}
	void down(){
		//not required
	}
}