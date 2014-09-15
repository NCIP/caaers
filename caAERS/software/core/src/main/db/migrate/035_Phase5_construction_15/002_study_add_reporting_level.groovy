class AddAeReportingLevel extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("studies","ae_reporting_level","string");
    }

    void down() {
        dropColumn("studies","ae_reporting_level");
    }
}