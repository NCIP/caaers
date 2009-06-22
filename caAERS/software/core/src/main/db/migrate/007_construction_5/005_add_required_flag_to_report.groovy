class AddRequiredFlagToReport extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("report_schedules", "required", "boolean", defaultValue: 0, nullable: false);
    }

    void down() {
        dropColumn("report_schedules", "required");
    }
}