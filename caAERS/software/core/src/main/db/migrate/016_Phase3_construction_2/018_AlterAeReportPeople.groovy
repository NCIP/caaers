class AlterAeReportPeople extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
            if (databaseMatches('oracle')) {
            addColumn("ae_report_people", "zip_new", "string")
            execute('UPDATE ae_report_people set zip_new = zip')
            execute('UPDATE ae_report_people set zip = NULL')
            execute('ALTER TABLE ae_report_people MODIFY zip VARCHAR2(20)')
            execute('UPDATE ae_report_people set zip = zip_new')
            execute('UPDATE ae_report_people set zip_new = NULL')
            dropColumn("ae_report_people", "zip_new")
            } else if (databaseMatches('postgresql')){
        execute('ALTER TABLE ae_report_people ALTER zip TYPE text')
            } else {
        execute('ALTER TABLE ae_report_people ALTER zip TYPE text')
            }
    }

    void down() {
       
    }
}