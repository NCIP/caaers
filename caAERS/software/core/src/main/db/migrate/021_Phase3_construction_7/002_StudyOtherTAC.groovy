class updateAETreatmentAssignment extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("studies", "other_treatment_assignment", "string")
    }

    void down(){
        dropColumn("studies", "other_treatment_assignment")
    }
}
