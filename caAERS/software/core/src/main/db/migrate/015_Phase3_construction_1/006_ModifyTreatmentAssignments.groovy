class ModifyTreatmentAssignments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("treatment_assignment","retired_indicator", "boolean", defaultValue: 0)
		
    }

    void down() {
        dropColumn("treatment_assignment", "retired_indicator")
    }
}