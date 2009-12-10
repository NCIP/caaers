class AlterCourseAgents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	execute("update mandatory_field_defs set mandatory=0 where field_path ='diseaseHistory.diagnosisDate'")
    }

    void down() {
    	execute("update mandatory_field_defs set mandatory=0 where field_path ='diseaseHistory.diagnosisDate'")
    }
}