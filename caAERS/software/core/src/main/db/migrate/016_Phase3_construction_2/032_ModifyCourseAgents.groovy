class AlterCourseAgents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
            if (databaseMatches('oracle')) {
            	execute('ALTER TABLE course_agents MODIFY dose_amount VARCHAR2(2000)')
            } 
    }

    void down() {
    	 if (databaseMatches('oracle')) {
    		 execute('ALTER TABLE course_agents MODIFY dose_amount VARCHAR2(20)')
         }
    	
    }
}