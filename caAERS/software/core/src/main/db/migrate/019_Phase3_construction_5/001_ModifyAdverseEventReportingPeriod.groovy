class ModifyAdverseEventReportingPeriod extends edu.northwestern.bioinformatics.bering.Migration {
	
    void up() {
        addColumn("ae_reporting_periods","retired_indicator", "boolean", defaultValue: 0)
    }

    void down() {
        dropColumn("ae_reporting_periods", "retired_indicator")
    }
}
