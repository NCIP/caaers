class AddStudyMonitorConfigProperty extends edu.northwestern.bioinformatics.bering.Migration {
    void up(){
        insert("config_properties", [ code: 'study_medical_monitor', version: '0',name : 'Study Medical Monitor',config_type:'2'])
    }
    void down(){
        execute("delete from config_properties where code like 'study_medical_monitor'")
    }
}