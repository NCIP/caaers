class updateAETreatmentAssignment extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_reporting_periods", "treatment_assignment_desc", "string")
    }

    void down(){
        dropColumn("ae_reporting_periods", "treatment_assignment_desc")
    }
}
