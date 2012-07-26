class ModifyCTCSequences extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
        if (databaseMatches('oracle')){

            execute('drop sequence ctc_versions_id_seq')
            execute('drop sequence ctc_categories_id_seq')
            execute('drop sequence ctc_terms_id_seq')
            execute('drop sequence ctc_grades_id_seq')

            execute("CREATE SEQUENCE seq_ctc_versions_id increment by 1 start with 10 NOMAXVALUE minvalue 1 nocycle  nocache noorder;");
            execute("CREATE SEQUENCE seq_ctc_categories_id increment by 1 start with 500 NOMAXVALUE minvalue 1 nocycle  nocache noorder;");
            execute("CREATE SEQUENCE seq_ctc_terms_id increment by 1 start with 5000 NOMAXVALUE minvalue 1 nocycle  nocache noorder;");
            execute("CREATE SEQUENCE seq_ctc_grades_id increment by 1 start with 50000 NOMAXVALUE minvalue 1 nocycle  nocache noorder;");
        }

        if (databaseMatches('postgresql')) {
            execute('ALTER SEQUENCE ctc_versions_id_seq RESTART WITH 5;')
            execute('ALTER SEQUENCE ctc_categories_id_seq RESTART WITH 500;')
            execute('ALTER SEQUENCE ctc_terms_id_seq RESTART WITH 5000;')
            execute('ALTER SEQUENCE ctc_grades_id_seq RESTART WITH 50000;')
        }
    }

    void down(){
    }
}