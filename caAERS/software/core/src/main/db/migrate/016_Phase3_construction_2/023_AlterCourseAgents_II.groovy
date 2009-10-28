class AlterCourseAgentsII extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
            if (databaseMatches('oracle')) {
            	
            	//create a temp column
            	addColumn("course_agents", "dose_amount_new", "string")
            	
            	//alter the modified dose
            	execute('UPDATE course_agents set dose_amount_new = modified_dose_amount')
            	execute('UPDATE course_agents set modified_dose_amount = NULL')
            	execute('ALTER TABLE course_agents MODIFY modified_dose_amount VARCHAR2(20)')
            	execute('UPDATE course_agents set modified_dose_amount = dose_amount_new')
            	
            	//drop the temp column
            	dropColumn("course_agents", "dose_amount_new")
            	
            } else {
            	execute('ALTER TABLE course_agents ALTER modified_dose_amount TYPE text')
            }
    }

    void down() {
       
    }
}