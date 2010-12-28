class UpdateReportDefMandatoryField extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("MANDATORY_FIELD_DEFS", "self_referenced", "boolean" , defaultValue:0);
    }

    void down(){
        dropColumn("MANDATORY_FIELD_DEFS", "self_referenced")
    }
}