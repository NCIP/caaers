class ModifyStudyPersonnel extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("study_personnel","retired_indicator", "boolean", defaultValue: 0)
		
    }

    void down() {
        dropColumn("study_personnel", "retired_indicator")
    }
}