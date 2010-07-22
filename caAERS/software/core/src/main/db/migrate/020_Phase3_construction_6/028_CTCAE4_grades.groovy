class CTCAE4_Grades extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
      if (databaseMatches('oracle')) {
          external("CTCAE4_Grades_Oracle.sql")
      } else if (databaseMatches('postgresql')){
          external("CTCAE4_Grades_Postgres.sql")  
      }
    }

    void down() {
    }
}
