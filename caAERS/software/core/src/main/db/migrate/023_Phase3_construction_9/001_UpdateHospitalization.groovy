class UpdateHospitalization extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute('update adverse_events  set hospitalization_code = null where hospitalization_code = 0')
    }

    void down(){
        dropColumn("update adverse_events  set hospitalization_code = 0 where hospitalization_code is null")
    }
}
