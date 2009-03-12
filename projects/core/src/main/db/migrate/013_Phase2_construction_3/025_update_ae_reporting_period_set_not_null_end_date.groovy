class updateAETreatmentAssignment extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute("ALTER TABLE ae_reporting_periods ALTER COLUMN end_date DROP NOT NULL");
    }

    void down(){
        execute("ALTER TABLE ae_reporting_periods ALTER COLUMN end_date SET NOT NULL");
    }
}
