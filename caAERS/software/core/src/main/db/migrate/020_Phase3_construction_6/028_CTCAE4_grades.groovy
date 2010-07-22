class CTCAE4_Grades extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
      if (databaseMatches('oracle')) {
          // external("CTCAE4_Grades_Oracle.sql")
      } else if (databaseMatches('postgresql')){
          //singleStatement("UPDATE ctc_grades SET grade_text = 'Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated.' WHERE grade_code = 1 AND term_id IN (SELECT t.id FROM ctc_categories c JOIN ctc_terms t ON t.category_id = c.id JOIN ctc_grades g ON t.id = g.term_id WHERE c.version_id = 4 AND t.other_required = true AND grade_code = 1);");
      }
    }

    void down() {
    }
}
