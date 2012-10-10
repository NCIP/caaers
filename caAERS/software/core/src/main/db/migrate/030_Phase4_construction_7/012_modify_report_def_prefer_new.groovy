class ModifyRpDef extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("report_calendar_templates","include_non_serious_aes","boolean",defaultValue: 1);

    }

    void down() {
        dropColumn("report_calendar_templates","include_non_serious_aes");
    }
}