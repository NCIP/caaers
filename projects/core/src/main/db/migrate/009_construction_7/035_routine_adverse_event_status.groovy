class AddRoutineAdverseEventStatus extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_routine_reports", "status_code", "integer")
        execute("UPDATE ae_routine_reports set status_code=1 ")
    }

    void down() {
        dropColumn("ae_routine_reports", "status_code")
    }
}