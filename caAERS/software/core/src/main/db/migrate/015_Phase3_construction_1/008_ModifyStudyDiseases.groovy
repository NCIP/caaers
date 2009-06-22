class ModifyStudyDiseases extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("study_diseases","retired_indicator", "boolean", defaultValue: 0)
		
    }

    void down() {
        dropColumn("study_diseases", "retired_indicator")
    }
}