class AlterAeReportPeople extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
            if (databaseMatches('oracle')) {
            execute('ALTER TABLE ae_report_people MODIFY zip VARCHAR2(20)')
            } else if (databaseMatches('postgresql')){
        execute('ALTER TABLE ae_report_people ALTER zip TYPE text')
            } else {
        execute('ALTER TABLE ae_report_people ALTER zip TYPE text')
            }
    }

    void down() {
       
    }
}