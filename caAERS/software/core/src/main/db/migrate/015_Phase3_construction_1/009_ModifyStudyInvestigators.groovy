class ModifyStudyInvestigators extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("study_investigators","retired_indicator", "boolean", defaultValue: 0)
		
    }

    void down() {
        dropColumn("study_investigators", "retired_indicator")
    }
}