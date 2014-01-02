class CreateReportIdSequence extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
        if (databaseMatches('oracle')){
            execute("CREATE SEQUENCE safety_report_id_gen_seq increment by 1 start with 1 NOMAXVALUE minvalue 1 nocycle  nocache noorder;");
        }

        if (databaseMatches('postgresql')) {
            execute('ALTER SEQUENCE safety_report_id_gen_seq RESTART WITH 1;')
        }
    }

    void down(){
    }
}