class AddTacToTreatmentInfo extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("treatments", "treatment_assignment_code", "string")
    }

    void down() {
        dropColumn("treatments", "treatment_assignment_code")
    }
}