class AlterResearchStaff extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
            if (databaseMatches('oracle')) {
            execute('ALTER TABLE research_staffs MODIFY zip VARCHAR2(20)')
            execute('ALTER TABLE site_research_staffs MODIFY zip VARCHAR2(20)')
            } else if (databaseMatches('postgresql')){
        execute('ALTER TABLE research_staffs ALTER zip TYPE text')
        execute('ALTER TABLE site_research_staffs ALTER zip TYPE text')
            } else {
        execute('ALTER TABLE research_staffs ALTER zip TYPE text')
        execute('ALTER TABLE site_research_staffs ALTER zip TYPE text')
            }


    }

    void down() {
       
    }
}