class UpdateConstraintsAeReportPeople extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
        addColumn("ae_report_people", "title", "string")
        addColumn("ae_report_people", "street", "string")
        addColumn("ae_report_people", "city", "string")
        addColumn("ae_report_people", "state", "string")
        addColumn("ae_report_people", "zip", "integer")
        addColumn("ae_report_people", "code", "integer")
        execute("update ae_report_people set code = 0");
    }

    void down() {
		dropColumn("ae_report_people", "title")
		dropColumn("ae_report_people", "street")
		dropColumn("ae_report_people", "city")
		dropColumn("ae_report_people", "state")
		dropColumn("ae_report_people", "zip")
    }
}