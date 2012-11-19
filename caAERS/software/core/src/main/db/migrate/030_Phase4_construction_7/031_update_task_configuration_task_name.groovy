class UpdateTaskConfigurationTaskName extends edu.northwestern.bioinformatics.bering.Migration {
    void up(){
        external('update_task_config_task_name_To_to_to.sql')
    }
    void down(){
        //NA
    }
}

