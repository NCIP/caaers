class AlterResearchStaff extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
            if (databaseMatches('oracle')) {
            addColumn("research_staffs", "zip_new", "string")
            execute('UPDATE research_staffs set zip_new = zip')
            execute('UPDATE research_staffs set zip = NULL')
            execute('ALTER TABLE research_staffs MODIFY zip VARCHAR2(20)')
            execute('UPDATE research_staffs set zip = zip_new')
            execute('UPDATE research_staffs set zip_new = NULL')
            dropColumn("research_staffs", "zip_new")

            dropColumn("site_research_staffs", "zip_new")
            addColumn("site_research_staffs", "zip_new", "string")
            execute('UPDATE site_research_staffs set zip_new = zip')
            execute('UPDATE site_research_staffs set zip = NULL')
            execute('ALTER TABLE site_research_staffs MODIFY zip VARCHAR2(20)')
            execute('UPDATE site_research_staffs set zip = zip_new')
            execute('UPDATE site_research_staffs set zip_new = NULL')
            dropColumn("site_research_staffs", "zip_new")
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