class ModifyTaskLink extends edu.northwestern.bioinformatics.bering.Migration {
    void up(){
        external('update_task_message.sql')
    }
    void down(){
        //NA
    }
}

