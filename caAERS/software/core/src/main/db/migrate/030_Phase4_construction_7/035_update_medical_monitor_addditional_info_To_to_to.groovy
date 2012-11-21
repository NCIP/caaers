class UpdateJbpmNodeToToTo extends edu.northwestern.bioinformatics.bering.Migration {
    void up(){
        external('update_medical_monitor_additional_info_To_to_to.sql')
    }
    void down(){
        //NA
    }
}

