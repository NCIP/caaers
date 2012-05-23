class TreatmentAssignmentStudyInterventionSeq extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
		if (databaseMatches('oracle')){
			execute("CREATE SEQUENCE ctc_versions_id_seq increment by 1 start with 1000 NOMAXVALUE minvalue 1 nocycle  nocache noorder;");
			execute("CREATE SEQUENCE ctc_categories_id_seq increment by 1 start with 1000 NOMAXVALUE minvalue 1 nocycle  nocache noorder;");
			execute("CREATE SEQUENCE ctc_terms_id_seq increment by 1 start with 1000 NOMAXVALUE minvalue 1 nocycle  nocache noorder;");
			execute("CREATE SEQUENCE ctc_grades_id_seq increment by 1 start with 1000 NOMAXVALUE minvalue 1 nocycle  nocache noorder;");
		}
		
		if (databaseMatches('postgresql')) {
			execute('create sequence ctc_versions_id_seq INCREMENT 1 START 1000;')
			execute('create sequence ctc_categories_id_seq INCREMENT 1 START 1000;')
			execute('create sequence ctc_terms_id_seq INCREMENT 1 START 1000;')
			execute('create sequence ctc_grades_id_seq INCREMENT 1 START 1000;')
			execute("ALTER TABLE ctc_versions ALTER COLUMN id SET DEFAULT nextval('ctc_versions_id_seq');")
			execute("ALTER TABLE ctc_categories ALTER COLUMN id SET DEFAULT nextval('ctc_categories_id_seq');")
			execute("ALTER TABLE ctc_terms ALTER COLUMN id SET DEFAULT nextval('ctc_terms_id_seq');")
			execute("ALTER TABLE ctc_grades ALTER COLUMN id SET DEFAULT nextval('ctc_grades_id_seq');")
		}
		
		execute("update ctc_versions set name='2.0' where id=2")
		execute("update ctc_versions set name='3.0' where id=3")
		execute("update ctc_versions set name='4.0' where id=4")
		
		execute("ALTER TABLE ctc_grades DROP CONSTRAINT uk_ctc_grade_term");
		
		addColumn("ctc_versions", "grid_id", "string")
		addColumn("ctc_versions", "version", "integer", nullable: false, defaultValue: 0)
		addColumn("ctc_categories", "grid_id", "string")
		addColumn("ctc_categories", "version", "integer", nullable: false, defaultValue: 0)
		addColumn("ctc_terms", "grid_id", "string")
		addColumn("ctc_terms", "version", "integer", nullable: false, defaultValue: 0)
		addColumn("ctc_grades", "grid_id", "string")
		addColumn("ctc_grades", "version", "integer", nullable: false, defaultValue: 0)
		
    }

    void down() {
        execute('drop sequence ctc_versions_id_seq')
        execute('drop sequence ctc_categories_id_seq')
		execute('drop sequence ctc_terms_id_seq')
		execute('drop sequence ctc_grades_id_seq')
		
		execute("update ctc_versions set name='CTC v2.0' where id=2")
		execute("update ctc_versions set name='CTCAE v3.0' where id=3")
		execute("update ctc_versions set name='CTCAE v4.0' where id=4")
		
		dropColumn("ctc_versions", "grid_id")
		dropColumn("ctc_versions", "version")
		dropColumn("ctc_categories", "grid_id")
		dropColumn("ctc_categories", "version")
		dropColumn("ctc_terms", "grid_id")
		dropColumn("ctc_terms", "version")
		dropColumn("ctc_grades", "grid_id")
		dropColumn("ctc_grades", "version")
    }
}
