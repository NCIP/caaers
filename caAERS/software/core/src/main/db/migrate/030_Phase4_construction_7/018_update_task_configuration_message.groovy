class UpdateTaskConfigurationMessage extends edu.northwestern.bioinformatics.bering.Migration {
    void up(){
        external('update_task_configuration_message.sql')
    }
    void down(){
        //NA
    }
}

