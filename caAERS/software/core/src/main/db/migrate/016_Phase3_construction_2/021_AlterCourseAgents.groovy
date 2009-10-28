class AlterCourseAgents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	
            if (databaseMatches('oracle')) {
            	
            	addColumn("course_agents", "dose_amount_new", "string")
            	execute('UPDATE course_agents set dose_amount_new = dose_amount')
            	execute('UPDATE course_agents set dose_amount = NULL')
            	execute('ALTER TABLE course_agents MODIFY dose_amount VARCHAR2(20)')
            	execute('UPDATE course_agents set dose_amount = dose_amount_new')
            	dropColumn("course_agents", "dose_amount_new")
            
            } else {
            	execute('ALTER TABLE course_agents ALTER dose_amount TYPE text')
            }
    }

    void down() {
       
    }
}