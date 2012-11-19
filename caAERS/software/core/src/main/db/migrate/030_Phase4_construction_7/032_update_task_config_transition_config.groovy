class UpdateTaskConfigurationTaskName extends edu.northwestern.bioinformatics.bering.Migration {
    void up(){
        external('update_task_config_transition_config.sql')
    }
    void down(){
        //NA
    }
}

