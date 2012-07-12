class AddToTreatmentsCode extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("treatments","adverse_event_course_dcode","integer",defaultValue: 0, nullable: false);
    }

    void down() {
        dropColumn("treatments","adverse_event_course_dcode");
    }
}