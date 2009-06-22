class AddAeReportCreatedDate extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_reports", "created_at", "timestamp")
        // this is misleading, but since it's only development data at this point ...
        execute("UPDATE ae_reports SET created_at=CURRENT_TIMESTAMP")
        setNullable("ae_reports", "created_at", false)
    }

    void down() {
        dropColumn("ae_reports", "created_at")
    }
}