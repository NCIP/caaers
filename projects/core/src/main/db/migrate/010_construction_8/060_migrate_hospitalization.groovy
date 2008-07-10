class MigrateHospitalization extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute('update adverse_events set hospitalization_code = 1 where hospitalization_code=2');
        execute('update adverse_events set hospitalization_code = 2 where hospitalization_code=0');
    }

    void down() {
        //not possible
    }
}
