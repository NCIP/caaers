class UpdateCourseAgents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
         addColumn("course_agents","comments", 'string');
    }
      
    void down() {
        dropColumn("course_agents","comments");
    }
}
