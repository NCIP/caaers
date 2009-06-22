class OneDatePerReport extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_reports", "detection_date", "date")
        execute("UPDATE ae_reports\n" +
                "   SET detection_date=\n" +
                "       (SELECT ae.detection_date FROM adverse_events ae WHERE ae.report_id=ae_reports.id)")
        dropColumn("adverse_events", "detection_date")
        setNullable("ae_reports", "detection_date", false)
    }

    void down() {
        addColumn("adverse_events", "detection_date", "date")
        execute("UPDATE adverse_events\n" +
                "   SET detection_date=\n" +
                "       (SELECT aer.detection_date FROM ae_reports aer WHERE adverse_events.report_id=aer.id)")
        dropColumn("ae_reports", "detection_date")
        setNullable("adverse_events", "detection_date", false)
    }
}