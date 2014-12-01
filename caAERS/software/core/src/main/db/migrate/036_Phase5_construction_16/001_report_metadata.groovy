class AddReportMetadata extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("report_schedules","meta_data","string");
    }

    void down() {
        dropColumn("report_schedules","meta_data");
    }
}