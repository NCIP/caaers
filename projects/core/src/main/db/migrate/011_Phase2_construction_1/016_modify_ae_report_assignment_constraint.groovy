class UpdateConstraintsAeReportAssignment extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        setNullable("ae_reports", "assignment_id", true)
    }

    void down() {
        //not possible
    }
}