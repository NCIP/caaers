class UpdateConstraintsAeReportPeople extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        setNullable("ae_report_people", "first_name", true)
        setNullable("ae_report_people", "last_name", true)
    }

    void down() {
        //not possible
    }
}
