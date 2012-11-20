class UpdateJbpmNodeToToTo extends edu.northwestern.bioinformatics.bering.Migration {
    void up(){
        external('update_jbpm_node_To_to_to.sql')
    }
    void down(){
        //NA
    }
}

